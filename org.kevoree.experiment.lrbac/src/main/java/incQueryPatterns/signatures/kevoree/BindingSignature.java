/*******************************************************************************
 * Pattern signature of the enforcementInfo.binding pattern
 * Generated by EMF-IncQuery
 *******************************************************************************/

package incQueryPatterns.signatures.kevoree;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.viatra2.emf.incquery.runtime.api.IPatternSignature;
import org.eclipse.viatra2.emf.incquery.runtime.api.impl.BasePatternSignature;

/**
 * Pattern-specific signature object of the enforcementInfo.binding pattern.
 * Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters, 
 * usable e.g. to represent a match of the pattern in the result of a query, 
 * or to specify the bound (fixed) input parameters when issuing a query.
 */
@SuppressWarnings("unused")
public final class BindingSignature extends BasePatternSignature implements IPatternSignature {

	// constructor
	public BindingSignature(Object B) {
		this.fB = B;	
	}

	//private attributes
	private Object fB;
	
	// getter methods
	/** Returns the value of the parameter with the given name, or null if name is invalid. */
	@Override
	public Object get(String parameterName) {
		if ("B".equals(parameterName)) return fB;
		return null;
	}	
	
	public Object getValueOfB(){
		 return fB;
	}	
	
	// setter methods
	/** 
	 * Sets the parameter with the given name to the given value. 
	 * @returns true if successful, false if parameter name is invalid. May also fail and return false if the value type is incompatible. 
	 */
	@Override
	public boolean set(String parameterName, Object newValue) {
		if ("B".equals(parameterName)) {
			fB = newValue;
			return true;
		}
		return false;
	}
	
	public void setValueOfB(Object B){
		 this.fB=B;
	}

	// overridden prettyPrint(), hashCode(), equals() with Tuple-semantics
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (! (obj instanceof IPatternSignature))
			return false;
		IPatternSignature otherSig = (IPatternSignature) obj;
		if (!patternName().equals(otherSig.patternName()))
			return false;
		if (!BindingSignature.class.equals(obj.getClass()))
			return Arrays.deepEquals(toArray(), otherSig.toArray());
		BindingSignature other = (BindingSignature) obj;
		if (fB == null) {if (other.fB != null) return false;}
		else if (!fB.equals(other.fB)) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fB == null) ? 0 : fB.hashCode());
		return result;
	}

	@Override
	public String prettyPrint() {
		StringBuilder result = new StringBuilder();
		result.append("\"B\"=" + prettyPrintValue(fB));
		return result.toString();
	}

	// conversion and reflection
	/** Converts the signature to an array representation, with each pattern parameter at their respective position */
	@Override
	public Object[] toArray() {
		return new Object[] {fB};
	}
	
	/** Identifies the name of the pattern for which this is a signature. */
	@Override
	public String patternName() {
		return "enforcementInfo.binding";
	}
	
	/** Returns the list of symbolic parameter names. */
	@Override
	public String[] parameterNames() {
		return parameterNames;
	}
	private static String[] parameterNames = {"B"};
	
	
}