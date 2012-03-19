package fr.inria.hocl.core.hoclc2j;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class ReactionRule implements Atom {

	private static int reactionRuleCounter = 0;

	private String ruleName; // name of the reaction rule

	public String reactionRuleClassName; // name of the class generated

	private enum Shot {
		ONE_SHOT, N_SHOT
	}

	private Shot shot;

	private Pattern pattern; // description of the pattern

	public Molecule result; // description of the result

	public External condition; // description of the reaction condition // FIXME:
															// ReactionCondition

	private SymbolsTable symbolsTable;

	private Set<Symbols> arguments;

	private Access access;


	public ReactionRule( String ruleName ) {
		setRuleName( ruleName );
		shot = null;
		access = Access.REWRITE;
	}


	public String getReactionRuleClassName() {
		return this.reactionRuleClassName;
	}


	public void setSymbolsTable( SymbolsTable symbolsTable ) {
		this.symbolsTable = (SymbolsTable) symbolsTable.clone();
	}


	public void setRuleName( String newName ) {
		ruleName = newName;
		reactionRuleClassName = CodeGeneratorHelper.upperFirstLetter(ruleName);
	}


	public String getRuleName() {
		return ruleName;
	}


	public void setOneShot() {
		shot = Shot.ONE_SHOT;
	}


	public void setNShot() {
		shot = Shot.N_SHOT;
	}


	public String generateClass() {
		String s, attributs; // , s2;
		attributs = "";
		Iterator<Symbols> it_vd = getArguments().iterator();
		Symbols symb;
		while( it_vd.hasNext() ) {
			symb = it_vd.next();
			attributs = attributs + "private final " + symb.generateVariableDecl()
					+ ";\n";
		}
		s = "class "
				+ reactionRuleClassName
				+ " extends ReactionRule implements Serializable {\n\n"
				+ CodeGeneratorHelper.indentCode(attributs)
				+ "\n"
				+ CodeGeneratorHelper.indentCode(genConstructor(symbolsTable))
				+ "\n"
				// System.out.println("Variables in result of " + reactionName + " = " +
				// varsInRes);
				// s2 = patDesc.generateDeclarations(varsInRes, patDesc,"permutation");
				// // ,0
				+ "\n"
				+ "\tpublic void addType(String s){}\n\n"
				+ "\t// compute result of the rule "
				+ ruleName
				+ "\n"
				+ "\tprotected Molecule computeResult(){\n"
				+ "\t\tExternalObject object;\n"
				+ "\t\tReactionRule rule;\n"
				+ "\t\tString[] string;\n"
				+ "\t\tTuple tuple;\n"
				// (s2.length() > 0 ? Hoclc2j.indentCode(2,s2) + "\n" : "") +
				// "\t\tMolecule " + result + " = new Molecule();\n" +
				// (result.length() > 0 ? Hoclc2j.indentCode(2,result) + "\n" : "" ) +
				+ CodeGeneratorHelper.indentCode(2, result.generateAllVariablesDeclaration(this
				.getArguments())) + "\n"
				+ CodeGeneratorHelper.indentCode(2, result.generateDeclarationInit()) + "\n"
				+ "\t\treturn " + result.generateReference() + ";\n" + "\t}\n" + "\n"
				+ "} // end of class " + reactionRuleClassName + "\n";
		return s;
	}


	private String genConstructor( SymbolsTable symbolsTable ) {
		String s, args = "", initArgs = "", trope;
		Set<Symbols> arguments = this.getArguments();
		if( arguments.size() > 0 ) {
			Iterator<Symbols> it_arg = arguments.iterator();
			Symbols symb;
			while( it_arg.hasNext() ) {
				symb = it_arg.next();
				args = args + ", final " + symb.generateVariableDecl();
				initArgs = initArgs + "this." + symb.generateIdent() + " = "
						+ symb.generateIdent() + ";\n";
			}
			args = args.substring( 2 );
		}
		if( pattern.length() < result.size() ) {
			trope = "EXPANSER";
		} else if( pattern.length() > result.size() ) {
			trope = "REDUCER";
		} else {
			trope = "OPTIMIZER";
		}
		s = "public "
				+ reactionRuleClassName
				+ "("
				+ args
				+ "){\n"
				+ "\tsuper(\""
				+ ruleName
				+ "\", Shot."
				+ shot
				+ ");\n"
				+ "\tsetTrope(Trope."
				+ trope
				+ ");\n"
				+ CodeGeneratorHelper.indentCode(initArgs)
				+ CodeGeneratorHelper.indentCode(pattern.generatePatternDeclaration(
				"permutation", symbolsTable, ruleName)) + "\n" + "}";
		return s;
	}


	public String generateDeclarationInit( int position, SymbolsTable symbolsTable ) {
		if( ruleName.length() == 0 ) {
			reactionRuleCounter++;
			ruleName = "_HOCL_RR" + reactionRuleCounter;
			reactionRuleClassName = ruleName;
		}
		return generateClass(); // for unnamed rule
	}


	public String generateReference() {
		String arguments = "";
		Set<Symbols> args = this.getArguments();
		Iterator<Symbols> itArgs = args.iterator();
		Symbols symb;
		while( itArgs.hasNext() ) {
			symb = itArgs.next();
			arguments = arguments + ", " + symb.generateIdent();
		}
		if( arguments.length() > 2 ) {
			arguments = arguments.substring( 2 );
		}
		return "new " + reactionRuleClassName + "(" + arguments + ")";
	}


	public Set<Symbols> getFreeVars() {
		Set<Symbols> freeVars;
		freeVars = result.getFreeVars();
		if( condition != null ) {
			// when there is a reaction condition
			freeVars.addAll( condition.getFreeVars() );
		}
		freeVars.removeAll( pattern.getVariables() );
		return freeVars;
	}


	public Set<Symbols> getArguments() {
		if( arguments == null ) {
			Set<Symbols> patternVars;
			arguments = result.getFreeVars();
			if( condition != null ) {
				arguments.addAll( condition.getFreeVars() );
			}
			patternVars = pattern.getVariables();
			// remove variables from pattern and reaction rule names
			Iterator<Symbols> itSymb = arguments.iterator();
			Symbols symb;
			while( itSymb.hasNext() ) {
				symb = itSymb.next();
				if( symb instanceof ReactionRulePat || patternVars.contains( symb ) ) {
					itSymb.remove();
				}
			}
		}
		return arguments;
	}


	public void setPattern( Pattern pattern ) {
		this.pattern = pattern;
		pattern.setReactionRule( this );
	}


	public void findReadOnlyReactives() {
		pattern.setReadOnlyReactives( result );
		// remove read only atoms from the result
		Iterator<Atom> it = result.iterator();
		Atom atom;
		while( it.hasNext() ) {
			atom = it.next();
			if( atom.isReadOnly() ) {
				it.remove();
			}
		}
	}


	public void setReadOnly() {
		access = Access.READ_ONLY;
	}


	public boolean isReadOnly() {
		return access == Access.READ_ONLY;
	}


	public String getType() {
		// TODO Auto-generated method stub
		return "reactionRule";
	}


	public LinkedList<String> getElementTypes() {
		LinkedList<String> s = new LinkedList<String>();

		s.add( this.getRuleName() + "-Reaction Rule" );

		return s;
	}

}
