package lrbacTools.guiEditor.commands.commandsEditor;

import javax.swing.JOptionPane;

import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandShortcut  extends CommandRbac implements ICommandRbac{
	private TextPaneEditor textPaneEditor;
	
	public CommandShortcut(TextPaneEditor editor,String id){
		textPaneEditor=editor;
		setName(id);
		setDescription(id);
	}
	
	@Override
	public void execute() {
		JOptionPane.showMessageDialog(textPaneEditor,  "ctrl-space : completion\nctrl-k     : coloration\nctrl-shift : interpret","Shorcut", JOptionPane.INFORMATION_MESSAGE);
	}

}
