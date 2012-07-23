package lrbacTools.guiEditor.commands.commands;

import grapho.GraphO;

import javax.swing.JOptionPane;

import lrbacTools.comparisons.ComparaisonPolicyRulePolicyRuleEnforced;
import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.transformations.Kevoree2Graph;
import lrbacTools.transformations.Policy2Graph;
import lrbacTools.transformations.Policy2Stats;

public class CommandComparisonRule extends Command{
	
	
	public CommandComparisonRule(RbacTextualEditor e, String text) {
		super(e, text);
		// TODO Auto-generated constructor stub
	}
	
	public void execute(){
		
		Policy2Stats transfo2Graph = new Policy2Stats(getEditor().getPolicy());
		GraphO gRequirement = transfo2Graph.transform2PolicyRuleGraph();

		
		Kevoree2Graph transfo2GraphE = new Kevoree2Graph(getEditor().kevoreeLauncher.getKevoreeModel());
		GraphO gEnforced = transfo2GraphE.transformation();
						
		
		ComparaisonPolicyRulePolicyRuleEnforced comp = new ComparaisonPolicyRulePolicyRuleEnforced(gRequirement, gEnforced);
		String comparison = comp.compareText();
		JOptionPane.showMessageDialog(getEditor(), comparison);
	}

}
