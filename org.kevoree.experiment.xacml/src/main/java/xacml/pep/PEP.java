package xacml.pep;

import java.io.File;
import java.net.URISyntaxException;

import xacml.filesGenerator.RequestGenerator;

public class PEP {
	
	public DummyGUIPEP gui;
	
	public PEP(){
		gui = new DummyGUIPEP(this);
		gui.setVisible(true);		
	}
	
	public File generateRequest(String userId,String resourceId, String actionId){
		try {
			RequestGenerator.createRequest("request.xml", userId, resourceId, actionId);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return new File("request.xml");
	}
}