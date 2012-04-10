package org.kevoree.experiment.kmf.bench;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.kemf.compat.kevoree.ContainerRoot;
import org.kevoree.tools.emf.compat.EMFXmiHelper;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 01/04/12
 * Time: 18:50
 */
public class EMFMemoryBenchmark {


    static List keeper = new ArrayList<Object>();

    static String modelURL = "/Users/duke/Documents/dev/dukeboard/kevoree-experiment/org.kevoree.experiment.smartForest/duke.irisa.fr-generated/kevoreeIndividualModel.kev";



    public static void main(String[] args) throws IOException, InterruptedException {

        MemoryMXBean beanMemory = ManagementFactory.getMemoryMXBean();

        long begin = System.currentTimeMillis();
        ContainerRoot model = EMFXmiHelper.loadStream(new FileInputStream(modelURL));
        for (int i = 0; i < 10001; i++) {
            /*
        ContainerRoot model2 = EMFXmiHelper.loadStream(new FileInputStream("/Users/duke/Documents/dev/dukeboard/kevoree-experiment/org.kevoree.experiment.smartForest/src/main/resources/defaultLibrary.kev"));
        EcoreUtil.resolveAll(model2);
        keeper.add(model2);
            */

            /*
         ByteArrayOutputStream out = new ByteArrayOutputStream();
         EMFXmiHelper.saveStream(out, model);
         ByteInputStream input = new ByteInputStream();
         input.setBuf(out.toByteArray());
         ContainerRoot model2 = EMFXmiHelper.loadStream(input);
         EcoreUtil.resolveAll(model2);
         keeper.add(model2);
         out.close();
         input.close();
         input = null;
         out = null; */


            long before = System.nanoTime();
            ContainerRoot model2 = EcoreUtil.copy(model);
            EcoreUtil.resolveAll(model2);
            System.out.println(i+"-"+(System.nanoTime()-before));


            keeper.add(model2);

            double memoryUsed = beanMemory.getHeapMemoryUsage().getUsed() / Math.pow(10, 6);
            System.out.println("End : Memory used " + memoryUsed);

        }
        System.out.println("ExpTime="+(System.currentTimeMillis()-begin));
        for (int i = 0; i < 3; i++) {
            System.gc();
            Thread.sleep(1000);
        }

        double memoryUsed = beanMemory.getHeapMemoryUsage().getUsed() / Math.pow(10, 6);
        System.out.println("End : Memory used " + memoryUsed);

        for(GarbageCollectorMXBean gmx : ManagementFactory.getGarbageCollectorMXBeans()){
            System.out.println("GC TIME : "+gmx.getCollectionTime());
        }

    }

}
