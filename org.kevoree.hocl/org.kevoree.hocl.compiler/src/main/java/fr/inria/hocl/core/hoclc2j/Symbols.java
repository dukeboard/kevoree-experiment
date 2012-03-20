package fr.inria.hocl.core.hoclc2j;


public interface Symbols {

	public Symbols lookUp (String ident);


	public String generateDeclaration(Symbols variable, String iterator);


	public String generateVariableDecl ();


	public String generateIdent ();
}
