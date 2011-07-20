package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object GossipRFFactory{
def createChannel()={new org.kevoree.experiment.smartbuilding.GossipRF with ChannelTypeFragment {
override def startChannelFragment(){this.asInstanceOf[org.kevoree.experiment.smartbuilding.GossipRF].lifeCycle()}
override def stopChannelFragment(){this.asInstanceOf[org.kevoree.experiment.smartbuilding.GossipRF].lifeCycle()}
}}}
