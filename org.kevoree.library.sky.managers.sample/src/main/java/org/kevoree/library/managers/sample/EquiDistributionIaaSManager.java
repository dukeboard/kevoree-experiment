package org.kevoree.library.managers.sample;

import org.kevoree.ContainerNode;
import org.kevoree.library.managers.NodeHostingIaaSManager;

import java.util.List;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 11/11/13
 * Time: 15:10
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public class EquiDistributionIaaSManager extends NodeHostingIaaSManager {

    @Override
    protected ContainerNode findAdequatHostingNodes(ContainerNode nodeToHost, List<ContainerNode> iaasNodes) {
        ContainerNode potentialParent = null;
        int min = Integer.MAX_VALUE;

        for (ContainerNode node : iaasNodes) {
            int nbChildNodes = node.getHosts().size();
            if (nbChildNodes < min) {
                min = nbChildNodes;
                potentialParent = node;
            }
        }
        return potentialParent;
    }
}
