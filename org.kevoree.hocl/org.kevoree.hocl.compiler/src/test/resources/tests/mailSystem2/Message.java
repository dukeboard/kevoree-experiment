package fr.inria.hocl.tests.mailSystem2;


public class Message {

	String message;

	String receiverDomain;

	String receiverId;


	public Message( String message, String receiverDomain, String receiverId ) {
		this.message = message;
		this.receiverDomain = receiverDomain;
		this.receiverId = receiverId;
	}


	public Message( String message ) {
		this( message, "A", "B" );
	}


	public String toString() {
		String s;
		s = "{\"" + message + "\" to " + receiverDomain + receiverId + "}";
		return s;
	}


	public boolean equals( Object obj ) {
		Message mess;
		boolean equ = false;

		if( obj instanceof Message ) {
			mess = ( Message ) obj;
			equ = message.equals( mess.message )
					&& receiverDomain.equals( mess.receiverDomain )
					&& receiverId.equals( mess.receiverId );
		}
		return equ;
	}

} // class Message
