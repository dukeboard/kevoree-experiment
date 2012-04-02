package org.kevoree.hocl.scubeDemo.command

import fr.inria.hocl.api.{Tuple, ExternalObject, Molecule, Solution}
import org.slf4j.LoggerFactory
import org.kevoree.{ContainerNode, ComponentInstance}


/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 16/03/12
 * Time: 14:57
 *
 * @author Erwan Daubert
 * @version 1.0
 */

case class AddComponentMolecule (component: ComponentInstance, s: Solution) extends HoclCommand(s) {
  private final val logger = LoggerFactory.getLogger(this.getClass)
  def execute (): Boolean = {
    logger.debug("execute ADD_COMPONENT on HOCL")
    // Build molecule
    val t = new Tuple(4)
    t.set(0, new ExternalObject("ADD_COMPONENT"))
    t.set(1, new ExternalObject(component))

    // add node id
    t.set(2, new ExternalObject("Node_Id"))
    t.set(3, new ExternalObject(component.eContainer.asInstanceOf[ContainerNode].getName))

    val mo = new Molecule()
    mo.add(t)

    //add Molecule;
    s.addMolecule(mo)

    true
  }

  def undo (): Boolean = RemoveComponentMolecule(component, s).execute()
}
