package client.peer;

import java.util.Vector;

import javax.swing.JFrame;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;


@Library(name = "XACML")
@ComponentType()
public class Peer extends AbstractComponentType{
	private GUIPeer gui;
	private Vector<String> operations;

    @Start
    public void start() {
        System.out.println("hello AddressBookClient");
        operations = new Vector<String>();
        operations.add("create");
        operations.add("read");
        operations.add("update");
        operations.add("delete");
        gui = new GUIPeer(this);
        gui.updateEntities(operations);
        gui.setTitle("AddressBookClient : "+getName());
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
    	gui.setTitle("AddressBookClient : "+getName());
    }



}
