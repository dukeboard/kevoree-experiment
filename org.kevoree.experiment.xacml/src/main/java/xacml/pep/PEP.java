package xacml.pep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;

//import server.EnforcementServer;
//import server.pep.ThreadRPEP;
//import useful.GUI;
//import useful.Pair;

import com.sun.xacml.ParsingException;
import com.sun.xacml.ctx.RequestCtx;
import com.sun.xacml.ctx.ResponseCtx;
import com.sun.xacml.ctx.Result;

public class PEP {
	
	public DummyGUIPEP gui;
	
	public PEP(){
		gui = new DummyGUIPEP(this);
		gui.setVisible(true);		
	}
	
	public File generateRequest(){		
		return new File("/home/obendavi/Bureau/coding/workspaceKEVOREE/kevoree-experiment/org.kevoree.experiment.xacml/src/main/resources/requests/request.xml");
	}
}