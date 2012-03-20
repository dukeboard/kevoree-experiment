package fr.inria.hocl.tests.graphMinSpanTree;


import java.util.Collection;
import java.util.HashSet;

public class MySet extends HashSet<String> {

	private static final long serialVersionUID = 7155096591087012499L;


	public MySet() {
		super();
	}


	public MySet( String s ) {
		super();
		super.add( s );
	}


	public MySet addAll( Collection<String> c ) {
		super.addAll( c );
		return this;
	}


	static public MySet expectedResult() {
		MySet ms = new MySet();
		ms.add( "A" );
		ms.add( "B" );
		ms.add( "C" );
		ms.add( "D" );
		return ms;
	}

} // class MySet
