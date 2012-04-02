/*
package fr.inria.hocl.core.hocli;


import fr.inria.hocl.api.ExternalObject;
import fr.inria.hocl.api.Molecule;
import fr.inria.hocl.api.Tuple;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IOSender {

	public boolean send( double num, String from, String path ) {
		try {

			String name;
			String nextSteps = "";
			Molecule m = new Molecule();

			if( path.contains( "-" ) ) {
				String[] s = path.split( "-" );
				name = s[0] + "Srv";
				nextSteps = s[1];
				for( int i = 2; i < s.length; i++ ) {
					nextSteps += "-" + s[i];
				}

			} else {
				name = path + "Srv";
			}

			Registry registry = LocateRegistry.getRegistry();
			GeneralSrvInterface remoteiosrv = (GeneralSrvInterface) registry.lookup( name );

			Tuple t1 = new Tuple( 3 );
			t1.set( 0, new ExternalObject( "INPUT" ) );
			t1.set( 1, new ExternalObject( from ) );
			t1.set( 2, new ExternalObject(num) );

			if( nextSteps.equals( "" ) ) {
				m.add( t1 );
			}

			else {
				Tuple t2 = new Tuple( 2 );
				t2.set( 0, new ExternalObject( "NEXTSTEPS" ) );
				t2.set( 1, new ExternalObject( nextSteps ) );
				m.add( t1 );
				m.add( t2 );
			}

			remoteiosrv.remoteOperations( m );

			return true;

		} catch( Exception e ) {
			System.out.println( "RemoteIOclient exception: " + e );
			return false;
		}

	}


	public boolean sendback( double num, String to ) {
		try {

			String name = to + "Srv";
			Registry registry = LocateRegistry.getRegistry();
			GeneralSrvInterface remoteiosrv = (GeneralSrvInterface) registry
					.lookup( name );

			Tuple t1 = new Tuple( 2 );

			t1.set( 0, new ExternalObject( "RESULT" ) );
			t1.set( 1, new ExternalObject( num ) );

			Molecule m = new Molecule();
			m.add( t1 );

			remoteiosrv.remoteOperations( m );

			return true;

		} catch( Exception e ) {
			System.out.println( "RemoteIOclient exception: " + e );
			return false;
		}

	}


	public String toString() {
		return "@";
	}

}
*/
