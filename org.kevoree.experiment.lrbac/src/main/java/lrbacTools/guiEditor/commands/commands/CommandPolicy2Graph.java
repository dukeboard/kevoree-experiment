package lrbacTools.guiEditor.commands.commands;

import grapho.GraphO;

import java.io.File;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.transformations.GraphOpolicy2DotFile;
import lrbacTools.transformations.Policy2Graph;
import utils.graphStructure.dotThings.DotDisplayer;

public class CommandPolicy2Graph  extends Command{
	
	public CommandPolicy2Graph(RbacTextualEditor editor, String id){
		super(editor, id);
	}
	
	@Override
	public void execute() {
		Policy2Graph transfo2Graph = new Policy2Graph(getEditor().getPolicy());
		GraphO g = transfo2Graph.transformation();
		GraphOpolicy2DotFile transfo2Dot = new GraphOpolicy2DotFile(g);
		File f = transfo2Dot.transformation("policyGraph.dot");
		DotDisplayer.displayPdfFile(f);
	}
}
