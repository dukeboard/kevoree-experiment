package org.kevoree.experiment.smartForest;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.kevoree.ContainerRoot;
import org.kevoree.experiment.smartForest.model.Generator;
import org.kevoree.experiment.smartForest.results.StatisticsParser;
import org.kevoree.library.reasoner.ecj.KevoreeMultipleGeneticAlgorithm;
import org.kevoree.tools.marShell.parser.ParserUtil;
import org.omg.PortableServer.POAManager;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

public class ParseResults {

    private static final ClassLoader classLoader = ParseResults.class.getClassLoader();


    public static void main(String[] args){
        String path = SmartForestExperiment.getComputerFullName() + "-" + SmartForestExperiment.folderToStoreTempFile + File.separator + "completeStat.stat";

        System.out.println("PATH = " + path);
        try {
            InputStream stream = new FileInputStream(new File(path));
            if (stream == null)
                System.out.println("Perdu");
            StatisticsParser.parseCompleteStatistics(stream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }


}
