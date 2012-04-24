package lrbacTools.guiEditor.commands.commandsEditor;

import java.lang.reflect.Method;

import javax.swing.Action;
import javax.swing.text.BadLocationException;

import lrbacTools.checker.PolicyChecker;
import lrbacTools.editor.PolicyEditor;
import lrbacTools.guiEditor.commands.CommandRbac;
import lrbacTools.guiEditor.commands.ICommandRbac;
import lrbacTools.guiEditor.graphicComponents.TextPaneEditor;

public class CommandCompletion extends CommandRbac implements ICommandRbac{
	private TextPaneEditor textPaneEditor;
	
	
	public CommandCompletion(TextPaneEditor editor, String id){
		textPaneEditor = editor;		
		setName(id);
		setDescription(id);
		putValue(Action.NAME, getName());
	}
	
	@Override
	public void execute() {
		//to insert the selected string into the document
		int pos =textPaneEditor.getCaretPosition() -1;
		String content = null;
		try {
			content = textPaneEditor.getText(0, pos + 1);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
		int w;
		for (w = pos; w >= 0; w--) {
			if (!Character.isLetter(content.charAt(w))) {
				break;
			}
		}
		String prefix = content.substring(w + 1).toLowerCase();
		try {	
			String toInsert = getName().substring(prefix.length(),getName().length());
			
			String[] args = null;
			for(Method m : PolicyEditor.class.getMethods()){
				if (m.getName().equals(getName())){
					if(!(m.getParameterTypes().length == 0)){
						args = new String[m.getParameterTypes().length];
					}
					int cpt = 0;
					for(Class c : m.getParameterTypes()){
						args[cpt]= c.getSimpleName();
						cpt = cpt+1;
					}
				}
			}
			toInsert = toInsert + "(";
			if(! (args == null)){
				for(int i = 0;i<args.length;i++){
					if(i == (args.length - 1)){
						toInsert = toInsert + args[i];
					}
					else
					{
						toInsert = toInsert + args[i]+",";
					}
				}
			}
			toInsert = toInsert + ");";
			
			textPaneEditor.getDocument().insertString(pos + 1, toInsert, null);
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}


}
