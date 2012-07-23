package lrbacTools.guiEditor.controllers;

import javax.swing.JOptionPane;


import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import incQueryPatterns.patternbuilders.lrbac.*;
import incQueryPatterns.patternmatchers.lrbac.*;
import incQueryPatterns.signatures.lrbac.*;

import lrbac.*;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;
import lrbacTools.guiEditor.Launcher;
import lrbacTools.transformations.Policy2KevScript;

import org.kevoree.api.service.core.script.KevScriptEngine;

public class PolicyListenerLrbac {

	private Policy policy;
	private RbacTextualEditor editor;

	// matchers
	private UserMatcher userMatcher;
	private ObjectMatcher objectMatcher;
	private UserOperationMatcher userOperationMatcher;
	private UserRuleMatcher userRuleMatcher;

	// monitors
	final DeltaMonitor<UserSignature> monitorUser;
	final DeltaMonitor<ObjectSignature> monitorObject;
	final DeltaMonitor<UserOperationSignature> monitorUserOperation;
	final DeltaMonitor<UserRuleSignature> monitorUserRule;

	// transformator
	private Policy2KevScript policy2KevScript;

	private int portNumber;

	public PolicyListenerLrbac(RbacTextualEditor ed) {
		editor = ed;
		policy = editor.getPolicy();

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

		policy2KevScript = new Policy2KevScript(policy);
		portNumber = 42000;

		monitorUser = userMatcher.newDeltaMonitor(false);
		monitorObject = objectMatcher.newDeltaMonitor(false);
		monitorUserOperation = userOperationMatcher.newDeltaMonitor(false);
		monitorUserRule = userRuleMatcher.newDeltaMonitor(false);

	}

	public void startMonitor() {
		userMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				for (UserSignature sig : monitorUser.matchFoundEvents) {
					String script = policy2KevScript.addSubject(((User) sig
							.getValueOfU()).getName());
					applyScript(script);
				}
				
				for (UserSignature sig : monitorUser.matchLostEvents) {
					System.out.println("lost event");
					String script = policy2KevScript.removeSubject(((User) sig
							.getValueOfU()).getName());
					JOptionPane.showMessageDialog(null, script);
					applyScript(script);
				}
				
				monitorUser.clear();
			}
		});

		objectMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				for (ObjectSignature sig : monitorObject.matchFoundEvents) {
					String script = policy2KevScript
							.addObject(((lrbac.Object) sig.getValueOfOB())
									.getName());
					applyScript(script);
				}
				for (ObjectSignature sig : monitorObject.matchLostEvents) {
					String script = policy2KevScript
							.removeObject(((lrbac.Object) sig.getValueOfOB())
									.getName());
					applyScript(script);
				}
				monitorObject.clear();
			}
		});

		userRuleMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				for (UserRuleSignature sig : monitorUserRule.matchFoundEvents) {
					String script = policy2KevScript.addUserRule(
							((User) sig.getValueOfUSER()).getName(),
							((Operation) sig.getValueOfOPERATION()).getName(),
							((lrbac.Object) sig.getValueOfOBJECT()).getName());
					applyScript(script);
				}
				for (UserRuleSignature sig : monitorUserRule.matchLostEvents) {
					String script = removeRule(
							((User) sig.getValueOfUSER()).getName(),
							((Operation) sig.getValueOfOPERATION()).getName(),
							((lrbac.Object) sig.getValueOfOBJECT()).getName());
					applyScript(script);
				}
				monitorUserRule.clear();
			}
		});
		String script= policy2KevScript.addStaticArchitecturalElements();
		applyScript(script);
	}
	
	
	
	//finir remove Rule
	public String removeRule(String userName,String operationName,String objectName){
		String res = "";
		//boolean haveToSuppressChannel = false;
		int haveToSuppressChannel = 0;
		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
			String user = ((User) sig.getValueOfUSER()).getName();
			String operation = ((Operation) sig.getValueOfOPERATION()).getName();
			if(user.equals(userName)&&operation.equals(operationName)){
				res = res + "\n"+ policy2KevScript.removeUserRuleBindingChannelObjectOperation(userName, operationName, objectName);
				return res;
			}
		}
		res =res +"\n"+policy2KevScript.removeUserRule(userName, operationName, objectName);
		return res;
	}
	
	public void applyScript(String s){
		editor.update();
		Boolean scriptApplied = true; 
		if (! (editor.kevoreeLauncher == null)){
			KevScriptEngine kse = editor.kevoreeLauncher.getKevScriptEngineFactory().createKevScriptEngine();
			kse.append(s);
			System.out.println("script : "+s+" on : "+kse);
			scriptApplied = kse.atomicInterpretDeploy();
		}
		JOptionPane.showMessageDialog(editor, scriptApplied+":"+s,
				"IncTransf", JOptionPane.INFORMATION_MESSAGE);
	}
	
}
