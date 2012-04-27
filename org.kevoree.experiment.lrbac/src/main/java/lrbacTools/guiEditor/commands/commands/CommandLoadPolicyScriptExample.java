package lrbacTools.guiEditor.commands.commands;

import lrbac.LrbacFactory;
import lrbac.Policy;
import lrbacTools.generator.PolicyGenerator;
import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.transformations.Policy2PolicyScript;

public class CommandLoadPolicyScriptExample extends Command{
	
	public CommandLoadPolicyScriptExample(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		Policy p = LrbacFactory.eINSTANCE.createPolicy();
		PolicyGenerator pg = new PolicyGenerator(p);
		pg.generate(1, 1, 1, 1, 1, 1, false, true, false, false, false, false);
		Policy2PolicyScript transfo2text = new Policy2PolicyScript(p);
		getEditor().textPaneEditor.setText(transfo2text.transformation());
	}
}
