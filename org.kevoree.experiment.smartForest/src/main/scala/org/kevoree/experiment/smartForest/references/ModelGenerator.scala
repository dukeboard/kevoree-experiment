package org.kevoree.experiment.smartForest.references

import java.net.{URLDecoder, URL}
import org.kevoree.tools.marShell.parser.KevsParser
import org.kevoree.tools.marShell.interpreter.KevsInterpreterContext
import org.kevoree.tools.marShell.interpreter.KevsInterpreterAspects._
import org.kevoree.experiment.smartForest.SmartForestExperiment

import org.kevoree.experiment.smartForest.dpa.{PeriodValues, ChangePeriodPropertyDPAO}
import java.lang.Math
import org.kevoree.ContainerRoot
import org.kevoree.framework.KevoreeXmiHelper

/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 17:23
 */

object ModelGenerator {

  val parser: KevsParser = new KevsParser
  var superNodesIndices = List((0, 0), (0, SmartForestExperiment.forestWidth - 1), (SmartForestExperiment.forestWidth - 1, 0), (SmartForestExperiment.forestWidth - 1, SmartForestExperiment.forestWidth - 1))


  def generateForest(forestWidth: Int): ContainerRoot = {
    superNodesIndices = List((0, 0), (0, forestWidth - 1), (forestWidth - 1, 0), (forestWidth - 1, forestWidth - 1))
    val myModel: ContainerRoot = KevoreeXmiHelper.loadStream(this.getClass.getClassLoader.getResourceAsStream("defaultLibrary.kev"));

    for (i <- 0 until (forestWidth * forestWidth)) {
      val scriptString: String = "tblock {\n addNode node" + i + ":ArduinoNode \n " +
        "addComponent temperature" + i + "@node" + i + " : TempSensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"}" +
        "addComponent humidity" + i + "@node" + i + " : HumiditySensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"}" +
        "addComponent smoke" + i + "@node" + i + " : SmokeSensor { " + ChangePeriodPropertyDPAO.periodPropertyName + "=\"" + getPeriod(i, forestWidth) + "\"} \n }"


      parser.parseScript(scriptString) match {
        case Some(script) => {
          val context = KevsInterpreterContext(myModel)
          script.interpret(context)

        }
        case None =>
      }
    }



    myModel

  }


  private def getPeriod(indice: Int, forestWidth: Int): Int = {
    val distance = getDistanceWithClosestSuperNode(indice, forestWidth)
    val targetFrequence = 1000 / PeriodValues.values.min
    val target = targetFrequence / Math.pow((forestWidth / 2), 2)
    val theoreticalFrequence = target * Math.pow(distance, 2)
    var periodeResult = PeriodValues.values.min
    PeriodValues.values.foreach{
      periode =>
        if (Math.abs(theoreticalFrequence - (1000 / periode)) < Math.abs(theoreticalFrequence - (1000 / periodeResult))){
          periodeResult = periode
        }
    }
    periodeResult
  }

  private def getDistanceWithClosestSuperNode(indice: Int, forestWidth: Int): Int = {
    val i: Int = indice / forestWidth
    val j: Int = indice - (i * forestWidth)
    var minDistance = forestWidth
    superNodesIndices.foreach {
      t =>
        val distanceI = Math.abs(t._1 - i)
        val distanceJ = Math.abs(t._2 - j)
        if (Math.max(distanceI, distanceJ) < minDistance) {
          minDistance = Math.max(distanceI, distanceJ)
        }
    }
    minDistance
  }



}