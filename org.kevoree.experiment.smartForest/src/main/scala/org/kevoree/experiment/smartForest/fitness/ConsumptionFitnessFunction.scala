package org.kevoree.experiment.smartForest.fitness

import org.kevoree.ContainerRoot
import scala.collection.JavaConversions._
import collection.immutable.HashMap

/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 15:29
 */

class ConsumptionFitnessFunction extends FitnessFunction {

  final val tempSensor: String = "TempSensor"
  final val smokeSensor: String = "SmokeSensor"
  final val humiditySensor: String = "HumiditySensor"

  final val periodProp = "period"

  val constantMap = HashMap[String, Long](
    tempSensor -> 10,
    smokeSensor -> 100,
    humiditySensor -> 20
  )

  /*
   *   Power consumption  =  nbCycle * period
   */
  def evaluate(model: ContainerRoot): Float = {
    var result = 0l
    model.getNodes.foreach {
      node =>
        node.getComponents.foreach {
          compo =>
            if (compo.getDictionary == null) return 0
            compo.getDictionary.getValues.find(v => v.getAttribute.getName == periodProp) match {
              case Some(property) => {

                val period =  Integer.parseInt(property.getValue)
                val freq = 1 /  period

                result = result + ( freq * constantMap.get(compo.getTypeDefinition.getName).getOrElse(1l))
              }
              case None => result = result + constantMap.get(compo.getTypeDefinition.getName).getOrElse(1l)
            }
        }
    }
    result
  }
}