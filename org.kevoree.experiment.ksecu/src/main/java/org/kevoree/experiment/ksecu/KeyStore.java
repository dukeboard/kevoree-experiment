package org.kevoree.experiment.ksecu;

import org.kevoree.ContainerRoot;
import org.kevoree.kompare.KevoreeKompareBean;
import org.kevoreeAdaptation.AdaptationModel;
import org.kevoreeAdaptation.AdaptationPrimitive;

import java.security.*;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 09/01/13
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */


public class KeyStore  implements IKeyStore
{
    private HashMap<PublicKey, Permissions> keys =new HashMap<PublicKey, Permissions>();

    public HashMap<PublicKey, Permissions> getKeys() {
        return keys;
    }

    public void addKey(PublicKey key,Permissions o){
        keys.put(key, o);
    }

    public void removeKey(PublicKey key){
        keys.remove(key);
    }


    public byte[] getSignature(PrivateKey privateKey,byte []model) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(privateKey);
        sig.update(model);
        return sig.sign();

    }


    public boolean verifySignature(byte[]signatureBytes ,byte []model) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        for(PublicKey key : getKeys().keySet())
        {
            sig.initVerify(key);
            sig.update(model);
            if(sig.verify(signatureBytes)){
              return  true;

            }

        }
        return false;

    }


    public KeyPair generateKeys(int size) throws NoSuchAlgorithmException{
        // Generate a key
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(size);
        KeyPair kp = kpg.genKeyPair();
        return kp;
    }


}
