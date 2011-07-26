package org.kevoree.experiment.smartForest.experiment

import org.kevoree.library.reasoner.ecj.DiffModel
import org.kevoree.{DictionaryAttribute, ComponentInstance, ContainerNode, NamedElement}
import org.kevoree.experiment.smartForest.dpa.{AddComponentDPAO, RemoveComponentDPAO}
import scala.collection.JavaConversions._

/**
 * Created by IntelliJ IDEA.
 * User: jbourcie
 * Date: 25/07/11
 * Time: 12:59
 * To change this template use File | Settings | File Templates.
 */

object SmartForestIndividualHelper {

  def compareForest(ki: SmartForestIndividual): DiffModel = {
    val addList = new java.util.ArrayList[java.util.Map[String, NamedElement]]
    val removeList = new java.util.ArrayList[java.util.Map[String, NamedElement]]
    (0 until ki.myModel.getNodes.size).foreach { i =>
      {
        val myNode: ContainerNode = ki.myModel.getNodes.get(i)
        val otherNode: ContainerNode = ki.myModel.getNodes.get(i)
        myNode.getComponents.foreach{ ci =>
          if (!containsInstance(otherNode, ci.getTypeDefinition.getName)) {
            val myMap = new java.util.HashMap[String, NamedElement]
            myMap.put(RemoveComponentDPAO.componentName, ci.asInstanceOf[NamedElement])
            myMap.put(RemoveComponentDPAO.nodeName, myNode.asInstanceOf[NamedElement])
            removeList.add(myMap.asInstanceOf[java.util.Map[String, NamedElement]])
          }
        }
        otherNode.getComponents.foreach{ ci =>
          if (!containsInstance(myNode, ci.getTypeDefinition.getName)) {
            val myMap = new java.util.HashMap[String, NamedElement]
            myMap.put(AddComponentDPAO.typeDefinition, ci.getTypeDefinition.asInstanceOf[NamedElement])
            myMap.put(AddComponentDPAO.nodeName, myNode.asInstanceOf[NamedElement])
            addList.add(myMap.asInstanceOf[java.util.Map[String, NamedElement]])
          }
        }
      }
    }
    return new DiffModel(addList, removeList)
  }

  private def getInstance(myNode: ContainerNode, componentName: String): ComponentInstance = {
     myNode.getComponents.find {
      component => component.getName == componentName
    }.get
  }

  private def containsInstance(myNode: ContainerNode, componentName: String): Boolean = {
    myNode.getComponents.find {
      component => component.getName == componentName
    }.isDefined
  }
}