package org.kevoree.hocl.scubeDemo

import org.kevoreeAdaptation.{AdaptationPrimitive, KevoreeAdaptationFactory, AdaptationModel}
import org.slf4j.{LoggerFactory, Logger}
import org.kevoree._
import kompare.KevoreeKompareBean


/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 13/12/11
 * Time: 09:19
 *
 * @author Erwan Daubert
 * @version 1.0
 */

object PlanningManager {
  private val logger: Logger = LoggerFactory.getLogger(PlanningManager.getClass)
  private val REMOVE_NODE: String = "RemoveNode"
  private val ADD_NODE: String = "AddNode"

  def kompare (current: ContainerRoot, target: ContainerRoot): AdaptationModel = {
    val adaptationModel: AdaptationModel = KevoreeAdaptationFactory.eINSTANCE.createAdaptationModel

    var removeNodeType: AdaptationPrimitiveType = null
    var addNodeType: AdaptationPrimitiveType = null
    current.getAdaptationPrimitiveTypes.foreach {
      primitiveType =>
        if (primitiveType.getName == REMOVE_NODE) {
          removeNodeType = primitiveType
        }
        else if (primitiveType.getName == ADD_NODE) {
          addNodeType = primitiveType
        }
    }
    if (removeNodeType == null || addNodeType == null) {
      target.getAdaptationPrimitiveTypes.foreach {
        primitiveType =>
          if (primitiveType.getName == REMOVE_NODE) {
            removeNodeType = primitiveType
          }
          else if (primitiveType.getName == ADD_NODE) {
            addNodeType = primitiveType
          }
      }
    }

    current.getNodes.filter(n => target.getNodes.find(n1 => n1.getName == n.getName).isEmpty).foreach {
      node =>
        val command: AdaptationPrimitive = KevoreeAdaptationFactory.eINSTANCE.createAdaptationPrimitive
        command.setPrimitiveType(removeNodeType)
        command.setRef(node)
        adaptationModel.addAdaptations(command)
    }

    target.getNodes.filter(n => current.getNodes.find(n1 => n1.getName == n.getName).isEmpty).foreach {
      node =>
        val command: AdaptationPrimitive = KevoreeAdaptationFactory.eINSTANCE.createAdaptationPrimitive
        command.setPrimitiveType(addNodeType)
        command.setRef(node)
        adaptationModel.addAdaptations(command)
    }

    // check all nodes to find all adaptations
    current.getNodes.foreach {
      node =>
        val superModel: AdaptationModel = softwareKompare(current, target, node.getName)
        val adaptations = superModel.getAdaptations.filter(
                   a => adaptationModel.getAdaptations.find(aa => aa.getPrimitiveType.getName == a.getPrimitiveType.getName &&
                  (aa.getRef.isInstanceOf[NamedElement] && a.getRef.isInstanceOf[NamedElement]
                    && aa.getRef.asInstanceOf[NamedElement].getName == a.getRef.asInstanceOf[NamedElement].getName)).isEmpty).toList
                adaptationModel.addAllAdaptations(adaptations)
    }
    target.getNodes.foreach {
      node =>
        val superModel: AdaptationModel = softwareKompare(current, target, node.getName)
        val adaptations = superModel.getAdaptations.filter(
           a => adaptationModel.getAdaptations.find(aa => aa.getPrimitiveType.getName == a.getPrimitiveType.getName &&
          (aa.getRef.isInstanceOf[NamedElement] && a.getRef.isInstanceOf[NamedElement]
            && aa.getRef.asInstanceOf[NamedElement].getName == a.getRef.asInstanceOf[NamedElement].getName)).isEmpty).toList
        adaptationModel.addAllAdaptations(adaptations)
    }

    adaptationModel
  }

  private def softwareKompare (current: ContainerRoot, target: ContainerRoot, nodeName: String): AdaptationModel = {
    val kompareBean = new KevoreeKompareBean
    kompareBean.kompare(current, target, nodeName)
  }
}