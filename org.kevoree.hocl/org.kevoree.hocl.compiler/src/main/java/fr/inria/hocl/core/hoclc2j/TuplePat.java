package fr.inria.hocl.core.hoclc2j;


import java.util.*;

public class TuplePat implements AtomPattern {

	private List<AtomPattern> contents;

	// private int nestLevel;
	private static int tupleIterCounter;

	private static int iterArrayCounter;

	private String patVar;

	private Access access;


	public TuplePat( int nestLevel ) {
		// this.nestLevel = nestLevel;
		contents = new LinkedList<AtomPattern>();
		tupleIterCounter = 0;
		iterArrayCounter = 0;
		access = Access.REWRITE;
	}


	public Symbols lookUp( String ident ) {
		Symbols symb = null;
		Iterator<AtomPattern> it = contents.iterator();
		while( it.hasNext() && symb == null ) {
			symb = it.next().lookUp( ident );
		}
		return symb;
	}


	public void add( AtomPattern atomPat ) {
		contents.add( atomPat );
	}


	public String generateDeclaration( Symbols variable, String iterator ) {
		String s = "";

		Iterator<AtomPattern> it = contents.iterator();
		AtomPattern atomPattern;
		int position = 0;
		while( it.hasNext() ) {
			atomPattern = it.next();
			if( atomPattern.getVariables().contains( variable ) ) {
				String iterTuple = "_HOCL_tupleAtomIterator" + tupleIterCounter;
				tupleIterCounter++;
				s = "IteratorForTuple "
						+ iterTuple
						+ " = (IteratorForTuple)"
						+ iterator
						+ ";\n"
						+ atomPattern.generateDeclaration( variable, iterTuple
								+ ".getAtomIterator(" + position + ")" );
			}
			position++;
		}

		return s;
	}


	public Set<Symbols> getVariables() {
		Set<Symbols> vars = new HashSet<Symbols>();
		Iterator<AtomPattern> it = contents.iterator();
		AtomPattern atomPattern;
		while( it.hasNext() ) {
			atomPattern = it.next();
			vars.addAll( atomPattern.getVariables() );
		}
		return vars;
	}


	public String generatePatClass( String permutation, String reactionRuleName,
			SymbolsTable symbolsTable ) {
		Iterator<AtomPattern> it = contents.iterator();
		AtomPattern atomPattern;
		int position = -1;
		// String patVar;
		// Set<AtomPattern> assignedVars = new HashSet<AtomPattern>();

		patVar = "_HOCL_atomIteratorArrayTuple" + iterArrayCounter;
		iterArrayCounter++;
		String s = "AtomIterator[] " + patVar + ";\n" + patVar + " = new AtomIterator["
				+ contents.size() + "];\n";
		while( it.hasNext() ) {
			atomPattern = it.next();
			if( !( atomPattern instanceof MoleculeVarPat ) ) {
				position++;
				s = s
						+ "{\n"
						+ CodeGeneratorHelper.indentCode( atomPattern.generatePatClass( "permutation",
								reactionRuleName, symbolsTable ) ) + "\n" + "\t" + patVar + "["
						+ position + "] = "
						+ atomPattern.generatePatternNew( reactionRuleName ) + ";\n" + "}";
			}
			if( it.hasNext() ) {
				s = s + "\n";
			}
		}
		return s;
	}


	public String generatePatternNew( String reactionRuleName ) {
		return "new IteratorForTuple(" + patVar + ", "
				+ CodeGeneratorHelper.upperFirstLetter( reactionRuleName ) + ".this)";
	}


	public boolean setLastReactionCondition( External cond ) {
		boolean done = false;
		ListIterator<AtomPattern> it = contents.listIterator();
		AtomPattern atomPattern = null;
		while( it.hasNext() ) {
			atomPattern = it.next();
		}
		while( !done && it.hasPrevious() ) {
			if (atomPattern != null) {
				done = atomPattern.setLastReactionCondition( cond );
			}
			atomPattern = it.previous();
		}
		return done;
	}


	public void setReactionRule( ReactionRule reactionRule ) {
		Iterator<AtomPattern> it = contents.iterator();
		AtomPattern atomPattern;
		while( it.hasNext() ) {
			atomPattern = it.next();
			atomPattern.setReactionRule( reactionRule );
		}
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
		if( atom instanceof Tuple ) {
			Tuple tuple = ( Tuple ) atom;
			if( tuple.size() == contents.size() ) {
				Iterator<AtomPattern> itPat = contents.iterator();
				Iterator<Atom> itTuple = tuple.iterator();
				equals = true;
				while( itPat.hasNext() && equals ) {
					equals = itPat.next().equalsAtom( itTuple.next() );
				}
			}
		}
		return equals;
	}


	public Access getAccess() {
		return this.access;
	}

}
