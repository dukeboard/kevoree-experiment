package rbacTextualEditor.editor.graphicComponents;

import java.awt.*;
import java.util.HashMap;

public class DoubleBufferingSoftware extends Frame{
	   // boucle d'affichage
	   RenderingThread renderingThread = new RenderingThread();
	   // buffer mémoire (2eme buffer)
	   Graphics buffer;
	   // image mémoire correspondante au buffer
	   Image image; 
	   int x = 0; // coordonnée x de l'affichage du texte
	   int j =50;
	   private HashMap<Integer, Integer> points;

	   public DoubleBufferingSoftware(){
	      //affichage
	      setSize( 400, 400 );
	      setVisible( true );
	      points =new HashMap<Integer, Integer>();
	      // on démarre la bouche d'affichage
	      renderingThread.start();
	   
	   }
	   public void update(Graphics g){
	      paint(g);
	   }
	   public void paint( Graphics g ){
	     //création du buffer si il n'existe pas
	     if(buffer==null){
	        image = createImage(400,400);
	        buffer = image.getGraphics();
	      }
	     //on dessine sur le buffer mémoire
	      buffer.setColor( Color.white );
	      buffer.fillRect( 0, 0, 400, 400 );
	      buffer.setColor( Color.black );
	      buffer.drawString( "rules evolution", 200, 350 );
	      buffer.drawString( "rules", 10, 100 );
	      buffer.drawString( "time", 280, 320 );
	      buffer.drawLine(50, 100, 50, 300);
	      buffer.drawLine(50, 300, 300, 300);
	      if (j< 300){
	      points.put(j, j);
	      
	      j = j +2;
	      }
	      for(int i :points.keySet()){
	    	  buffer.drawLine(i, 150, i, 151);  
	      }
	      x++;
	      if(x>400) x = 0;
	      // finalement, le buffer mémoire est dessiné dans le buffer d'affichage
	      g.drawImage(image, 0, 0, this);
	   }
	   class RenderingThread extends Thread {
	     /**
	      *  Ce thread appelle le rafraichissement de notre fenêtre 
	      *  toutes les 10 milli-secondes
	      */
	      public void run(){
	         while(true){
	            try {
	               repaint(); 
	               sleep( 50 );
	            } catch ( Exception e ) {} 
	         }
	      }
	   }   
	   public static void main(String[] args){new DoubleBufferingSoftware();}
	}
