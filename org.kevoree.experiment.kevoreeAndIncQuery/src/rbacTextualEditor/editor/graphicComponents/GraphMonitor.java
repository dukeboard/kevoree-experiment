package rbacTextualEditor.editor.graphicComponents;

import grapho.GraphO;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import rbac.Policy;
import transformations.GraphOpolicy2DotFile;
import transformations.Policy2Graph;
import utils.graphStructure.dotThings.DotDisplayer;

public class GraphMonitor extends JFrame{
	
	private JScrollPane scroll;
	private Policy policy;
	
	public GraphMonitor(Policy p){
		policy = p;
		Policy2Graph transfo2Graph = new Policy2Graph(policy);
		GraphO g = transfo2Graph.transformation();
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("graphs/ola.dot");
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
		Policy2Graph transfo2Graph = new Policy2Graph(policy);
		GraphO g = transfo2Graph.transformation();
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("graphs/ola.dot");
		DotDisplayer.createPngFile(f);
		        
        String pngPath = f.getAbsolutePath();
        pngPath = pngPath.substring(0,f.getAbsolutePath().length()-3);
        pngPath = pngPath+"png";
        File pngFile = new File(pngPath); 
        setContentPane(new JScrollPane(new ImageComponent(pngFile)));
        
	}
	

	
}
