package rbacTextualEditor.editor.commands.commandsPolicy;

import javax.swing.Action;
import javax.swing.JOptionPane;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;
import rbacTools.checker.PolicyChecker;
import transformations.IncrementalPolicy2KevScript;

public class CommandPolicy2KevScript extends CommandRbac implements ICommandRbac{
	
	private TextPaneEditor textPaneEditor;
	
	
	public CommandPolicy2KevScript(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	
	public void execute(){
		IncrementalPolicy2KevScript transfo = new IncrementalPolicy2KevScript(textPaneEditor.getPolicy());
		String res = transfo.transformation();
		JOptionPane.showMessageDialog(textPaneEditor, res, "Policy2KevScript", JOptionPane.INFORMATION_MESSAGE);
	}
		
	}


