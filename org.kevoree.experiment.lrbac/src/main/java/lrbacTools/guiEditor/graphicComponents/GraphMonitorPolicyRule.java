package lrbacTools.guiEditor.graphicComponents;

import grapho.GraphO;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import lrbacTools.transformations.GraphOpolicy2DotFile;
import lrbacTools.transformations.Policy2Graph;
import lrbacTools.transformations.Policy2Stats;
import utils.graphStructure.dotThings.DotDisplayer;

public class GraphMonitorPolicyRule  extends JFrame{
	
	private JScrollPane scroll;
	private RbacTextualEditor editor;
	
	public GraphMonitorPolicyRule(RbacTextualEditor e){
		super();
		editor = e;
		Policy2Graph transfo2Graph = new Policy2Graph(editor.getPolicy());
		GraphO g = transfo2Graph.transformation();
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("policyRuleGraph.dot");
		DotDisplayer.createPngFile(f);
		
        scroll = new JScrollPane();
        add(scroll);
        
        setTitle("Graph Monitor Policy Rule");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
	}
	
	public void update()
	{
		Policy2Stats transfo2Graph = new Policy2Stats(editor.getPolicy());
		GraphO g = transfo2Graph.transform2PolicyRuleGraph();
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("policyGraph.dot");
		DotDisplayer.createPngFile(f);
		        
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