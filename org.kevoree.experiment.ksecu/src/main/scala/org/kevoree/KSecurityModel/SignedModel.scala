package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 16 janv. 13 Time: 13:37
 * Meta-Model:NS_URI=null
 */
trait SignedModel extends org.kevoree.KSecurityModel.KSecurityModelContainer {
   private var model: Array[Byte] = _
   private var signed_java_cache: java.util.List[org.kevoree.KSecurityModel.KSignature] = null

   private var signed_scala_cache: scala.collection.immutable.List[org.kevoree.KSecurityModel.KSignature] = null

   private val signed: scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSignature] = new scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSignature]()

   def getModel: Array[Byte] = {
      model
   }

   def setModel(model: Array[Byte]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.model = model
   }

   def getSigned: List[org.kevoree.KSecurityModel.KSignature] = {
      if (signed_scala_cache != null) {
         signed_scala_cache
      } else {
         val tempL: List[org.kevoree.KSecurityModel.KSignature] = signed.toList
         signed_scala_cache = tempL
         tempL
      }
   }
   def getSignedForJ: java.util.List[org.kevoree.KSecurityModel.KSignature] = {
      if (signed_java_cache != null) {
         signed_java_cache
      } else {
         import scala.collection.JavaConversions._
         val tempL: java.util.List[org.kevoree.KSecurityModel.KSignature] = signed.toList
         signed_java_cache = tempL
         tempL
      }
   }

   def setSigned(signed: List[org.kevoree.KSecurityModel.KSignature]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      if (signed == null) throw new IllegalArgumentException("The list in parameter of the setter cannot be null. Use removeAll to empty a collection.")
      signed_scala_cache = null
      signed_java_cache = null
      if (this.signed != signed) {
         this.signed.clear()
         this.signed.insertAll(0, signed)
      }

   }

   def addSigned(signed: org.kevoree.KSecurityModel.KSignature) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      signed_scala_cache = null
      signed_java_cache = null
      this.signed.append(signed)
   }

   def addAllSigned(signed: List[org.kevoree.KSecurityModel.KSignature]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      signed_scala_cache = null
      signed_java_cache = null
      this.signed.appendAll(signed)
   }

   def removeSigned(signed: org.kevoree.KSecurityModel.KSignature) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      signed_scala_cache = null
      signed_java_cache = null
      if (this.signed.size != 0 && this.signed.indexOf(signed) != -1) {
         this.signed.remove(this.signed.indexOf(signed))
      }
   }

   def removeAllSigned() {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      signed_scala_cache = null
      signed_java_cache = null
      this.signed.clear()
   }
   def getClonelazy(subResult: java.util.IdentityHashMap[Object, Object]): Unit = {
      val selfObjectClone = KSecurityModelFactory.createSignedModel
      selfObjectClone.setModel(this.getModel)
      subResult.put(this, selfObjectClone)
   }
   def resolve(addrs: java.util.IdentityHashMap[Object, Object], readOnly: Boolean): SignedModel = {
      val clonedSelfObject = addrs.get(this).asInstanceOf[org.kevoree.KSecurityModel.SignedModel]
      this.getSigned.foreach { sub =>
         clonedSelfObject.addSigned(addrs.get(sub).asInstanceOf[org.kevoree.KSecurityModel.KSignature])
      }

      if (readOnly) { clonedSelfObject.setInternalReadOnly() }
      clonedSelfObject
   }

}