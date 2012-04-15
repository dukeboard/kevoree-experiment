/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rbac.rbac.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import rbac.RbacPackage;
import rbac.Role;
import rbac.Session;
import rbac.User;



/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link rbac.rbac.impl.UserImpl#getAssignedRoles <em>Assigned Roles</em>}</li>
 *   <li>{@link rbac.rbac.impl.UserImpl#getDelegations <em>Delegations</em>}</li>
 *   <li>{@link rbac.rbac.impl.UserImpl#getSession <em>Session</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserImpl extends PolicyElementImpl implements User {
	/**
	 * The cached value of the '{@link #getAssignedRoles() <em>Assigned Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssignedRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> assignedRoles;

	/**
	 * The cached value of the '{@link #getDelegations() <em>Delegations</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelegations()
	 * @generated
	 * @ordered
	 */
	protected EList<User> delegations;

	/**
	 * The cached value of the '{@link #getSession() <em>Session</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSession()
	 * @generated
	 * @ordered
	 */
	protected Session session;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UserImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return RbacPackage.Literals.USER;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getAssignedRoles() {
		if (assignedRoles == null) {
			assignedRoles = new EObjectResolvingEList<Role>(Role.class, this, RbacPackage.USER__ASSIGNED_ROLES);
		}
		return assignedRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<User> getDelegations() {
		if (delegations == null) {
			delegations = new EObjectResolvingEList<User>(User.class, this, RbacPackage.USER__DELEGATIONS);
		}
		return delegations;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Session getSession() {
		if (session != null && session.eIsProxy()) {
			InternalEObject oldSession = (InternalEObject)session;
			session = (Session)eResolveProxy(oldSession);
			if (session != oldSession) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, RbacPackage.USER__SESSION, oldSession, session));
			}
		}
		return session;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Session basicGetSession() {
		return session;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSession(Session newSession) {
		Session oldSession = session;
		session = newSession;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, RbacPackage.USER__SESSION, oldSession, session));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case RbacPackage.USER__ASSIGNED_ROLES:
				return getAssignedRoles();
			case RbacPackage.USER__DELEGATIONS:
				return getDelegations();
			case RbacPackage.USER__SESSION:
				if (resolve) return getSession();
				return basicGetSession();
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
			case RbacPackage.USER__ASSIGNED_ROLES:
				getAssignedRoles().clear();
				getAssignedRoles().addAll((Collection<? extends Role>)newValue);
				return;
			case RbacPackage.USER__DELEGATIONS:
				getDelegations().clear();
				getDelegations().addAll((Collection<? extends User>)newValue);
				return;
			case RbacPackage.USER__SESSION:
				setSession((Session)newValue);
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
			case RbacPackage.USER__ASSIGNED_ROLES:
				getAssignedRoles().clear();
				return;
			case RbacPackage.USER__DELEGATIONS:
				getDelegations().clear();
				return;
			case RbacPackage.USER__SESSION:
				setSession((Session)null);
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
			case RbacPackage.USER__ASSIGNED_ROLES:
				return assignedRoles != null && !assignedRoles.isEmpty();
			case RbacPackage.USER__DELEGATIONS:
				return delegations != null && !delegations.isEmpty();
			case RbacPackage.USER__SESSION:
				return session != null;
		}
		return super.eIsSet(featureID);
	}

} //UserImpl
