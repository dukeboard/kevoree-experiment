package policyTools.generator;
import java.util.Vector;
import policy.*;
public class Generator{
	private Policy policy;
	private Vector<String> addressBookOperations;
	private int cptElt;
	public Generator(Policy x) {
		policy = x;
		addressBookOperations = new Vector<String>();
		addressBookOperations.add("create");
		addressBookOperations.add("read");
		addressBookOperations.add("update");
		addressBookOperations.add("delete");
		cptElt = 0;
	}
	public void generateModelExample() {
		generate(1, 1, 1, 1, 1, 1, false, false, false, false, false, false);
		cptElt = cptElt+1;
	}
	
	
	public void addRandomRules(){
		cptElt = cptElt+1;
		generate(1, 1, 1, 1, 1, 1, false, false, false, false, false, false);
	}

	
	public void generate(int numberUser,int numberRole,int numberPermission, int numberOperationByPermission, int numberObject, int numberSession,
			boolean randomPermission, boolean addressBookOperation, boolean cyclicDelegation,boolean cyclicHierarchy, boolean ssod,
			boolean dsod) {
		policyTools.editor.PolicyEditor editor = new policyTools.editor.PolicyEditor(policy);
		for (int i = 0; i < numberUser; i++) {
			editor.addUser("user" + cptElt);
			editor.addRole("role" + cptElt);
			editor.addPermission("perm" + cptElt);
			editor.addOperation("op"+i);
			editor.addObject("obj" + cptElt);
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
			for (policy.Object ob : editor.getObjects()){
				op.getObjects().add(ob);
			}
		}
		
	}
}