package fr.inria.hocl.core.hoclc2j;


import java.util.Set;

public class PrimaryCondition {

	String condition; // sub-condition Ci of a condition of the form
										// "C1 && C2 && ..."

	Set<String> variables; // variables occurring in condition


	public PrimaryCondition( String condition, Set<String> variables ) {
		this.condition = condition;
		this.variables = variables;
	}


	public boolean equals( Object o ) {
		return o instanceof PrimaryCondition
				&& ( (PrimaryCondition) o ).variables.equals( variables );
	}


	public void mergeAnd( PrimaryCondition pc ) {
		condition = condition + " && " + pc.condition;
		variables.addAll( pc.variables );
	}


	public void mergeOr( PrimaryCondition pc ) {
		condition = "(" + condition + ") || (" + pc.condition + ")";
		variables.addAll( pc.variables );
	}


	public String toString() {
		String s;
		s = "{\"" + condition + "\"," + variables + "}";
		return s;
	}

}
