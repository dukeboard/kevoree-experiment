package org.kevoree.library.javase.modelProvider;

import org.kevoree.annotation.ComponentType;
import org.kevoree.annotation.Library;
import org.kevoree.annotation.Start;
import org.kevoree.annotation.Stop;
import org.kevoree.annotation.Update;
import org.kevoree.framework.AbstractComponentType;



//in order to replace the switch to manage modifications of locations


@Library(name = "IncQuery SAMPLE")
@ComponentType()

public class Simulator extends AbstractComponentType{
	
	@Start
    public void start() {
     
    }

    @Stop
    public void stop() {
    }

    @Update
    public void update() {
    }
	
    
    public void addNodeWithMovableEntity(){
    	
    }
    
    
    public void changelocationOfMovableEntities(){
    	
    }
	
	

}
