package org.kevoree.library.ec2;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;
import org.kevoree.ContainerNode;
import org.kevoree.ContainerRoot;
import org.kevoree.annotation.*;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.cloner.ModelCloner;
import org.kevoree.framework.KevoreePropertyHelper;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.library.sky.api.KevoreeNodeRunner;
import org.kevoree.library.sky.api.nodeType.AbstractIaaSNode;
import org.kevoree.framework.Constants;

import org.kevoree.library.utils.AmazonEc2Utils;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
//import org.apache.log4j.BasicConfigurator;
//import org.apache.log4j.Logger;

// java -Dnode.name="local" -Dnode.port=8000 -jar org.kevoree.platform.osgi.standalone-1.2.0-20110708.152210-2.jar
// sudo route add -net 192.168.100.0/24 gw 131.254.10.209 dev p255p1
// /home/armel/work/m1/stage/kevoree/kevoree-library/javase/org.kevoree.library.javase.ec2/AwsCredentials.properties
// https://192.168.100.162:8444
// debian.img

@NodeType
@Library(name = "EC2")
@DictionaryType({
        @DictionaryAttribute(name = "endPointUrl", /*defaultValue = "https://192.168.100.162:8444", */optional = false),
        @DictionaryAttribute(name = "AWSCredentials", /*defaultValue = "AwsCredentials.properties",*/optional = false),
        @DictionaryAttribute(name = "imageID", defaultValue = "debian.img", optional = false),
        @DictionaryAttribute(name = "instanceName", defaultValue = "Ubuntu", optional = false),
        @DictionaryAttribute(name = "instanceType", defaultValue = "m1.small", optional = false),
        @DictionaryAttribute(name = "keyPairName", defaultValue = "ubuntu", optional = false),
        @DictionaryAttribute(name = "securityGroup", defaultValue = "quicklaunch-1", optional = false)

})
public class Ec2Node extends AbstractIaaSNode {

    private AmazonEC2 ec2 = null;
    // Mapping InstanceID and it PublicIPAddress
    private Map<String,String> id2ip = new TreeMap<String,String>();
    private Map<String,String> id2dns = new TreeMap<String,String>();

    private boolean ec2Auth() {
        System.out.println("* ec2 ec2Auth()");
        try {
            String filePath =
                    this.getDictionary().get("AWSCredentials").toString();
            String endPointUrl =
                    this.getDictionary().get("endPointUrl").toString();
            if (filePath != null && endPointUrl != null) {
                File f = new File(filePath);
                if (f.exists()) {
                    PropertiesCredentials credentials =
                            new PropertiesCredentials(f);
                    ClientConfiguration config = new ClientConfiguration();
                    config.setProtocol(Protocol.HTTPS);
                    ec2 = new AmazonEC2Client(credentials, config);
                    ec2.setEndpoint(endPointUrl);//"https://smith:8444"
                    System.out.println("* EC2 Authenticate is valid");
                    return true;
                } else {
                    System.err.println("file: " + filePath + " does not exist");
                    return false;
                }
            } else {
                System.err.println(
                        "You must define the Credential file" +
                                " and the EndPoint Url in your env " +
                                "com.amazonaws.auth.AWSCredentials " +
                                "com.amazonaws.auth.endPointUrl"
                );
                return false;
            }

        } catch (java.io.IOException ioe) {
            System.out.println("io Error" + ioe);
            return false;
        }

    }

    @Start
    public void startNode() {
        super.startNode();
        ec2Auth();
    }


    @Override
    public KevoreeNodeRunner createKevoreeNodeRunner(String nodeName) {
        return new EC2KevoreeNodeRunner(nodeName, this);
    }

    class EC2KevoreeNodeRunner extends KevoreeNodeRunner {

        private Ec2Node iaasNode;
        private String ip;
        private String ec2InstanceId;
        private Reservation reservation;

        public EC2KevoreeNodeRunner(String nodeName, Ec2Node iaasNode) {
            super(nodeName);
            this.iaasNode = iaasNode;
        }

        @Override
        public boolean startNode(ContainerRoot iaasModel, ContainerRoot childBootStrapModel) {
//            try {
            //boolean created = ec2Create(iaasModel);
            System.out.println("--> Kevoree Node is starting ...");
            argsCatching();
//            try {
//                String ipNode = runEc2Instance(iaasModel);
//                if (ipNode != null){
//                    System.out.println("--> Public IP Address = " + ipNode);
//                    return true;
//                }
//                else return false;
//            } catch (Exception e) {
//                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
//                return false;
//
//            }
            //System.out.println(waitForTransitionCompletion());
            //return created;
            /*} catch (java.io.IOException e) {
                System.out.println("IO ERROR: " + e);
                e.printStackTrace();
                return false;
            }*/
            return true;
        }

        @Override
        public boolean stopNode() {
            // TODO stop and remove the VM on AMazaon EC2
            return false;
            //boolean stopInstance = terminateEc2InstanceByID()
        }

        // Test arguments catching from DictionaryAttributes at the starting
        public void argsCatching(){
           System.out.println("endPointUrl"+this.iaasNode.getDictionary().get("endPointUrl").toString());
           System.out.println("imageID ="+this.iaasNode.getDictionary().get("imageID").toString());
           System.out.println("keyPairName = "+this.iaasNode.getDictionary().get("keyPairName").toString());

        }


        public String runEc2Instance(ContainerRoot iaasModel) throws Exception {
            //NOTE: ask to use log of Kevoree
            //logger.log(Level.INFO,"Running new instance..");
            System.out.println("--> Running new instance ...");
            String imageID = this.iaasNode.getDictionary().get("imageID").toString();
            String keyName = this.iaasNode.getDictionary().get("keyPairName").toString();
            String secGroup = this.iaasNode.getDictionary().get("securityGroup").toString();
            String instanceType = this.iaasNode.getDictionary().get("instanceType").toString();
            ContainerNode node = iaasModel.findNodesByID(nodeName())        ;
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
                    Thread.sleep(2000);

                    DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest();
                    describeInstancesRequest.withInstanceIds(instanceId);
                    DescribeInstancesResult describeInstancesResult = ec2.describeInstances(describeInstancesRequest);

                    Instance instance = describeInstancesResult.getReservations().get(0).getInstances().get(0);
                    //logger.log(Level.INFO,"Current instance state: " + instance.getState().getName() + "..");
                    System.out.println("--> Current instance state: " + instance.getState().getName() + "..");
                    if (instance.getState().getName().equals("running")) {
                        instanceIp = instance.getPublicIpAddress();
                        dnsName = instance.getPublicDnsName();
                        id2ip.put(instanceId,instanceIp);
                        id2dns.put(instanceId,dnsName);
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
            kengine.addVariable("nodeName", nodeName());

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

                    }
                        iaasNode.getModelService().atomicUpdateModel(model);
                }
            }                                                              .start();
            return instanceIp;
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
        private boolean terminateEc2InstanceByID(String instanceId){
            try{
                //logger.log(Level.INFO,"Terminating instance with IP: " + instanceIp + "..");
                System.out.println("Terminating instance with InstanceID: " + instanceId + "..");
                //String instanceId = id2ip.get(instanceId);

                TerminateInstancesRequest terminateInstancesRequest = new TerminateInstancesRequest();
                terminateInstancesRequest.withInstanceIds(instanceId);
                ec2.terminateInstances(terminateInstancesRequest);
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

        private boolean ec2Launch(ContainerRoot iaasModel) {

            System.out.println("--> EC2 Instance is launching ...");
            if (ec2 != null) {
                ContainerNode node = iaasModel.findNodesByID(nodeName())        ;
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
                ContainerNode node = iaasModel.findNodesByID(nodeName())        ;
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

}
