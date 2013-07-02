package org.kevoree.library.ec2;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.PropertiesCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import org.kevoree.annotation.*;
import org.kevoree.library.defaultNodeTypes.JavaSENode;
import org.kevoree.library.sky.api.CloudNode;
import org.kevoree.library.sky.api.IaaSNode;
import org.kevoree.library.sky.api.KevoreeNodeRunnerFactory;
import org.kevoree.library.sky.api.execution.CommandMapper;
import org.kevoree.library.sky.api.execution.KevoreeNodeManager;
import org.kevoree.library.sky.api.execution.KevoreeNodeRunner;
import org.kevoree.library.sky.api.planning.PlanningManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;
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
        @DictionaryAttribute(name = "keyPairPath", defaultValue = "/TamLN-INRIA/AmazonEC2/seckey/ubuntu.pem", optional = false),
        @DictionaryAttribute(name = "securityGroup", defaultValue = "quicklaunch-1", optional = false),
        @DictionaryAttribute(name = "userName", defaultValue = "ubuntu", optional = false)

})
@PrimitiveCommands(value = {
        @PrimitiveCommand(name = CloudNode.ADD_NODE, maxTime = 180000),      // set timeout for adding a node in 3 minutes
        @PrimitiveCommand(name = CloudNode.REMOVE_NODE, maxTime = 60000)     // set timeout for removing a node in 1 minutes
})
public class Ec2Node extends JavaSENode implements IaaSNode {
    private static final Logger logger = LoggerFactory.getLogger(Ec2Node.class);

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

    private KevoreeNodeManager nodeManager;


    @Start
    @Override
    public void startNode() {
        nodeManager = new KevoreeNodeManager(new EC2NodeRunnerFactory());
        kompareBean = new PlanningManager(this);
        mapper = new CommandMapper(nodeManager);
        mapper.setNodeType(this);
        ec2Auth();
    }

    @Stop
    @Override
    public void stopNode () {
        logger.debug("stopping node type of {}", this.getNodeName());
        nodeManager.stop();
        super.stopNode();
    }

	public class EC2NodeRunnerFactory implements KevoreeNodeRunnerFactory {
       @Override
		public KevoreeNodeRunner createKevoreeNodeRunner(String nodeName) {
		    return new EC2KevoreeNodeRunner(nodeName, Ec2Node.this, ec2, id2ip, id2dns);
		}
    }

}
