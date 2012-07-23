package fr.inria.hocl.core.hoclc2j;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ReactionRulePat implements AtomPattern {

	public final String ruleName; // name of the reaction rule looked for

	public final String varName; // name of the corresponding variable

	public ReactionRule reactionRule;

	private Access access;


	public ReactionRulePat( String ruleName, String varName ) {
		this.ruleName = ruleName;
		this.varName = varName;
		access = Access.REWRITE;
	}


	public AtomPattern lookUp( String ident ) {
		return ( ident.equals( ruleName ) || ident.equals( varName ) ) ? this
				: null;
	}


	public void setReactionRule( ReactionRule reactRule ) {
		reactionRule = reactRule;
	}


	public String toString() {
		return ruleName + "=" + varName;
	}


	public String generateDeclarationInit() {
		return "/* reaction rule pattern: no declaration no init */";
	}


	public String generateReference() {
		return reactionRule.generateReference();
	}


	public String generateDeclaration( Symbols symbols, String iterator ) {
		String s = "";
		if( symbols == this ) {
			s = reactionRule.generateClass();
		}
		return s;
	}


	public Set<Symbols> getVariables() {
		Set<Symbols> vars = new HashSet<Symbols>();
		vars.add( this );
		return vars;
	}


	public String generatePatClass( String permutation, String reactionRuleName,
			SymbolsTable symbolsTable ) {
		return "/* reaction rule pattern: no pattern class */";
	}


	public String generatePatternNew( String reactionRuleName ) {
		return "new IteratorForRule(\"" + ruleName + "\","
				+ CodeGeneratorHelper.upperFirstLetter(reactionRule.getRuleName()) + ".this)";
	}


	public boolean setLastReactionCondition( External cond ) {
		// nothin to do because there is no reaction condition on variable of
		// reaction rule
		return false;
	}


	public String generateIdent() {
		return varName;
	}


	public String generateVariableDecl() {
		return "ReactionRule " + varName;
	}


	public void checkReadOnly( Molecule molecule ) {
		Iterator<Atom> itMol = molecule.iterator();
		Atom atom;
		while( itMol.hasNext() ) {
			atom = itMol.next();
			if( this.equalsAtom( atom ) ) {
				access = Access.READ_ONLY;
				atom.setReadOnly();
			}
		}
	}


	public boolean equalsAtom( Atom atom ) {
		boolean equals = false;
		if( atom instanceof ReactionRuleVar) {
			equals = ( (ReactionRuleVar) atom ).varName.equals( varName );
		}
		return equals;
	}


	public Access getAccess() {
		return this.access;
	}

}
