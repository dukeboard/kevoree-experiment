package fr.inria.hocl.iterator;


import fr.inria.hocl.api.ExternalObject;

/**
 * Atom iterator specialised for the search of an external object
 * 
 */
abstract public class IteratorForExternal extends AtomIterator {

	private static final long serialVersionUID = -3947330568491802850L;


	/**
	 * 
	 * @return the object pointed by the iterator assuming that it refers to an
	 *         ExternalObject
	 */
	public Object getObject() {
		return ( (ExternalObject) iterator.getElement() ).getObject();
	}


	public boolean conditionSatisfied() {
		return iterator.getElement() instanceof ExternalObject;
	}

} // class IteratorForExternal
