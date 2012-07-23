package lrbacTools.guiEditor.commands.commands;

import javax.swing.JOptionPane;

import lrbacTools.checker.PolicyChecker;
import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;


public class CommandCheck extends Command{
	
	public CommandCheck(RbacTextualEditor editor, String id){
		super(editor,id);
	}
	
	
	public void execute(){
		PolicyChecker checker = new PolicyChecker(getEditor().getPolicy());
		String res =checker.checkPolicy();
		JOptionPane.showMessageDialog(getEditor(), res, "Check", JOptionPane.INFORMATION_MESSAGE);
	}

}