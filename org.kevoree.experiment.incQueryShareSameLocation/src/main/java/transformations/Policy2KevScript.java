package transformations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

import rbac.*;
import rbac.rbac.editor.PolicyEditor;
import rbac.rbac.impl.*;
import utils.time.Chrono;

public class Policy2KevScript {

	private Policy policy;
	private int portNumber;
	private ArrayList<String> bindings;
	private ArrayList<String> nodes;

	public Policy2KevScript(Policy p) {
		policy = p;
		portNumber = 42000;
		bindings = new ArrayList<String>();
		nodes = new ArrayList<String>();
	}

	// generate script to add subjects
	public String addSubjects() {
		String script = "";
		// ajout d'un node subjects
		script = script + "\n" + "addNode subjects : JavaSENode";
		script = script + "\n" + "addChild subjects@node0";
		script = script + "\n" + "addToGroup sync subjects";
		script = script + "\n"
				+ "updateDictionary sync{ port=\"8101\"}@subjects";
		// ajout des composants addressBookClients
		for (rbac.PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				script = script + "\n" + "addComponent " + e.getName()
						+ "@subjects : AddressBookClient";
				nodes.add(e.getName() + "@subjects");
			}
		}
		return script;
	}

	// generate script to add ressources
	public String addResources() {
		String script = "";
		// ajout d'un node resources
		script = script + "\n" + "addNode resources : JavaSENode";
		script = script + "\n" + "addChild resources@node0";
		script = script + "\n" + "addToGroup sync resources";
		script = script + "\n"
				+ "updateDictionary sync{ port=\"8104\"}@resources";

		// ajout des composants addressBooks
		for (rbac.PolicyElement e : policy.getElements()) {
			if (e instanceof ResourceImpl) {
				script = script + "\n" + "addComponent " + e.getName()
						+ "@resources : AddressBook { }";
				nodes.add(e.getName() + "@resources");
			}
		}
		return script;
	}

	// generate script to add binding enforcementNEW
	public String addBindingSubjectsEnforcementChannelResources2() {
		String script = "";
		// ajout des relations subject -> channelsEnforcement -> resources
		// ajout des relations subject -> channelsEnforcement
		// System.out.println("1");
		HashMap<String, Vector<String>> channelSubjectUserOperation = new HashMap<String, Vector<String>>();
		for (rbac.PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (rbac.Role r : ((rbac.User) e).getAssignedRoles()) {
					for (rbac.Permission p : r.getPermissions()) {
						for (rbac.Operation o : p.getOperations()) {
							if (!channelSubjectUserOperation.containsKey(e
									.getName())) {
								channelSubjectUserOperation.put(e.getName(),
										new Vector<String>());
								channelSubjectUserOperation.get(e.getName())
										.add(o.getName());
							} else {
								if (!channelSubjectUserOperation.get(
										e.getName()).contains(o.getName())) {
									channelSubjectUserOperation
											.get(e.getName()).add(o.getName());
								}
							}
						}
					}
				}
			}
		}
		// System.out.println("2");
		for (String e : channelSubjectUserOperation.keySet()) {
			for (String o : channelSubjectUserOperation.get(e)) {
				portNumber = portNumber + 1;
				String channelName = "subject" + e + o;
				script = script + "\n" + "addChannel " + channelName
						+ " : SocketChannel{name = \"" + channelName + "\"}";
				script = script + "\n" + "bind " + e + "." + o + "@subjects"
						+ "=>" + channelName;
				bindings.add("bind " + e + "." + o + "@subjects" + "=>"
						+ channelName);
				script = script + "\n" + "updateDictionary " + channelName	+ "{ port=\"" + portNumber + "\"}@subjects";
			}
		}
		// System.out.println("3");
		// ajout des relations channelsEnforcement -> resources
		for (rbac.PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				// System.out.println("user "+e.getName());
				for (rbac.Role r : ((rbac.User) e).getAssignedRoles()) {
					// System.out.println("r "+r.getName());
					for (rbac.Permission p : r.getPermissions()) {
						// System.out.println("p "+p.getName());
						for (rbac.Operation o : p.getOperations()) {
							// System.out.println("o "+o.getName());
							for (rbac.Resource resource : o.getResources()) {
								// System.out.println("resource "+resource.getName());
								portNumber = portNumber + 1;
								String channelName = "subject" + e.getName()
										+ o.getName();

								script = script + "\n" + "bind "
										+ resource.getName() + "."
										+ o.getName() + "@resources" + " =>"
										+ channelName;
								script = script + "\n" + "updateDictionary "
										+ channelName + "{ port=\""
										+ portNumber + "\"}@resources";
							}
						}
					}
				}
			}
		}
		// System.out.println("4");
		return script;
	}

	// generate script to add binding enforcementNEW
	public String addBindingSubjectsEnforcementChannelResources4() {
		String script = "";
		PolicyEditor policyEditor = new PolicyEditor(policy);
		Chrono c = new Chrono();
		c.start();
		HashMap<String, HashMap<String, HashSet<String>>> usersOperationsResources = policyEditor
				.getUsersOperationsResources2();
		c.stop();
		System.out.println("meth time consuming : " + c.displayTime());
		
		c.start();
		for (String user : usersOperationsResources.keySet()) {
			for (String operation : usersOperationsResources.get(user).keySet()) {
				portNumber = portNumber + 1;
				String channelName = "subject" + user + operation;
				script = script + "\n" + "addChannel " + channelName
						+ " : SocketChannel{name = \"" + channelName + "\"}";
				script = script + "\n" + "bind " + user + "." + operation
						+ "@subjects" + "=>" + channelName;
				bindings.add("bind " + user + "." + operation + "@subjects"
						+ "=>" + channelName);
				script = script + "\n" + "updateDictionary " + channelName
						+ "{ port=\"" + portNumber + "\"}@subjects";
				for (String resource : usersOperationsResources.get(user).get(
						operation)) {
					portNumber = portNumber + 1;
					script = script + "\n" + "bind " + resource + "."
							+ operation + "@resources" + " =>" + channelName;
					script = script + "\n" + "updateDictionary " + channelName
							+ "{ port=\"" + portNumber + "\"}@resources";
				}
			}
		}
		c.stop();
		System.out.println("meth time consuming : " + c.displayTime());
		return script;
	}
	
	public String addBindingSubjectsEnforcementChannelResources() {
		String script = "";
		PolicyEditor policyEditor = new PolicyEditor(policy);
		Chrono c = new Chrono();
		c.start();
		for(User u : policyEditor.getUsers()){
			String user = u.getName();
			HashMap<String,HashSet<String>> opResources= new HashMap<String, HashSet<String>>();
				for(Role r : u.getAssignedRoles()){					
					for(Permission p : r.getPermissions()){
						for(Operation op : p.getOperations()){
							String operation = op.getName();
							String channelName = "subject" + user + operation;
							if(!opResources.keySet().contains(operation)){
								HashSet<String> resources = new HashSet<String>();
								opResources.put(operation, resources);
								portNumber = portNumber + 1;								
								script = script + "\n" + "addChannel " + channelName
										+ " : SocketChannel{name = \"" + channelName + "\"}";
								script = script + "\n" + "bind " + user + "." + operation
										+ "@subjects" + "=>" + channelName;
								bindings.add("bind " + user + "." + operation + "@subjects"
										+ "=>" + channelName);
								script = script + "\n" + "updateDictionary " + channelName
										+ "{ port=\"" + portNumber + "\"}@subjects";
							}
							for(Resource obj : op.getResources()){																
								if(! opResources.get(operation).contains(obj.getName())){
									opResources.get(operation).add(obj.getName());
									portNumber = portNumber + 1;
									script = script + "\n" + "bind " + obj.getName() + "."
											+ operation + "@resources" + " =>" + channelName;
									script = script + "\n" + "updateDictionary " + channelName
											+ "{ port=\"" + portNumber + "\"}@resources";
									//script = script + "\n" + "updateDictionary " + channelName+ "{ "+r.getName()+p.getName()+op.getName()+obj.getName()+"=\" true \"}";
								}
							}
						}
					}					
				}
			}
		c.stop();
		System.out.println("meth time consuming : " + c.displayTime());
		return script;
	}
	
	// generate script to add binding enforcementNEW
	public String addBindingSubjectsEnforcementChannelResources3() {
		String script = "";
		// ajout des relations subject -> channelsEnforcement -> resources
		// ajout des relations subject -> channelsEnforcement
		// System.out.println("1");
		Chrono c = new Chrono();
		c.start();
		HashMap<String, Vector<String>> channelSubjectUserOperation = new HashMap<String, Vector<String>>();
		for (rbac.PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (rbac.Role r : ((rbac.User) e).getAssignedRoles()) {
					for (rbac.Permission p : r.getPermissions()) {
						for (rbac.Operation o : p.getOperations()) {
							if (!channelSubjectUserOperation.containsKey(e
									.getName())) {
								channelSubjectUserOperation.put(e.getName(),
										new Vector<String>());
								channelSubjectUserOperation.get(e.getName())
										.add(o.getName());
							} else {
								if (!channelSubjectUserOperation.get(
										e.getName()).contains(o.getName())) {
									channelSubjectUserOperation
											.get(e.getName()).add(o.getName());
								}
							}

							for (rbac.Resource resource : o.getResources()) {
								// System.out.println("resource "+resource.getName());
								portNumber = portNumber + 1;
								String channelName = "subject" + e.getName()
										+ o.getName();

								script = script + "\n" + "bind "
										+ resource.getName() + "."
										+ o.getName() + "@resources" + " =>"
										+ channelName;
								script = script + "\n" + "updateDictionary "
										+ channelName + "{ port=\""
										+ portNumber + "\"}@resources";
							}
						}
					}
				}
			}
		}
		c.stop();
		System.out.println("meth time consuming : " + c.displayTime());
		// System.out.println("2");
		for (String e : channelSubjectUserOperation.keySet()) {
			for (String o : channelSubjectUserOperation.get(e)) {
				portNumber = portNumber + 1;
				String channelName = "subject" + e + o;
				script = script + "\n" + "addChannel " + channelName
						+ " : SocketChannel{name = \"" + channelName + "\"}";
				script = script + "\n" + "bind " + e + "." + o + "@subjects"
						+ "=>" + channelName;
				bindings.add("bind " + e + "." + o + "@subjects" + "=>"
						+ channelName);
				script = script + "\n" + "updateDictionary " + channelName
						+ "{ port=\"" + portNumber + "\"}@subjects";
			}
		}
		return script;
	}

	// NEW MAPPING
	// generate script to remove previously added binding
	public String removeBindingSubjectsEnforcementChannelResources() {
		String script = "";
		// ajout des relations subject -> channelsEnforcement -> resources
		for (String s : bindings) {
			script = script + "\n" + "un" + s;
		}
		bindings = new ArrayList<String>();
		return script;
	}

	// generate script to remove previously added binding
	public String removeNodes() {
		String script = "";
		// ajout des relations subject -> channelsEnforcement -> resources
		for (String s : nodes) {
			script = script + "\n" + "removeComponent " + s;
		}
		nodes = new ArrayList<String>();
		return script;
	}

	/*
	 * transform the policy into components, channels and bindings and trigger
	 * the adaptation
	 */
	public String transfoPolicyIntoKevScriptNEW() {
		String script = "";
		// System.out.println("remove bindings");
		// Chrono c = new Chrono();
		// c.start();
		script = script + removeBindingSubjectsEnforcementChannelResources();
		// c.stop();
		// System.out.println("rem bindings : " + c.displayTime());
		// System.out.println("remove nodes");
		// c.start();
		script = script + removeNodes();
		// c.stop();
		// System.out.println("rem nodes : " + c.displayTime());
		// add nodes and components
		// System.out.println("add subject");
		// c.start();
		script = script + addSubjects();
		// c.stop();
		// System.out.println("addsubjects : " + c.displayTime());
		// System.out.println("add resource");
		// c.start();
		script = script + addResources();
		// c.stop();
		// System.out.println("addResources : " + c.displayTime());
		// add channels + bindings
		// System.out.println("add bindings");
		// c.start();
		script = script + addBindingSubjectsEnforcementChannelResources();
		// c.stop();
		// System.out.println("addBindings : " + c.displayTime());
		portNumber = 42000;
		return script;
	}

	// HERE AFTER METHODS FOR ASE MAPPING

	// generate script to add users
	public String addUsers() {
		String script = "";
		// ajout d'un node user
		script = script + "\n" + "addNode users : JavaSENode";
		script = script + "\n" + "addChild users@node0";
		script = script + "\n" + "addToGroup sync users";
		script = script + "\n" + "updateDictionary sync{ port=\"8102\"}@users";

		// ajout des composants users
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				script = script + "\n" + "addComponent " + e.getName()
						+ "@users : User { }";
			}
		}
		return script;
	}

	// generate script to add roles
	public String addRoles() {
		String script = "";
		// ajout d'un node role
		script = script + "\n" + "addNode roles : JavaSENode";
		script = script + "\n" + "addChild roles@node0";
		script = script + "\n" + "addToGroup sync roles";
		script = script + "\n" + "updateDictionary sync{ port=\"8103\"}@roles";
		// ajout des composants roles
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				script = script + "\n" + "addComponent " + e.getName()
						+ "@roles : Role { }";
				portNumber = portNumber + 1;
			}
		}

		return script;
	}

	// generate script to add channel subjects Users
	public String addChannelSubjectsUsers() {
		String script = "";
		// adding channels subjects users
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					for (Permission p : r.getPermissions()) {
						for (Operation o : p.getOperations()) {
							String channelName = "subject" + e.getName()
									+ o.getName();
							script = script + "\n" + "addChannel "
									+ channelName
									+ " : SocketChannel{name = \""
									+ channelName + "\"}";
							portNumber = portNumber + 1;
						}
					}
				}
			}
		}
		return script;
	}

	// generate script to add channel Users roles
	public String addChannelUsersRoles() {
		String script = "";
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					for (Permission p : r.getPermissions()) {
						for (Operation o : p.getOperations()) {
							String channelName = e.getName() + o.getName();
							script = script + "\n" + "addChannel "
									+ channelName
									+ " : SocketChannel{name = \""
									+ channelName + "\"" + "}";
							portNumber = portNumber + 1;
						}
					}
				}
			}
		}
		return script;
	}

	// generate script to add channel roles resources
	public String addChannelRolesResources() {
		String script = "";
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				for (Permission p : ((Role) e).getPermissions()) {
					for (Operation o : p.getOperations()) {
						String channelName = p.getName() + o.getName();
						script = script + "\n" + "addChannel " + channelName
								+ " : SocketChannel{name = \"" + channelName
								+ "\"" + "}";
						portNumber = portNumber + 1;
					}
				}
			}
		}
		return script;
	}

	// generate script to add binding subjects users
	public String addBindingSubjectsUsers() {
		String script = "";
		// adding bindings subjects-usersChannelOp
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					for (Permission p : r.getPermissions()) {
						for (Operation o : p.getOperations()) {

							portNumber = portNumber + 1;
							String channelName = "subject" + e.getName()
									+ o.getName();
							script = script + "\n" + "bind " + e.getName()
									+ "." + o.getName() + "@subjects" + "=>"
									+ channelName;
							script = script + "\n" + "updateDictionary "
									+ channelName + "{ port=\"" + portNumber
									+ "\"}@subjects";
						}
					}
				}
			}

		}

		// adding bindings usersChannelOp-users
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					for (Permission p : r.getPermissions()) {
						for (Operation o : p.getOperations()) {
							portNumber = portNumber + 1;
							String channelName = "subject" + e.getName()
									+ o.getName();
							script = script + "\n" + "bind " + e.getName()
									+ "." + o.getName() + "@users" + "=>"
									+ channelName;
							script = script + "\n" + "updateDictionary "
									+ channelName + "{ port=\"" + portNumber
									+ "\"}@users";
						}
					}
				}
			}
		}
		return script;
	}

	// generate script to add binding users roles
	public String addBindingUsersRoles() {
		String script = "";
		// adding bindings users-ChannelRoleOp
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					for (Permission p : r.getPermissions()) {
						for (Operation o : p.getOperations()) {
							portNumber = portNumber + 1;
							String channelName = e.getName() + o.getName();
							script = script + "\n" + "bind " + e.getName()
									+ "." + o.getName() + "R@users" + "=>"
									+ channelName;
							script = script + "\n" + "updateDictionary "
									+ channelName + "{ port=\"" + portNumber
									+ "\"}@users";
						}
					}
				}
			}
		}

		// adding bindings ChannelRoleOp-roles
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role rol : ((User) e).getAssignedRoles()) {
					for (Permission p : rol.getPermissions()) {
						for (Operation o : p.getOperations()) {
							for (Role r : ((User) e).getAssignedRoles()) {
								portNumber = portNumber + 1;
								String channelName = e.getName() + o.getName();
								script = script + "\n" + "bind " + r.getName()
										+ "." + o.getName() + "@roles" + "=>"
										+ channelName;
								script = script + "\n" + "updateDictionary "
										+ channelName + "{ port=\""
										+ portNumber + "\"}@roles";
							}
						}
					}
				}
			}
		}
		return script;
	}

	// generate script to add binding roles resources
	public String addBindingRolesResources() {
		String script = "";
		// ajout des relations permissions roles -> resources
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				for (Permission p : ((Role) e).getPermissions()) {
					for (Operation o : p.getOperations()) {
						portNumber = portNumber + 1;
						String channelName = p.getName() + o.getName();
						script = script + "\n" + "bind " + e.getName() + "."
								+ o.getName() + "R@roles" + "=>" + channelName;
						script = script + "\n" + "updateDictionary "
								+ channelName + "{ port=\"" + portNumber
								+ "\"}@roles";

					}
				}
			}
		}

		// ajout des relations permissions roles -> resources
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				for (rbac.Permission p : ((rbac.Role) e).getPermissions()) {
					for (rbac.Operation o : p.getOperations()) {
						for (rbac.Resource c : o.getResources()) {
							portNumber = portNumber + 1;
							String channelName = p.getName() + o.getName();
							script = script + "\n" + "bind " + c.getName()
									+ "." + o.getName() + "@resources" + " =>"
									+ channelName;
							script = script + "\n" + "updateDictionary "
									+ channelName + "{ port=\"" + portNumber
									+ "\"}@resources";
						}
					}
				}
			}
		}
		return script;
	}

	/*
	 * transform the policy into components, channels and bindings and trigger
	 * the adaptation
	 */
	public String transfoPolicyIntoKevScriptASE() {

		String script = "";
		// add nodes and components
		script = script + addSubjects();
		script = script + addUsers();
		script = script + addRoles();
		script = script + addResources();
		// add channels
		script = script + addChannelSubjectsUsers();
		script = script + addChannelUsersRoles();
		script = script + addChannelRolesResources();
		// add bindings
		script = script + addBindingSubjectsUsers();
		script = script + addBindingUsersRoles();
		script = script + addBindingRolesResources();
		portNumber = 42000;
		return script;
	}

}