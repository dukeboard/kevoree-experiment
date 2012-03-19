package fr.inria.hocl.core.hoclc2j;


import java.util.Set;

// import java.util.*;

public interface VarDesc {

	// public HoclType getType();

	public VarDesc lookUp (String ident);


	/**
	 * 
	 * @param varDesc
	 * @return true if this var desc describes the same var than varDesc
	 */
	public boolean equals(VarDesc varDesc);


	public String generatePatClass(Pattern allDesc, Set<String> vars,
			String permutation);


	public String generateNewPat ();


	public String generateVarDecl ();


	public String generateIdent ();


	public String generateDeclarations(String ident, Pattern allDesc,
			String atomIterator);


	public void pushCond(ReactionCondition decompCond);


	public void applyCondOfBool ();


	public Set<String> getVars ();


	public Set<String> getJokers ();


	public void incrNbOcc ();


	public int getNbOcc ();


	public void applyCond(Set<String> assignedVars, ReactionCondition decompCond);


	/**
	 * This "Var" will not be modified in the reaction
	 * 
	 */
	public void setReadOnly ();

	/**
	 * This "Var" may be modified in the reaction
	 * 
	 */
	// public void setRewrite();

	/**
	 * 
	 * @return true if this "Var" is read-only
	 */
	// public boolean isReadOnly();

	/**
	 * 
	 * @return the Java code corresponding to a reference to this varDesc
	 */
	// public String reference();

} // interface VarDesc

/*
 * HoclType type;
 * 
 * String ident; // if type is EXTERNAL, REACTION_RULE or JOKER
 * 
 * // if type is EXTERNAL String javaType; String condition; // reaction
 * condition for this variable Set<String> conditionVars; // variables that
 * occurs in condition int nbOccurences; // number of "occurrences" in the whole
 * condition
 * 
 * // if type is REACTION_RULE String reactionName; // if pattern for a reaction
 * 
 * // if type is SOLUTION or TUPLE PatternDesc subPatternDesc; // if pattern for
 * inert sub-solution, or tuple of size > 1 Set<String> subVars; // variables of
 * external occurring in subPattern
 * 
 * public VarDesc(){ // FIXME: transitional }
 * 
 * public VarDesc(String ident, HoclType type){ this.ident = ident; this.type =
 * type; if(type == HoclType.SOLUTION || type == HoclType.TUPLE){ subPatternDesc
 * = new PatternDesc(); } condition = new String(); subVars = new
 * HashSet<String>(); conditionVars = new HashSet<String>(); }
 * 
 * 
 * static class OccurrencesComparator implements Comparator<VarDesc> { public
 * int compare(VarDesc o1, VarDesc o2) { return o2.nbOccurences -
 * o1.nbOccurences; } }
 * 
 * 
 * public void setJavaType(String javaType){ this.javaType =
 * Hoclc2j.primitiveToObject(javaType); }
 * 
 * 
 * public String getJavaType(){ return javaType; }
 * 
 * 
 * public Set<String> getVars(){ Set<String> s = new HashSet<String>();
 * switch(type){ case EXTERNAL: case REACTION_RULE: s.add(ident); break; case
 * SOLUTION: case TUPLE: s = subPatternDesc.getVars(); break; case JOKER: //
 * nothing to do break; } return s; }
 * 
 * 
 * public String generatePatExternalClass(PatternDesc allDesc) throws
 * ParseException { String s,s2; s2 = allDesc.generateUntilDeclarations(ident,
 * allDesc); s = "class PatAtom_" + ident + " extends PatAtom {\n" +
 * "\t@Override\n" +
 * "\tpublic Condition conditionSatisfied(Reactives currentReactives) {\n" +
 * "\t\tAtom _HOCL_atom;\n" + "\t\tCondition _HOCL_cond;\n" + "\t\t" + javaType
 * + " " + ident + ";\n" + "\t\t_HOCL_atom = iterator.getElement();\n" +
 * "\t\t_HOCL_cond = Condition.NO;\n" +
 * "\t\tif (_HOCL_atom instanceof ExternalObject\n" +
 * "\t\t  && ((ExternalObject)_HOCL_atom).object instanceof " + javaType +
 * ") {\n" + "\t\t\t" + ident + " = (" + javaType +
 * ") ((ExternalObject)_HOCL_atom).object;\n" + (s2.length() > 0 ?
 * Hoclc2j.indentCode(3,s2) + "\n" : "") + "\t\t\t_HOCL_cond = " + condition +
 * ";\n" + "\t\t}\n" + // end of if "\t\treturn _HOCL_cond;\n" + "\t}\n\n" + //
 * end of conditionSatisfied "\tpublic void initSubIterator(){\n" +
 * "\t\t// nothing by default\n" + "\t}\n" + //end of initSubIterator
 * "\n} // class PatAtom_" + ident; return s; }
 * 
 * 
 * public String generatePatExternalClass(Set<String> vars, PatternDesc allDesc)
 * throws ParseException { String s,s2; s2 =
 * allDesc.generateOnlyDeclarations(allDesc, vars, 0); s = "class PatAtom_" +
 * ident + " extends PatAtom {\n" + "\t@Override\n" +
 * "\tpublic Condition conditionSatisfied(Reactives currentReactives) {\n" +
 * "\t\tAtom _HOCL_atom;\n" + "\t\tCondition _HOCL_cond;\n" + "\t\t" + javaType
 * + " " + ident + ";\n" + "\t\t_HOCL_atom = iterator.getElement();\n" +
 * "\t\t_HOCL_cond = Condition.NO;\n" +
 * "\t\tif (_HOCL_atom instanceof ExternalObject\n" +
 * "\t\t  && ((ExternalObject)_HOCL_atom).object instanceof " + javaType +
 * ") {\n" + "\t\t\t" + ident + " = (" + javaType +
 * ") ((ExternalObject)_HOCL_atom).object;\n" + (s2.length() > 0 ?
 * Hoclc2j.indentCode(3,s2) + "\n" : "") + "\t\t\t_HOCL_cond = " + condition +
 * ";\n" + "\t\t}\n" + // end of if "\t\treturn _HOCL_cond;\n" + "\t}\n\n" + //
 * end of conditionSatisfied "\tpublic void initSubIterator(){\n" +
 * "\t\t// nothing by default\n" + "\t}\n" + //end of initSubIterator
 * "\n} // class PatAtom_" + ident; return s; }
 * 
 * 
 * public String generatePatTupleClass(PatternDesc allDesc, int nestLevel)
 * throws ParseException { String s =
 * subPatternDesc.generatePatAtomArray(allDesc, nestLevel+1); return s; }
 * 
 * 
 * public String generatePatSolutionClass(PatternDesc allDesc, int nestLevel)
 * throws ParseException { String s; String className = "PatSol" + nestLevel;
 * 
 * s = "class " + className + " extends PatSolution {\n" +
 * "\tprivate ReactionRule reactionRule;\n\n" + "\t" + className +
 * "(ReactionRule reactionRule){\n" + "\t\t super();\n" +
 * "\t\t this.reactionRule = reactionRule;\n" + "\t}\n\n" + "\t@Override\n" +
 * "\tpublic void initSubReactives(Solution solution){\n" +
 * Hoclc2j.indentCode(2,subPatternDesc.generatePatternDeclaration("subPattern",
 * allDesc, nestLevel)) + "\n" +
 * "\t\tsubReactives = new Reactives(solution, subPattern, reactionRule, reactionRule.currentReactives);\n"
 * + "\t}\n\n" + "} // class " + className; return s; }
 * 
 * } // class VarDesc
 */
