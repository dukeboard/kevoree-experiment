/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cartesiancoordinatesystem.impl;

import cartesiancoordinatesystem.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CartesiancoordinatesystemFactoryImpl extends EFactoryImpl implements CartesiancoordinatesystemFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CartesiancoordinatesystemFactory init() {
		try {
			CartesiancoordinatesystemFactory theCartesiancoordinatesystemFactory = (CartesiancoordinatesystemFactory)EPackage.Registry.INSTANCE.getEFactory("http://cartesiancoordinatesystem/1.0"); 
			if (theCartesiancoordinatesystemFactory != null) {
				return theCartesiancoordinatesystemFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CartesiancoordinatesystemFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CartesiancoordinatesystemFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM: return createCartesianCoordinateSystem();
			case CartesiancoordinatesystemPackage.AXIS: return createAxis();
			case CartesiancoordinatesystemPackage.POINT: return createPoint();
			case CartesiancoordinatesystemPackage.SEGMENT: return createSegment();
			case CartesiancoordinatesystemPackage.AXE_X: return createAxeX();
			case CartesiancoordinatesystemPackage.AXE_Y: return createAxeY();
			case CartesiancoordinatesystemPackage.AXE_Z: return createAxeZ();
			case CartesiancoordinatesystemPackage.ELEMENT: return createElement();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CartesianCoordinateSystem createCartesianCoordinateSystem() {
		CartesianCoordinateSystemImpl cartesianCoordinateSystem = new CartesianCoordinateSystemImpl();
		return cartesianCoordinateSystem;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Axis createAxis() {
		AxisImpl axis = new AxisImpl();
		return axis;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Point createPoint() {
		PointImpl point = new PointImpl();
		return point;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Segment createSegment() {
		SegmentImpl segment = new SegmentImpl();
		return segment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeX createAxeX() {
		AxeXImpl axeX = new AxeXImpl();
		return axeX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeY createAxeY() {
		AxeYImpl axeY = new AxeYImpl();
		return axeY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeZ createAxeZ() {
		AxeZImpl axeZ = new AxeZImpl();
		return axeZ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Element createElement() {
		ElementImpl element = new ElementImpl();
		return element;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CartesiancoordinatesystemPackage getCartesiancoordinatesystemPackage() {
		return (CartesiancoordinatesystemPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CartesiancoordinatesystemPackage getPackage() {
		return CartesiancoordinatesystemPackage.eINSTANCE;
	}

} //CartesiancoordinatesystemFactoryImpl
