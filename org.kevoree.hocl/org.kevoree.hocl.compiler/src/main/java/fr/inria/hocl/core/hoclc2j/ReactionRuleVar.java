package fr.inria.hocl.core.hoclc2j;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class ReactionRuleVar implements Atom {

	public final String varName;

	private ReactionRulePat reactRulePat;

	private boolean isRec;

	private Access access;


	public String getVarName() {
		return this.varName;
	}


	public ReactionRuleVar( ReactionRulePat reactRulePat, boolean isRec ) {
		varName = reactRulePat.varName;
		this.reactRulePat = reactRulePat;
		this.isRec = isRec;
		access = Access.REWRITE;
	}


	public String generateDeclarationInit( int position, SymbolsTable symbolsTable ) {

		return "";
	}


	public String generateReference() {
		return reactRulePat.generateReference();
	}


	public Set<Symbols> getFreeVars() {
		Set<Symbols> freeVars = new HashSet<Symbols>();
		freeVars.add( reactRulePat );
		if( !isRec ) {
			freeVars.addAll( reactRulePat.reactionRule.getFreeVars() );
		}
		return freeVars;
	}


	public String toString() {
		return varName;
	}


	public void findReadOnlyReactives() {
		// nothing to do
	}


	public void setReadOnly() {
		access = Access.READ_ONLY;
	}


	public boolean isReadOnly() {
		return access == Access.READ_ONLY;
	}


	public String getType() {
		// TODO Auto-generated method stub
		return "reactionRuleVar";
	}


	public LinkedList<String> getElementTypes() {
		LinkedList<String> s = new LinkedList<String>();

		s.add( this.getVarName() + "-Reaction Rule" );

		return s;
	}

}
