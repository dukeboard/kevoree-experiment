package lrbacTools.guiEditor.commands.commands;

import lrbacTools.guiEditor.commands.Command;
import lrbacTools.guiEditor.graphicComponents.RbacTextualEditor;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public abstract class CommandEditor extends Command{
	
	public CommandEditor(RbacTextualEditor editor, String id){
		super(editor, id);
	}

	/**
	 * @return the textPaneEditor
	 */
	public TextPaneEditor getTextPaneEditor() {
		return getEditor().textPaneEditor;
	}
	
}
