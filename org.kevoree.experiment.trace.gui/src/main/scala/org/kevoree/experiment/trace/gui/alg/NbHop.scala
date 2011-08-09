package org.kevoree.experiment.trace.gui.alg

import org.kevoree.experiment.modelScript.KevTopo2JGraph
import scala.collection.JavaConversions._
import org.jgrapht.alg.{BellmanFordShortestPath, FloydWarshallShortestPaths, KShortestPaths}
import org.kevoree.{KevoreeFactory, NodeNetwork, ContainerNode, ContainerRoot}

/**
 * User: ffouquet
 * Date: 08/08/11
 * Time: 10:37
 */

case class NbHop(model: ContainerRoot = KevoreeFactory.eINSTANCE.createContainerRoot(), originNodeName: String = "def") {

  val originNode = model.getNodes.find(node => node.getName == originNodeName).getOrElse(null)
  val shortestPath = new BellmanFordShortestPath[ContainerNode, NodeNetwork](KevTopo2JGraph(model), originNode)

  /*
  model.getNodes.foreach {
    targetnode => {
      if (originNode != targetnode) {
        println(originNode.getName + "->" + targetnode.getName + "=" + shortestPath.getPathEdgeList(targetnode).size())

      }
    }

  }   */


  def getPathSize(targetNodeName: String): Int = {
    val targetNode = model.getNodes.find(node => node.getName == targetNodeName).get
    try {
      shortestPath.getPathEdgeList(targetNode).size()
    } catch {
      case _@e => 1
    }
  }


}

case class DefNbHop() extends NbHop(){
  override def getPathSize(targetNodeName: String) = 1
}