/*
package fr.inria.hocl.core.hocli;

import fr.inria.hocl.api.ExternalObject;
import fr.inria.hocl.api.Molecule;
import fr.inria.hocl.api.Tuple;

import java.io.Serializable;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class IOSenderRe implements Serializable {
	
	public boolean send (Tuple t,String dest){
		try{
			//System.out.println("*******************\n");
			
			String name = "";
			Molecule m = new Molecule();
			
			name = dest+"Srv";
				
			Registry registry = LocateRegistry.getRegistry();
			GeneralSrvInterface remoteiosrv = (GeneralSrvInterface) registry.lookup(name);
			
			System.out.println("NAME: "+name +"remoteiosrv" + remoteiosrv +"\n");

			m.add(t);
			System.out.println("*******************"+name+"\n"+m+"\n*******************\n");	
			
			remoteiosrv.remoteOperations(m);
				
			//System.out.println("HAHAHA");	
			
			return true;
			
		}catch (Exception e ) {
			System.out.println ("RemoteIOclient exception: " + e.getMessage()+"\n**************\n"+e.getLocalizedMessage()+"\n");
			e.printStackTrace();
			
			System.out.println("+++++++++++++++++++\n");
			return false;
		}
		
	}
	
	public boolean send_with_delay (Tuple t,String dest, int time){
		try{
			//System.out.println("*******************\n");
			Thread.sleep(time);
			
			String name = "";
			Molecule m = new Molecule();
			
			name = dest+"Srv";
				
			Registry registry = LocateRegistry.getRegistry();
			GeneralSrvInterface remoteiosrv = (GeneralSrvInterface) registry.lookup(name);

			m.add(t);
			System.out.println("*******************"+name+"\n");	
			remoteiosrv.remoteOperations(m);
			return true;
			
		}catch (Exception e ) {
			System.out.println ("RemoteIOclient exception: " + e);
			System.out.println("+++++++++++++++++++\n");
			return false;
		}
		
	}
	
	public boolean send_with_delay_String (String t,String dest, int time){
		try{
			//System.out.println("*******************\n");
			Thread.sleep(time);
			
			String name = "";
			Molecule m = new Molecule();
			
			name = dest+"Srv";
				
			Registry registry = LocateRegistry.getRegistry();
			GeneralSrvInterface remoteiosrv = (GeneralSrvInterface) registry.lookup(name);
			ExternalObject ext = new ExternalObject(t);
			m.add(ext);
			System.out.println("*******************"+name+"\n");	
			remoteiosrv.remoteOperations(m);
			return true;
			
		}catch (Exception e ) {
			System.out.println ("RemoteIOclient exception: " + e);
			System.out.println("+++++++++++++++++++\n");
			return false;
		}
		
	}
	
	public String toString(){
		return "@THis is an IOSender@, ";
	} 

}
*/
