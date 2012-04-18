package rbacTextualEditor.editor.commands.commandsPolicy;

import javax.swing.Action;
import javax.swing.JOptionPane;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;
import transformations.IncrementalPolicy2KevScript;
import transformations.Policy2Stats;

public class CommandPolicyInfo extends CommandRbac implements ICommandRbac{
	
	private TextPaneEditor textPaneEditor;
	
	
	public CommandPolicyInfo(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	
	public void execute(){
		Policy2Stats transfo = new Policy2Stats(textPaneEditor.getPolicy());
		String res = transfo.transformation();
		JOptionPane.showMessageDialog(textPaneEditor, res, "PolicyInfo", JOptionPane.INFORMATION_MESSAGE);
	}
		
	}
