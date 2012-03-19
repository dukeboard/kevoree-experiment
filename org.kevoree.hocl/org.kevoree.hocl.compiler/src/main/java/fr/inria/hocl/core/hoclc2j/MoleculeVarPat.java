package fr.inria.hocl.core.hoclc2j;


import java.util.HashSet;
import java.util.Set;

public class MoleculeVarPat implements Symbols { //

	public final String name;

	int position;


	public MoleculeVarPat( String varName ) {
		name = varName;
	}


	public Symbols lookUp( String ident ) {
		return ( ident.equals( name ) ) ? this : null;
	}


	public String generateDeclarationInit() {
		return "Molecule " + name + "; // FIXME";
	}


	public String generateReference() {
		return name;
	}


	public void setPosition( int position ) {
		this.position = position;
	}


	public String generateDeclaration( Symbols symbols, String permutation ) {
		String s = "";
		if( symbols == this ) {
			s = "Molecule " + name;
			if( permutation.length() == 0 ) { // FIXME: molecule variable not inside a
																				// solution pattern
				s = s + " = new Molecule();"; // matches nothing
			} else {
				// if(this.name.equals(ident)){
				s = s + " = " + permutation + ".getMatchedMolecule(" + position + ");";
				// }
			}
		}
		return s;
	}


	public Set<Symbols> getVariables() {
		Set<Symbols> vars = new HashSet<Symbols>();
		vars.add( this );
		return vars;
	}


	public String toString() {
		return "?" + name;
	}


	public String generateIdent() {
		return ""; // no ident
	}


	public String generateVariableDecl() {
		return ""; // no ident => no declaration
	}

}
