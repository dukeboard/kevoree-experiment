package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object FloorAlarmFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createFloorAlarm()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.FloorAlarm].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.FloorAlarm].lifeCycle()}
}}
def createFloorAlarm() : org.kevoree.experiment.smartbuilding.FloorAlarm ={
var newcomponent = new org.kevoree.experiment.smartbuilding.FloorAlarm();
newcomponent.getHostedPorts().put("sdata",createFloorAlarmPORTsdata(newcomponent))
newcomponent.getNeededPorts().put("forward",createFloorAlarmPORTforward(newcomponent))
newcomponent}
def createFloorAlarmPORTsdata(component : FloorAlarm) : FloorAlarmPORTsdata ={ new FloorAlarmPORTsdata(component)}
def createFloorAlarmPORTforward(component : FloorAlarm) : FloorAlarmPORTforward ={ return new FloorAlarmPORTforward(component);}
}
