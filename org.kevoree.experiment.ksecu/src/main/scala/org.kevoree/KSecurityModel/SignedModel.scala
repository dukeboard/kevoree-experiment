package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 15 janv. 13 Time: 15:42
 * Meta-Model:NS_URI=null
 */
trait SignedModel extends org.kevoree.KSecurityModel.KSecurityModelContainer {
   private var model: java.util.List[java.lang.Byte] = _
   private var signatureModel: java.util.List[java.lang.Byte] = _

   def getModel: java.util.List[java.lang.Byte] = {
      model
   }

   def setModel(model: java.util.List[java.lang.Byte]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.model = model
   }

   def getSignatureModel: java.util.List[java.lang.Byte] = {
      signatureModel
   }

   def setSignatureModel(signatureModel: java.util.List[java.lang.Byte]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.signatureModel = signatureModel
   }
   def getClonelazy(subResult: java.util.IdentityHashMap[Object, Object]): Unit = {
      val selfObjectClone = KSecurityModelFactory.createSignedModel
      selfObjectClone.setModel(this.getModel)
      selfObjectClone.setSignatureModel(this.getSignatureModel)
      subResult.put(this, selfObjectClone)
   }
   def resolve(addrs: java.util.IdentityHashMap[Object, Object], readOnly: Boolean): SignedModel = {
      val clonedSelfObject = addrs.get(this).asInstanceOf[org.kevoree.KSecurityModel.SignedModel]
      if (readOnly) { clonedSelfObject.setInternalReadOnly() }
      clonedSelfObject
   }

}