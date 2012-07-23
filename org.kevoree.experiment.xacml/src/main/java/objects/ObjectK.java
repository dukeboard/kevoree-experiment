package objects;

import java.util.Vector;

import javax.swing.JFrame;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
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

import client.visitor.GUIVisitor;



@Library(name = "XACML")
@ComponentType()
@Requires({
    @RequiredPort(name = "send", type = PortType.MESSAGE, optional = true)
})

@Provides({
	@ProvidedPort(name = "receive", type = PortType.MESSAGE)
})

public class ObjectK extends AbstractComponentType{

    @Start
    public void start() {
    }

    @Stop
    public void stop() {
  
    }

    @Update
    public void update() {
    }

    @Port(name = "receive")
   	public void receive(Object o) {
   			String res = o.toString(); 	
   			getPortByName("send",MessagePort.class).process(getName());
   	}


}
