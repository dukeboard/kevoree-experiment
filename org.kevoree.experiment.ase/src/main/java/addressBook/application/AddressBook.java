package addressBook.application;

import java.util.ArrayList;
import java.util.Vector;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Port;
import org.kevoree.annotation.PortType;
import org.kevoree.annotation.ProvidedPort;
import org.kevoree.annotation.Provides;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;


@ComponentType()
@Provides({ @ProvidedPort(name = "create", type = PortType.MESSAGE),
		@ProvidedPort(name = "read", type = PortType.MESSAGE),
		@ProvidedPort(name = "update", type = PortType.MESSAGE),
		@ProvidedPort(name = "delete", type = PortType.MESSAGE) })

public class AddressBook extends AbstractComponentType {
	private DummyGUIApp gui;
	private ArrayList<Contact> contacts;

	@Start
	public void start() {
		contacts = new ArrayList<Contact>();
		System.out.println("hello AddressBook");
		gui = new DummyGUIApp(this);
		gui.setTitle("AddressBook : " + getName());
		gui.setVisible(true);
	}

	@Stop
	public void stop() {
	}

	@Update
	public void update() {
		gui.setTitle("AddressBook : " + getName());
		Vector<String> v = new Vector<String>();
		for (Contact c : contacts) {
			v.add(c.getName());
		}
		gui.updateEntities(v);
	}

	@Port(name = "create")
	public void create(Object o) {
			String res = o.toString(); 
			System.out.println("create : " + res);
			gui.updateTextArea("create : " + res);
	}

	@Port(name = "update")
	public void update(Object o) {
		String res = o.toString(); 
		System.out.println("update : " + res);
		gui.updateTextArea("update : " + res);
	}

	@Port(name = "delete")
	public void delete(Object o) {		
		String res = o.toString(); 
		System.out.println("delete : " + res);
		gui.updateTextArea("delete : " + res);
	}

	@Port(name = "read")
	public void read(Object o) {
		String res = o.toString(); 
		System.out.println("read : " + res);
		gui.updateTextArea("read : " + res);
	}

}
