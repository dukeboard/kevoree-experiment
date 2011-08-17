package org.kevoree.experiment.modelScript.failureManagement

/**
 * User: ffouquet
 * Date: 04/08/11
 * Time: 15:15
 */

import scala.collection.JavaConversions._
import org.jgrapht.graph.DefaultDirectedGraph
import org.kevoree._


case class KevTopo2JGraph(model: ContainerRoot) extends DefaultDirectedGraph[ContainerNode, NodeNetwork](classOf[NodeNetwork]) {
  model.getNodes.foreach {
    node => addVertex(node)
  }
  model.getNodeNetworks.foreach { nodeNetwork =>
     addEdge(nodeNetwork.getInitBy,nodeNetwork.getTarget,nodeNetwork)
  }


}
