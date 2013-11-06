package org.kevoree.thesis;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import org.kevoree.ContainerRoot;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.api.service.core.script.KevScriptEngineException;
import org.kevoree.cloner.DefaultModelCloner;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.impl.DefaultKevoreeFactory;
import org.kevoree.serializer.JSONModelSerializer;
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

        String folder = "/home/edaubert/";

        // 1
        generateKevScript(folder + "thesis_validation5-3.kevs", 1000, 250, false);
        generateModel(folder + "thesis_validation5-3.kevs", folder + "thesis_validation5-3.kev");
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
//        generateKevScript(folder + "/thesis_validation99995-0.kevs", 1, 0, true);
//       generateModel(folder + "/thesis_validation99995-0.kevs", folder + "thesis_validation99995-0.kev");
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
        DefaultModelCloner cloner = new DefaultModelCloner();
        for (int i = 1; i < 5; i++) {
            models[i] = (ContainerRoot)cloner.clone(models[0]);
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

            /*System.out.println("starting to generate model: " + System.currentTimeMillis());
            KevoreeXmiHelper.instance$.save(storageModel, kengine.interpret());
            System.out.println("finishing to generate model: " + System.currentTimeMillis());*/


            JSONModelSerializer serializer = new JSONModelSerializer();
            File fp = new File(storageModel);
            FileOutputStream fop = new FileOutputStream(fp);


            serializer.serializeToStream(kengine.interpret(),fop);

            fop.flush();
            fop.close();

            System.out.println(fp.getAbsolutePath());
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

        kengine.append("merge 'mvn:org.kevoree.corelibrary.sky/org.kevoree.library.sky.jails/2.0.6-SNAPSHOT'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.basicGossiper/2.0.6-SNAPSHOT'");

        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.defaultChannels/2.0.6-SNAPSHOT'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.p2pSock/2.0.6-SNAPSHOT'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.nioChannels/2.0.6-SNAPSHOT'");

        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.webserver.api/2.0.6-SNAPSHOT'");
        kengine.append("merge 'mvn:org.kevoree.corelibrary.javase/org.kevoree.library.javase.webserver.tjws/2.0.6-SNAPSHOT'");

//        kengine.append("merge 'mvn:org.kevoree.corelibrary.sky/org.kevoree.library.sky.provider/2.0.6-SNAPSHOT'");
//        kengine.append("merge 'mvn:org.kevoree.corelibrary.sky/org.kevoree.library.sky.provider.web/2.0.6-SNAPSHOT'");
//        kengine.append("merge 'mvn:org.kevoree.corelibrary.sky/org.kevoree.library.sky.minicloud/2.0.6-SNAPSHOT'");

        kengine.append("addNode atmosphere : JavaSENode");

//        kengine.append("network atmosphere => atmosphere { 'KEVOREE.remote.node.ip'= '127.0.0.1'}");

        kengine.append("addNode atmosphere : JavaSENode");
        //{role='host/container', VMARGS = '-Xmx1024m -XX:MaxPermSize=512m', logLevel='INFO'}
        kengine.append("addNode fog1 : JailNode {inet='alc0', subnet='10.0.1.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog2 : JailNode {inet='alc0', subnet='10.0.2.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog3 : JailNode {inet='alc0', subnet='10.0.3.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog4 : JailNode {inet='alc0', subnet='10.0.4.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog5 : JailNode {inet='alc0', subnet='10.0.5.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog6 : JailNode {inet='alc0', subnet='10.0.6.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog7 : JailNode {inet='alc0', subnet='10.0.7.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog8 : JailNode {inet='alc0', subnet='10.0.8.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog9 : JailNode {inet='alc0', subnet='10.0.9.0', mask='24', logLevel='INFO'}");
        kengine.append("addNode fog10 : JailNode {inet='alc0', subnet='10.0.10.0', mask='24', logLevel='INFO'}");

        kengine.append("network atmosphere => atmosphere { 'KEVOREE.remote.node.ip'= '127.0.0.1'}");
        kengine.append("network fog1 => fog1 { 'KEVOREE.remote.node.ip'= '10.0.1.1'}");
        kengine.append("network fog2 => fog2 { 'KEVOREE.remote.node.ip'= '10.0.2.1'}");
        kengine.append("network fog3 => fog3 { 'KEVOREE.remote.node.ip'= '10.0.3.1'}");
        kengine.append("network fog4 => fog4 { 'KEVOREE.remote.node.ip'= '10.0.4.1'}");
        kengine.append("network fog5 => fog5 { 'KEVOREE.remote.node.ip'= '10.0.5.1'}");
        kengine.append("network fog6 => fog6 { 'KEVOREE.remote.node.ip'= '10.0.6.1'}");
        kengine.append("network fog7 => fog7 { 'KEVOREE.remote.node.ip'= '10.0.7.1'}");
        kengine.append("network fog8 => fog8 { 'KEVOREE.remote.node.ip'= '10.0.8.1'}");
        kengine.append("network fog9 => fog9 { 'KEVOREE.remote.node.ip'= '10.0.9.1'}");
        kengine.append("network fog10 => fog10 { 'KEVOREE.remote.node.ip'= '10.0.10.1'}");

        kengine.append("addGroup sync : BasicGossiperGroup");

        kengine.append("addToGroup sync atmosphere");



      /*  kengine.append("addComponent webServer@atmosphere :KTinyWebServer {port = '8080', timeout = '5000'}");
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
        kengine.append("addComponent modelSubmitter@atmosphere :ThesisModelSubmitter");*/


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
//            kengine.append("bind {componentName}.content@{nodeName} => chan1");
//            kengine.append("bind {componentName}.request@{nodeName} => chan2");
        }

        System.out.println("Components are built.");

       /* if (nbComponent > 0) {
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

        }*/

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
