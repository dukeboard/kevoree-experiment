package org.kevoree.library.ec2;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.*;
import org.kevoree.ContainerRoot;
import org.kevoree.annotation.*;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.library.sky.api.KevoreeNodeRunner;
import org.kevoree.library.sky.api.nodeType.AbstractIaaSNode;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
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
        @DictionaryAttribute(name = "imgName", defaultValue = "debian.img", optional = false)

})
public class Ec2Node extends AbstractIaaSNode {

    private AmazonEC2 ec2 = null;

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
                    System.out.println("* ec2 Authentified");
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

        public EC2KevoreeNodeRunner(String nodeName, Ec2Node iaasNode) {
            super(nodeName);
            this.iaasNode = iaasNode;
        }

        @Override
        public boolean startNode(ContainerRoot iaasModel, ContainerRoot childBootStrapModel) {
//            try {
            boolean created = ec2Create();
            if (!waitRunning(600) || !waitKevoreeRunning(30)) {
                System.err.println("ERROR: running timeout expirated");
                return false;
            } else return created; //&& sendModel(childBootStrapModel);


            /*} catch (java.io.IOException e) {
                System.out.println("IO ERROR: " + e);
                e.printStackTrace();
                return false;
            }*/
        }

        @Override
        public boolean stopNode() {
            // TODO stop and remove the VM on AMazaon EC2
            return false;
        }


        private boolean ec2Create() {
            // TODO add ssh key to be able to connect to the VM
            // How to define key pair ?
            if (ec2 != null) {
                System.out.println("* ec2 ec2Create()");
                String instanceType = null;
                String imageName = this.iaasNode.getDictionary().get("imgName").toString();
                int minCount = 1;
                int maxCount = 1;
                RunInstancesRequest request = new RunInstancesRequest();
                if (imageName != null) request.setImageId(imageName);
                if (instanceType != null) request.setInstanceType(instanceType);
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
