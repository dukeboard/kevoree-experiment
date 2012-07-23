package rbacTextualEditor.editor.commands.commandsPolicy;

import rbac.Policy;
import rbac.RbacFactory;
import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;
import rbacTools.generator.PolicyGenerator;
import transformations.Policy2PolicyScript;

public class CommandLoadPolicyModelExample extends CommandRbac implements ICommandRbac{
	private TextPaneEditor textPaneEditor;
	
	public CommandLoadPolicyModelExample(TextPaneEditor editor,String id){
		textPaneEditor=editor;
		setName(id);
		setDescription(id);
	}
	
	@Override
	public void execute() {
		Policy policy = RbacFactory.eINSTANCE.createPolicy();
		PolicyGenerator pg = new PolicyGenerator(textPaneEditor.getPolicy());
		pg.generate(3, 3, 3, 3, 3, 3, false, false, false, false, false, false);
		textPaneEditor.setPolicy(policy);
		textPaneEditor.getGraphMonitor().update();
		textPaneEditor.getGraphMonitor().setVisible(true);
	}
}