package org.kevoree.experiment.smartbuilding
import org.kevoree.framework._
object IPMultiCastFactory{
def createChannel()={new org.kevoree.experiment.smartbuilding.IPMultiCast with ChannelTypeFragment {
override def startChannelFragment(){this.asInstanceOf[org.kevoree.experiment.smartbuilding.IPMultiCast].lifeCycle()}
override def stopChannelFragment(){this.asInstanceOf[org.kevoree.experiment.smartbuilding.IPMultiCast].lifeCycle()}
}}}
