package org.kevoree.experiment.trace.gui.alg

import org.kevoree.experiment.trace.TraceMessages.{Trace}
import java.util.Calendar
import java.lang.Boolean

case class LinkedTrace (trace: Trace, sucessors: List[LinkedTrace]) {

  val lineSeparator = System.getProperty("line.separator").toString

  override def toString: java.lang.String = {
    toString(0, trace.getTimestamp)
  }

  def rcontainsNodeId(nodeId : String) : Boolean = {
     trace.getClientId == nodeId || (sucessors.exists(suc => suc.rcontainsNodeId(nodeId)))
  }


  def toString (indice: Int, beginTime: Long): String = {
    val result = new StringBuffer

    for (i <- 0 until indice) {
      result append " "
    }
    result.append(trace.getClientId)
    result.append("[")
    result.append(TracePath.stringToVectorClock(trace.getBody).toString)
    result.append("]")
    result.append("")


    val timeStampMilli = ((trace.getTimestamp-beginTime) * ( 0.000001) )
    result.append(timeStampMilli)
    result.append("=>" + sucessors.size)
    result.append(lineSeparator)
    sucessors.foreach {
      successor =>
        result.append(successor.toString(indice + 1, beginTime))
    }
    result.toString
  }





}
