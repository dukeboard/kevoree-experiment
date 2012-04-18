package transformations;

import java.util.ArrayList;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import cartesiancoordinatesystem.CartesianCoordinateSystem;
import cartesiancoordinatesystem.CartesiancoordinatesystemFactory;
import cartesiancoordinatesystem.Point;

import patternbuilders.policyInfo.PatternBuilderForobject;
import patternbuilders.policyInfo.PatternBuilderForuser;
import patternbuilders.policyInfo.PatternBuilderForuserRule;
import patternmatchers.policyInfo.ObjectMatcher;
import patternmatchers.policyInfo.UserMatcher;
import patternmatchers.policyInfo.UserRuleMatcher;
import policyManager.PolicyManager;
import policyManager.PolicyManagerGUI;

import rbac.*;
import rbacTextualEditor.editor.graphicComponents.CartesianCoordinateSystemView;
import rbacTools.editor.PolicyEditor;
import rbacTools.generator.PolicyGenerator;
import signatures.policyInfo.ObjectSignature;
import signatures.policyInfo.UserRuleSignature;
import signatures.policyInfo.UserSignature;
import utils.time.Chrono;

public class IncrementalPolicy2KevScript {

	private Policy policy;
	private int portNumber;
	private ArrayList<String> bindings;
	private ArrayList<String> nodes;
	private UserMatcher userMatcher;
	private ObjectMatcher objectMatcher;
	private UserRuleMatcher userRuleMatcher;
	final DeltaMonitor<UserSignature> monitorUser;
	final DeltaMonitor<ObjectSignature> monitorObject;
	final DeltaMonitor<UserRuleSignature> monitorUserRule;
	private CartesianCoordinateSystem systemCartesian;
	private CartesiancoordinatesystemFactory fact = CartesiancoordinatesystemFactory.eINSTANCE;
	Chrono c = new Chrono();
	
	public IncrementalPolicy2KevScript(Policy p) {
		policy = p;
		portNumber = 42000;
		bindings = new ArrayList<String>();
		nodes = new ArrayList<String>();
		BuilderRegistry.getContributedStatelessPatternBuilders().put(UserMatcher.FACTORY.getPatternName(),new PatternBuilderForuser());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(ObjectMatcher.FACTORY.getPatternName(),new PatternBuilderForobject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(UserRuleMatcher.FACTORY.getPatternName(),new PatternBuilderForuserRule());
		try {
			userMatcher = UserMatcher.FACTORY.getMatcher(policy);
			objectMatcher = ObjectMatcher.FACTORY.getMatcher(policy);
			userRuleMatcher = UserRuleMatcher.FACTORY.getMatcher(policy);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}	
		monitorUser = userMatcher.newDeltaMonitor(false);
		monitorObject = objectMatcher.newDeltaMonitor(false);
		monitorUserRule = userRuleMatcher.newDeltaMonitor(false);
	}
	
	public IncrementalPolicy2KevScript(Policy p,CartesianCoordinateSystem sys) {
		systemCartesian = sys;
		fact = CartesiancoordinatesystemFactory.eINSTANCE;
		policy = p;
		portNumber = 42000;
		bindings = new ArrayList<String>();
		nodes = new ArrayList<String>();
		BuilderRegistry.getContributedStatelessPatternBuilders().put(UserMatcher.FACTORY.getPatternName(),new PatternBuilderForuser());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(ObjectMatcher.FACTORY.getPatternName(),new PatternBuilderForobject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(UserRuleMatcher.FACTORY.getPatternName(),new PatternBuilderForuserRule());
		try {
			userMatcher = UserMatcher.FACTORY.getMatcher(policy);
			objectMatcher = ObjectMatcher.FACTORY.getMatcher(policy);
			userRuleMatcher = UserRuleMatcher.FACTORY.getMatcher(policy);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}	
		monitorUser = userMatcher.newDeltaMonitor(false);
		monitorObject = objectMatcher.newDeltaMonitor(false);
		monitorUserRule = userRuleMatcher.newDeltaMonitor(false);
		c.start();
	}
	
	//***************************GENERAL   TRANSFORMATION****************************
	//have to be applied to the first deployment of the system
		public String transformation(){
			String script ="";
			script = script + addStaticArchitecturalElements();
			script = script + addSubjects();
			script = script + addObjects();
			script = script + addUserRules();
			return script;
		}
		
		
	//***************************INCREMENTAL TRANSFORMATION***************************
	// allows to apply policy changes transformations incrementally
	// based on a monitor the transformation have to be triggered when the policy change
	// also have to be initiated once the system have been deployed after the first transformation
		public void incrementalTransformation(){
			userMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
					System.out.println("SUBJECTS");
					System.out.println("found events");
					for(UserSignature sig : monitorUser.matchFoundEvents){
						System.out.println("sig : "+sig.getValueOfU());
						addSubject(((User)sig.getValueOfU()).getName());
					}
					
					System.out.println("lost events");
					for(UserSignature sig : monitorUser.matchLostEvents){
						System.out.println("sig : "+sig.getValueOfU());
					}
					
					System.out.println("all signatures");
					for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
						System.out.println("sig : "+sig.getValueOfU());
					}
					System.out.println("\n");
					monitorUser.clear();
				}
			});
			
			objectMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
					System.out.println("found events");
					System.out.println("OBJECTS");
					for(ObjectSignature sig : monitorObject.matchFoundEvents){
						System.out.println("sig : "+sig.getValueOfOB());
						addObject(((Resource)sig.getValueOfOB()).getName());
					}
					
					System.out.println("lost events");
					for(ObjectSignature sig : monitorObject.matchLostEvents){
						System.out.println("sig : "+sig.getValueOfOB());
					}
					
					System.out.println("all signatures");
					for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
						System.out.println("sig : "+sig.getValueOfOB());
					}
					System.out.println("\n");
					monitorUser.clear();
				}
			});
			
			userRuleMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
					System.out.println("USER RULE");
					System.out.println("found events");
					for(UserRuleSignature sig : monitorUserRule.matchFoundEvents){
						System.out.println("sig : "+sig.getValueOfUSER());
						addUserRule(((User)sig.getValueOfUSER()).getName(), ((Operation)sig.getValueOfOPERATION()).getName(), ((Resource)sig.getValueOfOBJECT()).getName());
					}
					
					System.out.println("lost events");
					for(UserRuleSignature sig : monitorUserRule.matchLostEvents){
						System.out.println("sig : "+sig.getValueOfUSER());
					}
					
					System.out.println("all signatures");
					for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
						System.out.println("sig : "+sig.getValueOfUSER());
					}
					System.out.println("\n");
					monitorUser.clear();
				}
			});		
		}
		
		
		public void incrementalTransformation(String bibi){
			
			userRuleMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
					Point p = fact.createPoint();
					p.setX(c.getTimeMsSpecial());
					System.out.println(c.getTimeMsSpecial()+":"+numberRules());
					p.setY(numberRules());
					systemCartesian.getPoints().add(p);
					monitorUser.clear();
					
					
				}
			});		
		}
		
		public void showGraphic(){
			CartesianCoordinateSystemView v = new CartesianCoordinateSystemView(systemCartesian);
			v.setVisible(true);
		}
		
		public int numberRules(){
			return userRuleMatcher.getAllMatchesAsSignature().size();
		}
	
	//***************************************static choices to modify later********************************************
		public String addStaticArchitecturalElements(){
			String script="";
			// ajout d'un node subjects
			script = script + "\n" + "addNode subjects : JavaSENode";
			script = script + "\n" + "addChild subjects@node0";
			script = script + "\n" + "addToGroup sync subjects";
			script = script + "\n" + "updateDictionary sync{ port=\"8101\"}@subjects";
			// ajout d'un node resources
			script = script + "\n" + "addNode resources : JavaSENode";
			script = script + "\n" + "addChild resources@node0";
			script = script + "\n" + "addToGroup sync resources";
			script = script + "\n" + "updateDictionary sync{ port=\"8104\"}@resources";				
			return script;
		}
	

	//**************************Subject management************************************************
	public void infoSubject(){		
		System.out.println("number of users : "+userMatcher.countMatches());
		for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
			System.out.println("sig : "+sig.getValueOfU());
		}
	}
	
	public String addSubject(String userName){
		String script = "";		
		script = script + "\n" + "addComponent " + userName + "@subjects : AddressBookClient";	
		nodes.add(userName + "@subjects");
		return script;	
	}
	
	public String addSubjects(){
		String script = "";
		for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
			String userName = ((User)sig.getValueOfU()).getName();
			script = script + "\n" + "addComponent " + userName + "@subjects : AddressBookClient";	
			nodes.add(userName + "@subjects");
		}
		return script;
	}
	
	//**************************Object management****************************************************
	public void infoObject(){		
		System.out.println("number of objects : "+objectMatcher.countMatches());
		for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
			System.out.println("sig : "+sig.getValueOfOB());
		}
	}
	
	public String addObject(String objectName){
		String script = "";		
		script = script + "\n" + "addComponent " + objectName + "@resources : AddressBook { }";
		nodes.add(objectName + "@resources");		
		return script;
	}
	
	public String addObjects(){
		String script = "";
		for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
			String objectName = ((Resource)sig.getValueOfOB()).getName();
			script = script + "\n" + "addComponent " + objectName + "@resources : AddressBook { }";
			nodes.add(objectName + "@resources");
		}
		return script;
	}
	
	//**************************Rule management *******************************************************
	public String infoUserRule(){
		String res = "number of rules : "+userRuleMatcher.countMatches();
		System.out.println("number of rules : "+userRuleMatcher.countMatches());
		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
			//System.out.println("sig : "+((User)sig.getValueOfUSER()).getName()+" : "+((Operation)sig.getValueOfOPERATION()).getName()+" : "+((Resource)sig.getValueOfOBJECT()));
			res = res + "\n"+((User)sig.getValueOfUSER()).getName()+" : "+((Operation)sig.getValueOfOPERATION()).getName()+" : "+((Resource)sig.getValueOfOBJECT());
		}
		return res;
	}
	
	public String addUserRule(String userName, String operationName, String objectName){
		String script = "";
		String channelName = "subject"+userName+operationName;
		portNumber = portNumber + 1;
		script = script + "\n" + "addChannel " + channelName + " : SocketChannel{name = \"" + channelName + "\"}";
		script = script + "\n" + "bind " + userName + "." + operationName + "@subjects" + "=>" + channelName;
		bindings.add("bind " + userName + "." + operationName + "@subjects"	+ "=>" + channelName);
		
		script = script + "\n" + "updateDictionary " + channelName	+ "{ port=\"" + portNumber + "\"}@subjects";
		portNumber = portNumber + 1;
		script = script + "\n" + "bind " + objectName + "."	+ operationName + "@resources" + " =>" + channelName;
		script = script + "\n" + "updateDictionary " + channelName + "{ port=\"" + portNumber + "\"}@resources";		
		return script;
	}
	
	
	public String addUserRules(){
		String script = "";
		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
			String userName = ((User)sig.getValueOfUSER()).getName();
			String objectName = ((Resource)sig.getValueOfOBJECT()).getName();
			String operationName = ((Operation)sig.getValueOfOPERATION()).getName();
			String channelName = "subject"+userName+operationName;
			
			portNumber = portNumber + 1;
			script = script + "\n" + "addChannel " + channelName
					+ " : SocketChannel{name = \"" + channelName + "\"}";
			script = script + "\n" + "bind " + userName + "." + operationName
					+ "@subjects" + "=>" + channelName;
			bindings.add("bind " + userName + "." + operationName + "@subjects"
					+ "=>" + channelName);
			script = script + "\n" + "updateDictionary " + channelName
					+ "{ port=\"" + portNumber + "\"}@subjects";
			
			
			portNumber = portNumber + 1;
			script = script + "\n" + "bind " + objectName + "."
					+ operationName + "@resources" + " =>" + channelName;
			script = script + "\n" + "updateDictionary " + channelName
					+ "{ port=\"" + portNumber + "\"}@resources";
		}
		return script;
	}
	
	//******************************************LAUNCHER**********************************************	
	public static void main(String[] args){
		Policy policy =RbacFactory.eINSTANCE.createPolicy();
//		PolicyGenerator policyGenerator = new PolicyGenerator(policy);				
//		policy = policyGenerator.initPolicyExamples(1, 4, false, true, false, false, false, false);
//		PolicyEditor policyEditor = new PolicyEditor(policy);
		IncrementalPolicy2KevScript transformator = new IncrementalPolicy2KevScript(policy);
		transformator.incrementalTransformation();
//		Chrono chrono = new Chrono();
//		policyEditor.addUser("BIBI");
		
//		System.out.println("\n");
//		User u = RbacFactory.eINSTANCE.createUser();
//		u.setName("bibi");
//		policy.getElements().add(u);		
//		System.out.println("ajouté bibi");
//		
//		System.out.println("\n");
//		User u2 = RbacFactory.eINSTANCE.createUser();
//		u2.setName("bobo");
//		policy.getElements().add(u2);
//		System.out.println("ajouté bobo");
//	
//		System.out.println("\n");		
//		policy.getElements().remove(u2);
//		System.out.println("retiré bobo");
		
//		System.out.println("\nusers");
//		transformator.infoSubject();
//		System.out.println("\nobjects");
//		transformator.infoObject();
//		System.out.println("\nrules");
//		transformator.infoUserRule();
//		chrono.start();
		//System.out.println(trans.transfo());
//		chrono.stop();
//		System.out.println("time relou method : "+chrono.displayTime());
	}	
}