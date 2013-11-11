package org.kevoree.library.managers.sample;

import org.kevoree.ComponentInstance;
import org.kevoree.ContainerNode;
import org.kevoree.library.managers.ForbidNodeMigrationPaaSManager;

import java.util.List;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 11/11/13
 * Time: 15:22
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public class FordbidNodeMigrationPaaSManagerWithRandomComponentMigration extends ForbidNodeMigrationPaaSManager {
    @Override
    protected List<ContainerNode> findCorrespondingPlatformNodes(ContainerNode hostingNode) {
        return hostingNode.getHost().getHosts();
    }

    @Override
    protected ContainerNode findAdequatePlatformNode(ComponentInstance component, List<ContainerNode> potentialHostingNodes) {
        ContainerNode potentialParent = null;
        int min = Integer.MAX_VALUE;

        for (ContainerNode node : potentialHostingNodes) {
            int nbChildNodes = node.getHosts().size();
            if (nbChildNodes < min) {
                min = nbChildNodes;
                potentialParent = node;
            }
        }
        return potentialParent;
    }
}
