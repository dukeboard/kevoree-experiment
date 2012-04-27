package lrbacTools.guiEditor.commands.commands;

import javax.swing.JOptionPane;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.transformations.Policy2KevScript;

public class CommandPolicy2KevScript extends Command{
	
	public CommandPolicy2KevScript(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	public void execute(){
		Policy2KevScript transfo = new Policy2KevScript(getEditor().getPolicy());
		String res = transfo.transformation();
		JOptionPane.showMessageDialog(getEditor(), res, "Policy2KevScript", JOptionPane.INFORMATION_MESSAGE);
	}
		
	}


