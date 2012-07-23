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
 * A representation of the model object '<em><b>User</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link lrbac.User#getSession <em>Session</em>}</li>
 *   <li>{@link lrbac.User#getRoles <em>Roles</em>}</li>
 *   <li>{@link lrbac.User#getDelegatees <em>Delegatees</em>}</li>
 *   <li>{@link lrbac.User#getDelegates <em>Delegates</em>}</li>
 * </ul>
 * </p>
 *
 * @see lrbac.LrbacPackage#getUser()
 * @model
 * @generated
 */
public interface User extends PolicyElement {
	/**
	 * Returns the value of the '<em><b>Session</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link lrbac.Session#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Session</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Session</em>' reference.
	 * @see #setSession(Session)
	 * @see lrbac.LrbacPackage#getUser_Session()
	 * @see lrbac.Session#getUser
	 * @model opposite="user"
	 * @generated
	 */
	Session getSession();

	/**
	 * Sets the value of the '{@link lrbac.User#getSession <em>Session</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Session</em>' reference.
	 * @see #getSession()
	 * @generated
	 */
	void setSession(Session value);

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' reference list.
	 * The list contents are of type {@link lrbac.Role}.
	 * It is bidirectional and its opposite is '{@link lrbac.Role#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' reference list.
	 * @see lrbac.LrbacPackage#getUser_Roles()
	 * @see lrbac.Role#getUsers
	 * @model opposite="users"
	 * @generated
	 */
	EList<Role> getRoles();

	/**
	 * Returns the value of the '<em><b>Delegatees</b></em>' reference list.
	 * The list contents are of type {@link lrbac.User}.
	 * It is bidirectional and its opposite is '{@link lrbac.User#getDelegates <em>Delegates</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegatees</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delegatees</em>' reference list.
	 * @see lrbac.LrbacPackage#getUser_Delegatees()
	 * @see lrbac.User#getDelegates
	 * @model opposite="delegates"
	 * @generated
	 */
	EList<User> getDelegatees();

	/**
	 * Returns the value of the '<em><b>Delegates</b></em>' reference list.
	 * The list contents are of type {@link lrbac.User}.
	 * It is bidirectional and its opposite is '{@link lrbac.User#getDelegatees <em>Delegatees</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Delegates</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Delegates</em>' reference list.
	 * @see lrbac.LrbacPackage#getUser_Delegates()
	 * @see lrbac.User#getDelegatees
	 * @model opposite="delegatees"
	 * @generated
	 */
	EList<User> getDelegates();

} // User
