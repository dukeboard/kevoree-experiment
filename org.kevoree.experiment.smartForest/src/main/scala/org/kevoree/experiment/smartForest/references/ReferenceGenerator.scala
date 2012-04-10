package org.kevoree.experiment.smartForest.references


import org.kevoree.experiment.smartForest.results.RIndividuGenerator
import org.kevoree.experiment.smartForest.experiment.SmartForestFitnessEvaluatorO

/**
 * User: ffouquet
 * Date: 28/07/11
 * Time: 17:21
 */

object ReferenceGenerator extends App {

  val model = ModelGenerator.generateForest(12);


  RIndividuGenerator.generateIndividualRRepresentation(model)

  println("Model "+model)


  val confidenceScore = SmartForestFitnessEvaluatorO.getConfidenceFitnessFunction.evaluate(model)
  println("Confidence Score = " + confidenceScore)

  val densityScore = SmartForestFitnessEvaluatorO.getDensityFitnessFunction.evaluate(model)
  println("Density Score = " + densityScore)

  val consumptionScore = SmartForestFitnessEvaluatorO.getConsumptionFitnessFunction.evaluate(model)
  println("Consumption Score = " + consumptionScore)



  println("Global Score = " + (consumptionScore + confidenceScore + densityScore))
}