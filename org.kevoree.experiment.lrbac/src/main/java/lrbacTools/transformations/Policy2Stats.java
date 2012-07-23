package lrbacTools.transformations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.misc.DeltaMonitor;

import grapho.Edge;
import grapho.GraphElement;
import grapho.GraphO;
import grapho.GraphoFactory;
import grapho.Node;
import grapho.impl.EdgeImpl;
import grapho.impl.NodeImpl;
import incQueryPatterns.patternbuilders.lrbac.*;
import incQueryPatterns.patternmatchers.lrbac.*;
import incQueryPatterns.signatures.lrbac.*;
import lrbac.*;
import lrbac.Object;

public class Policy2Stats {
	private Policy policy;
	private UserMatcher userMatcher;
	private ObjectMatcher objectMatcher;
	private UserRuleMatcher userRuleMatcher;
	final DeltaMonitor<UserSignature> monitorUser;
	final DeltaMonitor<ObjectSignature> monitorObject;
	final DeltaMonitor<UserRuleSignature> monitorUserRule;
	private FileWriter fileWriter;
	private BufferedWriter bufferedWriter;
	private File file;

	public Policy2Stats(Policy p) {
		file = new File("policy.stat");
		policy = p;
		try {
			fileWriter = new FileWriter(file, true);
			bufferedWriter = new BufferedWriter(fileWriter, 16384);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserMatcher.FACTORY.getPatternName(),
				new PatternBuilderForuser());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				ObjectMatcher.FACTORY.getPatternName(),
				new PatternBuilderForobject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(
				UserRuleMatcher.FACTORY.getPatternName(),
				new PatternBuilderForuserRule());
		try {
			userMatcher = UserMatcher.FACTORY.getMatcher(policy);
			objectMatcher = ObjectMatcher.FACTORY.getMatcher(policy);
			userRuleMatcher = UserRuleMatcher.FACTORY.getMatcher(policy);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
		monitorUser = userMatcher.newDeltaMonitor(false);
		monitorObject = objectMatcher.newDeltaMonitor(false);
		monitorUserRule = userRuleMatcher.newDeltaMonitor(false);
	}

	public String transformation() {
		String script = "";
		script = script + addSubjects();
		script = script + addObjects();
		script = script + addUserRules();
		return script;
	}

	public GraphO transform2PolicyRuleGraph() {
		GraphoFactory factory = GraphoFactory.eINSTANCE;
		GraphO g = factory.createGraphO();
		for (UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()) {

			String subj = ((User) sig.getValueOfUSER()).getName();
			String cha = ((Operation) sig.getValueOfOPERATION()).getName();
			String obj = ((Object) sig.getValueOfOBJECT()).getName();

			addNodeByName(g, subj);
			addNodeByName(g, obj);
			addNodeByName(g, cha);
			addEdge(g, subj, cha);
			addEdge(g, cha, obj);
		}

		return g;
	}

	public String addSubjects() {
		String script = "";
		script = script + "number users : "
				+ userMatcher.getAllMatchesAsSignature().size() + "\n";
		return script;
	}

	// **************************Object
	// management****************************************************

	public String addObjects() {
		String script = "";
		script = script + "number objects : "
				+ objectMatcher.getAllMatchesAsSignature().size() + "\n";
		return script;
	}

	// **************************Rule management
	// *******************************************************

	public String addUserRules() {
		String script = "";
		script = script + "number rules : "
				+ userRuleMatcher.getAllMatchesAsSignature().size() + "\n";
		return script;
	}

	public int numberRules() {
		return userRuleMatcher.getAllMatchesAsSignature().size();
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
		if (getNodeByName(g, e) == null) {
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
		System.out.println("getEdgeByName : " + edgeName);
		for (GraphElement e : g.getElements()) {
			if (e instanceof EdgeImpl && e.getName().equals(edgeName)) {
				res = (Edge) e;
			}
		}
		return res;
	}

	public void addEdge(GraphO g, String n1, String n2) {
		if (getEdgeByName(g, n1 + n2) == null) {
			Edge ed = GraphoFactory.eINSTANCE.createEdge();
			ed.setName(n1 + n2);
			ed.setColor("black");
			ed.setStyle("solid");
			ed.setConstraintRank(true);
			ed.setNodeA(getNodeByName(g, n1));
			ed.setNodeB(getNodeByName(g, n2));
			g.getElements().add(ed);
		}
	}

}
