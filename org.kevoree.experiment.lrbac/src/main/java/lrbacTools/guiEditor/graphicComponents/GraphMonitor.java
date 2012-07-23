package lrbacTools.guiEditor.graphicComponents;

import grapho.GraphO;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import lrbac.Policy;
import lrbacTools.transformations.GraphOpolicy2DotFile;
import lrbacTools.transformations.Policy2Graph;
import utils.graphStructure.dotThings.DotDisplayer;

public class GraphMonitor extends JFrame{
	
	private JScrollPane scroll;
	private RbacTextualEditor editor;
	
	public GraphMonitor(RbacTextualEditor e){
		super();
		editor = e;
		Policy2Graph transfo2Graph = new Policy2Graph(editor.getPolicy());
		GraphO g = transfo2Graph.transformation();
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("policyGraph.dot");
		DotDisplayer.createPngFile(f);
		        
        String pngPath = f.getAbsolutePath();
        pngPath = pngPath.substring(0,f.getAbsolutePath().length()-3);
        pngPath = pngPath+"png";
        
        File pngFile = new File(pngPath); 
        scroll = new JScrollPane(new ImageComponent(pngFile));
        add(scroll);
        
        setTitle("Graph Monitor");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(400, 400);
	}
	
	public void update()
	{
		Policy2Graph transfo2Graph = new Policy2Graph(editor.getPolicy());
		GraphO g = transfo2Graph.transformation();
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