package fr.inria.hocl.tests.graphConnectivity;


import java.util.Collection;
import java.util.HashSet;

public class MySet extends HashSet<String> {

	private static final long serialVersionUID = -3751280206054125442L;


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
		ms.add( "E" );
		ms.add( "F" );
		ms.add( "G" );
		ms.add( "H" );
		return ms;
	}

} // class MySet
