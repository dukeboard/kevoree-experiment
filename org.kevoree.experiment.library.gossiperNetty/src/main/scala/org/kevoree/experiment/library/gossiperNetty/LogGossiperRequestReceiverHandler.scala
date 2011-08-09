package org.kevoree.experiment.library.gossiperNetty

import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import org.kevoree.library.gossiperNetty.api.msg.KevoreeMessage.Message
import java.net.InetSocketAddress
import org.kevoree.library.gossiperNetty.{GossiperRequestReceiver, GossiperRequestReceiverHandler}
import org.slf4j.LoggerFactory

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 21/07/11
 * Time: 17:32
 */

class LogGossiperRequestReceiverHandler (serverActor: GossiperRequestReceiver)
  extends GossiperRequestReceiverHandler(serverActor) {

  private val logger = LoggerFactory.getLogger(classOf[LogGossiperRequestReceiverHandler])

  override def messageReceived (ctx: ChannelHandlerContext, e: MessageEvent) {
    val message = e.getMessage.asInstanceOf[Message]
    NetworkCommunicationCost.updateDataSizeReceived(message.getSerializedSize)
    logger.info("Received result asynchronously as " + message.getContentClass + ": " + message.getSerializedSize)
    serverActor.sendReply(message, e.getRemoteAddress.asInstanceOf[InetSocketAddress], e.getChannel)
  }

}