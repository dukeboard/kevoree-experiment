package lrbacTools.guiEditor.controllers;

import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import lrbacTools.guiEditor.commands.commands.CommandColoration;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;



public class DocumentListenerLrbac implements DocumentListener {

	private RbacTextualEditor editor;

	public DocumentListenerLrbac(RbacTextualEditor e) {
		editor = e;
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
				CommandColoration c =new CommandColoration(editor, "command");
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