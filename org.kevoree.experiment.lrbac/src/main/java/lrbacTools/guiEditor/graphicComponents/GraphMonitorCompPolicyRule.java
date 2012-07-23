package lrbacTools.guiEditor.graphicComponents;

import grapho.GraphO;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import lrbacTools.comparisons.ComparaisonPolicyRulePolicyRuleEnforced;
import lrbacTools.transformations.GraphOpolicy2DotFile;
import lrbacTools.transformations.Kevoree2Graph;
import lrbacTools.transformations.Policy2Stats;
import utils.graphStructure.dotThings.DotDisplayer;

public class GraphMonitorCompPolicyRule extends JFrame{
	
	private JScrollPane scroll;
	private RbacTextualEditor editor;
	
	public GraphMonitorCompPolicyRule(RbacTextualEditor e){
		super();
		editor = e;
        setTitle("Graph Comp Enforced Rule");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
         
        scroll = new JScrollPane();
        add(scroll);
	}
	
	public void update()
	{
		Policy2Stats transfo2Graph = new Policy2Stats(editor.getPolicy());
		GraphO gRequirement = transfo2Graph.transform2PolicyRuleGraph();

		
		Kevoree2Graph transfo2GraphE = new Kevoree2Graph(editor.kevoreeLauncher.getKevoreeModel());
		GraphO gEnforced = transfo2GraphE.transformation();
						
		
		ComparaisonPolicyRulePolicyRuleEnforced comp = new ComparaisonPolicyRulePolicyRuleEnforced(gRequirement, gEnforced);
		GraphO g = comp.compare();
		
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("compRuleGraph.dot");
		//DotDisplayer.createPngFile(f);
		DotDisplayer.createPdfFile(f);
		        
        String pngPath = f.getAbsolutePath();
        pngPath = pngPath.substring(0,f.getAbsolutePath().length()-3);
        pngPath = pngPath+"png";
        File pngFile = new File(pngPath); 
        setContentPane(new JScrollPane(new ImageComponent(pngFile)));
        if(isVisible()){
        	setVisible(true);
        }
	}
}
	