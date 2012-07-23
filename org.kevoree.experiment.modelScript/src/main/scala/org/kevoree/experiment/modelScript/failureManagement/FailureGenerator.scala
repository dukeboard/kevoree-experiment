package org.kevoree.experiment.modelScript.failureManagement

import org.eclipse.emf.ecore.util.EcoreUtil

import scala.collection.JavaConversions._
import org.kevoree.experiment.modelScript.Kev2GraphML
import org.kevoree.framework.{KevoreePlatformHelper, KevoreeXmiHelper}
import java.net.URL
import java.io.{InputStreamReader, BufferedReader}
import org.kevoree.{NodeNetwork, ContainerRoot}
import collection.mutable.Buffer
import java.lang.Math

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 08/08/11
 * Time: 17:52
 */

class FailureGenerator(model : ContainerRoot) {

  var removedNodeNetworks: List[NodeNetwork] = List()

  def reinitialize () {
    var urls = List[String]()
    removedNodeNetworks.foreach {
      nn =>
        sendOrder(buildBaseURL(model, nn.getInitBy.getName) + "?up=" + nn.getTarget.getName)
        sendOrder(buildBaseURL(model, nn.getTarget.getName) + "?up=" + nn.getInitBy.getName)
      urls = urls ++ List (buildBaseURL(model, nn.getInitBy.getName), buildBaseURL(model, nn.getTarget.getName))
    }
    urls.foreach {
      u => sendOrder(u + "?reset=true")
    }
    removedNodeNetworks = List()
  }

  def generateFailure (lambda: Int): ContainerRoot = {

    val localModel = EcoreUtil.copy(model)

    var nbFailure: Int = ((lambda / 100d) * localModel.getNodeNetworks.size()).asInstanceOf[Int]

    if (nbFailure % 2 != 0) {
      nbFailure = nbFailure + 1
    }
    println("\nfailures: ")
    for (i <- 0 until nbFailure/2) {
      val elementIndex: Int = (Math.random() * localModel.getNodeNetworks.size).asInstanceOf[Int]
      val nodeNetwork = localModel.getNodeNetworks.remove(elementIndex)
      removedNodeNetworks = removedNodeNetworks ++ List(nodeNetwork)
      println(nodeNetwork.getInitBy.getName + " -> " + nodeNetwork.getTarget.getName)
      sendOrder(buildBaseURL(localModel, nodeNetwork.getInitBy.getName) + "?down=" + nodeNetwork.getTarget.getName)
      sendOrder(buildBaseURL(localModel, nodeNetwork.getTarget.getName) + "?down=" + nodeNetwork.getInitBy.getName)
      localModel.getNodeNetworks.filter(n => n.getInitBy.getName.equals(nodeNetwork.getTarget.getName) &&
        n.getTarget.getName.equals(nodeNetwork.getInitBy.getName)) match {
        case Buffer(nn1: NodeNetwork) => {
          localModel.getNodeNetworks.remove(nn1)
          println(nn1.getInitBy.getName + " -> " + nn1.getTarget.getName)
          removedNodeNetworks = removedNodeNetworks ++ List(nn1)
        }
      }
    }
    save(localModel, lambda)
    localModel
  }

  def save (model: ContainerRoot, lambda: Int) {
    val fileNamePrefix = "model" + lambda
    saveModel(fileNamePrefix + ".kev", model)
    saveTopology(fileNamePrefix + ".graphml", model)
  }

  def saveModel (fileName: String, model: ContainerRoot) {
    KevoreeXmiHelper.save(fileName, model)
  }

  def saveTopology (fileName: String, model: ContainerRoot) {
    Kev2GraphML.toGraphMLFile(fileName, model)
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