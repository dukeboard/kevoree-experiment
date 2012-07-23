package fr.inria.hocl.core.hoclc2j;


public class Debug {

	public enum Level {
		NONE, ERROR, TRACE;
	}

	private Level verboseLevel = Level.ERROR;


	public void setVerboseLevel( Level newLevel ) {
		verboseLevel = newLevel;
	}


	public void addLog( String message, Level level ) {
		if( verboseLevel.compareTo( level ) >= 0 ) {
			System.out.println( message );
		}
	}

}
