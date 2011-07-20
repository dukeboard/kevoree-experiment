package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object TempSensorFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createTempSensor()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.TempSensor].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.TempSensor].lifeCycle()}
}}
def createTempSensor() : org.kevoree.experiment.smartbuilding.TempSensor ={
var newcomponent = new org.kevoree.experiment.smartbuilding.TempSensor();
newcomponent.getNeededPorts().put("value",createTempSensorPORTvalue(newcomponent))
newcomponent}
def createTempSensorPORTvalue(component : TempSensor) : TempSensorPORTvalue ={ return new TempSensorPORTvalue(component);}
}
