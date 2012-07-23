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
 * A representation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link rbac.rbac.Session#getActiveRoles <em>Active Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @see rbac.rbac.RbacPackage#getSession()
 * @model
 * @generated
 */
public interface Session extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Active Roles</b></em>' reference list.
	 * The list contents are of type {@link rbac.rbac.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Roles</em>' reference list.
	 * @see rbac.rbac.RbacPackage#getSession_ActiveRoles()
	 * @model
	 * @generated
	 */
	EList<Role> getActiveRoles();

} // Session
