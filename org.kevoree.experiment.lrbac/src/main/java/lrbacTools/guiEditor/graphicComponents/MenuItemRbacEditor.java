package lrbacTools.guiEditor.graphicComponents;

import javax.swing.JMenuItem;

import lrbacTools.guiEditor.commands.Command;

public class MenuItemRbacEditor extends JMenuItem{
	public MenuItemRbacEditor(Command c){
		setText(c.getName());
		setVisible(true);
		addActionListener(c);
	}		
}
