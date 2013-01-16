package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 16 janv. 13 Time: 13:37
 * Meta-Model:NS_URI=null
 */
trait KSecurityRule extends org.kevoree.KSecurityModel.KSecurityModelContainer {
   private var kElementQuery: java.lang.String = ""

   private var primitiveTypes: java.util.List[String] = _
   private var endValidity: java.util.Date = _
   private var EReference0: Option[org.kevoree.KSecurityModel.KSecurityRoot] = None

   private var authorized_java_cache: java.util.List[org.kevoree.KSecurityModel.KSecurityRoot] = null

   private var authorized_scala_cache: scala.collection.immutable.List[org.kevoree.KSecurityModel.KSecurityRoot] = null

   private val authorized: scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSecurityRoot] = new scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSecurityRoot]()

   private var allowed_java_cache: java.util.List[org.kevoree.KSecurityModel.KPublicKey] = null

   private var allowed_scala_cache: scala.collection.immutable.List[org.kevoree.KSecurityModel.KPublicKey] = null

   private val allowed: scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KPublicKey] = new scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KPublicKey]()

   private var acceptBinding_java_cache: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = null

   private var acceptBinding_scala_cache: scala.collection.immutable.List[org.kevoree.KSecurityModel.KSecurityRule] = null

   private val acceptBinding: scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSecurityRule] = new scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSecurityRule]()

   def getKElementQuery: java.lang.String = {
      kElementQuery
   }

   def setKElementQuery(kElementQuery: java.lang.String) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.kElementQuery = kElementQuery
   }

   def getPrimitiveTypes: java.util.List[String] = {
      primitiveTypes
   }

   def setPrimitiveTypes(primitiveTypes: java.util.List[String]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.primitiveTypes = primitiveTypes
   }

   def getEndValidity: java.util.Date = {
      endValidity
   }

   def setEndValidity(endValidity: java.util.Date) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      this.endValidity = endValidity
   }

   def getEReference0: Option[org.kevoree.KSecurityModel.KSecurityRoot] = {
      EReference0
   }

   def setEReference0(EReference0: Option[org.kevoree.KSecurityModel.KSecurityRoot]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      if (this.EReference0 != EReference0) {
         this.EReference0 = (EReference0)
      }

   }

   def getAuthorized: List[org.kevoree.KSecurityModel.KSecurityRoot] = {
      if (authorized_scala_cache != null) {
         authorized_scala_cache
      } else {
         val tempL: List[org.kevoree.KSecurityModel.KSecurityRoot] = authorized.toList
         authorized_scala_cache = tempL
         tempL
      }
   }
   def getAuthorizedForJ: java.util.List[org.kevoree.KSecurityModel.KSecurityRoot] = {
      if (authorized_java_cache != null) {
         authorized_java_cache
      } else {
         import scala.collection.JavaConversions._
         val tempL: java.util.List[org.kevoree.KSecurityModel.KSecurityRoot] = authorized.toList
         authorized_java_cache = tempL
         tempL
      }
   }

   def setAuthorized(authorized: List[org.kevoree.KSecurityModel.KSecurityRoot]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      if (authorized == null) throw new IllegalArgumentException("The list in parameter of the setter cannot be null. Use removeAll to empty a collection.")
      authorized_scala_cache = null
      authorized_java_cache = null
      if (this.authorized != authorized) {
         this.authorized.clear()
         this.authorized.insertAll(0, authorized)
      }

   }

   def addAuthorized(authorized: org.kevoree.KSecurityModel.KSecurityRoot) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      authorized_scala_cache = null
      authorized_java_cache = null
      this.authorized.append(authorized)
   }

   def addAllAuthorized(authorized: List[org.kevoree.KSecurityModel.KSecurityRoot]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      authorized_scala_cache = null
      authorized_java_cache = null
      this.authorized.appendAll(authorized)
   }

   def removeAuthorized(authorized: org.kevoree.KSecurityModel.KSecurityRoot) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      authorized_scala_cache = null
      authorized_java_cache = null
      if (this.authorized.size != 0 && this.authorized.indexOf(authorized) != -1) {
         this.authorized.remove(this.authorized.indexOf(authorized))
      }
   }

   def removeAllAuthorized() {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      authorized_scala_cache = null
      authorized_java_cache = null
      this.authorized.clear()
   }

   def getAllowed: List[org.kevoree.KSecurityModel.KPublicKey] = {
      if (allowed_scala_cache != null) {
         allowed_scala_cache
      } else {
         val tempL: List[org.kevoree.KSecurityModel.KPublicKey] = allowed.toList
         allowed_scala_cache = tempL
         tempL
      }
   }
   def getAllowedForJ: java.util.List[org.kevoree.KSecurityModel.KPublicKey] = {
      if (allowed_java_cache != null) {
         allowed_java_cache
      } else {
         import scala.collection.JavaConversions._
         val tempL: java.util.List[org.kevoree.KSecurityModel.KPublicKey] = allowed.toList
         allowed_java_cache = tempL
         tempL
      }
   }

   def setAllowed(allowed: List[org.kevoree.KSecurityModel.KPublicKey]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      if (allowed == null) throw new IllegalArgumentException("The list in parameter of the setter cannot be null. Use removeAll to empty a collection.")
      allowed_scala_cache = null
      allowed_java_cache = null
      if (this.allowed != allowed) {
         this.allowed.clear()
         this.allowed.insertAll(0, allowed)
      }

   }

   def addAllowed(allowed: org.kevoree.KSecurityModel.KPublicKey) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      allowed_scala_cache = null
      allowed_java_cache = null
      this.allowed.append(allowed)
   }

   def addAllAllowed(allowed: List[org.kevoree.KSecurityModel.KPublicKey]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      allowed_scala_cache = null
      allowed_java_cache = null
      this.allowed.appendAll(allowed)
   }

   def removeAllowed(allowed: org.kevoree.KSecurityModel.KPublicKey) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      allowed_scala_cache = null
      allowed_java_cache = null
      if (this.allowed.size != 0 && this.allowed.indexOf(allowed) != -1) {
         this.allowed.remove(this.allowed.indexOf(allowed))
      }
   }

   def removeAllAllowed() {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      allowed_scala_cache = null
      allowed_java_cache = null
      this.allowed.clear()
   }

   def getAcceptBinding: List[org.kevoree.KSecurityModel.KSecurityRule] = {
      if (acceptBinding_scala_cache != null) {
         acceptBinding_scala_cache
      } else {
         val tempL: List[org.kevoree.KSecurityModel.KSecurityRule] = acceptBinding.toList
         acceptBinding_scala_cache = tempL
         tempL
      }
   }
   def getAcceptBindingForJ: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = {
      if (acceptBinding_java_cache != null) {
         acceptBinding_java_cache
      } else {
         import scala.collection.JavaConversions._
         val tempL: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = acceptBinding.toList
         acceptBinding_java_cache = tempL
         tempL
      }
   }

   def setAcceptBinding(acceptBinding: List[org.kevoree.KSecurityModel.KSecurityRule]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      if (acceptBinding == null) throw new IllegalArgumentException("The list in parameter of the setter cannot be null. Use removeAll to empty a collection.")
      acceptBinding_scala_cache = null
      acceptBinding_java_cache = null
      if (this.acceptBinding != acceptBinding) {
         this.acceptBinding.clear()
         this.acceptBinding.insertAll(0, acceptBinding)
      }

   }

   def addAcceptBinding(acceptBinding: org.kevoree.KSecurityModel.KSecurityRule) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      acceptBinding_scala_cache = null
      acceptBinding_java_cache = null
      this.acceptBinding.append(acceptBinding)
   }

   def addAllAcceptBinding(acceptBinding: List[org.kevoree.KSecurityModel.KSecurityRule]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      acceptBinding_scala_cache = null
      acceptBinding_java_cache = null
      this.acceptBinding.appendAll(acceptBinding)
   }

   def removeAcceptBinding(acceptBinding: org.kevoree.KSecurityModel.KSecurityRule) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      acceptBinding_scala_cache = null
      acceptBinding_java_cache = null
      if (this.acceptBinding.size != 0 && this.acceptBinding.indexOf(acceptBinding) != -1) {
         this.acceptBinding.remove(this.acceptBinding.indexOf(acceptBinding))
      }
   }

   def removeAllAcceptBinding() {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      acceptBinding_scala_cache = null
      acceptBinding_java_cache = null
      this.acceptBinding.clear()
   }
   def getClonelazy(subResult: java.util.IdentityHashMap[Object, Object]): Unit = {
      val selfObjectClone = KSecurityModelFactory.createKSecurityRule
      selfObjectClone.setKElementQuery(this.getKElementQuery)
      selfObjectClone.setPrimitiveTypes(this.getPrimitiveTypes)
      selfObjectClone.setEndValidity(this.getEndValidity)
      subResult.put(this, selfObjectClone)
   }
   def resolve(addrs: java.util.IdentityHashMap[Object, Object], readOnly: Boolean): KSecurityRule = {
      val clonedSelfObject = addrs.get(this).asInstanceOf[org.kevoree.KSecurityModel.KSecurityRule]
      this.getEReference0.map { sub =>
         clonedSelfObject.setEReference0(Some(addrs.get(sub).asInstanceOf[org.kevoree.KSecurityModel.KSecurityRoot]))
      }

      this.getAuthorized.foreach { sub =>
         clonedSelfObject.addAuthorized(addrs.get(sub).asInstanceOf[org.kevoree.KSecurityModel.KSecurityRoot])
      }

      this.getAllowed.foreach { sub =>
         clonedSelfObject.addAllowed(addrs.get(sub).asInstanceOf[org.kevoree.KSecurityModel.KPublicKey])
      }

      this.getAcceptBinding.foreach { sub =>
         clonedSelfObject.addAcceptBinding(addrs.get(sub).asInstanceOf[org.kevoree.KSecurityModel.KSecurityRule])
      }

      if (readOnly) { clonedSelfObject.setInternalReadOnly() }
      clonedSelfObject
   }

}