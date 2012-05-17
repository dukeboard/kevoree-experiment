/*******************************************************************************
 * EMF Specific API of the policy.userOperation pattern
 * Generated by EMF-IncQuery
 *******************************************************************************/

package patternmatchers.policy;

import java.util.Collection;

import org.eclipse.viatra2.emf.incquery.runtime.api.impl.BaseGeneratedMatcher;
import org.eclipse.viatra2.emf.incquery.runtime.api.impl.BaseMatcherFactory;
import org.eclipse.viatra2.emf.incquery.runtime.api.IPatternSignature;
import org.eclipse.viatra2.emf.incquery.runtime.api.IMatcherFactory;
import org.eclipse.viatra2.emf.incquery.runtime.api.IncQueryMatcher;
import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.matcher.ReteEngine;
import org.eclipse.viatra2.gtasm.patternmatcher.incremental.rete.tuple.Tuple;

import signatures.policy.UserOperationSignature;

/**
 * Generated domain-specific pattern matcher API of of the policy.userOperation pattern.
 * Please instantiate using the static field FACTORY.
 */
@SuppressWarnings("unused")
public class UserOperationMatcher extends BaseGeneratedMatcher<UserOperationSignature> implements IncQueryMatcher<UserOperationSignature>{

	public final static IMatcherFactory<UserOperationSignature,UserOperationMatcher> FACTORY = new Factory();
	public static class Factory extends BaseMatcherFactory<UserOperationSignature,UserOperationMatcher> {
			@Override
			public UserOperationMatcher instantiate(ReteEngine<String> reteEngine) throws IncQueryRuntimeException {
				return new UserOperationMatcher(reteEngine);
			}
			@Override
			public String getPatternName() {
				return "policy.userOperation";
			}
		} 
			
	
	/** 
	 * Returns the set of all pattern matches given some fixed parameters.
	 * @param USER the fixed value of pattern parameter USER, or null if not bound.
	 * @param OPERATIONNAME the fixed value of pattern parameter OPERATIONNAME, or null if not bound.
	 * @return matches represented as an array containing the values of each parameter.
	 */
	public Collection<Object[]> getAllMatchesAsArray(Object USER, Object OPERATIONNAME) {
		return getAllMatchesAsArray(new Object[] {USER, OPERATIONNAME});
	}
	
	/** 
	 * Returns the set of all pattern matches given some fixed parameters.
	 * @param USER the fixed value of pattern parameter USER, or null if not bound.
	 * @param OPERATIONNAME the fixed value of pattern parameter OPERATIONNAME, or null if not bound.
	 * @return matches represented as a UserOperationSignature object.
	 */
	public Collection<UserOperationSignature> getAllMatchesAsSignature(Object USER, Object OPERATIONNAME) {
		return getAllMatchesAsSignature(new Object[] {USER, OPERATIONNAME});
	}

	/** 
	 * Returns an arbitrary pattern match given some fixed parameters.
	 * Neither determinism nor randomness of selection is guaranteed.
	 * @param USER the fixed value of pattern parameter USER, or null if not bound.
	 * @param OPERATIONNAME the fixed value of pattern parameter OPERATIONNAME, or null if not bound.
	 * @return a match represented as an array containing the values of each parameter, or null if no match is found.
	 */
	public Object[] getOneMatchAsArray(Object USER, Object OPERATIONNAME) {
		return getOneMatchAsArray(new Object[] {USER, OPERATIONNAME});
	}
	
	/** 
	 * Returns an arbitrary pattern match given some fixed parameters.
	 * Neither determinism nor randomness of selection is guaranteed.
	 * @param USER the fixed value of pattern parameter USER, or null if not bound.
	 * @param OPERATIONNAME the fixed value of pattern parameter OPERATIONNAME, or null if not bound.
	 * @return a match represented as a UserOperationSignature object, or null if no match is found.
	 */
	public UserOperationSignature getOneMatchAsSignature(Object USER, Object OPERATIONNAME) {
		return getOneMatchAsSignature(new Object[] {USER, OPERATIONNAME});
	}
	
	/**
	 * Indicates whether the given combination of specified pattern parameters constitute a valid pattern match,
	 * 	under any possible substitution of the unspecified parameters.
	 * @param USER the fixed value of pattern parameter USER, or null if not bound.
	 * @param OPERATIONNAME the fixed value of pattern parameter OPERATIONNAME, or null if not bound.
	 * @return true if the input is a valid (partial) match of the pattern.
	 */
	public boolean hasMatch(Object USER, Object OPERATIONNAME) {
		return hasMatch(new Object[] {USER, OPERATIONNAME});
	}
	
	/** 
	 * Returns the number of all pattern matches given some fixed parameters.
	 * @param USER the fixed value of pattern parameter USER, or null if not bound.
	 * @param OPERATIONNAME the fixed value of pattern parameter OPERATIONNAME, or null if not bound.
	 * @return the number of pattern matches found.
	 */	
	public int countMatches(Object USER, Object OPERATIONNAME) {
		return countMatches(new Object[] {USER, OPERATIONNAME});
	}
	


	/* (non-Javadoc)
	 * @see org.eclipse.viatra2.emf.incquery.runtime.api.IncQueryMatcher#getPatternName()
	 */
	@Override
	public String getPatternName() {
		return "policy.userOperation";
	}

	private static final String[] paramNames = new String[] {"USER", "OPERATIONNAME"};
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
	protected UserOperationSignature tupleToSignature(Tuple t) {
		return new UserOperationSignature(t.get(0), t.get(1));
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.viatra2.emf.incquery.runtime.api.IncQueryMatcher#arrayToSignature(java.lang.Object[])
	 */
	@Override
	public UserOperationSignature arrayToSignature(Object[] signature) {
		return new UserOperationSignature(signature[0], signature[1]);
	}	
	
	private UserOperationMatcher(ReteEngine<String> engine) throws IncQueryRuntimeException {
		super(engine, "policy.userOperation");
	}
		
}
