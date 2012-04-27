package lrbacTools.guiEditor.commands.commands;

import javax.swing.JOptionPane;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.transformations.Kevoree2PolicyStats;
import lrbacTools.transformations.Policy2Stats;

public class CommandKevoree2PolicyStats extends Command{
	public CommandKevoree2PolicyStats(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	 
	public void execute(){
		Kevoree2PolicyStats transfo = new Kevoree2PolicyStats(getEditor().kevoreeLauncher.getKevoreeModel());
		String res = transfo.transformation();
		JOptionPane.showMessageDialog(getEditor().textPaneEditor, res, "kevoree2policyStats", JOptionPane.INFORMATION_MESSAGE);
	}
}
