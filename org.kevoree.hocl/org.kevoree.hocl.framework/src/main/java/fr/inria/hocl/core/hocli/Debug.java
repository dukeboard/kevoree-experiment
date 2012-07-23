package fr.inria.hocl.core.hocli;


import fr.inria.hocl.api.ReactionRule;

import java.util.Hashtable;
import java.util.Set;
import java.util.TreeSet;

/**
 * Class grouping all data and operations related to debugging and profiling
 * (statistics)
 */
public class Debug {

	public enum DebugSymbol {
		PERMUTATION, SOLUTION_REDUCED, REACTIONS, REACTIONS_STATS, TESTED_PERM, FIRST_LAST_STATES, SOLUTION_PARTIAL, STRATEGY, SOLUTION_WHATISINSIDE
	}

	private String log;

	private Set<DebugSymbol> debugSymbols;

	private int indentLevel;

	private Hashtable<ReactionRule, Integer> ruleShots; // number of reactions per
	// rule

	private Hashtable<ReactionRule, Integer> ruleCheckedPermutations; // number of
	// checked
	// permutations
	// per rule


	public Debug () {
		clearLog();
		debugSymbols = new TreeSet<DebugSymbol>();
		indentLevel = 0;
		ruleShots = new Hashtable<ReactionRule, Integer>();
		ruleCheckedPermutations = new Hashtable<ReactionRule, Integer>();
	}


	/**
	 * Empty the log and the debug symbols
	 */
	public void reset () {
		clearLog();
		debugSymbols.clear();
		indentLevel = 0;
		ruleShots.clear();
	}


	private void clearLog () {
		log = "";
	}


	public void clearDebugSymbols () {
		debugSymbols.clear();
	}


	/**
	 * Adds a debug symbol in the managed symbols
	 *
	 * @param debugSymbol
	 */
	public void addDebugSymbol (DebugSymbol debugSymbol) {
		debugSymbols.add(debugSymbol);
	}


	/**
	 * @param debugSymbol
	 * @return true if the given symbol is in the managed debug symbols
	 */
	public boolean contains (DebugSymbol debugSymbol) {
		return debugSymbols.contains(debugSymbol);
	}


	/**
	 * Adds a message in the log if the corresponding symbol is managed
	 *
	 * @param debugSymbol
	 * @param message
	 */
	public void addLog (DebugSymbol debugSymbol, String message) {
		if (debugSymbol == DebugSymbol.SOLUTION_WHATISINSIDE) {
			if (indentLevel == 0) {
				System.out.println(message);
			}
		} else if (debugSymbols.contains(debugSymbol)) {
			// log += "\n" + indentSpace() + message;
			System.out.println(indentSpace() + message);
		}
	}


	/**
	 * Print the log to the standard output
	 */
	public void printLog () { // hack -_-
		if (log != null && (log.contains("Initial solution is:") || log.contains("Solution is:"))) {
			System.out.println(log);
		}
	}


	/**
	 * Increase the indent level.
	 */
	public void incrIndent () {
		indentLevel++;
	}


	/**
	 * Decrease the indent level if not null.
	 */
	public void decrIndent () {
		if (indentLevel > 0) {
			indentLevel--;
		}
	}


	private String indentSpace () {
		String s = "";
		for (int i = 0; i < indentLevel; i++) {
			s = s + "\t";
		}
		return s;
	}


	/**
	 * Increments the counter of reactions of the given rule
	 *
	 * @param rule
	 */
	public void incrRuleShots (ReactionRule rule) {
		if (debugSymbols.contains(DebugSymbol.REACTIONS_STATS)) {
			Integer nb = ruleShots.get(rule);
			if (nb == null) {
				ruleShots.put(rule, 1);
			} else {
				ruleShots.put(rule, nb + 1);
			}
		}
	}


	/**
	 * @param rule
	 * @return the number of reactions for the given rule
	 */
	public int getNbReactions (ReactionRule rule) {
		Integer nb = ruleShots.get(rule);
		if (nb == null) {
			return 0;
		} else {
			return nb;
		}
	}


	/**
	 * Increments the counter of checked permutations of the given rule
	 *
	 * @param rule
	 */
	public void incrRuleCheckedPermutations (ReactionRule rule) {
		if (debugSymbols.contains(DebugSymbol.REACTIONS_STATS)) {
			Integer nb = ruleCheckedPermutations.get(rule);
			if (nb == null) {
				ruleCheckedPermutations.put(rule, 1);
			} else {
				ruleCheckedPermutations.put(rule, nb + 1);
			}
		}
	}


	/**
	 * @param rule
	 * @return the number of checked permutations for the given rule
	 */
	public int getNbCheckedPermutations (ReactionRule rule) {
		Integer nb = ruleCheckedPermutations.get(rule);
		if (nb == null) {
			return 0;
		} else {
			return nb;
		}
	}

} // class Debug
