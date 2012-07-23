package rbacTextualEditor.editor.commands.commandsPolicy;

import javax.swing.Action;


import rbac.Policy;
import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;
import transformations.PolicyScript2Policy;

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