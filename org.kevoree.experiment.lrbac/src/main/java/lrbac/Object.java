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
 * A representation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lrbac.Object#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see lrbac.LrbacPackage#getObject()
 * @model
 * @generated
 */
public interface Object extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Operation}.
	 * It is bidirectional and its opposite is '{@link lrbac.Operation#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' reference list.
	 * @see lrbac.LrbacPackage#getObject_Operations()
	 * @see lrbac.Operation#getObjects
	 * @model opposite="objects"
	 * @generated
	 */
	EList<Operation> getOperations();

} // Object
