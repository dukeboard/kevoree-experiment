package addressBook.enforcement.aseComponents;

import java.util.ArrayList;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Port;
import org.kevoree.annotation.PortType;
import org.kevoree.annotation.ProvidedPort;
import org.kevoree.annotation.Provides;
import org.kevoree.annotation.RequiredPort;
import org.kevoree.annotation.Requires;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;
import org.kevoree.framework.MessagePort;

@ComponentType()

@Provides({
	@ProvidedPort(name = "create", type = PortType.MESSAGE),
    @ProvidedPort(name = "read", type = PortType.MESSAGE),
    @ProvidedPort(name = "update", type = PortType.MESSAGE),
    @ProvidedPort(name = "delete", type = PortType.MESSAGE)
})

@Requires({
    @RequiredPort(name = "createR", type = PortType.MESSAGE, optional = true),
    @RequiredPort(name = "readR", type = PortType.MESSAGE, optional = true),
    @RequiredPort(name = "updateR", type = PortType.MESSAGE, optional = true),
    @RequiredPort(name = "deleteR", type = PortType.MESSAGE, optional = true)
})

public class User extends AbstractComponentType{

    @Start
    public void start() {
    	System.out.println("hello AddressBook");
    }

    @Stop
    public void stop() {
    }

    @Update
    public void update() {
    }


    @Port(name = "create")
    public void create(Object o) {
        System.out.println("create : "+ o.toString());
        getPortByName("createR", MessagePort.class).process(o);
    }
    
    @Port(name = "update")
    public void update(Object o) {
        System.out.println("update : "+ o.toString());
        getPortByName("updateR", MessagePort.class).process(o);
    }
    
    @Port(name = "delete")
    public void delete(Object o) {
        System.out.println("delete : "+ o.toString());
        getPortByName("deleteR", MessagePort.class).process(o);
    }
    
    @Port(name = "read")
    public void read(Object o) {
        System.out.println("read : "+ o.toString());
        getPortByName("readR", MessagePort.class).process(o);
    }
  
}
