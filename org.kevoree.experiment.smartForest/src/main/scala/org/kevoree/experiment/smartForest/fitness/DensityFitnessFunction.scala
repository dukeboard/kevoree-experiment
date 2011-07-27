package org.kevoree.experiment.smartForest.fitness

import scala.collection.JavaConversions._
import org.kevoree.ContainerNode
import org.kevoree.ContainerRoot
import org.kevoree.experiment.smartForest.SmartForestExperiment

/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 15:29
 */

class DensityFitnessFunction extends FitnessFunction {

  val tempSensor: String = "TempSensor"
  val smokeSensor: String = "SmokeSensor"
  val humiditySensor: String = "HumiditySensor"
  val defaultPenality : Int = 2 // correspond to the default penality when non finding any component in this direction
  var worseDensity: Double = 0.0

  var count : Int = 0

  def evaluate(model: ContainerRoot): Float = {
    if (worseDensity == 0.0) worseDensity = evaluateWorseDensity(model)
    var densityV = 0.0;
    for (i <- 0 until model.getNodes.size()) {
      val myNode = model.getNodes.get(i);
      if (!containsInstance(myNode, tempSensor)) {
        densityV += density(model, tempSensor, i)
      }
      if (!containsInstance(myNode, smokeSensor)) {
        densityV += density(model, smokeSensor, i)
      }
      if (!containsInstance(myNode, humiditySensor)) {
        densityV += density(model, humiditySensor, i)
      }
    }
    return FitnessPostProcess(densityV)
  }

  private def FitnessPostProcess(density : Double) : Float = {

    (density * 100 / worseDensity).asInstanceOf[Float];
  }
  def density(myModel: ContainerRoot, myType: String, indice: Int): Double = {
    var density: Double = 0.0
    val i: Int = indice / SmartForestExperiment.forestWidth
    val j: Int = indice - (i * SmartForestExperiment.forestWidth)
    var minDiffI: Int = -1
    var minDiffJ: Int = -1
    var maxDiffI: Int = 1
    var maxDiffJ: Int = 1
    if (i == 0) minDiffI = 0
    if (j == 0) minDiffJ = 0
    if (i == SmartForestExperiment.forestWidth - 1) maxDiffI = 0
    if (j == SmartForestExperiment.forestWidth - 1) maxDiffJ = 0
    for (k <- minDiffI to maxDiffI) {
      for (l <- minDiffJ to maxDiffJ) {
        if (!(k == 0 && l == 0)) {
          density += calculateInDirection(myModel, k, l, myType, indice);
        }
      }
    }
    density
  }

  def calculateInDirection(myModel: ContainerRoot, diffI: Int, diffJ: Int, myType: String, indice: Int): Double = {
    var count : Int =0

    var i: Int = indice / SmartForestExperiment.forestWidth
    var j: Int = indice - (i * SmartForestExperiment.forestWidth)
    if (i == 0 || i == SmartForestExperiment.forestWidth - 1 || j == 0 || j == SmartForestExperiment.forestWidth - 1) {
      return defaultPenality
    }
    i += diffI
    j += diffJ
    val newIndice: Int = i * SmartForestExperiment.forestWidth + j
    val myNode: ContainerNode = myModel.getNodes.get(newIndice)
    if (containsInstance(myNode, myType)) {
      count += 1
      1
    }
    else {
      1 + calculateInDirection(myModel, diffI, diffJ, myType, newIndice)
    }
  }

  def containsInstance(myNode: ContainerNode, componentTypeName: String): Boolean = {
    myNode.getComponents.find {
      component => component.getTypeDefinition.getName == componentTypeName
    }.isDefined
  }

  private def evaluateWorseDensity(myModel: ContainerRoot): Float = {
    var density: Double = 0.0
    (0 until myModel.getNodes.size).foreach{ i => density += 3 * worseDensity(i)}
    println("Worse Density = " + density)
    return java.lang.Math.floor(density).asInstanceOf[Float]
  }

  private def worseDensity(indice: Int): Double = {
    var density: Double = 0.0
    val i: Int = indice / SmartForestExperiment.forestWidth
    val j: Int = indice - (i * SmartForestExperiment.forestWidth)
    var minDiffI: Int = -1
    var minDiffJ: Int = -1
    var maxDiffI: Int = 1
    var maxDiffJ: Int = 1
    if (i == 0) minDiffI = 0
    if (j == 0) minDiffJ = 0
    if (i == SmartForestExperiment.forestWidth - 1) maxDiffI = 0
    if (j == SmartForestExperiment.forestWidth - 1) maxDiffJ = 0
    (minDiffI to maxDiffI).foreach { k =>
      (minDiffJ to maxDiffJ).foreach { l =>
        if (!(k == 0 && l == 0)) density += calculateWorseInDirection(k, l, indice)
      }
    }
    return density
  }

  private def calculateWorseInDirection(diffI: Int, diffJ: Int, indice: Int): Double = {
    var i: Int = indice / SmartForestExperiment.forestWidth
    var j: Int = indice - (i * SmartForestExperiment.forestWidth)
//    if (i == 0 || i == SmartForestExperiment.forestWidth - 1 || j == 0 || j == SmartForestExperiment.forestWidth - 1) {
//      return defaultPenality
//    }
//    i += diffI
//    j += diffJ
//    var newIndice: Int = i * SmartForestExperiment.forestWidth + j
//    return 1 + calculateWorseInDirection(diffI, diffJ, newIndice)
    var result : Double = 2
    while (!(i == 0 || i == SmartForestExperiment.forestWidth - 1 || j == 0 || j == SmartForestExperiment.forestWidth - 1)) {
      result = result + 1d
      i += diffI
      j += diffJ
    }
    result
  }
}