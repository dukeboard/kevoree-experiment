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
 * A representation of the model object '<em><b>Permission</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lrbac.Permission#getRoles <em>Roles</em>}</li>
 *   <li>{@link lrbac.Permission#getOperations <em>Operations</em>}</li>
 *   <li>{@link lrbac.Permission#getConstraints <em>Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see lrbac.LrbacPackage#getPermission()
 * @model
 * @generated
 */
public interface Permission extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Roles</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Role}.
	 * It is bidirectional and its opposite is '{@link lrbac.Role#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' reference list.
	 * @see lrbac.LrbacPackage#getPermission_Roles()
	 * @see lrbac.Role#getPermissions
	 * @model opposite="permissions"
	 * @generated
	 */
	EList<Role> getRoles();

	/**
	 * Returns the value of the '<em><b>Operations</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Operation}.
	 * It is bidirectional and its opposite is '{@link lrbac.Operation#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' reference list.
	 * @see lrbac.LrbacPackage#getPermission_Operations()
	 * @see lrbac.Operation#getPermissions
	 * @model opposite="permissions"
	 * @generated
	 */
	EList<Operation> getOperations();

	/**
	 * Returns the value of the '<em><b>Constraints</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Constraint}.
	 * It is bidirectional and its opposite is '{@link lrbac.Constraint#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Constraints</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Constraints</em>' reference list.
	 * @see lrbac.LrbacPackage#getPermission_Constraints()
	 * @see lrbac.Constraint#getPermissions
	 * @model opposite="permissions"
	 * @generated
	 */
	EList<Constraint> getConstraints();

} // Permission
