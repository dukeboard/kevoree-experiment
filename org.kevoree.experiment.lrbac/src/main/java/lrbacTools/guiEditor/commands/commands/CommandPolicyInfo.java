package lrbacTools.guiEditor.commands.commands;

import javax.swing.JOptionPane;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;
import lrbacTools.transformations.Policy2Stats;

public class CommandPolicyInfo extends Command{
	
	public CommandPolicyInfo(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	
	public void execute(){
		Policy2Stats transfo = new Policy2Stats(getEditor().getPolicy());
		String res = transfo.transformation();
		JOptionPane.showMessageDialog(getEditor().textPaneEditor, res, "PolicyInfo", JOptionPane.INFORMATION_MESSAGE);
	}
		
	}
