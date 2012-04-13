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
 * A representation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link rbac.Role#getSsod <em>Ssod</em>}</li>
 *   <li>{@link rbac.Role#getDsod <em>Dsod</em>}</li>
 *   <li>{@link rbac.Role#getHierarchy <em>Hierarchy</em>}</li>
 *   <li>{@link rbac.Role#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @see rbac.RbacPackage#getRole()
 * @model
 * @generated
 */
public interface Role extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Ssod</b></em>' reference list.
	 * The list contents are of type {@link rbac.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ssod</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ssod</em>' reference list.
	 * @see rbac.RbacPackage#getRole_Ssod()
	 * @model
	 * @generated
	 */
	EList<Role> getSsod();

	/**
	 * Returns the value of the '<em><b>Dsod</b></em>' reference list.
	 * The list contents are of type {@link rbac.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dsod</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dsod</em>' reference list.
	 * @see rbac.RbacPackage#getRole_Dsod()
	 * @model
	 * @generated
	 */
	EList<Role> getDsod();

	/**
	 * Returns the value of the '<em><b>Hierarchy</b></em>' reference list.
	 * The list contents are of type {@link rbac.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Hierarchy</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Hierarchy</em>' reference list.
	 * @see rbac.RbacPackage#getRole_Hierarchy()
	 * @model
	 * @generated
	 */
	EList<Role> getHierarchy();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link rbac.Permission}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see rbac.RbacPackage#getRole_Permissions()
	 * @model
	 * @generated
	 */
	EList<Permission> getPermissions();

} // Role
