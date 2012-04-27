package lrbacTools.guiEditor.graphicComponents;

import grapho.GraphO;

import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import lrbacTools.transformations.GraphOpolicy2DotFile;
import lrbacTools.transformations.Kevoree2Graph;
import utils.graphStructure.dotThings.DotDisplayer;

public class GraphMonitorEnforcedRule extends JFrame{
	
	private JScrollPane scroll;
	private RbacTextualEditor editor;
	
	public GraphMonitorEnforcedRule(RbacTextualEditor e){
		super();
		editor = e;
        setTitle("Graph Enforced Rule");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
         
        scroll = new JScrollPane();
        add(scroll);
        
	}
	
	public void update()
	{
		Kevoree2Graph transfo2Graph = new Kevoree2Graph(editor.kevoreeLauncher.getKevoreeModel());
		GraphO g = transfo2Graph.transformation();
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("enforcedRuleGraph.dot");
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