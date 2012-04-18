package assessments;

import cartesiancoordinatesystem.CartesianCoordinateSystem;
import cartesiancoordinatesystem.CartesiancoordinatesystemFactory;
import cartesiancoordinatesystem.Point;
import rbac.Policy;
import rbac.RbacFactory;
import rbacTextualEditor.editor.graphicComponents.CartesianCoordinateSystemView;
import rbacTools.generator.PolicyGenerator;
import transformations.Policy2Stats;
import utils.time.Chrono;

public class AssessGen {
	public static void main(String[] args){
		CartesiancoordinatesystemFactory factory = CartesiancoordinatesystemFactory.eINSTANCE;			
		CartesianCoordinateSystem sys = factory.createCartesianCoordinateSystem();
		sys.setName("generator evaluation");
		for (int i = 1;i<15;i++){
			Policy p =RbacFactory.eINSTANCE.createPolicy();		
			Chrono c = new Chrono();			
			PolicyGenerator pg = new PolicyGenerator(p);
			
			c.start();						
			pg.generate(i, i, i, i, i, i, false, false, false, false, false, false);
			c.stop();
			Policy2Stats ps = new Policy2Stats(p);

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
