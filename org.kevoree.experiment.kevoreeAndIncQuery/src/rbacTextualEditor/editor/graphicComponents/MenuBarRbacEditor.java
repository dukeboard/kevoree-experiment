package rbacTextualEditor.editor.graphicComponents;

import javax.swing.JMenu;
import javax.swing.JMenuBar;

import rbacTextualEditor.editor.commands.assessmentCommands.CommandAssessIncInfo;
import rbacTextualEditor.editor.commands.commandsEditor.CommandHelp;
import rbacTextualEditor.editor.commands.commandsEditor.CommandLoadPolicyScriptExample;
import rbacTextualEditor.editor.commands.commandsEditor.CommandShortcut;
import rbacTextualEditor.editor.commands.commandsEditor.CommandVersion;
import rbacTextualEditor.editor.commands.commandsPolicy.CommandCheck;
import rbacTextualEditor.editor.commands.commandsPolicy.CommandGraphMonitor;
import rbacTextualEditor.editor.commands.commandsPolicy.CommandLoadPolicyModelExample;
import rbacTextualEditor.editor.commands.commandsPolicy.CommandPolicy2KevScript;
import rbacTextualEditor.editor.commands.commandsPolicy.CommandPolicy2Graph;
import rbacTextualEditor.editor.commands.commandsPolicy.CommandInterpret;
import rbacTextualEditor.editor.commands.commandsPolicy.CommandPolicyInfo;

public class MenuBarRbacEditor extends JMenuBar{
	
	private TextPaneEditor textPaneEditor;
	private JMenu menuFile;
	private JMenu menuRun;
	private JMenu menuPolicy;
	private JMenu menuHelp;
	private JMenu menuAssessment;
	private JMenu menuEditor;
	public MenuBarRbacEditor(TextPaneEditor editor){
		super();
		textPaneEditor = editor;
		initBar();
	}
	
	public void initBar(){
		menuFile = new JMenu("File");
		menuRun = new JMenu("Run");
		menuPolicy = new JMenu("Policy");
		menuAssessment = new JMenu("Assess");
		menuEditor = new JMenu("Editor");
		menuHelp = new JMenu("Help");
		
		menuFile.add(new MenuItemRbacEditor(new CommandLoadPolicyScriptExample(textPaneEditor, "initScriptExample"), textPaneEditor));
		
		menuRun.add(new MenuItemRbacEditor(new CommandInterpret(textPaneEditor, "interpret"), textPaneEditor));
		
		menuEditor.add(new MenuItemRbacEditor(new CommandGraphMonitor(textPaneEditor, "graphMonitor"), textPaneEditor));
		
		menuPolicy.add(new MenuItemRbacEditor(new CommandLoadPolicyModelExample(textPaneEditor, "initModelExample"), textPaneEditor));
		menuPolicy.add(new MenuItemRbacEditor(new CommandPolicy2Graph(textPaneEditor.getPolicy(), "policy2PDFGraph"), textPaneEditor));
		menuPolicy.add(new MenuItemRbacEditor(new CommandPolicy2KevScript(textPaneEditor, "policy2KevScript"), textPaneEditor));
		menuPolicy.add(new MenuItemRbacEditor(new CommandPolicyInfo(textPaneEditor, "policy2stats"), textPaneEditor));
		menuPolicy.add(new MenuItemRbacEditor(new CommandCheck(textPaneEditor, "policyCheck"), textPaneEditor));
		
		menuAssessment.add(new MenuItemRbacEditor(new CommandAssessIncInfo(textPaneEditor, "incInfo"), textPaneEditor));
		
		menuHelp.add(new MenuItemRbacEditor(new CommandHelp(textPaneEditor,"help"), textPaneEditor));
		menuHelp.add(new MenuItemRbacEditor(new CommandShortcut(textPaneEditor,"shortcut"), textPaneEditor));
		menuHelp.add(new MenuItemRbacEditor(new CommandVersion(textPaneEditor,"version"), textPaneEditor));
		
		
		add(menuFile);
		add(menuRun);
		add(menuPolicy);
		add(menuAssessment);
		add(menuEditor);
		add(menuHelp);
	}
}
