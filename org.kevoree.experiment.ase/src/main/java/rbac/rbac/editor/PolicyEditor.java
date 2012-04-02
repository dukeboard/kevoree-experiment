package rbac.rbac.editor;

import java.util.ArrayList;

import rbac.rbac.Operation;
import rbac.rbac.Permission;
import rbac.rbac.Policy;
import rbac.rbac.PolicyElement;
import rbac.rbac.RbacFactory;
import rbac.rbac.Resource;
import rbac.rbac.Role;
import rbac.rbac.Session;
import rbac.rbac.User;
import rbac.rbac.impl.PermissionImpl;
import rbac.rbac.impl.ResourceImpl;
import rbac.rbac.impl.RoleImpl;
import rbac.rbac.impl.SessionImpl;
import rbac.rbac.impl.UserImpl;

public class PolicyEditor {

	private Policy policy;
	private RbacFactory factory;
	
	public PolicyEditor(){
		factory = RbacFactory.eINSTANCE;
		policy = factory.createPolicy();
	}

	public PolicyEditor(Policy p){
		policy = p;
		factory = RbacFactory.eINSTANCE;
	}
	
	//Number of change primitives : 32
	
	//add policy elements
	public void addUser(String name){
		User u = factory.createUser();
		u.setName(name);
		policy.getElements().add(u);
	}
	
	public void addRole(String name){
		Role r = factory.createRole();
		r.setName(name);
		policy.getElements().add(r);
	}
	
	public void addPermission(String name){
		Permission p = factory.createPermission();
		p.setName(name);
		policy.getElements().add(p);
	}
	
	public void addResource(String name){
		Resource r = factory.createResource();
		r.setName(name);
		policy.getElements().add(r);
	}
	
	public void addSession(String name){
		Session s = factory.createSession();
		s.setName(name);
		policy.getElements().add(s);
	}
	
	public void addOperation(String name,String permissionName){
		Operation op = factory.createOperation();
		op.setName(name);
		getPermission(permissionName).getOperations().add(op);
	}
	
	
	//remove policy elements
	
	public void removeUser(String name){
		policy.getElements().remove(getUser(name));
	}
	
	public void removeRole(String name){
		policy.getElements().remove(getRole(name));
	}
	
	public void removePermission(String name){
		policy.getElements().remove(getPermission(name));
	}
	
	public void removeResource(String name){
		policy.getElements().remove(getResource(name));
	}
	
	public void removeSession(String name){
		policy.getElements().remove(getSession(name));
	}
	
	public void removeOperation(String name,Permission p){
		getPermission(p.getName()).getOperations().remove(getOperation(name, p));
	}
	
	
	//add relationships
	
	public void addUserRoleAssignment(String userName,String roleName){
		getUser(userName).getAssignedRoles().add(getRole(roleName));
	}
	
	
	public void addRolePermissionAssignment(String roleName, String permissionName){
		getRole(roleName).getPermissions().add(getPermission(permissionName));
	}
	
	
	public void addPermissionOperationAssignment(String permissionName, String operationName){
		addOperation(operationName, permissionName);
	}
	
	
	public void addOperationResourceAssignment(String permissionName, String operationName, String resourceName){
		getOperation(operationName, getPermission(permissionName)).getResources().add(getResource(resourceName));
	}
	
	public void addUserSessionAssignment(String userName, String sessionName){
		getUser(userName).setSession(getSession(sessionName));
	}
	
	public void addUserRoleActivation(String userName, String roleName){
		getUser(userName).getSession().getActiveRoles().add(getRole(roleName));
	}
	
	public void addRoleRoleSSoD(String role1name, String role2name){
		getRole(role1name).getSsod().add(getRole(role2name));
	}
	
	public void addRoleRoleDSoD(String role1name, String role2name){
		getRole(role1name).getDsod().add(getRole(role2name));
	}
	
	public void addUserUserDelegation(String userName1, String userName2){
		getUser(userName1).getDelegations().add(getUser(userName2));
	}
	
	public void addRoleRoleHierarchy(String roleName1, String roleName2){
		getRole(roleName1).getHierarchy().add(getRole(roleName2));
	}
	
	//remove relationships
	
	public void removeUserRoleAssignment(String userName,String roleName){
		getUser(userName).getAssignedRoles().remove(getRole(roleName));
	}
	
	
	public void removeRolePermissionAssignment(String roleName, String permissionName){
		getRole(roleName).getPermissions().remove(getPermission(permissionName));
	}
	
	
	public void removePermissionOperationAssignment(String permissionName, String operationName){
		removeOperation(operationName, getPermission(permissionName));
	}
	
	
	public void removeOperationResourceAssignment(String permissionName, String operationName, String resourceName){
		getOperation(operationName, getPermission(permissionName)).getResources().remove(getResource(resourceName));
	}
	
	public void removeUserSessionAssignment(String userName, String sessionName){
		getUser(userName).setSession(null);
	}
	
	public void removeUserRoleActivation(String userName, String roleName){
		getUser(userName).getSession().getActiveRoles().remove(getRole(roleName));
	}
	
	public void removeRoleRoleSSoD(String role1name, String role2name){
		getRole(role1name).getSsod().remove(getRole(role2name));
	}
	
	public void removeRoleRoleDSoD(String role1name, String role2name){
		getRole(role1name).getDsod().remove(getRole(role2name));
	}
	
	public void removeUserUserDelegation(String userName1, String userName2){
		getUser(userName1).getDelegations().remove(getUser(userName2));
	}
	
	public void removeRoleRoleHierarchy(String roleName1, String roleName2){
		getRole(roleName1).getHierarchy().remove(getRole(roleName2));
	}
	
	//getters
	
	public Policy getPolicy() {
		return policy;
	}
	
	public User getUser(String name){
		User u = null;
		for(PolicyElement e : policy.getElements()){
			if (e instanceof UserImpl){
				if (e.getName().equals(name)){
					u = (User)e;
				}
			}
		}
		return u;
	}
	
	public Role getRole(String name){
		Role r = null;
		for(PolicyElement e : policy.getElements()){
			if (e instanceof RoleImpl){
				if (e.getName().equals(name)){
					r = (Role)e;
				}
			}
		}
		return r;
	}
	
	public Permission getPermission(String name){
		Permission p = null;
		for(PolicyElement e : policy.getElements()){
			if (e instanceof PermissionImpl){
				if (e.getName().equals(name)){
					p = (Permission)e;
				}
			}
		}
		return p;
	}
	
	public Resource getResource(String name){
		Resource r = null;
		for(PolicyElement e : policy.getElements()){
			if (e instanceof ResourceImpl){
				if (e.getName().equals(name)){
					r = (Resource)e;
				}
			}
		}
		return r;
	}
	
	public Session getSession(String name){
		Session s = null;
		for(PolicyElement e : policy.getElements()){
			if (e instanceof SessionImpl){
				if (e.getName().equals(name)){
					s = (Session)e;
				}
			}
		}
		return s;
	}
	
	public Operation getOperation(String name,Permission p){
		Operation o = null;
		for(PolicyElement e : policy.getElements()){
			if (e instanceof PermissionImpl){
				if (e.equals(p)){
					for(Operation op : p.getOperations()){
						if (op.getName().equals(name)){
							o = op;
						}
					}
				}
			}
		}
		return o;
	}
	
	public ArrayList<User> getUsers(){
		ArrayList<User> users = new ArrayList<User>();
		for(PolicyElement e : policy.getElements()){
			if (e instanceof UserImpl){
				users.add((User)e);
			}
		}
		return users;
	}

	public ArrayList<Role> getRoles(){
		ArrayList<Role> roles = new ArrayList<Role>();
		for(PolicyElement e : policy.getElements()){
			if (e instanceof RoleImpl){
				roles.add((Role)e);
			}
		}
		return roles;
	}
	
	public ArrayList<Permission> getPermissions(){
		ArrayList<Permission> permissions = new ArrayList<Permission>();
		for(PolicyElement e : policy.getElements()){
			if (e instanceof PermissionImpl){
				permissions.add((Permission)e);
			}
		}
		return permissions;
	}
	
	public ArrayList<Resource> getResources(){
		ArrayList<Resource> resources = new ArrayList<Resource>();
		for(PolicyElement e : policy.getElements()){
			if (e instanceof ResourceImpl){
				resources.add((Resource)e);
			}
		}
		return resources;
	}
	
	public ArrayList<Session> getSessions(){
		ArrayList<Session> session = new ArrayList<Session>();
		for(PolicyElement e : policy.getElements()){
			if (e instanceof SessionImpl){
				session.add((Session)e);
			}
		}
		return session;
	}
}
