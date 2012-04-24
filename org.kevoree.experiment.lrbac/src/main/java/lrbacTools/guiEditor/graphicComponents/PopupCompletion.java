package lrbacTools.guiEditor.graphicComponents;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.BadLocationException;

import lrbacTools.guiEditor.commands.commandsEditor.CommandCompletion;



public class PopupCompletion extends JPopupMenu{
	private ArrayList<String> propositions;
	private TextPaneEditor textPaneEditor;
	
	public PopupCompletion(TextPaneEditor editor){
		super();
		textPaneEditor = editor;
		completion();
		propositions = (ArrayList<String>) textPaneEditor.propositions;
		
		if(propositions.size() == 0){
			add(new JMenuItem("no match found"));
		}
		for(String s : propositions){
			add(new MenuItemRbacEditor(new CommandCompletion(textPaneEditor, s),textPaneEditor));
		}
		
		setAutoscrolls(true);
	}
	
	public void completion() {
		textPaneEditor.propositions.clear();
		int pos = textPaneEditor.getCaretPosition() - 1;
		String content = null;
		try {
			content = textPaneEditor.getText(0, pos + 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		int w;
		for (w = pos; w >= 0; w--) {
			if (!Character.isLetter(content.charAt(w))) {
				break;
			}
		}
		if (pos - w < 1) {
			textPaneEditor.propositions.clear();
			for (String val : textPaneEditor.primitives) {
				textPaneEditor.propositions.add(val);
			}
			return;
		}
		//String prefix = content.substring(w + 1).toLowerCase();
		String prefix = content.substring(w + 1);
		int n = Collections.binarySearch(textPaneEditor.primitives, prefix);
		if (n < 0 && -n <= textPaneEditor.primitives.size()) {
			textPaneEditor.propositions.clear();
			for (String val : textPaneEditor.primitives) {
				if (val.startsWith(prefix)) {
					textPaneEditor.propositions.add(val);
				}
			}
		}
	}
	
}
