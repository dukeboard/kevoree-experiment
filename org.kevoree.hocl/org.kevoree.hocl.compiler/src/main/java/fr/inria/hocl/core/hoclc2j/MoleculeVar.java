package fr.inria.hocl.core.hoclc2j;


import java.util.LinkedList;
import java.util.Set;

public class MoleculeVar implements Atom {

	private MoleculeVarPat moleculeVarPat;

	private Access access;


	public MoleculeVar( MoleculeVarPat molVarPat ) {
		moleculeVarPat = molVarPat;
		access = Access.REWRITE;
	}


	public String generateDeclarationInit( int position, SymbolsTable symbolsTable ) {
		return ""; // moleculeVarPat.generateDeclaration(moleculeVarPat,"permutation.getAtomIterator("
								// + position + ")");
	}


	public String generateReference() {
		return moleculeVarPat.generateReference();
	}


	public Set<Symbols> getFreeVars() {
		return moleculeVarPat.getVariables();
	}


	public void findReadOnlyReactives() {
		// nothing to do
	}


	public void setReadOnly() {
		access = Access.READ_ONLY;
	}


	public boolean isReadOnly() {
		return access == Access.READ_ONLY;
	}


	public String getType() {
		// TODO Auto-generated method stub
		return "moleculeVar";
	}


	public LinkedList<String> getElementTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
