/*******************************************************************************
 * Pattern builder for pattern policyInfo.userFullRule
 * Generated by EMF-IncQuery
 * Should contribute a pattern-builder to extension point
 * org.eclipse.viatra2.emf.incquery.codegen.patternmatcher.builder 
 *******************************************************************************/

package patternbuilders.policyInfo;

import java.util.HashMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.viatra2.emf.incquery.runtime.IStatelessGeneratedRetePatternBuilder;
import org.eclipse.viatra2.emf.incquery.runtime.term.VPMTermEvaluator;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.boundary.AbstractEvaluator;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.construction.ReteContainerBuildable;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.construction.RetePatternBuildException;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.construction.Stub;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.matcher.IPatternMatcherContext;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.network.Receiver;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.network.Supplier;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.remote.Address;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.tuple.FlatTuple;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.tuple.Tuple;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.tuple.TupleMask;

/**
 * Generated automatically from pattern policyInfo.userFullRule
 */
public class PatternBuilderForuserFullRule implements IStatelessGeneratedRetePatternBuilder {
	@Override
	public Address<? extends Receiver> construct(
			ReteContainerBuildable<String> buildable,
			IPatternMatcherContext<String> context, String gtPattern)
			throws RetePatternBuildException {
		assert("policyInfo.userFullRule".equals(gtPattern));
		buildable = buildable.getNextContainer().putOnTab("policyInfo.userFullRule");
		
		final Address<? extends Receiver> var_308 = buildable.patternCollector("policyInfo.userFullRule");
		final Stub<Address<? extends Supplier>> var_309 = buildable.buildStartStub(new Object[] {}, new Object[] {});
		final Object var_310 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("rbac.rbac").getEClassifier("Permission"))).getEStructuralFeature("operations");
		final Stub<Address<? extends Supplier>> var_311 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"PERMISSION", "OPERATION"}), var_310);
		final TupleMask var_312 = new TupleMask(new int[] {}, 0);
		final TupleMask var_313 = new TupleMask(new int[] {}, 2);
		final TupleMask var_314 = new TupleMask(new int[] {0, 1}, 2);
		final Stub<Address<? extends Supplier>> var_315 = buildable.buildBetaNode(var_309, var_311, var_312, var_313, var_314, false);
		final Stub<Address<? extends Supplier>> var_316 = buildable.buildInjectivityChecker(var_315, 1, new int[] {0});
		final Object var_317 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("rbac.rbac").getEClassifier("Role"))).getEStructuralFeature("permissions");
		final Stub<Address<? extends Supplier>> var_318 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"ROLE", "PERMISSION"}), var_317);
		final TupleMask var_319 = new TupleMask(new int[] {0}, 2);
		final TupleMask var_320 = new TupleMask(new int[] {1}, 2);
		final TupleMask var_321 = new TupleMask(new int[] {0}, 2);
		final Stub<Address<? extends Supplier>> var_322 = buildable.buildBetaNode(var_316, var_318, var_319, var_320, var_321, false);
		final Stub<Address<? extends Supplier>> var_323 = buildable.buildInjectivityChecker(var_322, 1, new int[] {2});
		final Stub<Address<? extends Supplier>> var_324 = buildable.buildInjectivityChecker(var_323, 0, new int[] {2});
		final Object var_325 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("rbac.rbac").getEClassifier("User"))).getEStructuralFeature("assignedRoles");
		final Stub<Address<? extends Supplier>> var_326 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"USER", "ROLE"}), var_325);
		final TupleMask var_327 = new TupleMask(new int[] {2}, 3);
		final TupleMask var_328 = new TupleMask(new int[] {1}, 2);
		final TupleMask var_329 = new TupleMask(new int[] {0}, 2);
		final Stub<Address<? extends Supplier>> var_330 = buildable.buildBetaNode(var_324, var_326, var_327, var_328, var_329, false);
		final Stub<Address<? extends Supplier>> var_331 = buildable.buildInjectivityChecker(var_330, 1, new int[] {3});
		final Stub<Address<? extends Supplier>> var_332 = buildable.buildInjectivityChecker(var_331, 0, new int[] {3});
		final Stub<Address<? extends Supplier>> var_333 = buildable.buildInjectivityChecker(var_332, 2, new int[] {3});
		final Object var_334 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("rbac.rbac").getEClassifier("Operation"))).getEStructuralFeature("resources");
		final Stub<Address<? extends Supplier>> var_335 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"OPERATION", "OBJECT"}), var_334);
		final TupleMask var_336 = new TupleMask(new int[] {1}, 4);
		final TupleMask var_337 = new TupleMask(new int[] {0}, 2);
		final TupleMask var_338 = new TupleMask(new int[] {1}, 2);
		final Stub<Address<? extends Supplier>> var_339 = buildable.buildBetaNode(var_333, var_335, var_336, var_337, var_338, false);
		final Stub<Address<? extends Supplier>> var_340 = buildable.buildInjectivityChecker(var_339, 4, new int[] {1});
		final Stub<Address<? extends Supplier>> var_341 = buildable.buildInjectivityChecker(var_340, 4, new int[] {0});
		final Stub<Address<? extends Supplier>> var_342 = buildable.buildInjectivityChecker(var_341, 4, new int[] {2});
		final Stub<Address<? extends Supplier>> var_343 = buildable.buildInjectivityChecker(var_342, 4, new int[] {3});
		final TupleMask var_344 = new TupleMask(new int[] {3, 2, 0, 1, 4}, 5);
		final Stub<Address<? extends Supplier>> var_345 = buildable.buildTrimmer(var_343, var_344);
		buildable.buildConnection(var_345, var_308);
		return var_308;

	}
	
	HashMap<Object, Integer> posMapping;
	@Override
	public HashMap<Object, Integer> getPosMapping(String gtPattern) {
		assert("policyInfo.userFullRule".equals(gtPattern));
		if (posMapping == null) {
			posMapping = new HashMap<Object, Integer>();
			
			posMapping.put("USER", 0);
			posMapping.put("ROLE", 1);
			posMapping.put("PERMISSION", 2);
			posMapping.put("OPERATION", 3);
			posMapping.put("OBJECT", 4);

		}
		return posMapping;
	}
}