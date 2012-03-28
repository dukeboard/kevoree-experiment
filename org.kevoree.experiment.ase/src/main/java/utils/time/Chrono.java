/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package utils.time;

/**
 *
 * @author obendavi
 */



import java.util.Date;

public class Chrono 
{

        private static Date dStartDate;
        private static Date dEndDate;

        public Chrono(){}
        
        public void start(){
        	
        		dStartDate = new Date();
    		    		
        }
        
        public void stop(){        	
        		dEndDate = new Date();    		    		
        }
        
        public void afficheMillisecondes()
        {        	
        	long lExecTime = dEndDate.getTime() - dStartDate.getTime();
        	System.out.println("Temps d'exécution : "+lExecTime+" millisecondes");
        }

        
        public java.lang.String displayTime()
        {
            long lExecTime = dEndDate.getTime() - dStartDate.getTime();
            return "Execution time : "+lExecTime+" milliseconds";
        }

        
        
        public void main(String[] args){      
        	Chrono c = new Chrono();
        	c.start();
        	System.out.println(Chrono.dStartDate.getTime());
        	for (int i = 0;i< 1000;i++)
        		System.out.println("bibi");
        	c.stop();
        	System.out.println(c.dEndDate.getTime());
        	System.out.println(c.displayTime());
        }
}


