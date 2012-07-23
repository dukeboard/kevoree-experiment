package fr.inria.hocl.api;


import fr.inria.hocl.core.hocli.Debug;
import fr.inria.hocl.core.hocli.Hocli;
import fr.inria.hocl.iterator.AtomIterator;
import fr.inria.hocl.iterator.MoleculeIterator;
import fr.inria.hocl.iterator.SimpleIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;

/**
 * PermutationNoRepeat is a PermutationEngine that ensures that calling next()
 * will never return a permutation already given
 * 
 * A multiset is a list. All new atoms are added at the end of the list. Two
 * pointers on that list : it_new and it_nextNew. Any permutation of atoms
 * before it_new have been checked. The algorithm checks all permutations that
 * contain at least one atom between it_new and it_nextNew (a cycle). When all
 * permutations have been checked, it_new becomes it_nextNew and it_nextNew is
 * set to the end of the list (new cycle).
 * 
 */
public class PermutationNoRepeat implements Permutation, Serializable {

	private static final long serialVersionUID = -925071669433082995L;

	private Logger logger = LoggerFactory.getLogger(PermutationNoRepeat.class);

	private final AtomIterator[] atomIterators; // for variables of atoms

	private final MoleculeIterator[] moleculeIterators; // for variables of molecules

	private final ReactionRule reactionRule; // reaction rule this permutation belongs to

	private Solution solution; // solution to iterate

	private final int permSize; // number of atoms to look for

	private boolean wholeSolutionMatched; // true if all element of the solution have been matched

	private SimpleIterator<Atom> it_new;

	private SimpleIterator<Atom> it_nextNew;

	private int currentNew; // ensure that at least atomIterators[currentNew] is a new atom

	public PermutationNoRepeat(AtomIterator[] atomIterators, MoleculeIterator[] moleculeIterators, ReactionRule reactionRule ) {

		this.atomIterators = atomIterators;
		this.moleculeIterators = moleculeIterators;
		this.reactionRule = reactionRule;
		wholeSolutionMatched = false;
		permSize = atomIterators.length;
		solution = null;
	}


	public void setSolution( Solution solution ) {
		this.solution = solution;
		if( solution != null ) {
			solution.addObserverAdd( this ); // Observer DP: attach
			it_new = solution.newIterator();
			it_nextNew = solution.newIterator();
			reset();
		}
	}


	public void reset() {
		for( int i = 0; i < size(); i++ ) {
			atomIterators[i].initIterator( solution );
		}
		it_new.moveToBeginning();
		it_nextNew.moveToEnd();
		currentNew = 0;
		firstPermutation();
	}


	private void unsetSolution() {
		solution.removeObserverAdd( this ); // Observer DP: detach from previous
																				// solution if present
		solution = null;
	}


	public int size() {
		return permSize;
	}


	public void notifyAdd( Solution solution, Atom atom ) {
		if( size() > 0 ) { // if non nul permutation
			// may generate a conflict if the permutation was in a final state
			moveIterIfConflict( size() - 1 );
		}
	}


	public void notifyRemove( Solution solution, Atom atom ) {
		if( atom == reactionRule ) {
			unsetSolution();
		} else {
			// ensure that no iterator points to this atom that will be removed
			// i.e. find a match that does not consider this atom
			int i = 0;
			while( i < size() ) {
				if( !atomIterators[i].isAtEnd() && atomIterators[i].getAtom() == atom ) {
					nextHard( i );
					i = 0; // re-check from the start, since next(i) may have moved a
									// previous iterator on that atom again
				} else {
					i++;
				}
			}
			if( !it_new.isAtEnd() && it_new.getElement() == atom ) {
				it_new.next();
			}
			if( !it_nextNew.isAtEnd() && it_nextNew.getElement() == atom ) {
				it_nextNew.next();
			}
		}
		assert correctState();
	}


	private boolean correctState() {
		if( isFinal() ) {
			return true;
		}
		int i = 0;
		while( i < size() ) {
			if( atomIterators[i].getAtom() == null ) {
				return false;
			}
		}
		return true;
	}


	public Molecule getPermutation() {
		Molecule molecule = new Molecule();
		for( int i = 0; i < size(); i++ ) {
			molecule.add( atomIterators[i].getAtom() );
		}
		return molecule;
	}


	public Molecule getReactives() {
		Molecule molecule = new Molecule();
		// get all atoms to rewrite
		for( int i = 0; i < size(); i++ ) {
			if( atomIterators[i].access == Access.REWRITE ) {
				molecule.add( atomIterators[i].getAtom() );
			}
		}
		// get all atoms of all molecules
		for (MoleculeIterator moleculeIterator : moleculeIterators) {
			molecule.add(moleculeIterator.getMolecule());
		}
		return molecule;
	}


	public AtomIterator getAtomIterator( int i ) {
		assert ( i >= 0 && i < size() );
		return atomIterators[i];
	}


	public boolean nextMatch() {
		int i;
		boolean exists;
		boolean satisfied;

		i = 0;
		while( i < permSize && !isFinal() ) {

			if( i == 0 ) {
				Hocli.debug.incrRuleCheckedPermutations( reactionRule );
				Hocli.debug.addLog( Debug.DebugSymbol.TESTED_PERM, reactionRule
						+ " test permutation n"
						+ Hocli.debug.getNbCheckedPermutations( reactionRule ) + "\n"
						+ this.toString() );
			}

			satisfied = atomIterators[i].conditionSatisfied();
			if( satisfied ) {
				// all atom until the ith one are correct
				i++; // check next atom of the pattern
			} else {

				Hocli.debug.addLog( Debug.DebugSymbol.TESTED_PERM, " next it" + i );

				exists = next( i ); // next permutations where the ith iterator is
														// different (particular case for solution and
														// tuple)
				if( !exists ) {
					satisfied = false;
				} else {
					i = 0; // restart all checks since next(i) may have moved some
									// iterators before i
					/*
					 * FIXME: optimization: checks only if some iterators have really
					 * moved => j = next(i); // j = most left iter that has moved, -1 if
					 * not exists FIXME: save result of partial conditions on given atoms
					 */
				}
			}
		}
		if( i == permSize ) { // all atomic reactives have been found
			computeMolecules();
			satisfied = true;
		} else {
			satisfied = false;
		}
		return satisfied;
	}


	public boolean nextMatchHard() {
		boolean exists = false;
		if( permSize > 0 ) {
			exists = next( permSize - 1 );
			if( exists ) {
				exists = nextMatch();
			}
		}
		return exists;
	}


	private boolean next( int itToMove ) {
		assert ( itToMove < permSize && itToMove >= 0 );
		boolean exists;

		Hocli.debug
				.addLog( Debug.DebugSymbol.PERMUTATION, "next(" + itToMove + ")" );

		if( !isIteratorAtCycleEnd( itToMove ) ) {
			atomIterators[itToMove].next();
		}
		// either the iterator has moved or this iterator is at the end => solve
		// conflict
		exists = moveIterIfConflict( itToMove );
		if( exists ) {
			// move next iterators to the beginning
			for( int i = itToMove + 1; i < permSize && exists; i++ ) {
				exists = moveIterToStartNoConflict( i );
			}
		}
		return exists;
	}


	private boolean nextHard( int itToMove ) {
		assert ( itToMove < permSize && itToMove >= 0 );
		boolean exists;

		Hocli.debug.addLog( Debug.DebugSymbol.PERMUTATION, "nextHard(" + itToMove
				+ ")" );

		if( !isIteratorAtCycleEnd( itToMove ) ) {
			atomIterators[itToMove].nextHard();
		}
		// either the iterator has moved or this iterator is at the end => solve
		// conflict
		exists = moveIterIfConflict( itToMove );
		if( exists ) {
			// move next iterators to the beginning
			for( int i = itToMove + 1; i < permSize && exists; i++ ) {
				exists = moveIterToStartNoConflict( i );
			}
		}
		return exists;
	}


	public boolean isFinal() {
		return permSize == 0 // case when looking just for jokers (no atom) OR
				|| ( isIteratorAtCycleEnd( 0 ) // end of cycle for the first iterator
																				// AND
						&& currentNew == permSize - 1 // no more exactly new iterator AND
				&& it_nextNew.isAtEnd() ); // no more elements
	}


	/*
	 * Move the iterator to a no conflict place. Returns true if it has found a
	 * place without a conflict, false otherwise.
	 */
	private boolean moveIterIfConflict( int itNb ) {
		boolean exists;

		Hocli.debug.addLog( Debug.DebugSymbol.PERMUTATION,
				"... : begin moveIterIfConflict(" + itNb + "): " + atomIterators[itNb] );

		/*
		 * 3 conflicts may happen: - end of cycle - reference to the current rr (ie
		 * self): rr cannot be argument of itself - reference to an atom already
		 * referred by another iterator of the current rr
		 */

		if( isIteratorAtCycleEnd( itNb ) ) {
			if( itNb > 0 && !isIteratorAtCycleStart( itNb ) ) {
				exists = next( itNb - 1 );
			} else {
				if( currentNew < permSize - 1 ) {
					currentNew++;

					Hocli.debug.addLog( Debug.DebugSymbol.PERMUTATION, "CurrentNew = "
							+ currentNew + " on ..." );

					exists = firstPermutation();
				} else {
					if( it_nextNew.isAtEnd() ) {
						// no more new elements, so no new permutations
						exists = false;
						// set all iterators in the "end state"
						it_new.moveToEnd();
						for( int i = 0; i < permSize; i++ ) {
							atomIterators[i].moveToEnd();
							// atomIterators[i].initIterator(); // FIXME: recursive revert to
							// null
						}
					} else {
						// new elements, try new permutations with new elements
						exists = nextCycle();
					}
				}
			}
		} else {

			// cannot refer to elements referred by previous iterators
			int j = 0;
			while( j < itNb && !atomIterators[j].equals( atomIterators[itNb] ) ) {
				j++;
			}
			exists = j >= itNb || nextHard(itNb);
		}

		Hocli.debug.addLog( Debug.DebugSymbol.PERMUTATION,
				"... :  end moveIterIfConflict(" + itNb + ")"
						+ ( exists ? "-> " + atomIterators[itNb] : "" ) );



		return exists;
	}


	private boolean nextCycle() {
		it_new.moveToIterator( it_nextNew );
		it_nextNew.moveToEnd();
		currentNew = 0;

		Hocli.debug.addLog( Debug.DebugSymbol.PERMUTATION,
				"NewCycle: CurrentNew = 0 on " + "..." );

		return firstPermutation();
	}


	private void moveIteratorAtCycleStart( int itNumber ) {
		if( itNumber == currentNew ) {
			atomIterators[itNumber].moveToIterator( it_new );
		} else {
			atomIterators[itNumber].moveToBeginning();
		}
	}


	private boolean isIteratorAtCycleStart( int itNumber ) {
		if( itNumber == currentNew ) {
			return atomIterators[itNumber].equals( it_new );
		} else {
			return atomIterators[itNumber].isAtStart();
		}
	}


	private boolean isIteratorAtCycleEnd( int itNumber ) {
		if( itNumber >= currentNew ) {
			return atomIterators[itNumber].equals( it_nextNew );
		} else {
			return atomIterators[itNumber].equals( it_new );
		}
	}


	private boolean firstPermutation() {
		boolean exists = true;

		for( int i = 0; i < permSize && exists; i++ ) {
			exists = moveIterToStartNoConflict( i );
		}
		return exists;
	}


	private boolean moveIterToStartNoConflict( int itNb ) {
		moveIteratorAtCycleStart( itNb );
		return moveIterIfConflict( itNb );
	}


	private void computeMolecules() {
		int nbRemainingAtoms = solution.size() - size();

		if( solution.contains( reactionRule ) ) {
			nbRemainingAtoms--;
		}

		if( moleculeIterators.length > 0 ) {
			SimpleIterator<Atom> it_sol = solution.newIterator();
			for (MoleculeIterator moleculeIterator : moleculeIterators) {
				int size;

				if (moleculeIterator.getFraction() == 0) {
					size = 0;
				} else {
					size = solution.size() / moleculeIterator.getFraction();
				}

				moleculeIterator.clear();

				for (int j = 0; j < size && !it_sol.isAtEnd(); j++) {
					int k;
					// find an atom that is not already a reactive and
					// not currentRule
					boolean found;
					do {
						k = 0;
						while (k < size() && !atomIterators[k].iterator.equals(it_sol)) {
							k++;
						}
						found = (k == size() && it_sol.getElement() != reactionRule);
						if (!found) {
							it_sol.next();
						}
					} while (!found && !it_sol.isAtEnd());
					if (found) {
						moleculeIterator.addAtom(it_sol.getElement());
						nbRemainingAtoms--;
						it_sol.next();
					}
				}
			}
			// gets the remaining atoms in the last molecule iterator
			if( !it_sol.isAtEnd()
					&& moleculeIterators[moleculeIterators.length - 1].getFraction() > 0 ) {
				while( !it_sol.isAtEnd() ) {
					moleculeIterators[moleculeIterators.length - 1].addAtom( it_sol
							.getElement() );
					nbRemainingAtoms--;
					it_sol.next();
				}
			}
		}

		wholeSolutionMatched = ( nbRemainingAtoms == 0 );
	}


	public Molecule getMatchedMolecule( int i ) {
		assert i >= 0 && i < moleculeIterators.length;
		return moleculeIterators[i].getMolecule();
	}


	public boolean wholeSolutionMatched() {
		return wholeSolutionMatched;
	}


	public String toString() {
		String s = "";
		for( int j = 0; j < size(); j++ ) {
			s += reactionRule + ":it" + j + " = " + atomIterators[j] + "\n";
		}
		return s;
	}

} // class PermutationNoRepeat
