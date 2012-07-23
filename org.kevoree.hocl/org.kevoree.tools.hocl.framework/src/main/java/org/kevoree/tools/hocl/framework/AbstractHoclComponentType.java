package org.kevoree.tools.hocl.framework;

import fr.inria.hocl.api.Solution;
import fr.inria.hocl.api.StrategyManager;
import fr.inria.hocl.core.hocli.Hocli;
import org.kevoree.framework.AbstractComponentType;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 15/03/12
 * Time: 18:19
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public abstract class AbstractHoclComponentType extends AbstractComponentType {

	private Solution solution;

	public Solution getSolution() {
		return solution;
	}

	public void initializeSolution() throws Exception {
		Hocli.init();
		StrategyManager.init();

		// build the solution using reflexivity on the name of the implementing class{
		try {
			Class clazz = Class.forName(this.getClass().getName() + "_gen");
			solution = (Solution)clazz.newInstance();
		} catch (InstantiationException e) {
			throw new Exception("Unable to initialize hocl engine", e);
		} catch (IllegalAccessException e) {
			throw new Exception("Unable to initialize hocl engine", e);
		} catch (ClassNotFoundException e) {
			throw new Exception("Unable to initialize hocl engine", e);
		}
	}
}
