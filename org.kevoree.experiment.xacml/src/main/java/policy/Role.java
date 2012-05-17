/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package policy;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link policy.Role#getUsers <em>Users</em>}</li>
 *   <li>{@link policy.Role#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link policy.Role#getSsod <em>Ssod</em>}</li>
 *   <li>{@link policy.Role#getSsodopp <em>Ssodopp</em>}</li>
 *   <li>{@link policy.Role#getDsod <em>Dsod</em>}</li>
 *   <li>{@link policy.Role#getDsodopp <em>Dsodopp</em>}</li>
 *   <li>{@link policy.Role#getChildrenHierarchy <em>Children Hierarchy</em>}</li>
 *   <li>{@link policy.Role#getParentsHierarchy <em>Parents Hierarchy</em>}</li>
 *   <li>{@link policy.Role#getSessions <em>Sessions</em>}</li>
 * </ul>
 * </p>
 *
 * @see policy.PolicyPackage#getRole()
 * @model
 * @generated
 */
public interface Role extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Users</b></em>' reference list.
	 * The list contents are of type {@link policy.User}.
	 * It is bidirectional and its opposite is '{@link policy.User#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Users</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Users</em>' reference list.
	 * @see policy.PolicyPackage#getRole_Users()
	 * @see policy.User#getRoles
	 * @model opposite="roles"
	 * @generated
	 */
	EList<User> getUsers();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link policy.Permission}.
	 * It is bidirectional and its opposite is '{@link policy.Permission#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see policy.PolicyPackage#getRole_Permissions()
	 * @see policy.Permission#getRoles
	 * @model opposite="roles"
	 * @generated
	 */
	EList<Permission> getPermissions();

	/**
	 * Returns the value of the '<em><b>Ssod</b></em>' reference list.
	 * The list contents are of type {@link policy.Role}.
	 * It is bidirectional and its opposite is '{@link policy.Role#getSsodopp <em>Ssodopp</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ssod</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ssod</em>' reference list.
	 * @see policy.PolicyPackage#getRole_Ssod()
	 * @see policy.Role#getSsodopp
	 * @model opposite="ssodopp"
	 * @generated
	 */
	EList<Role> getSsod();

	/**
	 * Returns the value of the '<em><b>Ssodopp</b></em>' reference list.
	 * The list contents are of type {@link policy.Role}.
	 * It is bidirectional and its opposite is '{@link policy.Role#getSsod <em>Ssod</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ssodopp</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ssodopp</em>' reference list.
	 * @see policy.PolicyPackage#getRole_Ssodopp()
	 * @see policy.Role#getSsod
	 * @model opposite="ssod"
	 * @generated
	 */
	EList<Role> getSsodopp();

	/**
	 * Returns the value of the '<em><b>Dsod</b></em>' reference list.
	 * The list contents are of type {@link policy.Role}.
	 * It is bidirectional and its opposite is '{@link policy.Role#getDsodopp <em>Dsodopp</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dsod</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dsod</em>' reference list.
	 * @see policy.PolicyPackage#getRole_Dsod()
	 * @see policy.Role#getDsodopp
	 * @model opposite="dsodopp"
	 * @generated
	 */
	EList<Role> getDsod();

	/**
	 * Returns the value of the '<em><b>Dsodopp</b></em>' reference list.
	 * The list contents are of type {@link policy.Role}.
	 * It is bidirectional and its opposite is '{@link policy.Role#getDsod <em>Dsod</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dsodopp</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dsodopp</em>' reference list.
	 * @see policy.PolicyPackage#getRole_Dsodopp()
	 * @see policy.Role#getDsod
	 * @model opposite="dsod"
	 * @generated
	 */
	EList<Role> getDsodopp();

	/**
	 * Returns the value of the '<em><b>Children Hierarchy</b></em>' reference list.
	 * The list contents are of type {@link policy.Role}.
	 * It is bidirectional and its opposite is '{@link policy.Role#getParentsHierarchy <em>Parents Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children Hierarchy</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children Hierarchy</em>' reference list.
	 * @see policy.PolicyPackage#getRole_ChildrenHierarchy()
	 * @see policy.Role#getParentsHierarchy
	 * @model opposite="parentsHierarchy"
	 * @generated
	 */
	EList<Role> getChildrenHierarchy();

	/**
	 * Returns the value of the '<em><b>Parents Hierarchy</b></em>' reference list.
	 * The list contents are of type {@link policy.Role}.
	 * It is bidirectional and its opposite is '{@link policy.Role#getChildrenHierarchy <em>Children Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Parents Hierarchy</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Parents Hierarchy</em>' reference list.
	 * @see policy.PolicyPackage#getRole_ParentsHierarchy()
	 * @see policy.Role#getChildrenHierarchy
	 * @model opposite="childrenHierarchy"
	 * @generated
	 */
	EList<Role> getParentsHierarchy();

	/**
	 * Returns the value of the '<em><b>Sessions</b></em>' reference list.
	 * The list contents are of type {@link policy.Session}.
	 * It is bidirectional and its opposite is '{@link policy.Session#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sessions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sessions</em>' reference list.
	 * @see policy.PolicyPackage#getRole_Sessions()
	 * @see policy.Session#getRoles
	 * @model opposite="roles"
	 * @generated
	 */
	EList<Session> getSessions();

} // Role
