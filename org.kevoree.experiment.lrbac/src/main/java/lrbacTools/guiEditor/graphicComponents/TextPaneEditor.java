package lrbacTools.guiEditor.graphicComponents;

import java.lang.reflect.Method;
import java.util.*;

import javax.swing.JTextPane;
import javax.swing.event.DocumentListener;

import lrbacTools.editor.PolicyEditor;
import lrbacTools.guiEditor.controllers.DocumentListenerLrbac;
import lrbacTools.guiEditor.controllers.KeyListenerLrbac;
import lrbacTools.guiEditor.controllers.PolicyListenerLrbac;

import lrbac.*;
import org.kevoree.api.service.core.script.KevScriptEngine;
public class TextPaneEditor extends JTextPane{

	//popup completion
	private PopupCompletion popupCompletion;
	public final List<String> primitives;
	public static List<String> propositions;
	
	
	//listeners
	private KeyListenerLrbac keyListenerLrbac;
	private DocumentListenerLrbac documentListenerLrbac;
	private PolicyListenerLrbac policyListenerLrbac;
	
	//model
	private Policy policy;
	
	//graph monitor
	private GraphMonitor graphMonitor;
	public KevScriptEngine kse;
	public TextPaneEditor(KevScriptEngine k) {
		super();		
		kse = k;
		policy = LrbacFactory.eINSTANCE.createPolicy();
		
		graphMonitor = new GraphMonitor(policy);
		
		primitives = new ArrayList<String>();
		propositions = new ArrayList<String>();
		initPrimitives();
		popupCompletion = new PopupCompletion(this);
		add(popupCompletion);
		
		keyListenerLrbac = new KeyListenerLrbac(this,popupCompletion);
		addKeyListener(keyListenerLrbac);
		documentListenerLrbac = new DocumentListenerLrbac(this, popupCompletion);
		getDocument().addDocumentListener(documentListenerLrbac);
		policyListenerLrbac = new PolicyListenerLrbac(this,policy);
		//policyListenerLrbac.startMonitor();
		
		
		setText("PolicyScript{\n\n}");
	}

	/**
	 * @return the policyListenerLrbac
	 */
	public PolicyListenerLrbac getPolicyListenerLrbac() {
		return policyListenerLrbac;
	}

	/**
	 * @param policyListenerLrbac the policyListenerLrbac to set
	 */
	public void setPolicyListenerLrbac(PolicyListenerLrbac policyListenerLrbac) {
		this.policyListenerLrbac = policyListenerLrbac;
	}

	/**
	 * @return the graphMonitor
	 */
	public GraphMonitor getGraphMonitor() {
		return graphMonitor;
	}

	/**
	 * @param graphMonitor the graphMonitor to set
	 */
	public void setGraphMonitor(GraphMonitor graphMonitor) {
		this.graphMonitor = graphMonitor;
	}

	public void initPrimitives() {
		for (Method m : PolicyEditor.class.getMethods()){
			primitives.add(m.getName());
		}
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
	public void setPolicy(Policy p) {
		policy = policy;
	}
}