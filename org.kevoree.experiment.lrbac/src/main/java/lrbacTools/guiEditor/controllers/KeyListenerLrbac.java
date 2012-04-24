package lrbacTools.guiEditor.controllers;

import java.awt.*;
import java.awt.event.*;

import lrbacTools.guiEditor.commands.commandsEditor.CommandColoration;
import lrbacTools.guiEditor.commands.commandsPolicy.CommandInterpret;
import lrbacTools.guiEditor.graphicComponents.PopupCompletion;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;


public class KeyListenerLrbac implements KeyListener {

	private TextPaneEditor textPaneEditor;
	private PopupCompletion popupCompletion;

	public KeyListenerLrbac(TextPaneEditor editor,
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