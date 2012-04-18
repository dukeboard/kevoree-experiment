package rbacTextualEditor.editor.commands.commandsPolicy;

import javax.swing.Action;
import javax.swing.JOptionPane;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;
import rbacTools.checker.PolicyChecker;

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