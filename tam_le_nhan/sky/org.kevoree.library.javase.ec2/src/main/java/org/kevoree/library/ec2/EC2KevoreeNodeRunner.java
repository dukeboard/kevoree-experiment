package org.kevoree.library.ec2;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.model.*;
import com.jcraft.jsch.Session;
import org.kevoree.ContainerNode;
import org.kevoree.ContainerRoot;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.cloner.ModelCloner;
import org.kevoree.framework.Constants;
import org.kevoree.framework.KevoreePropertyHelper;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.library.sky.api.execution.KevoreeNodeRunner;
import org.kevoree.library.utils.SSHUtils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 01/07/13
 * Time: 10:46
 *
 * @author Erwan Daubert
 * @version 1.0
 */
public class EC2KevoreeNodeRunner extends KevoreeNodeRunner {

    private Ec2Node iaasNode;
    private String ip;
    private String ec2InstanceId;
    private Reservation reservation;
    private AmazonEC2 ec2;


    private Map<String,String> id2ip = new TreeMap<String,String>();
    private Map<String,String> id2dns = new TreeMap<String,String>();

    public EC2KevoreeNodeRunner(String nodeName, Ec2Node iaasNode, AmazonEC2 ec2, Map<String, String> id2ip, Map<String, String>id2dns) {
        super(nodeName);
        this.iaasNode = iaasNode;
        this.ec2 = ec2;
    }

    @Override
    public boolean startNode(ContainerRoot iaasModel, ContainerRoot childBootStrapModel) {
        try {
            String dnsNode = runEc2Instance(iaasModel);
            if (dnsNode != null){
                System.out.println("EC2 Instance is running at the host: " + dnsNode);
                String userName = this.iaasNode.getDictionary().get("userName").toString();
                String privateKey = this.iaasNode.getDictionary().get("keyPairPath").toString();
                Session session = SSHUtils.createSSHSession(userName, privateKey, dnsNode);
                // stop Kevoree service running on the node
                SSHUtils.sshRemoteCommand(session,"sudo service kevoree stop");
                // delete old config file of Kevoree on the node
                SSHUtils.sshRemoteCommand(session,"sudo rm /etc/kevoree/config");

                File kconfigFile = createKevoreeConfigFile(getNodeName());
                // copy new config file of Kevoree to home directory of the node
                SSHUtils.scpByChannel(session,kconfigFile.getAbsolutePath(),"config");
                // new config file to /etc/kevoree/config at the node
                SSHUtils.sshRemoteCommand(session,"sudo cp config /etc/kevoree/config");

                // Update current Kevoree model to the node
                File file = File.createTempFile("model", ".xmi");
                //String mfilePath = "model"+nodeName()+".xmi";
                KevoreeXmiHelper.instance$.save(file.getAbsolutePath(),childBootStrapModel);
                SSHUtils.scpByChannel(session,file.getAbsolutePath(),"bootmodel");
                SSHUtils.sshRemoteCommand(session,"sudo cp bootmodel /etc/kevoree/bootmodel");
                // restart Kevoree service at the node
                SSHUtils.sshRemoteCommand(session,"sudo service kevoree start");
                session.disconnect();

                // add ip on model
                ModelCloner cloner = new ModelCloner();
                ContainerRoot newModel = cloner.clone(childBootStrapModel);
                KevScriptEngine kengine = iaasNode.getKevScriptEngineFactory().createKevScriptEngine(newModel);
                //String instanceId = id2dns.get()
                kengine.addVariable("ip", dnsNode); //use public dns instead of public ip
                kengine.addVariable("dnsName", dnsNode);
                kengine.addVariable("ipKey", Constants.instance$.getKEVOREE_PLATFORM_REMOTE_NODE_IP());
                kengine.addVariable("nodeName", getNodeName());

                kengine.append("network {nodeName} { '{ipKey}' = '{ip}' }:ipv4/100");
                //kengine.append("network {nodeName} { '{ipKey}' = '{dnsName}' }:dns/100");
                // Tam comment here and uncomment return true;
                //newModel = kengine.interpret();
                //return sendModel(newModel);
                return true;
            }
            else return false;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return false;

        }
        //System.out.println(waitForTransitionCompletion());
        //return created;
            /*} catch (java.io.IOException e) {
                System.out.println("IO ERROR: " + e);
                e.printStackTrace();
                return false;
            }*/
        //return true;
    }

    @Override
    public boolean addNode(ContainerRoot iaasModel, ContainerRoot childBootStrapModel) {
        // FIXME ?
        return true;
    }

    @Override
    public boolean stopNode() {
        // TODO stop and remove the VM on AMazaon EC2
        //return false;
        boolean stopNodeStatus = false;
        try {
            stopNodeStatus = terminateEc2InstanceByID(ec2InstanceId);
            return stopNodeStatus;
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return stopNodeStatus;
        }
    }

    @Override
    public boolean removeNode() {
        // FIXME ?
        return true;
    }

    // run an EC2 Instance and return a public DSN of the running instance.
    public String runEc2Instance(ContainerRoot iaasModel) throws Exception {
        //NOTE: ask to use log of Kevoree
        //logger.log(Level.INFO,"Running new instance..");
        System.out.println("--> Running new instance ...");
        String imageID = this.iaasNode.getDictionary().get("imageID").toString();
        String keyName = getKeyPairName(this.iaasNode.getDictionary().get("keyPairPath").toString());
        String secGroup = this.iaasNode.getDictionary().get("securityGroup").toString();
        String instanceType = this.iaasNode.getDictionary().get("instanceType").toString();
        ContainerNode node = iaasModel.findNodesByID(getNodeName())        ;
        // Get the value of DictionaryAttribute "imageName" from the node (e.g. PEc2Node)
        // which is deployed in this EC2Node
        String knodeName = KevoreePropertyHelper.instance$.getProperty(node, "imageName", false, "")    ;

        //Prepares the run request.
        RunInstancesRequest runInstancesRequest = new RunInstancesRequest();
        runInstancesRequest.setImageId(imageID);
        runInstancesRequest.setKeyName(keyName);
        runInstancesRequest.setSecurityGroups(Collections.singleton(secGroup));
        runInstancesRequest.setInstanceType(instanceType);
        runInstancesRequest.setMaxCount(1);
        runInstancesRequest.setMinCount(1);

        //Executes the run request.
        String instanceId = ""; //Will be changed eventually.
        try {
            RunInstancesResult runInstancesResult = ec2.runInstances(runInstancesRequest);
            instanceId = runInstancesResult.getReservation().getInstances().get(0).getInstanceId();
            //logger.log(Level.INFO,"Sent RunInstanceRequest successfully..");
            System.out.println("--> Sent RunInstanceRequest successfully..");
        } catch (Exception e) {
            //logger.log(Level.SEVERE,"Error while sending RunInstanceRequest!" );
            System.out.println("--> Error while sending RunInstanceRequest!");
            e.printStackTrace(System.out);
            throw e; //Forwards the exception
        }

        //Waits for the instance to be "running".
        String instanceIp = null; //Will be changed eventually.
        String dnsName  = null;
        boolean done = false;
        while (!done) {
            try {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException ex){
                    // Thread.currentThread().interrupt();
                }

                DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest();
                describeInstancesRequest.withInstanceIds(instanceId);
                DescribeInstancesResult describeInstancesResult = ec2.describeInstances(describeInstancesRequest);

                Instance instance = describeInstancesResult.getReservations().get(0).getInstances().get(0);
                //logger.log(Level.INFO,"Current instance state: " + instance.getState().getName() + "..");
                System.out.println("--> Current instance state: " + instance.getState().getName() + "..");
                if (instance.getState().getName().equals("running")) {
                    instanceIp = instance.getPublicIpAddress();
                    dnsName = instance.getPublicDnsName();
                    System.out.println("DSN: "+dnsName);
                    id2ip.put(instanceId, instanceIp);
                    id2dns.put(instanceId,dnsName);
                    ec2InstanceId = instanceId;
                    done = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //logger.log(Level.SEVERE,"Error while waiting for instance to become running!" );
                System.out.println("--> Error while waiting for instance to become running!");
                throw e;
            }
        }

        //logger.log(Level.INFO,"Instance ran with IP: " + instanceIp + ".");
        System.out.println("--> Instance ran with IP: " + instanceIp + " and DNS: "+dnsName);
        System.out.println("--> Kevoree node: "+knodeName);

        // add ip on model
        ModelCloner cloner = new ModelCloner();
        ContainerRoot newModel = cloner.clone(iaasModel);
        KevScriptEngine kengine = iaasNode.getKevScriptEngineFactory().createKevScriptEngine(newModel);
        kengine.addVariable("ip", instanceIp);
        kengine.addVariable("dnsName", dnsName);
        kengine.addVariable("ipKey", Constants.instance$.getKEVOREE_PLATFORM_REMOTE_NODE_IP());
        kengine.addVariable("nodeName", getNodeName());

        kengine.append("network {nodeName} { '{ipKey}' = '{ip}' }:ipv4/100");
        kengine.append("network {nodeName} { '{ipKey}' = '{dnsName}' }:dns/100");

        newModel = kengine.interpret();
        final ContainerRoot model = newModel;
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    // Thread.currentThread().interrupt();
                }
                // Tam uncomment here
                //iaasNode.getModelService().atomicUpdateModel(model);
            }
        }                                                              .start();
        return dnsName;
    }

    /**
     * Terminates a VM instance.
     *
     * @param instanceIp The IP address of the instance to be removed
     */
    public boolean terminateEc2InstanceByIPAddr(String instanceIp)throws Exception{
        try{
            //logger.log(Level.INFO,"Terminating instance with IP: " + instanceIp + "..");
            System.out.println("Terminating instance with IP Address: " + instanceIp + "..");
            String instanceId = id2ip.get(instanceIp);

            TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest();
            terminateInstancesRequest.withInstanceIds(instanceId);
            ec2.terminateInstances(terminateInstancesRequest);

            id2ip.remove(instanceIp);
            //logger.log(Level.INFO,"Terminated instance successfully.");
            System.out.println("Terminated instance successfully.");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            //logger.log(Level.SEVERE,"Error while waiting for instance to become running!" );
            System.out.println("--> Error while terminating the instance with IP Address: "+instanceIp);
            return false;
        }
    }
    public boolean terminateEc2InstanceByID(String instanceId)throws Exception{
        try{
            //logger.log(Level.INFO,"Terminating instance with IP: " + instanceIp + "..");
            System.out.println("Terminating instance with InstanceID: " + instanceId + "..");
            //String instanceId = id2ip.get(instanceId);

            TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest();
            terminateInstancesRequest.withInstanceIds(instanceId);
            ec2.terminateInstances(terminateInstancesRequest);
            waitFor(instanceId,"terminated");
            id2ip.remove(instanceId);
            //logger.log(Level.INFO,"Terminated instance successfully.");
            System.out.println("Terminated instance successfully.");
            return true;
        }catch (Exception e){
            e.printStackTrace();
            //logger.log(Level.SEVERE,"Error while waiting for instance to become running!" );
            System.out.println("--> Error while terminating the instance with InstanceID: "+instanceId);
            return false;
        }
    }

    private void waitFor(String instanceID, String status) throws Exception {
        boolean done = false;
        while (!done) {
            try {
                try{
                    Thread.sleep(2000);
                }catch (InterruptedException ex){
                    // Thread.currentThread().interrupt();
                }

                DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest();
                describeInstancesRequest.withInstanceIds(instanceID);
                DescribeInstancesResult describeInstancesResult = ec2.describeInstances(describeInstancesRequest);

                Instance instance = describeInstancesResult.getReservations().get(0).getInstances().get(0);
                //logger.log(Level.INFO,"Current instance state: " + instance.getState().getName() + "..");
                System.out.println("--> Current instance state: " + instance.getState().getName() + "..");
                if (instance.getState().getName().equals(status)) {
                    done = true;
                }
            } catch (Exception e) {
                e.printStackTrace();
                //logger.log(Level.SEVERE,"Error while waiting for instance to become running!" );
                System.out.println("--> Error while waiting for instance to become "+status+" !");
                throw e;
            }
        }

    }

    private boolean ec2Launch(ContainerRoot iaasModel) {

        System.out.println("--> EC2 Instance is launching ...");
        if (ec2 != null) {
            ContainerNode node = iaasModel.findNodesByID(getNodeName())        ;
            String instanceName = KevoreePropertyHelper.instance$.getProperty(node, "instanceName", false, "")    ;
            // test if imgName is not null
            // if it's null then ze use the one define in this node
            // else ze use it to set imageName attribute
            String nodeName = node.getName();

            System.out.println("* ec2 ec2Create()");
            String instanceType = null;
            String imageID = this.iaasNode.getDictionary().get("imageID").toString();
            System.out.println("--> imgName from DictionaryAttribute: "+imageID);
            RunInstancesRequest request =   new RunInstancesRequest();

            request.withImageId(imageID)
                    .withInstanceType("m1.small")
                    .withMinCount(1)
                    .withMaxCount(1)
                    .withKeyName("ubuntu")
                    .withSecurityGroups("quicklaunch-1");
            try {
                // TODO check why platform ip is null
                // maybe because we need to wait the end of the initialization of the VM
                // then ask the instance properties to get the ip
                System.out.println(ec2);
                System.out.println("Submitted request: "+request);
                RunInstancesResult result = ec2.runInstances(request);
                System.out.println("--> RunInstancesResult = "+result.toString());
            } catch (AmazonServiceException ase) {
                System.out.println("Error Message:    " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
                return false;

            } catch (AmazonClientException ace) {
                System.out.println("Error Message: " + ace.getMessage());
                return false;
            }

            return true;
        } else {
            System.err.println("Authentication is not done so we are not able to submit virtual machine on the Cloud");
            return false;
        }

    }

    private boolean ec2Create(ContainerRoot iaasModel) {
        // TODO add ssh key to be able to connect to the VM
        // How to define key pair ?
        if (ec2 != null) {
            ContainerNode node = iaasModel.findNodesByID(getNodeName())        ;
            String imgName = KevoreePropertyHelper.instance$.getProperty(node, "imgName", false, "")    ;
            // test if imgName is not null
            // if it's null then ze use the one define in this node
            // else ze use it to set imageName attribute
            String name = node.getName();

            System.out.println("* ec2 ec2Create()");
            String instanceType = null;
            String imageName = this.iaasNode.getDictionary().get("imgName").toString();
            //List<Tag> tags = new ArrayList<Tag>();
            int minCount = 1;
            int maxCount = 1;
            RunInstancesRequest request = new RunInstancesRequest();
            //RunInstancesRequest request = new RunInstancesRequest(){

            //};
            if (imageName != null) request.setImageId(imageName);
            if (instanceType != null) request.setInstanceType(instanceType);
            request.withSecurityGroups("quicklaunch-1");
            request.withKeyName("ubuntu");
            if (minCount > 0) {
                if (maxCount < minCount) maxCount = minCount;
                request.setMaxCount(maxCount);
                request.setMinCount(minCount);
            }
            try {
                // TODO check why platform ip is null
                // maybe because we need to wait the end of the initialization of the VM
                // then ask the instance properties to get the ip
                System.out.println(ec2);
                System.out.println(request);
                RunInstancesResult result = ec2.runInstances(request);
                Reservation reservation = result.getReservation();

                for (Instance instance : reservation.getInstances()) {
                    ec2InstanceId = instance.getInstanceId();
                    ip = instance.getPublicIpAddress();
                    System.out.println("* platform ip: " + ip);
                    System.out.println("* ec2 instanceid: " + ec2InstanceId);
                }

                System.out.println("");
            } catch (AmazonServiceException ase) {
                System.out.println("Error Message:    " + ase.getMessage());
                System.out.println("HTTP Status Code: " + ase.getStatusCode());
                System.out.println("AWS Error Code:   " + ase.getErrorCode());
                System.out.println("Error Type:       " + ase.getErrorType());
                System.out.println("Request ID:       " + ase.getRequestId());
                return false;

            } catch (AmazonClientException ace) {
                System.out.println("Error Message: " + ace.getMessage());
                return false;
            }

            /*ContainerNode newNode = new DefaultKevoreeFactory().createContainerNode();
            newNode.setName(encodeEc2Id(ec2InstanceId));// cloud instance id
            System.out.println("* ec2 PhysicalNodeName: " + newNode.getName());
            for (ContainerNode nn : iaasModel.getNodes()) {
                //System.out.println("name:"+ nn.getName());
                if (nn.getName().equals(targetNodeName)) {
                    newNode.addHosts(nn);
                    break;
                }
            }
            root.addNodes(newNode);*/

            return true;
        } else {
            System.err.println("Authentication is not done so we are not able to submit virtual machine on the Cloud");
            return false;
        }
    }

    public File createKevoreeConfigFile(String noteName) throws IOException {

        File fstream = File.createTempFile("config","");
        //File fstream = new File("config");
        try{
            // Create file
            PrintStream out = new PrintStream(new FileOutputStream(fstream));
            out.println("KEVOREE_VERSION=2.0.0-SNAPSHOT");
            out.println("NODE_NAME="+noteName);
            out.println("PING_PORT=9999");
            out.println("PING_TIMEOUT=3000");
            //Close the output stream
            out.close();
        }catch (Exception e){//Catch exception if any
            System.err.println("Error: " + e.getMessage());
        }
        return fstream;
    }


    // extract KeyPairName from the file path of keypair
    // e.g. Keypair file path = "/TamLN-INRIA/AmazonEC2/seckey/ubuntu.pem"  --> KeyPairName=ubuntu
    private String getKeyPairName(String filePath){
        return filePath.substring(filePath.lastIndexOf('/')+1,filePath.lastIndexOf('.'));
    }

    private boolean waitRunning(int timeout) {
        System.out.println("* ec2 waitRunning()");
        String res = "";
        long begin = System.currentTimeMillis();

        while (!res.equals("running")) {
            try {
                DescribeInstancesRequest request = new DescribeInstancesRequest();
                Collection<String> instanceIds = new LinkedList();
                instanceIds.add(ec2InstanceId);
                request.setInstanceIds(instanceIds);

                DescribeInstancesResult result = ec2.describeInstances(request);
                List<Reservation> reservations = result.getReservations();
                for (Reservation reservation : reservations) {
                    for (Instance instance : reservation.getInstances()) {
                        res = instance.getState().getName();
                        break;
                    }
                }

            } catch (AmazonServiceException ase) {
                System.out.println("Caught Exception: " + ase.getMessage());
                System.out.println("Reponse Status Code: " +
                        ase.getStatusCode());
                System.out.println("Error Code: " + ase.getErrorCode());
                System.out.println("Request ID: " + ase.getRequestId());
                return false;
            }
            try {
                Thread.sleep(2000);
            } catch (java.lang.InterruptedException e) {
                System.out.println(e);
            }

            if (((System.currentTimeMillis() - begin) / 1000) > timeout) {
                return false;
            }
            System.out.print(".");
        }

        System.out.println("\n* ec2 platform is running, propagating time:" +
                ((System.currentTimeMillis() - begin) / 1000) + "s"
        );
        return true;
    }


    private boolean waitKevoreeRunning(int timeout) {
        System.out.println("* ec2 waitKevoreeRunning(): " +
                "http://" + ip + ":8000"
        );
        long begin = System.currentTimeMillis();
        while (true) {
            try {
                URL url = new URL("http://" + ip + ":8000");
                System.out.print(".");
                URLConnection conn = url.openConnection();
                conn.setConnectTimeout(500);
                conn.connect();

                break;

            } catch (java.io.IOException ioe) {
            }
            try {
                Thread.sleep(500);
            } catch (java.lang.InterruptedException e) {
            }

            if (((System.currentTimeMillis() - begin) / 1000) > timeout) {
                return false;
            }
        }
        System.out.println("\n* ec2 platform is running");
        return true;
    }

    // Ec2id is stored with setName on physical Node
    // Ec2id contains "-" character which is not
    // accepted in the kevoree naming convention so
    // we have to convert it
    // TODO use a dedicated property to store the unmodified Ec2Id

    private String encodeEc2Id(String id) {
        String encodedId = new String();
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == '-') {
                encodedId = encodedId + "_";
            } else {
                encodedId = encodedId + id.charAt(i);
            }

        }
        return encodedId;
    }

    private String decodeEc2Id(String id) {
        String decodedId = new String();
        for (int i = 0; i < id.length(); i++) {
            if (id.charAt(i) == '_') {
                decodedId = decodedId + "-";
            } else {
                decodedId = decodedId + id.charAt(i);
            }

        }
        return decodedId;
    }

    private void getIpfromInstanceId() {
        try {
            DescribeInstancesRequest request = new DescribeInstancesRequest();
            Collection<String> instanceIds = new LinkedList();
            instanceIds.add(ec2InstanceId);
            request.setInstanceIds(instanceIds);

            DescribeInstancesResult result = ec2.describeInstances(request);
            List<Reservation> reservations = result.getReservations();
            for (Reservation reservation : reservations) {
                for (Instance instance : reservation.getInstances()) {
                    ip = instance.getPublicIpAddress();
                    System.out.println("* platform ip: " + ip);

                    break;
                }
            }
        } catch (AmazonServiceException ase) {
            System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " +
                    ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());

        }
    }

    private boolean sendModel(ContainerRoot model) throws java.io.IOException {

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        KevoreeXmiHelper.instance$.saveStream(outStream, model);
        outStream.flush();
        if (ip == null || ip.length() == 0) {
            System.out.println("* ec2 ip must not be null");
            return false;
        }
        URL url = new URL("http://" + ip + ":8000/model/current");
        System.out.println("* send model to " + url);

        URLConnection conn = url.openConnection();
        conn.setConnectTimeout(2000);
        conn.setDoOutput(true);
        conn.setDoInput(true);
        OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
        wr.write(outStream.toString());
        wr.flush();

        // Get the response
        BufferedReader rd =
                new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line = rd.readLine();
        while (line != null) {
            System.out.println("* " + line);
            line = rd.readLine();
        }
        wr.close();
        rd.close();
        return true;
    }
}