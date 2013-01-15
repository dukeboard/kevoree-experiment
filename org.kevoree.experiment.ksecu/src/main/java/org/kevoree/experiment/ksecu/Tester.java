package org.kevoree.experiment.ksecu;

import org.kevoree.*;
import org.kevoree.KSecurityModel.KSecurityModelFactory;
import org.kevoree.KSecurityModel.KSecurityRoot;
import org.kevoree.KSecurityModel.SecurityRule;
import org.kevoree.KevoreeFactory;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.impl.ComponentTypeImpl;
import org.kevoree.kompare.JavaSePrimitive;
import org.kevoreeAdaptation.AdaptationPrimitive;
import scala.Option;

import java.io.*;
import java.math.BigInteger;
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



    public static void main(String argv[]) throws Exception {

        byte[] base_model = load_file(Tester.class.getClassLoader().getResourceAsStream("simple_fake_console.kev"));
        byte[] new_model = load_file(Tester.class.getClassLoader().getResourceAsStream("target_simple_fake_console.kev"));
        ContainerRoot current_model = KevoreeXmiHelper.loadString(new String(base_model));
        ContainerRoot target_model = KevoreeXmiHelper.loadString(new String(new_model));




        SecurityManager  securityManager = new SecurityManager();



        KSecurityRoot security_model = KSecurityModelFactory.createKSecurityRoot();


        SecurityRule rule1 = KSecurityModelFactory.createSecurityRule();
        rule1.setKElementQuery("typeDefinitions[FakeConsole]");

        List<String>  primitiveTypes = new ArrayList<String>();

        primitiveTypes.add(JavaSePrimitive.AddInstance());
        primitiveTypes.add(JavaSePrimitive.StopInstance());
        primitiveTypes.add(JavaSePrimitive.StartInstance());
        primitiveTypes.add(JavaSePrimitive.UpdateDictionaryInstance());

        rule1.setPrimitiveTypes(primitiveTypes);


        security_model.addAuthorized(rule1);






        securityManager.setSecurity_model(security_model);





                /*
        permissions.accept( JavaSePrimitive.UpdateType());
        permissions.accept( JavaSePrimitive.UpdateDeployUnit());
        permissions.accept( JavaSePrimitive.AddType());
        permissions.accept( JavaSePrimitive.AddDeployUnit());
        permissions.accept( JavaSePrimitive.AddThirdParty());
        permissions.accept( JavaSePrimitive.RemoveThirdParty());
        permissions.accept( JavaSePrimitive.RemoveType());
        permissions.accept( JavaSePrimitive.RemoveDeployUnit());
        permissions.accept( JavaSePrimitive.UpdateInstance());

        permissions.accept( JavaSePrimitive.UpdateBinding());
        permissions.accept( JavaSePrimitive.UpdateDictionaryInstance());

        permissions.accept( JavaSePrimitive.RemoveInstance());
        permissions.accept( JavaSePrimitive.AddBinding());
        permissions.accept( JavaSePrimitive.RemoveBinding());
        permissions.accept( JavaSePrimitive.AddFragmentBinding());
        permissions.accept( JavaSePrimitive.RemoveFragmentBinding());
        permissions.accept( JavaSePrimitive.UpdateFragmentBinding());
        permissions.accept( JavaSePrimitive.StartInstance());
        permissions.accept( JavaSePrimitive.StopInstance());
        permissions.accept( JavaSePrimitive.StartThirdParty());
        permissions.accept( JavaSePrimitive.StartThirdParty());
        permissions.accept( JavaSePrimitive.AddNode());
        permissions.accept( JavaSePrimitive.RemoveNode());
        */






        List<AdaptationPrimitive> result =  securityManager.verify("node0", current_model, target_model);

        if(result.size() == 0)
        {
            System.out.println("accepted");
        }else
        {
            for(AdaptationPrimitive p : result)
            {
                System.out.println("ici "+p.getPrimitiveType().getName()+" "+p.getRef());
            }
        }








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
