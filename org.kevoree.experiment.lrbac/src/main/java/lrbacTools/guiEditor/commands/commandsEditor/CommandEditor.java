package lrbacTools.guiEditor.commands.commandsEditor;

import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public abstract class CommandEditor extends CommandRbac implements ICommandRbac{

	private TextPaneEditor textPaneEditor;
	
	public CommandEditor(TextPaneEditor editor){
		textPaneEditor = editor;
	}

	/**
	 * @return the textPaneEditor
	 */
	public TextPaneEditor getTextPaneEditor() {
		return textPaneEditor;
	}

	/**
	 * @param textPaneEditor the textPaneEditor to set
	 */
	public void setTextPaneEditor(TextPaneEditor textPaneEditor) {
		this.textPaneEditor = textPaneEditor;
	}
	
	
}
