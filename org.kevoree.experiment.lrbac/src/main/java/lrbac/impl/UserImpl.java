/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package lrbac.impl;

import java.util.Collection;

import lrbac.LrbacPackage;
import lrbac.Role;
import lrbac.Session;
import lrbac.User;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link lrbac.impl.UserImpl#getSession <em>Session</em>}</li>
 *   <li>{@link lrbac.impl.UserImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link lrbac.impl.UserImpl#getDelegatees <em>Delegatees</em>}</li>
 *   <li>{@link lrbac.impl.UserImpl#getDelegates <em>Delegates</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UserImpl extends PolicyElementImpl implements User {
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
	 * The cached value of the '{@link #getRoles() <em>Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> roles;

	/**
	 * The cached value of the '{@link #getDelegatees() <em>Delegatees</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelegatees()
	 * @generated
	 * @ordered
	 */
	protected EList<User> delegatees;

	/**
	 * The cached value of the '{@link #getDelegates() <em>Delegates</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDelegates()
	 * @generated
	 * @ordered
	 */
	protected EList<User> delegates;

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
		return LrbacPackage.Literals.USER;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, LrbacPackage.USER__SESSION, oldSession, session));
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
	public NotificationChain basicSetSession(Session newSession, NotificationChain msgs) {
		Session oldSession = session;
		session = newSession;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, LrbacPackage.USER__SESSION, oldSession, newSession);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSession(Session newSession) {
		if (newSession != session) {
			NotificationChain msgs = null;
			if (session != null)
				msgs = ((InternalEObject)session).eInverseRemove(this, LrbacPackage.SESSION__USER, Session.class, msgs);
			if (newSession != null)
				msgs = ((InternalEObject)newSession).eInverseAdd(this, LrbacPackage.SESSION__USER, Session.class, msgs);
			msgs = basicSetSession(newSession, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, LrbacPackage.USER__SESSION, newSession, newSession));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getRoles() {
		if (roles == null) {
			roles = new EObjectWithInverseResolvingEList.ManyInverse<Role>(Role.class, this, LrbacPackage.USER__ROLES, LrbacPackage.ROLE__USERS);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<User> getDelegatees() {
		if (delegatees == null) {
			delegatees = new EObjectWithInverseResolvingEList.ManyInverse<User>(User.class, this, LrbacPackage.USER__DELEGATEES, LrbacPackage.USER__DELEGATES);
		}
		return delegatees;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<User> getDelegates() {
		if (delegates == null) {
			delegates = new EObjectWithInverseResolvingEList.ManyInverse<User>(User.class, this, LrbacPackage.USER__DELEGATES, LrbacPackage.USER__DELEGATEES);
		}
		return delegates;
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
			case LrbacPackage.USER__SESSION:
				if (session != null)
					msgs = ((InternalEObject)session).eInverseRemove(this, LrbacPackage.SESSION__USER, Session.class, msgs);
				return basicSetSession((Session)otherEnd, msgs);
			case LrbacPackage.USER__ROLES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getRoles()).basicAdd(otherEnd, msgs);
			case LrbacPackage.USER__DELEGATEES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDelegatees()).basicAdd(otherEnd, msgs);
			case LrbacPackage.USER__DELEGATES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getDelegates()).basicAdd(otherEnd, msgs);
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
			case LrbacPackage.USER__SESSION:
				return basicSetSession(null, msgs);
			case LrbacPackage.USER__ROLES:
				return ((InternalEList<?>)getRoles()).basicRemove(otherEnd, msgs);
			case LrbacPackage.USER__DELEGATEES:
				return ((InternalEList<?>)getDelegatees()).basicRemove(otherEnd, msgs);
			case LrbacPackage.USER__DELEGATES:
				return ((InternalEList<?>)getDelegates()).basicRemove(otherEnd, msgs);
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
			case LrbacPackage.USER__SESSION:
				if (resolve) return getSession();
				return basicGetSession();
			case LrbacPackage.USER__ROLES:
				return getRoles();
			case LrbacPackage.USER__DELEGATEES:
				return getDelegatees();
			case LrbacPackage.USER__DELEGATES:
				return getDelegates();
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
			case LrbacPackage.USER__SESSION:
				setSession((Session)newValue);
				return;
			case LrbacPackage.USER__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection<? extends Role>)newValue);
				return;
			case LrbacPackage.USER__DELEGATEES:
				getDelegatees().clear();
				getDelegatees().addAll((Collection<? extends User>)newValue);
				return;
			case LrbacPackage.USER__DELEGATES:
				getDelegates().clear();
				getDelegates().addAll((Collection<? extends User>)newValue);
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
			case LrbacPackage.USER__SESSION:
				setSession((Session)null);
				return;
			case LrbacPackage.USER__ROLES:
				getRoles().clear();
				return;
			case LrbacPackage.USER__DELEGATEES:
				getDelegatees().clear();
				return;
			case LrbacPackage.USER__DELEGATES:
				getDelegates().clear();
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
			case LrbacPackage.USER__SESSION:
				return session != null;
			case LrbacPackage.USER__ROLES:
				return roles != null && !roles.isEmpty();
			case LrbacPackage.USER__DELEGATEES:
				return delegatees != null && !delegatees.isEmpty();
			case LrbacPackage.USER__DELEGATES:
				return delegates != null && !delegates.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UserImpl
