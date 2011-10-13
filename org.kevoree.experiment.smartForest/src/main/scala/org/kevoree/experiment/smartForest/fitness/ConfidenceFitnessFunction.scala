package org.kevoree.experiment.smartForest.fitness

import org.kevoree.{ComponentInstance, ContainerRoot}
import org.kevoree.experiment.smartForest.SmartForestExperiment
import java.lang.{Math}
import org.kevoree.experiment.smartForest.dpa.{PeriodValues, ChangePeriodPropertyDPAO}
import reflect.ValDef

/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 15:29
 */

class ConfidenceFitnessFunction extends FitnessFunction {

  val superNodesIndices = List((0,0), (0,SmartForestExperiment.forestWidth-1), (SmartForestExperiment.forestWidth-1, 0), (SmartForestExperiment.forestWidth-1,SmartForestExperiment.forestWidth-1))
  var bestConfidence : Double = 0.0

  def evaluate(model: ContainerRoot) : Float = {
    if (bestConfidence == 0) bestConfidence = calculateBestConfidence(model)
    var result : Float = 0
    (0 until model.getNodes.size).foreach{ indice =>
      val node = model.getNodes(indice);
      node.getComponents.foreach{ componentInstance =>
        val period = Integer.parseInt(componentInstance.asInstanceOf[ComponentInstance].getDictionary.get.getValues.find {
          dv =>
            dv.getAttribute.getName == ChangePeriodPropertyDPAO.getPeriodPropertyName
        }.get.getValue)
        val frequency = 1000/period
        result = result + frequency/((1+getDistanceWithClosestSuperNode(indice))*(1+getDistanceWithClosestSuperNode(indice) )) // 1+ getDistance to avoid division by 0
      }
    }
    FitnessPostProcess(result)
  }

  private def getDistanceWithClosestSuperNode(indice : Int): Int = {
    val i: Int = indice / SmartForestExperiment.forestWidth
    val j: Int = indice - (i * SmartForestExperiment.forestWidth)
    var minDistance = SmartForestExperiment.forestWidth
    superNodesIndices.foreach{ t =>
      val distanceI = Math.abs(t._1-i)
      val distanceJ = Math.abs(t._2-j)
      if (Math.max(distanceI,distanceJ)<minDistance){
        minDistance = Math.max(distanceI,distanceJ)
      }
    }
    minDistance
  }

  private def calculateBestConfidence(model: ContainerRoot): Float = {
    var result : Float = 0
    val worstPeriod = PeriodValues.values.min
    val worstFreq = 1000 / worstPeriod
    (0 until model.getNodes.size).foreach{ indice =>
      result = result + 3*worstFreq/((1+getDistanceWithClosestSuperNode(indice))*(1+getDistanceWithClosestSuperNode(indice) )) // 1+ getDistance to avoid division by 0
    }
    println("Best confidence = " + result)
    (result).asInstanceOf[Float]
  }


  private def FitnessPostProcess(confidence : Double) : Float = {
    val result = ((bestConfidence - confidence) * 100 / bestConfidence)
    (result).asInstanceOf[Float]
  }
}