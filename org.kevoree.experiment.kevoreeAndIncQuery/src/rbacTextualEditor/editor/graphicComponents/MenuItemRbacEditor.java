package rbacTextualEditor.editor.graphicComponents;

import javax.swing.JMenuItem;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.commands.commandsEditor.CommandCompletion;

public class MenuItemRbacEditor extends JMenuItem{
	
	private String name; 
	private TextPaneEditor textPaneEditor;	
	
	public MenuItemRbacEditor(CommandRbac c,TextPaneEditor editor){
		textPaneEditor =editor;
		setText(c.getName());
		setVisible(true);
		addActionListener(c);
		
	}
		
}
