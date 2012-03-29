package addressBook.enforcement;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.framework.AbstractComponentType;

import rbac.rbac.*;
import rbac.rbac.generator.PolicyGenerator;
import rbac.rbac.impl.ResourceImpl;
import rbac.rbac.impl.RoleImpl;
import rbac.rbac.impl.UserImpl;
import utils.time.Chrono;

@ComponentType()
public class Reasoner extends AbstractComponentType {

	
	
	private DummyGUIReasoner gui;
	private Policy policy;
	private ResourceSet resourceSetMetamodel;
	private Resource resourceModel;
	private Vector<String> reasonerOperations;
	private Vector<String> addressBookOperations;
	private KevScriptEngine kse;
	private int portNumber;
	private ArrayList<String> bindings;
	private PolicyGenerator policyGenerator;
	
	@Start
	public void start() {
		bindings = new ArrayList<String>();
		
		System.out.println("hello Reasoner");
		reasonerOperations = new Vector<String>();
		reasonerOperations.add("initPolicyExample1"); 
		reasonerOperations.add("initPolicyExample2");
		reasonerOperations.add("displayPolicy");
		reasonerOperations.add("checkPolicy");
		reasonerOperations.add("enforcePolicyASE");
		reasonerOperations.add("enforcePolicyNEW");
		reasonerOperations.add("launchTest");

		addressBookOperations = new Vector<String>();
		addressBookOperations.add("create");
		addressBookOperations.add("read");
		addressBookOperations.add("update");
		addressBookOperations.add("delete");

		gui = new DummyGUIReasoner(this);
		gui.setTitle("Reasoner : " + getName());
		gui.setVisible(true);
		gui.updateEntities(reasonerOperations);
		
		policyGenerator = new PolicyGenerator(policy);
		policyGenerator.initPolicy();
		/*
		 * TO DO : see where to put the resources folder and what is the path to set to access it  
		 */
		
		
//		//test load EMF
//		loadPolicyModel();
//		for(PolicyElement e : policy.getElements()){
//			System.out.println(e.getName());
//		}
		

		portNumber = 42000;
		kse = getKevScriptEngineFactory().createKevScriptEngine();
	}

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
				.createFileURI("Policy.xmi"));
		try {
			resourceModel.load(null);
		} catch (IOException e) {
			System.out.println("error during the model loading step");
			e.printStackTrace();
		}
		// INSTANTIATE ROOTELEMENT WITH THE CONTAINERROOT OF THE LOADED MODEL
		policy = (Policy) resourceModel.getContents().get(0);	
		for(PolicyElement e : policy.getElements()){
			System.out.println(e.getName());
		}
	}
	
	@Stop
	public void stop() {
	}

	@Update
	public void update() {
		gui.setTitle("AddressBookClient : " + getName());
	}
	
	public void launchTest(){
		gui.updateTextArea("Launching test");
		gui.updateTextArea("1 generating valid test model (3,3)");
		Chrono c = new Chrono();
		c.start();
		policyGenerator.initPolicyExamples(3, 3, true, false, false, false, false);
		c.stop();
		gui.updateTextArea("1 : "+c.displayTime());
		
		gui.updateTextArea("2 checking it");
		c.start();
		checkPolicy();		
		c.stop();
		gui.updateTextArea("2 : "+c.displayTime());
		
		gui.updateTextArea("3 transforming the policy");
		c.start();
		enforcePolicyNEW();
		c.stop();
		gui.updateTextArea("3 : "+c.displayTime());
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
							for (rbac.rbac.Resource re : o.getResources()) {
								gui.updateTextArea("    resource(s) : "
										+ re.getName());
							}
						}
					}
				}
			}
		}
	}

	//check the policy against nameNotNull SSoD DSoD AID AIH
	public void checkPolicy(){
		PolicyChecker pc = new PolicyChecker(policy);	
		//gui.updateTextArea(pc.checkPolicy());
		gui.updateTextArea(pc.checkPolicyWithoutTime());
	}
	
	//generate script to add subjects
	public String addSubjects() {
		String script = "";
		// ajout d'un node subjects
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding subjects components");
		script = script + "\n" + "addNode subjects : JavaSENode";
		script = script + "\n" + "addChild subjects@node0";
		script = script + "\n" + "addToGroup sync subjects";
		script = script + "\n"
				+ "updateDictionary sync{ port=\"8101\"}@subjects";
		// ajout des composants addressBookClients
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				gui.updateTextArea("subject : " + e.getName());
				script = script + "\n" + "addComponent " + e.getName()
						+ "@subjects : AddressBookClient";
			}
		}
		return script;
	}

	//generate script to add users
	public String addUsers() {
		String script = "";
		// ajout d'un node user
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding users components");
		script = script + "\n" + "addNode users : JavaSENode";
		script = script + "\n" + "addChild users@node0";
		script = script + "\n" + "addToGroup sync users";
		script = script + "\n" + "updateDictionary sync{ port=\"8102\"}@users";

		// ajout des composants users
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				gui.updateTextArea("user : " + e.getName());
				script = script + "\n" + "addComponent " + e.getName()
						+ "@users : User { }";
			}
		}
		return script;
	}

	//generate script to add roles
	public String addRoles() {
		String script = "";
		// ajout d'un node role
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding roles components");
		script = script + "\n" + "addNode roles : JavaSENode";
		script = script + "\n" + "addChild roles@node0";
		script = script + "\n" + "addToGroup sync roles";
		script = script + "\n" + "updateDictionary sync{ port=\"8103\"}@roles";
		// ajout des composants roles
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof RoleImpl) {
				gui.updateTextArea("role : " + e.getName());
				script = script + "\n" + "addComponent " + e.getName()
						+ "@roles : Role { }";
				portNumber = portNumber + 1;
			}
		}
		gui.updateTextArea("added roles components : "
				+ kse.atomicInterpretDeploy());
		return script;
	}

	//generate script to add ressources
	public String addResources() {
		String script = "";
		// ajout d'un node resources
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding resources components");
		script = script + "\n" + "addNode resources : JavaSENode";
		script = script + "\n" + "addChild resources@node0";
		script = script + "\n" + "addToGroup sync resources";
		script = script + "\n"
				+ "updateDictionary sync{ port=\"8104\"}@resources";
		// ajout des composants addressBooks
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof ResourceImpl) {
				gui.updateTextArea("resource : " + e.getName());
				script = script + "\n" + "addComponent " + e.getName()
						+ "@resources : AddressBook { }";
				portNumber = portNumber + 1;
			}
		}
		return script;
	}

	//generate script to add channel subjects Users
	public String addChannelSubjectsUsers() {
		String script = "";
		// adding channels subjects users
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding channels subjects users");
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (String s : addressBookOperations) {
					String channelName = "subject" + e.getName() + s;
					script = script + "\n" + "addChannel " + channelName
							+ " : SocketChannel{name = \"" + channelName + "\"}";
					portNumber = portNumber + 1;
				}
			}
		}
		return script;
	}

	//generate script to add channel Users roles
	public String addChannelUsersRoles() {
		String script = "";
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
				}
			}
		}
		return script;
	}

	//generate script to add channel roles resources
	public String addChannelRolesResources() {
		String script = "";
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
					}
				}
			}
		}
		return script;
	}

	//generate script to add binding subjects users
	public String addBindingSubjectsUsers() {
		String script = "";
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
				}
			}
		}
		return script;
	}

	//generate script to add binding users roles
	public String addBindingUsersRoles() {
		String script ="";
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
					}
				}
			}
		}
		return script;
	}

	//generate script to add binding roles resources
	public String addBindingRolesResources() {
		String script ="";
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
						for (rbac.rbac.Resource c : o.getResources()) {
							portNumber = portNumber + 1;
							String channelName = p.getName() + o.getName();
							script = script +"\n"+ "bind " + c.getName() + "."
									+ o.getName() + "@resources" + " =>"
									+ channelName;
							script = script +"\n"+ "updateDictionary " + channelName
									+ "{ port=\"" + portNumber
									+ "\"}@resources";
						}
					}
				}
			}
		}
		return script;
	}

	//NEW MAPPING
	//generate script to remove previously added binding
	public String removeBindingSubjectsEnforcementChannelResources() {
		String script ="";
		// ajout des relations subject -> channelsEnforcement -> resources
		gui.updateTextArea("************************************");
		gui.updateTextArea("removing bindings subject -> channelsEnforcement -> resources");
		for(String s : bindings){
			script = script + "\n" + "un"+s;
		}
		bindings = new ArrayList<String>();
		return script;
	}

	//generate script to add binding enforcementNEW
	public String addBindingSubjectsEnforcementChannelResources() {
		String script ="";
		// ajout des relations subject -> channelsEnforcement -> resources
		gui.updateTextArea("************************************");
		gui.updateTextArea("adding bindings subject -> channelsEnforcement -> resources");
		// ajout des relations subject -> channelsEnforcement
		HashMap<String,Vector<String>> channelSubjectUserOperation =new HashMap<String, Vector<String>>();
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					for (Permission p : r.getPermissions()) {
						for (Operation o : p.getOperations()){
							if (! channelSubjectUserOperation.containsKey(e.getName())){
								channelSubjectUserOperation.put(e.getName(), new Vector<String>());
								channelSubjectUserOperation.get(e.getName()).add(o.getName());
							}
							else{
								if (!channelSubjectUserOperation.get(e.getName()).contains(o.getName())){
									channelSubjectUserOperation.get(e.getName()).add(o.getName());
								}
							}
						}
					}
				}
			}
		}
		for(String e : channelSubjectUserOperation.keySet()){
			for(String o : channelSubjectUserOperation.get(e)){
				portNumber = portNumber + 1;
				String channelName = "subject" + e + o;
				script = script + "\n" + "addChannel " + channelName + " : SocketChannel{name = \"" + channelName + "\"}";
				script = script + "\n" + "bind " + e + "." + o + "@subjects" + "=>" + channelName;
				bindings.add("bind " + e + "." + o + "@subjects" + "=>" + channelName);
				script = script + "\n" + "updateDictionary " + channelName + "{ port=\"" + portNumber + "\"}@subjects";
			}
		}
				
		// ajout des relations channelsEnforcement -> resources
		for (PolicyElement e : policy.getElements()) {
			if (e instanceof UserImpl) {
				for (Role r : ((User) e).getAssignedRoles()) {
					for (Permission p : r.getPermissions()) {
						for (Operation o : p.getOperations()){
							for (rbac.rbac.Resource resource : o.getResources()) {
								portNumber = portNumber + 1;
								String channelName = "subject"+e.getName() + o.getName();
								
								script = script +"\n"+ "bind " + resource.getName() + "."+ o.getName() + "@resources" + " =>"+ channelName;
								script = script +"\n"+ "updateDictionary " + channelName+ "{ port=\"" + portNumber+ "\"}@resources";
							}
						}
					}
				}
			}
		}
		return script;
	}
	
	/*
	 * transform the policy into components, channels and bindings and trigger the adaptation
	 */
	public void enforcePolicyASE() {
		
		String script = "";
		//add nodes and components
		script = script + addSubjects();
		script = script + addUsers();
		script = script + addRoles();
		script = script + addResources();
		//add channels
		script = script + addChannelSubjectsUsers();
		script = script +addChannelUsersRoles();
		script = script +addChannelRolesResources();
		//add bindings
		script = script + addBindingSubjectsUsers();
		script = script + addBindingUsersRoles();
		script = script + addBindingRolesResources();
		//apply reconfiguration script
		kse = getKevScriptEngineFactory().createKevScriptEngine();
		kse.append(script);
		Boolean scriptApplied = kse.atomicInterpretDeploy();
		
		System.out.println("scriptApplied : " +scriptApplied);
		gui.updateTextArea("scriptApplied : " +scriptApplied);
		portNumber = 42000;
	}
	
	/*
	 * transform the policy into components, channels and bindings and trigger the adaptation
	 */
	public void enforcePolicyNEW() {
		String script = "";
		//add nodes and components
		script = script + addSubjects();
		script = script + addResources();
		//add channels + bindings
		script = script + removeBindingSubjectsEnforcementChannelResources();
		script = script + addBindingSubjectsEnforcementChannelResources();
		//apply reconfiguration script
		kse = getKevScriptEngineFactory().createKevScriptEngine();
		kse.append(script);
		Boolean scriptApplied = kse.atomicInterpretDeploy();
		System.out.println("scriptApplied : " +scriptApplied);
		gui.updateTextArea("scriptApplied : " +scriptApplied);
		portNumber = 42000;
	}
	
	public PolicyGenerator getPolicyGenerator() {
		return policyGenerator;
	}	
}