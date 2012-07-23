package fr.inria.hocl.tests.queensEight;


public class Queens {

	static public boolean noConflict( int l1, int c1, int l2, int c2 ) {
		return c1 != c2 // different column
				&& c2 - c1 != l2 - l1 // different diagonal
				&& c2 - c1 != l1 - l2; // different diagonal
	}
}
