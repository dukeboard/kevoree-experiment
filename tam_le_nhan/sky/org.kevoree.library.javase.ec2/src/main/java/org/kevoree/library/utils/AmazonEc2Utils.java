package org.kevoree.library.utils;

/**
 * Created with IntelliJ IDEA.
 * User: tamln
 * Date: 6/5/13
 * Time: 6:49 PM
 * To change this template use File | Settings | File Templates.
 */
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.ClasspathPropertiesFileCredentialsProvider;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeAvailabilityZonesResult;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.InstanceStateChange;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesResult;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesResult;
import com.amazonaws.util.StringUtils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class AmazonEc2Utils {
    /*
     * Important: Be sure to fill in your AWS access credentials in the
     *            AwsCredentials.properties file before you try to run this
     *            sample.
     * http://aws.amazon.com/security-credentials
     */
    static final int WAIT_FOR_TRANSITION_INTERVAL = 5000;

    private AmazonEC2 init(AmazonEC2 ec2) throws Exception {
        /*
           * This credentials provider implementation loads your AWS credentials
           * from a properties file at the root of your classpath.
           */
        AWSCredentialsProvider credentialsProvider = new ClasspathPropertiesFileCredentialsProvider();

        ec2 = new AmazonEC2Client(credentialsProvider);
        return ec2;
    }

//    Discovery the resources on EC2 account
    public void discoEC2(AmazonEC2 ec2){
        /*
         * Amazon EC2
         *
         * The AWS EC2 client allows you to create, delete, and administer
         * instances programmatically.
         *
         * In this sample, we use an EC2 client to get a list of all the
         * availability zones, and all instances sorted by reservation id.
         */
        try {
            DescribeAvailabilityZonesResult availabilityZonesResult = ec2.describeAvailabilityZones();
            System.out.println("You have access to " + availabilityZonesResult.getAvailabilityZones().size() +
                    " Availability Zones.");

            DescribeInstancesResult describeInstancesRequest = ec2.describeInstances();

            List<Reservation> reservations = describeInstancesRequest.getReservations();
            Set<Instance> instances = new HashSet<Instance>();

            for (Reservation reservation : reservations) {

                instances.addAll(reservation.getInstances());
            }

            System.out.println("You have " + instances.size() + " Amazon EC2 instance(s) running.");

            Iterator iterRes = instances.iterator();
            while (iterRes.hasNext()){
                Instance tmpInstance = (Instance)iterRes.next();

                System.out.println("Image ID : "+tmpInstance.getImageId());
                System.out.println("Instance : "+tmpInstance.getInstanceId());
                System.out.println("Public DSN: "+tmpInstance.getPublicDnsName()+" ; PublicIPAddress: "+tmpInstance.getPublicIpAddress());

            }

        } catch (AmazonServiceException ase) {
            System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
        }

    }

    // Start an Amazon EC2 Instance
    private String startInstance(AmazonEC2 ec2, String instanceId)
            throws AmazonServiceException, AmazonClientException, InterruptedException
    {
        // Stop the instance
        StartInstancesRequest startRequest = new StartInstancesRequest().withInstanceIds(instanceId);
        StartInstancesResult startResult = ec2.startInstances(startRequest);
        List<InstanceStateChange> stateChangeList = startResult.getStartingInstances();
        //buildLogger.addBuildLogEntry("Starting instance '" + instanceId + "':");
        System.out.println("Starting instance '" + instanceId + "':");
        // Wait for the instance to be stopped
        return waitForTransitionCompletion(stateChangeList, "running", ec2, instanceId);
    }

    // Stop a running Amazon EC2 Instance

    private String stopInstance(final String instanceId, final Boolean forceStop, AmazonEC2 ec2) throws AmazonServiceException, AmazonClientException, InterruptedException
    {
        // Stop the instance
        StopInstancesRequest stopRequest = new StopInstancesRequest().withInstanceIds(instanceId).withForce(forceStop);
        StopInstancesResult startResult = ec2.stopInstances(stopRequest);
        List<InstanceStateChange> stateChangeList = startResult.getStoppingInstances();
        //buildLogger.addBuildLogEntry("Stopping instance '" + instanceId + "':");
        System.out.println("Stopping instance '" + instanceId + "':");
        // Wait for the instance to be stopped
        return waitForTransitionCompletion(stateChangeList, "stopped", ec2, instanceId);
    }

    // Return an Instance with the describe information
    public Instance describeInstance(AmazonEC2 ec2, String instanceId)
            throws AmazonServiceException, AmazonClientException
    {
        DescribeInstancesRequest describeRequest = new DescribeInstancesRequest().withInstanceIds(instanceId);
        DescribeInstancesResult result = ec2.describeInstances(describeRequest);

        for (Reservation reservation : result.getReservations())
        {
            for (Instance instance : reservation.getInstances())
            {
                if (instanceId.equals(instance.getInstanceId()))
                {
                    return instance;
                }
            }
        }
        return null;
    }

    // Waiting for the transition of an Instance is complete
    public final String waitForTransitionCompletion(List<InstanceStateChange> stateChangeList,
                                                    final String desiredState, AmazonEC2 ec2, String instanceId)
            throws InterruptedException
    {
        Boolean transitionCompleted = false;
        InstanceStateChange stateChange = stateChangeList.get(0);
        String previousState = stateChange.getPreviousState().getName();
        String currentState = stateChange.getCurrentState().getName();
        String transitionReason = "";

        while (!transitionCompleted)
        {
            try
            {
                Instance instance = describeInstance(ec2, instanceId);
                currentState = instance.getState().getName();
                if (previousState.equals(currentState))
                {
                    //buildLogger.addBuildLogEntry("... '" + instanceId + "' is still in state " + currentState + " ...");
                    System.out.println("... '" + instanceId + "' is still in state " + currentState + " ...");
                }
                else
                {
                    //buildLogger.addBuildLogEntry("... '" + instanceId + "' entered state " + currentState + " ...");
                    System.out.println("... '" + instanceId + "' entered state " + currentState + " ...");
                    transitionReason = instance.getStateTransitionReason();
                }
                previousState = currentState;

                if (currentState.equals(desiredState))
                {
                    transitionCompleted = true;
                }
            }
            catch (AmazonServiceException ase)
            {
                //buildLogger.addErrorLogEntry("Failed to describe instance '" + instanceId + "'!", ase);
                System.out.println("Failed to describe instance '" + instanceId + "'! "+ ase.toString());
                throw ase;
            }

            // Sleep for WAIT_FOR_TRANSITION_INTERVAL seconds until transition has completed.
            if (!transitionCompleted)
            {
                Thread.sleep(WAIT_FOR_TRANSITION_INTERVAL);
            }
        }

/*        buildLogger.addBuildLogEntry("Transition of instance '" + instanceId + "' completed with state " + currentState
                + " (" + (StringUtils.isEmpty(transitionReason) ? "Unknown transition reason" : transitionReason)
                + ").");
*/
        System.out.println("Transition of instance '" + instanceId + "' completed with state " + currentState);

        return currentState;
    }


}
