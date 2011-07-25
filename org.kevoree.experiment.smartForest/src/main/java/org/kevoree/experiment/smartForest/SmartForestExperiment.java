package org.kevoree.experiment.smartForest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.kevoree.ContainerNode;
import org.kevoree.ContainerRoot;
import org.kevoree.KevoreePackage;
import org.kevoree.NamedElement;
import org.kevoree.experiment.smartForest.model.Generator;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.library.reasoner.ecj.App;
import org.kevoree.library.reasoner.ecj.KevoreeMultipleGeneticAlgorithm;
import org.kevoree.library.reasoner.ecj.KevoreeMultipleRepresentation;
import org.kevoree.library.reasoner.ecj.MultiObjectiveKevoreeProblem;
import org.kevoree.tools.marShell.ast.Script;
import org.kevoree.tools.marShell.interpreter.KevsInterpreterAspects;
import org.kevoree.tools.marShell.interpreter.KevsInterpreterContext;
import org.kevoree.tools.marShell.parser.KevsParser;
import org.kevoree.tools.marShell.parser.ParserUtil;
import org.omg.CORBA.INITIALIZE;
import org.slf4j.LoggerFactory;
import scala.Option;

import java.io.*;
import java.net.InetAddress;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

public class SmartForestExperiment {
    public final static int forestWidth = 10;
    public final static int generations = 500;
    public final static int populations = 500;
    public final static int elite = 0;

    public final static String paramsSourceFile = "kevoreeMultiCrossOverTest.params";
    public final static String paramsTargetFile = "kevoreeMultiTestGenerated.params";
    public final static String individualBaseModel = "kevoreeIndividualModel.kev";

    public static String folderToStoreTempFile = "generated";

    private static final ClassLoader classLoader = SmartForestExperiment.class.getClassLoader();


    public static void main(String[] args){

        initExperiment();

        // initialization of the architecture : We are starting with an architecture with all components on all nodes
        ContainerRoot myModel = Generator.generateForest(forestWidth);
        // Pass it to the SmartForestIndividual
        ParserUtil.save(folderToStoreTempFile + File.separator + individualBaseModel, myModel);

        // Initialize parameters to match with the experiment
        Map<String,String> myProperties = new HashMap<String,String>();
        myProperties.put("pop.subpop.0.size = 100", "pop.subpop.0.size = " + populations);
        myProperties.put("generations = 100", "generations = " + generations);
        myProperties.put("breed.elite.0 = 100", "breed.elite.0 = " + elite);
        initializeParams(paramsSourceFile, paramsTargetFile, myProperties);

        //Start the experiment
        KevoreeMultipleGeneticAlgorithm kmga = new KevoreeMultipleGeneticAlgorithm ();

        collectStatistics();
    }

    private static void collectStatistics() {
        // TODO
    }

    private static void initializeParams(String sourceFile, String targetFile, Map<String,String> myProperties) {
        try {
            InputStream ips=classLoader.getResourceAsStream(sourceFile);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String line;
            File generatedDirectory = new File(folderToStoreTempFile);
            if (generatedDirectory.exists() && generatedDirectory.isDirectory()){
                for (File myFile : generatedDirectory.listFiles()){
                    myFile.delete();
                }
            }
            generatedDirectory.mkdirs();
            FileWriter fw = new FileWriter (folderToStoreTempFile + File.separator + targetFile);
            BufferedWriter bw = new BufferedWriter (fw);
            PrintWriter outputFile = new PrintWriter (bw);

            while ((line=br.readLine())!=null){
                for (String myProperty : myProperties.keySet()){
                    line = line.replace(myProperty, myProperties.get(myProperty));
                }
                outputFile.println (line);
            }
            br.close();
            outputFile.flush();
            outputFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        copyFileToGeneratedDirectory("ec.params");
        copyFileToGeneratedDirectory("multiobjective.params");
        copyFileToGeneratedDirectory("nsga2.params");
        copyFileToGeneratedDirectory("spea2.params");
        copyFileToGeneratedDirectory("simple.params");

    }

    private static void initExperiment() {
        // initialize : logger are quiet, old statistic files are deleted and the folder to store statistic file is prefixed byt the computer name
        Logger root = (Logger)LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.OFF); //change to off
        deleteStatistiqueFile();
        folderToStoreTempFile = getComputerFullName() + "-" + folderToStoreTempFile;
    }

    private static void deleteStatistiqueFile() {
        File myFile = new File("run.statistique");
        if (myFile.exists() && myFile.isFile()){
            myFile.delete();
        }
    }


    private static void copyFileToGeneratedDirectory(String myFileName){
        try {
            InputStream ips = classLoader.getResourceAsStream(myFileName);
            InputStreamReader ipsr = new InputStreamReader(ips);
            BufferedReader br = new BufferedReader(ipsr);
            String line;
            FileWriter fw = new FileWriter(folderToStoreTempFile + File.separator + myFileName);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter outputFile = new PrintWriter(bw);

            while ((line = br.readLine()) != null) {
                outputFile.println(line);
            }
            br.close();
            outputFile.flush();
            outputFile.close();

        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private static String getComputerFullName() {
    String hostName = null;
    try {
      final InetAddress addr = InetAddress.getLocalHost();
      hostName = new String(addr.getHostName());
    } catch(final Exception e) {
    }//end try
    return hostName;
  }
}
