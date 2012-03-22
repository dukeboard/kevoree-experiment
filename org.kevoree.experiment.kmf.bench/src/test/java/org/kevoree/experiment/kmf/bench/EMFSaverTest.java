package org.kevoree.experiment.kmf.bench;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.kemf.compat.kevoree.ContainerRoot;
import org.kevoree.tools.emf.compat.EMFXmiHelper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 22/03/12
 * Time: 22:52
 */
@PerfTest(invocations = 1)
public class EMFSaverTest {

    private ContainerRoot model = null;

    @Before
    public void setUp() throws IOException {
        //WARM UP
        model = EMFXmiHelper.loadStream(this.getClass().getClassLoader().getResourceAsStream("aGood.kev"));

    }

    @Rule
    public ContiPerfRule rule = new ContiPerfRule();

    @Test
    public void test1() throws Exception {
        EMFXmiHelper.saveStream(new ByteArrayOutputStream(),model);
    }

}
