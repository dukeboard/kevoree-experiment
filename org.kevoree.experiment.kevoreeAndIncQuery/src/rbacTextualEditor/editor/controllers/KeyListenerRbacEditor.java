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
				completion();
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
					coloration();
				}
			}
		}
		if (e.isControlDown()) {
			if (code == KeyEvent.VK_SHIFT) {
				CommandInterpret ci = new CommandInterpret(textPaneEditor, "interpret");
				ci.execute();
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

	public void completion() {
		textPaneEditor.propositions.clear();
		int pos = textPaneEditor.getCaretPosition() - 1;
		String content = null;
		try {
			content = textPaneEditor.getText(0, pos + 1);
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
			return;
		}
		String prefix = content.substring(w + 1).toLowerCase();
		int n = Collections.binarySearch(textPaneEditor.primitives, prefix);
		if (n < 0 && -n <= textPaneEditor.primitives.size()) {
			textPaneEditor.propositions.clear();
			for (String val : textPaneEditor.primitives) {
				if (val.startsWith(prefix)) {
					textPaneEditor.propositions.add(val);
				}
			}
		}
	}

	public void coloration() {
		popupCompletion.setVisible(false);
		String text = textPaneEditor.getText().replaceAll("\n", " ");
		System.out.println(text);
		final StyledDocument doc = textPaneEditor.getStyledDocument();
		final MutableAttributeSet normal = new SimpleAttributeSet();
		StyleConstants.setForeground(normal, Color.black);
		StyleConstants.setBold(normal, false);
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				doc.setCharacterAttributes(0, doc.getLength(), normal, true);
			}
		});

		for (String strToHL : textPaneEditor.primitives) {
			System.out.println(strToHL);
			Pattern p = Pattern.compile("(" + strToHL + ")");
			Matcher m = p.matcher(text);
			while (m.find() == true) {
				System.out.println("Found    '" + m.group(0)
						+ "'  at position  " + m.start(0) + "-" + m.end(0)
						+ "   +++\n");
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

}