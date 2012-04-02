package org.kevoree.experiment.kmf.bench;

import org.kevoree.ContainerRoot;
import org.kevoree.cloner.ModelCloner;
import org.kevoree.framework.KevoreeXmiHelper$;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: duke
 * Date: 01/04/12
 * Time: 18:50
 */
public class KMFMemoryBenchmark {

    static List keeper = new ArrayList<Object>();
    static String modelURL = "/Users/duke/Documents/dev/dukeboard/kevoree-experiment/org.kevoree.experiment.smartForest/duke.irisa.fr-generated/kevoreeIndividualModel.kev";

    public static void main(String[] args) throws InterruptedException {

        List<Integer> stepIDS = new ArrayList<Integer>();
        List<Long> elems = new ArrayList<Long>();
        List<Double> heapMem = new ArrayList<Double>();
        List<Long> garbageTime = new ArrayList<Long>();
        List<Double> cloneTime = new ArrayList<Double>();

        MemoryMXBean beanMemory = ManagementFactory.getMemoryMXBean();
        long begin = System.currentTimeMillis();

        ContainerRoot model = KevoreeXmiHelper$.MODULE$.load(modelURL);
        ModelCloner cloner = new ModelCloner();
        for (int i = 0; i < 10001; i++) {
            /* ContainerRoot model2 = KevoreeXmiHelper$.MODULE$.load("/Users/duke/Documents/dev/dukeboard/kevoree-experiment/org.kevoree.experiment.smartForest/src/main/resources/defaultLibrary.kev");
         keeper.add(model2);   */

            long beforeClone = System.nanoTime();
            keeper.add(cloner.clone(model));
            double after = (System.nanoTime() - beforeClone) / Math.pow(10, 3);

            System.out.println(i + "->" + after);

            //double memoryUsed = beanMemory.getHeapMemoryUsage().getUsed() / Math.pow(10,6)  ;
            //System.out.println(i+ " : Memory used "+memoryUsed);

            if (i % 1000 == 0 && i != 0) {
                stepIDS.add(i);
                cloneTime.add(after);

                long newBegin = System.currentTimeMillis();
                for (int i2 = 0; i2 < 2; i2++) {
                    System.gc();
                    Thread.sleep(1000);
                }
                begin = begin + (System.currentTimeMillis() - newBegin); // COMPENSATE WAITING TIME FOR GC
                //time.add((System.currentTimeMillis() - begin));
                //System.out.println(i+"-"+(System.currentTimeMillis() - begin));
                double memoryUsed = beanMemory.getHeapMemoryUsage().getUsed() / Math.pow(10, 6);
                heapMem.add(memoryUsed);
                long gct = 0;
                for (GarbageCollectorMXBean gmx : ManagementFactory.getGarbageCollectorMXBeans()) {
                    gct = gct + gmx.getCollectionTime();
                }
                garbageTime.add(gct);
            }

        }

        System.out.println("t-" + (System.currentTimeMillis() - begin));
        double memoryUsed = beanMemory.getHeapMemoryUsage().getUsed() / Math.pow(10, 6);
        System.out.println("End : Memory used " + memoryUsed);
        for (GarbageCollectorMXBean gmx : ManagementFactory.getGarbageCollectorMXBeans()) {
            System.out.println("GC TIME : " + gmx.getCollectionTime());
            System.out.println(gmx.getName());
        }


        System.out.println("======================== Script R ==========================");
        boolean isfirst = true;
        System.out.print("steps <- c(");
        for (Integer i : stepIDS) {
            if (!isfirst) {
                System.out.print(",");
            }
            System.out.print(i);
            isfirst = false;
        }
        System.out.println(")\n");
        isfirst = true;
        System.out.print("heapMem <- c(");
        for (Double i : heapMem) {
            if (!isfirst) {
                System.out.print(",");
            }
            System.out.print(i);
            isfirst = false;
        }
        System.out.println(")\n");
        isfirst = true;
        System.out.print("garbageTime <- c(");
        for (Long i : garbageTime) {
            if (!isfirst) {
                System.out.print(",");
            }
            System.out.print(i);
            isfirst = false;
        }
        System.out.println(")\n");
        isfirst = true;
        System.out.print("clonetime <- c(");
        for (Double i : cloneTime) {
            if (!isfirst) {
                System.out.print(",");
            }
            System.out.print(i);
            isfirst = false;
        }
        System.out.println(")\n");


        System.out.println("======================== Script R ==========================");
    }

}
