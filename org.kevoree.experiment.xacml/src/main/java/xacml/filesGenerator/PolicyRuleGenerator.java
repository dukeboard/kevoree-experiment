package xacml.filesGenerator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import com.sun.xacml.Rule;

public class PolicyRuleGenerator {
	
	public ArrayList<Rule> rules;
	
	public PolicyRuleGenerator(){
		rules = new ArrayList<Rule>();
	}

	public void addRule(String subjectName,String resourceName,String actionName){
		int effect = 0; //0 to permit or 1 to deny
		String description = subjectName+actionName+resourceName;
		PolicyTargetGenerator policyTargetGenerator = new PolicyTargetGenerator();
		policyTargetGenerator.addListTargetsSubject(subjectName);
		policyTargetGenerator.addListTargetsResource(resourceName);
		policyTargetGenerator.addListTargetsAction(actionName);
		Rule rule= null;
		try {
			rule = new Rule(new URI(description), effect, description, policyTargetGenerator.createTarget(), null);
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rules.add(rule);
	}
}