package rbacTextualEditor.editor.controllers;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import rbacTextualEditor.editor.commands.commandsEditor.CommandColoration;
import rbacTextualEditor.editor.graphicComponents.PopupCompletion;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;

public class DocumentListenerRbacEditor implements DocumentListener {

	private TextPaneEditor textPaneEditor;

	public DocumentListenerRbacEditor(TextPaneEditor editor,
			PopupCompletion popup) {
		textPaneEditor = editor;
	}
	
	@Override
	public void changedUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUpdate(DocumentEvent e) {		
		Runnable r = new Runnable() {
			@Override
			public void run() {
				CommandColoration c =new CommandColoration(textPaneEditor, "command");
				c.execute();
				
			}
		};
		SwingUtilities.invokeLater(r);
		
	}

	@Override
	public void removeUpdate(DocumentEvent e) {
		// TODO Auto-generated method stub
		
	}
}