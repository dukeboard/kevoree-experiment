package lrbacTools.transformations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import incQueryPatterns.patternbuilders.lrbac.*;
import incQueryPatterns.patternmatchers.lrbac.*;
import incQueryPatterns.signatures.lrbac.*;
import lrbac.*;


public class Policy2Stats {
	private Policy policy;
	private UserMatcher userMatcher;
	private ObjectMatcher objectMatcher;
	private UserRuleMatcher userRuleMatcher;
	final DeltaMonitor<UserSignature> monitorUser;
	final DeltaMonitor<ObjectSignature> monitorObject;
	final DeltaMonitor<UserRuleSignature> monitorUserRule;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private File file;
	
	
	public Policy2Stats(Policy p) {
		file =new File("policy.stat");
		policy = p;
		try {
			fileWriter = new FileWriter(file,true);
			bufferedWriter = new BufferedWriter(fileWriter,16384);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
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
	
		public String transformation(){
			String script ="";
			script = script + addSubjects();
			script = script + addObjects();
			script = script + addUserRules();
			return script;
		}
		
		public void incrementalTransformation(){
			userMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
					
					for(UserSignature sig : monitorUser.matchFoundEvents){
					}
					monitorUser.clear();
				}
			});
			
			objectMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
					monitorUser.clear();
				}
			});
			
			userRuleMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
					for(UserRuleSignature sig : monitorUserRule.matchFoundEvents){
						String script = "";
						script = script + "number rules : "+userRuleMatcher.getAllMatchesAsSignature().size()+"\n";
						try {
							bufferedWriter.write(script);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
					for(UserRuleSignature sig : monitorUserRule.matchLostEvents){
						String script = "";
						script = script + "number rules : "+userRuleMatcher.getAllMatchesAsSignature().size()+"\n";
						try {
							bufferedWriter.write(script);
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
					monitorUser.clear();
				}
			});		
		}
		
	public String addSubjects(){
		String script = "";
		script = script + "number users : "+userMatcher.getAllMatchesAsSignature().size()+"\n";
		return script;
	}
	
	
	
	//**************************Object management****************************************************
	
	
	public String addObjects(){
		String script = "";
		script = script + "number objects : "+objectMatcher.getAllMatchesAsSignature().size()+"\n";
		return script;
	}
	
	//**************************Rule management *******************************************************
	
	public String addUserRules(){
		String script = "";
		script = script + "number rules : "+userRuleMatcher.getAllMatchesAsSignature().size()+"\n";
		return script;
	}
	
	public int numberRules(){
		return userRuleMatcher.getAllMatchesAsSignature().size();
	}
}
