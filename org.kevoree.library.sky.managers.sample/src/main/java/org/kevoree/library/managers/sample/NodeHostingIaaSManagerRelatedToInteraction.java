package org.kevoree.library.managers.sample;

import org.kevoree.ContainerNode;
import org.kevoree.library.managers.NodeHostingIaaSManager;
import org.kevoree.library.managers.helper.ModelHelper;
import org.kevoree.modeling.api.KMFContainer;
import org.kevoree.modeling.api.util.ModelVisitor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 11/11/13
 * Time: 15:10
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public class NodeHostingIaaSManagerRelatedToInteraction extends NodeHostingIaaSManager {

    @Override
    protected ContainerNode findAdequatHostingNodes(ContainerNode nodeToHost, List<ContainerNode> iaasNodes) {
        return selectOneHostingNode(selectMoreAppropriateHostingNodes(findPotentialHostingNodesAccordingToConnectingPlatformNodes(nodeToHost)));
    }

    private Map<ContainerNode, Integer> findPotentialHostingNodesAccordingToConnectingPlatformNodes(final ContainerNode nodeToHost) {
        final Map<ContainerNode, Integer> connectingPlatformNode = new HashMap<ContainerNode, Integer>();

        nodeToHost.visit(new ModelVisitor(){
            public void visit(KMFContainer elem, String refNameInParent, KMFContainer parent) {
                if (elem instanceof ContainerNode && elem.path() != nodeToHost.path() && ModelHelper.isPaaSNode((ContainerNode)elem)) {
                    ContainerNode host = ((ContainerNode)elem).getHost();
                    if (!connectingPlatformNode.containsKey(host)) {
                        connectingPlatformNode.put(host, 1);
                    } else {
                        connectingPlatformNode.put(host, connectingPlatformNode.get(((ContainerNode)elem).getHost()) + 1);
                    }
                }
            }

        }, true, true, true);
        return connectingPlatformNode;
    }

    private List<ContainerNode> selectMoreAppropriateHostingNodes(Map<ContainerNode, Integer> hostingNodes) {
        List<ContainerNode> potentialParents = new ArrayList<ContainerNode>();
        int min = Integer.MAX_VALUE;

        for (ContainerNode node : hostingNodes.keySet()) {
            int nbConnnections = hostingNodes.get(node);
            if (nbConnnections < min) {
                min = nbConnnections;
                potentialParents.clear();
                potentialParents.add(node);
            } else if (nbConnnections == min) {
                potentialParents.add(node);
            }
        }
        return potentialParents;
    }

    private ContainerNode selectOneHostingNode(List<ContainerNode> hostingNodes) {
        ContainerNode potentialParent = null;
        int min = Integer.MAX_VALUE;

        for (ContainerNode node : hostingNodes) {
            int nbChildNodes = node.getHosts().size();
            if (nbChildNodes < min) {
                min = nbChildNodes;
                potentialParent = node;
            }
        }
        return potentialParent;
    }
}
