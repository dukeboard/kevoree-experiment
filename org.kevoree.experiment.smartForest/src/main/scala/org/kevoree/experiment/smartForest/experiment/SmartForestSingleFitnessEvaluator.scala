package org.kevoree.experiment.smartForest.experiment

import org.kevoree.experiment.smartForest.fitness.{ConfidenceFitnessFunction, ConsumptionFitnessFunction, DensityFitnessFunction}
import ec.multiobjective.MultiObjectiveFitness
import ec.simple.{SimpleFitness, SimpleProblemForm}
import ec.{Fitness, Individual, EvolutionState, Problem}

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 26/07/11
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */

object SmartForestSingleFitnessEvaluatorO{
  val densityFitnessFunction = new DensityFitnessFunction
  def getDensityFitnessFunction = densityFitnessFunction
  val consumptionFitnessFunction = new ConsumptionFitnessFunction
  def getConsumptionFitnessFunction = consumptionFitnessFunction
  val confidenceFitnessFunction = new ConfidenceFitnessFunction
  def getConfidenceFitnessFunction = confidenceFitnessFunction
}

class SmartForestSingleFitnessEvaluator extends Problem with SimpleProblemForm {

  override def evaluate(state: EvolutionState, ind: Individual, subpopulation: Int, threadnum: Int): Unit = {
    if (ind.evaluated) return
    if (!(ind.isInstanceOf[SmartForestIndividual])) state.output.fatal("Whoa!  It's not a SmartForestIndividual!!!", null)
    val ki = ind.asInstanceOf[SmartForestIndividual]
    val fitness:Float = SmartForestFitnessEvaluatorO.getDensityFitnessFunction.evaluate(ki.myModel)+
      SmartForestFitnessEvaluatorO.getConsumptionFitnessFunction.evaluate(ki.myModel)+
      SmartForestFitnessEvaluatorO.getConfidenceFitnessFunction.evaluate(ki.myModel)



    setFitness(state, ki, (300f - fitness).asInstanceOf[Float])
  }

  private def setFitness(state: EvolutionState, ki: SmartForestIndividual, fitnessValue: Float): Unit = {
    if (!(ki.fitness.isInstanceOf[SimpleFitness])) {
      state.output.fatal("Whoa!  It's not a Single!!!", null)
    }
    (ki.fitness.asInstanceOf[SimpleFitness]).setFitness(state, fitnessValue, fitnessValue >=290)
    ki.evaluated = true
  }
}