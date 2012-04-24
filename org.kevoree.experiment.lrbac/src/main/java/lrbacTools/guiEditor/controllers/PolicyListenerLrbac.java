package lrbacTools.guiEditor.controllers;

import javax.swing.JOptionPane;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import patternbuilders.lrbac.*;
import patternmatchers.lrbac.*;
import signatures.lrbac.*;

import lrbac.*;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;
import lrbacTools.transformations.Policy2KevScript;

public class PolicyListenerLrbac {

	private Policy policy;
	private TextPaneEditor textPaneEditor;

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

	public PolicyListenerLrbac(TextPaneEditor editor, Policy p) {
		textPaneEditor = editor;
		policy = p;

		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserMatcher.FACTORY.getPatternName(),
				new patternbuilders.lrbac.PatternBuilderForuser());
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
					textPaneEditor.kse.append(script);
					Boolean scriptApplied = textPaneEditor.kse.atomicInterpretDeploy();
					System.out.println(scriptApplied+":"+script);
//					JOptionPane.showMessageDialog(textPaneEditor, scriptApplied +script,
//							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
				}
				for (UserSignature sig : monitorUser.matchLostEvents) {
					String script = policy2KevScript.removeSubject(((User) sig
							.getValueOfU()).getName());
//					JOptionPane.showMessageDialog(textPaneEditor, script,
//							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
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
					textPaneEditor.kse.append(script);
					Boolean scriptApplied = textPaneEditor.kse.atomicInterpretDeploy();
					System.out.println(scriptApplied+":"+script);
//					JOptionPane.showMessageDialog(textPaneEditor,scriptApplied+ script,
//							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
				}
				for (ObjectSignature sig : monitorObject.matchLostEvents) {
					String script = policy2KevScript
							.removeObject(((lrbac.Object) sig.getValueOfOB())
									.getName());
//					JOptionPane.showMessageDialog(textPaneEditor, script,
//							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
				}
				monitorUser.clear();
			}
		});

//		userOperationMatcher.addCallbackAfterUpdates(new Runnable() {
//			@Override
//			public void run() {
//				for (UserOperationSignature sig : monitorUserOperation.matchFoundEvents) {
//					String script = policy2KevScript.addUserOperation(
//							((User) sig.getValueOfUSER()).getName(),
//							(sig.getValueOfOPERATIONNAME()).toString());
//					textPaneEditor.kse.append(script);
//					Boolean scriptApplied = textPaneEditor.kse.atomicInterpretDeploy();
////					JOptionPane.showMessageDialog(textPaneEditor,scriptApplied + script,
////							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
//				}
//				for (UserOperationSignature sig : monitorUserOperation.matchLostEvents) {
//					String script = policy2KevScript.removeUserOperation(((User) sig
//							.getValueOfUSER()).getName(), ((Operation) sig
//							.getValueOfOPERATIONNAME()).getName());
////					JOptionPane.showMessageDialog(textPaneEditor, script,
////							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
//				}
//			}
//		});
//
//		userRuleMatcher.addCallbackAfterUpdates(new Runnable() {
//			@Override
//			public void run() {
//				for (UserRuleSignature sig : monitorUserRule.matchFoundEvents) {
//					String script = policy2KevScript.addUserRule(
//							((User) sig.getValueOfUSER()).getName(),
//							((Operation) sig.getValueOfOPERATION()).getName(),
//							((lrbac.Object) sig.getValueOfOBJECT()).getName());
//					textPaneEditor.kse.append(script);
//					Boolean scriptApplied = textPaneEditor.kse.atomicInterpretDeploy();
////					JOptionPane.showMessageDialog(textPaneEditor, scriptApplied+script,
////							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
//				}
//				for (UserRuleSignature sig : monitorUserRule.matchLostEvents) {
//					String script = policy2KevScript.removeUserRule(
//							((User) sig.getValueOfUSER()).getName(),
//							((Operation) sig.getValueOfOPERATION()).getName(),
//							((lrbac.Object) sig.getValueOfOBJECT()).getName());
////					JOptionPane.showMessageDialog(textPaneEditor, script,
////							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
//				}
//				monitorUser.clear();
//			}
//		});
		
		String script= policy2KevScript.addStaticArchitecturalElements();
		textPaneEditor.kse.append(script);
		Boolean scriptApplied = textPaneEditor.kse.atomicInterpretDeploy();
		System.out.println(scriptApplied+":"+script);
	}

}
