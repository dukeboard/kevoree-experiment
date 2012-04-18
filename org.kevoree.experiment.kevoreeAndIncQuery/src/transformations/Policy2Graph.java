package transformations;

import grapho.Edge;
import grapho.GraphElement;
import grapho.GraphO;
import grapho.GraphoFactory;
import grapho.GraphoPackage;
import grapho.Node;
import grapho.impl.NodeImpl;

import java.io.IOException;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;

import rbac.Operation;
import rbac.Permission;
import rbac.Policy;
import rbac.PolicyElement;
import rbac.RbacFactory;
import rbac.RbacPackage;
import rbac.Role;
import rbac.User;
import rbac.rbac.impl.PermissionImpl;
import rbac.rbac.impl.ResourceImpl;
import rbac.rbac.impl.RoleImpl;
import rbac.rbac.impl.UserImpl;
import rbacTools.editor.PolicyEditor;
import rbacTools.generator.PolicyGenerator;

public class Policy2Graph {
	private ResourceSet resourceSetMetamodel;
	private Resource resourceModel;
	private Policy policy;

	public Policy2Graph(Policy p ){
		policy = p;
	}
	
	public Policy2Graph(){
		policy = RbacFactory.eINSTANCE.createPolicy();
	}
	
	public void loadPolicyModel(String path) {
		// REGISTER THE METAMODEL
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(RbacPackage.eNS_URI,
				RbacPackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
		// LOAD THE MODEL
		resourceModel = resourceSetMetamodel.createResource(URI
				.createFileURI(path));
		try {
			resourceModel.load(null);
		} catch (IOException e) {
			System.out.println("error during the model loading step");
			e.printStackTrace();
		}
		// INSTANTIATE ROOTELEMENT WITH THE CONTAINERROOT OF THE LOADED MODEL
		policy = (Policy) resourceModel.getContents().get(0);
		for (PolicyElement e : policy.getElements()) {
			System.out.println(e.getName());
		}
	}

	public void savePolicyModel(String path) {
		// REGISTER THE METAMODEL
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(RbacPackage.eNS_URI,
				RbacPackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());

		resourceModel = resourceSetMetamodel.createResource(URI
				.createFileURI("models/" + path + ".xmi"));

		resourceModel.getContents().add(policy);

		try {
			resourceModel.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveGraphOModel(String path, GraphO g) {
		// REGISTER THE METAMODEL
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(GraphoPackage.eNS_URI,
				GraphoPackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());

		resourceModel = resourceSetMetamodel.createResource(URI
				.createFileURI("models/" + path + ".xmi"));

		resourceModel.getContents().add(g);

		try {
			resourceModel.save(Collections.EMPTY_MAP);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	public GraphO transformation(){
		GraphoFactory factory = GraphoFactory.eINSTANCE;
		GraphO g = factory.createGraphO();
		//adding simple nodes
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				Node n = factory.createNode();
				n.setColor("black");
				n.setLabel(e.getName());
				n.setName(e.getName());
				n.setShape("ellipse");
				n.setStyle("solid");
				g.getElements().add(n);
			}
			if (e instanceof RoleImpl) {
				Node n = factory.createNode();
				n.setColor("black");
				n.setLabel(e.getName());
				n.setName(e.getName());
				n.setShape("ellipse");
				n.setStyle("solid");
				g.getElements().add(n);
			}
			if (e instanceof PermissionImpl) {
				Node n = factory.createNode();
				n.setColor("black");
				n.setLabel(e.getName());
				n.setName(e.getName());
				n.setShape("ellipse");
				n.setStyle("solid");
				g.getElements().add(n);
			}
			if (e instanceof ResourceImpl) {
				Node n = factory.createNode();
				n.setColor("black");
				n.setLabel(e.getName());
				n.setName(e.getName());
				n.setShape("ellipse");
				n.setStyle("solid");
				g.getElements().add(n);
			}
		}

		// adding relationships
		for (PolicyElement e : policy.getElements()) {
			//user->assignedRoles
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					Edge ed = factory.createEdge();
					ed.setName(e.getName() + r.getName());
					ed.setColor("black");
					ed.setStyle("solid");
					ed.setConstraintRank(true);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(getNodeByName(g, r.getName()));
					g.getElements().add(ed);
				}
			}
			
			//user->user
			if (e instanceof UserImpl) {
				for (User u : ((User) e).getDelegations()) {
					Edge ed = factory.createEdge();
					ed.setName(e.getName() + u.getName());
					ed.setColor("black");
					ed.setStyle("dashed");
					ed.setConstraintRank(false);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(getNodeByName(g, u.getName()));
					g.getElements().add(ed);
				}
			}

			//role->assignedPermissions
			if (e instanceof RoleImpl) {
				for (Permission p : ((Role) e).getPermissions()) {
					Edge ed = factory.createEdge();
					ed.setName(e.getName() + p.getName());
					ed.setColor("black");
					ed.setStyle("solid");
					ed.setConstraintRank(true);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(getNodeByName(g, p.getName()));
					g.getElements().add(ed);
				}
			}
			
			//SSOD role->role 
			if (e instanceof RoleImpl) {
				for (Role r : ((Role) e).getSsod()) {
					Edge ed = factory.createEdge();
					ed.setName(e.getName() + r.getName());
					ed.setColor("red");
					ed.setStyle("dashed");
					ed.setConstraintRank(false);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(getNodeByName(g, r.getName()));
					g.getElements().add(ed);
				}
			}

			//permission->operation->resource
			if (e instanceof PermissionImpl) {
				for (Operation o : ((Permission) e).getOperations()) {
					Node n = factory.createNode();
					n.setColor("black");
					n.setLabel(o.getName());
					n.setName(e.getName() + o.getName());
					n.setShape("ellipse");
					n.setStyle("solid");
					g.getElements().add(n);

					Edge ed = factory.createEdge();
					ed.setName(e.getName() + o.getName());
					ed.setColor("black");
					ed.setStyle("solid");
					ed.setConstraintRank(true);
					ed.setNodeA(getNodeByName(g, e.getName()));
					ed.setNodeB(n);
					g.getElements().add(ed);
					for (rbac.Resource r : o.getResources()) {
						Edge edge = factory.createEdge();
						edge.setName(o.getName() + r.getName());
						edge.setColor("black");
						edge.setStyle("solid");
						edge.setConstraintRank(true);
						edge.setNodeA(n);
						edge.setNodeB(getNodeByName(g, r.getName()));
						g.getElements().add(edge);
					}
				}
			}
		}
		return g;
	}
	
	public void transform2GraphO() {
		saveGraphOModel("graphOTest", transformation());
	}

	public Node getNodeByName(GraphO g, String nodeName) {
		Node res = null;
		for (GraphElement e : g.getElements()) {
			if (e instanceof NodeImpl && e.getName().equals(nodeName)) {
				res = (Node) e;
			}
		}
		return res;
	}
}