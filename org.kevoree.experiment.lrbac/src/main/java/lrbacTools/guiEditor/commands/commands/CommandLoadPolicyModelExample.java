package lrbacTools.guiEditor.commands.commands;

import lrbac.LrbacFactory;
import lrbac.Policy;
import lrbacTools.generator.PolicyGenerator;
import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;
import lrbacTools.transformations.Policy2PolicyScript;
import lrbacTools.transformations.PolicyScript2Policy;

public class CommandLoadPolicyModelExample extends Command{
	
	public CommandLoadPolicyModelExample(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		Policy policy = LrbacFactory.eINSTANCE.createPolicy();
		PolicyGenerator pg = new PolicyGenerator(policy);
		pg.generate(3, 3, 3, 3, 3, 3, false, false, false, false, false, false);
		Policy2PolicyScript p2ps = new Policy2PolicyScript(policy);
		String ps = p2ps.transformation();
		PolicyScript2Policy transfo = new PolicyScript2Policy(ps);
		transfo.transformation(getEditor().getPolicy());
		getEditor().graphMonitorPolicy.update();
	}
}