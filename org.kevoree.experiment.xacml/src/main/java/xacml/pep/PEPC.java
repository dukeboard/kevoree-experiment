package xacml.pep;

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
    @ProvidedPort(name = "receiveRequest", type = PortType.MESSAGE),
    @ProvidedPort(name = "receiveResponse", type = PortType.MESSAGE)
})
@Requires({
    @RequiredPort(name = "send", type = PortType.MESSAGE, optional = true)
})

public class PEPC  extends AbstractComponentType{
	
	private PEP pep;

    @Start
    public void start() {
      pep = new PEP();
    }

    @Stop
    public void stop() {
    	
    }

    @Update
    public void update() {
    	
    }

    
    @Port(name = "receiveRequest")
	public void receiveRequest(Object o) {
			String res = o.toString(); 
			pep.gui.updateTextArea("receiveRequest : " + res);
			
			getPortByName("send", MessagePort.class).process(pep.generateRequest());
	}

    @Port(name = "receiveResponse")
	public void receiveResponse(Object o) {
			String res = o.toString(); 
			pep.gui.updateTextArea("receiveRequest : " + res);
	}
}
