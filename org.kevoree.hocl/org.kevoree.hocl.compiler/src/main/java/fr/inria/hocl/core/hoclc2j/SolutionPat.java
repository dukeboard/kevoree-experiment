package fr.inria.hocl.core.hoclc2j;


import java.util.Iterator;
import java.util.Set;

public class SolutionPat implements AtomPattern {

	private Pattern pattern;

	private int nestLevel;

	private static int iterSolVarCounter = 0;

	private static int iterSolClassCounter = 0;

	private final String iterSolClass;

	private Access access;


	public SolutionPat( Pattern pat, int nestLevel ) {
		pattern = pat;
		this.nestLevel = nestLevel;
		iterSolClass = "IteratorSolution" + iterSolClassCounter;
		iterSolClassCounter++;
		access = Access.REWRITE;
	}


	public Symbols lookUp( String ident ) {
		return pattern.lookUp( ident );
	}


	public String generateDeclaration( Symbols symbols, String iterator ) {
		String s = "";
		String iterSolVar = "_HOCL_iteratorSolution" + iterSolVarCounter;
		iterSolVarCounter++;
		Set<Symbols> vars = pattern.getVariables();
		if( vars.contains( symbols ) ) {
			s = "IteratorForSolution "
					+ iterSolVar
					+ " = "
					+ "(IteratorForSolution)"
					+ iterator
					+ ";\n"
					+ pattern.generateDeclaration( symbols, iterSolVar
							+ ".getSubPermutation()" );
		}
		return s;
	}


	public Set<Symbols> getVariables() {
		return pattern.getVariables();
	}


	public String generatePatClass( String permutation, String reactionRuleName,
			SymbolsTable symbolsTable ) {
		String s;

		s = "class "
				+ iterSolClass
				+ " extends IteratorForSolution {\n"
				+ "\tprotected Permutation makeSubPermutation(){\n"
				+ "\t\tPermutation perm;\n"
				+ CodeGeneratorHelper.indentCode( 2, pattern.generatePatternDeclaration( "perm",
						symbolsTable, reactionRuleName ) ) + "\n" + "\t\treturn perm;"
				+ "\n" + "\t}\n\n" + "} // class " + iterSolClass;
		return s;
	}


	public String generatePatternNew( String reactionRuleName ) {
		String reactionRule = "this";
		for( int i = 0; i < nestLevel; i++ ) {
			reactionRule += ".reactionRule";
		}
		return "new " + iterSolClass + "()";
	}


	public boolean setLastReactionCondition( External cond ) {
		return pattern.setLastReactionCondition( cond );
	}


	public void setReactionRule( ReactionRule reactionRule ) {
		pattern.setReactionRule( reactionRule );
	}


	public String toString() {
		return "<" + pattern.toString() + ">";
	}


	public String generateIdent() {
		return ""; // no ident
	}


	public String generateVariableDecl() {
		return ""; // no ident => no declaration
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
		if( atom instanceof Solution) {
			equals = pattern.equalsMolecule( ( (Solution) atom ).getContents() );
		}
		return equals;
	}


	public Access getAccess() {
		return this.access;
	}

}
