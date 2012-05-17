/*******************************************************************************
 * Pattern signature of the policy.userFullRule pattern
 * Generated by EMF-IncQuery
 *******************************************************************************/

package signatures.policy;

import java.util.Arrays;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.viatra2.emf.incquery.runtime.api.IPatternSignature;
import org.eclipse.viatra2.emf.incquery.runtime.api.impl.BasePatternSignature;

/**
 * Pattern-specific signature object of the policy.userFullRule pattern.
 * Class fields correspond to parameters of the pattern. Fields with value null are considered unassigned.
 * Each instance is a (possibly partial) substitution of pattern parameters, 
 * usable e.g. to represent a match of the pattern in the result of a query, 
 * or to specify the bound (fixed) input parameters when issuing a query.
 */
@SuppressWarnings("unused")
public final class UserFullRuleSignature extends BasePatternSignature implements IPatternSignature {

	// constructor
	public UserFullRuleSignature(Object USER, Object ROLE, Object PERMISSION, Object OPERATION, Object OBJECT) {
		this.fUSER = USER;
		this.fROLE = ROLE;
		this.fPERMISSION = PERMISSION;
		this.fOPERATION = OPERATION;
		this.fOBJECT = OBJECT;	
	}

	//private attributes
	private Object fUSER;
	private Object fROLE;
	private Object fPERMISSION;
	private Object fOPERATION;
	private Object fOBJECT;
	
	// getter methods
	/** Returns the value of the parameter with the given name, or null if name is invalid. */
	@Override
	public Object get(String parameterName) {
		if ("USER".equals(parameterName)) return fUSER;
		if ("ROLE".equals(parameterName)) return fROLE;
		if ("PERMISSION".equals(parameterName)) return fPERMISSION;
		if ("OPERATION".equals(parameterName)) return fOPERATION;
		if ("OBJECT".equals(parameterName)) return fOBJECT;
		return null;
	}	
	
	public Object getValueOfUSER(){
		 return fUSER;
	}
	public Object getValueOfROLE(){
		 return fROLE;
	}
	public Object getValueOfPERMISSION(){
		 return fPERMISSION;
	}
	public Object getValueOfOPERATION(){
		 return fOPERATION;
	}
	public Object getValueOfOBJECT(){
		 return fOBJECT;
	}	
	
	// setter methods
	/** 
	 * Sets the parameter with the given name to the given value. 
	 * @returns true if successful, false if parameter name is invalid. May also fail and return false if the value type is incompatible. 
	 */
	@Override
	public boolean set(String parameterName, Object newValue) {
		if ("USER".equals(parameterName)) {
			fUSER = newValue;
			return true;
		}
		if ("ROLE".equals(parameterName)) {
			fROLE = newValue;
			return true;
		}
		if ("PERMISSION".equals(parameterName)) {
			fPERMISSION = newValue;
			return true;
		}
		if ("OPERATION".equals(parameterName)) {
			fOPERATION = newValue;
			return true;
		}
		if ("OBJECT".equals(parameterName)) {
			fOBJECT = newValue;
			return true;
		}
		return false;
	}
	
	public void setValueOfUSER(Object USER){
		 this.fUSER=USER;
	}
	public void setValueOfROLE(Object ROLE){
		 this.fROLE=ROLE;
	}
	public void setValueOfPERMISSION(Object PERMISSION){
		 this.fPERMISSION=PERMISSION;
	}
	public void setValueOfOPERATION(Object OPERATION){
		 this.fOPERATION=OPERATION;
	}
	public void setValueOfOBJECT(Object OBJECT){
		 this.fOBJECT=OBJECT;
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
		if (!UserFullRuleSignature.class.equals(obj.getClass()))
			return Arrays.deepEquals(toArray(), otherSig.toArray());
		UserFullRuleSignature other = (UserFullRuleSignature) obj;
		if (fUSER == null) {if (other.fUSER != null) return false;}
		else if (!fUSER.equals(other.fUSER)) return false;
		if (fROLE == null) {if (other.fROLE != null) return false;}
		else if (!fROLE.equals(other.fROLE)) return false;
		if (fPERMISSION == null) {if (other.fPERMISSION != null) return false;}
		else if (!fPERMISSION.equals(other.fPERMISSION)) return false;
		if (fOPERATION == null) {if (other.fOPERATION != null) return false;}
		else if (!fOPERATION.equals(other.fOPERATION)) return false;
		if (fOBJECT == null) {if (other.fOBJECT != null) return false;}
		else if (!fOBJECT.equals(other.fOBJECT)) return false;
		return true;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fUSER == null) ? 0 : fUSER.hashCode());
		result = prime * result + ((fROLE == null) ? 0 : fROLE.hashCode());
		result = prime * result + ((fPERMISSION == null) ? 0 : fPERMISSION.hashCode());
		result = prime * result + ((fOPERATION == null) ? 0 : fOPERATION.hashCode());
		result = prime * result + ((fOBJECT == null) ? 0 : fOBJECT.hashCode());
		return result;
	}

	@Override
	public String prettyPrint() {
		StringBuilder result = new StringBuilder();
		result.append("\"USER\"=" + prettyPrintValue(fUSER));
		result.append(", \"ROLE\"=" + prettyPrintValue(fROLE));
		result.append(", \"PERMISSION\"=" + prettyPrintValue(fPERMISSION));
		result.append(", \"OPERATION\"=" + prettyPrintValue(fOPERATION));
		result.append(", \"OBJECT\"=" + prettyPrintValue(fOBJECT));
		return result.toString();
	}

	// conversion and reflection
	/** Converts the signature to an array representation, with each pattern parameter at their respective position */
	@Override
	public Object[] toArray() {
		return new Object[] {fUSER, fROLE, fPERMISSION, fOPERATION, fOBJECT};
	}
	
	/** Identifies the name of the pattern for which this is a signature. */
	@Override
	public String patternName() {
		return "policy.userFullRule";
	}
	
	/** Returns the list of symbolic parameter names. */
	@Override
	public String[] parameterNames() {
		return parameterNames;
	}
	private static String[] parameterNames = {"USER", "ROLE", "PERMISSION", "OPERATION", "OBJECT"};
	
	
}
