/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cartesiancoordinatesystem.impl;

import cartesiancoordinatesystem.Axe;
import cartesiancoordinatesystem.AxeX;
import cartesiancoordinatesystem.AxeY;
import cartesiancoordinatesystem.AxeZ;
import cartesiancoordinatesystem.Axis;
import cartesiancoordinatesystem.CartesianCoordinateSystem;
import cartesiancoordinatesystem.CartesiancoordinatesystemFactory;
import cartesiancoordinatesystem.CartesiancoordinatesystemPackage;
import cartesiancoordinatesystem.Element;
import cartesiancoordinatesystem.Point;
import cartesiancoordinatesystem.Segment;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CartesiancoordinatesystemPackageImpl extends EPackageImpl implements CartesiancoordinatesystemPackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cartesianCoordinateSystemEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass axisEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass pointEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass segmentEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass axeXEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass axeYEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass axeZEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass axeEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass elementEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private CartesiancoordinatesystemPackageImpl() {
		super(eNS_URI, CartesiancoordinatesystemFactory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 * 
	 * <p>This method is used to initialize {@link CartesiancoordinatesystemPackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static CartesiancoordinatesystemPackage init() {
		if (isInited) return (CartesiancoordinatesystemPackage)EPackage.Registry.INSTANCE.getEPackage(CartesiancoordinatesystemPackage.eNS_URI);

		// Obtain or create and register package
		CartesiancoordinatesystemPackageImpl theCartesiancoordinatesystemPackage = (CartesiancoordinatesystemPackageImpl)(EPackage.Registry.INSTANCE.get(eNS_URI) instanceof CartesiancoordinatesystemPackageImpl ? EPackage.Registry.INSTANCE.get(eNS_URI) : new CartesiancoordinatesystemPackageImpl());

		isInited = true;

		// Create package meta-data objects
		theCartesiancoordinatesystemPackage.createPackageContents();

		// Initialize created meta-data
		theCartesiancoordinatesystemPackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theCartesiancoordinatesystemPackage.freeze();

  
		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(CartesiancoordinatesystemPackage.eNS_URI, theCartesiancoordinatesystemPackage);
		return theCartesiancoordinatesystemPackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCartesianCoordinateSystem() {
		return cartesianCoordinateSystemEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCartesianCoordinateSystem_Axes() {
		return (EReference)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCartesianCoordinateSystem_Points() {
		return (EReference)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCartesianCoordinateSystem_Segments() {
		return (EReference)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCartesianCoordinateSystem_AxeX() {
		return (EReference)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCartesianCoordinateSystem_AxeY() {
		return (EReference)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getCartesianCoordinateSystem_AxeZ() {
		return (EReference)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCartesianCoordinateSystem_CoeffX() {
		return (EAttribute)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(6);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCartesianCoordinateSystem_CoeffY() {
		return (EAttribute)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(7);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCartesianCoordinateSystem_CoeffZ() {
		return (EAttribute)cartesianCoordinateSystemEClass.getEStructuralFeatures().get(8);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAxis() {
		return axisEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getPoint() {
		return pointEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_X() {
		return (EAttribute)pointEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getPoint_Y() {
		return (EAttribute)pointEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSegment() {
		return segmentEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSegment_A() {
		return (EReference)segmentEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getSegment_B() {
		return (EReference)segmentEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAxeX() {
		return axeXEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAxeY() {
		return axeYEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAxeZ() {
		return axeZEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getAxe() {
		return axeEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getAxe_Length() {
		return (EAttribute)axeEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getElement() {
		return elementEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElement_Name() {
		return (EAttribute)elementEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElement_Description() {
		return (EAttribute)elementEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getElement_Color() {
		return (EAttribute)elementEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CartesiancoordinatesystemFactory getCartesiancoordinatesystemFactory() {
		return (CartesiancoordinatesystemFactory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		cartesianCoordinateSystemEClass = createEClass(CARTESIAN_COORDINATE_SYSTEM);
		createEReference(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__AXES);
		createEReference(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__POINTS);
		createEReference(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__SEGMENTS);
		createEReference(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__AXE_X);
		createEReference(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__AXE_Y);
		createEReference(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__AXE_Z);
		createEAttribute(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__COEFF_X);
		createEAttribute(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__COEFF_Y);
		createEAttribute(cartesianCoordinateSystemEClass, CARTESIAN_COORDINATE_SYSTEM__COEFF_Z);

		axisEClass = createEClass(AXIS);

		pointEClass = createEClass(POINT);
		createEAttribute(pointEClass, POINT__X);
		createEAttribute(pointEClass, POINT__Y);

		segmentEClass = createEClass(SEGMENT);
		createEReference(segmentEClass, SEGMENT__A);
		createEReference(segmentEClass, SEGMENT__B);

		axeXEClass = createEClass(AXE_X);

		axeYEClass = createEClass(AXE_Y);

		axeZEClass = createEClass(AXE_Z);

		axeEClass = createEClass(AXE);
		createEAttribute(axeEClass, AXE__LENGTH);

		elementEClass = createEClass(ELEMENT);
		createEAttribute(elementEClass, ELEMENT__NAME);
		createEAttribute(elementEClass, ELEMENT__DESCRIPTION);
		createEAttribute(elementEClass, ELEMENT__COLOR);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		cartesianCoordinateSystemEClass.getESuperTypes().add(this.getElement());
		pointEClass.getESuperTypes().add(this.getElement());
		segmentEClass.getESuperTypes().add(this.getElement());
		axeXEClass.getESuperTypes().add(this.getAxe());
		axeYEClass.getESuperTypes().add(this.getAxe());
		axeZEClass.getESuperTypes().add(this.getAxe());
		axeEClass.getESuperTypes().add(this.getElement());

		// Initialize classes and features; add operations and parameters
		initEClass(cartesianCoordinateSystemEClass, CartesianCoordinateSystem.class, "CartesianCoordinateSystem", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getCartesianCoordinateSystem_Axes(), this.getAxis(), null, "axes", null, 2, 3, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCartesianCoordinateSystem_Points(), this.getPoint(), null, "points", null, 0, -1, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCartesianCoordinateSystem_Segments(), this.getSegment(), null, "segments", null, 0, -1, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCartesianCoordinateSystem_AxeX(), this.getAxeX(), null, "axeX", null, 0, 1, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCartesianCoordinateSystem_AxeY(), this.getAxeY(), null, "axeY", null, 0, 1, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getCartesianCoordinateSystem_AxeZ(), this.getAxeZ(), null, "axeZ", null, 0, 1, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCartesianCoordinateSystem_CoeffX(), ecorePackage.getEInt(), "coeffX", null, 0, 1, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCartesianCoordinateSystem_CoeffY(), ecorePackage.getEInt(), "coeffY", null, 0, 1, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCartesianCoordinateSystem_CoeffZ(), ecorePackage.getEInt(), "coeffZ", null, 0, 1, CartesianCoordinateSystem.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(axisEClass, Axis.class, "Axis", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(pointEClass, Point.class, "Point", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getPoint_X(), ecorePackage.getEInt(), "x", null, 0, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getPoint_Y(), ecorePackage.getEInt(), "y", null, 0, 1, Point.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(segmentEClass, Segment.class, "Segment", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getSegment_A(), this.getPoint(), null, "a", null, 1, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getSegment_B(), this.getPoint(), null, "b", null, 1, 1, Segment.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(axeXEClass, AxeX.class, "AxeX", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(axeYEClass, AxeY.class, "AxeY", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(axeZEClass, AxeZ.class, "AxeZ", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(axeEClass, Axe.class, "Axe", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getAxe_Length(), ecorePackage.getEInt(), "length", null, 0, 1, Axe.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(elementEClass, Element.class, "Element", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getElement_Name(), ecorePackage.getEString(), "name", null, 1, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElement_Description(), ecorePackage.getEString(), "description", null, 1, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getElement_Color(), ecorePackage.getEString(), "color", null, 0, 1, Element.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);
	}

} //CartesiancoordinatesystemPackageImpl
