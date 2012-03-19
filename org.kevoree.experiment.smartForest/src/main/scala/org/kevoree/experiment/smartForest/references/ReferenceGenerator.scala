package org.kevoree.experiment.smartForest.references


import org.kevoree.experiment.smartForest.results.RIndividuGenerator
import org.kevoree.experiment.smartForest.experiment.SmartForestFitnessEvaluatorO

/**
 * User: ffouquet
 * Date: 28/07/11
 * Time: 17:21
 */

object ReferenceGenerator extends App {

  val model = ModelGenerator.generateForest(20);
  RIndividuGenerator.generateIndividualRRepresentation(model)
  val confidenceScore = SmartForestFitnessEvaluatorO.getConfidenceFitnessFunction.evaluate(model)
  val densityScore = SmartForestFitnessEvaluatorO.getDensityFitnessFunction.evaluate(model)
  val consumptionScore = SmartForestFitnessEvaluatorO.getConsumptionFitnessFunction.evaluate(model)
  println("Confidence Score = " + confidenceScore)
  println("Density Score = " + densityScore)
  println("Consumption Score = " + consumptionScore)
  println("Global Score = " + (consumptionScore + confidenceScore + densityScore))
}