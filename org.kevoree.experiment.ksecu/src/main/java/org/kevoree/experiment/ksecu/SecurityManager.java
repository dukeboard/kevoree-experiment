package org.kevoree.experiment.ksecu;

import org.kevoree.*;
import org.kevoree.KSecurityModel.*;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.kompare.KevoreeKompareBean;
import org.kevoreeAdaptation.AdaptationModel;
import org.kevoreeAdaptation.AdaptationPrimitive;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 14/01/13
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
public class SecurityManager {

   private KSecurityRoot model;


    public SecurityManager(){
        model = KSecurityModelFactory.createKSecurityRoot();
    }


    public KSecurityRoot getModel() {
        return model;
    }

    public void setModel(KSecurityRoot model) {
        this.model = model;
    }



    public List<AdaptationPrimitive> verify(String nodeName,ContainerRoot current_model,SignedModel signed_model ) throws SecurityException, NoSuchAlgorithmException, InvalidKeyException, SignatureException {

        if(model == null)
        {
            throw new SecurityException("no security model");
        }

        List<AdaptationPrimitive> adaptation_primitives_refused = new ArrayList<AdaptationPrimitive>();
        ContainerRoot target_model = KevoreeXmiHelper.loadString(new String(signed_model.getModel()));

        // clean rules
        KSecurityRoot  model_cleaned=  SecurityHelper.filter_rules(signed_model, model);

        List<KSecurityRule> authorize_rules =    model_cleaned.getAuthorizedForJ();
        List<KSecurityRule> refused_rules =      model_cleaned.getNo_authorizedForJ();


        KevoreeKompareBean kompareBean = new KevoreeKompareBean();
        AdaptationModel adaptationModel = kompareBean.kompare(current_model,target_model, nodeName);

        for(AdaptationPrimitive p : adaptationModel.getAdaptationsForJ())
        {
            if(p.getRef() instanceof Instance)
            {
                Instance instance =(Instance) p.getRef();

                boolean  found_in_refused_rules = false;
                boolean  found_in_authorize_rules = false;

                // check in refused rules
                for(KSecurityRule rule :refused_rules)
                {
                    Object ptr =   target_model.findByQuery( rule.getKElementQuery());
                    rule.getAllowed();

                    if( ptr instanceof TypeDefinition)
                    {
                        TypeDefinition componentType= (TypeDefinition) ptr;
                        if(instance.getTypeDefinition().getName().equals(componentType.getName()))
                        {
                            if(rule.getPrimitiveTypes().contains(p.getPrimitiveType().getName())){
                                found_in_refused_rules = true;

                            }
                        }
                    }
                }

                if(found_in_refused_rules)
                {
                    if(!adaptation_primitives_refused.contains(p))
                    {
                        adaptation_primitives_refused.add(p);
                    }
                } else
                {
                    // this rules is not denied we check if is authorized
                    for(KSecurityRule rule :authorize_rules)
                    {

                        Object ptr =   target_model.findByQuery(rule.getKElementQuery());

                        if( ptr instanceof TypeDefinition)
                        {
                            TypeDefinition componentType= (TypeDefinition) ptr;


                            if(instance.getTypeDefinition().getName().equals(componentType.getName())){
                                //check primitives access

                                if(rule.getPrimitiveTypes().contains(p.getPrimitiveType().getName())){
                                   /*  ports bindings
                                    if(ptr instanceof ComponentType){
                                        ComponentType c = (ComponentType)ptr;

                                         for(PortTypeRef required_port : c.getRequiredForJ() ){

                                                  System.out.println(required_port.getName()+" "+required_port.getRef());

                                         }


                                        for(PortTypeRef provided_port : c.getProvidedForJ() ){


                                        }

                                    }*/
                                    found_in_authorize_rules = true;
                                }
                            }
                        }

                    }
                    if(!found_in_authorize_rules)
                    {
                        if(!adaptation_primitives_refused.contains(p)){
                            adaptation_primitives_refused.add(p);
                        }
                    }

                }



            } else  if(p.getRef() instanceof MBinding){





            }



        }
        return adaptation_primitives_refused;

    }
}
