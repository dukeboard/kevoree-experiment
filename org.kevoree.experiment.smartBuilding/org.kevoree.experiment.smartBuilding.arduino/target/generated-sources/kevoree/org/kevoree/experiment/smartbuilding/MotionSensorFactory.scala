package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object MotionSensorFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createMotionSensor()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.MotionSensor].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.MotionSensor].lifeCycle()}
}}
def createMotionSensor() : org.kevoree.experiment.smartbuilding.MotionSensor ={
var newcomponent = new org.kevoree.experiment.smartbuilding.MotionSensor();
newcomponent.getNeededPorts().put("out",createMotionSensorPORTout(newcomponent))
newcomponent}
def createMotionSensorPORTout(component : MotionSensor) : MotionSensorPORTout ={ return new MotionSensorPORTout(component);}
}
