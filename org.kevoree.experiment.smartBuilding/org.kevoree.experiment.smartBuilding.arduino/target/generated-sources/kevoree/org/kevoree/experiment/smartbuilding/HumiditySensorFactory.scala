package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object HumiditySensorFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createHumiditySensor()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.HumiditySensor].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.HumiditySensor].lifeCycle()}
}}
def createHumiditySensor() : org.kevoree.experiment.smartbuilding.HumiditySensor ={
var newcomponent = new org.kevoree.experiment.smartbuilding.HumiditySensor();
newcomponent.getNeededPorts().put("value",createHumiditySensorPORTvalue(newcomponent))
newcomponent}
def createHumiditySensorPORTvalue(component : HumiditySensor) : HumiditySensorPORTvalue ={ return new HumiditySensorPORTvalue(component);}
}
