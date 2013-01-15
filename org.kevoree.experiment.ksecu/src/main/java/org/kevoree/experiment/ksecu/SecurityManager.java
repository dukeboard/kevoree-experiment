package org.kevoree.experiment.ksecu;

import org.jboss.netty.channel.AbstractChannel;
import org.kevoree.AdaptationPrimitiveType;
import org.kevoree.ComponentInstance;
import org.kevoree.ContainerRoot;
import org.kevoree.DeployUnit;
import org.kevoree.KSecurityModel.KSecurityRoot;
import org.kevoree.KSecurityModel.SecurityRule;
import org.kevoree.impl.ComponentTypeImpl;
import org.kevoree.kompare.KevoreeKompareBean;
import org.kevoreeAdaptation.AdaptationModel;
import org.kevoreeAdaptation.AdaptationPrimitive;

import java.nio.channels.Channel;
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

    private KSecurityRoot security_model;

    public SecurityManager(){
        keyStore = new KeyStore();
    }

    public KeyStore getKeyStore() {
        return keyStore;
    }

    public void setSecurity_model(KSecurityRoot security_model)
    {
        this.security_model = security_model;
    }

    public List<AdaptationPrimitive> verify(String nodeName,ContainerRoot current_model,ContainerRoot target_model ) throws SecurityException {
        if(security_model == null)
        {
            throw new SecurityException("no security model");
        }

        List<org.kevoree.KSecurityModel.SecurityRule> authorize_rules =            security_model.getAuthorizedForJ();
        List<org.kevoree.KSecurityModel.SecurityRule> refused_rules =            security_model.getNo_authorizedForJ();

        List<AdaptationPrimitive> result = new ArrayList<AdaptationPrimitive>();
        KevoreeKompareBean kompareBean = new KevoreeKompareBean();
        AdaptationModel adaptationModel = kompareBean.kompare(current_model, target_model, nodeName);
        for(AdaptationPrimitive p : adaptationModel.getAdaptationsForJ())
        {
            if(p.getRef() instanceof ComponentInstance )
            {
                ComponentInstance instance =(ComponentInstance) p.getRef();

                boolean  found_refused_rules = false;
                boolean  found_authorize_rules = false;

               // check
                for(SecurityRule rule :refused_rules)
                {
                    Object ptr =   current_model.findByQuery( rule.getKElementQuery());
                    if( ptr instanceof ComponentTypeImpl)
                    {
                        ComponentTypeImpl componentType= (ComponentTypeImpl) ptr;

                        if(instance.getTypeDefinition().getName().equals(componentType.getName())){
                            //ok
                            found_refused_rules = true;

                            // todo check primitives
                        }
                    }
                }

                if(found_refused_rules)
                {
                    if(!result.contains(p)){
                        result.add(p);
                    }
                } else
                {                             // todo check primitives
                    // this rules is not denied we check if is authorized
                    for(SecurityRule rule :authorize_rules)
                    {
                        Object ptr =   current_model.findByQuery( rule.getKElementQuery());

                        if( ptr instanceof ComponentTypeImpl)
                        {

                            ComponentTypeImpl componentType= (ComponentTypeImpl) ptr;

                            if(instance.getTypeDefinition().getName().equals(componentType.getName())){
                                //ok
                                found_authorize_rules = true;
                            }
                        }

                    }
                    if(!found_authorize_rules)
                    {
                        if(!result.contains(p)){
                            result.add(p);
                        }
                    }

                }





            }



          /*

            if(p.getRef() instanceof ComponentInstance || p.getRef() instanceof ComponentInstance ){

                ComponentInstance instance =(ComponentInstance) p.getRef();


                    List<String>  operation = cluster_permissions.get(nodeName).getPermissions(instance.getTypeDefinition());

                    if(!operation.contains(p.getPrimitiveType().getName())){

                        if(!result.contains(p)){
                            result.add(p);
                        }
                    }


            }else if( p.getRef() instanceof AbstractChannel)
            {
                Channel d = (Channel)p.getRef();



            } else {
                //System.err.println("todo type "+p.getRef());
            }
               */

        }
        return result;

    }
}
