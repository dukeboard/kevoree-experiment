package org.kevoree.experiment.smartForest.dpa

import org.kevoree.library.tools.dpa.DPA
import org.kevoree.{ContainerRoot, NamedElement}
import org.kevoree.tools.marShell.ast.{UpdateDictionaryStatement, TransactionalBloc, Script}
import ec.util.MersenneTwisterFast
import java.util.{HashMap, Map}
import scala.collection.JavaConversions._


/**
 * User: ffouquet
 * Date: 22/07/11
 * Time: 17:19
 */

object ChangePeriodPropertyDPAO {
  val componentName: String = "component"
  def getComponentName = componentName
  val nodeName: String = "node"
  def getNodeName = nodeName
  val periodPropertyName = "period"
  def getPeriodPropertyName = periodPropertyName
}

class ChangePeriodPropertyDPA extends DPA {

  def applyPointcut(model: ContainerRoot) : java.util.List[java.util.Map[String, NamedElement]] = {
    val results: java.util.List[java.util.Map[String, NamedElement]] = new java.util.ArrayList[java.util.Map[String, NamedElement]]
    model.getNodes.foreach{ node =>
      node.getComponents.foreach{ component =>
        val map: java.util.Map[String, NamedElement] = new java.util.HashMap[String, NamedElement]
        map.put(ChangePeriodPropertyDPAO.getComponentName, component)
        map.put(ChangePeriodPropertyDPAO.getNodeName, node)
        results.add(map)
      }
    }
    results
  }

  val random = new MersenneTwisterFast()

  def getASTScript(map: Map[String, NamedElement]): Script = {
    val props = new java.util.Properties()
    val newIndex = random.nextInt(PeriodValues.values.size)
    props.put(ChangePeriodPropertyDPAO.getPeriodPropertyName, PeriodValues.values(newIndex).toString)

    val propsAll = new HashMap[String,java.util.Properties]()
    propsAll.put("*",props)

    Script(
      List(
        TransactionalBloc(
          List(
            UpdateDictionaryStatement(map.get(ChangePeriodPropertyDPAO.getComponentName).getName,
              Some(map.get(ChangePeriodPropertyDPAO.getNodeName).getName),
              propsAll)
          )
        )
      )
    )
  }

  def getScript(p1: Map[String, NamedElement]) = ""



}