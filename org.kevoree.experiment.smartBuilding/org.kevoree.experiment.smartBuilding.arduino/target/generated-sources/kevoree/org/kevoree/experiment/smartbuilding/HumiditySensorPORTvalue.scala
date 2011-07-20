package org.kevoree.experiment.smartbuilding
import org.kevoree.framework.port._
import scala.{Unit=>void}
class HumiditySensorPORTvalue(component : HumiditySensor) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "value"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
def getInOut = false
}
