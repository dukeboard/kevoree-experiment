package fr.inria.hocl.tests.vo;


import java.util.Scanner;

public class VO {

	static public boolean AgreeProduct( int p ) {
		String answer;
		Scanner in = new Scanner( System.in );
		System.out.println( "Agree Product ?" );
		answer = in.nextLine();
		if( answer.equals( "no" ) ) {
			System.out.println( "You did not agree !" );
			return false;
		} else {
			System.out.println( "You agree !" );
			return true;
		}
	}


	static public boolean FinishProduct( int p ) {
		String answer;
		Scanner in = new Scanner( System.in );
		System.out.println( "Finish Product ?" );
		answer = in.nextLine();
		if( answer.equals( "no" ) ) {
			System.out.println( "Not finished !" );
			return false;
		} else {
			System.out.println( "Finished !" );
			return true;
		}
	}
}
