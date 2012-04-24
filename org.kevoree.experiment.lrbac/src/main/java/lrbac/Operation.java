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
 * A representation of the model object '<em><b>Operation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lrbac.Operation#getObjects <em>Objects</em>}</li>
 *   <li>{@link lrbac.Operation#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @see lrbac.LrbacPackage#getOperation()
 * @model
 * @generated
 */
public interface Operation extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Objects</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Object}.
	 * It is bidirectional and its opposite is '{@link lrbac.Object#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Objects</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Objects</em>' reference list.
	 * @see lrbac.LrbacPackage#getOperation_Objects()
	 * @see lrbac.Object#getOperations
	 * @model opposite="operations"
	 * @generated
	 */
	EList<lrbac.Object> getObjects();

	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Permission}.
	 * It is bidirectional and its opposite is '{@link lrbac.Permission#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see lrbac.LrbacPackage#getOperation_Permissions()
	 * @see lrbac.Permission#getOperations
	 * @model opposite="operations"
	 * @generated
	 */
	EList<Permission> getPermissions();

} // Operation
