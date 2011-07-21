package org.kevoree.experiment.library.gossiperNetty

import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import org.kevoree.library.gossiperNetty.api.msg.KevoreeMessage.Message
import java.net.InetSocketAddress
import org.kevoree.library.gossiperNetty.{GossiperRequestReceiver, GossiperRequestReceiverHandler}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 21/07/11
 * Time: 17:32
 */

class LogGossiperRequestReceiverHandler(serverActor: GossiperRequestReceiver) extends GossiperRequestReceiverHandler(serverActor) {

  override def messageReceived (ctx: ChannelHandlerContext, e: MessageEvent) {
    val message = e.getMessage.asInstanceOf[Message]
    NetworkCommunicationCost.updateDataSizeReceived(message.getSerializedSize)
    serverActor.sendReply (message, e.getRemoteAddress.asInstanceOf[InetSocketAddress], e.getChannel)
  }

}