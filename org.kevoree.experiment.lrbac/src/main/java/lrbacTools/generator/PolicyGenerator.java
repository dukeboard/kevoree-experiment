package lrbacTools.generator;

import java.util.Vector;

import lrbac.*;
import lrbac.impl.*;
import lrbac.Object;
import lrbacTools.editor.PolicyEditor;

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
	
	public void addRandomRules(){
		cptElt = cptElt+1;
		generate(1, 1, 1, 1, 1, 1, false, false, false, false, false, false);
	}

	
	public void generate(int numberUser,int numberRole,int numberPermission, int numberOperationByPermission, int numberObject, int numberSession,
			boolean randomPermission, boolean addressBookOperation, boolean cyclicDelegation,boolean cyclicHierarchy, boolean ssod,
			boolean dsod) {
		
		PolicyEditor editor = new PolicyEditor(policy);
		
		LrbacFactory factory = LrbacFactory.eINSTANCE;
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
			
			Operation op = factory.createOperation();
			op.setName("op"+i);
			policy.getElements().add(op);
			
			Object re = factory.createObject();
			re.setName("obj" + cptElt);
			policy.getElements().add(re);
		
			cptElt = cptElt +1;
		}
		
		for (User u : editor.getUsers()){
			for (Role r : editor.getRoles()){
				u.getRoles().add(r);
			}
		}
		
		for (Role r : editor.getRoles()){
			for (Permission p : editor.getPermissions()){
				r.getPermissions().add(p);
			}
		}
		
		for (Permission p : editor.getPermissions()){
			for (Operation op : editor.getOperations()){
				p.getOperations().add(op);
			}
		}
		
		for (Operation op : editor.getOperations()){
			for (Object ob : editor.getObjects()){
				op.getObjects().add(ob);
			}
		}
		
	}
	
}
