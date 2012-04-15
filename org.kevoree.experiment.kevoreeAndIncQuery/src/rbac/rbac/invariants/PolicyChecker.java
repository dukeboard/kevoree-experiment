package rbac.rbac.invariants;

import java.util.HashSet;
import java.util.Set;

import rbac.Operation;
import rbac.Permission;
import rbac.Policy;
import rbac.PolicyElement;
import rbac.RbacFactory;
import rbac.Resource;
import rbac.Role;
import rbac.User;
import rbac.rbac.impl.PermissionImpl;
import rbac.rbac.impl.ResourceImpl;
import rbac.rbac.impl.RoleImpl;
import rbac.rbac.impl.UserImpl;
import utils.time.Chrono;

public class PolicyChecker {

	private Policy policy;

	public static void main(String[] args) {
		
		Chrono c = new Chrono();
		c.start();
		PolicyChecker r = new PolicyChecker();
		c.stop();
		System.out.println("temps de generation : "+c.displayTime());
		
		
		c.start();
		System.out.println("checkPolicyElementsNameNotNull : "
				+ r.checkPolicyElementsNameNotNull());
		System.out.println("checkAcyclicDelegation : "
				+ r.checkAcyclicDelegation());
		System.out.println("checkAcyclicHierachy : "
				+ r.checkAcyclicHierachy());
		System.out.println("checkSSoD : " + r.checkSSoD());
		System.out.println("checkDSoD : " + r.checkDSoD());
		c.stop();
		System.out.println("temps de verication : "+c.displayTime());
	}

	public PolicyChecker() {
		// policy = RbacFactoryImpl.eINSTANCE.createPolicy();
		// initPolicy();
		// initPolicy(5, 3);
		// initPolicyCyclicDelegation(5,3);
		// initPolicyCyclicHierarchy(5,3);
		// initPolicySSoDViolated(5,3);
		initPolicyExamples(1000, 3, false, true, true, true, true);

	}
	
	public PolicyChecker(Policy p) {
		// policy = RbacFactoryImpl.eINSTANCE.createPolicy();
		// initPolicy();
		// initPolicy(5, 3);
		// initPolicyCyclicDelegation(5,3);
		// initPolicyCyclicHierarchy(5,3);
		// initPolicySSoDViolated(5,3);
		//initPolicyExamples(1000, 3, false, true, true, true, true);
		policy = p;

	}

	
	
	public String checkPolicy(){
		String res ="";
		Chrono c = new Chrono();
		c.start();
		//System.out.println("checkPolicyElementsNameNotNull : "+ checkPolicyElementsNameNotNull());
		res = res+ "checkPolicyElementsNameNotNull : "	+ checkPolicyElementsNameNotNull();
		//System.out.println("checkAcyclicDelegation : "+ checkAcyclicDelegation());
		res = res +"\n"+"checkAcyclicDelegation : "+ checkAcyclicDelegation();
		//System.out.println("checkAcyclicHierachy : "+ checkAcyclicHierachy());
		res = res +"\n"+"checkAcyclicHierachy : "+ checkAcyclicHierachy();
		//System.out.println("checkSSoD : " + checkSSoD());
		res = res +"\n"+"checkSSoD : " + checkSSoD();
		//System.out.println("checkDSoD : " + checkDSoD());
		res = res +"\n"+"checkDSoD : " + checkDSoD();
		c.stop();
		res = res +"\n"+"temps de verication : "+c.displayTime();
		//System.out.println("temps de verication : "+c.displayTime());
		return res;
	}
	
	public String checkPolicyWithoutTime(){
		String res ="";
		//System.out.println("checkPolicyElementsNameNotNull : "+ checkPolicyElementsNameNotNull());
		res = res+ "checkPolicyElementsNameNotNull : "	+ checkPolicyElementsNameNotNull();
		//System.out.println("checkAcyclicDelegation : "+ checkAcyclicDelegation());
		res = res +"\n"+"checkAcyclicDelegation : "+ checkAcyclicDelegation();
		//System.out.println("checkAcyclicHierachy : "+ checkAcyclicHierachy());
		res = res +"\n"+"checkAcyclicHierachy : "+ checkAcyclicHierachy();
		//System.out.println("checkSSoD : " + checkSSoD());
		res = res +"\n"+"checkSSoD : " + checkSSoD();
		//System.out.println("checkDSoD : " + checkDSoD());
		res = res +"\n"+"checkDSoD : " + checkDSoD();
		//System.out.println("temps de verication : "+c.displayTime());
		return res;
	}
	
	// code false means that the invariant is not verified
	// else true means that the property defined by the invariant is verified

	/**
	 * @return
	 */
	public boolean checkPolicyElementsNameNotNull() {
		for (PolicyElement e : policy.getElements()) {
			if (e.getName() == null) {
				return false;
			}
		}
		return true;
	}

	public boolean checkSSoD() {
		// verifier qu'aucun user n'est assigné à deux roles qui soient associés
		// à une relation de SSoD
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					for (Role r2 : ((User) e).getAssignedRoles()) {
						if (!r.equals(r2)) {
							if (r.getSsod().contains(r2)) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}

	public boolean checkDSoD() {
		// verifier qu'aucune session n'est assignée à deux roles qui soient
		// associés à une relation de SSoD
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				if (!(((User) e).getSession() == null)) {
					for (Role r : ((User) e).getSession().getActiveRoles()) {
						for (Role r2 : ((User) e).getSession().getActiveRoles()) {
							if (!r.equals(r2)) {
								if (r.getDsod().contains(r2)) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	public boolean checkAcyclicDelegation() {
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				boolean res = checkAcyclicDelegation(new HashSet<User>(),
						(User) e);
				if (!res) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkAcyclicHierachy() {
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				boolean res = checkAcyclicHirerachy(new HashSet<Role>(),
						(Role) e);
				if (!res) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean checkAcyclicDelegation(Set<User> explored, User u) {
		explored.add(u);
		for (User du : u.getDelegations()) {
			if (!explored.contains(u)) {
				checkAcyclicDelegation(explored, du);
			} else {
				return false;
			}
		}
		return true;
	}

	public boolean checkAcyclicHirerachy(Set<Role> explored, Role r) {
		explored.add(r);
		for (Role rj : r.getHierarchy()) {
			if (!explored.contains(r)) {
				checkAcyclicHirerachy(explored, rj);
			} else {
				return false;
			}
		}
		return true;
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
}
