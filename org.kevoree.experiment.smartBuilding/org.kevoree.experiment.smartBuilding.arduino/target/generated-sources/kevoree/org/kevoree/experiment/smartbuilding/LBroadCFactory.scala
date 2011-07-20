package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object LBroadCFactory{
def createChannel()={new org.kevoree.experiment.smartbuilding.LBroadC with ChannelTypeFragment {
override def startChannelFragment(){this.asInstanceOf[org.kevoree.experiment.smartbuilding.LBroadC].lifeCycle()}
override def stopChannelFragment(){this.asInstanceOf[org.kevoree.experiment.smartbuilding.LBroadC].lifeCycle()}
}}}
