package graphoTools.guiEditor.graphicComponents;

import javax.swing.*;
import graphoTools.guiEditor.commands.*;

public class MenuBarEditor  extends JMenuBar{
private JMenu menuFile;
private JMenu menuRun;
private JMenu menuGraphO;
private JMenu menuHelp;
private JMenu menuAssessment;
private JMenu menuEditor;
private GraphOTextualEditor editor;
public MenuBarEditor(GraphOTextualEditor e){
	editor = e;
menuFile = new JMenu("File");
menuRun = new JMenu("Run");
menuGraphO  = new JMenu("GraphO");
menuAssessment = new JMenu("Assess");
menuEditor = new JMenu("Editor");
menuHelp = new JMenu("Help");

JMenuItem m = new JMenuItem("loadModelExample");
m.setAction(new CommandLoadModelExample(editor, "loadModelExample", "loadModelExample"));
m.setText("loadModelExample");
m.setName("loadModelExample");
m.setVisible(true);
menuGraphO.add(m);


JMenuItem m1 = new JMenuItem("loadScriptExample");
m1.setAction(new CommandLoadScriptExample(editor, "loadScriptExample", "loadScriptExample"));
m1.setText("loadScriptExample");
m1.setName("loadScriptExample");
m1.setVisible(true);
menuGraphO.add(m1);


JMenuItem m2 = new JMenuItem("interpret");
m2.setAction(new CommandInterpretScript(editor, "interpret", "interpret"));
m2.setText("interpret");
m2.setName("interpret");
m2.setVisible(true);
menuRun.add(m2);


JMenuItem m3 = new JMenuItem("displayGraph");
m3.setAction(new CommandDisplayGraph(editor, "displayGraph", "displayGraph"));
m3.setText("displayGraph");
m3.setName("displayGraph");
m3.setVisible(true);
menuRun.add(m3);

add(menuFile);
add(menuRun);
add(menuGraphO);
add(menuAssessment);
add(menuEditor);
add(menuHelp);
}
}