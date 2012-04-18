package rbacTextualEditor.editor.graphicComponents;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextPane;
import javax.swing.event.DocumentListener;

import rbac.Policy;
import rbac.RbacFactory;
import rbacTextualEditor.editor.controllers.DocumentListenerRbacEditor;
import rbacTextualEditor.editor.controllers.KeyListenerRbacEditor;
import rbacTools.editor.PolicyEditor;

public class TextPaneEditor extends JTextPane{

	private PopupCompletion popupCompletion;
	private KeyListenerRbacEditor keyListenerTextAreaEditor;
	private DocumentListenerRbacEditor documentListenerTextPaneEditor;
	public final List<String> primitives;
	public static List<String> propositions;
	private Policy policy;
	private GraphMonitor graphMonitor;
	
	public TextPaneEditor() {
		super();		
		policy = RbacFactory.eINSTANCE.createPolicy();
		graphMonitor = new GraphMonitor(policy);
		primitives = new ArrayList<String>();
		propositions = new ArrayList<String>();
		initPrimitives();
		popupCompletion = new PopupCompletion(this);
		add(popupCompletion);
		keyListenerTextAreaEditor = new KeyListenerRbacEditor(this,
				popupCompletion);
		addKeyListener(keyListenerTextAreaEditor);
		documentListenerTextPaneEditor = new DocumentListenerRbacEditor(this, popupCompletion);
		getDocument().addDocumentListener(documentListenerTextPaneEditor);
		setText("PolicyScript{\n\n}");
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