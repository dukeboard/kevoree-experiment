package fr.inria.hocl.api;


import java.io.Serializable;

/**
 * Represents an external Java object, i.e. not an object from the interpretor
 * 
 */
public class ExternalObject implements Atom, Serializable {

	private static final long serialVersionUID = -3044136841583809892L;

	private final Object object;


	public ExternalObject( Object object ) {
		this.object = object;
	}


	public boolean equals( Atom atom ) {
		boolean eq = false;
		if( atom instanceof ExternalObject) {
			eq = ( (ExternalObject) atom ).object.equals( object );
		}
		return eq;
	}


	/**
	 * 
	 * @return the object
	 */
	public Object getObject() {
		return object;
	}


	public String toString() {
		String s = "";

		if( object instanceof String )
			s = s + "\"" + object + "\"";
		else
			s = object.toString();
		return s;
	}

} // class ExternalObject
