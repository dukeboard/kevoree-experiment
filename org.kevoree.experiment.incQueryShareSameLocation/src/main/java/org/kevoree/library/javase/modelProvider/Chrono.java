package org.kevoree.library.javase.modelProvider;


import java.util.Date;


public class Chrono 
{

        private Date dStartDate;
        private Date dEndDate;

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
}