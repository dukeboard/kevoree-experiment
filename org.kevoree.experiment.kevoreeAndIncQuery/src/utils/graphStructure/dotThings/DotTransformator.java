package utils.graphStructure.dotThings;

import java.io.File;
import java.io.IOException;

import utils.time.Chrono;

public class DotTransformator {

    static String path;

    public DotTransformator(String s) {
        path = s;
    }
    
    public DotTransformator() {
    }

    public static void displayPdfFile(String s) {
        //File f = new File (s);
        String nomFichier = s.replace(".dot", "");
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println("debut transformation dot to pdf");
            Chrono c = new Chrono();
            c.start();
            runtime.exec(
                    new String[]{
                        "dot",
                        "-Tpdf",
                        "-Kdot",
                        s,
                        "-o",
                        nomFichier + ".pdf"}).waitFor();
            c.stop();
            System.out.print("fin transformation dot to pdf  ");
            c.afficheMillisecondes();
        } catch (InterruptedException e) {
            System.out.println("ca marche pas dottopdf");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ca marche pas dottopdf");
        }
        displayPDF(nomFichier + ".pdf");
    }
    
    
    public static void displayPdfFile2() {
        //File f = new File (s);
        String nomFichier = "/home/obendavi/Bureau/coding/workspaceIncQuery/rbac/models/Policy.dot";
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println("debut transformation dot to pdf");
            Chrono c = new Chrono();
            c.start();
            runtime.exec(
                    new String[]{
                        "dot",
                        "-Tpdf",
                        "-Kdot",
                        "/home/obendavi/Bureau/coding/workspaceIncQuery/rbac/models/Policy.dot",
                        "-o",
                        nomFichier + ".pdf"}).waitFor();
            c.stop();
            System.out.print("fin transformation dot to pdf  ");
            c.afficheMillisecondes();


        } catch (InterruptedException e) {
            System.out.println("ca marche pas dottopdf");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ca marche pas dottopdf");
        }


        displayPDF(nomFichier + ".pdf");

    }
    
    public static void createPngFile2() {
        //File f = null;
    	String nomFichier = "/local/NESSOS/spatioTemporal/workspace/org.starbac.model/GRRR";
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println("debut transformation dot to png");
            Chrono c = new Chrono();
            c.start();
            runtime.exec(
                    new String[]{
                        "dot",
                        "-Tpng",
                        "-Kdot",
                        "/local/NESSOS/spatioTemporal/workspace/org.starbac.model/GRRR.dot",
                        "-o",
                        nomFichier + ".png"}).waitFor();        
            c.stop();
            System.out.print("fin transformation dot to png  ");
            c.afficheMillisecondes();
          
            

        } catch (InterruptedException e) {
            System.out.println("ca marche pas dottopng");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ca marche pas dottopng");
        }      
        
    }
    

    public File createPdfFile(String s) {

        String nomFichier = s.replace(".dot", "");
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println("debut transformation dot to pdf");
            Chrono c = new Chrono();
            c.start();
            runtime.exec(
                    new String[]{
                        "dot",
                        "-Tpdf",
                        "-Kdot",
                        s,
                        "-o",
                        nomFichier + ".pdf"}).waitFor();
            c.stop();
            System.out.print("fin transformation dot to pdf  ");
            c.afficheMillisecondes();


        } catch (InterruptedException e) {
            System.out.println("ca marche pas dottopdf");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ca marche pas dottopdf");
        }
        File f = new File(nomFichier + ".pdf");
        return f;
    }

    public static void displayPngFile(String s) {
        //File f = new File (s);
        String nomFichier = s.replace(".dot", "");
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println("debut transformation dot to png");
            Chrono c = new Chrono();
            c.start();
            runtime.exec(
                    new String[]{
                        "dot",
                        "-Tpng",
                        "-Kdot",
                        s,
                        "-o",
                        nomFichier + ".png"}).waitFor();
            c.stop();
            System.out.print("fin transformation dot to png  ");
            c.afficheMillisecondes();


        } catch (InterruptedException e) {
            System.out.println("ca marche pas dottopng");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ca marche pas dottopng");
        }

    }

    public File createPngFile(String s) {
        File f = null;
        String nomFichier = s.replace(".dot", "");
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println("debut transformation dot to png");
            Chrono c = new Chrono();
            c.start();
            runtime.exec(
                    new String[]{
                        "dot",
                        "-Tpng",
                        "-Kdot",
                        s,
                        "-o",
                        nomFichier + ".png"}).waitFor();        
            c.stop();
            System.out.print("fin transformation dot to png  ");
            c.afficheMillisecondes();
            f = new File(nomFichier + ".png");
            

        } catch (InterruptedException e) {
            System.out.println("ca marche pas dottopng");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ca marche pas dottopng");
        }        
        return f;
    }
    
    public String createPngFilePath(String s) {
        File f = null;
        String nomFichier = s.replace(".dot", "");
        Runtime runtime = Runtime.getRuntime();
        try {
            System.out.println("debut transformation dot to png");
            Chrono c = new Chrono();
            c.start();
            runtime.exec(
                    new String[]{
                        "dot",
                        "-Tpng",
                        "-Kdot",
                        s,
                        "-o",
                        nomFichier + ".png"}).waitFor();        
            c.stop();
            System.out.print("fin transformation dot to png  ");
            c.afficheMillisecondes();
            f = new File(nomFichier + ".png");
            

        } catch (InterruptedException e) {
            System.out.println("ca marche pas dottopng");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ca marche pas dottopng");
        }        
        
        String result = nomFichier + ".png";
        return result;
    }


    public static void displayPDF(String s) {
        Runtime runtime = Runtime.getRuntime();
        try {
            runtime.exec(new String[]{"evince", s}).waitFor();
        } catch (InterruptedException e) {
            System.out.println("ca marche pas l'affichage pdf");
        } catch (IOException e) {
            System.out.println("ca marche pas l'affichage pdf");
        }
    }

    public static String getPath() {
        return path;
    }

    public static void setPath(String p) {
        path = p;
    }
    
    
    
    public static void main(String[] args)
    {
    	DotTransformator.displayPdfFile2();
    }
    
    
    
}
