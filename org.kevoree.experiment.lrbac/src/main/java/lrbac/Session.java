/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package lrbac;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Session</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lrbac.Session#getUser <em>User</em>}</li>
 *   <li>{@link lrbac.Session#getRoles <em>Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @see lrbac.LrbacPackage#getSession()
 * @model
 * @generated
 */
public interface Session extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>User</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link lrbac.User#getSession <em>Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>User</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>User</em>' reference.
	 * @see #setUser(User)
	 * @see lrbac.LrbacPackage#getSession_User()
	 * @see lrbac.User#getSession
	 * @model opposite="session"
	 * @generated
	 */
	User getUser();

	/**
	 * Sets the value of the '{@link lrbac.Session#getUser <em>User</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>User</em>' reference.
	 * @see #getUser()
	 * @generated
	 */
	void setUser(User value);

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Role}.
	 * It is bidirectional and its opposite is '{@link lrbac.Role#getSessions <em>Sessions</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' reference list.
	 * @see lrbac.LrbacPackage#getSession_Roles()
	 * @see lrbac.Role#getSessions
	 * @model opposite="sessions"
	 * @generated
	 */
	EList<Role> getRoles();

} // Session
