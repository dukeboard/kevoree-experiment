package utils.infos;

import java.util.Vector;

public class InfoThread {

	public class ThreadTest extends Thread{
		public void run(){
			while(true){
				System.out.println(getName());
				try {
					sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	
	private int maxThread;
	
	public InfoThread(){
		maxThread = 0;
	}
	
	public void searchMax(){
		Vector<Thread> vect = new Vector<Thread>();
		for (int i = 0;i<5600; i++){
			vect.add(new ThreadTest());
		}
		for (Thread t : vect){
			t.start();
		}
		
	}
	

	public int getMaxThread() {
		return maxThread;
	}

	public static void main(String[] args){
		InfoThread it = new InfoThread();
		it.searchMax();
	}


	
}
