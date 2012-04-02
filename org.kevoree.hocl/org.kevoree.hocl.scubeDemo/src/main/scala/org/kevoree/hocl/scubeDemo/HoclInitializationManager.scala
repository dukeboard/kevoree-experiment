package org.kevoree.hocl.scubeDemo

import fr.inria.hocl.api.Solution
import org.kevoree.{KevoreeFactory, ContainerRoot}
import org.kevoree.cloner.ModelCloner
import org.kevoreeAdaptation.AdaptationModel

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 26/03/12
 * Time: 11:43
 *
 * @author Erwan Daubert
 * @version 1.0
 */

object HoclInitializationManager {

  def execute(currentModel : ContainerRoot, solution : Solution) : AdaptationModel = {
    PlanningManager.kompare(initializeModel(currentModel), currentModel)
  }
  
  def initializeModel(currentModel : ContainerRoot) : ContainerRoot = {
    // initialize empty model with primitive types and kompare with the current one
    val cloner = new ModelCloner()
    val initialModel = cloner.clone(currentModel)
    initialModel.removeAllGroups()
    initialModel.removeAllMBindings()
    initialModel.removeAllHubs()
    initialModel.removeAllNodes()
    initialModel
  }
}
