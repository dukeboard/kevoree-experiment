/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rbac.rbac;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Policy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link rbac.rbac.Policy#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see rbac.rbac.RbacPackage#getPolicy()
 * @model
 * @generated
 */
public interface Policy extends EObject {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link rbac.rbac.PolicyElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see rbac.rbac.RbacPackage#getPolicy_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<PolicyElement> getElements();

} // Policy
