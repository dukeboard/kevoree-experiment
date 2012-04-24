package graphoTools.guiEditor.commands;
import graphoTools.guiEditor.graphicComponents.GraphOTextualEditor;
import grapho.*;
import graphoTools.generator.Generator;
import graphoTools.transformations.GraphO2GraphOScript;
import graphoTools.transformations.GraphOScript2GraphO;
import javax.swing.Action;
public class CommandDisplayGraph extends Command{
	public CommandDisplayGraph(GraphOTextualEditor e, String nme, String desc) {
		super(e, nme, desc);
	}
	@Override
	public void execute() {
			editor.graphMonitor.update();
			editor.graphMonitor.setVisible(true);
	}
}