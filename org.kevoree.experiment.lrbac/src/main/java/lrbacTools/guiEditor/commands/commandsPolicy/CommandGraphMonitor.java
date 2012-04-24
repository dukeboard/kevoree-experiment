package lrbacTools.guiEditor.commands.commandsPolicy;



import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;


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