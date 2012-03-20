package fr.inria.hocl.core.hoclc2j;


import java.util.LinkedList;

public interface Solution extends Atom {

	public String generateContentsDeclaration ();


	public String generateContentsReference ();


	public Molecule getContents ();


	public String generateAddElement ();


	public LinkedList<String> getElementTypes ();


	public String generateClassCode ();
}
