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

import rbac.rbac.Permission;
import rbac.rbac.RbacPackage;
import rbac.rbac.Role;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link rbac.rbac.impl.RoleImpl#getSsod <em>Ssod</em>}</li>
 *   <li>{@link rbac.rbac.impl.RoleImpl#getDsod <em>Dsod</em>}</li>
 *   <li>{@link rbac.rbac.impl.RoleImpl#getHierarchy <em>Hierarchy</em>}</li>
 *   <li>{@link rbac.rbac.impl.RoleImpl#getPermissions <em>Permissions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends PolicyElementImpl implements Role {
	/**
	 * The cached value of the '{@link #getSsod() <em>Ssod</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSsod()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> ssod;

	/**
	 * The cached value of the '{@link #getDsod() <em>Dsod</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsod()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> dsod;

	/**
	 * The cached value of the '{@link #getHierarchy() <em>Hierarchy</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getHierarchy()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> hierarchy;

	/**
	 * The cached value of the '{@link #getPermissions() <em>Permissions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPermissions()
	 * @generated
	 * @ordered
	 */
	protected EList<Permission> permissions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RbacPackage.Literals.ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getSsod() {
		if (ssod == null) {
			ssod = new EObjectResolvingEList<Role>(Role.class, this, RbacPackage.ROLE__SSOD);
		}
		return ssod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getDsod() {
		if (dsod == null) {
			dsod = new EObjectResolvingEList<Role>(Role.class, this, RbacPackage.ROLE__DSOD);
		}
		return dsod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getHierarchy() {
		if (hierarchy == null) {
			hierarchy = new EObjectResolvingEList<Role>(Role.class, this, RbacPackage.ROLE__HIERARCHY);
		}
		return hierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Permission> getPermissions() {
		if (permissions == null) {
			permissions = new EObjectResolvingEList<Permission>(Permission.class, this, RbacPackage.ROLE__PERMISSIONS);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RbacPackage.ROLE__SSOD:
				return getSsod();
			case RbacPackage.ROLE__DSOD:
				return getDsod();
			case RbacPackage.ROLE__HIERARCHY:
				return getHierarchy();
			case RbacPackage.ROLE__PERMISSIONS:
				return getPermissions();
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
			case RbacPackage.ROLE__SSOD:
				getSsod().clear();
				getSsod().addAll((Collection<? extends Role>)newValue);
				return;
			case RbacPackage.ROLE__DSOD:
				getDsod().clear();
				getDsod().addAll((Collection<? extends Role>)newValue);
				return;
			case RbacPackage.ROLE__HIERARCHY:
				getHierarchy().clear();
				getHierarchy().addAll((Collection<? extends Role>)newValue);
				return;
			case RbacPackage.ROLE__PERMISSIONS:
				getPermissions().clear();
				getPermissions().addAll((Collection<? extends Permission>)newValue);
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
			case RbacPackage.ROLE__SSOD:
				getSsod().clear();
				return;
			case RbacPackage.ROLE__DSOD:
				getDsod().clear();
				return;
			case RbacPackage.ROLE__HIERARCHY:
				getHierarchy().clear();
				return;
			case RbacPackage.ROLE__PERMISSIONS:
				getPermissions().clear();
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
			case RbacPackage.ROLE__SSOD:
				return ssod != null && !ssod.isEmpty();
			case RbacPackage.ROLE__DSOD:
				return dsod != null && !dsod.isEmpty();
			case RbacPackage.ROLE__HIERARCHY:
				return hierarchy != null && !hierarchy.isEmpty();
			case RbacPackage.ROLE__PERMISSIONS:
				return permissions != null && !permissions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RoleImpl
