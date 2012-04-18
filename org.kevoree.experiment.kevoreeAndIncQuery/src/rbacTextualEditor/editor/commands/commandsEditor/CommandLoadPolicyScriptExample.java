package rbacTextualEditor.editor.commands.commandsEditor;

import rbac.Policy;
import rbac.RbacFactory;
import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import rbacTextualEditor.editor.graphicComponents.TextPaneEditor;
import rbacTools.generator.PolicyGenerator;
import transformations.Policy2PolicyScript;

public class CommandLoadPolicyScriptExample extends CommandRbac implements ICommandRbac{
	private TextPaneEditor textPaneEditor;
	
	public CommandLoadPolicyScriptExample(TextPaneEditor editor,String id){
		textPaneEditor=editor;
		setName(id);
		setDescription(id);
	}
	
	@Override
	public void execute() {
		Policy p = RbacFactory.eINSTANCE.createPolicy();
		PolicyGenerator pg = new PolicyGenerator(p);
		pg.generate(3, 3, 3, 3, 3, 3, false, false, false, false, false, false);
		Policy2PolicyScript transfo2text = new Policy2PolicyScript(p);
		textPaneEditor.setText(transfo2text.transfo());
	}
}
