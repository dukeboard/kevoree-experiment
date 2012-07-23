package rbacTextualEditor.editor.commands.commandsEditor;

import javax.swing.JOptionPane;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;

public class CommandVersion extends CommandRbac implements ICommandRbac{
	
	private TextPaneEditor textPaneEditor;
	
	public CommandVersion(TextPaneEditor editor, String id){
		setName(id);
		setDescription(id);
		textPaneEditor=editor;
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(textPaneEditor, "Version 0.01 16/04/2012", "Version", JOptionPane.INFORMATION_MESSAGE);
	}
}
