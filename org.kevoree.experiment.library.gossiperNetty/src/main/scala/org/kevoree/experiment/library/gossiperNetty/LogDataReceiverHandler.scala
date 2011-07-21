package org.kevoree.experiment.library.gossiperNetty

import org.kevoree.library.gossiperNetty.{DataReceiverHandler, GossiperRequestSender}
import org.kevoree.library.gossiperNetty.api.msg.KevoreeMessage.Message
import org.kevoree.library.gossiperNetty.version.Gossip.VersionedModel
import org.jboss.netty.channel.{ChannelFutureListener, MessageEvent, ChannelHandlerContext}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 21/07/11
 * Time: 17:31
 */

class LogDataReceiverHandler(gossiperRequestSender: GossiperRequestSender) extends DataReceiverHandler(gossiperRequestSender) {

  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
		//println("response received")
		val message = e.getMessage.asInstanceOf[Message]
		if (message.getContentClass.equals(classOf[VersionedModel].getName)) {
      NetworkCommunicationCost.updateDataSizeReceived(message.getSerializedSize)
			gossiperRequestSender.endGossipAction(message)
			//e.getChannel.getCloseFuture.awaitUninterruptibly
			e.getChannel.getCloseFuture.addListener(ChannelFutureListener.CLOSE);
		}
	}
}