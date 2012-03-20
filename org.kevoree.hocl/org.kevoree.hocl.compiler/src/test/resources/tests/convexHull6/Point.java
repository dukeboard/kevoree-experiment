package fr.inria.hocl.tests.convexHull6;


class Point {

	int x; // abscissa

	int y; // ordinate

	int number;


	public Point( int abs, int ord, int n ) {
		x = abs;
		y = ord;
		number = n;
	}


	public Point( String dummyString ) {
		// a dummy constructor to satisfy molecule addition
		this( 0, 0, 0 );
	}


	public String toString() {
		return "P".concat( String.valueOf( number ) );
	}


	public boolean equals( Object object ) {
		return object instanceof Point && ( ( Point ) object ).x == x
				&& ( ( Point ) object ).y == y && ( ( Point ) object ).number == number;
	}


	/**
	 * @return true if the point is in the triangle p1p2p3
	 */
	boolean inTriangle( Point p1, Point p2, Point p3 ) {
		// compute the barycentric coordinates
		boolean isIn;

		float a = p1.x - p3.x;
		float b = p2.x - p3.x;
		float c = p3.x - x;
		float d = p1.y - p3.y;
		float e = p2.y - p3.y;
		float f = p3.y - y;

		if( a * e != b * d ) {
			float r1 = ( b * f - c * e ) / ( a * e - b * d );
			float r2 = ( a * f - c * d ) / ( b * d - a * e );
			float r3 = 1 - r1 - r2;
			isIn = r1 > 0 && r2 > 0 && r3 > 0;
		} else {
			// p1, p2 and p3 are aligned
			isIn = false;
		}
		return isIn;
	}

} // class Point
