package lrbacTools.guiEditor.commands.commands;

import javax.swing.JOptionPane;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandShortcut  extends Command{
	
	public CommandShortcut(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(getEditor().textPaneEditor,  "ctrl-space : completion\nctrl-k     : coloration\nctrl-shift : interpret","Shorcut", JOptionPane.INFORMATION_MESSAGE);
	}

}
