package org.kevoree.library.managers.helper;

import org.kevoree.ContainerNode;
import org.kevoree.ContainerRoot;
import org.kevoree.NodeType;
import org.kevoree.TypeDefinition;
import org.kevoree.library.sky.api.IaaSNode;
import org.kevoree.library.sky.api.PaaSNode;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 11/11/13
 * Time: 14:27
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public class ModelHelper {

    public static void findCloudNodes(ContainerRoot model, List<ContainerNode> iaasNodes, List<ContainerNode> paasNodes) {
        for (ContainerNode node : model.getNodes()) {
            if (isIaaSNode(node)) {
                iaasNodes.add(node);
            }
            if (isPaaSNode(node)) {
                paasNodes.add(node);
            }
        }
    }

    public static List<ContainerNode> findIaaSNodes(ContainerRoot model) {
        List<ContainerNode> nodes = new ArrayList<ContainerNode>();
        for (ContainerNode node : model.getNodes()) {
            if (isIaaSNode(node)) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    private static boolean isIaaSNode(ContainerNode node) {
        return isIaaSNodeTypeDefinition((NodeType) node.getTypeDefinition());
    }

    public static boolean isIaaSNodeTypeDefinition(NodeType nodeType) {
        if (nodeType.getName().equals(IaaSNode.class.getName())) {
            return true;
        }
        boolean isByInheritance = false;
        for (TypeDefinition superType : nodeType.getSuperTypes()) {
            if (superType instanceof NodeType) {
                isByInheritance = isIaaSNodeTypeDefinition((NodeType) superType);
                if (isByInheritance) {
                    break;
                }
            }
        }
        return isByInheritance;
    }

    public static List<ContainerNode> findPaaSNodes(ContainerRoot model) {
        List<ContainerNode> nodes = new ArrayList<ContainerNode>();
        for (ContainerNode node : model.getNodes()) {
            if (isPaaSNode(node)) {
                nodes.add(node);
            }
        }
        return nodes;
    }

    private static boolean isPaaSNode(ContainerNode node) {
        return isPaaSNodeTypeDefinition((NodeType) node.getTypeDefinition());
    }

    public static boolean isPaaSNodeTypeDefinition(NodeType nodeType) {
        if (nodeType.getName().equals(PaaSNode.class.getName())) {
            return true;
        }
        boolean isByInheritance = false;
        for (TypeDefinition superType : nodeType.getSuperTypes()) {
            if (superType instanceof NodeType) {
                isByInheritance = isPaaSNodeTypeDefinition((NodeType) superType);
                if (isByInheritance) {
                    break;
                }
            }
        }
        return isByInheritance;
    }
}
