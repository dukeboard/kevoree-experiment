package org.kevoree.experiment.smartForest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.kevoree.ContainerRoot;
import org.kevoree.experiment.smartForest.model.Generator;
import org.kevoree.library.reasoner.ecj.KevoreeMultipleGeneticAlgorithm;
import org.kevoree.tools.marShell.parser.ParserUtil;

import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.InetAddress;
import java.util.*;

public class SmartForestExperiment {
    public final static int forestWidth = 10;
    public final static int generations = 100;
    public final static int populations = 200;
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
        myProperties.put("stat.file = $out.stat", "stat.file = " + "classicStat.stat");
        myProperties.put("stat.front = $front.stat", "stat.front = " + "front.stat");
        myProperties.put("stat.child.0.file = $out2.stat", "stat.child.0.file = " + "completeStat.stat");
        myProperties.put("pop.subpop.0.species.ind.models-folder = models", "pop.subpop.0.species.ind.models-folder = " + folderToStoreTempFile + "/models");
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
        folderToStoreTempFile = getComputerFullName() + "-" + folderToStoreTempFile;
        // clean and create the temp folder
        File generatedDirectory = new File(folderToStoreTempFile);
        if (generatedDirectory.exists() && generatedDirectory.isDirectory()){
            for (File myFile : generatedDirectory.listFiles()){
                myFile.delete();
            }
        }
        generatedDirectory.mkdirs();
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
            e.printStackTrace();
        }
    }

    public static String getComputerFullName() {
    String hostName = null;
    try {
      final InetAddress addr = InetAddress.getLocalHost();
      hostName = new String(addr.getHostName());
    } catch(final Exception e) {
    }//end try
    return hostName;
  }
}
