package policyTools.transformations;

import java.net.URISyntaxException;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;

import patternbuilders.policy.PatternBuilderForobject;
import patternbuilders.policy.PatternBuilderForoperation;
import patternbuilders.policy.PatternBuilderForuserRule;
import patternmatchers.policy.ObjectMatcher;
import patternmatchers.policy.OperationMatcher;
import patternmatchers.policy.UserMatcher;
import patternmatchers.policy.UserRuleMatcher;
import policy.Operation;
import policy.Policy;
import policy.User;
import policy.Object;
import signatures.policy.ObjectSignature;
import signatures.policy.OperationSignature;
import signatures.policy.UserRuleSignature;
import signatures.policy.UserSignature;
import xacml.filesGenerator.PolicyGenerator;


public class Policy2Xacml {
	private Policy policy;
	private PolicyGenerator policyGenerator;
	
	private UserMatcher userMatcher;
	private ObjectMatcher objectMatcher;
	private UserRuleMatcher userRuleMatcher;
	private OperationMatcher operationMatcher;
	
	public Policy2Xacml(Policy x) {
		policy = x;
		policyGenerator = new PolicyGenerator();
		

		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserMatcher.FACTORY.getPatternName(),
				new patternbuilders.policy.PatternBuilderForuser());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				ObjectMatcher.FACTORY.getPatternName(),
				new PatternBuilderForobject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserRuleMatcher.FACTORY.getPatternName(),
				new PatternBuilderForuserRule());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				OperationMatcher.FACTORY.getPatternName(),
				new PatternBuilderForoperation());
		try {
			userMatcher = UserMatcher.FACTORY.getMatcher(policy);
			objectMatcher = ObjectMatcher.FACTORY.getMatcher(policy);
			userRuleMatcher = UserRuleMatcher.FACTORY.getMatcher(policy);
			operationMatcher = OperationMatcher.FACTORY.getMatcher(policy);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
	}
	
	public void transformation() {
		policyGenerator = new PolicyGenerator();
		addUsers();
		addOperations();
		addObjects();
		addRules();
		try {
			policyGenerator.createPolicy();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addUsers(){
		for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
			User u = (User) sig.getValueOfU();
			policyGenerator.policyTargetGenerator.addListTargetsSubject(u.getName());
		}
	}
	
	public void addObjects(){
		for(OperationSignature sig : operationMatcher.getAllMatchesAsSignature()){
			Operation op = (Operation) sig.getValueOfOP();
			policyGenerator.policyTargetGenerator.addListTargetsAction(op.getName());
		}
	}
	
	public void addOperations(){
		for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
			Object ob = (Object)sig.getValueOfOB();
			policyGenerator.policyTargetGenerator.addListTargetsResource(ob.getName());
		}
	}
	
	public void addRules(){
		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
			User u = (User) sig.getValueOfUSER();
			Operation op = (Operation) sig.getValueOfOPERATION();
			Object ob = (Object)sig.getValueOfOBJECT();
			policyGenerator.policyRuleGenerator.addRule(u.getName(), op.getName(), ob.getName());
		}
	}
	
}
