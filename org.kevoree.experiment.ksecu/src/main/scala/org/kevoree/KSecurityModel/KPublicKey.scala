package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 16 janv. 13 Time: 13:37
 * Meta-Model:NS_URI=null
 */
trait KPublicKey extends org.kevoree.KSecurityModel.KSecurityModelContainer {
   private var key: java.lang.Object = null

   private var endValidity: java.util.Date = _

   def getKey: java.lang.Object = {
      key
   }

   def setKey(key: java.lang.Object) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.key = key
   }

   def getEndValidity: java.util.Date = {
      endValidity
   }

   def setEndValidity(endValidity: java.util.Date) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.endValidity = endValidity
   }
   def getClonelazy(subResult: java.util.IdentityHashMap[Object, Object]): Unit = {
      val selfObjectClone = KSecurityModelFactory.createKPublicKey
      selfObjectClone.setKey(this.getKey)
      selfObjectClone.setEndValidity(this.getEndValidity)
      subResult.put(this, selfObjectClone)
   }
   def resolve(addrs: java.util.IdentityHashMap[Object, Object], readOnly: Boolean): KPublicKey = {
      val clonedSelfObject = addrs.get(this).asInstanceOf[org.kevoree.KSecurityModel.KPublicKey]
      if (readOnly) { clonedSelfObject.setInternalReadOnly() }
      clonedSelfObject
   }

}