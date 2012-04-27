package lrbacTools.guiEditor.commands.commandsPolicy;

import javax.swing.Action;
import javax.swing.JOptionPane;


import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;
import lrbacTools.transformations.Policy2Stats;

public class CommandPolicyInfo extends CommandRbac implements ICommandRbac{
	
	private TextPaneEditor textPaneEditor;
	
	
	public CommandPolicyInfo(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	
	public void execute(){
		Policy2Stats transfo = new Policy2Stats(textPaneEditor.getPolicy());
		String res = transfo.transformation();
		JOptionPane.showMessageDialog(textPaneEditor, res, "PolicyInfo", JOptionPane.INFORMATION_MESSAGE);
	}
		
	}
