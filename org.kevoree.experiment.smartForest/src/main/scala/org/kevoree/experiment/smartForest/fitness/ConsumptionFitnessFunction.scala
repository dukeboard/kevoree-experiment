package org.kevoree.experiment.smartForest.fitness

import org.kevoree.ContainerRoot
import scala.collection.JavaConversions._
import collection.immutable.HashMap
import java.lang.Double
import org.kevoree.experiment.smartForest.dpa.{PeriodValues, ChangePeriodPropertyDPAO}

/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 15:29
 */

class ConsumptionFitnessFunction extends FitnessFunction {

  final val tempSensor: String = "TempSensor"
  final val smokeSensor: String = "SmokeSensor"
  final val humiditySensor: String = "HumiditySensor"

  val constantMap = HashMap[String, Long](
    tempSensor -> 10l,
    smokeSensor -> 100l,
    humiditySensor -> 20l
  )

  var worseConsumption : Double = 0.0
  /*
   *   Power consumption  =  nbCycle * period
   */


  def evaluate(model: ContainerRoot): Float = {
    if (worseConsumption == 0) worseConsumption = calculateWorseConsumption(model)
    var result = 0l
    model.getNodes.foreach {
      node =>
        node.getComponents.foreach {
          compo =>
            if (compo.getDictionary == null) {

              return 0l
            }
            compo.getDictionary.getValues.find(v => v.getAttribute.getName == ChangePeriodPropertyDPAO.periodPropertyName) match {
              case Some(property) => {
                val period =  Integer.parseInt(property.getValue)
                val freq = 1000 /  period

                result = result + ( freq * constantMap.get(compo.getTypeDefinition.getName).getOrElse(1l))
              }
              case None => {
                result = result + constantMap.get(compo.getTypeDefinition.getName).getOrElse(1l)
              }
            }
        }
    }
    FitnessPostProcess(result)
  }

  private def calculateWorseConsumption(model: ContainerRoot): Double = {
    var result = 0l
    val worstPeriod = PeriodValues.values.min
    println("Worst Period = " + worstPeriod)
    val worstFreq = 1000 / worstPeriod
    model.getNodes.foreach {
      node =>
        constantMap.values.foreach {
          cost =>
            result = result + (worstFreq * cost)

        }
    }
    println("Worse consumption = " + result)
    result
  }

  private def FitnessPostProcess(consumption : Double) : Float = {
    math.floor(consumption * 100 / worseConsumption).asInstanceOf[Float];
  }
}