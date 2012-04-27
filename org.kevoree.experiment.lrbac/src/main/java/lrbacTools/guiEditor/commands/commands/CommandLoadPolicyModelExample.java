package lrbacTools.guiEditor.commands.commands;

import lrbac.LrbacFactory;
import lrbac.Policy;
import lrbacTools.generator.PolicyGenerator;
import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandLoadPolicyModelExample extends Command{
	
	public CommandLoadPolicyModelExample(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		Policy policy = LrbacFactory.eINSTANCE.createPolicy();
		PolicyGenerator pg = new PolicyGenerator(getEditor().getPolicy());
		pg.generate(3, 3, 3, 3, 3, 3, false, false, false, false, false, false);
		getEditor().setPolicy(policy);
		getEditor().graphMonitor.update();
	}
}