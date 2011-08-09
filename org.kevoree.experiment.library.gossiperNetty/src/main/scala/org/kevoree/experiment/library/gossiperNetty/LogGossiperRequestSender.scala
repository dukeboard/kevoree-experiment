package org.kevoree.experiment.library.gossiperNetty

import scala.collection.JavaConversions._
import java.net.{InetAddress, InetSocketAddress}
import org.slf4j.LoggerFactory
import org.kevoree.library.gossiperNetty._
import api.msg.KevoreeMessage.Message
import org.jboss.netty.channel.{Channels, ChannelPipeline, ChannelPipelineFactory}
import org.jboss.netty.handler.codec.protobuf.{ProtobufEncoder, ProtobufVarint32LengthFieldPrepender, ProtobufDecoder, ProtobufVarint32FrameDecoder}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 20/05/11
 * Time: 14:14
 */

class LogGossiperRequestSender (timeout: java.lang.Long, channelFragment: NettyGossipAbstractElement,
  dataManager: DataManager, fullUDP: java.lang.Boolean, garbage: Boolean, serializer: Serializer,
  alwaysAskModel: Boolean, peerSelector : StrictGroupPeerSelector) extends GossiperRequestSender(timeout, channelFragment,
                                                          dataManager, fullUDP, garbage, serializer, alwaysAskModel) {
  private val logger = LoggerFactory.getLogger(classOf[LogGossiperRequestSender])

  bootstrap.setPipelineFactory(new ChannelPipelineFactory() { // TODO refactor with an object for all pipelineFactories
    override def getPipeline: ChannelPipeline = {
      val p: ChannelPipeline = Channels.pipeline()
      //p.addLast("inflater", new ZlibDecoder(ZlibWrapper.ZLIB))
      p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder)
      p.addLast("protobufDecoder", new ProtobufDecoder(Message.getDefaultInstance))
      //p.addLast("deflater", new ZlibEncoder(ZlibWrapper.ZLIB))
      p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender())
      p.addLast("protobufEncoder", new ProtobufEncoder())

      p.addLast("handler", new LogGossiperRequestSenderHandler(self))
      p
    }
  })

  askForDataTCPActor = new LogAskForDataTCPActor(channelFragment, this, peerSelector)

  override def start () = {
    /*channel = bootstrap.bind(new InetSocketAddress(0)) //.asInstanceOf[DatagramChannel]
    askForDataTCPActor.start()*/
    super.start()
    this
  }

  override protected def writeMessage (o: Object, address: InetSocketAddress) {
    logger.debug("before to send message, we need to test if the node is available")
    var networkIsDown = false
    var targetNodeName = ""
    FailureSimulation.failureOutNode.foreach {
      nodeName =>
        logger.debug(nodeName + " is one of the node available from here but the communication link is down")
        if (channelFragment.parsePortNumber(nodeName).equals(address.getPort) &&
          isEquals(channelFragment.getAddress(nodeName), address.getAddress)) {
          networkIsDown = true
          targetNodeName = nodeName
          logger.debug("the message won't be sent because the node is register as a node failure")
        }
    }
    if (!networkIsDown) {
      logger.debug("message is sent by LogRequestSender.")
      channel.write(o, address)
      peerSelector.resetNodeFailureAction(targetNodeName)
    } else {
      logger.debug("message is not sent because the link with " + targetNodeName + " is broken")
      peerSelector.modifyNodeScoreAction(targetNodeName, true)
    }
  }

  private def isEquals (address: String, inetAddress: InetAddress): Boolean = {
    address.equals(inetAddress.getHostName) ||
      address.equals(inetAddress.getHostAddress)
  }
}