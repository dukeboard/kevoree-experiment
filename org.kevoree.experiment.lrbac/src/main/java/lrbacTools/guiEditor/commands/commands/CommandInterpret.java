package lrbacTools.guiEditor.commands.commands;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.transformations.PolicyScript2Policy;

public class CommandInterpret extends Command{
	
	
	public CommandInterpret(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	public void execute(){
		PolicyScript2Policy transfo = new PolicyScript2Policy(getEditor().textPaneEditor.getText());
		transfo.transformation(getEditor().getPolicy());
		getEditor().textPaneEditor.setText("PolicyScript{\n\n}");
		getEditor().update();
	}
}