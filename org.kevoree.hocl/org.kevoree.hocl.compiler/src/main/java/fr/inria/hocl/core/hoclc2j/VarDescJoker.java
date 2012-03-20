package fr.inria.hocl.core.hoclc2j;


import java.util.HashSet;
import java.util.Set;

public class VarDescJoker implements VarDesc {

	String ident;

	private int nbOccurrences; // cf varDesc reordering

	private int posJoker;

	private boolean readOnly;


	public VarDescJoker( String ident ) {
		this.ident = ident;
		readOnly = false;
	}


	/*
	 * public HoclType getType(){ return HoclType.JOKER; }
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

		if( varDesc instanceof VarDescJoker) {
			equals = (((VarDescJoker) varDesc).ident.equals(ident));
		}
		return equals;
	}


	public String generatePatClass( Pattern allDesc, Set<String> vars,
			String patAtom ) {
		return "";
	}


	public String generateNewPat() {
		return ""; // cf PatternDesc.generateJokerSizeArray
	}


	public String generateVarDecl() {
		return "Molecule " + ident;
	}


	public String generateIdent() {
		return ident;
	}


	public void setPosJoker( int posJoker ) {
		this.posJoker = posJoker;
	}


	public String generateDeclarations( String ident, Pattern allDesc,
			String permutation ) {
		String decl = "";
		if( permutation.length() == 0 ) { // joker not inside a solution
			decl = ident + " = new Molecule();"; // matches nothing
		} else {
			if( this.ident.equals( ident ) ) {
				decl = ident + " =  " + permutation + ".getMatchedMolecule(" + posJoker
						+ ");";
			}
		}
		return decl;
	}


	public void applyCondOfBool() {
		// nothing to do: no condition on jokers
	}


	public void pushCond( ReactionCondition decompCond ) {
		// nothing to do: no condition on jokers
	}


	public Set<String> getVars() {
		return new HashSet<String>(); // returns only ident of atoms not jokers
	}


	public Set<String> getJokers() {
		Set<String> s = new HashSet<String>();
		s.add( ident );
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


	public void setReadOnly() {
		readOnly = true;
	}


	public boolean isReadOnly() {
		return readOnly;
	}

} // class VarDescJoker
