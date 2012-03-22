package fr.inria.hocl.core.hoclc2j;


import java.util.Set;

public class VarDescSolution implements VarDesc {

	private Pattern subPatternDesc;

	private int nbOccurrences; // cf varDesc reordering

	private final int nestLevel;

	private boolean readOnly;


	public VarDescSolution( Pattern subPatternDesc, int nestLevel ) {
		this.subPatternDesc = subPatternDesc;
		this.nestLevel = nestLevel;
		readOnly = false;
	}


	/*
	 * public HoclType getType(){ return HoclType.SOLUTION; }
	 */

	public VarDesc lookUp( String ident ) {
		return null; // subPatternDesc.lookUp(ident);
	}


	public boolean equals( VarDesc varDesc ) {
		boolean equals = false;
		if( varDesc instanceof VarDescSolution) {
			equals = subPatternDesc
					.equals( ( (VarDescSolution) varDesc ).subPatternDesc );
		}
		return equals;
	}


	public String generatePatClass( Pattern allDesc, Set<String> vars,
			String permutation ) {
		String s;
		String className = "IteratorSolution" + nestLevel;

		s = "class " + className + " extends IteratorForSolution {\n"
				+ "\tprotected Permutation makeSubPermutation(){\n"
				+ "\t\tPermutation perm;\n"
				// + Hoclc2j.indentCode(2,
				// subPatternDesc.generatePatternDeclaration("perm",
				// allDesc,nestLevel+1)) + "\n"
				+ "\t\treturn perm;" + "\n" + "\t}\n\n" + "} // class " + className;
		return s;
	}


	public String generateNewPat() { // int nestLevel){
		String reactionRule = "this";
		for( int i = 0; i < nestLevel; i++ ) {
			reactionRule += ".reactionRule";
		}
		return "new IteratorSolution" + nestLevel + "()";
	}


	public String generateVarDecl() {
		return "";
	}


	public String generateIdent() {
		return "";
	}


	public String generateDeclarations( String ident, Pattern allDesc,
			String atomIterator ) {
		String decl;
		String iteratorSolution = "_HOCL_iteratorSolution" + nestLevel;
		decl = ""; // subPatternDesc.generateDeclaration(ident, allDesc,
								// iteratorSolution + ".getSubPermutation()");
		if( decl.length() != 0 ) {
			decl = "{\n" + "\tIteratorForSolution " + iteratorSolution
					+ " = (IteratorForSolution)" + atomIterator + ";\n"
					+ ( decl.length() > 0 ? CodeGeneratorHelper.indentCode( decl ) + "\n" : "" )
					+ "}";
		}
		return decl;
	}


	public void applyCondOfBool() {
		// subPatternDesc.applyCondOfBool();
	}


	public void pushCond( ReactionCondition decompCond ) {
		// subPatternDesc.pushCond(decompCond);
	}


	public Set<String> getVars() {
		return null; // subPatternDesc.getVars();
	}


	public Set<String> getJokers() {
		return null; // subPatternDesc.getJokers();
	}


	public void incrNbOcc() {
		nbOccurrences++;
	}


	public int getNbOcc() {
		return nbOccurrences;
	}


	public void applyCond( Set<String> assignedVars, ReactionCondition decompCond ) {
		// subPatternDesc.applyCond(assignedVars, decompCond);
	}


	public boolean isReadOnly() {
		return readOnly;
	}


	public void setReadOnly() {
		readOnly = true;
	}

} // class VarDescSolution
