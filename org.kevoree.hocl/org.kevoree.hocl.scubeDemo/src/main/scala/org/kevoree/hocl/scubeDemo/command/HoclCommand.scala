package org.kevoree.hocl.scubeDemo.command

import fr.inria.hocl.api.{Solution, Molecule}


/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 16/03/12
 * Time: 14:28
 *
 * @author Erwan Daubert
 * @version 1.0
 */

abstract class HoclCommand(solution : Solution) {

  def execute() : Boolean;
  def undo() : Boolean;

}
