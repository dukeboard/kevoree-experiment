package lrbacTools.comparisons;

import grapho.Edge;
import grapho.GraphElement;
import grapho.GraphO;
import grapho.GraphoFactory;
import grapho.Node;
import grapho.impl.EdgeImpl;
import grapho.impl.NodeImpl;

public class ComparaisonPolicyRulePolicyRuleEnforced {
	
	private GraphO policyRules;
	private GraphO policyRulesEnforced;
	
	
	public ComparaisonPolicyRulePolicyRuleEnforced(GraphO policyReq, GraphO policyEnf){
		policyRules = policyReq;
		policyRulesEnforced = policyEnf;
	}
	
	public GraphO compare(){
		
		for (GraphElement e : policyRules.getElements()){
			if(e instanceof NodeImpl){
				if (getNodeByName(policyRulesEnforced, e.getName()) == null)
				{
					addNodeByName(policyRulesEnforced, e.getName());
				}
			}
			if(e instanceof EdgeImpl){
				if (getEdgeByName(policyRulesEnforced, e.getName()) == null)
				{
					Edge ed = (Edge)e; 
					addNodeByName(policyRulesEnforced, ed.getNodeA().getName());
					addNodeByName(policyRulesEnforced, ed.getNodeB().getName());
					addEdge(policyRulesEnforced, ed.getNodeA().getName(), ed.getNodeB().getName());
				}
			}
		}
		
		return policyRulesEnforced;
	}
	
	public String compareText(){
		String res = "";
		res = res +"\n"+ "policy rules";
		res = res +"\n"+ "number elements : "+policyRules.getElements().size();
		
		String nodes ="";
		String edges ="";
		for (GraphElement e : policyRules.getElements()){
			if(e instanceof NodeImpl){
				nodes = nodes +"\n"+e.getName();
			}
			if(e instanceof EdgeImpl){
				edges = edges +"\n"+e.getName();
			}
		}
		res = res + "\nnodes : \n" + nodes;
		res = res + "\nedges : \n" + edges;
		
		
		
	
		res = res +"\n"+ "policy rules enforced ";
		res = res +"\n"+ "number elements : "+policyRulesEnforced.getElements().size();
		
		nodes ="";
		edges ="";
		for (GraphElement e : policyRulesEnforced.getElements()){
			if(e instanceof NodeImpl){
				nodes = nodes +"\n"+e.getName();
			}
			if(e instanceof EdgeImpl){
				edges = edges +"\n"+e.getName();
			}
		}
		res = res + "\nnodes : \n" + nodes;
		res = res + "\nedges : \n" + edges;
		
		
		return res;
	}
	
	
	public Node getNodeByName(GraphO g, String nodeName) {
		Node res = null;
		for (GraphElement e : g.getElements()) {
			if (e instanceof NodeImpl && e.getName().equals(nodeName)) {
				res = (Node) e;
			}
		}
		return res;
	}
	
	public void addNodeByName(GraphO g, String e) {
		if (getNodeByName(g, e) == null){
			Node n = GraphoFactory.eINSTANCE.createNode();
			n.setColor("red");
			n.setLabel(e);
			n.setName(e);
			n.setShape("ellipse");
			n.setStyle("solid");		
			g.getElements().add(n);
		}
	}
	
	public Edge getEdgeByName(GraphO g, String edgeName) {
		Edge res = null;
		System.out.println("getEdgeByName : "+edgeName);
		for (GraphElement e : g.getElements()) {
			if (e instanceof EdgeImpl && e.getName().equals(edgeName)) {
				res = (Edge) e;
			}
		}
		return res;
	}
	
	public void addEdge(GraphO g,String n1,String n2){
		if (getEdgeByName(g, n1+n2) == null){
			Edge ed = GraphoFactory.eINSTANCE.createEdge();
			ed.setName(n1+n2);
			ed.setColor("red");
			ed.setStyle("solid");
			ed.setConstraintRank(true);
			ed.setNodeA(getNodeByName(g, n1));
			ed.setNodeB(getNodeByName(g,n2));
			g.getElements().add(ed);
		}
	}
	

}
