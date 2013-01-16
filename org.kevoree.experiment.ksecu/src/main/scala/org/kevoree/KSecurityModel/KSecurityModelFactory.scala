package org.kevoree.KSecurityModel;

import org.kevoree.KSecurityModel.impl._;

/**
 * Created by Ecore Model Generator.
 * @authors: Gregory NAIN, Fouquet Francois
 * Date: 16 janv. 13 Time: 13:37
 * Meta-Model:NS_URI=null
 */
object KSecurityModelFactory {

   def eINSTANCE = KSecurityModelFactory
   def getVersion = "1.0"

   def createKSecurityRoot: KSecurityRoot = new KSecurityRootImpl
   def createKSecurityRule: KSecurityRule = new KSecurityRuleImpl
   def createPrimitives: Primitives = new PrimitivesImpl
   def createKPublicKey: KPublicKey = new KPublicKeyImpl
   def createSignedModel: SignedModel = new SignedModelImpl
   def createKSignature: KSignature = new KSignatureImpl

}