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
 * A representation of the model object '<em><b>Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lrbac.Constraint#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @see lrbac.LrbacPackage#getConstraint()
 * @model
 * @generated
 */
public interface Constraint extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Permissions</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Permission}.
	 * It is bidirectional and its opposite is '{@link lrbac.Permission#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Permissions</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Permissions</em>' reference list.
	 * @see lrbac.LrbacPackage#getConstraint_Permissions()
	 * @see lrbac.Permission#getConstraints
	 * @model opposite="constraints"
	 * @generated
	 */
	EList<Permission> getPermissions();

} // Constraint
