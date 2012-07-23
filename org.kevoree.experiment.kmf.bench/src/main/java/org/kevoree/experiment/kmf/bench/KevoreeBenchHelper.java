package org.kevoree.experiment.kmf.bench;

import org.eclipse.emf.ecore.util.EcoreUtil;
import org.kevoree.loader.ContainerRootLoader;
import org.kevoree.tools.emf.compat.EMFXmiHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 23/03/12
 * Time: 07:41
 */
public class KevoreeBenchHelper {

    public static void main(String[] args) throws IOException {



/*
        for (int i = 0; i < 5; i++) {
            ContainerRootLoader.loadModel(new File("/Users/duke/Documents/dev/dukeboard/kevoree-experiment/org.kevoree.experiment.kmf.bench/src/test_old/resources/aGood.kev"));
        }*/


        for (int i = 0; i < 10; i++) {
        long deb = System.currentTimeMillis();
        ContainerRootLoader.loadModel(new File("/Users/duke/Documents/dev/dukeboard/kevoree-experiment/org.kevoree.experiment.smartForest/duke.irisa.fr-duke.irisa.fr-generated/kevoreeIndividualModel.kev"));
        System.out.println("Time : " + (System.currentTimeMillis() - deb) + " ms");
            System.out.println("Total Memory"+Runtime.getRuntime().freeMemory());
        }




        for (int i = 0; i < 10; i++) {
        long deb2 = System.currentTimeMillis();
        EcoreUtil.resolveAll(EMFXmiHelper.loadStream(new FileInputStream("/Users/duke/Documents/dev/dukeboard/kevoree-experiment/org.kevoree.experiment.smartForest/duke.irisa.fr-duke.irisa.fr-generated/kevoreeIndividualModel.kev")));
        System.out.println("Time : " + (System.currentTimeMillis() - deb2) + " ms");
            System.out.println("Total Memory"+Runtime.getRuntime().freeMemory());
        }




    }

}
