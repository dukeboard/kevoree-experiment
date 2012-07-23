/*******************************************************************************
 * Pattern builder for pattern policy.userRoleAssignment
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
 * Generated automatically from pattern policy.userRoleAssignment
 */
public class PatternBuilderForuserRoleAssignment implements IStatelessGeneratedRetePatternBuilder {
	@Override
	public Address<? extends Receiver> construct(
			ReteContainerBuildable<String> buildable,
			IPatternMatcherContext<String> context, String gtPattern)
			throws RetePatternBuildException {
		assert("policy.userRoleAssignment".equals(gtPattern));
		buildable = buildable.getNextContainer().putOnTab("policy.userRoleAssignment");
		
		final Address<? extends Receiver> var_767 = buildable.patternCollector("policy.userRoleAssignment");
		final Stub<Address<? extends Supplier>> var_768 = buildable.buildStartStub(new Object[] {}, new Object[] {});
		final Object var_769 = ((EClass) (EPackage.Registry.INSTANCE.getEPackage("http://policy/1.0").getEClassifier("User"))).getEStructuralFeature("roles");
		final Stub<Address<? extends Supplier>> var_770 = buildable.binaryEdgeTypeStub(new FlatTuple(new Object[] {"USER", "ROLE"}), var_769);
		final TupleMask var_771 = new TupleMask(new int[] {}, 0);
		final TupleMask var_772 = new TupleMask(new int[] {}, 2);
		final TupleMask var_773 = new TupleMask(new int[] {0, 1}, 2);
		final Stub<Address<? extends Supplier>> var_774 = buildable.buildBetaNode(var_768, var_770, var_771, var_772, var_773, false);
		final Stub<Address<? extends Supplier>> var_775 = buildable.buildInjectivityChecker(var_774, 1, new int[] {0});
		final TupleMask var_776 = new TupleMask(new int[] {0, 1}, 2);
		final Stub<Address<? extends Supplier>> var_777 = buildable.buildTrimmer(var_775, var_776);
		buildable.buildConnection(var_777, var_767);
		return var_767;

	}
	
	HashMap<Object, Integer> posMapping;
	@Override
	public HashMap<Object, Integer> getPosMapping(String gtPattern) {
		assert("policy.userRoleAssignment".equals(gtPattern));
		if (posMapping == null) {
			posMapping = new HashMap<Object, Integer>();
			
			posMapping.put("USER", 0);
			posMapping.put("ROLE", 1);

		}
		return posMapping;
	}
}