package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object LuxOrchFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createLuxOrch()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.LuxOrch].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.LuxOrch].lifeCycle()}
}}
def createLuxOrch() : org.kevoree.experiment.smartbuilding.LuxOrch ={
var newcomponent = new org.kevoree.experiment.smartbuilding.LuxOrch();
newcomponent.getHostedPorts().put("sdata",createLuxOrchPORTsdata(newcomponent))
newcomponent.getNeededPorts().put("on",createLuxOrchPORTon(newcomponent))
newcomponent.getNeededPorts().put("off",createLuxOrchPORToff(newcomponent))
newcomponent}
def createLuxOrchPORTsdata(component : LuxOrch) : LuxOrchPORTsdata ={ new LuxOrchPORTsdata(component)}
def createLuxOrchPORTon(component : LuxOrch) : LuxOrchPORTon ={ return new LuxOrchPORTon(component);}
def createLuxOrchPORToff(component : LuxOrch) : LuxOrchPORToff ={ return new LuxOrchPORToff(component);}
}
