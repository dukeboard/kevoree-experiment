package org.kevoree.experiment.smartbuilding
import org.kevoree.framework.port._
import scala.{Unit=>void}
class SwitchPORTrelease(component : Switch) extends org.kevoree.framework.MessagePort with KevoreeRequiredPort {
def getName : String = "release"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
def getInOut = false
}
