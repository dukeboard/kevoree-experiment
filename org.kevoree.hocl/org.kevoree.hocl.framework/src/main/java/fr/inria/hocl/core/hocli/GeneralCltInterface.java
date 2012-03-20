package fr.inria.hocl.core.hocli;


import java.rmi.Remote;
import java.rmi.RemoteException;

public interface GeneralCltInterface extends Remote {

	/**
	 * Remotely invocable method.
	 * 
	 * @exception java.rmi.RemoteException
	 *              if the remote invocation fails.
	 */
	public void printMultiset (String s) throws RemoteException;

}
