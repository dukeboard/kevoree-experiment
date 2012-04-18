package assessments;

import rbac.Policy;
import rbac.RbacFactory;
import rbacTools.generator.PolicyGenerator;
import transformations.IncrementalPolicy2KevScript;
import utils.time.Chrono;

public class AssessTransfo {
	public static void main(String[] args){
		Policy p =RbacFactory.eINSTANCE.createPolicy(); 
		for (int i = 1;i<11;i++){
			PolicyGenerator pg = new PolicyGenerator(p);
			pg.generate(i, i, i, i, i, i, false, false, false, false, false, false);
			IncrementalPolicy2KevScript pc = new IncrementalPolicy2KevScript(p);
			Chrono c = new Chrono();
			c.start();			
			pc.transformation();
			c.stop();
			System.out.println(i+" check time : "+c.displayTime());
		}
		
	}
}
