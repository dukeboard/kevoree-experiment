package fr.inria.hocl.core.hocli;


/**
 * HOCL interpreter
 * <p/>
 * It reduces a given solution to a inert state. Strategies and debug messages
 * may be specified here.
 * <p/>
 * Command line switches: [-s [FR|UFR|Rand|KSS]] [-v [n]]
 * <p/>
 * [Strategies] A strategy to apply the rules may be specified between : - FR :
 * Fair Roundrobin (R1;R2;...;Rn)* - UFR : UnFair Roudrobin (R1*;R2*;...;Rn*)* -
 * Rand : select randomly a rule between the non inert ones - KSS : Keep Small
 * Solution, favorise Reducer over Optimiser over Unknown over Expanser
 * <p/>
 * <p/>
 * [Debugging] Verbose levels (each level assumes all lower ones): 0 -> quiet 1
 * -> initial and final inert states 2 -> all inert solutions 3 -> reactions 4
 * -> reactions statistics 5 -> intermediate solutions (non inert) states 6 ->
 * tested permutations 7 -> strategy choices 8 -> all permutations
 * <p/>
 * By default, everything is turned off execept the initial state (argument) and
 * the last state (result).
 */
public class Hocli {

	public static Debug debug;

	/*private String strategyTag;

	private Hashtable<String, Class> allStrategies;*/


	/**
	 * Init the interpretor to defaults and with the command line options
	 *
	 * @throws Exception
	 */
	public static void init () throws Exception {
		/*initStrategies();
		setStrategy("FR");*/
		initDebugMessages();
	}


	/*private void initStrategies () {
		allStrategies = new Hashtable<String, Class>();
		allStrategies.put("FR", StrategyFairRoundRobin.class);
		allStrategies.put("UFR", StrategyUnFairRoundRobin.class);
		allStrategies.put("RAND", StrategyRandom.class);
		allStrategies.put("KSS", StrategyKeepSmallSolution.class);
	}*/


	/*private String allStrategies () {
		Enumeration it = allStrategies.keys();
		String s = "";
		while (it.hasMoreElements()) {
			s = s + it.nextElement() + "\n";
		}
		return s;
	}*/


	private static void initDebugMessages () {
		if (debug == null) {
			debug = new Debug();
			setVerboseLevel(1);
		}
	}


	public static void setVerboseLevel (int level) {
		debug.clearDebugSymbols();
		if (level >= 1) {
			debug.addDebugSymbol(Debug.DebugSymbol.SOLUTION_WHATISINSIDE);
		}
		if (level >= 2) {
			debug.addDebugSymbol(Debug.DebugSymbol.FIRST_LAST_STATES);
		}
		if (level >= 3) {
			debug.addDebugSymbol(Debug.DebugSymbol.SOLUTION_REDUCED);
		}
		if (level >= 4) {
			debug.addDebugSymbol(Debug.DebugSymbol.REACTIONS);
		}
		if (level >= 5) {
			debug.addDebugSymbol(Debug.DebugSymbol.REACTIONS_STATS);
		}
		if (level >= 6) {
			debug.addDebugSymbol(Debug.DebugSymbol.SOLUTION_PARTIAL);
		}
		if (level >= 7) {
			debug.addDebugSymbol(Debug.DebugSymbol.TESTED_PERM);
		}
		if (level >= 8) {
			debug.addDebugSymbol(Debug.DebugSymbol.STRATEGY);
		}
		if (level >= 9) {
			debug.addDebugSymbol(Debug.DebugSymbol.PERMUTATION);
		}
	}


	/*public void setStrategy (String str) {
		assert allStrategies.containsKey(str);
		strategyTag = str;
	}


	*//**
	 * Return a new strategy (Design Pattern Abstract Factory)
	 *
	 * @return a new strategy
	 * @throws Exception
	 *//*
	public Strategy newStrategy () throws Exception {
		Strategy strategy;
		Class stratClass;

		stratClass = allStrategies.get(strategyTag.toUpperCase());
		if (stratClass == null) {
			throw new Exception("Hocli.newStrategy(Solution) : Unknown strategy class '" + strategyTag + "'");
		}
		strategy = (Strategy) stratClass.newInstance();

		return strategy;
	}*/

} // class Hocli
