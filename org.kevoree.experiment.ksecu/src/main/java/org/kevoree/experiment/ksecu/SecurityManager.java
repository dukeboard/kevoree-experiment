package org.kevoree.experiment.ksecu;

import org.kevoree.AdaptationPrimitiveType;
import org.kevoree.ComponentInstance;
import org.kevoree.ContainerRoot;
import org.kevoree.DeployUnit;
import org.kevoree.impl.ComponentTypeImpl;
import org.kevoree.kompare.KevoreeKompareBean;
import org.kevoreeAdaptation.AdaptationModel;
import org.kevoreeAdaptation.AdaptationPrimitive;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 14/01/13
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
public class SecurityManager {

    private KeyStore keyStore;

    private HashMap<String,Permissions> cluster_permissions;

    public SecurityManager(){
        keyStore = new KeyStore();
        cluster_permissions =    new HashMap<String, Permissions>();
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public Permissions getPermissions(String nodename) {
        if(!cluster_permissions.containsKey(nodename)){
            cluster_permissions.put(nodename,  new  Permissions());
        }
        return cluster_permissions.get(nodename);
    }


    public List<AdaptationPrimitive> verify(String nodeName,ContainerRoot current_model,ContainerRoot target_model )
    {
        List<AdaptationPrimitive> result = new ArrayList<AdaptationPrimitive>();
        KevoreeKompareBean kompareBean = new KevoreeKompareBean();
        AdaptationModel adaptationModel = kompareBean.kompare(current_model, target_model, nodeName);
        for(AdaptationPrimitive p : adaptationModel.getAdaptationsForJ())
        {

            if(p.getRef() instanceof ComponentInstance || p.getRef() instanceof ComponentInstance ){

                ComponentInstance instance =(ComponentInstance) p.getRef();

                List<String>  operation = cluster_permissions.get(nodeName).getPermissions(instance.getTypeDefinition());


                if(!operation.contains(p.getPrimitiveType().getName())){

                    if(!result.contains(p)){
                        result.add(p);
                    }
                }


            }else if( p.getRef() instanceof DeployUnit)
            {
                DeployUnit d = (DeployUnit)p.getRef();


            } else {
                //System.err.println("todo type "+p.getRef());
            }


        }
        return result;

    }
}
