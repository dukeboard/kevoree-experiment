package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object SerialComFactory{
def createChannel()={new org.kevoree.experiment.smartbuilding.SerialCom with ChannelTypeFragment {
override def startChannelFragment(){this.asInstanceOf[org.kevoree.experiment.smartbuilding.SerialCom].lifeCycle()}
override def stopChannelFragment(){this.asInstanceOf[org.kevoree.experiment.smartbuilding.SerialCom].lifeCycle()}
}}}
