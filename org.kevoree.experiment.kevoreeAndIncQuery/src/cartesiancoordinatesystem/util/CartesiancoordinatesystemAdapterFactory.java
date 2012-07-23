/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cartesiancoordinatesystem.util;

import cartesiancoordinatesystem.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see cartesiancoordinatesystem.CartesiancoordinatesystemPackage
 * @generated
 */
public class CartesiancoordinatesystemAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static CartesiancoordinatesystemPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CartesiancoordinatesystemAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = CartesiancoordinatesystemPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CartesiancoordinatesystemSwitch<Adapter> modelSwitch =
		new CartesiancoordinatesystemSwitch<Adapter>() {
			@Override
			public Adapter caseCartesianCoordinateSystem(CartesianCoordinateSystem object) {
				return createCartesianCoordinateSystemAdapter();
			}
			@Override
			public Adapter caseAxis(Axis object) {
				return createAxisAdapter();
			}
			@Override
			public Adapter casePoint(Point object) {
				return createPointAdapter();
			}
			@Override
			public Adapter caseSegment(Segment object) {
				return createSegmentAdapter();
			}
			@Override
			public Adapter caseAxeX(AxeX object) {
				return createAxeXAdapter();
			}
			@Override
			public Adapter caseAxeY(AxeY object) {
				return createAxeYAdapter();
			}
			@Override
			public Adapter caseAxeZ(AxeZ object) {
				return createAxeZAdapter();
			}
			@Override
			public Adapter caseAxe(Axe object) {
				return createAxeAdapter();
			}
			@Override
			public Adapter caseElement(Element object) {
				return createElementAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.CartesianCoordinateSystem <em>Cartesian Coordinate System</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.CartesianCoordinateSystem
	 * @generated
	 */
	public Adapter createCartesianCoordinateSystemAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.Axis <em>Axis</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.Axis
	 * @generated
	 */
	public Adapter createAxisAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.Point <em>Point</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.Point
	 * @generated
	 */
	public Adapter createPointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.Segment <em>Segment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.Segment
	 * @generated
	 */
	public Adapter createSegmentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.AxeX <em>Axe X</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.AxeX
	 * @generated
	 */
	public Adapter createAxeXAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.AxeY <em>Axe Y</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.AxeY
	 * @generated
	 */
	public Adapter createAxeYAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.AxeZ <em>Axe Z</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.AxeZ
	 * @generated
	 */
	public Adapter createAxeZAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.Axe <em>Axe</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.Axe
	 * @generated
	 */
	public Adapter createAxeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link cartesiancoordinatesystem.Element <em>Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see cartesiancoordinatesystem.Element
	 * @generated
	 */
	public Adapter createElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //CartesiancoordinatesystemAdapterFactory
