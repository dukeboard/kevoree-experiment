/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rbac;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link rbac.Operation#getResources <em>Resources</em>}</li>
 * </ul>
 * </p>
 *
 * @see rbac.RbacPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Resources</b></em>' reference list.
	 * The list contents are of type {@link rbac.Resource}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Resources</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Resources</em>' reference list.
	 * @see rbac.RbacPackage#getOperation_Resources()
	 * @model
	 * @generated
	 */
	EList<Resource> getResources();

} // Operation
