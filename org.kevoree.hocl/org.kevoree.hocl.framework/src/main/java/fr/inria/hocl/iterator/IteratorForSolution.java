package fr.inria.hocl.iterator;


import fr.inria.hocl.api.*;
import fr.inria.hocl.core.hocli.Hocli;

/**
 * Atom iterator specialised for the search of a solution
 * 
 */
public abstract class IteratorForSolution extends AtomIterator {

	private static final long serialVersionUID = 58753361602739408L;

	private final Permutation subPermutation;


	public IteratorForSolution() {
		this.subPermutation = makeSubPermutation();
	}


	abstract protected Permutation makeSubPermutation();


	public void initIterator( Solution solution ) {
		super.initIterator( solution );
		initSubIterators();
	}


	private void initSubIterators() {
		if( !isAtEnd() && super.getAtom() instanceof Solution ) {
			Solution solution = ( Solution ) super.getAtom();
			if( !solution.isInert() ) {
				// FIXME: remove this line to avoid the execution of inner solutions

				// Hocli.debug.incrIndent();
				// solution.reduce();
				// Hocli.debug.decrIndent();
			}
			subPermutation.setSolution( solution );
		}
	}


	public boolean conditionSatisfied(){
		boolean satisfied = false;
		Solution solution;
		Atom atom = getAtom();

		if( atom instanceof Solution ) {
			solution = ( Solution ) atom;
			if( !solution.isInert() ) {
				Hocli.debug.incrIndent();
				// reduce sub-solutions with the same strategy
				solution.reduce();
				Hocli.debug.decrIndent();
			}
			satisfied = subPermutation.nextMatch();
			satisfied = satisfied && subPermutation.wholeSolutionMatched();
		}
		return satisfied;
	}


	public void next() {
		if( !( getAtom() instanceof Solution && subPermutation.nextMatchHard() ) ) {
			// no more sub-permutation for this sub-solution or not a solution
			super.next();
			if( !isAtEnd() ) {
				initSubIterators();
			}
		}
	}


	public void nextHard() {
		super.nextHard();
		initSubIterators();
	}


	public void moveToIterator( SimpleIterator<Atom> it ) {
		super.moveToIterator( it );
		initSubIterators();
	}


	public void moveToBeginning() {
		super.moveToBeginning();
		initSubIterators();
	}


	public void moveToEnd() {
		super.moveToEnd();
		initSubIterators();
	}


	public Permutation getSubPermutation() {
		return subPermutation;
	}


	public Molecule getMatchedMolecule( int i ) {
		return subPermutation.getMatchedMolecule( i );
	}

} // class IteratorForSolution
