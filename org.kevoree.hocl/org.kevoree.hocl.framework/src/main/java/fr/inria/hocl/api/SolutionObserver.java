package fr.inria.hocl.api;


/**
 * Observer DP: Abstract Observer specialised for Solution
 * 
 */
interface SolutionObserver {

	/**
	 * Method called by a solution when a new atom has been added
	 * 
	 * @param solution
	 *          solution that has changed
	 * @param atom
	 *          atom that has been added
	 */
	public void notifyAdd (Solution solution, Atom atom);


	/**
	 * Method called by a solution when a new atom has been removed
	 * 
	 * @param solution
	 *          solution that has changed
	 * @param atom
	 *          atom that has been removed
	 */
	public void notifyRemove (Solution solution, Atom atom);

} // interface SolutionObserver
