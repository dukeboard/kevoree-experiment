package lrbacTools.guiEditor.graphicComponents;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.text.BadLocationException;

import lrbacTools.guiEditor.commands.commands.CommandCompletion;


public class PopupCompletion extends JPopupMenu{
	private RbacTextualEditor editor;
	private ArrayList<String> propositions;
	public PopupCompletion(RbacTextualEditor e){
		super();
		editor = e;		
		setAutoscrolls(true);
		propositions = new ArrayList<String>(); 
	}
	
	public void updateItems(){
		removeAll();
		for(String s : propositions){
			JMenuItem menu = new JMenuItem(s);
			CommandCompletion c = new CommandCompletion(editor, s);
			menu.setAction(c);
			menu.setText(s);
			menu.setName(s);
			menu.setVisible(true);
			add(menu);
		}
	}

	public void completion() {
		int pos = editor.textPaneEditor.getCaretPosition() - 1;
		String content = null;
		try {
			content = editor.textPaneEditor.getText(0, pos + 1);
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
			propositions.clear();
			for (String val : editor.languagePrimitives) {
				propositions.add(val);
			}
			updateItems();
			return;
		}
		String prefix = content.substring(w + 1);
		int n = Collections.binarySearch(editor.languagePrimitives, prefix);
		if (n < 0 && -n <= editor.languagePrimitives.size()) {
			propositions.clear();
			for (String val : editor.languagePrimitives) {
				if (val.startsWith(prefix)) {
					propositions.add(val);
				}
			}
		}
		updateItems();
	}
	
}
