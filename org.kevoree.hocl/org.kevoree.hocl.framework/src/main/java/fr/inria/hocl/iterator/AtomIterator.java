package fr.inria.hocl.iterator;


import fr.inria.hocl.api.Access;
import fr.inria.hocl.api.Atom;
import fr.inria.hocl.api.Solution;

import java.io.Serializable;

/**
 * Iterator that points to an element in a solution that is considered for a
 * reaction. It is part of a permutation
 * 
 */
public abstract class AtomIterator implements Serializable {

	private static final long serialVersionUID = 5861687577632066234L;

	public SimpleIterator<Atom> iterator;

	// Bug found by TP and fixed by YR
	// protected Access access;
	public Access access = Access.REWRITE;


	/**
	 * Init the iterator to enumerate atoms from solution
	 * 
	 * @param solution solution to enumerate
	 */
	public void initIterator( Solution solution ) {
		iterator = solution.newIterator();
		// access = Access.REWRITE;
	}


	abstract public boolean conditionSatisfied();


	/**
	 * 
	 * @return the atom that the iterator points to (assume it exists)
	 */
	public Atom getAtom() {
		return iterator.getElement();
	}


	/**
	 * Move the iterator to the next atom (if not at the end). May not realy move
	 * it: cf IteratorForSolution, IteratorForTuple
	 * 
	 */
	public void next() {
		iterator.next();
	}


	/**
	 * Move really the iterator to the next atom if it exists.
	 * 
	 */
	public void nextHard() {
		iterator.next();
	}


	public void moveToIterator( SimpleIterator<Atom> it ) {
		iterator.moveToIterator( it );
	}


	public void moveToBeginning() {
		iterator.moveToBeginning();
	}


	public void moveToEnd() {
		iterator.moveToEnd();
	}


	public boolean isAtStart() {
		return iterator.isAtStart();
	}


	public boolean isAtEnd() {
		return iterator.isAtEnd();
	}


	public boolean equals( AtomIterator atomIterator ) {
		return iterator.equals( atomIterator.iterator );
	}


	public boolean equals( SimpleIterator<Atom> iterator ) {
		return this.iterator.equals( iterator );
	}


	public String toString() {
		if( iterator == null ) {
			return "null_iterator";
		} else {
			return iterator.toString();
		}
	}
}
