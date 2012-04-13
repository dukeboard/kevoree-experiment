/*******************************************************************************
 * EMF Specific API of the enforcementInfo.subjectsBinded pattern
 * Generated by EMF-IncQuery
 *******************************************************************************/

package patternmatchers.enforcementInfo;

import java.util.Collection;

import org.eclipse.viatra2.emf.incquery.runtime.api.impl.BaseGeneratedMatcher;
import org.eclipse.viatra2.emf.incquery.runtime.api.impl.BaseMatcherFactory;
import org.eclipse.viatra2.emf.incquery.runtime.api.IPatternSignature;
import org.eclipse.viatra2.emf.incquery.runtime.api.IMatcherFactory;
import org.eclipse.viatra2.emf.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.matcher.ReteEngine;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.tuple.Tuple;

import signatures.enforcementInfo.SubjectsBindedSignature;

/**
 * Generated domain-specific pattern matcher API of of the enforcementInfo.subjectsBinded pattern.
 * Please instantiate using the static field FACTORY.
 */
@SuppressWarnings("unused")
public class SubjectsBindedMatcher extends BaseGeneratedMatcher<SubjectsBindedSignature> implements IncQueryMatcher<SubjectsBindedSignature>{

	public final static IMatcherFactory<SubjectsBindedSignature,SubjectsBindedMatcher> FACTORY = new Factory();
	public static class Factory extends BaseMatcherFactory<SubjectsBindedSignature,SubjectsBindedMatcher> {
			@Override
			public SubjectsBindedMatcher instantiate(ReteEngine<String> reteEngine) throws IncQueryRuntimeException {
				return new SubjectsBindedMatcher(reteEngine);
			}
			@Override
			public String getPatternName() {
				return "enforcementInfo.subjectsBinded";
			}
		} 
			
	
	/** 
	 * Returns the set of all pattern matches given some fixed parameters.
	 * @param C the fixed value of pattern parameter C, or null if not bound.
	 * @return matches represented as an array containing the values of each parameter.
	 */
	public Collection<Object[]> getAllMatchesAsArray(Object C) {
		return getAllMatchesAsArray(new Object[] {C});
	}
	
	/** 
	 * Returns the set of all pattern matches given some fixed parameters.
	 * @param C the fixed value of pattern parameter C, or null if not bound.
	 * @return matches represented as a SubjectsBindedSignature object.
	 */
	public Collection<SubjectsBindedSignature> getAllMatchesAsSignature(Object C) {
		return getAllMatchesAsSignature(new Object[] {C});
	}

	/** 
	 * Returns an arbitrary pattern match given some fixed parameters.
	 * Neither determinism nor randomness of selection is guaranteed.
	 * @param C the fixed value of pattern parameter C, or null if not bound.
	 * @return a match represented as an array containing the values of each parameter, or null if no match is found.
	 */
	public Object[] getOneMatchAsArray(Object C) {
		return getOneMatchAsArray(new Object[] {C});
	}
	
	/** 
	 * Returns an arbitrary pattern match given some fixed parameters.
	 * Neither determinism nor randomness of selection is guaranteed.
	 * @param C the fixed value of pattern parameter C, or null if not bound.
	 * @return a match represented as a SubjectsBindedSignature object, or null if no match is found.
	 */
	public SubjectsBindedSignature getOneMatchAsSignature(Object C) {
		return getOneMatchAsSignature(new Object[] {C});
	}
	
	/**
	 * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
	 * 	under any possible substitution of the unspecified parameters.
	 * @param C the fixed value of pattern parameter C, or null if not bound.
	 * @return true if the input is a valid (partial) match of the pattern.
	 */
	public boolean hasMatch(Object C) {
		return hasMatch(new Object[] {C});
	}
	
	/** 
	 * Returns the number of all pattern matches given some fixed parameters.
	 * @param C the fixed value of pattern parameter C, or null if not bound.
	 * @return the number of pattern matches found.
	 */	
	public int countMatches(Object C) {
		return countMatches(new Object[] {C});
	}
	


	/* (non-Javadoc)
	 * @see org.eclipse.viatra2.emf.incquery.runtime.api.IncQueryMatcher#getPatternName()
	 */
	@Override
	public String getPatternName() {
		return "enforcementInfo.subjectsBinded";
	}

	private static final String[] paramNames = new String[] {"C"};
	/* (non-Javadoc)
	 * @see org.eclipse.viatra2.emf.incquery.runtime.api.IncQueryMatcher#getParameterNames()
	 */
	@Override
	public String[] getParameterNames() {
		return paramNames;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.viatra2.emf.incquery.runtime.api.impl.BaseGeneratedMatcher#tupleToSignature(org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.tuple.Tuple)
	 */
	@Override
	protected SubjectsBindedSignature tupleToSignature(Tuple t) {
		return new SubjectsBindedSignature(t.get(0));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.viatra2.emf.incquery.runtime.api.IncQueryMatcher#arrayToSignature(java.lang.Object[])
	 */
	@Override
	public SubjectsBindedSignature arrayToSignature(Object[] signature) {
		return new SubjectsBindedSignature(signature[0]);
	}	
	
	private SubjectsBindedMatcher(ReteEngine<String> engine) throws IncQueryRuntimeException {
		super(engine, "enforcementInfo.subjectsBinded");
	}
		
}
