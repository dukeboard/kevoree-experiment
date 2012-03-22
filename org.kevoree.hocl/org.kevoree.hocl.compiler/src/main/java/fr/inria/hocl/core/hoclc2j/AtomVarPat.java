package fr.inria.hocl.core.hoclc2j;


import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Descriptor of a variable
 * 
 */
public class AtomVarPat implements AtomPattern {

	final String name;

	String javaType;

	private Access access; // rewrite or read-only

	public External reactionCondition; // FIXME ReactionCondition

	private ReactionRule reactionRule;

	private int copyCounter;


	public AtomVarPat( String name, String javaType, Pattern pattern )
			throws ParseException {
		access = Access.REWRITE;
		reactionCondition = new External();
		copyCounter = 0;
		Symbols symb = pattern.lookUp( name );
		if( symb != null ) {
			// non linear pattern
			if( !( symb instanceof AtomVarPat) ) {
				throw new ParseException( "Identifier " + name
						+ " already used for another type of atom." );
			} else {
				AtomVarPat atomVarPat = (AtomVarPat) symb;
				this.name = atomVarPat.generateCopyName();
				this.javaType = atomVarPat.javaType;
				reactionCondition.javaExpression = this.name + ".equals("
						+ atomVarPat.name + ")";
				reactionCondition.addVar( this );
				reactionCondition.addVar( atomVarPat );
			}
		} else {
			this.name = name;
			this.javaType = primitiveToObject( javaType );
			reactionCondition.javaExpression = "true"; // reaction condition true by
																									// default
		}
	}


	public void setJavaType( String javaType ) {
		this.javaType = javaType;
	}


	public AtomVarPat lookUp( String ident ) {
		return ( ident.equals( name ) ) ? this : null;
	}


	private String generateCopyName() {
		String copyName = "_HOCL_" + name + "_copy" + copyCounter;
		copyCounter++;
		return copyName;
	}


	public String toString() {
		return name + ( javaType.length() > 0 ? "::" + javaType : "" );
	}


	public String generateDeclaration( Symbols symbols, String iterator ) {
		String s = "";
		if( symbols == this ) {
			s = javaType + " " + name + " = (" + javaType + ")((IteratorForExternal)"
					+ iterator + ").getObject();";
		}
		return s;
	}


	public boolean isExternal() {
		return true;
	}


	public void setReactionCondition( ReactionCondition reactCond ) {
		reactionCondition = reactCond;
	}


	private static String primitiveToObject( String primType ) {
		String res = primType;
		if( primType.equals( "int" ) ) {
			res = "Integer";
		} else if( primType.equals( "long" ) ) {
			res = "Long";
		} else if( primType.equals( "boolean" ) ) {
			res = "Boolean";
		} else if( primType.equals( "float" ) ) {
			res = "Float";
		} else if( primType.equals( "double" ) ) {
			res = "Double";
		}
		return res;
	}


	public Set<Symbols> getVariables() {
		Set<Symbols> vars = new HashSet<Symbols>();
		vars.add( this );
		return vars;
	}


	public String generatePatClass( String permutation, String reactionRuleName,
			SymbolsTable symbolsTable ) {
		String s, s2;
		Set<Symbols> declVars = new HashSet<Symbols>();

		declVars.addAll( reactionCondition.getFreeVars() );
		// remove arguments of rule
		Set<Symbols> arguments = reactionRule.getArguments();
		Iterator<Symbols> itVars = declVars.iterator();
		Symbols symb;
		while( itVars.hasNext() ) {
			symb = itVars.next();
			if( arguments.contains( symb ) ) {
				itVars.remove();
			}
		}
		s2 = generateVarDeclarations( declVars, "permutation", symbolsTable );
		s = "class AtomIterator_" + name + " extends IteratorForExternal {\n"
				+ "\tpublic AtomIterator_" + name + "(){\n" + "\t\taccess = "
				+ ( access == Access.READ_ONLY ? "Access.READ_ONLY" : "Access.REWRITE" )
				+ ";\n" + "\t}\n" + "\t@Override\n"
				+ "\tpublic boolean conditionSatisfied() {\n" + "\t\tAtom atom;\n"
				+ "\t\tboolean satisfied;\n" + "\t\tatom = iterator.getElement();\n"
				+ "\t\tsatisfied = false;\n"
				+ "\t\tif (atom instanceof ExternalObject\n"
				+ "\t\t  && ((ExternalObject)atom).getObject() instanceof " + javaType
				+ ") {\n"
				+ ( s2.length() > 0 ? CodeGeneratorHelper.indentCode( 3, s2 ) + "\n" : "" )
				+ "\t\t\tsatisfied = " + reactionCondition.javaExpression + ";\n"
				+ "\t\t}\n" + // end of if
				"\t\treturn satisfied;\n" + "\t}\n" + // end of conditionSatisfied
				"\n} // end of class AtomIterator_" + name;
		return s;
	}


	private static String generateVarDeclarations( Set<Symbols> declVars,
			String permutation, SymbolsTable symbolsTable ) {
		String s = "";
		Iterator<Symbols> it = declVars.iterator();
		Symbols variable;
		while( it.hasNext() ) {
			variable = it.next();
			s = s + "\n" + symbolsTable.generateDeclaration( variable, permutation );
		}
		return s;
	}


	public String generatePatternNew( String reactionRuleName ) {
		return "new AtomIterator_" + name + "()";
	}


	public boolean setLastReactionCondition( External cond ) {
		if( !( reactionCondition.javaExpression.equals( "true" ) ) ) {
			cond.javaExpression = reactionCondition.javaExpression + " && "
					+ cond.javaExpression;
			cond.addAllVars( reactionCondition );
		}
		reactionCondition = cond;
		return true;
	}


	public void setReactionRule( ReactionRule reactionRule ) {
		this.reactionRule = reactionRule;
	}


	public String generateIdent() {
		return name;
	}


	public String generateVariableDecl() {
		return javaType + " " + name;
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
		if( atom instanceof External ) {
			External ext = ( External ) atom;
			equals = ext.javaExpression.equals( name );
		}
		return equals;
	}

}
