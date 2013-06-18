package org.kevoree.library.ec2;

import org.kevoree.*;
import org.kevoree.annotation.*;
import org.kevoree.annotation.DictionaryAttribute;
import org.kevoree.annotation.DictionaryType;
import org.kevoree.annotation.NodeType;
import org.kevoree.api.service.core.handler.ModelListener;
import org.kevoree.framework.KevoreePropertyHelper;
import org.kevoree.library.sky.api.nodeType.PJavaSENode;

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 04/06/13
 * Time: 16:23
 *
 * @author Erwan Daubert
 * @version 1.0
 */
@Library(name = "EC2")
@NodeType
@DictionaryType({
        @DictionaryAttribute(name = "imageName", defaultValue = "debian.img", optional = false)
})
public class PEc2Node extends PJavaSENode implements ModelListener {

    @Start
    public void startNode() {
        // register this instance as an modelListener instance:
        // //each "interaction" with the Kevoree Core (which manipulates the model) will trigger this instance
              getModelService().registerModelListener(this);
    }

    @Override
    public void modelUpdated() {
        super.modelUpdated();
        // look at all the components, channels and group which are linked to this instance of node
        for (ComponentInstance component : getModelElement().getComponents()) {
            String value = KevoreePropertyHelper.instance$.getProperty(component, "port", false, "");
                for (org.kevoree.Port port : component.getProvided() ) {
                                             for (MBinding binding : port.getBindings()) {
                                                 Channel channel = binding.getHub();
                                                 String channelValue = KevoreePropertyHelper.instance$.getProperty(channel, "port", true, getName());
                                                 System.out.println(channelValue);

                                             }
                }
        }
        ContainerRoot currentModel = getModelService().getLastModel();
        for (Group group : currentModel.getGroups()) {
            if (group.findSubNodesByID(this.getName()) != null) {
                String groupValue = KevoreePropertyHelper.instance$.getProperty(group, "port", true, getName());

            }

        }

        // update the amazon configuration by opening all the new port

        //get all the already opened ports
        // foreach of them you need to test if it appears on the needed ports
        // if the port is not on the list, you need to use amazon ec2 to close it

    }
}
