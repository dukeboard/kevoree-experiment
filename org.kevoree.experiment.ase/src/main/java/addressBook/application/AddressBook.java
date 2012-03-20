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
		//if (o.toString().startsWith("create")) {
			System.out.println("create : " + o.toString());
			Contact c = new Contact();
			c.setName(o.toString());
			contacts.add(c);
			gui.updateTextArea(o.toString() + " created");
			update();
		//}
	}

	@Port(name = "update")
	public void update(Object o) {
		//if (o.toString().startsWith("update")) {
			System.out.println("update : " + o.toString());
			gui.updateTextArea(o.toString() + " updated");
		//}
	}

	@Port(name = "delete")
	public void delete(Object o) {
		//if (o.toString().startsWith("delete")) {
			System.out.println("delete : " + o.toString());
			gui.updateTextArea(o.toString() + " deleted");
			for (Contact c : contacts) {
				if (c.getName().equals(o.toString())) {
					contacts.remove(c);
				}
			}			
			update();
		//}
	}

	@Port(name = "read")
	public void read(Object o) {
		//if (o.toString().startsWith("read")) {
			System.out.println("read");
			gui.updateTextArea("read:"+o.toString());
			for (Contact c : contacts) {
				System.out.println("contact : " + c.getName());
				gui.updateTextArea("contact : " + c.getName());
			}
		//}
	}

}
