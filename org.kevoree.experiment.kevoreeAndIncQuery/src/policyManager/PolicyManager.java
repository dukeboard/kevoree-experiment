package policyManager;

import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import patternbuilders.policyInfo.PatternBuilderForobject;
import patternbuilders.policyInfo.PatternBuilderForuser;
import patternbuilders.policyInfo.PatternBuilderForuserRule;
import patternmatchers.policyInfo.ObjectMatcher;
import patternmatchers.policyInfo.UserMatcher;
import patternmatchers.policyInfo.UserRuleMatcher;

import rbac.Operation;
import rbac.Policy;
import rbac.RbacFactory;
import rbac.Resource;
import rbac.User;
import rbacTools.checker.PolicyChecker;
import rbacTools.editor.PolicyEditor;
import rbacTools.generator.PolicyGenerator;
import signatures.policyInfo.ObjectSignature;
import signatures.policyInfo.UserRuleSignature;
import signatures.policyInfo.UserSignature;
import transformations.IncrementalPolicy2KevScript;
import utils.time.Chrono;

public class PolicyManager {
	
	private Policy policy;
	private PolicyEditor policyEditor;
	private PolicyChecker policyChecker;
	private PolicyGenerator policyGenerator;
	private IncrementalPolicy2KevScript policyTransformator;
	private PolicyManagerGUI gui;
	private HashSet<String > actionsSet;
	private Chrono chrono;
	private UserMatcher userMatcher;
	private ObjectMatcher objectMatcher;
	private UserRuleMatcher userRuleMatcher;
	final DeltaMonitor<UserSignature> monitorUser;
	final DeltaMonitor<ObjectSignature> monitorObject;
	final DeltaMonitor<UserRuleSignature> monitorUserRule;
	private int numberUsers;
	
	public PolicyManager(){
		policy = RbacFactory.eINSTANCE.createPolicy();
		policyEditor = new PolicyEditor(policy);
		policyChecker = new PolicyChecker(policy);
		policyGenerator = new PolicyGenerator(policy);
		policyTransformator = new IncrementalPolicy2KevScript(policy);
		actionsSet = new HashSet<String>();
		actionsSet.add("check");
		actionsSet.add("generate");
		actionsSet.add("transform");
		actionsSet.add("display");	
		actionsSet.add("addUsers");
		gui = new PolicyManagerGUI(this);
		gui.setVisible(true);
		chrono = new Chrono();
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
		updateGUI();
		incrementalInfo();
		numberUsers = 0;
	}
	
	public void updateGUI(){
		gui.updateComboBox(set2Vector(policyEditor.getUsersStringSet()), gui.getComboBoxUser());
		gui.updateComboBox(set2Vector(policyEditor.getUsersStringSet()), gui.getComboBoxUser1());
		gui.updateComboBox(set2Vector(policyEditor.getRolesStringSet()), gui.getComboBoxRole());
		gui.updateComboBox(set2Vector(policyEditor.getRolesStringSet()), gui.getComboBoxRole1());
		gui.updateComboBox(set2Vector(policyEditor.getRolesStringSet()), gui.getComboBoxRole2());
		gui.updateComboBox(set2Vector(policyEditor.getRolesStringSet()), gui.getComboBoxRole3());
		gui.updateComboBox(set2Vector(policyEditor.getPermissionsStringSet()), gui.getComboBoxPerm());
		gui.updateComboBox(set2Vector(policyEditor.getPermissionsStringSet()), gui.getComboBoxPerm1());
		gui.updateComboBox(set2Vector(policyEditor.getPermissionsStringSet()), gui.getComboBoxPerm2());
		gui.updateComboBox(set2Vector(policyEditor.getPermissionsStringSet()), gui.getComboBoxPerm3());
		gui.updateComboBox(set2Vector(policyEditor.getPermissionsStringSet()), gui.getComboBoxPerm4());
		gui.updateComboBox(set2Vector(policyEditor.getPermissionsStringSet()), gui.getComboBoxPerm5());
		gui.updateComboBox(set2Vector(policyEditor.getResourcesStringSet()), gui.getComboBoxOb());
		gui.updateComboBox(set2Vector(policyEditor.getResourcesStringSet()), gui.getComboBoxOb1());		
		gui.updateComboBox(set2Vector(policyEditor.getPolicyElementTypes()), gui.getComboBoxTypElt());
		gui.updateComboBox(set2Vector(policyEditor.getPolicyElementTypes()), gui.getComboBoxTypElt1());
		gui.updateComboBox(set2Vector(policyEditor.getPermissionsOperationsStringSet()), gui.getComboBoxOpPerm());
		gui.updateComboBox(set2Vector(policyEditor.getPermissionsOperationsStringSet()), gui.getComboBoxOpPerm1());
		gui.updateComboBox(set2Vector(actionsSet), gui.getComboBoxAction());
	}
	
	public Vector<String> set2Vector(Set<String> hashSet){
		Vector<String> res = new Vector<String>();
		for(String s : hashSet){
			res.add(s);
		}
		return res;
	}
	
	
	public static void main(String[] args){
		PolicyManager policyManager = new PolicyManager();
		System.out.println("Policy Manager : "+policyManager.toString());		
	}

	public void addUsers(int number){
		for (int i=0;i<number;i++){
			policyEditor.addUser("U"+numberUsers);
			numberUsers = numberUsers + 1;
		}
	}

	public void transform(){
		gui.output("transform policy");
		chrono.start();
		policyTransformator.transformation();
		updateGUI();
		chrono.stop();
		gui.output(chrono.displayTime()+"\n");
	}
	
	public void check(){
		gui.output("check policy");
		chrono.start();
		policyChecker.checkPolicy();
		updateGUI();
		chrono.stop();
		gui.output(chrono.displayTime()+"\n");
	}

	public void generate(int numberUser,int numberRole,int numberPermission, int numberOperationByPermission, int numberObject,int numberSession){
		gui.output("generate policy");
		chrono.start();
		policyGenerator.generate(numberUser, numberRole, numberPermission, numberOperationByPermission, numberObject,numberSession, false, false, false, false, false, false);
		updateGUI();
		chrono.stop();
		gui.output(chrono.displayTime()+"\n");
	}

	public void display(){
		gui.output("display policy");
		chrono.start();
		gui.output(infoUserRule());
		updateGUI();
		chrono.stop();
		gui.output(chrono.displayTime()+"\n");
	}
	
	public void incrementalInfo(){
		userMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				chrono.start();
				gui.output("SUBJECTS");
				gui.output("found events : "+monitorUser.matchFoundEvents.size());
				for(UserSignature sig : monitorUser.matchFoundEvents){
					gui.output("sig : "+sig.getValueOfU());					
				}
				
				gui.output("lost events : "+monitorUser.matchLostEvents.size());
				for(UserSignature sig : monitorUser.matchLostEvents){
					gui.output("sig : "+sig.getValueOfU());
				}
				
//				gui.output("all signatures : "+userMatcher.getAllMatchesAsSignature().size());
//				for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
//					gui.output("sig : "+sig.getValueOfU());
//				}
				monitorUser.clear();
				chrono.stop();
				gui.output(chrono.displayTime()+"\n");
			}
		});
		
		objectMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				chrono.start();
				gui.output("OBJECTS");
				gui.output("found events : "+monitorObject.matchFoundEvents.size());
				for(ObjectSignature sig : monitorObject.matchFoundEvents){
					gui.output("sig : "+sig.getValueOfOB());
				}
				
				gui.output("lost events : "+monitorObject.matchLostEvents.size());
				for(ObjectSignature sig : monitorObject.matchLostEvents){
					gui.output("sig : "+sig.getValueOfOB());
				}
				
//				gui.output("all signatures : " + objectMatcher.getAllMatchesAsSignature().size());
//				for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
//					gui.output("sig : "+sig.getValueOfOB());
//				}
				monitorUser.clear();
				chrono.stop();
				gui.output(chrono.displayTime()+"\n");
			}
		});
		
		userRuleMatcher.addCallbackAfterUpdates(new Runnable() {
			@Override
			public void run() {
				chrono.start();
				gui.output("USER RULE");
				gui.output("found events : "+monitorUserRule.matchFoundEvents.size());
				for(UserRuleSignature sig : monitorUserRule.matchFoundEvents){
					gui.output("sig : "+sig.getValueOfUSER());
					
				}
				
				gui.output("lost events : "+monitorUserRule.matchLostEvents.size());
				for(UserRuleSignature sig : monitorUserRule.matchLostEvents){
					gui.output("sig : "+sig.getValueOfUSER());
				}
				
//				gui.output("all signatures : "+userRuleMatcher.getAllMatchesAsSignature().size());
//				for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
//					gui.output("sig : "+sig.getValueOfUSER());
//				}
				monitorUser.clear();
				chrono.stop();
				gui.output(chrono.displayTime()+"\n");
			}
		});		
	}
	
	public String infoUserRule(){
		String res = "number of rules : "+userRuleMatcher.countMatches();
		System.out.println("number of rules : "+userRuleMatcher.countMatches());
		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
			//System.out.println("sig : "+((User)sig.getValueOfUSER()).getName()+" : "+((Operation)sig.getValueOfOPERATION()).getName()+" : "+((Resource)sig.getValueOfOBJECT()));
			res = res + "\n"+((User)sig.getValueOfUSER()).getName()+" : "+((Operation)sig.getValueOfOPERATION()).getName()+" : "+((Resource)sig.getValueOfOBJECT());
		}
		return res;
	}
	
	
	/**
	 * @return the policy
	 */
	public Policy getPolicy() {
		return policy;
	}

	/**
	 * @return the policyEditor
	 */
	public PolicyEditor getPolicyEditor() {
		return policyEditor;
	}






	/**
	 * @return the policyChecker
	 */
	public PolicyChecker getPolicyChecker() {
		return policyChecker;
	}






	/**
	 * @return the policyGenerator
	 */
	public PolicyGenerator getPolicyGenerator() {
		return policyGenerator;
	}






	/**
	 * @return the policyTransformator
	 */
	public IncrementalPolicy2KevScript getPolicyTransformator() {
		return policyTransformator;
	}






	/**
	 * @return the gui
	 */
	public PolicyManagerGUI getGui() {
		return gui;
	}
	

}

