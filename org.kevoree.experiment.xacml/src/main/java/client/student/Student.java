package client.student;

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

@Library(name = "XACML")
@ComponentType()
@Requires({
    @RequiredPort(name = "request", type = PortType.MESSAGE, optional = true)
})
@Provides({
	@ProvidedPort(name = "receive", type = PortType.MESSAGE)
})
public class Student extends AbstractComponentType{
	private GUIStudent gui;
	private Vector<String> operations;

    @Start
    public void start() {
        operations = new Vector<String>();
        operations.add("download");
        operations.add("list");
        gui = new GUIStudent(this);
        gui.updateEntities(operations);
        gui.setTitle("Student : "+getName());
        gui.setVisible(true);
    }

    @Stop
    public void stop() {
    	operations = null;
    	gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		gui.dispose();
    }

    @Update
    public void update() {
    	gui.setTitle("Student : "+getName());
    }

    @Port(name = "receive")
   	public void receive(Object o) {
   			String res = o.toString(); 	
   			gui.updateTextArea("result request :"+res);
   	}

}
