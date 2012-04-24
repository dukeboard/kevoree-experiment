package graphoTools.editor;

import grapho.GraphO;
import grapho.GraphoFactory;
import grapho.Node;

public class GraphOEditor {

	private GraphO graphO;
	private GraphoFactory factory;
	
	public GraphOEditor(GraphO g){
		graphO = g;
		factory = GraphoFactory.eINSTANCE;
	}
	
	public void addNode(String name){
		Node n = factory.createNode();
		n.setName(name);
		graphO.getElements().add(n);
	}
	
}
