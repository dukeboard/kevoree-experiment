/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package lrbac;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Policy</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lrbac.Policy#getElements <em>Elements</em>}</li>
 * </ul>
 * </p>
 *
 * @see lrbac.LrbacPackage#getPolicy()
 * @model
 * @generated
 */
public interface Policy extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Elements</b></em>' containment reference list.
	 * The list contents are of type {@link lrbac.PolicyElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Elements</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Elements</em>' containment reference list.
	 * @see lrbac.LrbacPackage#getPolicy_Elements()
	 * @model containment="true"
	 * @generated
	 */
	EList<PolicyElement> getElements();

} // Policy
