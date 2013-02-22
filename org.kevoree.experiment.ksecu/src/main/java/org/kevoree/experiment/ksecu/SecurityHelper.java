package org.kevoree.experiment.ksecu;

import org.kevoree.ContainerRoot;
import org.kevoree.KSecurityModel.*;

import org.kevoree.framework.KevoreeXmiHelper;

import java.security.*;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 16/01/13
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class SecurityHelper {


    public static byte[] getSignature(PrivateKey privateKey,byte []model) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initSign(privateKey);
        sig.update(model);
        return sig.sign();

    }


    public static boolean verifySignature(byte[]signatureBytes ,PublicKey key,byte []model) throws InvalidKeyException, NoSuchAlgorithmException, SignatureException {
        Signature sig = Signature.getInstance("SHA1withRSA");
        sig.initVerify(key);
        sig.update(model);
        if(sig.verify(signatureBytes)){
            return  true;
        }
        return false;
    }


    public static KeyPair generateKeys(int size) throws NoSuchAlgorithmException{
        // Generate a key
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(size);
        KeyPair kp = kpg.genKeyPair();
        return kp;
    }



    public static  KSecurityRoot filter_rules(SignedModel model, KSecurityRoot security_model) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        KSecurityRoot filter = KSecurityModelFactory.createKSecurityRoot();
        filter.addAllNo_authorized(security_model.getNo_authorized());

        for(KSecurityRule r : security_model.getAuthorizedForJ())
        {
            for(KPublicKey k :r.getAllowedForJ())
            {
                boolean  found = false;
                for(KSignature sign :model.getSignedForJ())
                {
                    if(SecurityHelper.verifySignature(sign.getKey(),(PublicKey)k.getKey(),model.getModel())){
                        found =true;
                    }
                }

                if(found){
                    filter.addAuthorized(r);
                }  else {
                    filter.addNo_authorized(r);
                }
            }

        }

        return filter;

    }

    public static SignedModel createSignedModel(ContainerRoot model, List<PrivateKey> signatures) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        byte []  currentmodel = KevoreeXmiHelper.saveToString(model,true).getBytes();
        SignedModel deploy = KSecurityModelFactory.createSignedModel();
        deploy.setModel( currentmodel);
        for(PrivateKey key : signatures)
        {
            KSignature s = KSecurityModelFactory.createKSignature();
            s.setKey(SecurityHelper.getSignature(key,currentmodel));
            deploy.addSigned(s);
        }
        return deploy;

    }
}
