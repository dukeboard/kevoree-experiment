package lrbacTools.guiEditor.commands.commands;

import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;
import javax.swing.text.MutableAttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandColoration extends Command{
	
	private TextPaneEditor textPaneEditor;
	
	
	public CommandColoration(RbacTextualEditor editor, String id){
		super(editor, id);
		textPaneEditor = getEditor().textPaneEditor;
	}
	
	
	public void execute(){
		coloration();
	}
	
	public void coloration() {
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
		
		colorationPrimitives(text, doc);
		colorationPolicyScript(text, doc);

	}
	
	
	public void colorationPrimitives(String text, final StyledDocument doc){
		for (String statements : getEditor().languagePrimitives) {
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
	}
	
	public void colorationPolicyScript(String text, final StyledDocument doc){
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
