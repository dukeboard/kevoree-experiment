package fr.inria.hocl.core.hoclc2j;


import java.util.Set;

public interface AtomPattern extends Symbols {

	// public String generateDeclaration(AtomPattern variable, String iterator);

	public Set<Symbols> getVariables ();


	public String generatePatClass(String permutation, String reactionRuleName,
			SymbolsTable symbolsTable);


	public String generatePatternNew (String reactionRuleName);


	public boolean setLastReactionCondition (External cond);


	public void setReactionRule (ReactionRule reactionRule);


	public void checkReadOnly(Molecule molecule);


	public boolean equalsAtom(Atom atom);
}
