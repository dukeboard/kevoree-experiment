package peer;

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
@Provides({ 
	@ProvidedPort(name = "download", type = PortType.MESSAGE)
})
@Requires({
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
	
	
	@Port(name = "download")
	public void download(Object o) {
		
	}
	
}
