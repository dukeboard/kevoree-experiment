/*******************************************************************************
 * Pattern builder for pattern policy.userFullRule
 * Generated by EMF-IncQuery
 * Should contribute a pattern-builder to extension point
 * org.eclipse.viatra2.emf.incquery.codegen.patternmatcher.builder 
 *******************************************************************************/

package patternbuilders.policy;

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
 * Generated automatically from pattern policy.userFullRule
 */
public class PatternBuilderForuserFullRule implements IStatelessGeneratedRetePatternBuilder {
	@Override
	public Address<? extends Receiver> construct(
			ReteContainerBuildable<String> buildable,
			IPatternMatcherContext<String> context, String gtPattern)
			throws RetePatternBuildException {
		assert("policy.userFullRule".equals(gtPattern));
		buildable = buildable.getNextContainer().putOnTab("policy.userFullRule");
		
		final Address<? extends Receiver> var_0 = buildable.patternCollector("policy.userFullRule");
		final Stub<Address<? extends Supplier>> var_1 = buildable.buildStartStub(new Object[] {}, new Object[] {});
		final Object var_2 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://policy/1.0").getEClassifier("Permission"))).getEStructuralFeature("operations");
		final Stub<Address<? extends Supplier>> var_3 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"PERMISSION", "OPERATION"}), var_2);
		final TupleMask var_4 = new TupleMask(new int[] {}, 0);
		final TupleMask var_5 = new TupleMask(new int[] {}, 2);
		final TupleMask var_6 = new TupleMask(new int[] {0, 1}, 2);
		final Stub<Address<? extends Supplier>> var_7 = buildable.buildBetaNode(var_1, var_3, var_4, var_5, var_6, false);
		final Stub<Address<? extends Supplier>> var_8 = buildable.buildInjectivityChecker(var_7, 1, new int[] {0});
		final Object var_9 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://policy/1.0").getEClassifier("Role"))).getEStructuralFeature("permissions");
		final Stub<Address<? extends Supplier>> var_10 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"ROLE", "PERMISSION"}), var_9);
		final TupleMask var_11 = new TupleMask(new int[] {0}, 2);
		final TupleMask var_12 = new TupleMask(new int[] {1}, 2);
		final TupleMask var_13 = new TupleMask(new int[] {0}, 2);
		final Stub<Address<? extends Supplier>> var_14 = buildable.buildBetaNode(var_8, var_10, var_11, var_12, var_13, false);
		final Stub<Address<? extends Supplier>> var_15 = buildable.buildInjectivityChecker(var_14, 1, new int[] {2});
		final Stub<Address<? extends Supplier>> var_16 = buildable.buildInjectivityChecker(var_15, 0, new int[] {2});
		final Object var_17 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://policy/1.0").getEClassifier("User"))).getEStructuralFeature("roles");
		final Stub<Address<? extends Supplier>> var_18 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"USER", "ROLE"}), var_17);
		final TupleMask var_19 = new TupleMask(new int[] {2}, 3);
		final TupleMask var_20 = new TupleMask(new int[] {1}, 2);
		final TupleMask var_21 = new TupleMask(new int[] {0}, 2);
		final Stub<Address<? extends Supplier>> var_22 = buildable.buildBetaNode(var_16, var_18, var_19, var_20, var_21, false);
		final Stub<Address<? extends Supplier>> var_23 = buildable.buildInjectivityChecker(var_22, 1, new int[] {3});
		final Stub<Address<? extends Supplier>> var_24 = buildable.buildInjectivityChecker(var_23, 0, new int[] {3});
		final Stub<Address<? extends Supplier>> var_25 = buildable.buildInjectivityChecker(var_24, 2, new int[] {3});
		final Object var_26 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://policy/1.0").getEClassifier("Operation"))).getEStructuralFeature("objects");
		final Stub<Address<? extends Supplier>> var_27 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"OPERATION", "OBJECT"}), var_26);
		final TupleMask var_28 = new TupleMask(new int[] {1}, 4);
		final TupleMask var_29 = new TupleMask(new int[] {0}, 2);
		final TupleMask var_30 = new TupleMask(new int[] {1}, 2);
		final Stub<Address<? extends Supplier>> var_31 = buildable.buildBetaNode(var_25, var_27, var_28, var_29, var_30, false);
		final Stub<Address<? extends Supplier>> var_32 = buildable.buildInjectivityChecker(var_31, 4, new int[] {1});
		final Stub<Address<? extends Supplier>> var_33 = buildable.buildInjectivityChecker(var_32, 4, new int[] {0});
		final Stub<Address<? extends Supplier>> var_34 = buildable.buildInjectivityChecker(var_33, 4, new int[] {2});
		final Stub<Address<? extends Supplier>> var_35 = buildable.buildInjectivityChecker(var_34, 4, new int[] {3});
		final TupleMask var_36 = new TupleMask(new int[] {3, 2, 0, 1, 4}, 5);
		final Stub<Address<? extends Supplier>> var_37 = buildable.buildTrimmer(var_35, var_36);
		buildable.buildConnection(var_37, var_0);
		return var_0;

	}
	
	HashMap<Object, Integer> posMapping;
	@Override
	public HashMap<Object, Integer> getPosMapping(String gtPattern) {
		assert("policy.userFullRule".equals(gtPattern));
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