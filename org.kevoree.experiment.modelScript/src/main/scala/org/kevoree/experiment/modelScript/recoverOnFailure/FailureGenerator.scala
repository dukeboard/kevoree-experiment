package org.kevoree.experiment.modelScript.recoverOnFailure

import java.lang.Math
import scala.collection.JavaConversions._
import java.net.URL
import java.io.{InputStreamReader, BufferedReader}
import org.kevoree.framework.{KevoreeXmiHelper, KevoreePlatformHelper}
import org.eclipse.emf.ecore.util.EcoreUtil
import org.kevoree.{ContainerNode, NodeNetwork, ContainerRoot}
import scala.Predef._
import org.kevoree.experiment.modelScript.Kev2GraphML
import collection.generic.GenSeqFactory
import collection.mutable.{Buffer, ArrayBuffer}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 28/05/11
 * Time: 13:59
 */

class FailureGenerator (model: ContainerRoot) {

  var removedNodeNetworks: List[NodeNetwork] = List()

  var ip: String = null
  var port: Int = 0

  def reinitialize () {
    var urls = List[String]()
    removedNodeNetworks.foreach {
      nn =>
        sendOrder(buildBaseURL(model, nn.getInitBy.getName) + "?up=" + nn.getTarget.getName)
        sendOrder(buildBaseURL(model, nn.getTarget.getName) + "?up=" + nn.getInitBy.getName)
        urls = urls ++ List(buildBaseURL(model, nn.getInitBy.getName), buildBaseURL(model, nn.getTarget.getName))
    }
    /*urls.foreach {
      u => sendOrder(u + "?reset=true")
    }*/
    removedNodeNetworks = List()
  }

  def generateFailure (followingPlatform: String): ContainerRoot = {

    val localModel = EcoreUtil.copy(model)

    localModel.getNodeNetworks
      .filter(nn => nn.getInitBy.getName.equals(followingPlatform) && !nn.getTarget.getName.contains(followingPlatform.substring(0, followingPlatform.length() - 1)) /*|| nn.getTarget.getName.equals(followingPlatform)*/)
      .foreach {
      nn =>
        localModel.getNodeNetworks.remove(nn)
        println(nn.getInitBy.getName + " -> " + nn.getTarget.getName)
        sendOrder(buildBaseURL(localModel, nn.getInitBy.getName) + "?down=" + nn.getTarget.getName)
        sendOrder(buildBaseURL(localModel, nn.getTarget.getName) + "?down=" + nn.getInitBy.getName)
        removedNodeNetworks = removedNodeNetworks ++ List(nn)
        localModel.getNodeNetworks.filter(n => n.getInitBy.getName.equals(nn.getTarget.getName) &&
          n.getTarget.getName.equals(nn.getInitBy.getName)) match {
          case Buffer(nn1: NodeNetwork) => {
            localModel.getNodeNetworks.remove(nn1)
            println(nn1.getInitBy.getName + " -> " + nn1.getTarget.getName)
            removedNodeNetworks = removedNodeNetworks ++ List(nn1)
          }
        }
    }
    save(localModel, "_without_" + followingPlatform)
    localModel
  }

  def save (model: ContainerRoot, subfix: String) {
    val fileNamePrefix = "model" + subfix
    saveModel(fileNamePrefix + ".kev", model)
    saveTopology(fileNamePrefix + ".graphml", model)
  }

  def updateModelAccordingToRemovedNodeNetworks (model: ContainerRoot): ContainerRoot = {
    removedNodeNetworks.foreach {
      nn =>
        model.getNodeNetworks.find(n => n.getInitBy.getName.equals(nn.getInitBy.getName) &&
          n.getTarget.getName.equals(nn.getTarget.getName)) match {
          case Some(nodeNetwork) => model.getNodeNetworks.remove(nodeNetwork)
          case _ => println("removed node network doesn't exist")
        }

    }
    model
  }


  /*def loadCurrentModel (): ContainerRoot = {
    port = 8000
    ip = selectRandomlyIntoList(ips).asInstanceOf[String]

    val url = "http://" + ip + ":" + port + "/model/current"

    KevoreeXmiHelper.load(url)
  }*/

  def save (model: ContainerRoot) {
    val fileNamePrefix = "model" + System.currentTimeMillis()
    saveModel(fileNamePrefix + ".kev", model)
    saveTopology(fileNamePrefix + ".graphml", model)
  }

  def saveModel (fileName: String, model: ContainerRoot) {
    KevoreeXmiHelper.save(fileName, model)
  }

  def saveTopology (fileName: String, model: ContainerRoot) {
    Kev2GraphML.toGraphMLFile(fileName, model)
  }

  private def selectRandomlyIntoList[A] (elements: List[A]): A = {
    val i: Int = (Math.random() * elements.size).asInstanceOf[Int]
    elements(i)
  }

  def buildBaseURL (model: ContainerRoot, nodeName: String): String = {
    var ip: String = KevoreePlatformHelper
      .getProperty(model, nodeName, org.kevoree.framework.Constants.KEVOREE_PLATFORM_REMOTE_NODE_IP)
    if (ip == null || (ip == "")) {
      ip = "127.0.0.1"
    }
    var port: String = KevoreePlatformHelper
      .getProperty(model, nodeName, org.kevoree.framework.Constants.KEVOREE_PLATFORM_REMOTE_NODE_MODELSYNCH_PORT)
    if (port == null || (port == "")) {
      port = "8000"
    }
    "http://" + ip + ":" + port.replace("8", "11")
  }

  def sendOrder (urlString: String) {
    val url = new URL(urlString);
    val conn = url.openConnection();
    conn.setConnectTimeout(2000);
    // Get the response
    val rd = new BufferedReader(new InputStreamReader(conn.getInputStream));
    var line: String = rd.readLine;
    while (line != null) {
      line = rd.readLine
    }
    rd.close();
  }
}