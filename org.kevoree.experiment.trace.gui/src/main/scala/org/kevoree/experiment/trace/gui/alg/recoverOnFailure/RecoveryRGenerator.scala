package org.kevoree.experiment.trace.gui.alg.recoverOnFailure

import org.kevoree.experiment.trace.gui.alg.LinkedTrace
import org.kevoree.experiment.trace.TraceMessages

/**
 * User: ffouquet
 * Date: 11/08/11
 * Time: 10:42
 */

object RecoveryRGenerator {

  def generateLinkedGraph(ltraces: List[LinkedTrace], beginOfTime: Long, colors: List[String], pchs: List[String]): String = {
    val result = new StringBuilder
    ltraces.foreach {
      trace => populateNodeId(trace)
    }
    ltraces.foreach {
      trace =>
        result.append(recursiveGeneration(
          trace,
          beginOfTime,
          colors(ltraces.indexOf(trace)),
          pchs(ltraces.indexOf(trace))
        ))
    }
    "nullSet <- c()\n" +
      "plot(nullSet,nullSet,xlim=c(0," + maxTime + "),ylim=c(0," + nodesl.size + "),axes=FALSE,ann=FALSE)\n" +
      "" + result.toString() + "box()\n" +
      "axis(2,at=(0:" + (nodesl.size - 1) + "),las=1,lab=c(\"" + nodesl.mkString("\",\"") + "\"))\n" +
      "axis(1)\n" +
      "title(\"Concurrent reconfiguration reconciliation experiment\")\n" +
      "mtext(\"Absolute time (ms)\", side=1, line=3)\n" +
      "mtext(\"Nodes name\", side=2, line=3)\n" +
      ""
  }

  var maxTime = 0l

  def getTime(trace: TraceMessages.Trace, beginOfTime: Long): String = {
    val nt = ((trace.getTimestamp - beginOfTime) / 1000000)
    maxTime = scala.math.max(maxTime, nt)
    nt.toString
  }


  private def recursiveGeneration(l: LinkedTrace, beginOfTime: Long, color: String, pch: String): String = {
    val result = new StringBuilder
    l.sucessors.foreach {
      suc =>
        result.append("points(type=\"o\",pch="+pch+",col=\"" + color + "\",")
        result.append("c(" + List(nodesl.indexOf(l.trace.getClientId), nodesl.indexOf(suc.trace.getClientId)).mkString(",") + ")")
        result.append("~")
        result.append("c(" + List(getTime(l.trace, beginOfTime), getTime(suc.trace, beginOfTime)).mkString(",") + ")")
        result.append(")\n")
        result.append(recursiveGeneration(suc, beginOfTime, color, pch))
    }
    result.toString()
  }


  var nodes = Set[String]()
  var nodesl = nodes.toList

  def populateNodeId(l: LinkedTrace) {
    nodes += (l.trace.getClientId)
    l.sucessors.foreach(suc => populateNodeId(suc))
    nodesl = nodes.toList.sortWith((a, b) => a < b)
  }


}