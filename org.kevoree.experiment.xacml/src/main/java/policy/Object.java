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
 * A representation of the model object '<em><b>Object</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link policy.Object#getOperations <em>Operations</em>}</li>
 * </ul>
 * </p>
 *
 * @see policy.PolicyPackage#getObject()
 * @model
 * @generated
 */
public interface Object extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Operations</b></em>' reference list.
	 * The list contents are of type {@link policy.Operation}.
	 * It is bidirectional and its opposite is '{@link policy.Operation#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Operations</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Operations</em>' reference list.
	 * @see policy.PolicyPackage#getObject_Operations()
	 * @see policy.Operation#getObjects
	 * @model opposite="objects"
	 * @generated
	 */
	EList<Operation> getOperations();

} // Object
