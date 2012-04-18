package rbacTextualEditor.editor.graphicComponents;

import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import rbacTextualEditor.editor.commands.commandsEditor.CommandCompletion;


public class PopupCompletion extends JPopupMenu{
	private ArrayList<String> propositions;
	private TextPaneEditor textPaneEditor;
	
	public PopupCompletion(TextPaneEditor editor){
		super();
		textPaneEditor = editor;
		propositions = (ArrayList<String>) textPaneEditor.propositions;
		if(propositions.size() == 0){
			add(new JMenuItem("no match found"));
		}
		for(String s : propositions){
			add(new MenuItemRbacEditor(new CommandCompletion(textPaneEditor, s),textPaneEditor));
		}
	}
}
