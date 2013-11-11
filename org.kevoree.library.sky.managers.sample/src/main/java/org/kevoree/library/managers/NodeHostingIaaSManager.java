package org.kevoree.library.managers;

import org.kevoree.ContainerNode;
import org.kevoree.ContainerRoot;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.library.managers.helper.ModelHelper;
import org.kevoree.log.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 11/11/13
 * Time: 14:06
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@ComponentType
public abstract class NodeHostingIaaSManager extends AbstractComponentType {
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

    protected abstract ContainerNode findAdequatHostingNodes(ContainerNode nodeToHost, List<ContainerNode> iaasNodes);

    List<ContainerNode> findNonHostedPlatformNodes(List<ContainerNode> paasNodes) {
        List<ContainerNode> nonHostedNodes = new ArrayList<ContainerNode>();
        for (ContainerNode node : paasNodes) {
            if (node.getHost() == null) {
                nonHostedNodes.add(node);
            }
        }
        return nonHostedNodes;
    }

    private void triggerNewReconfiguration(final KevScriptEngine kengine) {
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
            Log.error("After 20 attempt, it was not able to update the IaaS configuration, so we are not able to host the new nodes");
        }
    }

    class ModelListenerImpl implements ModelListener {

        private KevScriptEngine kengine;

        ModelListenerImpl(KevScriptEngine kengine) {
            this.kengine = kengine;
        }

        @Override
        public boolean preUpdate(ContainerRoot currentModel, ContainerRoot proposedModel) {
            return true;
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
            ContainerRoot currentModel = getModelService().getLastModel();
            // find all platform node which are not hosted by a iaas node
            List<ContainerNode> newNodesToHost = findNonHostedPlatformNodes(ModelHelper.findPaaSNodes(currentModel));

            boolean newReconfigurationNeeded = false;

            for (ContainerNode nodeToHost : newNodesToHost) {
                // find a iaas node which is able to host the nodeToHost
                ContainerNode hostingNode = findAdequatHostingNodes(nodeToHost, ModelHelper.findIaaSNodes(currentModel));
                kengine.addVariable("nodeToHostName", nodeToHost.getName());
                kengine.addVariable("hostingNodeName", hostingNode.getName());
                kengine.append("addChild {nodeToHostName}@{hostingNodeName}");
                newReconfigurationNeeded = true;
            }

            if (newReconfigurationNeeded) {
                triggerNewReconfiguration(kengine);
            }
        }

        @Override
        public void preRollback(ContainerRoot currentModel, ContainerRoot proposedModel) {
        }

        @Override
        public void postRollback(ContainerRoot currentModel, ContainerRoot proposedModel) {
        }
    }
}
