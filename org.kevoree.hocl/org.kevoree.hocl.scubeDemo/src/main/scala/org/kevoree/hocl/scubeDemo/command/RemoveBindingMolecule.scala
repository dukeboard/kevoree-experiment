package org.kevoree.hocl.scubeDemo.command

import fr.inria.hocl.api.{Tuple, ExternalObject, Molecule, Solution}
import org.slf4j.LoggerFactory
import org.kevoree.MBinding


/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 16/03/12
 * Time: 14:57
 *
 * @author Erwan Daubert
 * @version 1.0
 */

case class RemoveBindingMolecule (binding: MBinding, s: Solution) extends HoclCommand(s) {
  private final val logger = LoggerFactory.getLogger(this.getClass)

  def execute (): Boolean = {
    logger.debug("execute REMOVE_BINDING on HOCL")
    // Build molecule
    val t = new Tuple(2)
    t.set(0, new ExternalObject("REMOVE_BINDING"))
    t.set(1, new ExternalObject(binding))

    val mo = new Molecule()
    mo.add(t)

    //add Molecule;
    s.addMolecule(mo)

    true
  }

  def undo (): Boolean = AddBindingMolecule(binding, s).execute()
}
