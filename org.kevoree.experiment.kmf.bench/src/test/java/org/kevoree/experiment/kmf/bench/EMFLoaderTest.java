package org.kevoree.experiment.kmf.bench;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.CSVInvocationReportModule;
import org.databene.contiperf.report.CSVLatencyReportModule;
import org.databene.contiperf.report.CSVSummaryReportModule;
import org.databene.contiperf.report.HtmlReportModule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.tools.emf.compat.EMFXmiHelper;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 22/03/12
 * Time: 22:52
 */
@PerfTest(invocations = 50)
public class EMFLoaderTest {
    @Before
    public void setUp() {
        //WARM UP
        for (int i = 0; i < 20; i++) {
           // try {
              //  EMFXmiHelper.loadStream(this.getClass().getClassLoader().getResourceAsStream("sky.kev"));
           // } catch (IOException e) {
           //     e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           // }
        }
    }

    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    @Test
    public void test1() throws Exception {
        EMFXmiHelper.loadStream(this.getClass().getClassLoader().getResourceAsStream("sky.kev"));
    }

    @Test
    public void test2() throws Exception {
        EMFXmiHelper.loadStream(this.getClass().getClassLoader().getResourceAsStream("aGood.kev"));
    }
}
