package fr.inria.hocl.core.hoclc2j;


import java.util.Set;

public class VarDescTuple implements VarDesc {

	private Pattern subPatternDesc;

	private int nbOccurrences; // cf varDesc reordering

	private final int nestLevel;

	private final String reactionRule;

	private boolean readOnly;


	public VarDescTuple( Pattern subPatternDesc, int nestLevel,
			String reactionRule ) {
		this.subPatternDesc = subPatternDesc;
		this.nestLevel = nestLevel;
		this.reactionRule = reactionRule;
		readOnly = false;
	}


	/*
	 * public HoclType getType(){ return HoclType.TUPLE; }
	 */

	public VarDesc lookUp( String ident ) {
		return null; // subPatternDesc.lookUp(ident);
	}


	public boolean equals( VarDesc varDesc ) {
		boolean equals = false;
		if( varDesc instanceof VarDescTuple) {
			equals = subPatternDesc
					.equals( ( (VarDescTuple) varDesc ).subPatternDesc );
		}
		return equals;
	}


	public String generatePatClass( Pattern allDesc, Set<String> vars,
			String patAtom ) {
		String s = ""; // subPatternDesc.generateAtomIteratorArray(allDesc,
										// nestLevel+1);
		return s;
	}


	public String generateNewPat() {
		return "new IteratorForTuple(_HOCL_atomIteratorArray" + ( nestLevel + 1 )
				+ ", " + reactionRule + ".this)";
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
		String tupleIterator = "_HOCL_tupleAtomIterator" + nestLevel;
		decl = ""; // subPatternDesc.generateDeclaration(ident, allDesc,
								// tupleIterator);
		if( decl.length() != 0 ) {
			decl = "{\n" + "\tIteratorForTuple " + tupleIterator
					+ " = (IteratorForTuple)" + atomIterator + ";\n"
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

} // class VarDescTuple
