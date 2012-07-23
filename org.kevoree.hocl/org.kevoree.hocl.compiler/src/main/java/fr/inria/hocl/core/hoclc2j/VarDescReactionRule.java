package fr.inria.hocl.core.hoclc2j;


import java.util.*;

public class VarDescReactionRule implements VarDesc {

	String ident;

	private final String reactionName;

	private final String ruleName;

	private int nbOccurrences; // cf varDesc reordering

	String shot;

	List<VarDesc> arguments;

	private boolean readOnly;


	// pattern "reactionName = ident" that matches a rule
	// pattern for the rule ruleName
	public VarDescReactionRule( String ident, String reactionName, String ruleName ) {
		this.ident = ident;
		this.reactionName = reactionName;
		this.ruleName = ruleName;
		arguments = new LinkedList<VarDesc>();
		readOnly = false;
	}


	public String toString() {
		return "VarDesc of " + ident;
	}


	/*
	 * public HoclType getType(){ return HoclType.REACTION_RULE; }
	 */

	public VarDesc lookUp( String ident ) {
		VarDesc res;
		res = null;
		if( this.ident.equals( ident ) ) {
			res = this;
		}
		return res;
	}


	public boolean equals( VarDesc varDesc ) {
		boolean equals = false;

		if( varDesc instanceof VarDescReactionRule) {
			equals = (((VarDescReactionRule) varDesc).ident.equals(ident));
		}
		return equals;
	}


	public String generateNew() {
		String s, args = "";
		Set<String> arguments = getArguments();
		for (String argument : arguments) {
			args = args + ", " + argument;
		}
		if( args.length() > 0 ) {
			args = args.substring( 2 );
		}
		s = "new " + CodeGeneratorHelper.upperFirstLetter( reactionName ) + "(" + args + ")";
		return s;
	}


	public String generatePatClass( Pattern allDesc, Set<String> vars,
			String permutation ) {
		return ""; // cf fr.inria.hocl.core.hocli.PatName
	}


	public String generateNewPat() {
		return "new IteratorForRule(\"" + reactionName + "\","
				+ CodeGeneratorHelper.upperFirstLetter( ruleName ) + ".this)";
	}


	public String generateVarDecl() {
		return "ReactionRule " + ident;
	}


	public String generateIdent() {
		return ident;
	}


	public String generateDeclarations( String ident, Pattern allDesc,
			String atomIterator ) {
		String decl;
		if( this.ident.equals( ident ) ) {
			decl = ident + " = (ReactionRule) ((IteratorForRule)" + atomIterator
					+ ").getAtom();";
		} else {
			decl = "";
		}
		return decl;
	}


	public void applyCondOfBool() {
		// nothing to do: cf fr.inria.hocl.core.hocli.PatName.conditionSatisfied
		// condition = ident + ".equals.(\"" + reactionName + "\")"
	}


	public void pushCond( ReactionCondition decompCond ) {
		// nothing to do: default condition (equality on names)
	}


	public Set<String> getVars() {
		Set<String> s = new HashSet<String>();
		s.add( ident );
		Iterator<VarDesc> it_arg = arguments.iterator();
		VarDesc varDesc;
		while( it_arg.hasNext() ) {
			varDesc = it_arg.next();
			s.addAll( varDesc.getVars() );
		}
		return s;
	}


	public Set<String> getJokers() {
		return new HashSet<String>();
	}


	public Set<String> getArguments() {
		Set<String> s = new HashSet<String>();
		Iterator<VarDesc> it_arg = arguments.iterator();
		VarDesc varDesc;
		while( it_arg.hasNext() ) {
			varDesc = it_arg.next();
			s.addAll( varDesc.getVars() );
		}
		return s;
	}


	public void incrNbOcc() {
		nbOccurrences++;
	}


	public int getNbOcc() {
		return nbOccurrences;
	}


	public void applyCond( Set<String> assignedVars, ReactionCondition decompCond ) {
		// nothing to do
	}


	public String generateClassDecl( Pattern patDesc,
			ReactionCondition decompCond, Set<String> varsInRes, String resMol,
			String result, int sizeResult ) {
		String s, s2;
		Set<String> assignedVars = new HashSet<String>();
		String attributs = "";
		Iterator<VarDesc> it_vd = arguments.iterator();
		VarDesc varDesc;
		while( it_vd.hasNext() ) {
			varDesc = it_vd.next();
			assignedVars.addAll( varDesc.getVars() );
			assignedVars.addAll( varDesc.getJokers() );
			attributs = attributs + varDesc.generateVarDecl() + ";\n";
		}
		// patDesc.applyCondOpt(assignedVars, decompCond);
		s = "class " + CodeGeneratorHelper.upperFirstLetter( reactionName )
				+ " extends ReactionRule implements Serializable {\n\n"
				+ CodeGeneratorHelper.indentCode( attributs ) + "\n"
				+ CodeGeneratorHelper.indentCode( generateConstructor( patDesc, sizeResult ) )
				+ "\n";
		// System.out.println("Variables in result of " + reactionName + " = " +
		// varsInRes);
		s2 = ""; // patDesc.generateDeclarations(varsInRes, patDesc,"permutation");
							// // ,0
		s = s + "\tprotected Molecule computeResult(){\n"
				+ ( s2.length() > 0 ? CodeGeneratorHelper.indentCode( 2, s2 ) + "\n" : "" )
				+ "\t\tMolecule " + resMol + " = new Molecule();\n"
				+ ( result.length() > 0 ? CodeGeneratorHelper.indentCode( 2, result ) + "\n" : "" )
				+ "\t\treturn " + resMol + ";\n" + "\t}\n";
		s = s + "\n" + "} // class " + CodeGeneratorHelper.upperFirstLetter( reactionName );
		return s;
	}


	private String generateConstructor( Pattern patDesc, int sizeResult ) {
		String s, args = "", initArgs = "", trope = "";
		if( arguments.size() > 0 ) {
			Iterator<VarDesc> it_arg = arguments.iterator();
			VarDesc varDesc;
			while( it_arg.hasNext() ) {
				varDesc = it_arg.next();
				args = args + ", final " + varDesc.generateVarDecl();
				initArgs = initArgs + "this." + varDesc.generateIdent() + " = "
						+ varDesc.generateIdent() + ";\n";
			}
			args = args.substring( 2 );
		}
		/*
		 * if(patDesc.nbAtoms < sizeResult){ trope = "EXPANSER"; } else if
		 * (patDesc.nbAtoms > sizeResult){ trope = "REDUCER"; } else { trope =
		 * "OPTIMIZER"; }
		 */
		s = "public " + CodeGeneratorHelper.upperFirstLetter( reactionName ) + "(" + args
				+ "){\n" + "\tsuper(\"" + reactionName + "\", Shot." + shot + ");\n"
				+ "\tsetTrope(Trope." + trope + ");\n" + CodeGeneratorHelper.indentCode( initArgs )
				+
				// Hoclc2j.indentCode(patDesc.generatePatternDeclaration("permutation",
				// patDesc, 0)) + "\n" +
				"}";
		return s;
	}


	public void setReadOnly() {
		readOnly = true;
	}


	public boolean isReadOnly() {
		return readOnly;
	}

} // class VarDescReactionRule
