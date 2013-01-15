package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 15 janv. 13 Time: 15:42
 * Meta-Model:NS_URI=null
 */
trait KSecurityModelContainer {

   private var internal_eContainer: KSecurityModelContainer = null
   private var internal_unsetCmd: Option[() => Any] = None
   def eContainer = internal_eContainer
   private var internal_readOnlyElem = false
   def setInternalReadOnly() {
      internal_readOnlyElem = true
   }
   def isReadOnly(): Boolean = {
      internal_readOnlyElem
   }

   def setEContainer(container: KSecurityModelContainer, unsetCmd: Option[() => Any]) {
      if (internal_readOnlyElem) { throw new Exception("ReadOnly Element are not modifiable") }
      val tempUnsetCmd = internal_unsetCmd
      internal_unsetCmd = None
      tempUnsetCmd.map { inCmd => inCmd() }
      this.internal_eContainer = container

      internal_unsetCmd = unsetCmd
   }
   def internalGetQuery(selfKey: String): String = null
}