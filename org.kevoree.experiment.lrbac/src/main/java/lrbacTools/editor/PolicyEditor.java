package lrbacTools.editor;

import java.util.HashSet;
import java.util.HashMap;

import lrbac.*;
import lrbac.Object;
import lrbac.impl.*;

public class PolicyEditor {

	private Policy policy;
	private LrbacFactory factory;
	private HashSet<String> policyElementTypes;

	public PolicyEditor() {
		factory = LrbacFactory.eINSTANCE;
		policy = factory.createPolicy();
		policyElementTypes = new HashSet<String>();
		policyElementTypes.add("User");
		policyElementTypes.add("Role");
		policyElementTypes.add("Permission");
		policyElementTypes.add("Operation");
		policyElementTypes.add("Object");
		policyElementTypes.add("Session");
	}

	public PolicyEditor(Policy p) {
		policy = p;
		factory = LrbacFactory.eINSTANCE;
		policyElementTypes = new HashSet<String>();
		policyElementTypes.add("User");
		policyElementTypes.add("Role");
		policyElementTypes.add("Permission");
		policyElementTypes.add("Operation");
		policyElementTypes.add("Object");
		policyElementTypes.add("Session");
	}

	// Number of change primitives : 32

	// add policy elements
	public void addUser(String name) {
		User u = factory.createUser();
		u.setName(name);
		policy.getElements().add(u);
	}

	public void addRole(String name) {
		Role r = factory.createRole();
		r.setName(name);
		policy.getElements().add(r);
	}

	public void addPermission(String name) {
		Permission p = factory.createPermission();
		p.setName(name);
		policy.getElements().add(p);
	}

	public void addOperation(String name) {
		Operation op = factory.createOperation();
		op.setName(name);
		policy.getElements().add(op);
	}

	public void addObject(String name) {
		Object r = factory.createObject();
		r.setName(name);
		policy.getElements().add(r);
	}

	public void addSession(String name) {
		Session s = factory.createSession();
		s.setName(name);
		policy.getElements().add(s);
	}

	// remove policy elements

	public void removeUser(String name) {
		policy.getElements().remove(getUser(name));
	}

	public void removeRole(String name) {
		policy.getElements().remove(getRole(name));
	}

	public void removePermission(String name) {
		policy.getElements().remove(getPermission(name));
	}

	public void removeOperation(String name) {
		policy.getElements().remove(getOperation(name));
	}

	public void removeObject(String name) {
		policy.getElements().remove(getObject(name));
	}

	public void removeSession(String name) {
		policy.getElements().remove(getSession(name));
	}


	// add relationships

	public void addUserRole(String userName, String roleName) {
		getUser(userName).getRoles().add(getRole(roleName));
	}

	public void addRolePermission(String roleName,
			String permissionName) {
		getRole(roleName).getPermissions().add(getPermission(permissionName));
	}

	public void addPermissionOperation(String permissionName,
			String operationName) {
		getPermission(permissionName).getOperations().add(getOperation(operationName));
	}

	public void addPermissionOperationObject(String permissionName,
			String operationName, String objectName) {
		getPermissionOperation(permissionName, operationName).getObjects()
				.add(getObject(objectName));
	}

	public void addUserSession(String userName, String sessionName) {
		getUser(userName).setSession(getSession(sessionName));
	}

	

	public void addRoleRoleSSoD(String role1name, String role2name) {
		getRole(role1name).getSsod().add(getRole(role2name));
	}

	public void addRoleRoleDSoD(String role1name, String role2name) {
		getRole(role1name).getDsod().add(getRole(role2name));
	}

	public void addUserUserDelegation(String userName1, String userName2) {
		getUser(userName1).getDelegatees().add(getUser(userName2));
	}

	public void addRoleRoleHierarchy(String roleName1, String roleName2) {
		getRole(roleName1).getChildrenHierarchy().add(getRole(roleName2));
	}

	// remove relationships

	public void removeUserRoleAssignment(String userName, String roleName) {
		getUser(userName).getRoles().remove(getRole(roleName));
	}

	public void removeRolePermissionAssignment(String roleName,
			String permissionName) {
		getRole(roleName).getPermissions()
				.remove(getPermission(permissionName));
	}

	public void removePermissionOperationAssignment(String permissionName,
			String operationName) {
		getOperation(operationName).getPermissions().remove(getPermission(permissionName));
	}

	public void removeOperationObjectAssignment(
			String operationName, String resourceName) {
		getOperation(operationName).getObjects()
				.remove(getObject(resourceName));
	}

	public void removeUserSessionAssignment(String userName, String sessionName) {
		getUser(userName).setSession(null);
	}

	public void removeUserRoleActivation(String userName, String roleName) {
		getUser(userName).getSession().getRoles().remove(getRole(roleName));
	}

	public void removeRoleRoleSSoD(String role1name, String role2name) {
		getRole(role1name).getSsod().remove(getRole(role2name));
	}

	public void removeRoleRoleDSoD(String role1name, String role2name) {
		getRole(role1name).getDsod().remove(getRole(role2name));
	}

	public void removeUserUserDelegation(String userName1, String userName2) {
		getUser(userName1).getDelegatees().remove(getUser(userName2));
	}

	public void removeRoleRoleHierarchy(String roleName1, String roleName2) {
		getRole(roleName1).getChildrenHierarchy().remove(getRole(roleName2));
	}

	// getters

	public Policy getPolicy() {
		return policy;
	}

	public User getUser(String name) {
		User u = null;
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				if (e.getName().equals(name)) {
					u = (User) e;
				}
			}
		}
		return u;
	}

	public Role getRole(String name) {
		Role r = null;
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				if (e.getName().equals(name)) {
					r = (Role) e;
				}
			}
		}
		return r;
	}

	public Permission getPermission(String name) {
		Permission p = null;
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof PermissionImpl) {
				if (e.getName().equals(name)) {
					p = (Permission) e;
				}
			}
		}
		return p;
	}

	public Object getObject(String name) {
		Object r = null;
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof ObjectImpl) {
				if (e.getName().equals(name)) {
					r = (Object) e;
				}
			}
		}
		return r;
	}

	public Session getSession(String name) {
		Session s = null;
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof SessionImpl) {
				if (e.getName().equals(name)) {
					s = (Session) e;
				}
			}
		}
		return s;
	}

	public Operation getPermissionOperation(String permissionName,String operationName) {
		Operation o = null;
		for (Operation op : getPermission(permissionName).getOperations()){
			if (op.getName().equals(operationName)){
				o = op;
			}
		}
		return o;
	}
	
	public Operation getOperation(String name) {
		Operation o = null;
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof OperationImpl) {
				if (e.getName().equals(name)) {
					o = (Operation)e;
				}

			}
		}
		return o;
	}

	public HashSet<String> getUserOperations(User u) {
		HashSet<String> res = new HashSet<String>();
		for (Role r : u.getRoles()) {
			for (Permission p : r.getPermissions()) {
				for (Operation op : p.getOperations()) {
					if (!res.contains(op.getName())) {
						res.add(op.getName());
					}
				}
			}
		}
		return res;
	}

	public HashSet<String> getUserOperationObjects(User u, Operation oo) {
		HashSet<String> res = new HashSet<String>();
		for (Role r : u.getRoles()) {
			for (Permission p : r.getPermissions()) {
				for (Operation op : p.getOperations()) {
					for (Object obj : op.getObjects()) {
						if (!res.contains(obj.getName())) {
							res.add(obj.getName());
						}
					}
				}
			}
		}
		return res;
	}

	public HashMap<String, HashSet<String>> getUserOperationsObjects(User u) {
		HashMap<String, HashSet<String>> res = new HashMap<String, HashSet<String>>();
		for (Role r : u.getRoles()) {
			for (Permission p : r.getPermissions()) {
				for (Operation op : p.getOperations()) {
					res.put(op.getName(), getUserOperationObjects(u, op));
				}
			}
		}
		return res;
	}

	public HashMap<String, HashMap<String, HashSet<String>>> getUsersOperationsObjects() {
		HashMap<String, HashMap<String, HashSet<String>>> res = new HashMap<String, HashMap<String, HashSet<String>>>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				res.put(e.getName(), getUserOperationsObjects((User) e));
			}
		}
		return res;
	}

	public HashMap<String, HashMap<String, HashSet<String>>> getUsersOperationsObjects2() {
		HashMap<String, HashMap<String, HashSet<String>>> res = new HashMap<String, HashMap<String, HashSet<String>>>();
		for (User u : getUsers()) {
			String user = u.getName();
			HashMap<String, HashSet<String>> opObjects = new HashMap<String, HashSet<String>>();
			for (Role r : u.getRoles()) {
				for (Permission p : r.getPermissions()) {
					for (Operation op : p.getOperations()) {
						String operation = op.getName();
						if (!opObjects.keySet().contains(operation)) {
							HashSet<String> resources = new HashSet<String>();
							opObjects.put(operation, resources);
						}
						for (Object obj : op.getObjects()) {
							if (!opObjects.get(operation).contains(
									obj.getName())) {
								opObjects.get(operation).add(obj.getName());
							}
						}
					}

				}
			}
			res.put(user, opObjects);
		}
		return res;
	}

	public HashSet<User> getUsers() {
		HashSet<User> users = new HashSet<User>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				users.add((User) e);
			}
		}
		return users;
	}

	public HashSet<String> getUsersStringSet() {
		HashSet<String> users = new HashSet<String>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				users.add(e.getName());
			}
		}
		return users;
	}

	public HashSet<Role> getRoles() {
		HashSet<Role> roles = new HashSet<Role>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				roles.add((Role) e);
			}
		}
		return roles;
	}

	public HashSet<String> getRolesStringSet() {
		HashSet<String> roles = new HashSet<String>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				roles.add(e.getName());
			}
		}
		return roles;
	}

	public HashSet<Permission> getPermissions() {
		HashSet<Permission> permissions = new HashSet<Permission>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof PermissionImpl) {
				permissions.add((Permission) e);
			}
		}
		return permissions;
	}

	public HashSet<String> getPermissionsStringSet() {
		HashSet<String> permissions = new HashSet<String>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof PermissionImpl) {
				permissions.add(e.getName());
			}
		}
		return permissions;
	}

	public HashSet<Object> getObjects() {
		HashSet<Object> resources = new HashSet<Object>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof ObjectImpl) {
				resources.add((Object) e);
			}
		}
		return resources;
	}

	public HashSet<String> getObjectsStringSet() {
		HashSet<String> resources = new HashSet<String>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof ObjectImpl) {
				resources.add(e.getName());
			}
		}
		return resources;
	}

	public HashSet<Session> getSessions() {
		HashSet<Session> session = new HashSet<Session>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof SessionImpl) {
				session.add((Session) e);
			}
		}
		return session;
	}

	public HashSet<Operation> getOperations() {
		HashSet<Operation> operation = new HashSet<Operation>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof OperationImpl) {
				operation.add((Operation) e);
			}
		}
		return operation;
	}

	public HashSet<String> getSessionsStringSet() {
		HashSet<String> session = new HashSet<String>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof SessionImpl) {
				session.add(e.getName());
			}
		}
		return session;
	}

	public HashSet<String> getPermissionsOperationsStringSet() {
		HashSet<String> permissionsOperations = new HashSet<String>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof PermissionImpl) {
				for (Operation o : ((Permission) e).getOperations()) {
					String s = e.getName() + ":" + o.getName();
					permissionsOperations.add(s);
				}
			}
		}
		return permissionsOperations;
	}

	// attributes getters

	/**
	 * @return the policyElementTypes
	 */
	public HashSet<String> getPolicyElementTypes() {
		return policyElementTypes;
	}
}
