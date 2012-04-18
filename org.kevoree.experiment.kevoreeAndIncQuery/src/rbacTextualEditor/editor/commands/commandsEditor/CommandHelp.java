package rbacTextualEditor.editor.commands.commandsEditor;

import javax.swing.JOptionPane;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;

public class CommandHelp  extends CommandRbac implements ICommandRbac{
	private TextPaneEditor textPaneEditor;
	
	public CommandHelp(TextPaneEditor editor,String id){
		textPaneEditor=editor;
		setName(id);
		setDescription(id);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(textPaneEditor, "This editor aims to manage Rbac policies at runtime", "Help", JOptionPane.INFORMATION_MESSAGE);
	}

}
