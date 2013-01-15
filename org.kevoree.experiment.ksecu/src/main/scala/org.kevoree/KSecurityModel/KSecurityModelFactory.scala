package org.kevoree.KSecurityModel;

import org.kevoree.KSecurityModel.impl._;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 15 janv. 13 Time: 15:42
 * Meta-Model:NS_URI=null
 */
object KSecurityModelFactory {

   def eINSTANCE = KSecurityModelFactory
   def getVersion = "1.0"

   def createKSecurityRoot: KSecurityRoot = new KSecurityRootImpl
   def createSecurityRule: SecurityRule = new SecurityRuleImpl
   def createPrimitives: Primitives = new PrimitivesImpl
   def createPublicKeys: PublicKeys = new PublicKeysImpl
   def createSignedModel: SignedModel = new SignedModelImpl

}