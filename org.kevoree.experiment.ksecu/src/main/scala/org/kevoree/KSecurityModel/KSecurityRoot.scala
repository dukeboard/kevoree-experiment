package org.kevoree.KSecurityModel;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 16 janv. 13 Time: 13:37
 * Meta-Model:NS_URI=null
 */
trait KSecurityRoot extends org.kevoree.KSecurityModel.KSecurityModelContainer {
   private var no_authorized_java_cache: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = null

   private var no_authorized_scala_cache: scala.collection.immutable.List[org.kevoree.KSecurityModel.KSecurityRule] = null

   private val no_authorized: scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSecurityRule] = new scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSecurityRule]()

   private var authorized_java_cache: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = null

   private var authorized_scala_cache: scala.collection.immutable.List[org.kevoree.KSecurityModel.KSecurityRule] = null

   private val authorized: scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSecurityRule] = new scala.collection.mutable.ListBuffer[org.kevoree.KSecurityModel.KSecurityRule]()

   def getNo_authorized: List[org.kevoree.KSecurityModel.KSecurityRule] = {
      if (no_authorized_scala_cache != null) {
         no_authorized_scala_cache
      } else {
         val tempL: List[org.kevoree.KSecurityModel.KSecurityRule] = no_authorized.toList
         no_authorized_scala_cache = tempL
         tempL
      }
   }
   def getNo_authorizedForJ: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = {
      if (no_authorized_java_cache != null) {
         no_authorized_java_cache
      } else {
         import scala.collection.JavaConversions._
         val tempL: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = no_authorized.toList
         no_authorized_java_cache = tempL
         tempL
      }
   }

   def setNo_authorized(no_authorized: List[org.kevoree.KSecurityModel.KSecurityRule]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      if (no_authorized == null) throw new IllegalArgumentException("The list in parameter of the setter cannot be null. Use removeAll to empty a collection.")
      no_authorized_scala_cache = null
      no_authorized_java_cache = null
      if (this.no_authorized != no_authorized) {
         this.no_authorized.clear()
         this.no_authorized.insertAll(0, no_authorized)
         no_authorized.foreach { elem => elem.setEContainer(this, Some(() => { this.removeNo_authorized(elem) })) }
      }

   }

   def addNo_authorized(no_authorized: org.kevoree.KSecurityModel.KSecurityRule) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      no_authorized_scala_cache = null
      no_authorized_java_cache = null
      no_authorized.setEContainer(this, Some(() => { this.removeNo_authorized(no_authorized) }))
      this.no_authorized.append(no_authorized)
   }

   def addAllNo_authorized(no_authorized: List[org.kevoree.KSecurityModel.KSecurityRule]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      no_authorized_scala_cache = null
      no_authorized_java_cache = null
      this.no_authorized.appendAll(no_authorized)
      no_authorized.foreach { el =>
         el.setEContainer(this, Some(() => { this.removeNo_authorized(el) }))
      }
   }

   def removeNo_authorized(no_authorized: org.kevoree.KSecurityModel.KSecurityRule) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      no_authorized_scala_cache = null
      no_authorized_java_cache = null
      if (this.no_authorized.size != 0 && this.no_authorized.indexOf(no_authorized) != -1) {
         this.no_authorized.remove(this.no_authorized.indexOf(no_authorized))
         no_authorized.setEContainer(null, None)
      }
   }

   def removeAllNo_authorized() {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      no_authorized_scala_cache = null
      no_authorized_java_cache = null
      val temp_els = no_authorized.toList
      temp_els.foreach { el =>
         el.setEContainer(null, None)
      }
      this.no_authorized.clear()
   }

   def getAuthorized: List[org.kevoree.KSecurityModel.KSecurityRule] = {
      if (authorized_scala_cache != null) {
         authorized_scala_cache
      } else {
         val tempL: List[org.kevoree.KSecurityModel.KSecurityRule] = authorized.toList
         authorized_scala_cache = tempL
         tempL
      }
   }
   def getAuthorizedForJ: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = {
      if (authorized_java_cache != null) {
         authorized_java_cache
      } else {
         import scala.collection.JavaConversions._
         val tempL: java.util.List[org.kevoree.KSecurityModel.KSecurityRule] = authorized.toList
         authorized_java_cache = tempL
         tempL
      }
   }

   def setAuthorized(authorized: List[org.kevoree.KSecurityModel.KSecurityRule]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      if (authorized == null) throw new IllegalArgumentException("The list in parameter of the setter cannot be null. Use removeAll to empty a collection.")
      authorized_scala_cache = null
      authorized_java_cache = null
      if (this.authorized != authorized) {
         this.authorized.clear()
         this.authorized.insertAll(0, authorized)
         authorized.foreach { elem => elem.setEContainer(this, Some(() => { this.removeAuthorized(elem) })) }
      }

   }

   def addAuthorized(authorized: org.kevoree.KSecurityModel.KSecurityRule) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      authorized_scala_cache = null
      authorized_java_cache = null
      authorized.setEContainer(this, Some(() => { this.removeAuthorized(authorized) }))
      this.authorized.append(authorized)
   }

   def addAllAuthorized(authorized: List[org.kevoree.KSecurityModel.KSecurityRule]) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      authorized_scala_cache = null
      authorized_java_cache = null
      this.authorized.appendAll(authorized)
      authorized.foreach { el =>
         el.setEContainer(this, Some(() => { this.removeAuthorized(el) }))
      }
   }

   def removeAuthorized(authorized: org.kevoree.KSecurityModel.KSecurityRule) {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      authorized_scala_cache = null
      authorized_java_cache = null
      if (this.authorized.size != 0 && this.authorized.indexOf(authorized) != -1) {
         this.authorized.remove(this.authorized.indexOf(authorized))
         authorized.setEContainer(null, None)
      }
   }

   def removeAllAuthorized() {
      if (isReadOnly()) { throw new Exception("This model is ReadOnly. Elements are not modifiable.") }
      authorized_scala_cache = null
      authorized_java_cache = null
      val temp_els = authorized.toList
      temp_els.foreach { el =>
         el.setEContainer(null, None)
      }
      this.authorized.clear()
   }
   def getClonelazy(subResult: java.util.IdentityHashMap[Object, Object]): Unit = {
      val selfObjectClone = KSecurityModelFactory.createKSecurityRoot
      subResult.put(this, selfObjectClone)
      this.getNo_authorized.foreach { sub =>
         sub.getClonelazy(subResult)
      }

      this.getAuthorized.foreach { sub =>
         sub.getClonelazy(subResult)
      }

   }
   def resolve(addrs: java.util.IdentityHashMap[Object, Object], readOnly: Boolean): KSecurityRoot = {
      val clonedSelfObject = addrs.get(this).asInstanceOf[org.kevoree.KSecurityModel.KSecurityRoot]
      this.getNo_authorized.foreach { sub =>
         clonedSelfObject.addNo_authorized(addrs.get(sub).asInstanceOf[org.kevoree.KSecurityModel.KSecurityRule])
      }

      this.getAuthorized.foreach { sub =>
         clonedSelfObject.addAuthorized(addrs.get(sub).asInstanceOf[org.kevoree.KSecurityModel.KSecurityRule])
      }

      this.getNo_authorized.foreach { sub =>
         sub.resolve(addrs, readOnly)
      }

      this.getAuthorized.foreach { sub =>
         sub.resolve(addrs, readOnly)
      }

      if (readOnly) { clonedSelfObject.setInternalReadOnly() }
      clonedSelfObject
   }

}