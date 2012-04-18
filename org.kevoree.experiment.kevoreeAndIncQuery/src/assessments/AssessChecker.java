package assessments;

import cartesiancoordinatesystem.CartesianCoordinateSystem;
import cartesiancoordinatesystem.CartesiancoordinatesystemFactory;
import cartesiancoordinatesystem.Point;
import rbac.Policy;
import rbac.RbacFactory;
import rbacTextualEditor.editor.graphicComponents.CartesianCoordinateSystemView;
import rbacTools.checker.PolicyChecker;
import rbacTools.generator.PolicyGenerator;
import transformations.Policy2Stats;
import utils.time.Chrono;

public class AssessChecker {
	public static void main(String[] args){
		Policy p =RbacFactory.eINSTANCE.createPolicy(); 
		CartesiancoordinatesystemFactory factory = CartesiancoordinatesystemFactory.eINSTANCE;			
		CartesianCoordinateSystem sys = factory.createCartesianCoordinateSystem();
		sys.setName("checker evaluation");
		
		for (int i = 1;i<8;i++){
			PolicyGenerator pg = new PolicyGenerator(p);
			pg.generate(i, i, i, i, i, i, false, false, false, false, false, false);
			PolicyChecker pc = new PolicyChecker(p);
			Policy2Stats ps = new Policy2Stats(p);
			
			Chrono c = new Chrono();
			c.start();
			pc.checkPolicy();
			c.stop();
			
			Point point = factory.createPoint();
			point.setX(ps.numberRules());
			point.setY(c.timeMs());
			sys.getPoints().add(point);
			System.out.println(ps.numberRules()+":"+c.timeMs());
			
		}
		CartesianCoordinateSystemView v = new CartesianCoordinateSystemView(sys);
		v.setVisible(true);
	}
}
