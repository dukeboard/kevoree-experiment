package lrbacTools.guiEditor.commands.commandsPolicy;

import lrbac.LrbacFactory;
import lrbac.Policy;
import lrbacTools.generator.PolicyGenerator;
import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandLoadPolicyModelExample extends CommandRbac implements ICommandRbac{
	private TextPaneEditor textPaneEditor;
	
	public CommandLoadPolicyModelExample(TextPaneEditor editor,String id){
		textPaneEditor=editor;
		setName(id);
		setDescription(id);
	}
	
	@Override
	public void execute() {
		Policy policy = LrbacFactory.eINSTANCE.createPolicy();
		PolicyGenerator pg = new PolicyGenerator(textPaneEditor.getPolicy());
		pg.generate(3, 3, 3, 3, 3, 3, false, false, false, false, false, false);
		textPaneEditor.setPolicy(policy);
		textPaneEditor.getGraphMonitor().update();
		textPaneEditor.getGraphMonitor().setVisible(true);
	}
}