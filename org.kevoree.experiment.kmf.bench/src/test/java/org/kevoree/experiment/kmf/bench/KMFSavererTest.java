//package org.kevoree.experiment.kmf.bench;
//
///**
// * Created by IntelliJ IDEA.
// * User: duke
// * Date: 22/03/12
// * Time: 22:34
// */
//
//import org.databene.contiperf.PerfTest;
//import org.databene.contiperf.junit.ContiPerfRule;
//import org.databene.contiperf.report.CSVInvocationReportModule;
//import org.databene.contiperf.report.CSVLatencyReportModule;
//import org.databene.contiperf.report.CSVSummaryReportModule;
//import org.databene.contiperf.report.HtmlReportModule;
//import org.junit.Before;
//import org.junit.Rule;
//import org.junit.Test;
//import org.kemf.compat.kevoree.ContainerRoot;
//import org.kevoree.framework.KevoreeXmiHelper;
//
//import java.io.ByteArrayOutputStream;
//
//@PerfTest(invocations = 1)
//public class KMFSavererTest {
//
//    private org.kevoree.ContainerRoot model = null;
//
//    @Before
//    public void setUp() {
//        //WARM UP
//        model = KevoreeXmiHelper.loadStream(this.getClass().getClassLoader().getResourceAsStream("aGood.kev"));
//    }
//
//    @Rule
//    public ContiPerfRule rule = new ContiPerfRule();
//
//    @Test
//    public void test1() throws Exception {
//        KevoreeXmiHelper.saveStream(new ByteArrayOutputStream(),model);
//    }
//
//
//}