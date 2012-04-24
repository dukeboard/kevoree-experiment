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

import patternbuilders.lrbac.*;
import patternmatchers.lrbac.*;
import signatures.lrbac.*;
import lrbac.*;


public class Policy2Stats {
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
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private OutputStreamWriter outputStreamWriter;
	private File file;
	
	
	public Policy2Stats(Policy p) {
		file =new File("graphs/test.test");
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
	
	//***************************GENERAL   TRANSFORMATION****************************
	//have to be applied to the first deployment of the system
		public String transformation(){
			String script ="";
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
					
//					System.out.println("SUBJECTS");
//					System.out.println("found events");
					for(UserSignature sig : monitorUser.matchFoundEvents){
						//System.out.println("sig : "+sig.getValueOfU());
					}
//					
//					System.out.println("lost events");
//					for(UserSignature sig : monitorUser.matchLostEvents){
//						System.out.println("sig : "+sig.getValueOfU());
//					}
//					System.out.println(addSubjects());
//					System.out.println("\n");
					monitorUser.clear();
				}
			});
			
			objectMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
//					System.out.println("found events");
//					System.out.println("OBJECTS");
//					for(ObjectSignature sig : monitorObject.matchFoundEvents){
//						System.out.println("sig : "+sig.getValueOfOB());
//					}
//					
//					System.out.println("lost events");
//					for(ObjectSignature sig : monitorObject.matchLostEvents){
//						System.out.println("sig : "+sig.getValueOfOB());
//					}
//					System.out.println(addObjects());
//					System.out.println("\n");
					monitorUser.clear();
				}
			});
			
			userRuleMatcher.addCallbackAfterUpdates(new Runnable() {
				@Override
				public void run() {
//					System.out.println("USER RULE");
//					System.out.println("found events");
					for(UserRuleSignature sig : monitorUserRule.matchFoundEvents){
						String script = "";
						script = script + "number rules : "+userRuleMatcher.getAllMatchesAsSignature().size()+"\n";
						try {
							bufferedWriter.write(script);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
//						System.out.println(script);
					}
					
//					System.out.println("lost events");
					for(UserRuleSignature sig : monitorUserRule.matchLostEvents){
						String script = "";
						script = script + "number rules : "+userRuleMatcher.getAllMatchesAsSignature().size()+"\n";
//						System.out.println(script);
						try {
							bufferedWriter.write(script);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
//					System.out.println(addUserRules());
//					System.out.println("\n");
					monitorUser.clear();
				}
			});		
		}
	
	//**************************Subject management************************************************

	
	public String addSubjects(){
		String script = "";
		script = script + "number users : "+userMatcher.getAllMatchesAsSignature().size()+"\n";
//		for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
//			script = script + "\n" + "User : " + ((User)sig.getValueOfU()).getName();	
//		}
		return script;
	}
	
	
	
	//**************************Object management****************************************************
	
	
	public String addObjects(){
		String script = "";
		script = script + "number objects : "+objectMatcher.getAllMatchesAsSignature().size()+"\n";
//		for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
//			script = script + "\n" + "Object : " + ((Resource)sig.getValueOfOB()).getName();	
//		}
		return script;
	}
	
	//**************************Rule management *******************************************************
	
	public String addUserRules(){
		String script = "";
		script = script + "number rules : "+userRuleMatcher.getAllMatchesAsSignature().size()+"\n";
//		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
//			String userName = ((User)sig.getValueOfUSER()).getName();
//			String objectName = ((Resource)sig.getValueOfOBJECT()).getName();
//			String operationName = ((Operation)sig.getValueOfOPERATION()).getName();
//			String channelName = "subject"+userName+operationName;
//			script = script + "\n" + "Rule : "+userName+":"+operationName+":"+objectName;
//		}
		return script;
	}
	
	public int numberRules(){
		return userRuleMatcher.getAllMatchesAsSignature().size();
	}
}
