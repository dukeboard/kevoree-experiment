package lrbacTools.guiEditor.commands.commands;

import grapho.GraphOEditor;

import java.lang.reflect.Method;

import javax.swing.text.BadLocationException;

import lrbacTools.editor.PolicyEditor;
import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandCompletion extends Command {
	private TextPaneEditor textPaneEditor;

	public CommandCompletion(RbacTextualEditor editor, String id) {
		super(editor, id);
	}

	@Override
	public void execute() {
		System.out.println("command completion");
		// to insert the selected string into the document
		int pos = getEditor().textPaneEditor.getCaretPosition() - 1;
		String content = null;
		try {
			content = getEditor().textPaneEditor.getText(0, pos + 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		int w;
		for (w = pos; w >= 0; w--) {
			if (!Character.isLetter(content.charAt(w))) {
				break;
			}
		}
		String prefix = content.substring(w + 1).toLowerCase();
		try {
			String toInsert = getName().substring(prefix.length(),
					getName().length());
			String[] args = null;
			for (Method m : PolicyEditor.class.getMethods()) {
				if (m.getName().equals(getName())) {
					if (!(m.getParameterTypes().length == 0)) {
						args = new String[m.getParameterTypes().length];
					}
					int cpt = 0;
					for (Class c : m.getParameterTypes()) {
						System.out.println(c);
						args[cpt] = c.getSimpleName();
						cpt = cpt + 1;
					}
				}
			}
			toInsert = toInsert + "(";
			if (!(args == null)) {
				for (int i = 0; i < args.length; i++) {
					if (i == (args.length - 1)) {
						toInsert = toInsert + args[i];
					} else {
						toInsert = toInsert + args[i] + ",";
					}
				}
			}
			toInsert = toInsert + ");";
			getEditor().textPaneEditor.getDocument()
					.insertString(pos + 1, toInsert, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}

}
