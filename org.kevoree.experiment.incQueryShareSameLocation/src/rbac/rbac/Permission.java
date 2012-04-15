/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rbac.rbac;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Permission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link rbac.rbac.Permission#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see rbac.rbac.RbacPackage#getPermission()
 * @model
 * @generated
 */
public interface Permission extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' containment reference list.
	 * The list contents are of type {@link rbac.rbac.Operation}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' containment reference list.
	 * @see rbac.rbac.RbacPackage#getPermission_Operations()
	 * @model containment="true"
	 * @generated
	 */
	EList<Operation> getOperations();

} // Permission
