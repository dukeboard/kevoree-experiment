/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kevoree.experiment.smartbuilding.com;

import org.kevoree.ContainerRoot;
import org.kevoree.KevoreeFactory;
import org.kevoree.framework.KevoreeActor;
import org.kevoree.tools.marShell.KevScriptOfflineEngine;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author ffouquet
 */
public class NativeLibUtil {


    public static ContainerRoot loadKevs(InputStream in) {
        String ins = null;
        try {
            org.kevoree.tools.aether.framework.NodeTypeBootstrapHelper hl = new org.kevoree.tools.aether.framework.NodeTypeBootstrapHelper();
            KevScriptOfflineEngine kevOfflineEngine = new KevScriptOfflineEngine(KevoreeFactory.createContainerRoot(), hl);
            kevOfflineEngine.addVariable("kevoree.version", KevoreeFactory.getVersion());
            ins = new String(FileManager.load(in));
            kevOfflineEngine.append(ins);
            ContainerRoot model = kevOfflineEngine.interpret();
            return model;
        } catch (Exception e) {
            System.out.println(ins);
            e.printStackTrace();
            return null;
        }
    }

    public static void copyFile(InputStream in, String to) {
        OutputStream out = null;
        try {
            out = new FileOutputStream(to);
            while (true) {
                int data = in.read();
                if (data == -1) {
                    break;
                }
                out.write(data);
            }
            in.close();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(NativeLibUtil.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(NativeLibUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException ex) {
                    Logger.getLogger(NativeLibUtil.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
