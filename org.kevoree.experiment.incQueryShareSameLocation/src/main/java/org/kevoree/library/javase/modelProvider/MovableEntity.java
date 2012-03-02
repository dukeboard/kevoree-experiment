/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.kevoree.library.javase.modelProvider;

/**
 *
 * @author obendavi
 */

import java.util.Random;
import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.DictionaryAttribute;
import org.kevoree.annotation.DictionaryType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Port;
import org.kevoree.annotation.PortType;
import org.kevoree.annotation.ProvidedPort;
import org.kevoree.annotation.Provides;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.api.service.core.script.KevScriptEngine;
import org.kevoree.framework.AbstractComponentType;

/**
 *
 * @author obendavi
 */


@Library(name = "IncQuery SAMPLE")
@ComponentType()
@Provides({
        @ProvidedPort(name = "changeLocation", type = PortType.MESSAGE)
})
@DictionaryType({
        @DictionaryAttribute(name = "location", defaultValue = "0", optional = false)
})

public class MovableEntity extends AbstractComponentType{
	
	private DummyGUI gui;
	
	@Start
    public void start() {
		getDictionary().put("location", 0);
        System.out.println("MovableEntity : "+getName());
        gui = new DummyGUI();
        gui.setVisible(true);
    }

    @Stop
    public void stop() {
    }

    @Update
    public void update() {
    	gui.setTitle(getNodeName()+" : "+getName());
    	System.out
		.println("*****************************************************************************");
    	System.out.println("Ok I've updated my location : "+getDictionary().get("location"));
    	gui.updateTextArea("Ok I've updated my location : "+getDictionary().get("location"));
    	System.out
		.println("*****************************************************************************");
    }

    @Port(name = "changeLocation")
    public void changeLocation(Object o) {    	
        Random generator = new Random();
        int loc = generator.nextInt(3);
        KevScriptEngine kse = getKevScriptEngineFactory().createKevScriptEngine();
        kse.append("updateDictionary "+getName()+"@"+getNodeName()+"{\"location\"=\""+Integer.toString(loc)+"\"} ");
        kse.atomicInterpretDeploy();
    }
}