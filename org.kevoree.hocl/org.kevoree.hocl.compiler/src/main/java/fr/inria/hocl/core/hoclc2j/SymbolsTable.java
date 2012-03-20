package fr.inria.hocl.core.hoclc2j;


import java.util.ListIterator;
import java.util.Stack;

public class SymbolsTable extends Stack<Symbols> {

	private static final long serialVersionUID = 439597201245012419L;

	private int nestLevel = 0;


	public Symbols lookUp( String ident ) {
		ListIterator<Symbols> it;
		Symbols symb = null;

		it = listIterator();
		while( it.hasNext() ) {
			it.next();
		}

		while( it.hasPrevious() && symb == null ) {
			symb = it.previous();
			symb = symb.lookUp( ident );
		}
		return symb;
	}


	public String generateDeclaration( Symbols variable, String permutation ) {
		String s = "";
		ListIterator<Symbols> it = this.listIterator();
		// place the iterator at the top of the stack (i.e. end of list)
		while( it.hasNext() ) {
			it.next();
		}
		Symbols symb;
		while( it.hasPrevious() && s.length() == 0 ) {
			symb = it.previous();
			s = symb.generateDeclaration( variable, permutation );
		}
		return s;
	}


	public void incrNestLevel() {
		nestLevel++;
	}


	public int getNestLevel() {
		return nestLevel;
	}


	public void decrNestLevel() {
		nestLevel--;
	}
}
