package org.kevoree.tools.kevoreegeneticpoc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.kevoree.experiment.smartForest.SmartForestExperiment;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }


    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        //WARMUP
        SmartForestExperiment.forestWidth = 3;
        SmartForestExperiment.main(new String[0]);

        HashMap<Integer, Long> times = new HashMap<Integer, Long>();
        for (int i = 3; i < 15; i = i + 1) {
            long timeBeforeExecution = System.currentTimeMillis();
            SmartForestExperiment.forestWidth = i;
            SmartForestExperiment.main(new String[0]);
            long mesuredTime = (System.currentTimeMillis() - timeBeforeExecution);
            System.out.println("Size = " + i + " ---> " + mesuredTime + " ms");
            times.put(i, mesuredTime);
        }

        System.out.println("=====================R Script =========================");
        System.out.println("library(ggplot2)");
        System.out.print("expData <- data.frame(x=c(");
        boolean firstValue = true;
        for (Integer i : times.keySet()) {
            if (!firstValue) {
                System.out.print(",");
            }
            System.out.print((i*i));
            firstValue = false;
        }
        System.out.print("), y= c(");
        firstValue = true;
        for (Long i : times.values()) {
            if (!firstValue) {
                System.out.print(",");
            }
            System.out.print(i);
            firstValue = false;
        }
        System.out.println("))");

        System.out.println("ggplot(expData, aes(x, y)) + geom_point(legend = FALSE) + geom_line() + scale_y_continuous(\"Computation time in millisecondes\") + scale_x_continuous(\"Number of sensor simulated in forest\")");
        
        System.out.println("=====================R Script =========================");


        assertTrue(true);
    }
}
