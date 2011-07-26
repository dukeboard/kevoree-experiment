package org.kevoree.experiment.smartForest.experiment

import ec.EvolutionState
import org.kevoree.tools.marShell.interpreter.{KevsInterpreterAspects, KevsInterpreterContext}
import org.kevoree.experiment.smartForest.dpa.{AddForestMonitoringComponentDPA, RemoveComponentDPA, AddComponentDPA}
import java.io.File
import org.kevoree.experiment.smartForest.SmartForestExperiment
import scala.collection.JavaConversions._
import org.kevoree.library.tools.dpa.DPA

/**
 * User: ffouquet
 * Date: 25/07/11
 * Time: 09:01
 */

class SmartForestIndividual extends KevoreeIndividualAbstract {

  var mutationDpas = Array(new RemoveComponentDPA().asInstanceOf[DPA], new AddForestMonitoringComponentDPA().asInstanceOf[DPA])
  var maxMutationDpasNumber = 1
  var minMutationDpasNumber = 1
  var maxResetDpasNumber = SmartForestExperiment.forestWidth*SmartForestExperiment.forestWidth*2
  var minResetDpasNumber = SmartForestExperiment.forestWidth*SmartForestExperiment.forestWidth
  var resetDpas : List[DPA] = List(new AddForestMonitoringComponentDPA())

  val baseModelPath = SmartForestExperiment.folderToStoreTempFile + File.separator + SmartForestExperiment.individualBaseModel

   /**
   * Destructively crosses over the individual with another in some default
   * manner.
   */
  override def defaultCrossover(state: EvolutionState, thread: Int, ind: KevoreeIndividualAbstract): Unit = {
    if (!ind.isInstanceOf[SmartForestIndividual]) {
      super.defaultCrossover(state, thread, ind)
      return
    }
    val dm = SmartForestIndividualHelper.compareForest(ind.asInstanceOf[SmartForestIndividual])
    val context = new KevsInterpreterContext(myModel)
    val addDPA = new AddComponentDPA
    val removeDPA = new RemoveComponentDPA
    dm.getAddInstance.foreach{ map =>
      if (state.random(thread).nextBoolean) {
        val script = addDPA.getASTScript(map)
        KevsInterpreterAspects.rich(script).interpret(context)
      }
    }
    dm.getRemoveInstance.foreach{ map =>
      if (state.random(thread).nextBoolean) {
        val script = removeDPA.getASTScript(map)
        KevsInterpreterAspects.rich(script).interpret(context)
      }
    }
  }

}