package lrbacTools.guiEditor.graphicComponents;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import org.kevoree.api.service.core.script.KevScriptEngine;
public class RbacTextualEditor extends JFrame{
	
	private TextPaneEditor textPaneEditor;
	private MenuBarRbacEditor menuBarTextPane;
	public KevScriptEngine kse;
	
	public RbacTextualEditor(KevScriptEngine k){
		kse = k;
		setTitle("RBAC Editor");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		textPaneEditor = new TextPaneEditor(kse);
		JScrollPane scroll = new JScrollPane();
		Dimension d =new Dimension(600, 400);
		scroll.setSize(d);
		scroll.setPreferredSize(d);
		scroll.setMinimumSize(d);
		setContentPane(scroll);
		scroll.setViewportView(textPaneEditor);
		menuBarTextPane = new MenuBarRbacEditor(textPaneEditor);
		setJMenuBar(menuBarTextPane);		
	}
}
