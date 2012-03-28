package utils.infos;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;

public class InfoPort extends Thread{

	private HashMap<String , Integer> ports;	
	private ArrayList<Integer> freePorts;
	private ArrayList<Integer> busyPorts;	
	private int portMin = 0;
	private int portMax = 65535;
	
	
	public InfoPort(){
		ports = new HashMap<String, Integer>();
		freePorts = new ArrayList<Integer>();
		busyPorts = new ArrayList<Integer>();
	}
	
	public void run(){
		analyzePorts("localhost");
	}
	
	public void analyzePorts(String ip){
		for (int i =portMin; i<portMax; i++){
			Boolean bool = true;
			try {
				DatagramSocket x = new DatagramSocket(i);
				x.close();
			} catch (SocketException e) {
				bool = false;
			}
			if(bool){
				ports.put("free : "+i, i);
				freePorts.add(i);
			}
			else{
				ports.put("busy : "+i, i);
				busyPorts.add(i);
			}
		}
		System.out.println("number of free port(s) : "+freePorts.size());
		System.out.println("number of busy port(s) : "+busyPorts.size());
		
	}
	
	public ArrayList<Integer> getFreePorts() {
		return freePorts;
	}

	public ArrayList<Integer> getBusyPorts() {
		return busyPorts;
	}

	public HashMap<String, Integer> getPorts() {
		return ports;
	}

	public static void main(String[] args){
		InfoPort fp = new InfoPort();
		fp.start();	
	}
}
