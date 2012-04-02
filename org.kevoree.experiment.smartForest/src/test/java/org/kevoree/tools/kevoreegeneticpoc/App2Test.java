package org.kevoree.tools.kevoreegeneticpoc;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.kevoree.experiment.smartForest.SmartForestExperiment;
import org.kevoree.experiment.smartForest.results.StatHandler$;

import java.util.*;

/**
 * Unit test for simple App.
 */
public class App2Test
        extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public App2Test(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(App2Test.class);
    }


    /**
     * Rigourous Test :-)
     */
    public void testApp() {


        HashMap<String, List<Float>> bestValues = new HashMap<String, List<Float>>();
        Set<Integer> generations = new HashSet<Integer>();


        //WARMUP
        SmartForestExperiment.forestWidth = 12;

        List<String> runs = new ArrayList<String>();
        runs.add("EMPTY_INIT");
        runs.add("FULL_INIT");
        runs.add("RANDOM_INIT");
        runs.add("HUMAN_INIT");

        for (String initParam : runs) {
            System.out.println("Run "+initParam);
            for (int i = 50; i < 200; i = i + 35) {
                System.out.println("Run for "+i +" generations");
                generations.add(i);
                SmartForestExperiment.generationsForSingle = i;
                SmartForestExperiment.generationsForMulti = i;
                System.setProperty("INIT_VAR", initParam);
                StatHandler$.MODULE$.putValue(0f);
                SmartForestExperiment.initTimeStat();
                SmartForestExperiment.main(new String[0]);
                long mesuredTime = SmartForestExperiment.collectStatistics();
                List<Float> values = bestValues.get(initParam);
                if (values == null) {
                    values = new ArrayList<Float>();
                }
                values.add(StatHandler$.MODULE$.bestValue());
                bestValues.put(initParam, values);
            }
        }

        System.out.println("=====================R Script =========================");
        System.out.print("generations <- c(");
        boolean firstValue = true;
        for (Integer i : generations) {
            if (!firstValue) {
                System.out.print(",");
            }
            System.out.print((i * i));
            firstValue = false;
        }
        System.out.println(")");

        for (String run : bestValues.keySet()) {
            System.out.print(run+" <- c(");
            firstValue = true;
            for (Float i : bestValues.get(run)) {
                if (!firstValue) {
                    System.out.print(",");
                }
                System.out.print(i);
                firstValue = false;
            }
            System.out.println(")");
        }

        System.out.println("=====================R Script =========================");
        assertTrue(true);
    }
}
