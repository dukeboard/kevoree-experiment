package lrbacTools.guiEditor.graphicComponents;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import lrbacTools.guiEditor.commands.commands.*;

public class MenuBarRbacEditor extends JMenuBar{
	
	private RbacTextualEditor editor;
	private JMenu menuFile;
	private JMenu menuRun;
	private JMenu menuTool;
	private JMenu menuView;
	private JMenu menuHelp;
	//private JMenu menuAssessment;
	
	
	public MenuBarRbacEditor(RbacTextualEditor e){
		super();
		editor = e;
		initBar();
	}
	
	public void initBar(){
		menuFile = new JMenu("File");
		menuRun = new JMenu("Run");
		menuTool = new JMenu("Tools");
		menuView = new JMenu("Views");
		menuHelp = new JMenu("Help");
		//menuAssessment = new JMenu("Assess");
		
		menuFile.add(new MenuItemRbacEditor(new CommandLoad(editor, "load")));
		menuFile.add(new MenuItemRbacEditor(new CommandSave(editor, "save")));
		
		menuRun.add(new MenuItemRbacEditor(new CommandInterpret(editor, "interpret")));
		
		menuView.add(new MenuItemRbacEditor(new CommandGraphMonitor(editor, "graphMonitor")));
		menuView.add(new MenuItemRbacEditor(new CommandGraphMonitorPolicyRule(editor, "graphMonitorPolicyRule")));
		menuView.add(new MenuItemRbacEditor(new CommandGraphMonitorEnforcedRule(editor, "graphMonitorEnforcedRule")));
		menuView.add(new MenuItemRbacEditor(new CommandCompGraphMonitor(editor, "graphMonitorCompPolicyRule")));
		
		
		menuTool.add(new MenuItemRbacEditor(new CommandLoadPolicyScriptExample(editor, "initScriptExample")));
		menuTool.add(new MenuItemRbacEditor(new CommandLoadPolicyModelExample(editor, "initModelExample")));
		menuTool.add(new MenuItemRbacEditor(new CommandInitPolicyMonitor(editor,"initPolicyMonitor")));
		menuTool.addSeparator();
		menuTool.add(new MenuItemRbacEditor(new CommandPolicy2Graph(editor, "policy2PDFGraph")));
		menuTool.add(new MenuItemRbacEditor(new CommandPolicy2KevScript(editor, "policy2KevScript")));
		menuTool.add(new MenuItemRbacEditor(new CommandPolicyInfo(editor, "policy2policyStats")));
		menuTool.add(new MenuItemRbacEditor(new CommandKevoree2PolicyStats(editor, "kevoree2policyStats")));
		menuTool.addSeparator();
		menuTool.add(new MenuItemRbacEditor(new CommandCheck(editor, "check")));
		menuTool.addSeparator();
		menuTool.add(new MenuItemRbacEditor(new CommandComparisonRule(editor, "comparison rule req runtime")));
		
		//menuAssessment.add(new MenuItemRbacEditor(new CommandAssessIncInfo(editor, "incInfo"), editor));
		
		menuHelp.add(new MenuItemRbacEditor(new CommandHelp(editor,"help")));
		menuHelp.add(new MenuItemRbacEditor(new CommandShortcut(editor,"shortcut")));
		menuHelp.add(new MenuItemRbacEditor(new CommandVersion(editor,"version")));
		
		
		add(menuFile);
		add(menuRun);
		add(menuTool);
		add(menuView);
		add(menuHelp);
		
		//add(menuAssessment);
	}
}