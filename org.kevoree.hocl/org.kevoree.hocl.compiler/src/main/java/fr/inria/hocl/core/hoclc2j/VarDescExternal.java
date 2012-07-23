package fr.inria.hocl.core.hoclc2j;


import java.util.HashSet;
import java.util.Set;

public class VarDescExternal implements VarDesc {

	private String ident;

	private String javaType;

	String condition; // reaction condition for this variable

	Set<String> conditionVars; // variables that occurs in condition

	private int nbOccurrences; // cf varDesc reordering according the number of
															// occurrences in condition

	private boolean readOnly;


	public VarDescExternal( String ident, String javaType ) {
		this.ident = ident;
		this.javaType = primitiveToObject( javaType );
		condition = "";
		conditionVars = new HashSet<String>();
		nbOccurrences = 0;
		readOnly = false;
	}


	public String toString() {
		return "VarDescExternal of " + ident;
	}


	private String primitiveToObject( String primType ) {
		String res = primType;
		if( primType.equals( "int" ) ) {
			res = "Integer";
		} else if( primType.equals( "long" ) ) {
			res = "Long";
		} else if( primType.equals( "boolean" ) ) {
			res = "Boolean";
		} else if( primType.equals( "float" ) ) {
			res = "Float";
		} else if( primType.equals( "double" ) ) {
			res = "Double";
		}
		return res;
	}


	/*
	 * public HoclType getType(){ return HoclType.EXTERNAL; }
	 */

	public VarDesc lookUp( String ident ) {
		VarDesc res;
		res = null;
		if( this.ident.equals( ident ) ) {
			res = this;
		}
		return res;
	}


	public boolean equals( VarDesc varDesc ) {
		boolean equals = false;

		if( varDesc instanceof VarDescExternal) {
			equals = (((VarDescExternal) varDesc).ident.equals(ident));
		}
		return equals;
	}


	public String generatePatClass( Pattern allDesc, Set<String> vars,
			String atomIterator ) {
		String s, s2;
		Set<String> declVars = new HashSet<String>();
		declVars.addAll( conditionVars );
		declVars.remove( ident );
		String comment = ( conditionVars.contains( ident ) ? "" : "//" );

		s2 = ""; // allDesc.generateDeclarations(declVars, allDesc, "permutation");
							// // FIXME: "permutation"
		s = "class AtomIterator_" + ident + " extends IteratorForExternal {\n"
				+ "\t@Override\n" + "\tpublic boolean conditionSatisfied() {\n"
				+ "\t\tAtom atom;\n" + "\t\tboolean satisfied;\n" + "\t\t" + comment
				+ javaType + " " + ident + ";\n"
				+ "\t\tatom = iterator.getElement();\n" + "\t\tsatisfied = false;\n"
				+ "\t\tif (atom instanceof ExternalObject\n"
				+ "\t\t  && ((ExternalObject)atom).getObject() instanceof " + javaType
				+ ") {\n" + "\t\t\t" + comment + ident + " = (" + javaType
				+ ") ((ExternalObject)atom).getObject();\n"
				+ ( s2.length() > 0 ? CodeGeneratorHelper.indentCode( 3, s2 ) + "\n" : "" )
				+ "\t\t\tsatisfied = " + condition + ";\n" + "\t\t}\n" + // end of if
				"\t\treturn satisfied;\n" + "\t}\n" + // end of conditionSatisfied
				"\n} // class AtomIterator_" + ident;
		return s;
	}


	public String generateNewPat() {
		return "new AtomIterator_" + ident + "()";
	}


	public String generateVarDecl() {
		String decl;
		decl = javaType + " " + ident;
		return decl;
	}


	public String generateIdent() {
		return ident;
	}


	public String generateDeclarations( String ident, Pattern allDesc,
			String atomIterator ) {
		String decl = "";
		if( this.ident.equals( ident ) ) {
			decl = ident + " = (" + javaType + ")((IteratorForExternal)"
					+ atomIterator + ").getObject();";
		}
		return decl;
	}


	public void applyCondOfBool() {
		if( condition.length() == 0 ) {
			condition = "true";
		}
	}


	public void pushCond( ReactionCondition decompCond ) {
		if( condition.length() > 0 ) {
			// decompCond.add(new PrimaryCondition(condition,conditionVars));
			condition = "";
		}
	}


	public Set<String> getVars() {
		Set<String> s = new HashSet<String>();
		s.add( ident );
		return s;
	}


	public Set<String> getJokers() {
		return new HashSet<String>();
	}


	public void incrNbOcc() {
		nbOccurrences++;
	}


	public int getNbOcc() {
		return nbOccurrences;
	}


	public void applyCond( Set<String> assignedVars, ReactionCondition decompCond ) {
		// Iterator<PrimaryCondition> itCond;
		// PrimaryCondition primCond;
		/*
		 * itCond = decompCond.iterator(); while(itCond.hasNext()){ primCond =
		 * itCond.next(); assignedVars.add(ident);
		 * if(assignedVars.containsAll(primCond.variables)){ if(condition.length()
		 * == 0){ condition = primCond.condition; } else { condition = condition +
		 * " && " + primCond.condition; } conditionVars.addAll(primCond.variables);
		 * itCond.remove(); // so that this condition is checked once } }
		 */
	}


	public void setReadOnly() {
		readOnly = true;
	}


	public void setRewrite() {
		readOnly = false;
	}


	public boolean isReadOnly() {
		return readOnly;
	}

} // class VarDescExternal
