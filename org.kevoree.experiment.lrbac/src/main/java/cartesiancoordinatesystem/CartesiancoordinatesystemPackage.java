/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cartesiancoordinatesystem;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see cartesiancoordinatesystem.CartesiancoordinatesystemFactory
 * @model kind="package"
 * @generated
 */
public interface CartesiancoordinatesystemPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cartesiancoordinatesystem";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://cartesiancoordinatesystem/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "cartesiancoordinatesystem";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CartesiancoordinatesystemPackage eINSTANCE = cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl.init();

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.ElementImpl <em>Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.ElementImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getElement()
	 * @generated
	 */
	int ELEMENT = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__DESCRIPTION = 1;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT__COLOR = 2;

	/**
	 * The number of structural features of the '<em>Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ELEMENT_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl <em>Cartesian Coordinate System</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getCartesianCoordinateSystem()
	 * @generated
	 */
	int CARTESIAN_COORDINATE_SYSTEM = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__DESCRIPTION = ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__COLOR = ELEMENT__COLOR;

	/**
	 * The feature id for the '<em><b>Axes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__AXES = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Points</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__POINTS = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Segments</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__SEGMENTS = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Axe X</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__AXE_X = ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Axe Y</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__AXE_Y = ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Axe Z</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__AXE_Z = ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Coeff X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__COEFF_X = ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Coeff Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__COEFF_Y = ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Coeff Z</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM__COEFF_Z = ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Cartesian Coordinate System</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.AxisImpl <em>Axis</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.AxisImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxis()
	 * @generated
	 */
	int AXIS = 1;

	/**
	 * The number of structural features of the '<em>Axis</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXIS_FEATURE_COUNT = 0;

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.PointImpl <em>Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.PointImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getPoint()
	 * @generated
	 */
	int POINT = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__DESCRIPTION = ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__COLOR = ELEMENT__COLOR;

	/**
	 * The feature id for the '<em><b>X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__X = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT__Y = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POINT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.SegmentImpl <em>Segment</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.SegmentImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getSegment()
	 * @generated
	 */
	int SEGMENT = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__DESCRIPTION = ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__COLOR = ELEMENT__COLOR;

	/**
	 * The feature id for the '<em><b>A</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__A = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>B</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT__B = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Segment</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SEGMENT_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.AxeImpl <em>Axe</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.AxeImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxe()
	 * @generated
	 */
	int AXE = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE__NAME = ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE__DESCRIPTION = ELEMENT__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE__COLOR = ELEMENT__COLOR;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE__LENGTH = ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Axe</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_FEATURE_COUNT = ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.AxeXImpl <em>Axe X</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.AxeXImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxeX()
	 * @generated
	 */
	int AXE_X = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_X__NAME = AXE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_X__DESCRIPTION = AXE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_X__COLOR = AXE__COLOR;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_X__LENGTH = AXE__LENGTH;

	/**
	 * The number of structural features of the '<em>Axe X</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_X_FEATURE_COUNT = AXE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.AxeYImpl <em>Axe Y</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.AxeYImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxeY()
	 * @generated
	 */
	int AXE_Y = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Y__NAME = AXE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Y__DESCRIPTION = AXE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Y__COLOR = AXE__COLOR;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Y__LENGTH = AXE__LENGTH;

	/**
	 * The number of structural features of the '<em>Axe Y</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Y_FEATURE_COUNT = AXE_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.AxeZImpl <em>Axe Z</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.AxeZImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxeZ()
	 * @generated
	 */
	int AXE_Z = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Z__NAME = AXE__NAME;

	/**
	 * The feature id for the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Z__DESCRIPTION = AXE__DESCRIPTION;

	/**
	 * The feature id for the '<em><b>Color</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Z__COLOR = AXE__COLOR;

	/**
	 * The feature id for the '<em><b>Length</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Z__LENGTH = AXE__LENGTH;

	/**
	 * The number of structural features of the '<em>Axe Z</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int AXE_Z_FEATURE_COUNT = AXE_FEATURE_COUNT + 0;


	/**
	 * The meta object id for the '{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemEditorImpl <em>Cartesian Coordinate System Editor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cartesiancoordinatesystem.impl.CartesianCoordinateSystemEditorImpl
	 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getCartesianCoordinateSystemEditor()
	 * @generated
	 */
	int CARTESIAN_COORDINATE_SYSTEM_EDITOR = 9;

	/**
	 * The number of structural features of the '<em>Cartesian Coordinate System Editor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CARTESIAN_COORDINATE_SYSTEM_EDITOR_FEATURE_COUNT = 0;


	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.CartesianCoordinateSystem <em>Cartesian Coordinate System</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cartesian Coordinate System</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem
	 * @generated
	 */
	EClass getCartesianCoordinateSystem();

	/**
	 * Returns the meta object for the containment reference list '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxes <em>Axes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Axes</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getAxes()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EReference getCartesianCoordinateSystem_Axes();

	/**
	 * Returns the meta object for the containment reference list '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getPoints <em>Points</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Points</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getPoints()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EReference getCartesianCoordinateSystem_Points();

	/**
	 * Returns the meta object for the containment reference list '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getSegments <em>Segments</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Segments</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getSegments()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EReference getCartesianCoordinateSystem_Segments();

	/**
	 * Returns the meta object for the reference '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeX <em>Axe X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Axe X</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeX()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EReference getCartesianCoordinateSystem_AxeX();

	/**
	 * Returns the meta object for the reference '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeY <em>Axe Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Axe Y</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeY()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EReference getCartesianCoordinateSystem_AxeY();

	/**
	 * Returns the meta object for the reference '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeZ <em>Axe Z</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Axe Z</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getAxeZ()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EReference getCartesianCoordinateSystem_AxeZ();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffX <em>Coeff X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coeff X</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffX()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EAttribute getCartesianCoordinateSystem_CoeffX();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffY <em>Coeff Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coeff Y</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffY()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EAttribute getCartesianCoordinateSystem_CoeffY();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffZ <em>Coeff Z</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Coeff Z</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem#getCoeffZ()
	 * @see #getCartesianCoordinateSystem()
	 * @generated
	 */
	EAttribute getCartesianCoordinateSystem_CoeffZ();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.Axis <em>Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Axis</em>'.
	 * @see cartesiancoordinatesystem.Axis
	 * @generated
	 */
	EClass getAxis();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Point</em>'.
	 * @see cartesiancoordinatesystem.Point
	 * @generated
	 */
	EClass getPoint();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.Point#getX <em>X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>X</em>'.
	 * @see cartesiancoordinatesystem.Point#getX()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_X();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.Point#getY <em>Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Y</em>'.
	 * @see cartesiancoordinatesystem.Point#getY()
	 * @see #getPoint()
	 * @generated
	 */
	EAttribute getPoint_Y();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Segment</em>'.
	 * @see cartesiancoordinatesystem.Segment
	 * @generated
	 */
	EClass getSegment();

	/**
	 * Returns the meta object for the reference '{@link cartesiancoordinatesystem.Segment#getA <em>A</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>A</em>'.
	 * @see cartesiancoordinatesystem.Segment#getA()
	 * @see #getSegment()
	 * @generated
	 */
	EReference getSegment_A();

	/**
	 * Returns the meta object for the reference '{@link cartesiancoordinatesystem.Segment#getB <em>B</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>B</em>'.
	 * @see cartesiancoordinatesystem.Segment#getB()
	 * @see #getSegment()
	 * @generated
	 */
	EReference getSegment_B();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.AxeX <em>Axe X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Axe X</em>'.
	 * @see cartesiancoordinatesystem.AxeX
	 * @generated
	 */
	EClass getAxeX();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.AxeY <em>Axe Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Axe Y</em>'.
	 * @see cartesiancoordinatesystem.AxeY
	 * @generated
	 */
	EClass getAxeY();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.AxeZ <em>Axe Z</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Axe Z</em>'.
	 * @see cartesiancoordinatesystem.AxeZ
	 * @generated
	 */
	EClass getAxeZ();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.Axe <em>Axe</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Axe</em>'.
	 * @see cartesiancoordinatesystem.Axe
	 * @generated
	 */
	EClass getAxe();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.Axe#getLength <em>Length</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Length</em>'.
	 * @see cartesiancoordinatesystem.Axe#getLength()
	 * @see #getAxe()
	 * @generated
	 */
	EAttribute getAxe_Length();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Element</em>'.
	 * @see cartesiancoordinatesystem.Element
	 * @generated
	 */
	EClass getElement();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.Element#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see cartesiancoordinatesystem.Element#getName()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Name();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.Element#getDescription <em>Description</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Description</em>'.
	 * @see cartesiancoordinatesystem.Element#getDescription()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Description();

	/**
	 * Returns the meta object for the attribute '{@link cartesiancoordinatesystem.Element#getColor <em>Color</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Color</em>'.
	 * @see cartesiancoordinatesystem.Element#getColor()
	 * @see #getElement()
	 * @generated
	 */
	EAttribute getElement_Color();

	/**
	 * Returns the meta object for class '{@link cartesiancoordinatesystem.CartesianCoordinateSystemEditor <em>Cartesian Coordinate System Editor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cartesian Coordinate System Editor</em>'.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystemEditor
	 * @generated
	 */
	EClass getCartesianCoordinateSystemEditor();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CartesiancoordinatesystemFactory getCartesiancoordinatesystemFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl <em>Cartesian Coordinate System</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getCartesianCoordinateSystem()
		 * @generated
		 */
		EClass CARTESIAN_COORDINATE_SYSTEM = eINSTANCE.getCartesianCoordinateSystem();

		/**
		 * The meta object literal for the '<em><b>Axes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARTESIAN_COORDINATE_SYSTEM__AXES = eINSTANCE.getCartesianCoordinateSystem_Axes();

		/**
		 * The meta object literal for the '<em><b>Points</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARTESIAN_COORDINATE_SYSTEM__POINTS = eINSTANCE.getCartesianCoordinateSystem_Points();

		/**
		 * The meta object literal for the '<em><b>Segments</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARTESIAN_COORDINATE_SYSTEM__SEGMENTS = eINSTANCE.getCartesianCoordinateSystem_Segments();

		/**
		 * The meta object literal for the '<em><b>Axe X</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARTESIAN_COORDINATE_SYSTEM__AXE_X = eINSTANCE.getCartesianCoordinateSystem_AxeX();

		/**
		 * The meta object literal for the '<em><b>Axe Y</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARTESIAN_COORDINATE_SYSTEM__AXE_Y = eINSTANCE.getCartesianCoordinateSystem_AxeY();

		/**
		 * The meta object literal for the '<em><b>Axe Z</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CARTESIAN_COORDINATE_SYSTEM__AXE_Z = eINSTANCE.getCartesianCoordinateSystem_AxeZ();

		/**
		 * The meta object literal for the '<em><b>Coeff X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CARTESIAN_COORDINATE_SYSTEM__COEFF_X = eINSTANCE.getCartesianCoordinateSystem_CoeffX();

		/**
		 * The meta object literal for the '<em><b>Coeff Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CARTESIAN_COORDINATE_SYSTEM__COEFF_Y = eINSTANCE.getCartesianCoordinateSystem_CoeffY();

		/**
		 * The meta object literal for the '<em><b>Coeff Z</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CARTESIAN_COORDINATE_SYSTEM__COEFF_Z = eINSTANCE.getCartesianCoordinateSystem_CoeffZ();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.AxisImpl <em>Axis</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.AxisImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxis()
		 * @generated
		 */
		EClass AXIS = eINSTANCE.getAxis();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.PointImpl <em>Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.PointImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getPoint()
		 * @generated
		 */
		EClass POINT = eINSTANCE.getPoint();

		/**
		 * The meta object literal for the '<em><b>X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__X = eINSTANCE.getPoint_X();

		/**
		 * The meta object literal for the '<em><b>Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POINT__Y = eINSTANCE.getPoint_Y();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.SegmentImpl <em>Segment</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.SegmentImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getSegment()
		 * @generated
		 */
		EClass SEGMENT = eINSTANCE.getSegment();

		/**
		 * The meta object literal for the '<em><b>A</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT__A = eINSTANCE.getSegment_A();

		/**
		 * The meta object literal for the '<em><b>B</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SEGMENT__B = eINSTANCE.getSegment_B();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.AxeXImpl <em>Axe X</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.AxeXImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxeX()
		 * @generated
		 */
		EClass AXE_X = eINSTANCE.getAxeX();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.AxeYImpl <em>Axe Y</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.AxeYImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxeY()
		 * @generated
		 */
		EClass AXE_Y = eINSTANCE.getAxeY();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.AxeZImpl <em>Axe Z</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.AxeZImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxeZ()
		 * @generated
		 */
		EClass AXE_Z = eINSTANCE.getAxeZ();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.AxeImpl <em>Axe</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.AxeImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getAxe()
		 * @generated
		 */
		EClass AXE = eINSTANCE.getAxe();

		/**
		 * The meta object literal for the '<em><b>Length</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute AXE__LENGTH = eINSTANCE.getAxe_Length();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.ElementImpl <em>Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.ElementImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getElement()
		 * @generated
		 */
		EClass ELEMENT = eINSTANCE.getElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__NAME = eINSTANCE.getElement_Name();

		/**
		 * The meta object literal for the '<em><b>Description</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__DESCRIPTION = eINSTANCE.getElement_Description();

		/**
		 * The meta object literal for the '<em><b>Color</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ELEMENT__COLOR = eINSTANCE.getElement_Color();

		/**
		 * The meta object literal for the '{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemEditorImpl <em>Cartesian Coordinate System Editor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cartesiancoordinatesystem.impl.CartesianCoordinateSystemEditorImpl
		 * @see cartesiancoordinatesystem.impl.CartesiancoordinatesystemPackageImpl#getCartesianCoordinateSystemEditor()
		 * @generated
		 */
		EClass CARTESIAN_COORDINATE_SYSTEM_EDITOR = eINSTANCE.getCartesianCoordinateSystemEditor();

	}

} //CartesiancoordinatesystemPackage
