package rbacTextualEditor.editor.commands.commandsEditor;

import javax.swing.JOptionPane;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;

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
