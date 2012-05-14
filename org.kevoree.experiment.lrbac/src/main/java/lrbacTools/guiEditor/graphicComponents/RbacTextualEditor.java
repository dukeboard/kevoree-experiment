package lrbacTools.guiEditor.graphicComponents;

import java.awt.Dimension;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import lrbac.*;
import lrbacTools.editor.PolicyEditor;
import lrbacTools.guiEditor.Launcher;
import lrbacTools.guiEditor.commands.commands.CommandColoration;
import lrbacTools.guiEditor.controllers.DocumentListenerLrbac;
import lrbacTools.guiEditor.controllers.KeyListenerLrbac;
import lrbacTools.guiEditor.controllers.PolicyListenerLrbac;
import lrbacTools.transformations.Policy2KevScript;

public class RbacTextualEditor extends JFrame {
	
	//kevoree launcher
	public Launcher kevoreeLauncher;
	
	//model
	private Policy policy;
	
	// graphic elements
	public TextPaneEditor textPaneEditor;
	public MenuBarRbacEditor menuBarTextPane;
	public PopupCompletion popupCompletion;
	public final List<String> languagePrimitives;

	public GraphMonitor graphMonitorPolicy;
	public GraphMonitorPolicyRule graphMonitorPolicyRule;
	public GraphMonitorEnforcedRule graphMonitorEnforcedRule;
	public GraphMonitorCompPolicyRule graphMonitorCompPolicyRule;
	
	// controllers
	public KeyListenerLrbac keyListenerLrbac;
	public DocumentListenerLrbac documentListenerLrbac;
	public PolicyListenerLrbac policyListenerLrbac;

	public RbacTextualEditor(Launcher k) {
		super();
		kevoreeLauncher = k;
		languagePrimitives = new ArrayList<String>();
		initLanguagePrimitives();		
	
		initModel();
		initGraphicComponent();
		initControllers();
		
		Policy2KevScript policy2KevScript = new Policy2KevScript(policy);
		String script= policy2KevScript.addStaticArchitecturalElements();
	}


	public RbacTextualEditor() {
		super();
		languagePrimitives = new ArrayList<String>();
		initLanguagePrimitives();		
		
		initModel();
		initGraphicComponent();
		initControllers();
	}
	
	public void initModel(){
		policy = LrbacFactory.eINSTANCE.createPolicy();
	}
	
	public void initGraphicComponent(){
		setTitle("RBAC Editor");
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		textPaneEditor = new TextPaneEditor(kevoreeLauncher);
		JScrollPane scroll = new JScrollPane();
		Dimension d = new Dimension(600, 400);
		scroll.setSize(d);
		scroll.setPreferredSize(d);
		scroll.setMinimumSize(d);
		setContentPane(scroll);
		scroll.setViewportView(textPaneEditor);
		
		menuBarTextPane = new MenuBarRbacEditor(this);
		setJMenuBar(menuBarTextPane);
		
		graphMonitorPolicy = new GraphMonitor(this);
		graphMonitorPolicyRule = new GraphMonitorPolicyRule(this);
		graphMonitorEnforcedRule = new GraphMonitorEnforcedRule(this);
		graphMonitorCompPolicyRule  = new GraphMonitorCompPolicyRule(this);
		
		popupCompletion = new PopupCompletion(this);
		add(popupCompletion);
	}

	public void initControllers(){
		keyListenerLrbac = new KeyListenerLrbac(this);
		textPaneEditor.addKeyListener(keyListenerLrbac);
		documentListenerLrbac = new DocumentListenerLrbac(this);
		textPaneEditor.getDocument().addDocumentListener(documentListenerLrbac);
		policyListenerLrbac = new PolicyListenerLrbac(this);
		// policyListenerLrbac.startMonitor();
	}
	
	public void initLanguagePrimitives() { 
		for (Method m : PolicyEditor.class.getMethods()) {
			languagePrimitives.add(m.getName());
		}
	}

	public void update(){
		graphMonitorPolicy.update();
		graphMonitorEnforcedRule.update();
		graphMonitorPolicyRule.update();
		graphMonitorCompPolicyRule.update();
		CommandColoration c= new CommandColoration(this, "coloration");
		c.execute();
	}
	
	/**
	 * @return the policy
	 */
	public Policy getPolicy() {
		return policy;
	}

	/**
	 * @param policy the policy to set
	 */
	public void setPolicy(Policy policy) {
		this.policy = policy;
	}
	
}