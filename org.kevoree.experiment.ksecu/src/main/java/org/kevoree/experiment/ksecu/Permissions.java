package org.kevoree.experiment.ksecu;

import org.kevoree.AdaptationPrimitiveType;
import org.kevoree.TypeDefinition;
import org.kevoree.kompare.KevoreeKompareBean;
import org.kevoreeAdaptation.AdaptationPrimitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 09/01/13
 * Time: 14:58
 * To change this template use File | Settings | File Templates.
 */
public class Permissions {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private HashMap<String,List<String>>  s = new HashMap<String, List<String>>();

    public List<String> getPermissions(TypeDefinition type) {
        String id = type.getBean();
        if(!s.containsKey(id)) {
            s.put(id,new ArrayList<String>());
        }
        return  s.get(id);
    }

    public void accept(String adapation_primitive,TypeDefinition type)
    {
        String id = type.getBean();
        if(!s.containsKey(id)) {
            s.put(id,new ArrayList<String>());
        }
        s.get(id).add(adapation_primitive);
    }


    public void acceptAll(List<String> o){

    }


}


