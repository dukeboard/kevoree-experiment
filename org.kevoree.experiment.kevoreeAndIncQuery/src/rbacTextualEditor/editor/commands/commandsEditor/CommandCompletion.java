package rbacTextualEditor.editor.commands.commandsEditor;

import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;

import javax.swing.Action;
import javax.swing.text.BadLocationException;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;


public class CommandCompletion extends CommandRbac implements ICommandRbac{

	private TextPaneEditor textPaneEditor;
	
	
	public CommandCompletion(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	@Override
	public void execute() {
		
		int pos =textPaneEditor.getCaretPosition() -1;
		String content = null;
		try {
			content = textPaneEditor.getText(0, pos + 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}

		// Find where the word starts
		int w;
		for (w = pos; w >= 0; w--) {
			if (!Character.isLetter(content.charAt(w))) {
				break;
			}
		}
		if (pos - w < 1) {
			// Too few chars
			return;
		}
		String prefix = content.substring(w + 1).toLowerCase();
		try {
			textPaneEditor.getDocument().insertString(pos + 1, getName().substring(prefix.length(),getName().length()), null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}


}
