package rbacTools.generator;

import java.util.Vector;

import rbac.Operation;
import rbac.Permission;
import rbac.Policy;
import rbac.PolicyElement;
import rbac.RbacFactory;
import rbac.Resource;
import rbac.Role;
import rbac.Session;
import rbac.User;
import rbacTools.editor.PolicyEditor;

import utils.random.RandomSingleton;

public class PolicyGenerator {
	
	private Policy policy;
	private Vector<String> addressBookOperations;
	private int cptElt;
	
	public PolicyGenerator(Policy p){
		policy = p;
		addressBookOperations = new Vector<String>();
		addressBookOperations.add("create");
		addressBookOperations.add("read");
		addressBookOperations.add("update");
		addressBookOperations.add("delete");
		cptElt = 0;
	}
	
	public void incrementalAdd(){
		cptElt = cptElt+1;
		generate(1, 1, 1, 1, 1, 1, false, false, false, false, false, false);
	}

	public void generate(int numberUser,int numberRole,int numberPermission, int numberOperationByPermission, int numberObject, int numberSession,
			boolean randomPermission, boolean addressBookOperation, boolean cyclicDelegation,boolean cyclicHierarchy, boolean ssod,
			boolean dsod) {
		RbacFactory factory = RbacFactory.eINSTANCE;
		for (int i = 0; i < numberUser; i++) {
			User u = factory.createUser();
			u.setName("user" + cptElt);
			policy.getElements().add(u);
			Role r = factory.createRole();
			r.setName("role" + cptElt);
			policy.getElements().add(r);
			Permission p = factory.createPermission();
			p.setName("perm" + cptElt);
			policy.getElements().add(p);
			rbac.Resource re = factory.createResource();
			re.setName("obj" + cptElt);
			policy.getElements().add(re);
		
			if (addressBookOperation) {
				for(String s : addressBookOperations){
					Operation op = factory.createOperation();
					op.setName(s);
					p.getOperations().add(op);
				}
			}
			else{
				for (int j = 0; j < numberOperationByPermission; j++) {
					Operation op = factory.createOperation();
					op.setName(p.getName()+"ope" + j);
					p.getOperations().add(op);
				}
			}
			cptElt = cptElt +1;
		}

		
		// everibody permitted to all
		if (randomPermission) {
			for (PolicyElement u : policy.getElements()) {
				if (u instanceof rbac.rbac.impl.UserImpl) {
					for (PolicyElement r : policy.getElements()) {
						//MAGIC NUMBER ;)
						if (r instanceof rbac.rbac.impl.RoleImpl && RandomSingleton.random(3)==1) {
							((User) u).getAssignedRoles().add((Role) r);
						}
					}
				}
			}

			for (PolicyElement r : policy.getElements()) {
				if (r instanceof rbac.rbac.impl.RoleImpl) {
					for (PolicyElement p : policy.getElements()) {
						//MAGIC NUMBER ;)
						if (p instanceof rbac.rbac.impl.PermissionImpl && RandomSingleton.random(3)==1) {
							((Role) r).getPermissions().add((Permission) p);
						}
					}
				}
			}
		}
		
		// everibody permitted to all
		if (!randomPermission) {
			for (PolicyElement u : policy.getElements()) {
				if (u instanceof rbac.rbac.impl.UserImpl) {
					for (PolicyElement r : policy.getElements()) {
						if (r instanceof rbac.rbac.impl.RoleImpl) {
							((User) u).getAssignedRoles().add((Role) r);
						}
					}
				}
			}

			for (PolicyElement r : policy.getElements()) {
				if (r instanceof rbac.rbac.impl.RoleImpl) {
					for (PolicyElement p : policy.getElements()) {
						if (p instanceof rbac.rbac.impl.PermissionImpl) {
							((Role) r).getPermissions().add((Permission) p);
						}
					}
				}
			}
		}

		for (PolicyElement e : policy.getElements()) {
			if (e instanceof rbac.rbac.impl.PermissionImpl) {
				for (Operation o : ((Permission) e).getOperations()) {
					for (PolicyElement res : policy.getElements()) {
						if (res instanceof rbac.rbac.impl.ResourceImpl) {
							o.getResources().add((rbac.Resource) res);
						}
					}
				}
			}
		}

		if (cyclicDelegation) {
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof rbac.rbac.impl.UserImpl) {
					for (PolicyElement de : policy.getElements()) {
						if (de instanceof rbac.rbac.impl.UserImpl && (!e.equals(de))) {
							((User) e).getDelegations().add((User) de);
						}
					}
				}
			}
		}
		
		if (cyclicHierarchy) {
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof rbac.rbac.impl.RoleImpl) {
					for (PolicyElement de : policy.getElements()) {
						if (de instanceof rbac.rbac.impl.RoleImpl && (!e.equals(de))) {
							((Role) e).getHierarchy().add((Role) de);
						}
					}
				}
			}
		}


		if (ssod) {
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof rbac.rbac.impl.RoleImpl) {
					for (PolicyElement ee : policy.getElements()) {
						if (ee instanceof rbac.rbac.impl.RoleImpl && (!e.equals(ee))) {
							((Role) e).getSsod().add((Role) ee);
						}
					}
				}
			}
		}

		if (dsod) {
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof rbac.rbac.impl.UserImpl) {
					Session s = factory.createSession();
					s.setName("session" + e.getName());
					((User) e).setSession(s);
					for (PolicyElement ee : policy.getElements()) {
						if (ee instanceof rbac.rbac.impl.RoleImpl) {
							s.getActiveRoles().add((Role) ee);
						}
					}
				}
			}
			for (PolicyElement e : policy.getElements()) {
				if (e instanceof rbac.rbac.impl.RoleImpl) {
					for (PolicyElement ee : policy.getElements()) {
						if (ee instanceof rbac.rbac.impl.RoleImpl && (!e.equals(ee))) {
							((Role) e).getDsod().add((Role) ee);
						}
					}
				}
			}
		}
	}
}
