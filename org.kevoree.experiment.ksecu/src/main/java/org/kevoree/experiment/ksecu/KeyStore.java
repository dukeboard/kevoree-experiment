package org.kevoree.experiment.ksecu;

import org.kevoree.ContainerRoot;

import java.security.*;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 09/01/13
 * Time: 11:02
 * To change this template use File | Settings | File Templates.
 */


public class KeyStore  implements IKeyStore
{
    private HashMap<PublicKey, Permission> keys =new HashMap<PublicKey, Permission>();

    public HashMap<PublicKey, Permission> getKeys() {
        return keys;
    }

    public void addKey(PublicKey key,Permission o){
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

    public boolean verifyPermission(ContainerRoot model,Permission permission){
        if(permission.validateOperations(model.getAdaptationPrimitiveTypesForJ())){
          return  true;
        } else {
         return  false;
        }

    }
}
