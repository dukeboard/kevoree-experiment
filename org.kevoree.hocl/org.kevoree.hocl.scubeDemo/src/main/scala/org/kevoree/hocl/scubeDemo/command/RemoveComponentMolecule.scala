package org.kevoree.hocl.scubeDemo.command

import fr.inria.hocl.api.{Molecule, ExternalObject, Tuple, Solution}
import org.kevoree.{ContainerNode, ComponentInstance}
import org.slf4j.LoggerFactory

/**
 * User: Erwan Daubert - erwan.daubert@gmail.com
 * Date: 16/03/12
 * Time: 14:58
 *
 * @author Erwan Daubert
 * @version 1.0
 */

case class RemoveComponentMolecule (component : ComponentInstance, s : Solution) extends HoclCommand(s){
  private final val logger = LoggerFactory.getLogger(this.getClass)
  def execute () : Boolean = {
    logger.debug("execute REMOVE_COMPONENT on HOCL")
     // Build molecule
    val t = new Tuple(3)
    t.set(0, new ExternalObject("REMOVE_COMPONENT"))
    t.set(1, new ExternalObject(component.eContainer.asInstanceOf[ContainerNode].getName))
    t.set(2, new ExternalObject(component.getName))

    val mo = new Molecule()
    mo.add(t)

    //add Molecule
    s.addMolecule(mo)

    true
  }

  def undo () : Boolean = {
    AddComponentMolecule(component, s).execute()
  }

}
