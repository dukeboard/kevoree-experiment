package org.kevoree.experiment.library.gossiperNetty

import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import org.kevoree.library.gossiperNetty.api.msg.KevoreeMessage.Message
import java.net.InetSocketAddress
import org.kevoree.library.gossiperNetty.version.Gossip.{VersionedModel, VectorClockUUIDs}
import org.kevoree.library.gossiperNetty.{GossiperRequestSender, GossiperRequestSenderHandler}
import org.slf4j.LoggerFactory

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 21/07/11
 * Time: 17:32
 */

class LogGossiperRequestSenderHandler (gossiperRequestSender: GossiperRequestSender)
  extends GossiperRequestSenderHandler(gossiperRequestSender) {

  private val logger = LoggerFactory.getLogger(classOf[LogGossiperRequestReceiverHandler])
  override def messageReceived (ctx: ChannelHandlerContext, e: MessageEvent) {
    val message = e.getMessage.asInstanceOf[Message]
    if (message.getContentClass.equals(classOf[VectorClockUUIDs].getName)) {
      NetworkCommunicationCost.updateDataSizeReceived(message.getSerializedSize)
      logger.info("Received result asynchronously for VectorClockUUIDs: " + message.getSerializedSize)
      gossiperRequestSender
        .initSecondStepAction(message, e.getRemoteAddress.asInstanceOf[InetSocketAddress] /*, e.getChannel*/)
    } /* else if (message.getContentClass.equals(classOf[VectorClockUUID].getName)) {
	  //var vectorClockUUID = RichString(message.getContent.toStringUtf8).fromJSON(classOf[VectorClockUUID])
	  gossiperRequestSender.initLastStepAction(message, e.getRemoteAddress, e.getChannel)
	}*/
    else if (message.getContentClass.equals(classOf[VersionedModel].getName)) {
      NetworkCommunicationCost.updateDataSizeReceived(message.getSerializedSize)
      logger.info("Received result asynchronously for VersionedModel: " + message.getSerializedSize)
      gossiperRequestSender.endGossipAction(message)
      //e.getChannel.close.awaitUninterruptibly
    }
  }
}