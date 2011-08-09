package org.kevoree.experiment.library.gossiperNetty

import scala.collection.JavaConversions._
import java.net.{InetAddress, InetSocketAddress}
import org.slf4j.LoggerFactory
import org.kevoree.library.gossiperNetty._
import api.msg.KevoreeMessage.Message
import java.util.concurrent.Executors
import org.jboss.netty.channel.{Channels, ChannelPipeline, ChannelPipelineFactory, Channel}
import org.jboss.netty.handler.codec.protobuf.{ProtobufEncoder, ProtobufVarint32LengthFieldPrepender, ProtobufDecoder, ProtobufVarint32FrameDecoder}
import org.jboss.netty.channel.socket.nio.{NioServerSocketChannelFactory, NioDatagramChannelFactory}
import org.jboss.netty.bootstrap.{ServerBootstrap, ConnectionlessBootstrap}
import org.jboss.netty.handler.codec.compression.{ZlibEncoder, ZlibWrapper, ZlibDecoder}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 20/05/11
 * Time: 14:14
 */

class LogGossiperRequestReceiver (channelFragment: NettyGossipAbstractElement, dataManager: DataManager, port: Int,
  gossiperRequestSender: GossiperRequestSender, fullUDP: java.lang.Boolean, serializer: Serializer, peerSelector : StrictGroupPeerSelector)
  extends GossiperRequestReceiver(channelFragment, dataManager, port, gossiperRequestSender, fullUDP, serializer) {

  private val logger = LoggerFactory.getLogger(classOf[LogGossiperRequestReceiver])

  self = this
  // define attributes used to define channel to listen request
  factoryForRequest = new NioDatagramChannelFactory(Executors.newCachedThreadPool())
  bootstrapForRequest = new ConnectionlessBootstrap(factoryForRequest)
  bootstrapForRequest.setPipelineFactory(new ChannelPipelineFactory() {
    override def getPipeline: ChannelPipeline = {
      val p: ChannelPipeline = Channels.pipeline()
      //p.addLast("inflater", new ZlibDecoder(ZlibWrapper.ZLIB))
      p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder)
      p.addLast("protobufDecoder", new ProtobufDecoder(Message.getDefaultInstance))
      //p.addLast("deflater", new ZlibEncoder(ZlibWrapper.ZLIB))
      p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender)
      p.addLast("protobufEncoder", new ProtobufEncoder)
      p.addLast("handler", new LogGossiperRequestReceiverHandler(self))
      p
    }
  })

  factoryForRequestTCP = new
      NioServerSocketChannelFactory(Executors.newCachedThreadPool(), Executors.newCachedThreadPool())
  bootstrapForRequestTCP = new ServerBootstrap(factoryForRequestTCP)
  bootstrapForRequestTCP.setPipelineFactory(new ChannelPipelineFactory() {
    override def getPipeline: ChannelPipeline = {
      val p: ChannelPipeline = Channels.pipeline()
      p.addLast("inflater", new ZlibDecoder(ZlibWrapper.ZLIB))
      p.addLast("frameDecoder", new ProtobufVarint32FrameDecoder)
      p.addLast("protobufDecoder", new ProtobufDecoder(Message.getDefaultInstance))
      p.addLast("deflater", new ZlibEncoder(ZlibWrapper.ZLIB))
      p.addLast("frameEncoder", new ProtobufVarint32LengthFieldPrepender)
      p.addLast("protobufEncoder", new ProtobufEncoder)
      p.addLast("handler", new LogDataSenderHandler(channelFragment, dataManager, serializer))
      p
    }
  })
  bootstrapForRequestTCP.setOption("tcpNoDelay", true)

  /* PUBLIC PART */
  override def start () = {
    super.start()
    /*channel = bootstrapForRequest.bind(new InetSocketAddress(port)) //.asInstanceOf[DatagramChannel]
    channelTCP = bootstrapForRequestTCP.bind(new InetSocketAddress(port))*/
    this
  }

  override protected def writeMessage (o: Object, address: InetSocketAddress, channel: Channel) {
    var networkIsDown = false
    var targetNodeName = ""
    FailureSimulation.failureOutNode.foreach {
      nodeName =>
        logger.debug(nodeName + " is one of the node available from here")
        if (channelFragment.parsePortNumber(nodeName).equals(address.getPort) &&
          isEquals(channelFragment.getAddress(nodeName), address.getAddress)) {
          networkIsDown = true
          targetNodeName = nodeName
          logger.debug("the message won't be sent because the node is register as a node failure")
        }
    }
    if (!networkIsDown) {
      logger.debug("message is sent by LogRequestReceiver.")
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