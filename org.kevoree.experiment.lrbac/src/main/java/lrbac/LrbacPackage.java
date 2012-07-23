/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package lrbac;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see lrbac.LrbacFactory
 * @model kind="package"
 * @generated
 */
public interface LrbacPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "lrbac";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://lrbac/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "lrbac";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	LrbacPackage eINSTANCE = lrbac.impl.LrbacPackageImpl.init();

	/**
	 * The meta object id for the '{@link lrbac.impl.PolicyElementImpl <em>Policy Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.PolicyElementImpl
	 * @see lrbac.impl.LrbacPackageImpl#getPolicyElement()
	 * @generated
	 */
	int POLICY_ELEMENT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ELEMENT__NAME = 0;

	/**
	 * The number of structural features of the '<em>Policy Element</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_ELEMENT_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link lrbac.impl.PolicyImpl <em>Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.PolicyImpl
	 * @see lrbac.impl.LrbacPackageImpl#getPolicy()
	 * @generated
	 */
	int POLICY = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY__ELEMENTS = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link lrbac.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.UserImpl
	 * @see lrbac.impl.LrbacPackageImpl#getUser()
	 * @generated
	 */
	int USER = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Session</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__SESSION = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__ROLES = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Delegatees</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DELEGATEES = POLICY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Delegates</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DELEGATES = POLICY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link lrbac.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.RoleImpl
	 * @see lrbac.impl.LrbacPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Users</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__USERS = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PERMISSIONS = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Ssod</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SSOD = POLICY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Ssodopp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SSODOPP = POLICY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The feature id for the '<em><b>Dsod</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DSOD = POLICY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The feature id for the '<em><b>Dsodopp</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DSODOPP = POLICY_ELEMENT_FEATURE_COUNT + 5;

	/**
	 * The feature id for the '<em><b>Children Hierarchy</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__CHILDREN_HIERARCHY = POLICY_ELEMENT_FEATURE_COUNT + 6;

	/**
	 * The feature id for the '<em><b>Parents Hierarchy</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PARENTS_HIERARCHY = POLICY_ELEMENT_FEATURE_COUNT + 7;

	/**
	 * The feature id for the '<em><b>Sessions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SESSIONS = POLICY_ELEMENT_FEATURE_COUNT + 8;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 9;

	/**
	 * The meta object id for the '{@link lrbac.impl.PermissionImpl <em>Permission</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.PermissionImpl
	 * @see lrbac.impl.LrbacPackageImpl#getPermission()
	 * @generated
	 */
	int PERMISSION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION__ROLES = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION__OPERATIONS = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Constraints</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION__CONSTRAINTS = POLICY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link lrbac.impl.ObjectImpl <em>Object</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.ObjectImpl
	 * @see lrbac.impl.LrbacPackageImpl#getObject()
	 * @generated
	 */
	int OBJECT = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT__OPERATIONS = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Object</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OBJECT_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link lrbac.impl.SessionImpl <em>Session</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.SessionImpl
	 * @see lrbac.impl.LrbacPackageImpl#getSession()
	 * @generated
	 */
	int SESSION = 6;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>User</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__USER = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__ROLES = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Session</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link lrbac.impl.ConstraintImpl <em>Constraint</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.ConstraintImpl
	 * @see lrbac.impl.LrbacPackageImpl#getConstraint()
	 * @generated
	 */
	int CONSTRAINT = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT__PERMISSIONS = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Constraint</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONSTRAINT_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link lrbac.impl.LocationImpl <em>Location</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.LocationImpl
	 * @see lrbac.impl.LrbacPackageImpl#getLocation()
	 * @generated
	 */
	int LOCATION = 8;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__NAME = CONSTRAINT__NAME;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION__PERMISSIONS = CONSTRAINT__PERMISSIONS;

	/**
	 * The number of structural features of the '<em>Location</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LOCATION_FEATURE_COUNT = CONSTRAINT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link lrbac.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see lrbac.impl.OperationImpl
	 * @see lrbac.impl.LrbacPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 9;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Objects</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__OBJECTS = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__PERMISSIONS = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 2;


	/**
	 * Returns the meta object for class '{@link lrbac.PolicyElement <em>Policy Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Policy Element</em>'.
	 * @see lrbac.PolicyElement
	 * @generated
	 */
	EClass getPolicyElement();

	/**
	 * Returns the meta object for the attribute '{@link lrbac.PolicyElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see lrbac.PolicyElement#getName()
	 * @see #getPolicyElement()
	 * @generated
	 */
	EAttribute getPolicyElement_Name();

	/**
	 * Returns the meta object for class '{@link lrbac.Policy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Policy</em>'.
	 * @see lrbac.Policy
	 * @generated
	 */
	EClass getPolicy();

	/**
	 * Returns the meta object for the containment reference list '{@link lrbac.Policy#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see lrbac.Policy#getElements()
	 * @see #getPolicy()
	 * @generated
	 */
	EReference getPolicy_Elements();

	/**
	 * Returns the meta object for class '{@link lrbac.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see lrbac.User
	 * @generated
	 */
	EClass getUser();

	/**
	 * Returns the meta object for the reference '{@link lrbac.User#getSession <em>Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Session</em>'.
	 * @see lrbac.User#getSession()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Session();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.User#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roles</em>'.
	 * @see lrbac.User#getRoles()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Roles();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.User#getDelegatees <em>Delegatees</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Delegatees</em>'.
	 * @see lrbac.User#getDelegatees()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Delegatees();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.User#getDelegates <em>Delegates</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Delegates</em>'.
	 * @see lrbac.User#getDelegates()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Delegates();

	/**
	 * Returns the meta object for class '{@link lrbac.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see lrbac.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getUsers <em>Users</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Users</em>'.
	 * @see lrbac.Role#getUsers()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Users();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Permissions</em>'.
	 * @see lrbac.Role#getPermissions()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Permissions();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getSsod <em>Ssod</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ssod</em>'.
	 * @see lrbac.Role#getSsod()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Ssod();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getSsodopp <em>Ssodopp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ssodopp</em>'.
	 * @see lrbac.Role#getSsodopp()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Ssodopp();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getDsod <em>Dsod</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dsod</em>'.
	 * @see lrbac.Role#getDsod()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Dsod();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getDsodopp <em>Dsodopp</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dsodopp</em>'.
	 * @see lrbac.Role#getDsodopp()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Dsodopp();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getChildrenHierarchy <em>Children Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children Hierarchy</em>'.
	 * @see lrbac.Role#getChildrenHierarchy()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_ChildrenHierarchy();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getParentsHierarchy <em>Parents Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Parents Hierarchy</em>'.
	 * @see lrbac.Role#getParentsHierarchy()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_ParentsHierarchy();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Role#getSessions <em>Sessions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Sessions</em>'.
	 * @see lrbac.Role#getSessions()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Sessions();

	/**
	 * Returns the meta object for class '{@link lrbac.Permission <em>Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Permission</em>'.
	 * @see lrbac.Permission
	 * @generated
	 */
	EClass getPermission();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Permission#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roles</em>'.
	 * @see lrbac.Permission#getRoles()
	 * @see #getPermission()
	 * @generated
	 */
	EReference getPermission_Roles();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Permission#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Operations</em>'.
	 * @see lrbac.Permission#getOperations()
	 * @see #getPermission()
	 * @generated
	 */
	EReference getPermission_Operations();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Permission#getConstraints <em>Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Constraints</em>'.
	 * @see lrbac.Permission#getConstraints()
	 * @see #getPermission()
	 * @generated
	 */
	EReference getPermission_Constraints();

	/**
	 * Returns the meta object for class '{@link lrbac.Object <em>Object</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Object</em>'.
	 * @see lrbac.Object
	 * @generated
	 */
	EClass getObject();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Object#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Operations</em>'.
	 * @see lrbac.Object#getOperations()
	 * @see #getObject()
	 * @generated
	 */
	EReference getObject_Operations();

	/**
	 * Returns the meta object for class '{@link lrbac.Session <em>Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Session</em>'.
	 * @see lrbac.Session
	 * @generated
	 */
	EClass getSession();

	/**
	 * Returns the meta object for the reference '{@link lrbac.Session#getUser <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>User</em>'.
	 * @see lrbac.Session#getUser()
	 * @see #getSession()
	 * @generated
	 */
	EReference getSession_User();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Session#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roles</em>'.
	 * @see lrbac.Session#getRoles()
	 * @see #getSession()
	 * @generated
	 */
	EReference getSession_Roles();

	/**
	 * Returns the meta object for class '{@link lrbac.Constraint <em>Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Constraint</em>'.
	 * @see lrbac.Constraint
	 * @generated
	 */
	EClass getConstraint();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Constraint#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Permissions</em>'.
	 * @see lrbac.Constraint#getPermissions()
	 * @see #getConstraint()
	 * @generated
	 */
	EReference getConstraint_Permissions();

	/**
	 * Returns the meta object for class '{@link lrbac.Location <em>Location</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Location</em>'.
	 * @see lrbac.Location
	 * @generated
	 */
	EClass getLocation();

	/**
	 * Returns the meta object for class '{@link lrbac.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see lrbac.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Operation#getObjects <em>Objects</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Objects</em>'.
	 * @see lrbac.Operation#getObjects()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Objects();

	/**
	 * Returns the meta object for the reference list '{@link lrbac.Operation#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Permissions</em>'.
	 * @see lrbac.Operation#getPermissions()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Permissions();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	LrbacFactory getLrbacFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link lrbac.impl.PolicyElementImpl <em>Policy Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.PolicyElementImpl
		 * @see lrbac.impl.LrbacPackageImpl#getPolicyElement()
		 * @generated
		 */
		EClass POLICY_ELEMENT = eINSTANCE.getPolicyElement();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POLICY_ELEMENT__NAME = eINSTANCE.getPolicyElement_Name();

		/**
		 * The meta object literal for the '{@link lrbac.impl.PolicyImpl <em>Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.PolicyImpl
		 * @see lrbac.impl.LrbacPackageImpl#getPolicy()
		 * @generated
		 */
		EClass POLICY = eINSTANCE.getPolicy();

		/**
		 * The meta object literal for the '<em><b>Elements</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference POLICY__ELEMENTS = eINSTANCE.getPolicy_Elements();

		/**
		 * The meta object literal for the '{@link lrbac.impl.UserImpl <em>User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.UserImpl
		 * @see lrbac.impl.LrbacPackageImpl#getUser()
		 * @generated
		 */
		EClass USER = eINSTANCE.getUser();

		/**
		 * The meta object literal for the '<em><b>Session</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__SESSION = eINSTANCE.getUser_Session();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__ROLES = eINSTANCE.getUser_Roles();

		/**
		 * The meta object literal for the '<em><b>Delegatees</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__DELEGATEES = eINSTANCE.getUser_Delegatees();

		/**
		 * The meta object literal for the '<em><b>Delegates</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__DELEGATES = eINSTANCE.getUser_Delegates();

		/**
		 * The meta object literal for the '{@link lrbac.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.RoleImpl
		 * @see lrbac.impl.LrbacPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '<em><b>Users</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__USERS = eINSTANCE.getRole_Users();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__PERMISSIONS = eINSTANCE.getRole_Permissions();

		/**
		 * The meta object literal for the '<em><b>Ssod</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__SSOD = eINSTANCE.getRole_Ssod();

		/**
		 * The meta object literal for the '<em><b>Ssodopp</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__SSODOPP = eINSTANCE.getRole_Ssodopp();

		/**
		 * The meta object literal for the '<em><b>Dsod</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__DSOD = eINSTANCE.getRole_Dsod();

		/**
		 * The meta object literal for the '<em><b>Dsodopp</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__DSODOPP = eINSTANCE.getRole_Dsodopp();

		/**
		 * The meta object literal for the '<em><b>Children Hierarchy</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__CHILDREN_HIERARCHY = eINSTANCE.getRole_ChildrenHierarchy();

		/**
		 * The meta object literal for the '<em><b>Parents Hierarchy</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__PARENTS_HIERARCHY = eINSTANCE.getRole_ParentsHierarchy();

		/**
		 * The meta object literal for the '<em><b>Sessions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__SESSIONS = eINSTANCE.getRole_Sessions();

		/**
		 * The meta object literal for the '{@link lrbac.impl.PermissionImpl <em>Permission</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.PermissionImpl
		 * @see lrbac.impl.LrbacPackageImpl#getPermission()
		 * @generated
		 */
		EClass PERMISSION = eINSTANCE.getPermission();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERMISSION__ROLES = eINSTANCE.getPermission_Roles();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERMISSION__OPERATIONS = eINSTANCE.getPermission_Operations();

		/**
		 * The meta object literal for the '<em><b>Constraints</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERMISSION__CONSTRAINTS = eINSTANCE.getPermission_Constraints();

		/**
		 * The meta object literal for the '{@link lrbac.impl.ObjectImpl <em>Object</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.ObjectImpl
		 * @see lrbac.impl.LrbacPackageImpl#getObject()
		 * @generated
		 */
		EClass OBJECT = eINSTANCE.getObject();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OBJECT__OPERATIONS = eINSTANCE.getObject_Operations();

		/**
		 * The meta object literal for the '{@link lrbac.impl.SessionImpl <em>Session</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.SessionImpl
		 * @see lrbac.impl.LrbacPackageImpl#getSession()
		 * @generated
		 */
		EClass SESSION = eINSTANCE.getSession();

		/**
		 * The meta object literal for the '<em><b>User</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SESSION__USER = eINSTANCE.getSession_User();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SESSION__ROLES = eINSTANCE.getSession_Roles();

		/**
		 * The meta object literal for the '{@link lrbac.impl.ConstraintImpl <em>Constraint</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.ConstraintImpl
		 * @see lrbac.impl.LrbacPackageImpl#getConstraint()
		 * @generated
		 */
		EClass CONSTRAINT = eINSTANCE.getConstraint();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONSTRAINT__PERMISSIONS = eINSTANCE.getConstraint_Permissions();

		/**
		 * The meta object literal for the '{@link lrbac.impl.LocationImpl <em>Location</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.LocationImpl
		 * @see lrbac.impl.LrbacPackageImpl#getLocation()
		 * @generated
		 */
		EClass LOCATION = eINSTANCE.getLocation();

		/**
		 * The meta object literal for the '{@link lrbac.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see lrbac.impl.OperationImpl
		 * @see lrbac.impl.LrbacPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Objects</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__OBJECTS = eINSTANCE.getOperation_Objects();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__PERMISSIONS = eINSTANCE.getOperation_Permissions();

	}

} //LrbacPackage
