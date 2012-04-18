package rbacTextualEditor.editor.commands.commandsEditor;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;

public class CommandColoration extends CommandRbac{
	
	private TextPaneEditor textPaneEditor;
	
	
	public CommandColoration(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	
	public void execute(){
		String text = textPaneEditor.getText().replaceAll("\n", " ");
		final StyledDocument doc = textPaneEditor.getStyledDocument();
		final MutableAttributeSet normal = new SimpleAttributeSet();
		StyleConstants.setForeground(normal, Color.black);
		StyleConstants.setBold(normal, false);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				doc.setCharacterAttributes(0, doc.getLength(), normal, true);
			}
		});

		for (String statements : textPaneEditor.primitives) {
			Pattern p = Pattern.compile("(" + statements + ")");
			Matcher m = p.matcher(text);
			while (m.find() == true) {
				MutableAttributeSet attri = new SimpleAttributeSet();
				StyleConstants.setForeground(attri, Color.blue);
				StyleConstants.setBold(attri, true);
				final int start = m.start(0);
				final int end = m.end(0);
				final int length = end - start;
				final MutableAttributeSet style = attri;
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						doc.setCharacterAttributes(start, length, style, true);
					}
				});
			}
		}
		
		Pattern p = Pattern.compile("(PolicyScript)");
		Matcher m = p.matcher(text);
		while (m.find() == true) {
			MutableAttributeSet attri = new SimpleAttributeSet();
			StyleConstants.setForeground(attri, Color.orange);
			StyleConstants.setBold(attri, true);
			final int start = m.start(0);
			final int end = m.end(0);
			final int length = end - start;
			final MutableAttributeSet style = attri;
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					doc.setCharacterAttributes(start, length, style, true);
				}
			});
		}
	}
	

}
