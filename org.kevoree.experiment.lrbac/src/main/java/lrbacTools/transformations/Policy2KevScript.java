package lrbacTools.transformations;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import incQueryPatterns.patternbuilders.lrbac.*;
import incQueryPatterns.patternmatchers.lrbac.*;
import incQueryPatterns.signatures.lrbac.*;
import utils.writer.FileWriterO;

import lrbac.*;
import lrbac.Object;

public class Policy2KevScript {

	//to discuss : deux solutions prinicpales pour terminer le mapping
	
	//premiere connecter toutes les operations de chaque subject avec leur 
	//channel correspondante et pour chaque regle ajouter et supprimer les bindings necessaires
	//bien mais requiert dintegrer des regles dans la channel ou bien un code vis a vis des objects
	
	// deuxieme solution avant d'ajouter la combi binding channel binding, checker sur le modele architectural 
	//si la channel existe deja auquel cas seul le dernier binding sera ajouté 
	
	
	// matchers
	private UserMatcher userMatcher;
	private ObjectMatcher objectMatcher;
	private UserOperationMatcher userOperationMatcher;
	private UserRuleMatcher userRuleMatcher;

	private Policy policy;
	private int portNumber;


	public Policy2KevScript(Policy p) {
		policy = p;
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserMatcher.FACTORY.getPatternName(),
				new PatternBuilderForuser());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				ObjectMatcher.FACTORY.getPatternName(),
				new PatternBuilderForobject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserOperationMatcher.FACTORY.getPatternName(),
				new PatternBuilderForuserOperation());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserRuleMatcher.FACTORY.getPatternName(),
				new PatternBuilderForuserRule());
		try {
			userMatcher = UserMatcher.FACTORY.getMatcher(policy);
			objectMatcher = ObjectMatcher.FACTORY.getMatcher(policy);
			userOperationMatcher = UserOperationMatcher.FACTORY
					.getMatcher(policy);
			userRuleMatcher = UserRuleMatcher.FACTORY.getMatcher(policy);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
		portNumber = 42000;
	}
	
	public String transformation() {
		String script = "";
		script = script + addStaticArchitecturalElements();
		script = script + addSubjects();
		script = script + addObjects();
		script = script + addUserRules();
		return script;
	}

	public File transformation(String path) {
		String script = "";
		script = script + addStaticArchitecturalElements();
		script = script + addSubjects();
		script = script + addObjects();
		script = script + addUserRules();

		FileWriterO fw = new FileWriterO();
		File f = fw.writeStringOnFile(script, path);

		return f;
	}

	public String addStaticArchitecturalElements() {
		String script = "";
		// ajout d'un node subjects
		script = script + "\n" + "addNode subjects : JavaSENode";
		script = script + "\n" + "addChild subjects@node0";
		script = script + "\n" + "addToGroup sync subjects";
		script = script + "\n" + "updateDictionary sync{ port=\"8101\"}@subjects";
		// ajout d'un node resources
		script = script + "\n" + "addNode resources : JavaSENode";
		script = script + "\n" + "addChild resources@node0";
		script = script + "\n" + "addToGroup sync resources";
		script = script + "\n"
				+ "updateDictionary sync{ port=\"8104\"}@resources";
		return script;
	}

	public String addSubject(String userName) {
		String script = "";
		script = script + "\n" + "addComponent " + userName
				+ "@subjects : AddressBookClient";
		return script;
	}
	
	public String removeSubject(String userName) {
		String script = "";
		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
			if ( ((User)sig.getValueOfUSER()).getName().equals(userName)){
				String objectName = ((Object) sig.getValueOfOBJECT()).getName();
				String operationName = ((Operation) sig.getValueOfOPERATION())
						.getName();						
				script = script + "\n"  + removeUserRule(userName, operationName, objectName);
			}
		}
		script = script + "\n" + "removeComponent " + userName	+ "@subjects";
		return script;
	}

	public String addSubjects() {
		String script = "";
		for (UserSignature  sig : userMatcher.getAllMatchesAsSignature()) {
			String userName = ((User) sig.getValueOfU()).getName();
			script = script + "\n" + "addComponent " + userName
					+ "@subjects : AddressBookClient";
		}
		return script;
	}

	public String removeObject(String objectName){
		String script = "";
		script = script + "\n" + "removeComponent " + objectName
				+ "@resources";
		return script;
	}
	
	public String addObject(String objectName) {
		String script = "";
		script = script + "\n" + "addComponent " + objectName
				+ "@resources : AddressBook { }";
		return script;
	}

	public String addObjects() {
		String script = "";
		for (ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()) {
			String objectName = ((Object) sig.getValueOfOB()).getName();
			script = script + "\n" + "addComponent " + objectName
					+ "@resources : AddressBook { }";
		}
		return script;
	}

	public String addUserRule(String userName, String operationName,
			String objectName) {
		String script = "";
		String channelName = "subject" + userName + operationName;
		portNumber = portNumber + 1;
	
		//ici necessaire de verifier si la channel existe deja
		//nop 
		
		
		script = script + "\n" + "addChannel " + channelName
				+ " : SocketChannel";
		script = script + "\n" + "bind " + userName + "." + operationName
				+ "@subjects" + "=>" + channelName;

		script = script + "\n" + "updateDictionary " + channelName
				+ "{ port=\"" + portNumber + "\"}@subjects";
		portNumber = portNumber + 1;
		script = script + "\n" + "bind " + objectName + "." + operationName
				+ "@resources" + " =>" + channelName;
		script = script + "\n" + "updateDictionary " + channelName
				+ "{ port=\"" + portNumber + "\"}@resources";
		return script;
	}
	
	public String removeUserRule(String userName, String operationName,
			String objectName) {
		String script = "";
		String channelName = "subject" + userName + operationName;

		script = script + "\n" + "unbind " + userName + "." + operationName
				+ "@subjects" + "=>" + channelName;
		
		script = script + "\n" + "unbind " + objectName + "." + operationName
				+ "@resources" + " =>" + channelName;
		
		script = script + "\n" + "removeChannel " + channelName;
		
		return script;
	}
	
	
	public String removeUserRuleChannel(String userName, String operationName,
			String objectName) {
		
		String script = "";
		String channelName = "subject" + userName + operationName;

		script = script + "\n" + "removeChannel " + channelName;
		
		return script;
	}
	
	public String removeUserRuleBindingSubjectOperationChannel(String userName, String operationName,
			String objectName) {
		String script = "";
		String channelName = "subject" + userName + operationName;

		script = script + "\n" + "unbind " + userName + "." + operationName
				+ "@subjects" + "=>" + channelName;
		return script;
	}
	
	public String removeUserRuleBindingChannelObjectOperation(String userName, String operationName,
			String objectName) {
		String script = "";
		String channelName = "subject" + userName + operationName;

		script = script + "\n" + "unbind " + objectName + "." + operationName
				+ "@resources" + " =>" + channelName;
		return script;
	}
	
	

	public String addUserRules() {
		String script = "";
		for (UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()) {
			
			
			
			String userName = ((User) sig.getValueOfUSER()).getName();
			String objectName = ((Object) sig.getValueOfOBJECT()).getName();
			String operationName = ((Operation) sig.getValueOfOPERATION())
					.getName();
			
			script = script + "\n"+addUserRule(userName, operationName, objectName);
		}
		return script;
	}

//	public String addUserOperations() {
//		String script = "";
//		for (UserOperationSignature sig : userOperationMatcher
//				.getAllMatchesAsSignature()) {
//			String userName = ((User) sig.getValueOfUSER()).getName();
//			String operationName = sig.getValueOfOPERATIONNAME().toString();
//			String channelName = "subject" + userName + operationName;
//			portNumber = portNumber + 1;
//			script = script + "\n" + "addChannel " + channelName
//					+ " : SocketChannel{name = \"" + channelName + "\"}";
//			script = script + "\n" + "bind " + userName + "." + operationName
//					+ "@subjects" + "=>" + channelName;
//			script = script + "\n" + "updateDictionary " + channelName
//					+ "{ port=\"" + portNumber + "\"}@subjects";
//		}
//		return script;
//	}
//
//	public String addUserOperation(String userName, String operationName) {
//		String channelName = "subject" + userName + operationName;
//		portNumber = portNumber + 1;
//		String script =  "\n" + "addChannel " + channelName
//				+ " : SocketChannel{name = \"" + channelName + "\"}";
//		script = script + "\n" + "bind " + userName + "." + operationName
//				+ "@subjects" + "=>" + channelName;
//		script = script + "\n" + "updateDictionary " + channelName
//				+ "{ port=\"" + portNumber + "\"}@subjects";
//		return script;
//	}
//	
//	public String removeUserOperation(String userName, String operationName) {
//		String channelName = "subject" + userName + operationName;
//		portNumber = portNumber + 1;
//		String script =  "\n" + "removeChannel " + channelName
//				+ " : SocketChannel{name = \"" + channelName + "\"}";
//		script = script + "\n" + "unbind " + userName + "." + operationName
//				+ "@subjects" + "=>" + channelName;
//		return script;
//	}
//	
//	public String addUserOperationsRule(String userName, String objectName,
//			String operationName){
//		
//		String channelName = "subject" + userName + operationName;
//		portNumber = portNumber + 1;
//		String script = "\n" + "bind " + objectName + "." + operationName
//				+ "@resources" + " =>" + channelName;
//		script = script + "\n" + "updateDictionary " + channelName
//				+ "{ port=\"" + portNumber + "\"}@resources";
//		return script;
//	}
//	
//	public String addUserOperationsRules() {
//		String script = "";
//		for (UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()) {
//			String userName = ((User) sig.getValueOfUSER()).getName();
//			String objectName = ((Object) sig.getValueOfOBJECT()).getName();
//			String operationName = ((Operation) sig.getValueOfOPERATION())
//					.getName();
//			String channelName = "subject" + userName + operationName;
//
//			portNumber = portNumber + 1;
//			script = script + "\n" + "bind " + objectName + "." + operationName
//					+ "@resources" + " =>" + channelName;
//			script = script + "\n" + "updateDictionary " + channelName
//					+ "{ port=\"" + portNumber + "\"}@resources";
//		}
//		return script;
//	}
}