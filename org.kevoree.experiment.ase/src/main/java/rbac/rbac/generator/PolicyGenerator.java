package rbac.rbac.generator;

import java.util.Vector;

import rbac.rbac.Operation;
import rbac.rbac.Permission;
import rbac.rbac.Policy;
import rbac.rbac.PolicyElement;
import rbac.rbac.RbacFactory;
import rbac.rbac.Role;
import rbac.rbac.Session;
import rbac.rbac.User;
import rbac.rbac.impl.PermissionImpl;
import rbac.rbac.impl.ResourceImpl;
import rbac.rbac.impl.RoleImpl;
import rbac.rbac.impl.UserImpl;
import utils.random.RandomSingleton;

public class PolicyGenerator {
	
	private Policy policy;
	private Vector<String> addressBookOperations;
	
	public PolicyGenerator(Policy p){
		policy = p;
		addressBookOperations = new Vector<String>();
		addressBookOperations.add("create");
		addressBookOperations.add("read");
		addressBookOperations.add("update");
		addressBookOperations.add("delete");
	}
	
	
	public void initPolicy() {
		policy = RbacFactory.eINSTANCE.createPolicy();
		// USERS
		rbac.rbac.User gary = RbacFactory.eINSTANCE.createUser();
		gary.setName("Gary");
		rbac.rbac.User mary = RbacFactory.eINSTANCE.createUser();
		mary.setName("Mary");
		rbac.rbac.User alicia = RbacFactory.eINSTANCE.createUser();
		alicia.setName("Alicia");
		rbac.rbac.User boris = RbacFactory.eINSTANCE.createUser();
		boris.setName("Boris");
		// adding users
		policy.getElements().add(gary);
		policy.getElements().add(mary);
		policy.getElements().add(alicia);
		policy.getElements().add(boris);

		// ROLES
		rbac.rbac.Role employee = RbacFactory.eINSTANCE.createRole();
		employee.setName("employee");
		rbac.rbac.Role relationshipManager = RbacFactory.eINSTANCE.createRole();
		relationshipManager.setName("relationshipManager");
		rbac.rbac.Role rescuer = RbacFactory.eINSTANCE.createRole();
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
		rbac.rbac.Resource employeeAddressBook = RbacFactory.eINSTANCE.createResource();
		employeeAddressBook.setName("employeeAddressBook");

		rbac.rbac.Resource externalContactAddressBook = RbacFactory.eINSTANCE
				.createResource();
		externalContactAddressBook.setName("externalContactAddressBook");

		rbac.rbac.Resource emergencyAddressBook = RbacFactory.eINSTANCE.createResource();
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
	
	public void initPolicyExamples(int number, int numberOperationByPermission,
			boolean randomPermission, boolean cyclicDelegation,boolean cyclicHierarchy, boolean ssod,
			boolean dsod) {
		RbacFactory factory = RbacFactory.eINSTANCE;
		policy = factory.createPolicy();
		for (int i = 0; i < number; i++) {
			User u = factory.createUser();
			u.setName("user" + i);
			policy.getElements().add(u);
			Role r = factory.createRole();
			r.setName("role" + i);
			policy.getElements().add(r);
			Permission p = factory.createPermission();
			p.setName("perm" + i);
			policy.getElements().add(p);
			rbac.rbac.Resource re = factory.createResource();
			re.setName("obj" + i);
			policy.getElements().add(re);
//			for (int j = 0; j < numberOperationByPermission; j++) {
//				Operation op = factory.createOperation();
//				op.setName("ope" + j);
//				p.getOperations().add(op);
//			}

			for(String s : addressBookOperations){
				Operation op = factory.createOperation();
				op.setName(s);
				p.getOperations().add(op);
			}
		}

		
		// everibody permitted to all
		if (randomPermission) {
			for (PolicyElement u : policy.getElements()) {
				if (u instanceof UserImpl) {
					for (PolicyElement r : policy.getElements()) {
						//MAGIC NUMBER ;)
						if (r instanceof RoleImpl && RandomSingleton.random(3)==1) {
							((User) u).getAssignedRoles().add((Role) r);
						}
					}
				}
			}

			for (PolicyElement r : policy.getElements()) {
				if (r instanceof RoleImpl) {
					for (PolicyElement p : policy.getElements()) {
						//MAGIC NUMBER ;)
						if (p instanceof PermissionImpl && RandomSingleton.random(3)==1) {
							((Role) r).getPermissions().add((Permission) p);
						}
					}
				}
			}
		}
		
		// everibody permitted to all
		if (!randomPermission) {
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
							o.getResources().add((rbac.rbac.Resource) res);
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
					Session s = factory.createSession();
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

	public void initPolicyExample1() {
		policy = RbacFactory.eINSTANCE.createPolicy();
		// USERS
		rbac.rbac.User gary = RbacFactory.eINSTANCE.createUser();
		gary.setName("Gary");
		rbac.rbac.User mary = RbacFactory.eINSTANCE.createUser();
		mary.setName("Mary");
		rbac.rbac.User alicia = RbacFactory.eINSTANCE.createUser();
		alicia.setName("Alicia");
		rbac.rbac.User boris = RbacFactory.eINSTANCE.createUser();
		boris.setName("Boris");
		// adding users
		policy.getElements().add(gary);
		policy.getElements().add(mary);
		policy.getElements().add(alicia);
		policy.getElements().add(boris);

		// ROLES
		rbac.rbac.Role employee = RbacFactory.eINSTANCE.createRole();
		employee.setName("employee");
		rbac.rbac.Role relationshipManager = RbacFactory.eINSTANCE.createRole();
		relationshipManager.setName("relationshipManager");
		rbac.rbac.Role rescuer = RbacFactory.eINSTANCE.createRole();
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
		rbac.rbac.Resource employeeAddressBook = RbacFactory.eINSTANCE.createResource();
		employeeAddressBook.setName("employeeAddressBook");

		rbac.rbac.Resource externalContactAddressBook = RbacFactory.eINSTANCE
				.createResource();
		externalContactAddressBook.setName("externalContactAddressBook");

		rbac.rbac.Resource emergencyAddressBook = RbacFactory.eINSTANCE.createResource();
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
		rbac.rbac.User gary = RbacFactory.eINSTANCE.createUser();
		gary.setName("Gary");
		rbac.rbac.User mary = RbacFactory.eINSTANCE.createUser();
		mary.setName("Mary");
		rbac.rbac.User alicia = RbacFactory.eINSTANCE.createUser();
		alicia.setName("Alicia");
		rbac.rbac.User boris = RbacFactory.eINSTANCE.createUser();
		boris.setName("Boris");
		// adding users
		policy.getElements().add(gary);
		policy.getElements().add(mary);
		policy.getElements().add(alicia);
		policy.getElements().add(boris);

		// ROLES
		rbac.rbac.Role employee = RbacFactory.eINSTANCE.createRole();
		employee.setName("employee");
		rbac.rbac.Role relationshipManager = RbacFactory.eINSTANCE.createRole();
		relationshipManager.setName("relationshipManager");
		rbac.rbac.Role rescuer = RbacFactory.eINSTANCE.createRole();
		rescuer.setName("rescuer");
		rbac.rbac.Role assistant = RbacFactory.eINSTANCE.createRole();
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
		rbac.rbac.Resource employeeAddressBook = RbacFactory.eINSTANCE.createResource();
		employeeAddressBook.setName("employeeAddressBook");

		rbac.rbac.Resource externalContactAddressBook = RbacFactory.eINSTANCE
				.createResource();
		externalContactAddressBook.setName("externalContactAddressBook");

		rbac.rbac.Resource emergencyAddressBook = RbacFactory.eINSTANCE.createResource();
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
}
