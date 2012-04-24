package lrbacTools.guiEditor.commands.commandsPolicy;

import javax.swing.Action;
import javax.swing.JOptionPane;

import lrbacTools.checker.PolicyChecker;
import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;


public class CommandCheck extends CommandRbac implements ICommandRbac{
	
	private TextPaneEditor textPaneEditor;
	
	
	public CommandCheck(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	
	public void execute(){
		PolicyChecker checker = new PolicyChecker(textPaneEditor.getPolicy());
		String res =checker.checkPolicy();
		JOptionPane.showMessageDialog(textPaneEditor, res, "Check", JOptionPane.INFORMATION_MESSAGE);
	}

}