package transformation;

import java.util.ArrayList;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import patternbuilders.policyInfo.PatternBuilderForobject;
import patternbuilders.policyInfo.PatternBuilderForuser;
import patternbuilders.policyInfo.PatternBuilderForuserRule;
import patternmatchers.policyInfo.ObjectMatcher;
import patternmatchers.policyInfo.UserMatcher;
import patternmatchers.policyInfo.UserRuleMatcher;

import rbac.*;
import rbac.rbac.generator.PolicyGenerator;
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
		userMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				System.out.println("found events");
				for(UserSignature sig : monitorUser.matchFoundEvents){
					System.out.println("sig : "+sig.getValueOfU());
				}
				
				System.out.println("lost events");
				for(UserSignature sig : monitorUser.matchLostEvents){
					System.out.println("sig : "+sig.getValueOfU());
				}
				
				System.out.println("all signatures");
				for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
					System.out.println("sig : "+sig.getValueOfU());
				}
				
				monitorUser.clear();
			}
		});
		
		
		
		
		
		
		
	}
	
	
	
	
	
	public void infoUser(){		
		System.out.println("number of users : "+userMatcher.countMatches());
		for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
			System.out.println("sig : "+sig.getValueOfU());
		}
	}
	
	public String addSubjects(){
		String script = "";
		// ajout d'un node subjects
		script = script + "\n" + "addNode subjects : JavaSENode";
		script = script + "\n" + "addChild subjects@node0";
		script = script + "\n" + "addToGroup sync subjects";
		script = script + "\n" + "updateDictionary sync{ port=\"8101\"}@subjects";
		for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
			String userName = ((User)sig.getValueOfU()).getName();
			script = script + "\n" + "addComponent " + userName + "@subjects : AddressBookClient";	
			nodes.add(userName + "@subjects");
		}
		return script;
	}
	
	public void infoObject(){		
		System.out.println("number of objects : "+objectMatcher.countMatches());
		for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
			System.out.println("sig : "+sig.getValueOfOB());
		}
	}
	
	public String addObjects(){
		String script = "";
		// ajout d'un node resources
		script = script + "\n" + "addNode resources : JavaSENode";
		script = script + "\n" + "addChild resources@node0";
		script = script + "\n" + "addToGroup sync resources";
		script = script + "\n" + "updateDictionary sync{ port=\"8104\"}@resources";
		for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
			String objectName = ((Resource)sig.getValueOfOB()).getName();
			script = script + "\n" + "addComponent " + objectName + "@resources : AddressBook { }";
			nodes.add(objectName + "@resources");
		}
		return script;
	}
	
	public void infoUserRule(){
		System.out.println("number of rules : "+userRuleMatcher.countMatches());
		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
			//System.out.println("sig : "+((User)sig.getValueOfUSER()).getName()+" : "+((Operation)sig.getValueOfOPERATION()).getName()+" : "+((Resource)sig.getValueOfOBJECT()));
		}
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
	
	public String transfo(){
		String script ="";
		script = script + addSubjects();
		script = script + addObjects();
		script = script + addUserRules();
		return script;
	}
	
	public static void main(String[] args){
		Policy policy =null;
		PolicyGenerator pg = new PolicyGenerator(policy);		
		policy = pg.initPolicyExamples(15, 4, false, true, false, false, false, false);
		IncrementalPolicy2KevScript trans = new IncrementalPolicy2KevScript(policy);
		Chrono c = new Chrono();
		
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
		
		System.out.println("\nusers");
		trans.infoUser();
		System.out.println("\nobjects");
		trans.infoObject();
		System.out.println("\nrules");
		trans.infoUserRule();
		c.start();
		System.out.println(trans.transfo());
		c.stop();
		System.out.println("time relou method : "+c.displayTime());
	}	
}