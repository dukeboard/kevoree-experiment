/*******************************************************************************
 * Pattern builder for pattern enforcementInfo.subjectsBinded
 * Generated by EMF-IncQuery
 * Should contribute a pattern-builder to extension point
 * org.eclipse.viatra2.emf.incquery.codegen.patternmatcher.builder 
 *******************************************************************************/

package patternbuilders.enforcementInfo;

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
 * Generated automatically from pattern enforcementInfo.subjectsBinded
 */
public class PatternBuilderForsubjectsBinded implements IStatelessGeneratedRetePatternBuilder {
	@Override
	public Address<? extends Receiver> construct(
			ReteContainerBuildable<String> buildable,
			IPatternMatcherContext<String> context, String gtPattern)
			throws RetePatternBuildException {
		assert("enforcementInfo.subjectsBinded".equals(gtPattern));
		buildable = buildable.getNextContainer().putOnTab("enforcementInfo.subjectsBinded");
		
		final Address<? extends Receiver> var_80 = buildable.patternCollector("enforcementInfo.subjectsBinded");
		final Stub<Address<? extends Supplier>> var_81 = buildable.buildStartStub(new Object[] {}, new Object[] {});
		final Object var_82 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://kevoree/1.0").getEClassifier("NamedElement"))).getEStructuralFeature("name");
		final Stub<Address<? extends Supplier>> var_83 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"TYPDEF", "TYPNAME"}), var_82);
		final TupleMask var_84 = new TupleMask(new int[] {}, 0);
		final TupleMask var_85 = new TupleMask(new int[] {}, 2);
		final TupleMask var_86 = new TupleMask(new int[] {0, 1}, 2);
		final Stub<Address<? extends Supplier>> var_87 = buildable.buildBetaNode(var_81, var_83, var_84, var_85, var_86, false);
		final Stub<Address<? extends Supplier>> var_88 = buildable.buildInjectivityChecker(var_87, 0, new int[] {1});
		final AbstractEvaluator var_89 = new AbstractEvaluator(){ 
			@Override 
			public Object doEvaluate(Tuple tuple) throws Exception { 
				return VPMTermEvaluator.equals(tuple.get(1).toString(),"AddressBookClient");
			}
		};
		final Stub<Address<? extends Supplier>> var_90 = buildable.buildPredicateChecker(var_89, null, new int[] {1}, var_88);
		final Object var_91 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://kevoree/1.0").getEClassifier("Instance"))).getEStructuralFeature("typeDefinition");
		final Stub<Address<? extends Supplier>> var_92 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"C", "TYPDEF"}), var_91);
		final TupleMask var_93 = new TupleMask(new int[] {0}, 2);
		final TupleMask var_94 = new TupleMask(new int[] {1}, 2);
		final TupleMask var_95 = new TupleMask(new int[] {0}, 2);
		final Stub<Address<? extends Supplier>> var_96 = buildable.buildBetaNode(var_90, var_92, var_93, var_94, var_95, false);
		final Stub<Address<? extends Supplier>> var_97 = buildable.buildInjectivityChecker(var_96, 2, new int[] {0});
		final Stub<Address<? extends Supplier>> var_98 = buildable.buildInjectivityChecker(var_97, 2, new int[] {1});
		final Object var_99 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://kevoree/1.0").getEClassifier("ComponentInstance"))).getEStructuralFeature("required");
		final Stub<Address<? extends Supplier>> var_100 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"C", "P"}), var_99);
		final TupleMask var_101 = new TupleMask(new int[] {2}, 3);
		final TupleMask var_102 = new TupleMask(new int[] {0}, 2);
		final TupleMask var_103 = new TupleMask(new int[] {1}, 2);
		final Stub<Address<? extends Supplier>> var_104 = buildable.buildBetaNode(var_98, var_100, var_101, var_102, var_103, false);
		final Stub<Address<? extends Supplier>> var_105 = buildable.buildInjectivityChecker(var_104, 2, new int[] {3});
		final Stub<Address<? extends Supplier>> var_106 = buildable.buildInjectivityChecker(var_105, 3, new int[] {0});
		final Stub<Address<? extends Supplier>> var_107 = buildable.buildInjectivityChecker(var_106, 3, new int[] {1});
		final Object var_108 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://kevoree/1.0").getEClassifier("MBinding"))).getEStructuralFeature("port");
		final Stub<Address<? extends Supplier>> var_109 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"B", "P"}), var_108);
		final TupleMask var_110 = new TupleMask(new int[] {3}, 4);
		final TupleMask var_111 = new TupleMask(new int[] {1}, 2);
		final TupleMask var_112 = new TupleMask(new int[] {0}, 2);
		final Stub<Address<? extends Supplier>> var_113 = buildable.buildBetaNode(var_107, var_109, var_110, var_111, var_112, false);
		final Stub<Address<? extends Supplier>> var_114 = buildable.buildInjectivityChecker(var_113, 4, new int[] {2});
		final Stub<Address<? extends Supplier>> var_115 = buildable.buildInjectivityChecker(var_114, 4, new int[] {3});
		final Stub<Address<? extends Supplier>> var_116 = buildable.buildInjectivityChecker(var_115, 4, new int[] {0});
		final Stub<Address<? extends Supplier>> var_117 = buildable.buildInjectivityChecker(var_116, 4, new int[] {1});
		final Object var_118 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://kevoree/1.0").getEClassifier("MBinding"))).getEStructuralFeature("hub");
		final Stub<Address<? extends Supplier>> var_119 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"B", "CHA"}), var_118);
		final TupleMask var_120 = new TupleMask(new int[] {4}, 5);
		final TupleMask var_121 = new TupleMask(new int[] {0}, 2);
		final TupleMask var_122 = new TupleMask(new int[] {1}, 2);
		final Stub<Address<? extends Supplier>> var_123 = buildable.buildBetaNode(var_117, var_119, var_120, var_121, var_122, false);
		final Stub<Address<? extends Supplier>> var_124 = buildable.buildInjectivityChecker(var_123, 4, new int[] {5});
		final Stub<Address<? extends Supplier>> var_125 = buildable.buildInjectivityChecker(var_124, 2, new int[] {5});
		final Stub<Address<? extends Supplier>> var_126 = buildable.buildInjectivityChecker(var_125, 5, new int[] {3});
		final Stub<Address<? extends Supplier>> var_127 = buildable.buildInjectivityChecker(var_126, 5, new int[] {0});
		final Stub<Address<? extends Supplier>> var_128 = buildable.buildInjectivityChecker(var_127, 5, new int[] {1});
		final TupleMask var_129 = new TupleMask(new int[] {2}, 6);
		final Stub<Address<? extends Supplier>> var_130 = buildable.buildTrimmer(var_128, var_129);
		buildable.buildConnection(var_130, var_80);
		return var_80;

	}
	
	HashMap<Object, Integer> posMapping;
	@Override
	public HashMap<Object, Integer> getPosMapping(String gtPattern) {
		assert("enforcementInfo.subjectsBinded".equals(gtPattern));
		if (posMapping == null) {
			posMapping = new HashMap<Object, Integer>();
			
			posMapping.put("C", 0);

		}
		return posMapping;
	}
}
