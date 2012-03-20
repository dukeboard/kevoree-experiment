package fr.inria.hocl.iterator;


import fr.inria.hocl.api.Atom;
import fr.inria.hocl.api.Molecule;

/**
 * "Iterator" to a molecule. The constructor requires an integer that represents
 * the fraction of the solution to match: - 0 : nothing - n : 1/n of the
 * remainder (may not be exact if n does not divides the cardinal of the
 * solution)
 * 
 */
public class MoleculeIterator {

	private Molecule molecule;

	private final int fraction;


	/**
	 * 
	 * @param fraction
	 *          fraction of the solution to match (e.g. 3 for a third, 0 for
	 *          nothing)
	 */
	public MoleculeIterator( int fraction ) {
		this.fraction = fraction;
		molecule = new Molecule();
	}


	public int getFraction() {
		return fraction;
	}


	/**
	 * Add an atom to the molecules matched by this iterator
	 * 
	 * @param atom
	 */
	public void addAtom( Atom atom ) {
		molecule.add( atom );
	}


	/**
	 * Clear all molecules currently considered
	 * 
	 */
	public void clear() {
		molecule.clear();
	}


	/**
	 * 
	 * @return the molecule matched
	 */
	public Molecule getMolecule() {
		Molecule mol = new Molecule();
		SimpleIterator<Atom> it = molecule.newIterator();
		while( !it.isAtEnd() ) {
			mol.add( it.getElement() );
			it.next();
		}
		return mol;
	}

} // class MoleculeIterator
