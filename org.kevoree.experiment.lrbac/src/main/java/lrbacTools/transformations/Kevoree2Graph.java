package lrbacTools.transformations;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;

import incQueryPatterns.patternbuilders.kevoree.PatternBuilderForauthorization;
import incQueryPatterns.patternbuilders.kevoree.PatternBuilderFornodeObject;
import incQueryPatterns.patternbuilders.kevoree.PatternBuilderFornodeSubject;
import incQueryPatterns.patternmatchers.kevoree.AuthorizationMatcher;
import incQueryPatterns.patternmatchers.kevoree.NodeObjectMatcher;
import incQueryPatterns.patternmatchers.kevoree.NodeSubjectMatcher;
import incQueryPatterns.signatures.kevoree.AuthorizationSignature;
import incQueryPatterns.signatures.kevoree.NodeObjectSignature;
import incQueryPatterns.signatures.kevoree.NodeSubjectSignature;
import grapho.Edge;
import grapho.GraphElement;
import grapho.GraphO;
import grapho.GraphoFactory;
import grapho.Node;
import grapho.impl.EdgeImpl;
import grapho.impl.NodeImpl;
import kevoree.Channel;
import kevoree.ComponentInstance;
import kevoree.ContainerNode;
import kevoree.ContainerRoot;
import lrbac.Object;
import lrbac.Operation;
import lrbac.Permission;
import lrbac.PolicyElement;
import lrbac.Role;
import lrbac.User;
import lrbac.impl.ObjectImpl;
import lrbac.impl.PermissionImpl;
import lrbac.impl.RoleImpl;
import lrbac.impl.UserImpl;

public class Kevoree2Graph {

	private ContainerRoot containerRoot;
	private AuthorizationMatcher authorizationMatcher;
	
	
	public Kevoree2Graph(ContainerRoot cr){
		containerRoot =cr; 
		BuilderRegistry.getContributedStatelessPatternBuilders().put(AuthorizationMatcher.FACTORY.getPatternName(),new PatternBuilderForauthorization());
		try {
			authorizationMatcher = AuthorizationMatcher.FACTORY.getMatcher(containerRoot);
		} catch (IncQueryRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public GraphO transformation(){
		GraphoFactory factory = GraphoFactory.eINSTANCE;
		GraphO g = factory.createGraphO();
		addAuthorizations(g);
		return g;
	}
	

	private void addAuthorizations(GraphO g) {
		for(AuthorizationSignature sig : authorizationMatcher.getAllMatchesAsSignature()){
			String subj = ((ComponentInstance)sig.getValueOfSUBJECT()).getName();
			String cha = ((Channel)sig.getValueOfCHANNEL()).getName();
			String obj = ((ComponentInstance)sig.getValueOfOBJECT()).getName();
			cha = cha.substring(cha.indexOf(subj)+subj.length(), cha.length());
			addNodeByName(g,subj);
			addNodeByName(g,obj);
			addNodeByName(g,cha);
			addEdge(g, subj, cha);
			addEdge(g, cha , obj);
			
		}
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
			n.setColor("black");
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
			ed.setColor("black");
			ed.setStyle("solid");
			ed.setConstraintRank(true);
			ed.setNodeA(getNodeByName(g, n1));
			ed.setNodeB(getNodeByName(g,n2));
			g.getElements().add(ed);
		}
	}
	
}