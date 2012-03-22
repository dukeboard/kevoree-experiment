package fr.inria.hocl.core.hoclc2j;


import java.util.*;

public class Tuple implements Atom {

	final List<Atom> contents;

	static private int tupleCounter;

	private String varName;

	private Access access;


	public Tuple( int nestLevel ) {
		contents = new LinkedList<Atom>();
		tupleCounter = 0;
		access = Access.REWRITE;
	}


	public void add( Atom atom ) {
		contents.add( atom );
	}


	public int size() {
		return contents.size();
	}


	public Iterator<Atom> iterator() {
		return contents.iterator();
	}


	public String generateDeclarationInit( int position, SymbolsTable symbolsTable ) {
		String s, s2;
		varName = "tuple" + tupleCounter;
		tupleCounter++;
		s = "Tuple " + varName + " = new Tuple(" + contents.size() + ");";
		Iterator<Atom> it = contents.iterator();
		Atom atom;
		int positionTuple = 0;
		while( it.hasNext() ) {
			atom = it.next();
			s2 = atom.generateDeclarationInit( position, symbolsTable );
			s = s + "\n" + ( s2.length() > 0 ? s2 + "\n" : "" ) + varName + ".set("
					+ positionTuple + ", " + atom.generateReference() + ");";
			positionTuple++;

		}
		return s;
	}


	public String generateReference() {
		return varName;
	}


	public Set<Symbols> getFreeVars() {
		Set<Symbols> freeVars = new HashSet<Symbols>();
		Iterator<Atom> it = contents.iterator();
		Atom atom;
		while( it.hasNext() ) {
			atom = it.next();
			freeVars.addAll( atom.getFreeVars() );
		}
		return freeVars;
	}


	public void findReadOnlyReactives() {
		for (Atom content : contents) {
			content.findReadOnlyReactives(); // a tuple may contain a reaction rule
		}
	}


	public void setReadOnly() {
		access = Access.READ_ONLY;
	}


	public boolean isReadOnly() {
		return access == Access.READ_ONLY;
	}


	public String getType() {
		return "tuple";
	}


	public LinkedList<String> getElementTypes() {
		// TODO Auto-generated method stub
		return null;
	}

}
