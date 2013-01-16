package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 16 janv. 13 Time: 13:37
 * Meta-Model:NS_URI=null
 */
trait KSignature extends org.kevoree.KSecurityModel.KSecurityModelContainer {
   private var key: Array[Byte] = _

   def getKey: Array[Byte] = {
      key
   }

   def setKey(key: Array[Byte]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.key = key
   }
   def getClonelazy(subResult: java.util.IdentityHashMap[Object, Object]): Unit = {
      val selfObjectClone = KSecurityModelFactory.createKSignature
      selfObjectClone.setKey(this.getKey)
      subResult.put(this, selfObjectClone)
   }
   def resolve(addrs: java.util.IdentityHashMap[Object, Object], readOnly: Boolean): KSignature = {
      val clonedSelfObject = addrs.get(this).asInstanceOf[org.kevoree.KSecurityModel.KSignature]
      if (readOnly) { clonedSelfObject.setInternalReadOnly() }
      clonedSelfObject
   }

}