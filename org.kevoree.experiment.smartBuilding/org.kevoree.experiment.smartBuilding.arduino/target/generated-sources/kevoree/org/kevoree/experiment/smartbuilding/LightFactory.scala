package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object LightFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createLight()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.Light].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.Light].lifeCycle()}
}}
def createLight() : org.kevoree.experiment.smartbuilding.Light ={
var newcomponent = new org.kevoree.experiment.smartbuilding.Light();
newcomponent.getHostedPorts().put("on",createLightPORTon(newcomponent))
newcomponent.getHostedPorts().put("off",createLightPORToff(newcomponent))
newcomponent}
def createLightPORTon(component : Light) : LightPORTon ={ new LightPORTon(component)}
def createLightPORToff(component : Light) : LightPORToff ={ new LightPORToff(component)}
}
