package org.kevoree.experiment.smartForest.experiment

import ec.simple.SimpleProblemForm
import ec.{Individual, EvolutionState, Problem}
import org.kevoree.experiment.smartForest.fitness.{ConfidenceFitnessFunction, ConsumptionFitnessFunction, DensityFitnessFunction}
import ec.multiobjective.MultiObjectiveFitness

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 26/07/11
 * Time: 10:06
 * To change this template use File | Settings | File Templates.
 */

object SmartForestFitnessEvaluatorO{
  val densityFitnessFunction = new DensityFitnessFunction
  def getDensityFitnessFunction = densityFitnessFunction
  val consumptionFitnessFunction = new ConsumptionFitnessFunction
  def getConsumptionFitnessFunction = consumptionFitnessFunction
  val confidenceFitnessFunction = new ConfidenceFitnessFunction
  def getConfidenceFitnessFunction = confidenceFitnessFunction
}

class SmartForestFitnessEvaluator extends Problem with SimpleProblemForm {

  override def evaluate(state: EvolutionState, ind: Individual, subpopulation: Int, threadnum: Int): Unit = {
    if (ind.evaluated) return
    if (!(ind.isInstanceOf[SmartForestIndividual])) state.output.fatal("Whoa!  It's not a SmartForestIndividual!!!", null)
    val ki = ind.asInstanceOf[SmartForestIndividual]
    val newObjectives: Array[Float] = Array(SmartForestFitnessEvaluatorO.getDensityFitnessFunction.evaluate(ki.myModel), SmartForestFitnessEvaluatorO.getConfidenceFitnessFunction.evaluate(ki.myModel), SmartForestFitnessEvaluatorO.getConsumptionFitnessFunction.evaluate(ki.myModel))
    setFitness(state, ki, newObjectives)
  }

  private def setFitness(state: EvolutionState, ki: SmartForestIndividual, newObjectives: Array[Float]): Unit = {
    if (!(ki.fitness.isInstanceOf[MultiObjectiveFitness])) {
      state.output.fatal("Whoa!  It's not a MultiObjective!!!", null)
    }
    (ki.fitness.asInstanceOf[MultiObjectiveFitness]).setObjectives(state, newObjectives)
    ki.evaluated = true
  }
}