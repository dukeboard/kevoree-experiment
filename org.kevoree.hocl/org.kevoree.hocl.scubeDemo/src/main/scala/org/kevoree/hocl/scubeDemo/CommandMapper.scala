package org.kevoree.hocl.scubeDemo

import command._
import org.kevoree.kompare.JavaSePrimitive
import org.kevoree.framework.AbstractNodeType
import fr.inria.hocl.api.Solution
import org.kevoree._

/**
 * Created by IntelliJ IDEA.
 * User: duke
 * Date: 30/01/12
 * Time: 17:02
 */

class CommandMapper (solution: Solution) {

  var nodeType: AbstractNodeType = null

  def setNodeType (n: AbstractNodeType) {
    nodeType = n
  }

  def getNodeType: AbstractNodeType = nodeType


  def buildHoclCommand (p: org.kevoreeAdaptation.AdaptationPrimitive, nodeName: String, chemicalComponentName: String): HoclCommand = {
    p.getPrimitiveType.getName match {

      //      case JavaSePrimitive.UpdateDictionaryInstance if (p.getRef.asInstanceOf[Instance].getName == nodeName) => SelfDictionaryUpdate(p.getRef.asInstanceOf[Instance], nodeType)

      //      case JavaSePrimitive.AddDeployUnit => AddDeployUnit(p.getRef.asInstanceOf[DeployUnit], nodeType.getBootStrapperService)
      //      case JavaSePrimitive.UpdateDeployUnit => UpdateDeployUnit(p.getRef.asInstanceOf[DeployUnit], nodeType.getBootStrapperService)
      //      case JavaSePrimitive.RemoveDeployUnit => RemoveDeployUnit(p.getRef.asInstanceOf[DeployUnit], nodeType.getBootStrapperService)
      //      case JavaSePrimitive.AddThirdParty => AddDeployUnit(p.getRef.asInstanceOf[DeployUnit], nodeType.getBootStrapperService)
      //      case JavaSePrimitive.RemoveThirdParty => RemoveDeployUnit(p.getRef.asInstanceOf[DeployUnit], nodeType.getBootStrapperService)
      //      case JavaSePrimitive.AddInstance => AddInstance(p.getRef.asInstanceOf[Instance], nodeName, nodeType.getModelService, nodeType.getKevScriptEngineFactory, nodeType.getBootStrapperService)
      //      case JavaSePrimitive.UpdateDictionaryInstance => UpdateDictionary(p.getRef.asInstanceOf[Instance], nodeName)
      //      case JavaSePrimitive.RemoveInstance => RemoveInstance(p.getRef.asInstanceOf[Instance], nodeName, nodeType.getModelService, nodeType.getKevScriptEngineFactory, nodeType.getBootStrapperService)
      case JavaSePrimitive.StartInstance if (p.getRef.isInstanceOf[ComponentInstance] && !chemicalComponentName.equals(p.getRef.asInstanceOf[ComponentInstance].getName))
      => AddComponentMolecule(p.getRef.asInstanceOf[ComponentInstance], solution)
      case JavaSePrimitive.StopInstance if (p.getRef.isInstanceOf[ComponentInstance] && !chemicalComponentName.equals(p.getRef.asInstanceOf[ComponentInstance].getName))
      => RemoveComponentMolecule(p.getRef.asInstanceOf[ComponentInstance], solution)
      case JavaSePrimitive.StartInstance if (p.getRef.isInstanceOf[Channel]) => AddChannelMolecule(p.getRef.asInstanceOf[Channel], solution)
      case JavaSePrimitive.StopInstance if (p.getRef.isInstanceOf[Channel]) => RemoveChannelMolecule(p.getRef.asInstanceOf[Channel], solution)
      case "AddNode" if (!nodeName.equals(p.getRef.asInstanceOf[ContainerNode].getName)) => AddNodeMolecule(p.getRef.asInstanceOf[ContainerNode], solution)
      case "RemoveNode" if (!nodeName.equals(p.getRef.asInstanceOf[ContainerNode].getName)) => RemoveNodeMolecule(p.getRef.asInstanceOf[ContainerNode], solution)
      case JavaSePrimitive.AddBinding => AddBindingMolecule(p.getRef.asInstanceOf[MBinding], solution)
      case JavaSePrimitive.RemoveBinding => RemoveBindingMolecule(p.getRef.asInstanceOf[MBinding], solution)
      //      case JavaSePrimitive.AddFragmentBinding => AddFragmentBindingCommand(p.getRef.asInstanceOf[Channel], p.getTargetNodeName, nodeName)
      //      case JavaSePrimitive.RemoveFragmentBinding => RemoveFragmentBindingCommand(p.getRef.asInstanceOf[Channel], p.getTargetNodeName, nodeName)
      //      case JavaSePrimitive.StartThirdParty => NoopCommand()
      case _ => NoopCommand(solution)
    }
  }

}