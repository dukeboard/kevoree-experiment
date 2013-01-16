package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 16 janv. 13 Time: 13:37
 * Meta-Model:NS_URI=null
 */
trait Primitives extends org.kevoree.KSecurityModel.KSecurityModelContainer {
   def getClonelazy(subResult: java.util.IdentityHashMap[Object, Object]): Unit = {
      val selfObjectClone = KSecurityModelFactory.createPrimitives
      subResult.put(this, selfObjectClone)
   }
   def resolve(addrs: java.util.IdentityHashMap[Object, Object], readOnly: Boolean): Primitives = {
      val clonedSelfObject = addrs.get(this).asInstanceOf[org.kevoree.KSecurityModel.Primitives]
      if (readOnly) { clonedSelfObject.setInternalReadOnly() }
      clonedSelfObject
   }

}