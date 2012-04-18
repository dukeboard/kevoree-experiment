package assessments;

import cartesiancoordinatesystem.AxeX;
import cartesiancoordinatesystem.AxeY;
import cartesiancoordinatesystem.CartesianCoordinateSystem;
import cartesiancoordinatesystem.CartesiancoordinatesystemFactory;
import rbac.Policy;
import rbac.RbacFactory;
import rbacTextualEditor.editor.graphicComponents.CartesianCoordinateSystemView;
import rbacTools.generator.PolicyGenerator;
import transformations.IncrementalPolicy2KevScript;
import transformations.Policy2Stats;
import utils.time.Chrono;

public class AssessIncInfo {
	
	
	public static void assessIncInfo(){
		Policy p =RbacFactory.eINSTANCE.createPolicy();
		CartesiancoordinatesystemFactory factory = CartesiancoordinatesystemFactory.eINSTANCE;
		CartesianCoordinateSystem cartesianCoordinateSystem = factory.createCartesianCoordinateSystem();
		cartesianCoordinateSystem.setName("Assessment Incremental Info");
		cartesianCoordinateSystem.setCoeffX(1);
		cartesianCoordinateSystem.setCoeffY(1);
		AxeX axeX = factory.createAxeX();
		axeX.setName("time ms");
		axeX.setColor("black");
		axeX.setLength(600);
		AxeY axeY = factory.createAxeY();
		axeY.setName("number of rules u:op:ob");
		axeY.setColor("black");
		axeY.setLength(600);
		cartesianCoordinateSystem.setAxeX(axeX);
		cartesianCoordinateSystem.setAxeY(axeY);
		
		Chrono c = new Chrono();
		PolicyGenerator pg = new PolicyGenerator(p);	
		pg.generate(1, 1, 1, 1, 1, 1, false, false, false, false, false, false);
		
		IncrementalPolicy2KevScript pc = new IncrementalPolicy2KevScript(p, cartesianCoordinateSystem);
		pc.incrementalTransformation("bob");
		
		c.start();						
		for (int i = 1;i<8;i++){			
			pg.incrementalAdd();
			axeY.setName("number of rules"+pc.numberRules());
		}
		c.stop();
		
		pc.showGraphic();
	}
	
	public static void main(String[] args){
		Policy p =RbacFactory.eINSTANCE.createPolicy();
		
		CartesiancoordinatesystemFactory factory = CartesiancoordinatesystemFactory.eINSTANCE;
		CartesianCoordinateSystem cartesianCoordinateSystem = factory.createCartesianCoordinateSystem();
		cartesianCoordinateSystem.setName("test");
		cartesianCoordinateSystem.setCoeffX(1);
		cartesianCoordinateSystem.setCoeffY(1);
		AxeX axeX = factory.createAxeX();
		axeX.setName("time ms");
		axeX.setColor("black");
		axeX.setLength(600);
		AxeY axeY = factory.createAxeY();
		axeY.setName("number of rules u:op:ob");
		axeY.setColor("black");
		axeY.setLength(600);
		cartesianCoordinateSystem.setAxeX(axeX);
		cartesianCoordinateSystem.setAxeY(axeY);
		
		Chrono c = new Chrono();
		PolicyGenerator pg = new PolicyGenerator(p);	
		pg.generate(1, 1, 1, 1, 1, 1, false, false, false, false, false, false);
		
		IncrementalPolicy2KevScript pc = new IncrementalPolicy2KevScript(p, cartesianCoordinateSystem);
		pc.incrementalTransformation("bob");
		
		c.start();						
		for (int i = 1;i<8;i++){			
			pg.incrementalAdd();
			axeY.setName("number of rules"+pc.numberRules());
		}
		c.stop();
		
		pc.showGraphic();
		
		System.out.println("number of rules : "+pc.numberRules()+" info time : "+c.displayTime());
	}
}