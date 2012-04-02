package org.kevoree.hocl.scubeDemo.command

import org.kevoree.{ContainerNode, ComponentInstance}
import fr.inria.hocl.api.{Molecule, ExternalObject, Tuple, Solution}
import org.slf4j.LoggerFactory

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 26/03/12
 * Time: 13:00
 *
 * @author Erwan Daubert
 * @version 1.0
 */

case class AddNodeMolecule (node: ContainerNode, s: Solution) extends HoclCommand(s) {
  private final val logger = LoggerFactory.getLogger(this.getClass)
  def execute (): Boolean = {
    logger.debug("execute ADD_NODE on HOCL")
    // Build molecule
    val t = new Tuple(2)
    t.set(0, new ExternalObject("ADD_NODE"))
    t.set(1, new ExternalObject(node))

    val mo = new Molecule()
    mo.add(t)

    //add Molecule;
    s.addMolecule(mo)

    true
  }

  def undo (): Boolean = {
    RemoveNodeMolecule(node, s).execute()
  }
}
