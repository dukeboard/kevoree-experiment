package lrbacTools.guiEditor.commands.commandsEditor;

import lrbac.LrbacFactory;
import lrbac.Policy;
import lrbacTools.generator.PolicyGenerator;
import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;
import lrbacTools.transformations.Policy2PolicyScript;

public class CommandLoadPolicyScriptExample extends CommandRbac implements ICommandRbac{
	private TextPaneEditor textPaneEditor;
	
	public CommandLoadPolicyScriptExample(TextPaneEditor editor,String id){
		textPaneEditor=editor;
		setName(id);
		setDescription(id);
	}
	
	@Override
	public void execute() {
		Policy p = LrbacFactory.eINSTANCE.createPolicy();
		PolicyGenerator pg = new PolicyGenerator(p);
		pg.generate(1, 1, 1, 1, 1, 1, false, false, false, false, false, false);
		Policy2PolicyScript transfo2text = new Policy2PolicyScript(p);
		textPaneEditor.setText(transfo2text.transformation());
	}
}
