/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package cartesiancoordinatesystem.impl;

import cartesiancoordinatesystem.AxeX;
import cartesiancoordinatesystem.AxeY;
import cartesiancoordinatesystem.AxeZ;
import cartesiancoordinatesystem.Axis;
import cartesiancoordinatesystem.CartesianCoordinateSystem;
import cartesiancoordinatesystem.CartesiancoordinatesystemPackage;
import cartesiancoordinatesystem.Point;
import cartesiancoordinatesystem.Segment;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cartesian Coordinate System</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getAxes <em>Axes</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getPoints <em>Points</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getSegments <em>Segments</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getAxeX <em>Axe X</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getAxeY <em>Axe Y</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getAxeZ <em>Axe Z</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getCoeffX <em>Coeff X</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getCoeffY <em>Coeff Y</em>}</li>
 *   <li>{@link cartesiancoordinatesystem.impl.CartesianCoordinateSystemImpl#getCoeffZ <em>Coeff Z</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CartesianCoordinateSystemImpl extends ElementImpl implements CartesianCoordinateSystem {
	/**
	 * The cached value of the '{@link #getAxes() <em>Axes</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAxes()
	 * @generated
	 * @ordered
	 */
	protected EList<Axis> axes;

	/**
	 * The cached value of the '{@link #getPoints() <em>Points</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPoints()
	 * @generated
	 * @ordered
	 */
	protected EList<Point> points;

	/**
	 * The cached value of the '{@link #getSegments() <em>Segments</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSegments()
	 * @generated
	 * @ordered
	 */
	protected EList<Segment> segments;

	/**
	 * The cached value of the '{@link #getAxeX() <em>Axe X</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAxeX()
	 * @generated
	 * @ordered
	 */
	protected AxeX axeX;

	/**
	 * The cached value of the '{@link #getAxeY() <em>Axe Y</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAxeY()
	 * @generated
	 * @ordered
	 */
	protected AxeY axeY;

	/**
	 * The cached value of the '{@link #getAxeZ() <em>Axe Z</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAxeZ()
	 * @generated
	 * @ordered
	 */
	protected AxeZ axeZ;

	/**
	 * The default value of the '{@link #getCoeffX() <em>Coeff X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoeffX()
	 * @generated
	 * @ordered
	 */
	protected static final int COEFF_X_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCoeffX() <em>Coeff X</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoeffX()
	 * @generated
	 * @ordered
	 */
	protected int coeffX = COEFF_X_EDEFAULT;

	/**
	 * The default value of the '{@link #getCoeffY() <em>Coeff Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoeffY()
	 * @generated
	 * @ordered
	 */
	protected static final int COEFF_Y_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCoeffY() <em>Coeff Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoeffY()
	 * @generated
	 * @ordered
	 */
	protected int coeffY = COEFF_Y_EDEFAULT;

	/**
	 * The default value of the '{@link #getCoeffZ() <em>Coeff Z</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoeffZ()
	 * @generated
	 * @ordered
	 */
	protected static final int COEFF_Z_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getCoeffZ() <em>Coeff Z</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCoeffZ()
	 * @generated
	 * @ordered
	 */
	protected int coeffZ = COEFF_Z_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected CartesianCoordinateSystemImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CartesiancoordinatesystemPackage.Literals.CARTESIAN_COORDINATE_SYSTEM;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Axis> getAxes() {
		if (axes == null) {
			axes = new EObjectContainmentEList<Axis>(Axis.class, this, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXES);
		}
		return axes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Point> getPoints() {
		if (points == null) {
			points = new EObjectContainmentEList<Point>(Point.class, this, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__POINTS);
		}
		return points;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Segment> getSegments() {
		if (segments == null) {
			segments = new EObjectContainmentEList<Segment>(Segment.class, this, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__SEGMENTS);
		}
		return segments;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeX getAxeX() {
		if (axeX != null && axeX.eIsProxy()) {
			InternalEObject oldAxeX = (InternalEObject)axeX;
			axeX = (AxeX)eResolveProxy(oldAxeX);
			if (axeX != oldAxeX) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_X, oldAxeX, axeX));
			}
		}
		return axeX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeX basicGetAxeX() {
		return axeX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAxeX(AxeX newAxeX) {
		AxeX oldAxeX = axeX;
		axeX = newAxeX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_X, oldAxeX, axeX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeY getAxeY() {
		if (axeY != null && axeY.eIsProxy()) {
			InternalEObject oldAxeY = (InternalEObject)axeY;
			axeY = (AxeY)eResolveProxy(oldAxeY);
			if (axeY != oldAxeY) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Y, oldAxeY, axeY));
			}
		}
		return axeY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeY basicGetAxeY() {
		return axeY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAxeY(AxeY newAxeY) {
		AxeY oldAxeY = axeY;
		axeY = newAxeY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Y, oldAxeY, axeY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeZ getAxeZ() {
		if (axeZ != null && axeZ.eIsProxy()) {
			InternalEObject oldAxeZ = (InternalEObject)axeZ;
			axeZ = (AxeZ)eResolveProxy(oldAxeZ);
			if (axeZ != oldAxeZ) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Z, oldAxeZ, axeZ));
			}
		}
		return axeZ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AxeZ basicGetAxeZ() {
		return axeZ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAxeZ(AxeZ newAxeZ) {
		AxeZ oldAxeZ = axeZ;
		axeZ = newAxeZ;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Z, oldAxeZ, axeZ));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCoeffX() {
		return coeffX;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoeffX(int newCoeffX) {
		int oldCoeffX = coeffX;
		coeffX = newCoeffX;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_X, oldCoeffX, coeffX));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCoeffY() {
		return coeffY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoeffY(int newCoeffY) {
		int oldCoeffY = coeffY;
		coeffY = newCoeffY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Y, oldCoeffY, coeffY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getCoeffZ() {
		return coeffZ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCoeffZ(int newCoeffZ) {
		int oldCoeffZ = coeffZ;
		coeffZ = newCoeffZ;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Z, oldCoeffZ, coeffZ));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXES:
				return ((InternalEList<?>)getAxes()).basicRemove(otherEnd, msgs);
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__POINTS:
				return ((InternalEList<?>)getPoints()).basicRemove(otherEnd, msgs);
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__SEGMENTS:
				return ((InternalEList<?>)getSegments()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXES:
				return getAxes();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__POINTS:
				return getPoints();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__SEGMENTS:
				return getSegments();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_X:
				if (resolve) return getAxeX();
				return basicGetAxeX();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Y:
				if (resolve) return getAxeY();
				return basicGetAxeY();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Z:
				if (resolve) return getAxeZ();
				return basicGetAxeZ();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_X:
				return getCoeffX();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Y:
				return getCoeffY();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Z:
				return getCoeffZ();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXES:
				getAxes().clear();
				getAxes().addAll((Collection<? extends Axis>)newValue);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__POINTS:
				getPoints().clear();
				getPoints().addAll((Collection<? extends Point>)newValue);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__SEGMENTS:
				getSegments().clear();
				getSegments().addAll((Collection<? extends Segment>)newValue);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_X:
				setAxeX((AxeX)newValue);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Y:
				setAxeY((AxeY)newValue);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Z:
				setAxeZ((AxeZ)newValue);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_X:
				setCoeffX((Integer)newValue);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Y:
				setCoeffY((Integer)newValue);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Z:
				setCoeffZ((Integer)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXES:
				getAxes().clear();
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__POINTS:
				getPoints().clear();
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__SEGMENTS:
				getSegments().clear();
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_X:
				setAxeX((AxeX)null);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Y:
				setAxeY((AxeY)null);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Z:
				setAxeZ((AxeZ)null);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_X:
				setCoeffX(COEFF_X_EDEFAULT);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Y:
				setCoeffY(COEFF_Y_EDEFAULT);
				return;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Z:
				setCoeffZ(COEFF_Z_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXES:
				return axes != null && !axes.isEmpty();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__POINTS:
				return points != null && !points.isEmpty();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__SEGMENTS:
				return segments != null && !segments.isEmpty();
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_X:
				return axeX != null;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Y:
				return axeY != null;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__AXE_Z:
				return axeZ != null;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_X:
				return coeffX != COEFF_X_EDEFAULT;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Y:
				return coeffY != COEFF_Y_EDEFAULT;
			case CartesiancoordinatesystemPackage.CARTESIAN_COORDINATE_SYSTEM__COEFF_Z:
				return coeffZ != COEFF_Z_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (coeffX: ");
		result.append(coeffX);
		result.append(", coeffY: ");
		result.append(coeffY);
		result.append(", coeffZ: ");
		result.append(coeffZ);
		result.append(')');
		return result.toString();
	}

} //CartesianCoordinateSystemImpl
