package org.kevoree.library.utils;


/**
 * Created with IntelliJ IDEA.
 * User: tamln
 * Date: 6/18/13
 * Time: 6:41 PM
 * To change this template use File | Settings | File Templates.
 */


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class SSHUtils {

    public static void scp(String userName, String keyPath, String sourcePath, String targetHostName, String targetFileName)
            throws IOException {
        try {
            File keyFile = new File(keyPath);
            JSch jsch = new JSch();
            jsch.addIdentity(keyFile.getAbsolutePath());

            Session session = jsch.getSession(userName, targetHostName, 22);
            UserInfo ui = new SshUser();
            session.setUserInfo(ui);
            session.connect();
            if (session.isConnected()) System.out.println("Connected to the remote host: "+targetHostName);
            else  System.out.println("Cannot connect to the remote host: "+targetHostName);
            String command = "scp -p -t  " + targetFileName;
            Channel channel = session.openChannel("exec");
            ((ChannelExec) channel).setCommand(command);

            // get I/O streams for remote scp
            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();

            channel.connect();

            if (checkAck(in) != 0) {
                throw new RuntimeException("Failed to scp key file");
            }

            // send "C0644 filesize filename", where filename should not include
            // '/'
            long filesize = (new File(sourcePath)).length();
            command = "C0644 " + filesize + " ";
            if (sourcePath.lastIndexOf('/') > 0) {
                command += sourcePath.substring(sourcePath.lastIndexOf('/') + 1);
            } else {
                command += sourcePath;
            }
            command += "\n";
            out.write(command.getBytes());
            out.flush();
            if (checkAck(in) != 0) {
                // this actually will never happend since checkAck already
                // throws an exception now.
                throw new RuntimeException("Fatal Error scp key file");
            }
            // send a content of lfile
            FileInputStream fis = new FileInputStream(sourcePath);
            byte[] buf = new byte[1024];
            while (true) {
                int len = fis.read(buf, 0, buf.length);
                if (len <= 0)
                    break;
                out.write(buf, 0, len); // out.flush();
            }
            fis.close();
            fis = null;
            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            if (checkAck(in) != 0) {
                throw new RuntimeException("Failed to scp  file");
            }
            out.close();
            channel.disconnect();
            session.disconnect();
        } catch (JSchException e) {
            throw new IOException("Unable to copy key file to host: ", e);
        }
    }


    // create a ssh channel to a remote host
    // e.g. createSSHChannel("root", "/AmazonEC2/ubuntu.pem","ec2-174-129-79-176.compute-1.amazonaws.com");
    public static Session createSSHSession(String userName, String keyPath, String remoteHostName) throws IOException{
        try{
            File keyFile = new File(keyPath);
            JSch jsch = new JSch();
            jsch.addIdentity(keyFile.getAbsolutePath());
            Session session = jsch.getSession(userName, remoteHostName, 22);
            UserInfo ui = new SshUser();
            session.setUserInfo(ui);
            session.connect();
            System.out.println("Connected to the remote host: "+remoteHostName);
//            Channel channel = session.openChannel("exec");
//            System.out.print("A channel is created");

            //channel.get
            return session;
        }   catch (JSchException e){
            throw new IOException("Unable to create a session connection to host: ", e);
        }
    }

    // close a channel
    public static boolean closeChannel(Channel channel) throws JSchException {
        Session session = channel.getSession();
        channel.disconnect();
        if (channel.isClosed()) {
            System.out.println("Channel is closed");
            session.disconnect();
            if (!session.isConnected()){
                System.out.println("Session is closed");
                return true;
            }else {
                System.out.println("Unable to close the session");
                return false;
            }
        }else {
            System.out.println("Unable to close the channel");
            return false;
        }
    }
    // Using SCP to transfer a file to remote host by a connected channel
    public static void scpByChannel(Session session, String sourcePath, String targetFileName)throws IOException {
        try {
            Channel channel = session.openChannel("exec");
            System.out.println("A channel is created");
            String command = "scp -p -t  " + targetFileName;
            ((ChannelExec) channel).setCommand(command);

            // get I/O streams for remote scp
            OutputStream out = channel.getOutputStream();
            InputStream in = channel.getInputStream();

            channel.connect();

            if (checkAck(in) != 0) {
                throw new RuntimeException("Failed to scp key file");
            }

            // send "C0644 filesize filename", where filename should not include
            // '/'
            long filesize = (new File(sourcePath)).length();
            command = "C0644 " + filesize + " ";
            if (sourcePath.lastIndexOf('/') > 0) {
                command += sourcePath.substring(sourcePath.lastIndexOf('/') + 1);
            } else {
                command += sourcePath;
            }
            command += "\n";
            out.write(command.getBytes());
            out.flush();
            if (checkAck(in) != 0) {
                // this actually will never happend since checkAck already
                // throws an exception now.
                throw new RuntimeException("Fatal Error scp key file");
            }
            // send a content of lfile
            FileInputStream fis = new FileInputStream(sourcePath);
            byte[] buf = new byte[1024];
            while (true) {
                int len = fis.read(buf, 0, buf.length);
                if (len <= 0)
                    break;
                out.write(buf, 0, len); // out.flush();
            }
            fis.close();
            fis = null;
            // send '\0'
            buf[0] = 0;
            out.write(buf, 0, 1);
            out.flush();
            if (checkAck(in) != 0) {
                throw new RuntimeException("Failed to scp  file");
            }
            out.close();
            channel.disconnect();
            //session.disconnect();
        } catch (JSchException e) {
            throw new IOException("Unable to copy file to host: ", e);
        }
    }

    // executes a command at the SSH remote host with the creation
    // of a new connection to the remote host
    public static boolean sshRemoteCommand(String userName, String keyPath, String dnsName, String command) throws IOException {
        try {
            File file = new File(keyPath);
            JSch jsch = new JSch();
            jsch.addIdentity(file.getAbsolutePath());
            Session session = jsch.getSession(userName, dnsName, 22);
            UserInfo ui = new SshUser();
            session.setUserInfo(ui);
            session.connect();

            Channel channel = session.openChannel("exec");
            System.out.println(" ... Executing command: "+command);
            ((ChannelExec) channel).setCommand(command);
            ((ChannelExec) channel).setErrStream(System.out);
            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    // Show the output
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("channel exit-status: "+channel.getExitStatus());
                    if (channel.getExitStatus() != 0) {
                        return false;
                    }
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ee) {
                    Thread.currentThread().interrupt();
                }
            }
            channel.disconnect();
            session.disconnect();
        } catch (JSchException e) {
            return false;
            // throw new IOException("Unable to ssh into master", e);
        }
        return true;
    }


    // execute a command at the SSH remote host by a created ssh channel
    public static boolean sshRemoteCommand(Session session, String command) throws IOException {
        try {
            Channel channel = session.openChannel("exec");
            System.out.println("A channel is created");
            ((ChannelExec) channel).setCommand(command);
            ((ChannelExec) channel).setErrStream(System.out);
            InputStream in = channel.getInputStream();
            channel.connect();
            byte[] tmp = new byte[1024];
            while (true) {
                while (in.available() > 0) {
                    int i = in.read(tmp, 0, 1024);
                    if (i < 0)
                        break;
                    // show the output
                    System.out.print(new String(tmp, 0, i));
                }
                if (channel.isClosed()) {
                    System.out.println("channel exit-status: "+channel.getExitStatus());
                    if (channel.getExitStatus() != 0) {
                        return false;
                    }
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ee) {
                    Thread.currentThread().interrupt();
                }
            }
            channel.disconnect();
            //session.disconnect();
        } catch (JSchException e) {
            return false;
            // throw new IOException("Unable to ssh into master", e);
        }
        return true;
    }

    // get a keypair name from a keypair path
    public String getKeyPairName(String filePath){
        return filePath.substring(filePath.lastIndexOf('/')+1,filePath.lastIndexOf('.'));
    }

    static class SshUser implements UserInfo {

        @Override
        public String getPassphrase() {
            return "";
        }

        @Override
        public String getPassword() {
            return "";
        }

        @Override
        public boolean promptPassphrase(String arg0) {
            return true;
        }

        @Override
        public boolean promptPassword(String arg0) {
            return true;
        }

        @Override
        public boolean promptYesNo(String arg0) {
            return true;
        }

        @Override
        public void showMessage(String arg0) {
            System.out.println(arg0);
        }

    }

    /**
     * from the jsch examples
     */
    private static int checkAck(InputStream in) throws IOException {
        int b = in.read();
        // b may be 0 for success,
        // 1 for error,
        // 2 for fatal error,
        // -1
        if (b == 0)
            return b;
        if (b == -1)
            return b;

        if (b == 1 || b == 2) {
            StringBuffer sb = new StringBuffer();
            int c;
            do {
                c = in.read();
                sb.append((char) c);
            } while (c != '\n');
            if (b == 1) { // error
                System.err.println("SSHUtils.checkAck(): " + sb.toString());
                throw new IOException("Error scp file: " + sb.toString());
                // System.out.print(sb.toString());
            }
            if (b == 2) { // fatal error
                throw new IOException("Error scp key file: " + sb.toString());
                // System.out.print(sb.toString());
            }
        }
        return b;
    }

    // testing
    public static void main(String args[]) throws IOException, JSchException {
        String sourceFile ="/TamLN-INRIA/Kevoree/kevoree-experiment.git/tam_le_nhan/sky/org.kevoree.library.sshutils/shellscript.sh";
        String keyPath = "/Users/tamln/.ssh/id_rsa";
        String keyPath2 = "/TamLN-INRIA/AmazonEC2/seckey/ubuntu.pem";
        //scp("ubuntu",keyPath2,"/TamLN-INRIA/AmazonEC2/seckey/ubuntu.pem","ec2-23-20-221-41.compute-1.amazonaws.com","ubuntu.pem");

        //sshRemoteCommand("ubuntu",keyPath2,"ec2-23-20-221-41.compute-1.amazonaws.com","mkdir testDir");
        Session session = createSSHSession("ubuntu",keyPath2,"ec2-23-20-221-41.compute-1.amazonaws.com");
        sshRemoteCommand(session,"dpkg --get-selections >> installedList.txt");
        session.disconnect();




    }
}