package peer;

import org.kevoree.ServicePortType;
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



@Library(name = "XACML")
@ComponentType()
@Provides({ 
	@ProvidedPort(name = "uploadRequest", type = PortType.MESSAGE),
	@ProvidedPort(name = "download", type = PortType.MESSAGE),
	@ProvidedPort(name = "trigger", type = PortType.MESSAGE)
})
@Requires({
	@RequiredPort(name = "downloadRequest", type = PortType.MESSAGE, optional = true),
	@RequiredPort(name = "upload", type = PortType.MESSAGE, optional = true)
})


public class Peer extends AbstractComponentType {

	@Start
	public void start() {
	}

	@Stop
	public void stop() {
	}

	@Update
	public void update() {
	}
	
	@Port(name = "trigger")
	public void trigger(Object o) {
		getPortByName("downloadRequest", MessagePort.class).process("test");
	}
	
	
	@Port(name = "uploadRequest")
	public void uploadRequest(Object o) {
		if(o.toString().equals("test")){
			getPortByName("upload",MessagePort.class).process("send test");
		}
	}
	
	@Port(name = "download")
	public void download(Object o) {
		System.out.println("downloaded : "+o.toString());
	}
	
	
	public String download(String id){
		System.out.println(id);
		return id;
	}
	
}
