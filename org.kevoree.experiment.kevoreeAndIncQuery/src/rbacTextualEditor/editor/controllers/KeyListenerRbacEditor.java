package rbacTextualEditor.editor.controllers;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


import rbacTextualEditor.editor.commands.commandsEditor.CommandColoration;
import rbacTextualEditor.editor.commands.commandsPolicy.CommandInterpret;
import rbacTextualEditor.editor.graphicComponents.PopupCompletion;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;

public class KeyListenerRbacEditor implements KeyListener {

	private TextPaneEditor textPaneEditor;
	private PopupCompletion popupCompletion;

	public KeyListenerRbacEditor(TextPaneEditor editor,
			PopupCompletion popup) {
		textPaneEditor = editor;
		popupCompletion = popup;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (e.isControlDown()) {
			textPaneEditor.setBackground(Color.lightGray);
			if (code == KeyEvent.VK_SPACE) {				
				popupCompletion = new PopupCompletion(textPaneEditor);
				if (!(textPaneEditor.getCaret().getMagicCaretPosition() == null)) {
					popupCompletion
							.show(textPaneEditor, textPaneEditor.getCaret()
									.getMagicCaretPosition().x + 5,
									textPaneEditor.getCaret()
											.getMagicCaretPosition().y + 10);
				}
			}
		}
		if (code == KeyEvent.VK_ESCAPE) {
			popupCompletion.setVisible(false);
		}
		if (e.isControlDown()) {
			if (code == KeyEvent.VK_K) {
				popupCompletion = new PopupCompletion(textPaneEditor);
				if (!(textPaneEditor.getCaret().getMagicCaretPosition() == null)) {
					CommandColoration coloration = new CommandColoration(textPaneEditor, "coloration");
					coloration.execute();
				}
			}
		}
		if (e.isControlDown()) {
			if (code == KeyEvent.VK_SHIFT) {
				CommandInterpret ci = new CommandInterpret(textPaneEditor, "interpret");
				ci.execute();
				textPaneEditor.setBackground(Color.white);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		textPaneEditor.setBackground(Color.white);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		textPaneEditor.setBackground(Color.white);
	}

	

}