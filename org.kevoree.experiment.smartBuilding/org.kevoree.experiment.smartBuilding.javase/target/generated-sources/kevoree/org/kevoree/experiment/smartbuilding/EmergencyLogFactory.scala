package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object EmergencyLogFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createEmergencyLog()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.EmergencyLog].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.EmergencyLog].lifeCycle()}
}}
def createEmergencyLog() : org.kevoree.experiment.smartbuilding.EmergencyLog ={
var newcomponent = new org.kevoree.experiment.smartbuilding.EmergencyLog();
newcomponent.getHostedPorts().put("sdata",createEmergencyLogPORTsdata(newcomponent))
newcomponent.getHostedPorts().put("synch",createEmergencyLogPORTsynch(newcomponent))
newcomponent.getNeededPorts().put("forward",createEmergencyLogPORTforward(newcomponent))
newcomponent}
def createEmergencyLogPORTsdata(component : EmergencyLog) : EmergencyLogPORTsdata ={ new EmergencyLogPORTsdata(component)}
def createEmergencyLogPORTsynch(component : EmergencyLog) : EmergencyLogPORTsynch ={ new EmergencyLogPORTsynch(component)}
def createEmergencyLogPORTforward(component : EmergencyLog) : EmergencyLogPORTforward ={ return new EmergencyLogPORTforward(component);}
}
