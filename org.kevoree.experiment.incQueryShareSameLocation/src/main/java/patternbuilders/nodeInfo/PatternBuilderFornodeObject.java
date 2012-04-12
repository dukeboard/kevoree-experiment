/*******************************************************************************
 * Pattern builder for pattern nodeInfo.nodeObject
 * Generated by EMF-IncQuery
 * Should contribute a pattern-builder to extension point
 * org.eclipse.viatra2.emf.incquery.codegen.patternmatcher.builder 
 *******************************************************************************/

package patternbuilders.nodeInfo;

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
 * Generated automatically from pattern nodeInfo.nodeObject
 */
public class PatternBuilderFornodeObject implements IStatelessGeneratedRetePatternBuilder {
	@Override
	public Address<? extends Receiver> construct(
			ReteContainerBuildable<String> buildable,
			IPatternMatcherContext<String> context, String gtPattern)
			throws RetePatternBuildException {
		assert("nodeInfo.nodeObject".equals(gtPattern));
		buildable = buildable.getNextContainer().putOnTab("nodeInfo.nodeObject");
		
		final Address<? extends Receiver> var_30 = buildable.patternCollector("nodeInfo.nodeObject");
		final Stub<Address<? extends Supplier>> var_31 = buildable.buildStartStub(new Object[] {}, new Object[] {});
		final Object var_32 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://kevoree/1.0").getEClassifier("NamedElement"))).getEStructuralFeature("name");
		final Stub<Address<? extends Supplier>> var_33 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"TYPDEF", "TYPNAME"}), var_32);
		final TupleMask var_34 = new TupleMask(new int[] {}, 0);
		final TupleMask var_35 = new TupleMask(new int[] {}, 2);
		final TupleMask var_36 = new TupleMask(new int[] {0, 1}, 2);
		final Stub<Address<? extends Supplier>> var_37 = buildable.buildBetaNode(var_31, var_33, var_34, var_35, var_36, false);
		final Stub<Address<? extends Supplier>> var_38 = buildable.buildInjectivityChecker(var_37, 0, new int[] {1});
		final AbstractEvaluator var_39 = new AbstractEvaluator(){ 
			@Override 
			public Object doEvaluate(Tuple tuple) throws Exception { 
				return VPMTermEvaluator.equals(tuple.get(1).toString(),"AddressBook");
			}
		};
		final Stub<Address<? extends Supplier>> var_40 = buildable.buildPredicateChecker(var_39, null, new int[] {1}, var_38);
		final Object var_41 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://kevoree/1.0").getEClassifier("Instance"))).getEStructuralFeature("typeDefinition");
		final Stub<Address<? extends Supplier>> var_42 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"C", "TYPDEF"}), var_41);
		final TupleMask var_43 = new TupleMask(new int[] {0}, 2);
		final TupleMask var_44 = new TupleMask(new int[] {1}, 2);
		final TupleMask var_45 = new TupleMask(new int[] {0}, 2);
		final Stub<Address<? extends Supplier>> var_46 = buildable.buildBetaNode(var_40, var_42, var_43, var_44, var_45, false);
		final Stub<Address<? extends Supplier>> var_47 = buildable.buildInjectivityChecker(var_46, 2, new int[] {0});
		final Stub<Address<? extends Supplier>> var_48 = buildable.buildInjectivityChecker(var_47, 2, new int[] {1});
		final Object var_49 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://kevoree/1.0").getEClassifier("ContainerNode"))).getEStructuralFeature("components");
		final Stub<Address<? extends Supplier>> var_50 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"N", "C"}), var_49);
		final TupleMask var_51 = new TupleMask(new int[] {2}, 3);
		final TupleMask var_52 = new TupleMask(new int[] {1}, 2);
		final TupleMask var_53 = new TupleMask(new int[] {0}, 2);
		final Stub<Address<? extends Supplier>> var_54 = buildable.buildBetaNode(var_48, var_50, var_51, var_52, var_53, false);
		final Stub<Address<? extends Supplier>> var_55 = buildable.buildInjectivityChecker(var_54, 2, new int[] {3});
		final Stub<Address<? extends Supplier>> var_56 = buildable.buildInjectivityChecker(var_55, 3, new int[] {0});
		final Stub<Address<? extends Supplier>> var_57 = buildable.buildInjectivityChecker(var_56, 3, new int[] {1});
		final TupleMask var_58 = new TupleMask(new int[] {3}, 4);
		final Stub<Address<? extends Supplier>> var_59 = buildable.buildTrimmer(var_57, var_58);
		buildable.buildConnection(var_59, var_30);
		return var_30;

	}
	
	HashMap<Object, Integer> posMapping;
	@Override
	public HashMap<Object, Integer> getPosMapping(String gtPattern) {
		assert("nodeInfo.nodeObject".equals(gtPattern));
		if (posMapping == null) {
			posMapping = new HashMap<Object, Integer>();
			
			posMapping.put("N", 0);

		}
		return posMapping;
	}
}
