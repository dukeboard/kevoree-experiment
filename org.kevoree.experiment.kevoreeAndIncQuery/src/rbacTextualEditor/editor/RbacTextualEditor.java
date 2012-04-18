package rbacTextualEditor.editor;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import rbac.Policy;
import rbac.RbacFactory;
import rbacTextualEditor.editor.graphicComponents.MenuBarRbacEditor;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;

public class RbacTextualEditor extends JFrame{
	
	private TextPaneEditor textPaneEditor;
	private MenuBarRbacEditor menuBarTextPane;
	
	public RbacTextualEditor(){
		setTitle("RBAC Editor");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		textPaneEditor = new TextPaneEditor();
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
