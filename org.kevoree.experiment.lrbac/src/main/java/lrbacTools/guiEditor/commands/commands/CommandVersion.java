package lrbacTools.guiEditor.commands.commands;

import javax.swing.JOptionPane;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandVersion extends Command{
	
	public CommandVersion(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(getEditor().textPaneEditor, "Version 0.01 16/04/2012", "Version", JOptionPane.INFORMATION_MESSAGE);
	}
}
