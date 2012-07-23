package org.kevoree.hocl.scubeDemo.command

import fr.inria.hocl.api.{Molecule, ExternalObject, Tuple, Solution}
import org.slf4j.LoggerFactory
import org.kevoree.{Channel, ContainerNode, ComponentInstance}

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 16/03/12
 * Time: 14:58
 *
 * @author Erwan Daubert
 * @version 1.0
 */

case class RemoveChannelMolecule (channel: Channel, s: Solution) extends HoclCommand(s) {
  private final val logger = LoggerFactory.getLogger(this.getClass)

  def execute (): Boolean = {
    logger.debug("execute REMOVE_CHANNEL on HOCL")
    // Build molecule
    val t = new Tuple(2)
    t.set(0, new ExternalObject("REMOVE_CHANNEL"))
    t.set(1, new ExternalObject(channel))

    val mo = new Molecule()
    mo.add(t)

    //add Molecule
    s.addMolecule(mo)

    true
  }

  def undo (): Boolean = {
    AddChannelMolecule(channel, s).execute()
  }

}
