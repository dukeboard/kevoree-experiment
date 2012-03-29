package org.kevoree.hocl.scubeDemo.command

import org.kevoree.ContainerNode
import org.slf4j.LoggerFactory
import fr.inria.hocl.api.{Molecule, ExternalObject, Tuple, Solution}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 26/03/12
 * Time: 13:00
 *
 * @author Erwan Daubert
 * @version 1.0
 */

case class RemoveNodeMolecule (node: ContainerNode, s: Solution) extends HoclCommand(s) {
  private final val logger = LoggerFactory.getLogger(this.getClass)
  def execute (): Boolean = {
    logger.debug("execute REMOVE_NODE on HOCL")
        // Build molecule
        val t = new Tuple(2)
        t.set(0, new ExternalObject("DELETE_NODE"))
        t.set(1, new ExternalObject(node))

        val mo = new Molecule()
        mo.add(t)

        //add Molecule;
        s.addMolecule(mo)

        true
  }

  def undo (): Boolean = {
    AddNodeMolecule(node, s).execute()
  }
}
