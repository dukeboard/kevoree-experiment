package lrbacTools.guiEditor.graphicComponents;

import java.lang.reflect.Method;
import java.util.*;
import javax.swing.JTextPane;
import lrbacTools.editor.PolicyEditor;
import lrbacTools.guiEditor.Launcher;
import lrbacTools.guiEditor.controllers.DocumentListenerLrbac;
import lrbacTools.guiEditor.controllers.KeyListenerLrbac;
import lrbacTools.guiEditor.controllers.PolicyListenerLrbac;
import lrbac.*;


public class TextPaneEditor extends JTextPane{
	
	public Launcher l;
	

	public TextPaneEditor() {
		super();		
		setText("PolicyScript{\n\n}");
	}
	
	
	public TextPaneEditor(Launcher k) {
		super();		
		l = k;		
		setText("PolicyScript{\n\n}");
	}

}