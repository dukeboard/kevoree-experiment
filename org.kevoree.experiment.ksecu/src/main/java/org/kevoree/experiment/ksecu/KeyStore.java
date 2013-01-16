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




}
