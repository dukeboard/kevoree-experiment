package org.kevoree.experiment.smartbuilding
import org.kevoree.framework.port._
import scala.{Unit=>void}
class LuxOrchPORTon(component : LuxOrch) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "on"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
def getInOut = false
}
