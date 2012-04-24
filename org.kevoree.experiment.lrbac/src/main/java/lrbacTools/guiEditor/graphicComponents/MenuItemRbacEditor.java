package lrbacTools.guiEditor.graphicComponents;

import javax.swing.JMenuItem;

import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.commands.commandsEditor.CommandCompletion;


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
