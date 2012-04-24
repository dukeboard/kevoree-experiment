package lrbacTools.guiEditor.commands.commandsEditor;

import javax.swing.JOptionPane;

import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

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
