/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cartesiancoordinatesystem;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Axe</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cartesiancoordinatesystem.Axe#getLength <em>Length</em>}</li>
 * </ul>
 * </p>
 *
 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getAxe()
 * @model abstract="true"
 * @generated
 */
public interface Axe extends Element {
	/**
	 * Returns the value of the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Length</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Length</em>' attribute.
	 * @see #setLength(int)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getAxe_Length()
	 * @model
	 * @generated
	 */
	int getLength();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.Axe#getLength <em>Length</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Length</em>' attribute.
	 * @see #getLength()
	 * @generated
	 */
	void setLength(int value);

} // Axe
