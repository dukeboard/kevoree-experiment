package fr.inria.hocl.api;


import fr.inria.hocl.core.hocli.Hocli;
import fr.inria.hocl.iterator.AtomIterator;
import fr.inria.hocl.iterator.MoleculeIterator;

import java.io.Serializable;
import java.util.Comparator;

/**
 * This class represents a reaction rule.
 * 
 */
abstract public class ReactionRule implements Atom, Serializable {

	private static final long serialVersionUID = -4561593662483581853L;

	public enum Shot {
		N_SHOT, ONE_SHOT
	}

	public enum Trope {
		REDUCER, OPTIMIZER, EXPANSER, UNKNOWN
	}

	private final String name;

	private final Shot shot;

	private Trope trope;

	protected Permutation permutation; // currently considered atoms


	public ReactionRule( String n, Shot sh ) {
		name = n;
		shot = sh;
		trope = Trope.UNKNOWN;
		// FIXME: permutation is initialised by the sub-class
	}


	/**
	 * Compute the result of the reaction
	 * 
	 * @return return the result of the reaction
	 */
	abstract protected Molecule computeResult();


	/**
	 * 
	 * @return the name of the reaction rule
	 */
	public String getName() {
		return name;
	}


	/**
	 * 
	 * @return the "shot" type of the rule
	 */
	public Shot getShot() {
		return shot;
	}


	/**
	 * Abstract Factory DP: ReactionRule is a ConcreteFactory for Permutation
	 * 
	 * @param atomIterators
	 * @param moleculeIterators
	 * @return
	 */
	public Permutation newPermutation( AtomIterator[] atomIterators, MoleculeIterator[] moleculeIterators ) {
		return new PermutationNoRepeat( atomIterators, moleculeIterators, this );
	}


	public void setTrope( Trope trope ) {
		this.trope = trope;
	}

	public static class CmpSmallSolution implements Comparator<ReactionRule> {

		public int compare( ReactionRule r1, ReactionRule r2 ) {
			int res;
			Trope t1 = r1.trope;
			Trope t2 = r2.trope;
			switch( t1 ) {
			case REDUCER:
				if( t2 == Trope.REDUCER ) {
					res = 0;
				} else {
					res = -1;
				}
				break;
			case OPTIMIZER:
				if( t2 == Trope.OPTIMIZER ) {
					res = 0;
				} else if( t2 == Trope.REDUCER ) {
					res = 1;
				} else {
					res = -1;
				}
				break;
			case UNKNOWN:
				if( t2 == Trope.UNKNOWN ) {
					res = 0;
				} else if( t2 == Trope.EXPANSER ) {
					res = -1;
				} else {
					res = 1;
				}
				break;
			case EXPANSER:
				if( t2 == Trope.EXPANSER ) {
					res = 0;
				} else {
					res = 1;
				}
				break;
			default:
				res = 0; // will never happen, but the compiler will complain if absent
			}
			return res;
		}
	} // class CmpSmallSolution


	public boolean equals( final Atom atom ) {
		return atom instanceof ReactionRule
				&& name.equals( ( (ReactionRule) atom ).name );
	}


	public String toString() {
		return name;
	}


	/**
	 * @return the required minimal number of atoms (reaction rule included) of
	 *         the solution that contains this rule such that a reaction may be
	 *         possible
	 */
	public int getMinAtoms() {
		return permutation.size() + 1; // variables of molecule can match nothing
	}


	/**
	 * @return some statistics about the rule : number of permutations checked and
	 *         number of reactions
	 */
	public String getStat() {
		String s = new String( "\n" );
		s = s + this + " statistics:\n";
		s = s + " - number of tests: "
				+ Hocli.debug.getNbCheckedPermutations( this ) + "\n";
		s = s + " - number of reactions: " + Hocli.debug.getNbReactions( this )
				+ "\n";
		return s;
	}


	/**
	 * Set the solution where this rule is, to find reactives
	 * 
	 * @param solution
	 */
	public void setSolution( Solution solution ) {
		permutation.setSolution( solution );
	}


	/**
	 * Search for the next reactives for this rule.
	 * 
	 * @return the result of the search
	 */
	public boolean nextReaction() {
		return permutation.nextMatch();
	}


	/**
	 * 
	 * @return the reactives assuming that they exist
	 */
	public Molecule getReactives() {
		Molecule reactives = permutation.getReactives();
		if( shot == Shot.ONE_SHOT && !reactives.contains( this ) ) {
			reactives.add( this );
		}
		return reactives;
	}

} // class ReactionRule
