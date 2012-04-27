package lrbacTools.guiEditor.commands.commandsPolicy;

import grapho.GraphO;
import graphoTools.transformations.GraphOpolicy2DotFile;

import java.io.File;

import lrbac.Policy;
import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.transformations.Policy2Graph;
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
