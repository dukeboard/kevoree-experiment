package rbacTextualEditor.editor.commands.commandsPolicy;

import grapho.GraphO;
import java.io.File;
import rbac.Policy;
import rbacTextualEditor.editor.commands.CommandRbac;
import rbacTextualEditor.editor.commands.ICommandRbac;
import transformations.GraphOpolicy2DotFile;
import transformations.Policy2Graph;
import utils.graphStructure.dotThings.DotDisplayer;

public class CommandPolicy2Graph  extends CommandRbac implements ICommandRbac{
	
	public CommandPolicy2Graph(Policy policy,String id){
		setPolicy(policy);
		setName(id);
		setDescription(id);
	}
	
	@Override
	public void execute() {
		Policy2Graph transfo2Graph = new Policy2Graph(getPolicy());
		GraphO g = transfo2Graph.transformation();
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("graphs/ola.dot");
		DotDisplayer.displayPdfFile(f);
	}
}
