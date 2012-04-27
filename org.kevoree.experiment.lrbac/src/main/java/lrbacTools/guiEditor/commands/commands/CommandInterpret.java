package lrbacTools.guiEditor.commands.commandsPolicy;

import javax.swing.Action;


import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;
import lrbacTools.transformations.PolicyScript2Policy;

public class CommandInterpret extends CommandRbac implements ICommandRbac{
	
	private TextPaneEditor textPaneEditor;
	
	public CommandInterpret(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	public void execute(){
		PolicyScript2Policy transfo = new PolicyScript2Policy(textPaneEditor.getText());
		transfo.transformation(textPaneEditor.getPolicy());
		textPaneEditor.setText("PolicyScript{\n\n}");
		textPaneEditor.getGraphMonitor().update();
		textPaneEditor.getGraphMonitor().setVisible(true);
	}
}