package org.kevoree.hocl.scubeDemo

import command.HoclCommand
import fr.inria.hocl.api.Solution
import org.kevoreeAdaptation.AdaptationModel
import org.slf4j.LoggerFactory

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 20/03/12
 * Time: 14:54
 *
 * @author Erwan Daubert
 * @version 1.0
 */

object HoclRunner {
  private final val logger = LoggerFactory.getLogger(this.getClass)

  def execute (adaptationModel: AdaptationModel, solution: Solution) {
    val commandMapper = new CommandMapper(solution)
    var actionToRollback = List[HoclCommand]()

    logger.debug("STARTING HOCL COMMAND")
    val done = adaptationModel.getAdaptations.forall {
      adaptation =>
        val command = commandMapper.buildHoclCommand(adaptation)
        if (command.execute()) {
          actionToRollback = actionToRollback ++ List[HoclCommand](command)
          true
        } else {
          false
        }
    }
    if (!done) {
      logger.debug("ROLLBACK HOCL COMMAND")
      actionToRollback.foreach {
        command => command.undo()
      }
    } else {
      logger.debug("APPLY HOCL COMMAND")
      solution.setNonInert() // must be done at the end of the adaptation to start the chemical engine
      solution.reduce()
    }
  }

}
