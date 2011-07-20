package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object GlobalAlarmFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createGlobalAlarm()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.GlobalAlarm].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.GlobalAlarm].lifeCycle()}
}}
def createGlobalAlarm() : org.kevoree.experiment.smartbuilding.GlobalAlarm ={
var newcomponent = new org.kevoree.experiment.smartbuilding.GlobalAlarm();
newcomponent.getHostedPorts().put("sdata",createGlobalAlarmPORTsdata(newcomponent))
newcomponent}
def createGlobalAlarmPORTsdata(component : GlobalAlarm) : GlobalAlarmPORTsdata ={ new GlobalAlarmPORTsdata(component)}
}
