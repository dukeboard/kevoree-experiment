package graphoTools.guiEditor.commands;
import graphoTools.guiEditor.graphicComponents.GraphOTextualEditor;
import grapho.*;
import graphoTools.generator.Generator;
public class CommandLoadModelExample extends Command{
	public CommandLoadModelExample(GraphOTextualEditor e, String nme, String desc) {
		super(e, nme, desc);
	}
	@Override
	public void execute() {
		GraphO grapho = GraphoFactory.eINSTANCE.createGraphO();
		Generator gen = new Generator(editor.getGraphO());
		gen.generateModelExample();
		editor.setGraphO(grapho);
	}
}