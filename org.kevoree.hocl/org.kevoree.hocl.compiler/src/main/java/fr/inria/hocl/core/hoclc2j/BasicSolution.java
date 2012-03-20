package fr.inria.hocl.core.hoclc2j;


import java.util.LinkedList;
import java.util.Set;

public class BasicSolution implements Solution {

	final Molecule contents;

	private final int nestLevel;

	String solutionClassName;

	private final String varName;

	private static int solutionVarCounter = 0;

	private Access access;


	public BasicSolution( Molecule contents, int nestLevel ) {
		this.contents = contents;
		this.nestLevel = nestLevel;
		solutionClassName = "_HOCL_Solution_" + this.nestLevel;
		varName = "solution" + solutionVarCounter;
		solutionVarCounter++;
		access = Access.REWRITE;
	}


	public void setSolutionName( String newName ) {
		solutionClassName = CodeGeneratorHelper.upperFirstLetter( newName );
	}


	public String generateDeclarationInit( int position, SymbolsTable symbolsTable ) {
		String s;
		solutionVarCounter++;
		s = "Solution " + varName + " = new Solution();\n" + "{\n"
				+ CodeGeneratorHelper.indentCode( contents.generateDeclarationInit() ) + "\n"
				+ "\t" + varName + ".addMolecule(" + contents.generateReference()
				+ ");\n" + "}";
		return s;
	}


	public String generateReference() {
		return varName;
	}


	public Set<Symbols> getFreeVars() {
		return contents.getFreeVars();
	}


	public String generateContentsReference() {
		return contents.generateReference();
	}


	public String generateContentsDeclaration() {
		return contents.generateDeclarationInit();
	}


	public String toString() {
		return "<" + contents.toString() + ">";
	}


	public void findReadOnlyReactives() {
		contents.findReadOnlyReactives();
	}


	public Molecule getContents() {
		return contents;
	}


	public void setReadOnly() {
		access = Access.READ_ONLY;
	}


	public boolean isReadOnly() {
		return access == Access.READ_ONLY;
	}


	public String getType() {
		return "basicSolution";
	}


	public String generateAddElement() {
		return contents.generateAddElementCode();
	}


	public LinkedList<String> getElementTypes() {
		return contents.getElementTypes();
	}


	public String generateClassCode() {

		return "BASICSOLUTION";
	}

	/*
	 * public String generateDeclarationInit() { return "Solution " + varName +
	 * " = new Solution();\n" + "{\n" +
	 * Hoclc2j.indentCode(contents.generateDeclarationInit()) + "\n" + "\t" +
	 * varName + ".add(" + contents.generateReference() + ");\n" + "}"; }
	 */

	/*
	 * public String generatePatClass(PatternDesc allDesc, Set<String> vars,
	 * String permutation) { return "\n\nclass " + iteratorClassName +
	 * " extends IteratorForSolution {\n" +
	 * "\tprotected Permutation makeSubPermutation(){\n" +
	 * "\t\tPermutation perm;\n" + Hoclc2j.indentCode(2,
	 * contents.generatePatternDeclaration("perm", allDesc,nestLevel+1)) + "\n" +
	 * "\t\treturn perm;" + "\n" + "\t}\n\n" + "} // class " + iteratorClassName;
	 * }
	 * 
	 * public String generateNewPat(){ String reactionRule = "this"; for(int i =
	 * 0; i < nestLevel; i++ ){ reactionRule += ".reactionRule"; } return "new" +
	 * iteratorClassName + "()"; }
	 * 
	 * public String generateVarDecl(){ return new String(); }
	 * 
	 * public String generateIdent(){ return ""; }
	 * 
	 * public String generateDeclarations(String ident, PatternDesc allDesc,
	 * String atomIterator) { String decl; String iteratorSolution =
	 * "_HOCL_iteratorSolution" + nestLevel; decl =
	 * contents.generateDeclaration(ident, allDesc, iteratorSolution +
	 * ".getSubPermutation()"); if(decl.length() != 0){ decl = "{\n" +
	 * "\tIteratorForSolution " + iteratorSolution + " = (IteratorForSolution)" +
	 * atomIterator + ";\n" + ( decl.length() > 0 ? Hoclc2j.indentCode(decl) +
	 * "\n" : "") + "}"; } return decl; }
	 * 
	 * public void applyCondOfBool(){ contents.applyCondOfBool(); }
	 * 
	 * public void pushCond(DecompCond decompCond){ contents.pushCond(decompCond);
	 * }
	 * 
	 * public Set<String> getVars(){ return contents.getVars(); }
	 * 
	 * public Set<String> getJokers(){ return contents.getJokers(); }
	 * 
	 * public void incrNbOcc(){ nbOccurrences++; }
	 * 
	 * public int getNbOcc(){ return nbOccurrences; }
	 * 
	 * public void applyCond(Set<String> assignedVars, DecompCond decompCond){
	 * contents.applyCond(assignedVars, decompCond); }
	 */
}
