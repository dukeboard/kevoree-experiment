package fr.inria.hocl.api;


/**
 * A strategy manages a set of rule. At each step, it chooses a rule to run.
 * 
 * Observer DP, Concrete Observer: this class observes all the rules of a
 * solution.
 * 
 */
public abstract interface Strategy extends SolutionObserver {

	/**
	 * Set the solution managed by this strategy
	 * 
	 * @param solution
	 */
	abstract void manageSolution(Solution solution);


	/**
	 * 
	 * @return the first rule to consider in this strategy, or null if there is no
	 *         rule
	 */
	abstract ReactionRule firstRule ();


	/**
	 * return the next reaction rule to consider whether the previous given rule
	 * has reacted
	 * 
	 * @param hasPreviousReacted
	 * @return the next reaction rule to consider or null if finished (all rules
	 *         are inert)
	 */
	abstract ReactionRule queryNextRule (boolean hasPreviousReacted);


	/**
	 * 
	 * @return statistics of all reactions
	 */
	abstract public String getStat ();

}
