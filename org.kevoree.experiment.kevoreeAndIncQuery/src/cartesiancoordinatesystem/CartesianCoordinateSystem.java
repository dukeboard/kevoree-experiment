/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cartesiancoordinatesystem;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cartesian Coordinate System</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxes <em>Axes</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getPoints <em>Points</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getSegments <em>Segments</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeX <em>Axe X</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeY <em>Axe Y</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeZ <em>Axe Z</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffX <em>Coeff X</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffY <em>Coeff Y</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffZ <em>Coeff Z</em>}</li>
 * </ul>
 * </p>
 *
 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem()
 * @model
 * @generated
 */
public interface CartesianCoordinateSystem extends Element {
	/**
	 * Returns the value of the '<em><b>Axes</b></em>' containment reference list.
	 * The list contents are of type {@link cartesiancoordinatesystem.Axis}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Axes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Axes</em>' containment reference list.
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_Axes()
	 * @model containment="true" lower="2" upper="3"
	 * @generated
	 */
	EList<Axis> getAxes();

	/**
	 * Returns the value of the '<em><b>Points</b></em>' containment reference list.
	 * The list contents are of type {@link cartesiancoordinatesystem.Point}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Points</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Points</em>' containment reference list.
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_Points()
	 * @model containment="true"
	 * @generated
	 */
	EList<Point> getPoints();

	/**
	 * Returns the value of the '<em><b>Segments</b></em>' containment reference list.
	 * The list contents are of type {@link cartesiancoordinatesystem.Segment}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Segments</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Segments</em>' containment reference list.
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_Segments()
	 * @model containment="true"
	 * @generated
	 */
	EList<Segment> getSegments();

	/**
	 * Returns the value of the '<em><b>Axe X</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Axe X</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Axe X</em>' reference.
	 * @see #setAxeX(AxeX)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_AxeX()
	 * @model
	 * @generated
	 */
	AxeX getAxeX();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeX <em>Axe X</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Axe X</em>' reference.
	 * @see #getAxeX()
	 * @generated
	 */
	void setAxeX(AxeX value);

	/**
	 * Returns the value of the '<em><b>Axe Y</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Axe Y</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Axe Y</em>' reference.
	 * @see #setAxeY(AxeY)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_AxeY()
	 * @model
	 * @generated
	 */
	AxeY getAxeY();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeY <em>Axe Y</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Axe Y</em>' reference.
	 * @see #getAxeY()
	 * @generated
	 */
	void setAxeY(AxeY value);

	/**
	 * Returns the value of the '<em><b>Axe Z</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Axe Z</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Axe Z</em>' reference.
	 * @see #setAxeZ(AxeZ)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_AxeZ()
	 * @model
	 * @generated
	 */
	AxeZ getAxeZ();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeZ <em>Axe Z</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Axe Z</em>' reference.
	 * @see #getAxeZ()
	 * @generated
	 */
	void setAxeZ(AxeZ value);

	/**
	 * Returns the value of the '<em><b>Coeff X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coeff X</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coeff X</em>' attribute.
	 * @see #setCoeffX(int)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_CoeffX()
	 * @model
	 * @generated
	 */
	int getCoeffX();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffX <em>Coeff X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coeff X</em>' attribute.
	 * @see #getCoeffX()
	 * @generated
	 */
	void setCoeffX(int value);

	/**
	 * Returns the value of the '<em><b>Coeff Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coeff Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coeff Y</em>' attribute.
	 * @see #setCoeffY(int)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_CoeffY()
	 * @model
	 * @generated
	 */
	int getCoeffY();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffY <em>Coeff Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coeff Y</em>' attribute.
	 * @see #getCoeffY()
	 * @generated
	 */
	void setCoeffY(int value);

	/**
	 * Returns the value of the '<em><b>Coeff Z</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Coeff Z</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Coeff Z</em>' attribute.
	 * @see #setCoeffZ(int)
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#getCartesianCoordinateSystem_CoeffZ()
	 * @model
	 * @generated
	 */
	int getCoeffZ();

	/**
	 * Sets the value of the '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffZ <em>Coeff Z</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Coeff Z</em>' attribute.
	 * @see #getCoeffZ()
	 * @generated
	 */
	void setCoeffZ(int value);

} // CartesianCoordinateSystem
