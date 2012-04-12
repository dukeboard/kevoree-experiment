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
import rbac.rbac.editor.PolicyEditor;
import rbac.rbac.generator.PolicyGenerator;
import rbac.rbac.impl.PermissionImpl;
import rbac.rbac.impl.ResourceImpl;
import rbac.rbac.impl.RoleImpl;
import rbac.rbac.impl.UserImpl;

public class Policy2Graph {
	private ResourceSet resourceSetMetamodel;
	private Resource resourceModel;
	private Policy policy;

	public void loadPolicyModel() {
		// REGISTER THE METAMODEL
		resourceSetMetamodel = new ResourceSetImpl();
		resourceSetMetamodel.getPackageRegistry().put(RbacPackage.eNS_URI,
				RbacPackage.eINSTANCE);
		resourceSetMetamodel.getResourceFactoryRegistry()
				.getExtensionToFactoryMap()
				.put("xmi", new XMIResourceFactoryImpl());
		// LOAD THE MODEL
		resourceModel = resourceSetMetamodel.createResource(URI
				.createFileURI("models/Policy.xmi"));
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

		initPolicy(5, 3);
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

	public void initPolicy(int number, int numberOperationByPermission) {
		RbacFactory factory = RbacFactory.eINSTANCE;
		policy = factory.createPolicy();
		for (int i = 0; i < number; i++) {
			rbac.User u = factory.createUser();
			u.setName("user" + i);
			policy.getElements().add(u);
			rbac.Role r = factory.createRole();
			r.setName("role" + i);
			policy.getElements().add(r);
			Permission p = factory.createPermission();
			p.setName("perm" + i);
			policy.getElements().add(p);
			rbac.Resource re = factory.createResource();
			re.setName("obj" + i);
			policy.getElements().add(re);
			for (int j = 0; j < numberOperationByPermission; j++) {
				Operation op = factory.createOperation();
				op.setName("ope" + j);
				p.getOperations().add(op);
			}
			// only one role
			u.getAssignedRoles().add(r);
			r.getPermissions().add(p);
		}

		// everibody permitted to all

		// for (PolicyElement u : policy.getElements()) {
		// if (u instanceof UserImpl) {
		// for (PolicyElement r : policy.getElements()) {
		// if (r instanceof RoleImpl) {
		// ((User)u).getAssignedRoles().add((Role)r);
		// }
		// }
		// }
		// }
		//
		// for (PolicyElement r : policy.getElements()) {
		// if (r instanceof RoleImpl) {
		// for (PolicyElement p : policy.getElements()) {
		// if (p instanceof PermissionImpl) {
		// ((Role)r).getPermissions().add((Permission)p);
		// }
		// }
		// }
		// }

		for (PolicyElement e : policy.getElements()) {
			if (e instanceof PermissionImpl) {
				for (Operation o : ((Permission) e).getOperations()) {
					for (PolicyElement res : policy.getElements()) {
						if (res instanceof ResourceImpl) {
							o.getResources().add((rbac.Resource) res);
						}
					}
				}
			}
		}
	}
	
	public void initPolicyThroughPolicyEditor(int number, int numberOperationByPermission) {
		RbacFactory factory = RbacFactory.eINSTANCE;
		policy = factory.createPolicy();
		PolicyEditor pe = new PolicyEditor(policy);
		for (int i = 0; i < number; i++) {
			pe.addUser("user" + i);
			pe.addRole("role" + i);
			pe.addPermission("perm" + i);
			pe.addResource("obj" + i);
			for (int j = 0; j < numberOperationByPermission; j++) {
				pe.addOperation("ope" + j,"perm" + i);
			}
			// only one role by user
			pe.addUserRoleAssignment("user" + i, "role" + i);
			pe.addRolePermissionAssignment("role" + i,"perm" + i);
		}

		for (Permission p : pe.getPermissions()) {
			for (Operation o : p.getOperations()) {
				for (rbac.Resource res : pe.getResources()) {
					pe.addOperationResourceAssignment(p.getName(), o.getName(), res.getName());
				}
			}
		}
	}

	public void initPolicyExamples(int number, int numberOperationByPermission,
			boolean reducedPermission, boolean cyclicDelegation,boolean cyclicHierarchy, boolean ssod,
			boolean dsod) {
		RbacFactory factory = RbacFactory.eINSTANCE;
		policy = factory.createPolicy();
		for (int i = 0; i < number; i++) {
			rbac.User u = factory.createUser();
			u.setName("user" + i);
			policy.getElements().add(u);
			rbac.Role r = factory.createRole();
			r.setName("role" + i);
			policy.getElements().add(r);
			Permission p = factory.createPermission();
			p.setName("perm" + i);
			policy.getElements().add(p);
			rbac.Resource re = factory.createResource();
			re.setName("obj" + i);
			policy.getElements().add(re);
			for (int j = 0; j < numberOperationByPermission; j++) {
				Operation op = factory.createOperation();
				op.setName("ope" + j);
				p.getOperations().add(op);
			}

			// only one role by user
			if (reducedPermission) {
				u.getAssignedRoles().add(r);
				r.getPermissions().add(p);
			}
		}

		// everibody permitted to all
		if (!reducedPermission) {
			for (PolicyElement u : policy.getElements()) {
				if (u instanceof UserImpl) {
					for (PolicyElement r : policy.getElements()) {
						if (r instanceof RoleImpl) {
							((User) u).getAssignedRoles().add((Role) r);
						}
					}
				}
			}

			for (PolicyElement r : policy.getElements()) {
				if (r instanceof RoleImpl) {
					for (PolicyElement p : policy.getElements()) {
						if (p instanceof PermissionImpl) {
							((Role) r).getPermissions().add((Permission) p);
						}
					}
				}
			}
		}

		for (PolicyElement e : policy.getElements()) {
			if (e instanceof PermissionImpl) {
				for (Operation o : ((Permission) e).getOperations()) {
					for (PolicyElement res : policy.getElements()) {
						if (res instanceof ResourceImpl) {
							o.getResources().add((rbac.Resource) res);
						}
					}
				}
			}
		}

		if (cyclicDelegation) {
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof UserImpl) {
					for (PolicyElement de : policy.getElements()) {
						if (de instanceof UserImpl && (!e.equals(de))) {
							((User) e).getDelegations().add((User) de);
						}
					}
				}
			}
		}
		
		if (cyclicHierarchy) {
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof RoleImpl) {
					for (PolicyElement de : policy.getElements()) {
						if (de instanceof RoleImpl && (!e.equals(de))) {
							((Role) e).getHierarchy().add((Role) de);
						}
					}
				}
			}
		}


		if (ssod) {
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof RoleImpl) {
					for (PolicyElement ee : policy.getElements()) {
						if (ee instanceof RoleImpl && (!e.equals(ee))) {
							((Role) e).getSsod().add((Role) ee);
						}
					}
				}
			}
		}

		if (dsod) {
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof UserImpl) {
					rbac.Session s = factory.createSession();
					s.setName("session" + e.getName());
					((User) e).setSession(s);
					for (PolicyElement ee : policy.getElements()) {
						if (ee instanceof RoleImpl) {
							s.getActiveRoles().add((Role) ee);
						}
					}
				}
			}
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof RoleImpl) {
					for (PolicyElement ee : policy.getElements()) {
						if (ee instanceof RoleImpl && (!e.equals(ee))) {
							((Role) e).getDsod().add((Role) ee);
						}
					}
				}
			}
		}
	}
	
	public void transform2GraphO() {
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
		saveGraphOModel("graphOTest", g);
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

	public void initPolicyExample1() {
		policy = RbacFactory.eINSTANCE.createPolicy();
		// USERS
		rbac.User gary = RbacFactory.eINSTANCE.createUser();
		gary.setName("Gary");
		rbac.User mary = RbacFactory.eINSTANCE.createUser();
		mary.setName("Mary");
		rbac.User alicia = RbacFactory.eINSTANCE.createUser();
		alicia.setName("Alicia");
		rbac.User boris = RbacFactory.eINSTANCE.createUser();
		boris.setName("Boris");
		// adding users
		policy.getElements().add(gary);
		policy.getElements().add(mary);
		policy.getElements().add(alicia);
		policy.getElements().add(boris);

		// ROLES
		rbac.Role employee = RbacFactory.eINSTANCE.createRole();
		employee.setName("employee");
		rbac.Role relationshipManager = RbacFactory.eINSTANCE.createRole();
		relationshipManager.setName("relationshipManager");
		rbac.Role rescuer = RbacFactory.eINSTANCE.createRole();
		rescuer.setName("rescuer");
		// adding roles
		policy.getElements().add(employee);
		policy.getElements().add(relationshipManager);
		policy.getElements().add(rescuer);

		// PERMISSIONS
		Permission consult = RbacFactory.eINSTANCE.createPermission();
		consult.setName("consult");
		Permission modify = RbacFactory.eINSTANCE.createPermission();
		modify.setName("modify");
		Permission admin = RbacFactory.eINSTANCE.createPermission();
		admin.setName("admin");
		// adding permissions
		policy.getElements().add(consult);
		policy.getElements().add(modify);
		policy.getElements().add(admin);

		// OPERATIONS
		Operation consultRead = RbacFactory.eINSTANCE.createOperation();
		consultRead.setName("read");

		Operation modifyRead = RbacFactory.eINSTANCE.createOperation();
		modifyRead.setName("read");
		Operation modifyUpdate = RbacFactory.eINSTANCE.createOperation();
		modifyUpdate.setName("update");

		Operation rescuerRead = RbacFactory.eINSTANCE.createOperation();
		rescuerRead.setName("read");
		Operation rescuerUpdate = RbacFactory.eINSTANCE.createOperation();
		rescuerUpdate.setName("update");
		Operation rescuerCreate = RbacFactory.eINSTANCE.createOperation();
		rescuerCreate.setName("create");
		Operation rescuerDelete = RbacFactory.eINSTANCE.createOperation();
		rescuerDelete.setName("delete");

		// RESOURCES
		rbac.Resource employeeAddressBook = RbacFactory.eINSTANCE.createResource();
		employeeAddressBook.setName("employeeAddressBook");

		rbac.Resource externalContactAddressBook = RbacFactory.eINSTANCE
				.createResource();
		externalContactAddressBook.setName("externalContactAddressBook");

		rbac.Resource emergencyAddressBook = RbacFactory.eINSTANCE.createResource();
		emergencyAddressBook.setName("emergencyAddressBook");
		policy.getElements().add(employeeAddressBook);
		policy.getElements().add(externalContactAddressBook);
		policy.getElements().add(emergencyAddressBook);

		// USERS-ROLES ASSIGNMENT
		gary.getAssignedRoles().add(employee);
		mary.getAssignedRoles().add(employee);
		boris.getAssignedRoles().add(employee);
		boris.getAssignedRoles().add(rescuer);
		alicia.getAssignedRoles().add(employee);
		alicia.getAssignedRoles().add(relationshipManager);

		// ROLES-PERMISSIONS ASSIGNMENT
		employee.getPermissions().add(consult);
		relationshipManager.getPermissions().add(modify);
		rescuer.getPermissions().add(admin);

		// OPERATIONS-RESOURCES ASSIGNMENT
		consultRead.getResources().add(employeeAddressBook);

		//modifyRead.getResources().add(employeeAddressBook);
		modifyRead.getResources().add(externalContactAddressBook);
		modifyUpdate.getResources().add(employeeAddressBook);
		modifyUpdate.getResources().add(externalContactAddressBook);

		rescuerRead.getResources().add(emergencyAddressBook);
		rescuerUpdate.getResources().add(emergencyAddressBook);
		rescuerDelete.getResources().add(emergencyAddressBook);
		rescuerCreate.getResources().add(emergencyAddressBook);

		// PERMISSIONS-OPERATIONS ASSIGNMENT
		consult.getOperations().add(consultRead);

		modify.getOperations().add(modifyRead);
		modify.getOperations().add(modifyUpdate);

		admin.getOperations().add(rescuerRead);
		admin.getOperations().add(rescuerUpdate);
		admin.getOperations().add(rescuerDelete);
		admin.getOperations().add(rescuerCreate);
	}
	
	public void initPolicyExample2() {
		policy = RbacFactory.eINSTANCE.createPolicy();
		// USERS
		rbac.User gary = RbacFactory.eINSTANCE.createUser();
		gary.setName("Gary");
		rbac.User mary = RbacFactory.eINSTANCE.createUser();
		mary.setName("Mary");
		rbac.User alicia = RbacFactory.eINSTANCE.createUser();
		alicia.setName("Alicia");
		rbac.User boris = RbacFactory.eINSTANCE.createUser();
		boris.setName("Boris");
		// adding users
		policy.getElements().add(gary);
		policy.getElements().add(mary);
		policy.getElements().add(alicia);
		policy.getElements().add(boris);

		// ROLES
		rbac.Role employee = RbacFactory.eINSTANCE.createRole();
		employee.setName("employee");
		rbac.Role relationshipManager = RbacFactory.eINSTANCE.createRole();
		relationshipManager.setName("relationshipManager");
		rbac.Role rescuer = RbacFactory.eINSTANCE.createRole();
		rescuer.setName("rescuer");
		rbac.Role assistant = RbacFactory.eINSTANCE.createRole();
		assistant.setName("assistant");
		// adding roles
		policy.getElements().add(employee);
		policy.getElements().add(relationshipManager);
		policy.getElements().add(rescuer);
		policy.getElements().add(assistant);

		// PERMISSIONS
		Permission consult = RbacFactory.eINSTANCE.createPermission();
		consult.setName("consult");
		Permission modify = RbacFactory.eINSTANCE.createPermission();
		modify.setName("modify");
		Permission admin = RbacFactory.eINSTANCE.createPermission();
		admin.setName("admin");
		// adding permissions
		policy.getElements().add(consult);
		policy.getElements().add(modify);
		policy.getElements().add(admin);

		// OPERATIONS
		Operation consultRead = RbacFactory.eINSTANCE.createOperation();
		consultRead.setName("read");

		Operation modifyRead = RbacFactory.eINSTANCE.createOperation();
		modifyRead.setName("read");
		Operation modifyUpdate = RbacFactory.eINSTANCE.createOperation();
		modifyUpdate.setName("update");

		Operation rescuerRead = RbacFactory.eINSTANCE.createOperation();
		rescuerRead.setName("read");
		Operation rescuerUpdate = RbacFactory.eINSTANCE.createOperation();
		rescuerUpdate.setName("update");
		Operation rescuerCreate = RbacFactory.eINSTANCE.createOperation();
		rescuerCreate.setName("create");
		Operation rescuerDelete = RbacFactory.eINSTANCE.createOperation();
		rescuerDelete.setName("delete");

		// RESOURCES
		rbac.Resource employeeAddressBook = RbacFactory.eINSTANCE.createResource();
		employeeAddressBook.setName("employeeAddressBook");

		rbac.Resource externalContactAddressBook = RbacFactory.eINSTANCE
				.createResource();
		externalContactAddressBook.setName("externalContactAddressBook");

		rbac.Resource emergencyAddressBook = RbacFactory.eINSTANCE.createResource();
		emergencyAddressBook.setName("emergencyAddressBook");
		policy.getElements().add(employeeAddressBook);
		policy.getElements().add(externalContactAddressBook);
		policy.getElements().add(emergencyAddressBook);

		// USERS-ROLES ASSIGNMENT
		gary.getAssignedRoles().add(employee);
		mary.getAssignedRoles().add(employee);
		mary.getAssignedRoles().add(assistant);
		boris.getAssignedRoles().add(employee);
		boris.getAssignedRoles().add(rescuer);
		alicia.getAssignedRoles().add(employee);
		alicia.getAssignedRoles().add(relationshipManager);

		// ROLES-PERMISSIONS ASSIGNMENT
		employee.getPermissions().add(consult);
		relationshipManager.getPermissions().add(modify);
		rescuer.getPermissions().add(admin);
		assistant.getPermissions().add(modify);

		// OPERATIONS-RESOURCES ASSIGNMENT
		consultRead.getResources().add(employeeAddressBook);

		//modifyRead.getResources().add(employeeAddressBook);
		modifyRead.getResources().add(externalContactAddressBook);
		modifyUpdate.getResources().add(employeeAddressBook);
		modifyUpdate.getResources().add(externalContactAddressBook);

		rescuerRead.getResources().add(emergencyAddressBook);
		rescuerUpdate.getResources().add(emergencyAddressBook);
		rescuerDelete.getResources().add(emergencyAddressBook);
		rescuerCreate.getResources().add(emergencyAddressBook);

		// PERMISSIONS-OPERATIONS ASSIGNMENT
		consult.getOperations().add(consultRead);

		modify.getOperations().add(modifyRead);
		modify.getOperations().add(modifyUpdate);

		admin.getOperations().add(rescuerRead);
		admin.getOperations().add(rescuerUpdate);
		admin.getOperations().add(rescuerDelete);
		admin.getOperations().add(rescuerCreate);
	}
	
	public void initPolicyGood(){
		PolicyGenerator policyGenerator = new PolicyGenerator(policy);		
		policy = policyGenerator.initPolicyExamples(3, 3, false, false, false, false, false);
	}
	
	
	
	
	public static void main(String[] args) {
		Policy2Graph p = new Policy2Graph();
		
		//p.initPolicyExamples(50, 2, false, true,true,true,true);
		//p.initPolicyExample1();
		//p.initPolicyExample2();
		//p.initPolicyThroughPolicyEditor(3, 3);
		p.initPolicyGood();
		// p.loadPolicyModel();
		// p.savePolicyModel("tee");
		p.transform2GraphO();
	}
}