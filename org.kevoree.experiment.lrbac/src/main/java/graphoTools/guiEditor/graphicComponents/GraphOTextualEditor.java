package graphoTools.guiEditor.graphicComponents;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.util.List;
import java.lang.reflect.*;
import grapho.*;
import graphoTools.guiEditor.controllers.KeyListenerEditor;
import graphoTools.guiEditor.controllers.DocumentListenerEditor;

public class GraphOTextualEditor extends JFrame{
private TextPaneEditor textPaneEditor;
private MenuBarEditor menuBar;
private GraphO grapho;
private KeyListenerEditor keyListenerEditor;
private DocumentListenerEditor documentListenerEditor;
public PopupCompletion popupCompletion;
public GraphMonitor graphMonitor;
public final List<String> primitives;

	public GraphOTextualEditor(){
		grapho = GraphoFactory.eINSTANCE.createGraphO();
		setTitle("GRAPHO Editor");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       popupCompletion =new PopupCompletion(this);
		textPaneEditor = new TextPaneEditor(this);
		keyListenerEditor = new KeyListenerEditor(this);
		documentListenerEditor = new DocumentListenerEditor(this);
		textPaneEditor.addKeyListener(keyListenerEditor);
		textPaneEditor.getDocument().addDocumentListener(documentListenerEditor);
		JScrollPane scroll = new JScrollPane();
		Dimension d =new Dimension(600, 400);
		scroll.setSize(d);
		scroll.setPreferredSize(d);
		scroll.setMinimumSize(d);
		setContentPane(scroll);
		scroll.setViewportView(textPaneEditor);
		menuBar = new MenuBarEditor(this);
		setJMenuBar(menuBar);
		primitives = new ArrayList<String>();
       initPrimitives();
       graphMonitor = new GraphMonitor(grapho);
	}
	public GraphOTextualEditor(GraphO x) {
		grapho = x;
		setTitle("GRAPHO Editor");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		popupCompletion =new PopupCompletion(this);
		textPaneEditor = new TextPaneEditor(this);
		keyListenerEditor = new KeyListenerEditor(this);
		documentListenerEditor = new DocumentListenerEditor(this);
		textPaneEditor.addKeyListener(keyListenerEditor);
		textPaneEditor.getDocument().addDocumentListener(documentListenerEditor);
		JScrollPane scroll = new JScrollPane();
		Dimension d =new Dimension(600, 400);
		scroll.setSize(d);
		scroll.setPreferredSize(d);
		scroll.setMinimumSize(d);
		setContentPane(scroll);
		scroll.setViewportView(textPaneEditor);
		menuBar = new MenuBarEditor(this);
		setJMenuBar(menuBar);
		primitives = new ArrayList<String>();
		initPrimitives();
	}
	public GraphO getGraphO(){
		return grapho;
   }
	public void setGraphO(GraphO x){
		grapho=x;
   }
	public TextPaneEditor getTextPaneEditor() {
		return textPaneEditor;
	}	
	public void initPrimitives() {
		for (Method m : GraphOEditor.class.getDeclaredMethods()){
			primitives.add(m.getName());
		}
	}
}