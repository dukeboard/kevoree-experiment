package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 15 janv. 13 Time: 15:42
 * Meta-Model:NS_URI=null
 */
trait PublicKeys extends org.kevoree.KSecurityModel.KSecurityModelContainer {
   private var key: java.util.List[java.lang.Byte] = _

   def getKey: java.util.List[java.lang.Byte] = {
      key
   }

   def setKey(key: java.util.List[java.lang.Byte]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.key = key
   }
   def getClonelazy(subResult: java.util.IdentityHashMap[Object, Object]): Unit = {
      val selfObjectClone = KSecurityModelFactory.createPublicKeys
      selfObjectClone.setKey(this.getKey)
      subResult.put(this, selfObjectClone)
   }
   def resolve(addrs: java.util.IdentityHashMap[Object, Object], readOnly: Boolean): PublicKeys = {
      val clonedSelfObject = addrs.get(this).asInstanceOf[org.kevoree.KSecurityModel.PublicKeys]
      if (readOnly) { clonedSelfObject.setInternalReadOnly() }
      clonedSelfObject
   }

}