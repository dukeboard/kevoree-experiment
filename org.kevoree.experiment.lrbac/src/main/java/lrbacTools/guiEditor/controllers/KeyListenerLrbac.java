package lrbacTools.guiEditor.controllers;

import java.awt.*;
import java.awt.event.*;

import lrbacTools.guiEditor.commands.commands.CommandColoration;
import lrbacTools.guiEditor.commands.commands.CommandInterpret;
import lrbacTools.guiEditor.graphicComponents.PopupCompletion;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;


public class KeyListenerLrbac implements KeyListener {

	private RbacTextualEditor editor;
	private PopupCompletion popupCompletion;

	public KeyListenerLrbac(RbacTextualEditor e) {
		editor = e;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		if (e.isControlDown()) {
			editor.setBackground(Color.lightGray);
			if (code == KeyEvent.VK_SPACE) {				
				editor.popupCompletion.completion();
				editor.popupCompletion.show(editor.textPaneEditor,editor.textPaneEditor.getCaret().getMagicCaretPosition().x+5,editor.textPaneEditor.getCaret().getMagicCaretPosition().y+13);
			}
		}
		if (code == KeyEvent.VK_ESCAPE) {
			popupCompletion.setVisible(false);
		}
		if (e.isControlDown()) {
			if (code == KeyEvent.VK_K) {
				popupCompletion = new PopupCompletion(editor);
				if (!(editor.textPaneEditor.getCaret().getMagicCaretPosition() == null)) {
					CommandColoration coloration = new CommandColoration(editor, "coloration");
					coloration.execute();
				}
			}
		}
		if (e.isControlDown()) {
			if (code == KeyEvent.VK_SHIFT) {
				CommandInterpret ci = new CommandInterpret(editor, "interpret");
				ci.execute();
				editor.setBackground(Color.white);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		editor.setBackground(Color.white);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		editor.setBackground(Color.white);
	}

	

}