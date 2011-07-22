package org.kevoree.experiment.trace.gui.alg

import java.io.{File, FileWriter}
import collection.mutable.HashMap

/**
 * User: ffouquet
 * Date: 18/07/11
 * Time: 09:07
 */

object RGenerator {

  val scriptEnd = "\nlibrary(Hmisc)\nbpplot(propDelais,main=\"Downtime propagation delay\")\nboxplot(propDelais, propDelais, names = c(\"a\", \"b\"), horizontal = TRUE, ylab = \"factors\", main = \"title\")"

  def generatePropagationTimeScript(trace: LinkedTrace): Tuple2[List[Long],HashMap[String, Long]] = {

    val nodeVersion: HashMap[String, Int] = HashMap[String, Int]()
    val nodeDiff: HashMap[String, Long] = HashMap[String, Long]()
    val networkSize: HashMap[String, Long] = HashMap[String, Long]()

    val firstTime = trace.trace.getTimestamp
    val firstClientID = trace.trace.getClientId
    nodeVersion.put(trace.trace.getClientId, TracePath.stringToVectorClock(trace.trace.getBody).versionForNode(trace.trace.getClientId).get)
    nodeDiff.put(trace.trace.getClientId, 0)
    networkSize.put(trace.trace.getClientId, TracePath.stringToVectorClock(trace.trace.getBody).recByteLong)

    def recusiveCall(traces: List[LinkedTrace]) {
      traces.foreach {
        trace =>

          nodeVersion.get(trace.trace.getClientId) match {
            case Some(nVersion) if (nVersion > TracePath.stringToVectorClock(trace.trace.getBody).versionForNode(firstClientID).get) => {
              nodeVersion.put(trace.trace.getClientId, TracePath.stringToVectorClock(trace.trace.getBody).versionForNode(firstClientID).get)
              val mili = ((trace.trace.getTimestamp - firstTime) / 1000000)
              nodeDiff.put(trace.trace.getClientId, mili)
              networkSize.put(trace.trace.getClientId, TracePath.stringToVectorClock(trace.trace.getBody).recByteLong)
            }

            case None => {
              nodeVersion.put(trace.trace.getClientId, TracePath.stringToVectorClock(trace.trace.getBody).versionForNode(firstClientID).get)
              val mili = ((trace.trace.getTimestamp - firstTime) / 1000000)
              nodeDiff.put(trace.trace.getClientId, mili)
              networkSize.put(trace.trace.getClientId, TracePath.stringToVectorClock(trace.trace.getBody).recByteLong)
            }

            case _ =>
          }

          recusiveCall(trace.sucessors)
      }
    }
    recusiveCall(trace.sucessors)
    (nodeDiff.values.toList,networkSize)
  }


  def generatePropagationTimeScript(traces: List[LinkedTrace]): String = {
    var diff: List[Long] = List()
    var previousNetworkSize: HashMap[String, Long] = HashMap[String, Long]()
    var netSize: List[Long] = List()
    traces.foreach {
      trace => {
        val tuple = generatePropagationTimeScript(trace)
        diff = diff ++ tuple._1
        var lnetSize = 0l
        tuple._2.foreach{ nettuple =>
          previousNetworkSize.get(nettuple._1) match {
            case Some(previousSize)=> lnetSize = lnetSize + ( nettuple._2 - previousSize )
            case None => lnetSize = lnetSize + nettuple._2
          }
        }
        netSize = netSize ++ List(lnetSize)
        previousNetworkSize = tuple._2
      }
    }

    var l = 0l
    diff.foreach {
      dif => l = l + dif
    }
    println("avg=" + (l / diff.size))

    "propDelais <- c(" + diff.mkString(",") + ")\n" +
    "netSize <- c(" + netSize.slice(1,netSize.size+1).mkString(",") + ")\n" +
      "\n" + scriptEnd
  }


  def generateFile(content: String, name: String) {
    val fwr = new FileWriter(new File(name))
    fwr.write(content)
    fwr.close()
  }


}