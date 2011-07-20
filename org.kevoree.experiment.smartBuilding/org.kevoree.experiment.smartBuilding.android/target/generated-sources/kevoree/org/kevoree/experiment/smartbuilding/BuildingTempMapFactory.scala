package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object BuildingTempMapFactory{
def createComponentActor() : KevoreeComponent = {
new KevoreeComponent(createBuildingTempMap()){def startComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.BuildingTempMap].lifeCycle()}
def stopComponent(){getKevoreeComponentType.asInstanceOf[org.kevoree.experiment.smartbuilding.BuildingTempMap].lifeCycle()}
}}
def createBuildingTempMap() : org.kevoree.experiment.smartbuilding.BuildingTempMap ={
var newcomponent = new org.kevoree.experiment.smartbuilding.BuildingTempMap();
newcomponent.getHostedPorts().put("datas",createBuildingTempMapPORTdatas(newcomponent))
newcomponent}
def createBuildingTempMapPORTdatas(component : BuildingTempMap) : BuildingTempMapPORTdatas ={ new BuildingTempMapPORTdatas(component)}
}
