package org.kevoree.library.javase.modelProvider;


import java.util.Date;


public class Chrono 
{

        private static Date dStartDate;
        private static Date dEndDate;

        public Chrono(){}
        
        public static void start(){
        	
        		dStartDate = new Date();
    		    		
        }
        
        public static void stop(){        	
        		dEndDate = new Date();    		    		
        }
        
        public static void afficheMillisecondes()
        {        	
        	long lExecTime = dEndDate.getTime() - dStartDate.getTime();
        	System.out.println("Temps d'exécution : "+lExecTime+" millisecondes");
        }

        
        public static java.lang.String displayTime()
        {
            long lExecTime = dEndDate.getTime() - dStartDate.getTime();
            return "Execution time : "+lExecTime+" milliseconds";
        }

        
        
        public static void main(String[] args){        	
        	Chrono.start();
        	System.out.println(Chrono.dStartDate.getTime());
        	for (int i = 0;i< 1000;i++)
        		System.out.println("bibi");
        	Chrono.stop();
        	System.out.println(Chrono.dEndDate.getTime());
        	System.out.println(Chrono.displayTime());
        }
}