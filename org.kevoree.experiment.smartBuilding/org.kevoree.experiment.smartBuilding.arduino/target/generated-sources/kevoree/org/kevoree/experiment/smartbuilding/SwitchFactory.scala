package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object SwitchFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createSwitch()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.Switch].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.Switch].lifeCycle()}
}}
def createSwitch() : org.kevoree.experiment.smartbuilding.Switch ={
var newcomponent = new org.kevoree.experiment.smartbuilding.Switch();
newcomponent.getNeededPorts().put("click",createSwitchPORTclick(newcomponent))
newcomponent.getNeededPorts().put("release",createSwitchPORTrelease(newcomponent))
newcomponent}
def createSwitchPORTclick(component : Switch) : SwitchPORTclick ={ return new SwitchPORTclick(component);}
def createSwitchPORTrelease(component : Switch) : SwitchPORTrelease ={ return new SwitchPORTrelease(component);}
}
