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

  def generatePropagationTimeScript(trace: LinkedTrace, hops: NbHop): Tuple2[HashMap[String, Long], HashMap[String, Long]] = {

    val nodeVersion: HashMap[String, Int] = HashMap[String, Int]()
    val nodeDiff: HashMap[String, Long] = HashMap[String, Long]()
    val networkSize: HashMap[String, Long] = HashMap[String, Long]()

    val firstTime = trace.trace.getTimestamp
    val firstClientID = trace.trace.getClientId
    nodeVersion.put(trace.trace.getClientId, TracePath.stringToVectorClock(trace.trace.getBody).versionForNode(trace.trace.getClientId).get)
    networkSize.put(trace.trace.getClientId, TracePath.stringToVectorClock(trace.trace.getBody).recByteLong)

    def recusiveCall(traces: List[LinkedTrace]) {
      traces.foreach {
        trace =>

        // println(trace.toString)

          if (trace.trace.getClientId != firstClientID) {

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
              // println("u")
            }

          }

          recusiveCall(trace.sucessors)
      }
    }
    recusiveCall(trace.sucessors)

    println(nodeDiff)
    println(nodeDiff.size)



    (nodeDiff, networkSize)
  }


  def generatePropagationTimeScript(traces: List[LinkedTrace], hops: NbHop = DefNbHop()): String = {
    val diff: scala.collection.mutable.HashMap[String, List[Long]] = scala.collection.mutable.HashMap[String, List[Long]]()
    val maxNetworkSize: HashMap[String, Long] = HashMap[String, Long]()

    println("NbReconfDetected=" + traces.size)

    traces.foreach {
      trace => {
        val tuple = generatePropagationTimeScript(trace, hops)

        // println(tuple._1.size)

        tuple._1.foreach {
          t =>
            diff.put(t._1, diff.get(t._1).getOrElse(List()) ++ List(t._2))
        }

        tuple._2.foreach {
          nettuple =>

            maxNetworkSize.put(
             nettuple._1,
             scala.math.max(maxNetworkSize.get(nettuple._1).getOrElse(1000l*1000l),nettuple._2)
            )
        }
      }
    }


    var resultDif = List[Long]()
    var resultDifDivHop = List[Long]()
    var avg = 0l
    diff.foreach {
      dif => {
        dif._2.foreach {
          difres => {
            resultDif = resultDif ++ List(difres)
            resultDifDivHop = resultDifDivHop ++ List(difres / hops.getPathSize(dif._1))
            avg = avg + difres
          }
        }
      }
    }

    if (diff.size > 0) {
      println("avg=" + (avg / resultDif.size))
    }
    maxNetworkSize.foreach{netSize =>
       maxNetworkSize.put(netSize._1,  (netSize._2 / ( (traces.size+(7-2) ) * 1000) )      )
    }



    "delay <- c(" + resultDif.mkString(",") + ")\n" +
    "delayDivHops <- c(" + resultDifDivHop.mkString(",") + ")\n" +
      "netSize <- c(" + maxNetworkSize.values /*.slice(1, netSize.size + 1)*/ .mkString(",") + ")\n" +
      "\n" + scriptEnd
  }


  def generateFile(content: String, name: String) {
    val fwr = new FileWriter(new File(name))
    fwr.write(content)
    fwr.close()
  }


}