package fr.inria.hocl.iterator;


import fr.inria.hocl.api.*;

/**
 * Atom iterator specialised for the search of a tuple
 * 
 */
public class IteratorForTuple extends AtomIterator {

	private static final long serialVersionUID = -3140717883097675319L;

	private final Permutation[] subPermutations;

	private final int size;

	private IteratorForSolution blockingSol;


	public IteratorForTuple( AtomIterator[] atomIteratorsTuple,
			ReactionRule reactionRule ) {
		super();
		size = atomIteratorsTuple.length;
		subPermutations = new PermutationNoRepeat[size];
		for( int i = 0; i < size; i++ ) {
			AtomIterator[] atomIter = new AtomIterator[1];
			atomIter[0] = atomIteratorsTuple[i];
			subPermutations[i] = new PermutationNoRepeat( atomIter,
					new MoleculeIterator[0], reactionRule );
		}
		blockingSol = null;
	}


	public void initIterator( Solution solution ) {
		super.initIterator( solution );
		initSubIterators();
	}


	private void initSubIterators() {
		if( !iterator.isAtEnd() ) {
			Atom atom = getAtom();
			if( atom instanceof Tuple) {
				Tuple tuple = (Tuple) atom;
				if( tuple.size == size ) {
					for( int i = 0; i < size; i++ ) {
						// init iterator FIXME: horrible hack
						Solution sol = new Solution();
						Molecule mol = new Molecule();
						mol.add( tuple.get( i ) );
						sol.addMolecule( mol );
						subPermutations[i].setSolution( sol );
					}
				}
			}
		}
	}


	public AtomIterator getAtomIterator( int i ) {
		assert ( i >= 0 && i < size );
		return subPermutations[i].getAtomIterator( 0 ); // 0 because all sub
																										// permutations have only
																										// one iterator
	}


	public boolean conditionSatisfied() {
		boolean satisfied = false;
		Tuple tuple;
		Atom atom = getAtom();

		if( atom instanceof Tuple) {
			tuple = (Tuple) atom;
			if( tuple.size == size ) {
				satisfied = true;
				int i;
				for( i = 0; i < size && satisfied; i++ ) {
					satisfied = getAtomIterator( i ).conditionSatisfied();
				}
			}
		}
		return satisfied;
	}


	public IteratorForSolution getBlockingSolution() {
		return blockingSol;
	}


	public void next() {
		if( !iterator.isAtEnd() ) {
			Atom atom = getAtom();
			if( atom instanceof Tuple && ( (Tuple) atom ).size == size ) {
				boolean satisfied;
				int nbIter = size - 1;

				satisfied = subPermutations[nbIter].nextMatchHard();
				while( !satisfied && nbIter > 0 ) {
					nbIter--;
					satisfied = subPermutations[nbIter].nextMatchHard();
				}
				if( satisfied ) {
					// reset all following iterators
					for( nbIter++; nbIter < size; nbIter++ ) {
						subPermutations[nbIter].reset();
					}
				} else {
					// no more sub reactives, find next tuple
					super.next();
					initSubIterators();
				}
			} else {
				// not a correct Tuple
				super.next();
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

} // class IteratorForTuple
