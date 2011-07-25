package org.kevoree.experiment.smartForest.fitness

import org.kevoree.ContainerRoot

/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 15:27
 */

trait FitnessFunction {

  def evaluate(model : ContainerRoot) : Float

}