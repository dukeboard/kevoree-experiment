package org.kevoree.experiment.kmf.bench;

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 22/03/12
 * Time: 22:34
 */

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.databene.contiperf.report.CSVInvocationReportModule;
import org.databene.contiperf.report.CSVLatencyReportModule;
import org.databene.contiperf.report.CSVSummaryReportModule;
import org.databene.contiperf.report.HtmlReportModule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.kevoree.framework.KevoreeXmiHelper;

@PerfTest(invocations = 50)
public class KMFLoaderTest {

    @Before
    public void setUp() {
        //WARM UP
        for(int i=0 ; i< 20 ; i++){
           // KevoreeXmiHelper.loadStream(this.getClass().getClassLoader().getResourceAsStream("sky.kev"));
        }
    }

    @Rule
    public ContiPerfRule rule = new ContiPerfRule(
            new HtmlReportModule(),
            new CSVSummaryReportModule(),
            new CSVInvocationReportModule(),
            new CSVLatencyReportModule());

    @Test
    public void test1() throws Exception {
        KevoreeXmiHelper.loadStream(this.getClass().getClassLoader().getResourceAsStream("sky.kev"));
    }

    @Test
    public void test2() throws Exception {
        KevoreeXmiHelper.loadStream(this.getClass().getClassLoader().getResourceAsStream("aGood.kev"));
    }

}