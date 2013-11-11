package org.kevoree.library.managers;

import org.kevoree.ComponentInstance;
import org.kevoree.ContainerNode;
import org.kevoree.ContainerRoot;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.log.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 11/11/13
 * Time: 13:28
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@ComponentType
public abstract class ForbidNodeMigrationPaaSManager extends AbstractComponentType {

    private ModelListener listener;

    @Start
    public void start() {
        listener = new ModelListenerImpl(getKevScriptEngineFactory().createKevScriptEngine());
        getModelService().registerModelListener(listener);
    }

    @Stop
    public void stop() {
        getModelService().unregisterModelListener(listener);
        listener = null;
    }

    protected abstract List<ContainerNode> findCorrespondingPlatformNodes(ContainerNode hostingNode);

    protected abstract ContainerNode findAdequatePlatformNode(ComponentInstance component, List<ContainerNode> potentialHostingNodes);

    private void triggerNewReconfiguration(final KevScriptEngine kengine, final ContainerRoot backupProposedModel) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Boolean created = false;
                for (int i = 0; i < 20; i++) {
                    try {
                        Thread.sleep(2000);
                        kengine.atomicInterpretDeploy();
                        created = true;
                        break;
                    } catch (Exception e) {
                        Log.debug("Error while try to update the IaaS configuration due to {}, try number {}", e.getMessage(), i);
                    }
                }
                if (!created) {
                    Log.error("After 20 attempt, it was not able to update the IaaS configuration, so we submit the previous model proposed by IaaS where there is platform migration");
                    getModelService().unregisterModelListener(listener);
                    getModelService().atomicUpdateModel(backupProposedModel);
                    getModelService().registerModelListener(listener);
                }
            }
        }).start();
    }

    class ModelListenerImpl implements ModelListener {

        private KevScriptEngine kengine;

        ModelListenerImpl(KevScriptEngine kengine) {
            this.kengine = kengine;
        }

        @Override
        public boolean preUpdate(ContainerRoot currentModel, ContainerRoot proposedModel) {
            List<ContainerNode[]> potentialUpdatedNodes = new ArrayList<ContainerNode[]>();
            for (ContainerNode node : currentModel.getNodes()) {
                ContainerNode proposedNode = proposedModel.findByPath(node.path(), ContainerNode.class);
                if (proposedNode != null && node.getHost() != null && proposedNode.getHost() != null && !node.getHost().path().equals(proposedNode.getHost().path())) {
                    potentialUpdatedNodes.add(new ContainerNode[]{node, proposedNode});
                }
            }

            if (potentialUpdatedNodes.size() > 0) {
                boolean newReconfiguration = true;
                for (ContainerNode[] nodes : potentialUpdatedNodes) {
                    // find all the platform nodes which are host by nodes[1].getHost()
                    List<ContainerNode> correspondingPlaformNodes = findCorrespondingPlatformNodes(nodes[1].getHost());
                    for (ComponentInstance component : nodes[0].getComponents()) {
                        // find a platform node which is able to host the component
                        ContainerNode adequatPlatformNode = findAdequatePlatformNode(component, correspondingPlaformNodes);
                        if (adequatPlatformNode != null) {
                            kengine.addVariable("componentName", component.getName());
                            kengine.addVariable("currentNodeName", nodes[0].getName());
                            kengine.addVariable("newNodeName", adequatPlatformNode.getName());
                            kengine.append("moveComponent {componentName}@{currentNodeName} => {newNodeName}");
                        } else {
                            newReconfiguration = false;
                        }
                    }
                }

                if (newReconfiguration) {
                    // try to apply a new configuration. If it is not possible the PaaSManager resubmit the model proposed by the infrastructure and accept it
                    triggerNewReconfiguration(kengine, proposedModel);
                }
                return newReconfiguration;
            } else {
                return true;
            }
        }

        @Override
        public boolean initUpdate(ContainerRoot currentModel, ContainerRoot proposedModel) {
            return true;
        }

        @Override
        public boolean afterLocalUpdate(ContainerRoot currentModel, ContainerRoot proposedModel) {
            return true;
        }

        @Override
        public void modelUpdated() {
        }

        @Override
        public void preRollback(ContainerRoot currentModel, ContainerRoot proposedModel) {
        }

        @Override
        public void postRollback(ContainerRoot currentModel, ContainerRoot proposedModel) {
        }
    }
}
