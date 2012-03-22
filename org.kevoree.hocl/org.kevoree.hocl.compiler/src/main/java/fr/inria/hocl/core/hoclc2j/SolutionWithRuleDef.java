package fr.inria.hocl.core.hoclc2j;


import java.util.LinkedList;
import java.util.Set;

public class SolutionWithRuleDef implements Solution {

	private ReactionRulePat reactionRulePat;

	private Solution solution;


	public SolutionWithRuleDef( Solution solution, ReactionRulePat reactionRulePat ) {
		this.reactionRulePat = reactionRulePat;
		this.solution = solution;
	}


	public String generateDeclarationInit( int position, SymbolsTable symbolsTable ) {
		String s = "";
		Set<Symbols> freeVars = solution.getFreeVars();
		if( freeVars.contains( reactionRulePat ) ) {
			s = reactionRulePat.generateDeclaration( reactionRulePat,
					"/*SolWithRuleDef: iterator */" ); // FIXME
		}
		return s + "\n" + solution.generateDeclarationInit( position, symbolsTable );
	}


	public String generateReference() {
		return solution.generateReference();
	}


	public Set<Symbols> getFreeVars() {
		Set<Symbols> freeVars = solution.getFreeVars();
		freeVars.addAll( reactionRulePat.reactionRule.getFreeVars() );
		return freeVars;
	}


	public String generateContentsReference() {
		return solution.generateContentsReference();
	}


	public String generateClassCode() {
		String s = "";
		Set<Symbols> freeVars = solution.getFreeVars();
		if( freeVars.contains( reactionRulePat ) ) {
			s = "public "
					+ reactionRulePat.generateDeclaration( reactionRulePat,
							"/*SolWithRuleDef: iterator */" ); // FIXME
		}
		return s + "ThisIsBetweenTheClasses" + solution.generateClassCode();
	}


	public String generateContentsDeclaration() {
		// may be you can make this function as void, and then write to the file
		// directly.

		String s = "";
		Set<Symbols> freeVars = solution.getFreeVars();
		if( freeVars.contains( reactionRulePat ) ) {
			s = reactionRulePat.generateDeclaration( reactionRulePat,
					"/*SolWithRuleDef: iterator */" ); // FIXME
		}
		return s + "\n" + solution.generateContentsDeclaration();
	}


	public void findReadOnlyReactives() {
		reactionRulePat.reactionRule.findReadOnlyReactives();
		solution.findReadOnlyReactives();
	}


	public Molecule getContents() {
		return solution.getContents();
	}


	public void setReadOnly() {
		solution.setReadOnly();
	}


	public boolean isReadOnly() {
		return solution.isReadOnly();
	}


	public String getType() {
		// TODO Auto-generated method stub
		return "solutionWithRuleDef";
	}


	public String generateAddElement() {
		return solution.generateAddElement();
	}


	public LinkedList<String> getElementTypes() {
		return this.solution.getElementTypes();
	}

}
