package rbacTextualEditor.editor.commands.commandsPolicy;


import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;


public class CommandGraphMonitor  extends CommandRbac implements ICommandRbac{
	private TextPaneEditor textPaneEditor;
	public CommandGraphMonitor(TextPaneEditor editor,String id){
		textPaneEditor = editor;
		setName(id);
		setDescription(id);
	}
	
	@Override
	public void execute() {
		textPaneEditor.getGraphMonitor().setVisible(true);
	}
}