package fr.inria.hocl.api;

import fr.inria.hocl.strategy.StrategyFairRoundRobin;
import fr.inria.hocl.strategy.StrategyKeepSmallSolution;
import fr.inria.hocl.strategy.StrategyRandom;
import fr.inria.hocl.strategy.StrategyUnFairRoundRobin;

import java.util.Hashtable;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 16/03/12
 * Time: 13:49
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public class StrategyManager {


	private static String strategyTag;

	private static Hashtable<String, Class> allStrategies;


	public static void setStrategy (String str) {
		if (allStrategies.containsKey(str)) {
			strategyTag = str;
		}
	}

	/**
	 * Init the Strategy Manager to defaults
	 */
	public static void init () {
		if (strategyTag == null) {
			initStrategies();
			setStrategy("FR");
		}
	}


	private static void initStrategies () {
		allStrategies = new Hashtable<String, Class>();
		allStrategies.put("FR", StrategyFairRoundRobin.class);
		allStrategies.put("UFR", StrategyUnFairRoundRobin.class);
		allStrategies.put("RAND", StrategyRandom.class);
		allStrategies.put("KSS", StrategyKeepSmallSolution.class);
	}

	/**
	 * Return a new strategy (Design Pattern Abstract Factory)
	 *
	 * @return a new strategy
	 */
	public static Strategy newStrategy () {
		Class stratClass = allStrategies.get(strategyTag.toUpperCase());
		if (stratClass == null) {
			return new StrategyFairRoundRobin();
		}
		try {
			return (Strategy) stratClass.newInstance();
		} catch (InstantiationException e) {
			return new StrategyFairRoundRobin();
		} catch (IllegalAccessException e) {
			return new StrategyFairRoundRobin();
		}
	}
}
