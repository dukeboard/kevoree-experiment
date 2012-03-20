package addressBook.client;

import java.util.Vector;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.PortType;
import org.kevoree.annotation.RequiredPort;
import org.kevoree.annotation.Requires;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;


@ComponentType()

@Requires({
        @RequiredPort(name = "create", type = PortType.MESSAGE, optional = true),
        @RequiredPort(name = "read", type = PortType.MESSAGE, optional = true),
        @RequiredPort(name = "update", type = PortType.MESSAGE, optional = true),
        @RequiredPort(name = "delete", type = PortType.MESSAGE, optional = true)
})

public class AddressBookClient  extends AbstractComponentType{
	private DummyGUIClient gui;
	private Vector<String> operations;

    @Start
    public void start() {
        System.out.println("hello AddressBookClient");
        operations = new Vector<String>();
        operations.add("create");
        operations.add("read");
        operations.add("update");
        operations.add("delete");
        gui = new DummyGUIClient(this);
        gui.updateEntities(operations);
        gui.setTitle("AddressBookClient : "+getName());
        gui.setVisible(true);
    }

    @Stop
    public void stop() {
    }

    @Update
    public void update() {
    	gui.setTitle("AddressBookClient : "+getName());
    }



}
