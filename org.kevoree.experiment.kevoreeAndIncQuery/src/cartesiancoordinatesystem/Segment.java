/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cartesiancoordinatesystem;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Segment</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cartesiancoordinatesystem.Segment#getA <em>A</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.Segment#getB <em>B</em>}</li>
 * </ul>
 * </p>
 *
 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getSegment()
 * @model
 * @generated
 */
public interface Segment extends Element {
	/**
	 * Returns the value of the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>A</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>A</em>' reference.
	 * @see #setA(Point)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getSegment_A()
	 * @model required="true"
	 * @generated
	 */
	Point getA();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.Segment#getA <em>A</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>A</em>' reference.
	 * @see #getA()
	 * @generated
	 */
	void setA(Point value);

	/**
	 * Returns the value of the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>B</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>B</em>' reference.
	 * @see #setB(Point)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getSegment_B()
	 * @model required="true"
	 * @generated
	 */
	Point getB();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.Segment#getB <em>B</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>B</em>' reference.
	 * @see #getB()
	 * @generated
	 */
	void setB(Point value);

} // Segment
