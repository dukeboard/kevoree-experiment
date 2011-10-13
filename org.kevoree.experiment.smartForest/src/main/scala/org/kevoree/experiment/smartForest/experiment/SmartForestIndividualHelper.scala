package org.kevoree.experiment.smartForest.experiment

import org.kevoree.library.reasoner.ecj.DiffModel
import org.kevoree.{DictionaryAttribute, ComponentInstance, ContainerNode, NamedElement}
import org.kevoree.experiment.smartForest.dpa.{ChangePeriodPropertyDPAO, AddComponentDPAO, RemoveComponentDPAO}
import org.kevoree.tools.marShell.ast.Script._
import org.kevoree.tools.marShell.ast.TransactionalBloc._
import org.kevoree.tools.marShell.ast.AddComponentInstanceStatment._
import org.kevoree.tools.marShell.ast.ComponentInstanceID._
import org.kevoree.tools.marShell.ast.UpdateDictionaryStatement._
import org.kevoree.tools.marShell.ast._

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 25/07/11
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */

object SmartForestIndividualHelper {

  val componentName = "componentName"
  val sourceNodeName = "sourceNodeName"
  val targetNodeName = "targetNodeName"


  def compareForest(ki: SmartForestIndividual): DiffModel = {
    val addList = new java.util.ArrayList[java.util.Map[String, NamedElement]]
    val removeList = new java.util.ArrayList[java.util.Map[String, NamedElement]]
    val updatePeriodList = new java.util.ArrayList[java.util.Map[String, NamedElement]]
    (0 until ki.myModel.getNodes.size).foreach { i =>
      {
        val myNode: ContainerNode = ki.myModel.getNodes(i)
        val otherNode: ContainerNode = ki.myModel.getNodes(i)
        myNode.getComponents.foreach{ ci =>
          if (!containsInstance(otherNode, ci.getTypeDefinition.getName)) {
            val myMap = new java.util.HashMap[String, NamedElement]
            myMap.put(RemoveComponentDPAO.componentName, ci.asInstanceOf[NamedElement])
            myMap.put(RemoveComponentDPAO.nodeName, myNode.asInstanceOf[NamedElement])
            removeList.add(myMap.asInstanceOf[java.util.Map[String, NamedElement]])
          } else {
            val otherComp = getInstance(otherNode, ci.getTypeDefinition.getName)
            val myValue = ci.getDictionary.get.getValues.find {
              dv =>
                dv.getAttribute.getName == ChangePeriodPropertyDPAO.periodPropertyName
            }.get
            val hisValue = otherComp.getDictionary.get.getValues.find {
              dv =>
                dv.getAttribute.getName == ChangePeriodPropertyDPAO.periodPropertyName
            }.get
            if (myValue.getValue != hisValue.getValue) {
              val myMap = new java.util.HashMap[String, NamedElement]
              myMap.put(ChangePeriodPropertyDPAO.periodPropertyName, hisValue.asInstanceOf[NamedElement])
              myMap.put(SmartForestIndividualHelper.targetNodeName, myNode.asInstanceOf[NamedElement])
              myMap.put(SmartForestIndividualHelper.componentName, ci.asInstanceOf[NamedElement])
              updatePeriodList.add(myMap.asInstanceOf[java.util.Map[String, NamedElement]])
            }
          }
        }
        otherNode.getComponents.foreach{ ci =>
          if (!containsInstance(myNode, ci.getTypeDefinition.getName)) {
            val myMap = new java.util.HashMap[String, NamedElement]
            myMap.put(SmartForestIndividualHelper.componentName, ci.asInstanceOf[NamedElement])
            myMap.put(SmartForestIndividualHelper.targetNodeName, myNode.asInstanceOf[NamedElement])
            myMap.put(SmartForestIndividualHelper.sourceNodeName, otherNode.asInstanceOf[NamedElement])
            addList.add(myMap.asInstanceOf[java.util.Map[String, NamedElement]])
          }
        }
      }
    }
    return new DiffModel(addList, removeList, updatePeriodList)
  }

  private def getInstance(myNode: ContainerNode, componentTypeName: String): ComponentInstance = {
     myNode.getComponents.find {
      component => component.getTypeDefinition.getName == componentTypeName
    }.get
  }

  private def containsInstance(myNode: ContainerNode, componentTypeName: String): Boolean = {
    myNode.getComponents.find {
      component => component.getTypeDefinition.getName == componentTypeName
    }.isDefined
  }

  def copyComponent(componentInstance : NamedElement, sourceNode: NamedElement, targetNode: NamedElement): Script = {
    val props = new java.util.Properties()
    val dv = componentInstance.asInstanceOf[ComponentInstance].getDictionary.get.getValues.find{ dv =>
      dv.getAttribute.getName == ChangePeriodPropertyDPAO.getPeriodPropertyName
    }.get
    props.put(ChangePeriodPropertyDPAO.getPeriodPropertyName, dv.getValue)
    Script(
      List(
        TransactionalBloc(
          List(
            AddComponentInstanceStatment(
              ComponentInstanceID(componentInstance.getName, Some(targetNode.getName)),
              componentInstance.asInstanceOf[ComponentInstance].getTypeDefinition.getName,
              props
            )
          )
        )
      )
    )
  }

  def updateProperty(propertyValue : String, componentInstance: NamedElement, targetNode: NamedElement): Script = {
    val props = new java.util.Properties()
    props.put(ChangePeriodPropertyDPAO.getPeriodPropertyName, propertyValue)

    Script(
      List(
        TransactionalBloc(
          List(
            UpdateDictionaryStatement(componentInstance.getName,
              Some(targetNode.getName),
              props)
          )
        )
      )
    )
  }
}