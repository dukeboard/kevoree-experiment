package org.kevoree.experiment.ksecu;

import org.kevoree.AdaptationPrimitiveType;
import org.kevoree.ContainerRoot;
import org.kevoree.KevoreeFactory;
import org.kevoree.TypeDefinition;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.kompare.JavaSePrimitive;
import org.kevoreeAdaptation.AdaptationPrimitive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: jed
 * Date: 14/01/13
 * Time: 11:42
 * To change this template use File | Settings | File Templates.
 */
@ComponentType
public class CSecurityManager extends AbstractComponentType {


    private Logger logger = LoggerFactory.getLogger(getClass());
    private SecurityManager securityManager;
    private Permissions permissions;

    @Start
    public void start()
    {
        securityManager = new SecurityManager();



        TypeDefinition t = KevoreeFactory.createTypeDefinition();
        t.setName("FakeConsole");
        t.setBean("org.kevoree.library.FakeConsole");

        permissions.accept( JavaSePrimitive.AddInstance(),t);
        permissions.accept( JavaSePrimitive.StartInstance(),t);
        permissions.accept( JavaSePrimitive.StopInstance(),t);
        permissions.accept( JavaSePrimitive.UpdateDictionaryInstance(),t);
        permissions.accept( JavaSePrimitive.RemoveInstance(),t);


        getModelService().registerModelListener(new ModelListener() {
            @Override
            public boolean preUpdate(ContainerRoot current_model, ContainerRoot target) {

                if (current_model != null && target != null)
                {

                    logger.info("Checking the permissions");





                }

                return true;
            }

            @Override
            public boolean initUpdate(ContainerRoot containerRoot, ContainerRoot containerRoot2) {
                return true;
            }

            @Override
            public boolean afterLocalUpdate(ContainerRoot containerRoot, ContainerRoot containerRoot2) {
                return true;
            }

            @Override
            public void modelUpdated() {

            }

            @Override
            public void preRollback(ContainerRoot containerRoot, ContainerRoot containerRoot2) {

            }

            @Override
            public void postRollback(ContainerRoot containerRoot, ContainerRoot containerRoot2) {

            }
        });
    }


    @Stop
    public void stop(){


    }


    @Update
    public void update(){


    }
}
