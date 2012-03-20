package addressBook.enforcement;

import java.util.Vector;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.framework.AbstractComponentType;

import rbac.rbac.*;
import rbac.rbac.impl.ResourceImpl;
import rbac.rbac.impl.RoleImpl;
import rbac.rbac.impl.UserImpl;

@ComponentType()
public class Reasoner extends AbstractComponentType {
	private DummyGUIReasoner gui;
	private Policy policy;
	private Vector<String> reasonerOperations;
	private Vector<String> addressBookOperations;
	private KevScriptEngine kse;
	private int portNumber;

	@Start
	public void start() {
		System.out.println("hello Reasoner");
		reasonerOperations = new Vector<String>();
		reasonerOperations.add("displayPolicy");
		reasonerOperations.add("enforcePolicy");

		addressBookOperations = new Vector<String>();
		addressBookOperations.add("create");
		addressBookOperations.add("read");
		addressBookOperations.add("update");
		addressBookOperations.add("delete");

		gui = new DummyGUIReasoner(this);
		gui.setTitle("Reasoner : " + getName());
		gui.setVisible(true);
		gui.updateEntities(reasonerOperations);

		initPolicy();

		portNumber = 42000;
		kse = getKevScriptEngineFactory().createKevScriptEngine();
	}

	@Stop
	public void stop() {
	}

	@Update
	public void update() {
		gui.setTitle("AddressBookClient : " + getName());
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
		Resource employeeAddressBook = RbacFactory.eINSTANCE.createResource();
		employeeAddressBook.setName("employeeAddressBook");

		Resource externalContactAddressBook = RbacFactory.eINSTANCE
				.createResource();
		externalContactAddressBook.setName("externalContactAddressBook");

		Resource emergencyAddressBook = RbacFactory.eINSTANCE.createResource();
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

	/*
	 * display the policy on the dummy gui
	 */
	public void displayPolicy() {
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				gui.updateTextArea("user : " + e.getName());
				for (Role r : ((User) e).getAssignedRoles()) {
					gui.updateTextArea(" assigned role(s) : " + r.getName());
					for (Permission p : r.getPermissions()) {
						gui.updateTextArea("  permission(s) : " + p.getName());
						for (Operation o : p.getOperations()) {
							gui.updateTextArea("   operation(s) : "
									+ o.getName());
							for (Resource re : o.getResources()) {
								gui.updateTextArea("    resource(s) : "
										+ re.getName());
							}
						}
					}
				}
			}
		}

	}

	public String addSubjects() {
		String script = "";
		// kse = getKevScriptEngineFactory()
		// .createKevScriptEngine();
		// ajout d'un node subjects
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding subjects components");
		// kse.append("addNode subjects : JavaSENode");
		// kse.append("addChild subjects@node0");
		// kse.append("addToGroup sync subjects");
		// kse.append("updateDictionary sync{ port=\"8101\"}@subjects");
		script = script + "\n" + "addNode subjects : JavaSENode";
		script = script + "\n" + "addChild subjects@node0";
		script = script + "\n" + "addToGroup sync subjects";
		script = script + "\n"
				+ "updateDictionary sync{ port=\"8101\"}@subjects";
		// ajout des composants addressBookClients
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				gui.updateTextArea("subject : " + e.getName());
				// kse.append("addComponent " + e.getName()
				// + "@subjects : AddressBookClient { }");
				script = script + "\n" + "addComponent " + e.getName()
						+ "@subjects : AddressBookClient";
			}
		}
//		gui.updateTextArea("added subjects components : "
//				+ kse.atomicInterpretDeploy());
		// kse.clearScript();
		return script;
	}

	public String addUsers() {
		String script = "";
		// kse = getKevScriptEngineFactory()
		// .createKevScriptEngine();
		// ajout d'un node user
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding users components");
		// kse.append("addNode users : JavaSENode");
		// kse.append("addChild users@node0");
		// kse.append("addToGroup sync users");
		// kse.append("updateDictionary sync{ port=\"8102\"}@users");
		script = script + "\n" + "addNode users : JavaSENode";
		script = script + "\n" + "addChild users@node0";
		script = script + "\n" + "addToGroup sync users";
		script = script + "\n" + "updateDictionary sync{ port=\"8102\"}@users";

		// ajout des composants users
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				gui.updateTextArea("user : " + e.getName());
				// kse.append("addComponent " + e.getName() +
				// "@users : User { }");
				script = script + "\n" + "addComponent " + e.getName()
						+ "@users : User { }";
			}
		}
//		gui.updateTextArea("added users components : "
//				+ kse.atomicInterpretDeploy());
		// kse.clearScript();
		return script;
	}

	public String addRoles() {
		String script = "";
		// kse = getKevScriptEngineFactory()
		// .createKevScriptEngine();
		// ajout d'un node role
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding roles components");
		// kse.append("addNode roles : JavaSENode");
		// kse.append("addChild roles@node0");
		// kse.append("addToGroup sync roles");
		// kse.append("updateDictionary sync{ port=\"8103\"}@roles");
		script = script + "\n" + "addNode roles : JavaSENode";
		script = script + "\n" + "addChild roles@node0";
		script = script + "\n" + "addToGroup sync roles";
		script = script + "\n" + "updateDictionary sync{ port=\"8103\"}@roles";
		// ajout des composants roles
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				gui.updateTextArea("role : " + e.getName());
				// kse.append("addComponent " + e.getName() +
				// "@roles : Role { }");
				script = script + "\n" + "addComponent " + e.getName()
						+ "@roles : Role { }";
				portNumber = portNumber + 1;
			}
		}
		gui.updateTextArea("added roles components : "
				+ kse.atomicInterpretDeploy());
		// kse.clearScript();
		return script;
	}

	public String addResources() {
		String script = "";
		// kse = getKevScriptEngineFactory()
		// .createKevScriptEngine();
		// ajout d'un node resources
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding resources components");
		// kse.append("addNode resources : JavaSENode");
		// kse.append("addChild resources@node0");
		// kse.append("addToGroup sync resources");
		// kse.append("updateDictionary sync{ port=\"8104\"}@resources");
		script = script + "\n" + "addNode resources : JavaSENode";
		script = script + "\n" + "addChild resources@node0";
		script = script + "\n" + "addToGroup sync resources";
		script = script + "\n"
				+ "updateDictionary sync{ port=\"8104\"}@resources";
		// ajout des composants addressBooks
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof ResourceImpl) {
				gui.updateTextArea("resource : " + e.getName());
				// kse.append("addComponent " + e.getName()
				// + "@resources : AddressBook { }");
				script = script + "\n" + "addComponent " + e.getName()
						+ "@resources : AddressBook { }";
				portNumber = portNumber + 1;
			}
		}
		// gui.updateTextArea("added resources components : "+kse.atomicInterpretDeploy());
		// kse.clearScript();
		return script;
	}

	public String addChannelSubjectsUsers() {
		String script = "";
		// kse = getKevScriptEngineFactory()
		// .createKevScriptEngine();
		// adding channels subjects users
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding channels subjects users");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (String s : addressBookOperations) {
					String channelName = "subject" + e.getName() + s;
					script = script + "\n" + "addChannel " + channelName
							+ " : SocketChannel{name = \"" + channelName + "\"}";
					// gui.updateTextArea("script : " + script);
					// kse.append(script);
					portNumber = portNumber + 1;
				}
			}
		}
		// kse.interpretDeploy();
		// kse.clearScript();
		return script;
	}

	public String addChannelUsersRoles() {
		String script = "";
		// kse = getKevScriptEngineFactory()
		// .createKevScriptEngine();
		// adding channels users roles
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding channels users roles");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (String s : addressBookOperations) {
					String channelName = e.getName() + s;
					script = script + "\n" + "addChannel " + channelName
							+ " : SocketChannel{name = \"" + channelName + "\""
							+ "}";
					portNumber = portNumber + 1;
					// gui.updateTextArea("script : " + script);
					// kse.append(script);
				}
			}
		}
		// kse.interpretDeploy();
		// kse.clearScript();
		return script;
	}

	public String addChannelRolesResources() {
		String script = "";
		// kse = getKevScriptEngineFactory()
		// .createKevScriptEngine();
		// ajout des channels roles -> resources
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding channels roles resources");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				for (Permission p : ((Role) e).getPermissions()) {
					for (Operation o : p.getOperations()) {
						String channelName = p.getName() + o.getName();
						script = script + "\n" + "addChannel " + channelName
								+ " : SocketChannel{name = \"" + channelName
								+ "\"" + "}";
						portNumber = portNumber + 1;
						// gui.updateTextArea("script : " + script);
						// kse.append(script);
					}
				}
			}
		}
		// kse.interpretDeploy();
		// kse.clearScript();
		return script;
	}

	public String addBindingSubjectsUsers() {
		String script = "";
		// kse = getKevScriptEngineFactory()
		// .createKevScriptEngine();
		// adding bindings subjects-usersChannelOp
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding bindings subjects-usersChannelOp");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (String s : addressBookOperations) {
					portNumber = portNumber + 1;
					String channelName = "subject" + e.getName() + s;
					script = script + "\n" + "bind " + e.getName() + "." + s
							+ "@subjects" + "=>" + channelName;
					script = script + "\n" + "updateDictionary " + channelName
							+ "{ port=\"" + portNumber + "\"}@subjects";
					portNumber = portNumber + 1;
					// gui.updateTextArea("script : " + script);
					// gui.updateTextArea("script2 : " + script2);
					// kse.append(script);
					// kse.interpretDeploy();
					// kse.clearScript();
					// kse.append(script2);
					// kse.interpretDeploy();
					// kse.clearScript();
				}
			}

		}

		// adding bindings usersChannelOp-users
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding bindings usersChannelOp-users");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (String s : addressBookOperations) {
					portNumber = portNumber + 1;
					String channelName = "subject" + e.getName() + s;
					script = script + "\n" + "bind " + e.getName() + "." + s
							+ "@users" + "=>" + channelName;
					script = script + "\n" + "updateDictionary " + channelName
							+ "{ port=\"" + portNumber + "\"}@users";
					portNumber = portNumber + 1;
					// gui.updateTextArea("script : " + script);
					// gui.updateTextArea("script2 : " + script2);
					// kse.append(script);
					// kse.interpretDeploy();
					// kse.clearScript();
					// kse.append(script2);
					// kse.interpretDeploy();
					// kse.clearScript();
				}
			}
		}
		return script;
	}

	public String addBindingUsersRoles() {
		String script ="";
//		kse = getKevScriptEngineFactory().createKevScriptEngine();
		// adding bindings users-ChannelRoleOp
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding bindings users-ChannelRoleOp");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (String s : addressBookOperations) {
					portNumber = portNumber + 1;
					String channelName = e.getName() + s;
					script = script +"\n"+ "bind " + e.getName() + "." + s + "R@users"
							+ "=>" + channelName;
					script = script +"\n"+ "updateDictionary " + channelName
							+ "{ port=\"" + portNumber + "\"}@users";
					portNumber = portNumber + 1;
//					gui.updateTextArea("script : " + script);
//					gui.updateTextArea("script2 : " + script2);
//					kse.append(script);
//					kse.interpretDeploy();
//					kse.clearScript();
//					kse.append(script2);
//					kse.interpretDeploy();
//					kse.clearScript();
				}
			}
		}

		// adding bindings ChannelRoleOp-roles
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding bindings ChannelRoleOp-roles");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (String s : addressBookOperations) {
					for (Role r : ((User) e).getAssignedRoles()) {
						portNumber = portNumber + 1;
						String channelName = e.getName() + s;
						script = script +"\n"+"bind " + r.getName() + "." + s
								+ "@roles" + "=>" + channelName;
						script = script +"\n"+"updateDictionary " + channelName
								+ "{ port=\"" + portNumber + "\"}@roles";
						portNumber = portNumber + 1;
//						gui.updateTextArea("script : " + script);
//						gui.updateTextArea("script2 : " + script2);
//						kse.append(script);
//						kse.interpretDeploy();
//						kse.clearScript();
//						kse.append(script2);
//						kse.interpretDeploy();
//						kse.clearScript();
					}
				}
			}
		}
		return script;
	}

	public String addBindingRolesResources() {
		String script ="";
//		kse = getKevScriptEngineFactory().createKevScriptEngine();
		// ajout des relations permissions roles -> resources
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding bindings roles-channels");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				for (Permission p : ((Role) e).getPermissions()) {
					for (Operation o : p.getOperations()) {
						portNumber = portNumber + 1;
						String channelName = p.getName() + o.getName();
						script = script +"\n"+"bind " + e.getName() + "."
								+ o.getName() + "R@roles" + "=>" + channelName;
						script = script +"\n"+"updateDictionary " + channelName
								+ "{ port=\"" + portNumber + "\"}@roles";
						portNumber = portNumber + 1;
//						gui.updateTextArea("script : " + script);
//						gui.updateTextArea("script2 : " + script2);
//						kse.append(script);
//						kse.interpretDeploy();
//						kse.clearScript();
//						kse.append(script2);
//						kse.interpretDeploy();
//						kse.clearScript();
					}
				}
			}
		}

		// ajout des relations permissions roles -> resources
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding bindings channels-resources");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				for (Permission p : ((Role) e).getPermissions()) {
					for (Operation o : p.getOperations()) {
						for (Resource c : o.getResources()) {
							portNumber = portNumber + 1;
							String channelName = p.getName() + o.getName();
							script = script +"\n"+ "bind " + c.getName() + "."
									+ o.getName() + "@resources" + " =>"
									+ channelName;
							script = script +"\n"+ "updateDictionary " + channelName
									+ "{ port=\"" + portNumber
									+ "\"}@resources";
//							gui.updateTextArea("script : " + script);
//							gui.updateTextArea("script2 : " + script2);
//							kse.append(script);
//							kse.interpretDeploy();
//							kse.clearScript();
//							kse.append(script2);
//							kse.interpretDeploy();
//							kse.clearScript();
						}
					}
				}
			}
		}
		return script;
	}

	/*
	 * transform the policy into components, channels and bindings
	 */
	public void enforcePolicy() {

		String script = "";
		script = script + addSubjects();
		script = script + addUsers();
		script = script + addRoles();
		script = script + addResources();

		script = script + addChannelSubjectsUsers();
		script = script +addChannelUsersRoles();
		script = script +addChannelRolesResources();

		script = script + addBindingSubjectsUsers();
		script = script + addBindingUsersRoles();
		script = script + addBindingRolesResources();

		kse = getKevScriptEngineFactory().createKevScriptEngine();
		kse.append(script);
		gui.updateTextArea("enforced : " + kse.atomicInterpretDeploy());

		// ajout des bindings entre users and roles
		// kse.append("updateDictionary nodes {host=node1}");
	}

}
