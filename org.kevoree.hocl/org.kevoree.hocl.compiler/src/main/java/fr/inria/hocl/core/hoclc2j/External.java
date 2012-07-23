package fr.inria.hocl.core.hoclc2j;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

/**
 * Descriptor of an external Java object (i.e. not a Java object of the
 * interpretor)
 * 
 */
public class External implements Atom {

	// private static int varCounter = 0;

	String javaExpression;

	private Set<Symbols> occuringVars; // variables occuring in the Java
																			// expression

	String javaType; // Java type of the expression

	private Access access;


	public String getJavaType() {
		return this.javaType;
	}


	public String getJavaExpression() {
		return this.javaExpression;
	}


	public External() {
		javaExpression = "";
		occuringVars = new HashSet<Symbols>();
		access = Access.REWRITE;
	}


	public String generateDeclarationInit( int position, SymbolsTable symbolsTable ) {
		return ""; // external: no declaration no init
	}


	public String generateReference() {
		return "new ExternalObject(" + javaExpression + ")";
	}


	public void addVar( AtomVarPat atomVarPat ) {
		occuringVars.add( atomVarPat );
	}


	public void addAllVars( External ext ) {
		occuringVars.addAll( ext.occuringVars );
	}


	public Set<Symbols> getFreeVars() {
		return occuringVars;
	}


	public String toString() {
		return javaExpression;
	}


	public void findReadOnlyReactives() {
		// nothing to do
	}


	public void setReadOnly() {
		access = Access.READ_ONLY;
	}


	public boolean isReadOnly() {
		return access == Access.READ_ONLY;
	}


	public String getType() {
		return "external";
	}


	public LinkedList<String> getElementTypes() {
		LinkedList<String> s = new LinkedList<String>();

		String type = this.getJavaType();

		if( type == null || type.equalsIgnoreCase( "null" ) ) {
			return null;
		}

		if( this.toString().length() > 3
				&& this.toString().substring( 0, 3 ).equals( "new" ) ) {
			String[] t = this.toString().split( "\\(" );
			String[] l = t[0].split( " " );
			type = l[1];
		}
		s.add( type + "-Java Object" );
		return s;
	}

}
