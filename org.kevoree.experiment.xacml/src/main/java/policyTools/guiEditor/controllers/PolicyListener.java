package policyTools.guiEditor.controllers;

import javax.swing.JOptionPane;

import policy.*;

import policyTools.guiEditor.graphicComponents.TextPaneEditor;
import policyTools.guiEditor.graphicComponents.*;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import patternbuilders.policy.*;

import patternmatchers.policy.*;

import signatures.policy.*;

public class PolicyListener {

	private PolicyTextualEditor editor;

	private Policy policy; 

	// matchers
	private UserMatcher userMatcher;
	private ObjectMatcher objectMatcher;
	private UserRuleMatcher userRuleMatcher;

	// monitors
	final DeltaMonitor<UserSignature> monitorUser;
	final DeltaMonitor<ObjectSignature> monitorObject;
	final DeltaMonitor<UserRuleSignature> monitorUserRule;


	private int portNumber;
	
	public PolicyListener(PolicyTextualEditor edit){
		editor = edit;
		policy = editor.getPolicy();
		

		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserMatcher.FACTORY.getPatternName(),
				new patternbuilders.policy.PatternBuilderForuser());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				ObjectMatcher.FACTORY.getPatternName(),
				new PatternBuilderForobject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserRuleMatcher.FACTORY.getPatternName(),
				new PatternBuilderForuserRule());
		try {
			userMatcher = UserMatcher.FACTORY.getMatcher(policy);
			objectMatcher = ObjectMatcher.FACTORY.getMatcher(policy);
			userRuleMatcher = UserRuleMatcher.FACTORY.getMatcher(policy);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
	
		
		portNumber = 42000;

		monitorUser = userMatcher.newDeltaMonitor(false);
		monitorObject = objectMatcher.newDeltaMonitor(false);
		monitorUserRule = userRuleMatcher.newDeltaMonitor(false);
	}
	public void listen(){
		
		userMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				
				for (UserSignature sig : monitorUser.matchFoundEvents) {
					String script = sig.prettyPrint();
					System.out.println(script);
					JOptionPane.showMessageDialog(editor, script,
							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
					
				
				}				
				
				for (UserSignature sig : monitorUser.matchLostEvents) {
					String script = sig.prettyPrint();
					System.out.println(script);
					JOptionPane.showMessageDialog(editor, script,
							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
				
				
				}	
				
				monitorUser.clear();
			}
		});

		objectMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
			
				//vers topology
				for (ObjectSignature sig : monitorObject.matchFoundEvents) {
					String script = sig.prettyPrint();
					System.out.println(script);
					JOptionPane.showMessageDialog(editor, script,
							"IncTransf", JOptionPane.INFORMATION_MESSAGE);
			
				}
				
				monitorObject.clear();
			}
		});

		userRuleMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				for (UserRuleSignature sig : monitorUserRule.matchFoundEvents) {
					String script = sig.prettyPrint();
							System.out.println(script);
					JOptionPane.showMessageDialog(editor, script,
							"IncTransf", JOptionPane.INFORMATION_MESSAGE);

				}
				
				monitorUserRule.clear();
			}
		});
	}

}
