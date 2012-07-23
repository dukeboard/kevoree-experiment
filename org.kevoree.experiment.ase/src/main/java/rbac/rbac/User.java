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
 * A representation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link rbac.rbac.User#getAssignedRoles <em>Assigned Roles</em>}</li>
 *   <li>{@link rbac.rbac.User#getDelegations <em>Delegations</em>}</li>
 *   <li>{@link rbac.rbac.User#getSession <em>Session</em>}</li>
 * </ul>
 * </p>
 *
 * @see rbac.rbac.RbacPackage#getUser()
 * @model
 * @generated
 */
public interface User extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Assigned Roles</b></em>' reference list.
	 * The list contents are of type {@link rbac.rbac.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Assigned Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Assigned Roles</em>' reference list.
	 * @see rbac.rbac.RbacPackage#getUser_AssignedRoles()
	 * @model
	 * @generated
	 */
	EList<Role> getAssignedRoles();

	/**
	 * Returns the value of the '<em><b>Delegations</b></em>' reference list.
	 * The list contents are of type {@link rbac.rbac.User}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delegations</em>' reference list.
	 * @see rbac.rbac.RbacPackage#getUser_Delegations()
	 * @model
	 * @generated
	 */
	EList<User> getDelegations();

	/**
	 * Returns the value of the '<em><b>Session</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Session</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Session</em>' reference.
	 * @see #setSession(Session)
	 * @see rbac.rbac.RbacPackage#getUser_Session()
	 * @model
	 * @generated
	 */
	Session getSession();

	/**
	 * Sets the value of the '{@link rbac.rbac.User#getSession <em>Session</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Session</em>' reference.
	 * @see #getSession()
	 * @generated
	 */
	void setSession(Session value);

} // User
