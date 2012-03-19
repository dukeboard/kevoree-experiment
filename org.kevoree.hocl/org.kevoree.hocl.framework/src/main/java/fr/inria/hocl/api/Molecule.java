package fr.inria.hocl.api;


import fr.inria.hocl.iterator.SimpleIterator;
import fr.inria.hocl.iterator.SimpleLinkedList;

import java.io.Serializable;

/**
 * Represents a molecule. A simple list of Atom objects
 * 
 */
public class Molecule extends SimpleLinkedList<Atom> implements Serializable {

	private static final long serialVersionUID = 6721316560450884857L;


	// public class Molecule extends SimpleLinkedList<Atom> {
	public String toString() {
		SimpleIterator<Atom> it = newIterator();
		String s = "";

		while( !it.isAtEnd() ) {
			s = s + it.getElement();
			if( it.hasNext() ) {
				s = s + ",";
			}
			it.next();
		}
		if( s.length() == 0 ) {
			s = ""; // empty molecule
		}
		return s;
	}


	/**
	 * Add a molecule to this molecule
	 * 
	 * @param molecule
	 */
	public void add( Molecule molecule ) {
		concat( molecule );
	}


	public void remove( Atom atom ) {
		SimpleIterator<Atom> it = newIterator();
		for( it.moveToBeginning(); !it.isAtEnd() && it.getElement() != atom; it
				.next() ) {}
		if( !it.isAtEnd() ) {
			it.remove();
		}
	}


	/**
	 * 
	 * @param atom
	 * @return true if atom belongs to this solution
	 */
	public boolean contains( Atom atom ) {
		boolean contain = false;
		SimpleIterator<Atom> it = newIterator();
		while( !it.isAtEnd() ) {
			if( it.getElement().equals( atom ) ) {
				contain = true;

				break;
			}
			it.next();
		}
		return contain;
	}


	/**
	 * Compares two molecules modulo associativity and commutativity, and takes
	 * into account multiplicities
	 * 
	 * @param molecule
	 * @return true if this molecule is the same to the molecule given
	 */
	public boolean equals( Molecule molecule ) {
		SimpleIterator<Atom> it1 = newIterator();
		SimpleIterator<Atom> it2 = molecule.newIterator();
		// following array records already checked atoms (because of multiplicities
		// > 1)
		boolean[] checked = new boolean[molecule.size()];
		for( int i = 0; i < checked.length; i++ ) {
			checked[i] = false;
		}
		boolean equals;
		int j;

		if( size() != molecule.size() ) {
			equals = false;
		} else {
			it1.moveToBeginning();
			equals = true;
			while( !it1.isAtEnd() && equals ) {
				it2.moveToBeginning();
				j = 0;
				while( !it2.isAtEnd()
						&& ( checked[j] || !it2.getElement().equals( it1.getElement() ) ) ) {
					it2.next();
					j++;
				}
				if( it2.isAtEnd() ) {
					// it1.getElement() not found in molecule (taking multiplicities into
					// account)
					equals = false;
				} else {
					checked[j] = true;
				}
				it1.next();
			}
		}
		return equals;
	}

} // class Molecule
