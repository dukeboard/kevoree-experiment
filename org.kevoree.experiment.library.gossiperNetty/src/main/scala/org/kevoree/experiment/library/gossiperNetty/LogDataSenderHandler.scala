package org.kevoree.experiment.library.gossiperNetty

import org.jboss.netty.channel.{MessageEvent, ChannelHandlerContext}
import org.kevoree.library.gossiperNetty.api.msg.KevoreeMessage.Message
import org.kevoree.library.gossiperNetty.version.Gossip
import java.util.UUID
import com.google.protobuf.ByteString
import org.kevoree.library.gossiperNetty.{DataSenderHandler, Serializer, DataManager, NettyGossipAbstractElement}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 21/07/11
 * Time: 17:31
 */

class LogDataSenderHandler(channelFragment: NettyGossipAbstractElement, dataManager: DataManager, serializer : Serializer) extends DataSenderHandler(channelFragment, dataManager, serializer) {

  override def messageReceived(ctx: ChannelHandlerContext, e: MessageEvent) {
		//println("message received")
		val message = e.getMessage.asInstanceOf[Message]
		if (message.getContentClass.equals(classOf[Gossip.UUIDDataRequest].getName)) {
      NetworkCommunicationCost.updateDataSizeReceived(message.getSerializedSize)
			val uuidDataRequest = Gossip.UUIDDataRequest.parseFrom(message.getContent)
			val data = dataManager.getData(UUID.fromString(uuidDataRequest.getUuid))

			val modelBytes = ByteString.copyFrom(serializer.serialize(data._2))

			val modelBytes2 = Gossip.VersionedModel.newBuilder.setUuid(uuidDataRequest.getUuid).setVector(data._1).setModel(modelBytes).build.toByteString
			val responseBuilder: Message.Builder = Message.newBuilder.setDestName(message.getDestName).setDestNodeName(channelFragment.getNodeName)
			responseBuilder.setContentClass(classOf[Gossip.VersionedModel].getName).setContent(modelBytes2)

			//e.getChannel.write(responseBuilder.build)
			e.getChannel.write(responseBuilder.build)
			//println("response sent")
		}
	}
}