package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object SmokeSensorFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createSmokeSensor()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.SmokeSensor].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.SmokeSensor].lifeCycle()}
}}
def createSmokeSensor() : org.kevoree.experiment.smartbuilding.SmokeSensor ={
var newcomponent = new org.kevoree.experiment.smartbuilding.SmokeSensor();
newcomponent.getNeededPorts().put("value",createSmokeSensorPORTvalue(newcomponent))
newcomponent}
def createSmokeSensorPORTvalue(component : SmokeSensor) : SmokeSensorPORTvalue ={ return new SmokeSensorPORTvalue(component);}
}
