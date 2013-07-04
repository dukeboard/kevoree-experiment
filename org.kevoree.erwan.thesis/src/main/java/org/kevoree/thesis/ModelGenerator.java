package org.kevoree.thesis;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.kevoree.ContainerRoot;
import org.kevoree.KevoreeFactory;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.api.service.core.script.KevScriptEngineException;
import org.kevoree.cloner.ModelCloner;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.impl.DefaultKevoreeFactory;
import org.kevoree.tools.aether.framework.NodeTypeBootstrapHelper;
import org.kevoree.tools.marShell.KevScriptOfflineEngine;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 03/11/12
 * Time: 18:22
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@Library(name = "Test")
@ComponentType
public class ModelGenerator {
   static DefaultKevoreeFactory defaultKevoreeFactory = new DefaultKevoreeFactory();
    public static void main(String[] args) {
        Logger root = (Logger) LoggerFactory.getLogger(org.slf4j.Logger.ROOT_LOGGER_NAME);
        root.setLevel(Level.WARN);

        String folder = "/tmp/gen";

        // 1
//        generateKevScript(folder + "thesis_validation5-3.kevs", 5, 3, false);
//        generateModel(folder + "thesis_validation5-3.kevs", folder + "thesis_validation5-3.kev");
//        load(folder + "thesis_validation5-3.kev");

        // 2
//        generateKevScript(folder + "thesis_validation50-4000.kevs", 50, 4000, false);
//        generateModel(folder + "thesis_validation50-4000.kevs", folder + "thesis_validation50-4000.kev");
//        load(folder + "thesis_validation50-4000.kev");

        // 3
//        generateKevScript(folder + "thesis_validation500-40000.kevs", 500, 40000, false);
//        generateModel(folder + "thesis_validation500-40000.kevs", folder + "thesis_validation500-40000.kev");
//        load(folder + "thesis_validation500-40000.kev");

        // 4
//        generateKevScript(folder + "thesis_validation5000-100000.kevs", 5000, 100000, false);
//        generateModel(folder + "thesis_validation5000-100000.kevs", folder + "thesis_validation5000-100000.kev");
//        load(folder + "thesis_validation5000-100000.kev");

        // 5
//        generateKevScript(folder + "thesis_validation5000-200000.kevs", 5000, 200000, false);
//        generateModel(folder + "thesis_validation5000-200000.kevs", folder + "thesis_validation5000-200000.kev");
//        load(folder + "thesis_validation5000-200000.kev");

        // 6
//        generateKevScript(folder + "thesis_validation5000-300000.kevs", 5000, 300000, false);
//        generateModel(folder + "thesis_validation5000-300000.kevs", folder + "thesis_validation5000-300000.kev");
//        load(folder + "thesis_validation5000-300000.kev");

        // 7
//        generateKevScript(folder + "thesis_validation5000-400000.kevs", 5000, 400000, false);
//        generateModel(folder + "thesis_validation5000-400000.kevs", folder + "thesis_validation5000-400000.kev");
//        load(folder + "thesis_validation5000-400000.kev");

        // 8
//        generateKevScript(folder + "thesis_validation50000-400000.kevs", 50000, 400000, false);
//        generateModel(folder + "thesis_validation50000-400000.kevs", folder + "thesis_validation50000-400000.kev");
//        load(folder + "thesis_validation5000-400000.kev");

        // 9
        generateKevScript(folder + "/thesis_validation99995-0.kevs", 1, 0, true);
       generateModel(folder + "/thesis_validation99995-0.kevs", folder + "thesis_validation99995-0.kev");
//        load(folder + "thesis_validation10000-400000.kev");

        // 10
//        generateKevScript("/root/thesis_validation507000-400000.kevs", 507000, 400000, false);
//        generateModel("/root/thesis_validation507000-400000.kevs", "/root/thesis_validation507000-400000.kev");
//        load("/home/edaubert/Documents/svns/kevoree_gforge/erwan_thesis/valid/thesis_validation400000.kev");
    }

    /*private static void loadXML(String model) {
        Node dom = XML.loadFile(model);
    }*/

    private static void load(String model) {
        ContainerRoot[] models = new ContainerRoot[10];
        System.out.println("starting to load model: " + System.currentTimeMillis());
        models[0] = KevoreeXmiHelper.instance$.load(model);
        System.out.println("first model loaded: " + System.currentTimeMillis());
        ModelCloner cloner = new ModelCloner();
        for (int i = 1; i < 5; i++) {
            models[i] = cloner.clone(models[0]);
        }
        System.out.println("finishing to load model: " + System.currentTimeMillis());
        while (true) {
            try {
                Thread.sleep(500000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generateModel(String kevScript, String storageModel) {
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            BufferedReader fileStream = new BufferedReader(new FileReader(new File(kevScript)));

            KevScriptEngine kengine = new KevScriptOfflineEngine(defaultKevoreeFactory.createContainerRoot(), new NodeTypeBootstrapHelper());
            String line = fileStream.readLine();
            while (line != null) {
                kengine.append(line).append("\n");
                line = fileStream.readLine();
            }

            System.out.println("starting to generate model: " + System.currentTimeMillis());
            KevoreeXmiHelper.instance$.save(storageModel, kengine.interpret());
            System.out.println("finishing to generate model: " + System.currentTimeMillis());
        } catch (KevScriptEngineException e) {
            System.err.println("Unable to save the generated model");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void generateKevScript(String storageModel, int nbNode, int nbComponent, boolean defineParentNode) {
        System.out.println("Building model...");
        KevScriptEngine kengine = new KevScriptOfflineEngine(defaultKevoreeFactory.createContainerRoot(), new NodeTypeBootstrapHelper());

        kengine.append("merge 'mvn:org.kevoree.corelibrary.sky/org.kevoree.library.sky.jails/"+defaultKevoreeFactory.getVersion()+"'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.basicGossiper/"+defaultKevoreeFactory.getVersion()+"'");

        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.defaultChannels/"+defaultKevoreeFactory.getVersion()+"'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.p2pSock/"+defaultKevoreeFactory.getVersion()+"'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.nioChannels/"+defaultKevoreeFactory.getVersion()+"'");

        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.webserver.api/"+defaultKevoreeFactory.getVersion()+"'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.webserver.tjws/"+defaultKevoreeFactory.getVersion()+"'");

        kengine.append("merge 'mvn:org.kevoree.corelibrary.sky/org.kevoree.library.sky.provider/"+defaultKevoreeFactory.getVersion()+"'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.sky/org.kevoree.library.sky.provider.web/"+defaultKevoreeFactory.getVersion()+"'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.sky/org.kevoree.library.sky.minicloud/"+defaultKevoreeFactory.getVersion()+"'");

        kengine.append("addNode atmosphere : JavaSENode");

        kengine.append("network atmosphere => atmosphere { 'KEVOREE.remote.node.ip'= '127.0.0.1'}");


        kengine.append("addGroup sync : BasicGossiperGroup");

        kengine.append("addToGroup sync atmosphere");



        kengine.append("addComponent webServer@atmosphere :KTinyWebServer {port = '8080', timeout = '5000'}");
        kengine.append("addComponent iaasPage@atmosphere : IaaSKloudResourceManagerPage { urlpattern='/iaas'}");
        kengine.append("addComponent iaasManager@atmosphere :IaaSKloudManager");


        kengine.append("addChannel iaasDelegateChannel : defSERVICE");
        kengine.append("addChannel requestChannel : defMSG");
        kengine.append("addChannel responseChannel : defMSG");

        kengine.append("bind webServer.handler@atmosphere => requestChannel");
        kengine.append("bind iaasPage.request@atmosphere => requestChannel");

        kengine.append("bind webServer.response@atmosphere => responseChannel");
        kengine.append("bind iaasPage.content@atmosphere => responseChannel");

        kengine.append("bind iaasManager.submit@atmosphere => iaasDelegateChannel");
        kengine.append("bind iaasPage.delegate@atmosphere => iaasDelegateChannel");


        kengine.append("addComponent ws@atmosphere : KTinyWebServer {port = '8081', timeout = '5000'}");

        kengine.append("addChannel chan1 : NioChannel");
        kengine.append("addChannel chan2 : NioChannel");

        kengine.append("bind ws.response@atmosphere => chan1");
        kengine.append("bind ws.handler@atmosphere => chan2");

        kengine.append("updateDictionary chan1 { port = '9000'}@atmosphere");
        kengine.append("updateDictionary chan2 { port = '16000'}@atmosphere");

        kengine.append("merge 'mvn:org.kevoree.thesis/org.kevoree.thesis/1.0'");
        kengine.append("addComponent modelSubmitter@atmosphere :ThesisModelSubmitter");


        for (int i = 0; i < nbNode; i++) {
            kengine.addVariable("childName", "childNode" + i);
            kengine.addVariable("parentNodeName", "fog" + (i % 10 + 1));
            kengine.append("addNode {childName} : PJavaSENode");
            if (defineParentNode) {
                kengine.append("addChild {childName} @ {parentNodeName}");
            }
        }

        System.out.println("Nodes are built.");

        int chanPort1 = 9000;
        int chanPort2 = 16000;
        for (int i = 0; i < nbComponent; i++) {
            kengine.addVariable("componentName", "c" + i);
            kengine.addVariable("nodeName", "childNode" + ((i % nbNode)));
            kengine.append("addComponent {componentName}@{nodeName} :HelloWorldPage");
            kengine.append("bind {componentName}.content@{nodeName} => chan1");
            kengine.append("bind {componentName}.request@{nodeName} => chan2");
        }

        System.out.println("Components are built.");

        if (nbComponent > 0) {
        for (int i = 0; i < nbNode; i++) {
            chanPort1++;
            chanPort2++;
            kengine.addVariable("nodeName", "childNode" + i);
            kengine.addVariable("chanPort1", "" + chanPort1);
            kengine.addVariable("chanPort2", "" + chanPort2);
            kengine.append("updateDictionary chan1 { port = '{chanPort1}'}@{nodeName}");
            kengine.append("updateDictionary chan2 { port = '{chanPort2}'}@{nodeName}");
        }

        System.out.println("Channels are updated.");

        }

        try {
            byte[] bytes = kengine.getScript().getBytes("UTF-8");
            File f = new File(storageModel);

            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(bytes);
            outputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
