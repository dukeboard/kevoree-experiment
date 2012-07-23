package rbacTextualEditor.editor.commands.assessmentCommands;

import javax.swing.Action;


import assessments.AssessIncInfo;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;

public class CommandAssessIncInfo extends CommandRbac implements ICommandRbac{
	
	private TextPaneEditor textPaneEditor;
	
	
	public CommandAssessIncInfo(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	
	public void execute(){
		AssessIncInfo.assessIncInfo();
	}

}