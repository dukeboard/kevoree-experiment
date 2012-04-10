//package org.kevoree.tools.kevoreegeneticpoc;
//
//import junit.framework.Test;
//import junit.framework.TestCase;
//import junit.framework.TestSuite;
//import org.kevoree.experiment.smartForest.SmartForestExperiment;
//import org.kevoree.experiment.smartForest.experiment.SmartForestFitnessEvaluatorO$;
//import org.kevoree.experiment.smartForest.results.StatHandler$;
//
//import java.lang.management.ManagementFactory;
//import java.lang.management.MemoryMXBean;
//import java.lang.management.MemoryPoolMXBean;
//import java.lang.management.RuntimeMXBean;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
///**
// * Unit test for simple App.
// */
//public class AppTest
//        extends TestCase {
//    /**
//     * Create the test case
//     *
//     * @param testName name of the test case
//     */
//    public AppTest(String testName) {
//        super(testName);
//    }
//
//    /**
//     * @return the suite of tests being tested
//     */
//    public static Test suite() {
//        return new TestSuite(AppTest.class);
//    }
//
//
//    /**
//     * Rigourous Test :-)
//     */
//    public void testApp() {
//
//        MemoryMXBean beanMemory = ManagementFactory.getMemoryMXBean();
//
//        List<Double> heapSize = new ArrayList<Double>();
//        List<Float> bestValue = new ArrayList<Float>();
//
//        //WARMUP
//        SmartForestExperiment.forestWidth = 5;
//        SmartForestExperiment.main(new String[0]);
//        HashMap<Integer, Long> times = new HashMap<Integer, Long>();
//        for (int i = 3; i < 13; i = i + 1) {
//            StatHandler$.MODULE$.putValue(0f);
//            SmartForestExperiment.initTimeStat();
//            SmartForestExperiment.forestWidth = i;
//            SmartForestExperiment.main(new String[0]);
//            long mesuredTime = SmartForestExperiment.collectStatistics();
//            times.put(i, mesuredTime);
//
//            bestValue.add(StatHandler$.MODULE$.bestValue());
//            heapSize.add(beanMemory.getHeapMemoryUsage().getUsed() / Math.pow(10,6)  );
//
//            System.out.println("Res = "+mesuredTime+"-"+StatHandler$.MODULE$.bestValue()+"-"+beanMemory.getHeapMemoryUsage().getUsed() / Math.pow(10,6) );
//
//        }
//        System.out.println("=====================R Script =========================");
//        System.out.println("library(ggplot2)");
//        System.out.print("nbSensors <- c(");
//        boolean firstValue = true;
//        for (Integer i : times.keySet()) {
//            if (!firstValue) {
//                System.out.print(",");
//            }
//            System.out.print((i*i));
//            firstValue = false;
//        }
//        System.out.println(")");
//        System.out.print("searchTime <- c(");
//        firstValue = true;
//        for (Long i : times.values()) {
//            if (!firstValue) {
//                System.out.print(",");
//            }
//            System.out.print((i/1000));
//            firstValue = false;
//        }
//        System.out.println(")");
//        System.out.print("heapSize <- c(");
//        firstValue = true;
//        for (Double i : heapSize) {
//            if (!firstValue) {
//                System.out.print(",");
//            }
//            System.out.print(i);
//            firstValue = false;
//        }
//        System.out.println(")");
//        System.out.print("foundValues <- c(");
//        firstValue = true;
//        for (Float i : bestValue) {
//            if (!firstValue) {
//                System.out.print(",");
//            }
//            System.out.print(i);
//            firstValue = false;
//        }
//        System.out.println(")");
//        System.out.println("ggplot(expData, aes(x, y)) + geom_point(legend = FALSE) + geom_line() + scale_y_continuous(\"Computation time in millisecondes\") + scale_x_continuous(\"Number of sensor simulated in forest\")");
//        System.out.println("=====================R Script =========================");
//        assertTrue(true);
//    }
//}
