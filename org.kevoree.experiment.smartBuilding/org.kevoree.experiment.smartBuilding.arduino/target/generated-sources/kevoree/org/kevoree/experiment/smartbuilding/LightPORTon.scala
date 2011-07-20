package org.kevoree.experiment.smartbuilding
import org.kevoree.framework.port._
import scala.{Unit=>void}
class LightPORTon(component : Light) extends org.kevoree.framework.MessagePort with KevoreeProvidedPort {
def getName : String = "on"
def getComponentName : String = component.getName 
def process(o : Object) = {this ! o}
override def internal_process(msg : Any)=msg match {
case _ @ msg => component.trigger(msg)
}
}
