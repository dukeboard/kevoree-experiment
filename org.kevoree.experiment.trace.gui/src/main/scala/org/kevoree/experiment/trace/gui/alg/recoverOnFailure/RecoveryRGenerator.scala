package org.kevoree.experiment.trace.gui.alg.recoverOnFailure

import org.kevoree.experiment.trace.gui.alg.LinkedTrace

/**
 * User: ffouquet
 * Date: 11/08/11
 * Time: 10:42
 */

object RecoveryRGenerator {

  def generateLinkedGraph(ltraces: List[LinkedTrace], beginOfTime: Long): String = {
    val result = new StringBuilder
    ltraces.foreach { trace => populateNodeId(trace) }
    println(nodesl.mkString(","))

    result.toString()
  }


  private def recursiveGeneration(l: LinkedTrace, beginOfTime: Long): String = {
    ""
  }

  var nodes = Set[String]()
  var nodesl = nodes.toList
  def populateNodeId(l: LinkedTrace) {
    nodes += (l.trace.getClientId)
    l.sucessors.foreach(suc => populateNodeId(suc))
    nodesl = nodes.toList.sortWith( (a, b) => a < b )
  }



}