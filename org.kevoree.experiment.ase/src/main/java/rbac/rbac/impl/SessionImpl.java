/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rbac.rbac.impl;

import java.util.Collection;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import rbac.rbac.RbacPackage;
import rbac.rbac.Role;
import rbac.rbac.Session;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link rbac.rbac.impl.SessionImpl#getActiveRoles <em>Active Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SessionImpl extends PolicyElementImpl implements Session {
	/**
	 * The cached value of the '{@link #getActiveRoles() <em>Active Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActiveRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> activeRoles;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SessionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RbacPackage.Literals.SESSION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getActiveRoles() {
		if (activeRoles == null) {
			activeRoles = new EObjectResolvingEList<Role>(Role.class, this, RbacPackage.SESSION__ACTIVE_ROLES);
		}
		return activeRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RbacPackage.SESSION__ACTIVE_ROLES:
				return getActiveRoles();
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
			case RbacPackage.SESSION__ACTIVE_ROLES:
				getActiveRoles().clear();
				getActiveRoles().addAll((Collection<? extends Role>)newValue);
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
			case RbacPackage.SESSION__ACTIVE_ROLES:
				getActiveRoles().clear();
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
			case RbacPackage.SESSION__ACTIVE_ROLES:
				return activeRoles != null && !activeRoles.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //SessionImpl
