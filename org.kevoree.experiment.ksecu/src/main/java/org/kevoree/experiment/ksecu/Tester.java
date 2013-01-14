package org.kevoree.experiment.ksecu;

import org.kevoree.AdaptationPrimitiveType;
import org.kevoree.ContainerRoot;
import org.kevoree.KevoreeFactory;
import org.kevoree.TypeDefinition;
import org.kevoree.api.service.core.script.KevScriptEngineException;
import org.kevoree.experiment.ksecu.utils.KevScriptLoader;
import org.kevoree.framework.KevoreeXmiHelper;
import org.kevoree.framework.osgi.KevoreeInstanceFactory;
import org.kevoree.impl.TypeDefinitionImpl;
import org.kevoree.kompare.JavaSePrimitive;
import org.kevoreeAdaptation.AdaptationPrimitive;

import java.io.*;
import java.math.BigInteger;
import java.security.*;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.KeyPair;
import java.security.spec.InvalidKeySpecException;
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


        SecurityManager  securityManager = new SecurityManager();






        Permissions permissions =  securityManager.getPermissions("node0");


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

        TypeDefinition t = KevoreeFactory.createTypeDefinition();
        t.setName("FakeConsole");
        t.setBean("org.kevoree.library.FakeConsole");

        permissions.accept( JavaSePrimitive.AddInstance(),t);
        permissions.accept( JavaSePrimitive.StopInstance(),t);
        permissions.accept( JavaSePrimitive.StartInstance(),t);
        permissions.accept( JavaSePrimitive.UpdateDictionaryInstance(),t);




        // read model and sign
        byte[] base_model = load_file(Tester.class.getClassLoader().getResourceAsStream("simple_fake_console.kev"));
        byte[] new_model = load_file(Tester.class.getClassLoader().getResourceAsStream("target_simple_fake_console.kev"));



        ContainerRoot current_model = KevoreeXmiHelper.loadString(new String(base_model));

        ContainerRoot target_model = KevoreeXmiHelper.loadString(new String(new_model));

        List<AdaptationPrimitive> result =  securityManager.verify("node0", current_model, target_model);

        if(result.size() == 0)
        {
            System.out.println("accepted");
        }else
        {
            for(AdaptationPrimitive p : result)
            {
                System.out.println(p.getPrimitiveType().getName()+" "+p.getRef());
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
