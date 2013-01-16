package org.kevoree.experiment.ksecu;

import org.kevoree.*;
import org.kevoree.KSecurityModel.*;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.kompare.JavaSePrimitive;
import org.kevoreeAdaptation.AdaptationPrimitive;

import java.io.*;
import java.math.BigInteger;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 08/01/13
 * Time: 08:54
 * To change this template use File | Settings | File Templates.
 */
public class Tester {


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







    public static void main(String argv[]) throws Exception
    {

        ContainerRoot current_model = KevoreeXmiHelper.loadStream(Tester.class.getClassLoader().getResourceAsStream("empty_node.kev"));
        ContainerRoot target_model = KevoreeXmiHelper.loadStream(Tester.class.getClassLoader().getResourceAsStream("random_nio_grapher_group.kev"));


        SecurityManager  securityManager = new SecurityManager();


        KeyPair key1 =  SecurityHelper.generateKeys(512);

        // insert
        KPublicKey kkey = KSecurityModelFactory.createKPublicKey();
        kkey.setKey(key1.getPublic());




        SignedModel modelsigned =   SecurityHelper.createSignedModel(target_model, Arrays.asList(key1.getPrivate())) ;



        KSecurityRule rule1 = KSecurityModelFactory.createKSecurityRule();
        rule1.setKElementQuery("typeDefinitions[FakeConsole]");
        rule1.setPrimitiveTypes(  Arrays.asList(JavaSePrimitive.AddInstance(),JavaSePrimitive.StopInstance(),JavaSePrimitive.AddInstance(),JavaSePrimitive.StartInstance(),JavaSePrimitive.StopInstance() ,JavaSePrimitive.AddFragmentBinding(),JavaSePrimitive.UpdateDictionaryInstance()));
        rule1.addAllowed(kkey);



        KSecurityRule rule2 = KSecurityModelFactory.createKSecurityRule();
        rule2.setKElementQuery("typeDefinitions[BasicGroup]");
        rule2.setPrimitiveTypes(             Arrays.asList(JavaSePrimitive.AddInstance(),JavaSePrimitive.StopInstance(),JavaSePrimitive.AddInstance(),JavaSePrimitive.StartInstance(),JavaSePrimitive.StopInstance() ,JavaSePrimitive.AddFragmentBinding(),JavaSePrimitive.UpdateDictionaryInstance()));

        rule2.addAllowed(kkey );


        KSecurityRule rule3 = KSecurityModelFactory.createKSecurityRule();

        rule3.setKElementQuery("typeDefinitions[NioChannel]");

        rule3.setPrimitiveTypes(   Arrays.asList(JavaSePrimitive.AddInstance(),JavaSePrimitive.StopInstance(),JavaSePrimitive.AddInstance(),JavaSePrimitive.StartInstance(),JavaSePrimitive.StopInstance() ,JavaSePrimitive.AddFragmentBinding(),JavaSePrimitive.UpdateDictionaryInstance()));
        rule3.addAllowed(kkey );




        KSecurityRule rule4 = KSecurityModelFactory.createKSecurityRule();

        rule4.setKElementQuery("typeDefinitions[Grapher]");

        rule4.setPrimitiveTypes(  Arrays.asList(JavaSePrimitive.AddInstance(),JavaSePrimitive.StopInstance(),JavaSePrimitive.AddInstance(),JavaSePrimitive.StartInstance(),JavaSePrimitive.StopInstance() ,JavaSePrimitive.AddFragmentBinding(),JavaSePrimitive.UpdateDictionaryInstance()));
        rule4.addAllowed(kkey );


        rule3.addAcceptBinding(rule4);



        securityManager.getModel().addAuthorized(rule1);
        securityManager.getModel().addAuthorized(rule2);
        securityManager.getModel().addAuthorized(rule3);
        securityManager.getModel().addNo_authorized(rule4);




        List<AdaptationPrimitive> result =  securityManager.verify("node1", current_model, modelsigned);

        if(result.size() == 0)
        {
            System.out.println("accepted");
        }else
        {
            for(AdaptationPrimitive p : result)
            {
                System.err.println("ERROR "+p.getPrimitiveType().getName()+" "+((Instance)p.getRef()).getName());
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

