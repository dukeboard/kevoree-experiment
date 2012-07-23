/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package policy.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

import policy.Permission;
import policy.PolicyPackage;
import policy.Role;
import policy.Session;
import policy.User;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link policy.impl.RoleImpl#getUsers <em>Users</em>}</li>
 *   <li>{@link policy.impl.RoleImpl#getPermissions <em>Permissions</em>}</li>
 *   <li>{@link policy.impl.RoleImpl#getSsod <em>Ssod</em>}</li>
 *   <li>{@link policy.impl.RoleImpl#getSsodopp <em>Ssodopp</em>}</li>
 *   <li>{@link policy.impl.RoleImpl#getDsod <em>Dsod</em>}</li>
 *   <li>{@link policy.impl.RoleImpl#getDsodopp <em>Dsodopp</em>}</li>
 *   <li>{@link policy.impl.RoleImpl#getChildrenHierarchy <em>Children Hierarchy</em>}</li>
 *   <li>{@link policy.impl.RoleImpl#getParentsHierarchy <em>Parents Hierarchy</em>}</li>
 *   <li>{@link policy.impl.RoleImpl#getSessions <em>Sessions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleImpl extends PolicyElementImpl implements Role {
	/**
	 * The cached value of the '{@link #getUsers() <em>Users</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUsers()
	 * @generated
	 * @ordered
	 */
	protected EList<User> users;

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
	 * The cached value of the '{@link #getSsod() <em>Ssod</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSsod()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> ssod;

	/**
	 * The cached value of the '{@link #getSsodopp() <em>Ssodopp</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSsodopp()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> ssodopp;

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
	 * The cached value of the '{@link #getDsodopp() <em>Dsodopp</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDsodopp()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> dsodopp;

	/**
	 * The cached value of the '{@link #getChildrenHierarchy() <em>Children Hierarchy</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getChildrenHierarchy()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> childrenHierarchy;

	/**
	 * The cached value of the '{@link #getParentsHierarchy() <em>Parents Hierarchy</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getParentsHierarchy()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> parentsHierarchy;

	/**
	 * The cached value of the '{@link #getSessions() <em>Sessions</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSessions()
	 * @generated
	 * @ordered
	 */
	protected EList<Session> sessions;

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
		return PolicyPackage.Literals.ROLE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<User> getUsers() {
		if (users == null) {
			users = new EObjectWithInverseResolvingEList.ManyInverse<User>(User.class, this, PolicyPackage.ROLE__USERS, PolicyPackage.USER__ROLES);
		}
		return users;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Permission> getPermissions() {
		if (permissions == null) {
			permissions = new EObjectWithInverseResolvingEList.ManyInverse<Permission>(Permission.class, this, PolicyPackage.ROLE__PERMISSIONS, PolicyPackage.PERMISSION__ROLES);
		}
		return permissions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getSsod() {
		if (ssod == null) {
			ssod = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this, PolicyPackage.ROLE__SSOD, PolicyPackage.ROLE__SSODOPP);
		}
		return ssod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getSsodopp() {
		if (ssodopp == null) {
			ssodopp = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this, PolicyPackage.ROLE__SSODOPP, PolicyPackage.ROLE__SSOD);
		}
		return ssodopp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getDsod() {
		if (dsod == null) {
			dsod = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this, PolicyPackage.ROLE__DSOD, PolicyPackage.ROLE__DSODOPP);
		}
		return dsod;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getDsodopp() {
		if (dsodopp == null) {
			dsodopp = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this, PolicyPackage.ROLE__DSODOPP, PolicyPackage.ROLE__DSOD);
		}
		return dsodopp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getChildrenHierarchy() {
		if (childrenHierarchy == null) {
			childrenHierarchy = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this, PolicyPackage.ROLE__CHILDREN_HIERARCHY, PolicyPackage.ROLE__PARENTS_HIERARCHY);
		}
		return childrenHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getParentsHierarchy() {
		if (parentsHierarchy == null) {
			parentsHierarchy = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this, PolicyPackage.ROLE__PARENTS_HIERARCHY, PolicyPackage.ROLE__CHILDREN_HIERARCHY);
		}
		return parentsHierarchy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Session> getSessions() {
		if (sessions == null) {
			sessions = new EObjectWithInverseResolvingEList.ManyInverse<Session>(Session.class, this, PolicyPackage.ROLE__SESSIONS, PolicyPackage.SESSION__ROLES);
		}
		return sessions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PolicyPackage.ROLE__USERS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getUsers()).basicAdd(otherEnd, msgs);
			case PolicyPackage.ROLE__PERMISSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getPermissions()).basicAdd(otherEnd, msgs);
			case PolicyPackage.ROLE__SSOD:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSsod()).basicAdd(otherEnd, msgs);
			case PolicyPackage.ROLE__SSODOPP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSsodopp()).basicAdd(otherEnd, msgs);
			case PolicyPackage.ROLE__DSOD:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDsod()).basicAdd(otherEnd, msgs);
			case PolicyPackage.ROLE__DSODOPP:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDsodopp()).basicAdd(otherEnd, msgs);
			case PolicyPackage.ROLE__CHILDREN_HIERARCHY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getChildrenHierarchy()).basicAdd(otherEnd, msgs);
			case PolicyPackage.ROLE__PARENTS_HIERARCHY:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getParentsHierarchy()).basicAdd(otherEnd, msgs);
			case PolicyPackage.ROLE__SESSIONS:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSessions()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case PolicyPackage.ROLE__USERS:
				return ((InternalEList<?>)getUsers()).basicRemove(otherEnd, msgs);
			case PolicyPackage.ROLE__PERMISSIONS:
				return ((InternalEList<?>)getPermissions()).basicRemove(otherEnd, msgs);
			case PolicyPackage.ROLE__SSOD:
				return ((InternalEList<?>)getSsod()).basicRemove(otherEnd, msgs);
			case PolicyPackage.ROLE__SSODOPP:
				return ((InternalEList<?>)getSsodopp()).basicRemove(otherEnd, msgs);
			case PolicyPackage.ROLE__DSOD:
				return ((InternalEList<?>)getDsod()).basicRemove(otherEnd, msgs);
			case PolicyPackage.ROLE__DSODOPP:
				return ((InternalEList<?>)getDsodopp()).basicRemove(otherEnd, msgs);
			case PolicyPackage.ROLE__CHILDREN_HIERARCHY:
				return ((InternalEList<?>)getChildrenHierarchy()).basicRemove(otherEnd, msgs);
			case PolicyPackage.ROLE__PARENTS_HIERARCHY:
				return ((InternalEList<?>)getParentsHierarchy()).basicRemove(otherEnd, msgs);
			case PolicyPackage.ROLE__SESSIONS:
				return ((InternalEList<?>)getSessions()).basicRemove(otherEnd, msgs);
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
			case PolicyPackage.ROLE__USERS:
				return getUsers();
			case PolicyPackage.ROLE__PERMISSIONS:
				return getPermissions();
			case PolicyPackage.ROLE__SSOD:
				return getSsod();
			case PolicyPackage.ROLE__SSODOPP:
				return getSsodopp();
			case PolicyPackage.ROLE__DSOD:
				return getDsod();
			case PolicyPackage.ROLE__DSODOPP:
				return getDsodopp();
			case PolicyPackage.ROLE__CHILDREN_HIERARCHY:
				return getChildrenHierarchy();
			case PolicyPackage.ROLE__PARENTS_HIERARCHY:
				return getParentsHierarchy();
			case PolicyPackage.ROLE__SESSIONS:
				return getSessions();
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
			case PolicyPackage.ROLE__USERS:
				getUsers().clear();
				getUsers().addAll((Collection<? extends User>)newValue);
				return;
			case PolicyPackage.ROLE__PERMISSIONS:
				getPermissions().clear();
				getPermissions().addAll((Collection<? extends Permission>)newValue);
				return;
			case PolicyPackage.ROLE__SSOD:
				getSsod().clear();
				getSsod().addAll((Collection<? extends Role>)newValue);
				return;
			case PolicyPackage.ROLE__SSODOPP:
				getSsodopp().clear();
				getSsodopp().addAll((Collection<? extends Role>)newValue);
				return;
			case PolicyPackage.ROLE__DSOD:
				getDsod().clear();
				getDsod().addAll((Collection<? extends Role>)newValue);
				return;
			case PolicyPackage.ROLE__DSODOPP:
				getDsodopp().clear();
				getDsodopp().addAll((Collection<? extends Role>)newValue);
				return;
			case PolicyPackage.ROLE__CHILDREN_HIERARCHY:
				getChildrenHierarchy().clear();
				getChildrenHierarchy().addAll((Collection<? extends Role>)newValue);
				return;
			case PolicyPackage.ROLE__PARENTS_HIERARCHY:
				getParentsHierarchy().clear();
				getParentsHierarchy().addAll((Collection<? extends Role>)newValue);
				return;
			case PolicyPackage.ROLE__SESSIONS:
				getSessions().clear();
				getSessions().addAll((Collection<? extends Session>)newValue);
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
			case PolicyPackage.ROLE__USERS:
				getUsers().clear();
				return;
			case PolicyPackage.ROLE__PERMISSIONS:
				getPermissions().clear();
				return;
			case PolicyPackage.ROLE__SSOD:
				getSsod().clear();
				return;
			case PolicyPackage.ROLE__SSODOPP:
				getSsodopp().clear();
				return;
			case PolicyPackage.ROLE__DSOD:
				getDsod().clear();
				return;
			case PolicyPackage.ROLE__DSODOPP:
				getDsodopp().clear();
				return;
			case PolicyPackage.ROLE__CHILDREN_HIERARCHY:
				getChildrenHierarchy().clear();
				return;
			case PolicyPackage.ROLE__PARENTS_HIERARCHY:
				getParentsHierarchy().clear();
				return;
			case PolicyPackage.ROLE__SESSIONS:
				getSessions().clear();
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
			case PolicyPackage.ROLE__USERS:
				return users != null && !users.isEmpty();
			case PolicyPackage.ROLE__PERMISSIONS:
				return permissions != null && !permissions.isEmpty();
			case PolicyPackage.ROLE__SSOD:
				return ssod != null && !ssod.isEmpty();
			case PolicyPackage.ROLE__SSODOPP:
				return ssodopp != null && !ssodopp.isEmpty();
			case PolicyPackage.ROLE__DSOD:
				return dsod != null && !dsod.isEmpty();
			case PolicyPackage.ROLE__DSODOPP:
				return dsodopp != null && !dsodopp.isEmpty();
			case PolicyPackage.ROLE__CHILDREN_HIERARCHY:
				return childrenHierarchy != null && !childrenHierarchy.isEmpty();
			case PolicyPackage.ROLE__PARENTS_HIERARCHY:
				return parentsHierarchy != null && !parentsHierarchy.isEmpty();
			case PolicyPackage.ROLE__SESSIONS:
				return sessions != null && !sessions.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //RoleImpl
