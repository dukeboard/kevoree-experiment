package org.kevoree.experiment.ksecu;

import org.kevoree.ContainerRoot;
import org.kevoree.framework.KevoreeXmiHelper;
import java.io.*;
import java.math.BigInteger;
import java.security.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.KeyPair;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 08/01/13
 * Time: 08:54
 * To change this template use File | Settings | File Templates.
 */
public class Tester {



    public static void main(String argv[]) throws NoSuchPaddingException, NoSuchAlgorithmException, NoSuchProviderException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, IOException, SignatureException, InvalidKeySpecException {

         // Poc

        // create a keyStore
        KeyStore keyStore = new KeyStore();
        KeyPair kp = keyStore.generateKeys(512);

        KeyPair kp2 = keyStore.generateKeys(512);


        //Adding permission  on Operation
        Permission permissions = new Permission();

        permissions.accept(Operation.UpdateFragmentBinding);
        permissions.accept(Operation.AddBinding);
        permissions.accept(Operation.RemoveBinding);
        permissions.accept(Operation.AddInstance);
        permissions.accept(Operation.RemoveInstance);
        permissions.accept(Operation.UpdateDictionaryInstance);
        permissions.accept(Operation.UpdateBinding);
        permissions.accept(Operation.RemoveThirdParty);
        permissions.accept(Operation.RemoveNode);
        permissions.accept(Operation.RemoveType);
        permissions.accept(Operation.StartInstance);
        permissions.accept(Operation.StopInstance);
        permissions.accept(Operation.UpdateType);
        permissions.accept(Operation.AddFragmentBinding);
        permissions.accept(Operation.StartThirdParty);
        permissions.accept(Operation.AddNode);
        permissions.accept(Operation.UpdateDeployUnit);
        permissions.accept(Operation.UpdateInstance);
        permissions.accept(Operation.RemoveFragmentBinding);
        permissions.accept(Operation.RemoveDeployUnit);
        permissions.accept(Operation.AddDeployUnit);
        permissions.accept(Operation.AddThirdParty);
        permissions.accept(Operation.AddType);


        // accept type component instnace





        //Associate a key with permissions
        keyStore.addKey(kp.getPublic(),permissions);

        // read model and sign
        byte[] model = load_file(Tester.class.getClassLoader().getResourceAsStream("simple_fake_console.kev"));


        // get signature
        byte[] signatureBytes = keyStore.getSignature(kp.getPrivate(),model);


        ContainerRoot current_model = KevoreeXmiHelper.loadString(new String(model));


        // verify signature
        if(keyStore.verifySignature(signatureBytes,model)){
            // verify permissions
            if(keyStore.verifyPermission(current_model,permissions)){
                System.out.println("Adapation accepté");
            }   else {
                System.out.println("Adapation refusé");
            }
        }else {
            System.err.println("check of the signature fail");

        }



        System.exit(0);




    }


    public static void saveToFile(String fileName,  BigInteger mod, BigInteger exp) throws IOException {
        ObjectOutputStream oout = new ObjectOutputStream( new BufferedOutputStream(new FileOutputStream(fileName)));
        try {
            oout.writeObject(mod);
            oout.writeObject(exp);
        } catch (Exception e) {
            throw new IOException("Unexpected error", e);
        } finally {
            oout.close();
        }
    }


    public static byte[] toByteArray(List<Byte> in) {
        final int n = in.size();
        byte ret[] = new byte[n];
        for (int i = 0; i < n; i++) {
            ret[i] = in.get(i);
        }
        return ret;
    }


    public static byte[] load_file(InputStream reader) throws IOException {
        int c;
        ArrayList<Byte> tab = new ArrayList<Byte>();
        while((c = reader.read()) != -1) {
            tab.add((byte)c);
        }
        if (reader!=null)
            reader.close();

        return toByteArray(tab);
    }
}
