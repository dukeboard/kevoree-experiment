package fr.inria.hocl.tests.quadrature;


public class Trapezoid {

	static double area( double height, double shorterSide, double longerSide ) {
		return ( shorterSide + longerSide ) / 2 * height;
	}
}
