package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object LightSensorFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createLightSensor()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.LightSensor].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.LightSensor].lifeCycle()}
}}
def createLightSensor() : org.kevoree.experiment.smartbuilding.LightSensor ={
var newcomponent = new org.kevoree.experiment.smartbuilding.LightSensor();
newcomponent.getNeededPorts().put("value",createLightSensorPORTvalue(newcomponent))
newcomponent}
def createLightSensorPORTvalue(component : LightSensor) : LightSensorPORTvalue ={ return new LightSensorPORTvalue(component);}
}
