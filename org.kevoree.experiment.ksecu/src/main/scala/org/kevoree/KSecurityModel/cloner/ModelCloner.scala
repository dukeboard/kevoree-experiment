package org.kevoree.KSecurityModel.cloner
class ModelCloner {
	def clone[A](o : A) : A = {
	 clone(o,false)
	}
	def clone[A](o : A,readOnly : Boolean) : A = {
		o match {
			case o : org.kevoree.KSecurityModel.KSecurityRoot => {
				val context = new java.util.IdentityHashMap[Object,Object]()
				o.getClonelazy(context)
				o.resolve(context,readOnly).asInstanceOf[A]
			}
			case _ => null.asInstanceOf[A]
		}
	}
}
