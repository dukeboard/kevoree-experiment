package org.kevoree.experiment.smartbuilding
import org.kevoree.framework.port._
import scala.{Unit=>void}
class EmergencyLogPORTsynch(component : EmergencyLog) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "synch"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)=msg match {
case _ @ msg => component.trigger(msg)
}
}
