/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package rbac.rbac;

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
 * @see rbac.rbac.RbacFactory
 * @model kind="package"
 * @generated
 */
public interface RbacPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "rbac";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "rbac.rbac";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "rbac.rbac";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	RbacPackage eINSTANCE = rbac.rbac.impl.RbacPackageImpl.init();

	/**
	 * The meta object id for the '{@link rbac.rbac.impl.PolicyImpl <em>Policy</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rbac.rbac.impl.PolicyImpl
	 * @see rbac.rbac.impl.RbacPackageImpl#getPolicy()
	 * @generated
	 */
	int POLICY = 0;

	/**
	 * The feature id for the '<em><b>Elements</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY__ELEMENTS = 0;

	/**
	 * The number of structural features of the '<em>Policy</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POLICY_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link rbac.rbac.impl.PolicyElementImpl <em>Policy Element</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rbac.rbac.impl.PolicyElementImpl
	 * @see rbac.rbac.impl.RbacPackageImpl#getPolicyElement()
	 * @generated
	 */
	int POLICY_ELEMENT = 6;

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
	 * The meta object id for the '{@link rbac.rbac.impl.UserImpl <em>User</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rbac.rbac.impl.UserImpl
	 * @see rbac.rbac.impl.RbacPackageImpl#getUser()
	 * @generated
	 */
	int USER = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Assigned Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__ASSIGNED_ROLES = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Delegations</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__DELEGATIONS = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Session</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER__SESSION = POLICY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The number of structural features of the '<em>User</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int USER_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The meta object id for the '{@link rbac.rbac.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rbac.rbac.impl.RoleImpl
	 * @see rbac.rbac.impl.RbacPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Ssod</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__SSOD = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Dsod</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__DSOD = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The feature id for the '<em><b>Hierarchy</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__HIERARCHY = POLICY_ELEMENT_FEATURE_COUNT + 2;

	/**
	 * The feature id for the '<em><b>Permissions</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE__PERMISSIONS = POLICY_ELEMENT_FEATURE_COUNT + 3;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 4;

	/**
	 * The meta object id for the '{@link rbac.rbac.impl.PermissionImpl <em>Permission</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rbac.rbac.impl.PermissionImpl
	 * @see rbac.rbac.impl.RbacPackageImpl#getPermission()
	 * @generated
	 */
	int PERMISSION = 3;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Operations</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION__OPERATIONS = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Permission</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int PERMISSION_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rbac.rbac.impl.OperationImpl <em>Operation</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rbac.rbac.impl.OperationImpl
	 * @see rbac.rbac.impl.RbacPackageImpl#getOperation()
	 * @generated
	 */
	int OPERATION = 4;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Resources</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION__RESOURCES = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Operation</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int OPERATION_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link rbac.rbac.impl.ResourceImpl <em>Resource</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rbac.rbac.impl.ResourceImpl
	 * @see rbac.rbac.impl.RbacPackageImpl#getResource()
	 * @generated
	 */
	int RESOURCE = 5;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The number of structural features of the '<em>Resource</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RESOURCE_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link rbac.rbac.impl.SessionImpl <em>Session</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see rbac.rbac.impl.SessionImpl
	 * @see rbac.rbac.impl.RbacPackageImpl#getSession()
	 * @generated
	 */
	int SESSION = 7;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__NAME = POLICY_ELEMENT__NAME;

	/**
	 * The feature id for the '<em><b>Active Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION__ACTIVE_ROLES = POLICY_ELEMENT_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Session</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SESSION_FEATURE_COUNT = POLICY_ELEMENT_FEATURE_COUNT + 1;


	/**
	 * Returns the meta object for class '{@link rbac.rbac.Policy <em>Policy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Policy</em>'.
	 * @see rbac.rbac.Policy
	 * @generated
	 */
	EClass getPolicy();

	/**
	 * Returns the meta object for the containment reference list '{@link rbac.rbac.Policy#getElements <em>Elements</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Elements</em>'.
	 * @see rbac.rbac.Policy#getElements()
	 * @see #getPolicy()
	 * @generated
	 */
	EReference getPolicy_Elements();

	/**
	 * Returns the meta object for class '{@link rbac.rbac.User <em>User</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>User</em>'.
	 * @see rbac.rbac.User
	 * @generated
	 */
	EClass getUser();

	/**
	 * Returns the meta object for the reference list '{@link rbac.rbac.User#getAssignedRoles <em>Assigned Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Assigned Roles</em>'.
	 * @see rbac.rbac.User#getAssignedRoles()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_AssignedRoles();

	/**
	 * Returns the meta object for the reference list '{@link rbac.rbac.User#getDelegations <em>Delegations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Delegations</em>'.
	 * @see rbac.rbac.User#getDelegations()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Delegations();

	/**
	 * Returns the meta object for the reference '{@link rbac.rbac.User#getSession <em>Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Session</em>'.
	 * @see rbac.rbac.User#getSession()
	 * @see #getUser()
	 * @generated
	 */
	EReference getUser_Session();

	/**
	 * Returns the meta object for class '{@link rbac.rbac.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see rbac.rbac.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for the reference list '{@link rbac.rbac.Role#getSsod <em>Ssod</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Ssod</em>'.
	 * @see rbac.rbac.Role#getSsod()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Ssod();

	/**
	 * Returns the meta object for the reference list '{@link rbac.rbac.Role#getDsod <em>Dsod</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Dsod</em>'.
	 * @see rbac.rbac.Role#getDsod()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Dsod();

	/**
	 * Returns the meta object for the reference list '{@link rbac.rbac.Role#getHierarchy <em>Hierarchy</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Hierarchy</em>'.
	 * @see rbac.rbac.Role#getHierarchy()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Hierarchy();

	/**
	 * Returns the meta object for the reference list '{@link rbac.rbac.Role#getPermissions <em>Permissions</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Permissions</em>'.
	 * @see rbac.rbac.Role#getPermissions()
	 * @see #getRole()
	 * @generated
	 */
	EReference getRole_Permissions();

	/**
	 * Returns the meta object for class '{@link rbac.rbac.Permission <em>Permission</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Permission</em>'.
	 * @see rbac.rbac.Permission
	 * @generated
	 */
	EClass getPermission();

	/**
	 * Returns the meta object for the containment reference list '{@link rbac.rbac.Permission#getOperations <em>Operations</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Operations</em>'.
	 * @see rbac.rbac.Permission#getOperations()
	 * @see #getPermission()
	 * @generated
	 */
	EReference getPermission_Operations();

	/**
	 * Returns the meta object for class '{@link rbac.rbac.Operation <em>Operation</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Operation</em>'.
	 * @see rbac.rbac.Operation
	 * @generated
	 */
	EClass getOperation();

	/**
	 * Returns the meta object for the reference list '{@link rbac.rbac.Operation#getResources <em>Resources</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Resources</em>'.
	 * @see rbac.rbac.Operation#getResources()
	 * @see #getOperation()
	 * @generated
	 */
	EReference getOperation_Resources();

	/**
	 * Returns the meta object for class '{@link rbac.rbac.Resource <em>Resource</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Resource</em>'.
	 * @see rbac.rbac.Resource
	 * @generated
	 */
	EClass getResource();

	/**
	 * Returns the meta object for class '{@link rbac.rbac.PolicyElement <em>Policy Element</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Policy Element</em>'.
	 * @see rbac.rbac.PolicyElement
	 * @generated
	 */
	EClass getPolicyElement();

	/**
	 * Returns the meta object for the attribute '{@link rbac.rbac.PolicyElement#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see rbac.rbac.PolicyElement#getName()
	 * @see #getPolicyElement()
	 * @generated
	 */
	EAttribute getPolicyElement_Name();

	/**
	 * Returns the meta object for class '{@link rbac.rbac.Session <em>Session</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Session</em>'.
	 * @see rbac.rbac.Session
	 * @generated
	 */
	EClass getSession();

	/**
	 * Returns the meta object for the reference list '{@link rbac.rbac.Session#getActiveRoles <em>Active Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Active Roles</em>'.
	 * @see rbac.rbac.Session#getActiveRoles()
	 * @see #getSession()
	 * @generated
	 */
	EReference getSession_ActiveRoles();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	RbacFactory getRbacFactory();

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
		 * The meta object literal for the '{@link rbac.rbac.impl.PolicyImpl <em>Policy</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rbac.rbac.impl.PolicyImpl
		 * @see rbac.rbac.impl.RbacPackageImpl#getPolicy()
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
		 * The meta object literal for the '{@link rbac.rbac.impl.UserImpl <em>User</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rbac.rbac.impl.UserImpl
		 * @see rbac.rbac.impl.RbacPackageImpl#getUser()
		 * @generated
		 */
		EClass USER = eINSTANCE.getUser();

		/**
		 * The meta object literal for the '<em><b>Assigned Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__ASSIGNED_ROLES = eINSTANCE.getUser_AssignedRoles();

		/**
		 * The meta object literal for the '<em><b>Delegations</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__DELEGATIONS = eINSTANCE.getUser_Delegations();

		/**
		 * The meta object literal for the '<em><b>Session</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference USER__SESSION = eINSTANCE.getUser_Session();

		/**
		 * The meta object literal for the '{@link rbac.rbac.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rbac.rbac.impl.RoleImpl
		 * @see rbac.rbac.impl.RbacPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '<em><b>Ssod</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__SSOD = eINSTANCE.getRole_Ssod();

		/**
		 * The meta object literal for the '<em><b>Dsod</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__DSOD = eINSTANCE.getRole_Dsod();

		/**
		 * The meta object literal for the '<em><b>Hierarchy</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__HIERARCHY = eINSTANCE.getRole_Hierarchy();

		/**
		 * The meta object literal for the '<em><b>Permissions</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROLE__PERMISSIONS = eINSTANCE.getRole_Permissions();

		/**
		 * The meta object literal for the '{@link rbac.rbac.impl.PermissionImpl <em>Permission</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rbac.rbac.impl.PermissionImpl
		 * @see rbac.rbac.impl.RbacPackageImpl#getPermission()
		 * @generated
		 */
		EClass PERMISSION = eINSTANCE.getPermission();

		/**
		 * The meta object literal for the '<em><b>Operations</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference PERMISSION__OPERATIONS = eINSTANCE.getPermission_Operations();

		/**
		 * The meta object literal for the '{@link rbac.rbac.impl.OperationImpl <em>Operation</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rbac.rbac.impl.OperationImpl
		 * @see rbac.rbac.impl.RbacPackageImpl#getOperation()
		 * @generated
		 */
		EClass OPERATION = eINSTANCE.getOperation();

		/**
		 * The meta object literal for the '<em><b>Resources</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference OPERATION__RESOURCES = eINSTANCE.getOperation_Resources();

		/**
		 * The meta object literal for the '{@link rbac.rbac.impl.ResourceImpl <em>Resource</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rbac.rbac.impl.ResourceImpl
		 * @see rbac.rbac.impl.RbacPackageImpl#getResource()
		 * @generated
		 */
		EClass RESOURCE = eINSTANCE.getResource();

		/**
		 * The meta object literal for the '{@link rbac.rbac.impl.PolicyElementImpl <em>Policy Element</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rbac.rbac.impl.PolicyElementImpl
		 * @see rbac.rbac.impl.RbacPackageImpl#getPolicyElement()
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
		 * The meta object literal for the '{@link rbac.rbac.impl.SessionImpl <em>Session</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see rbac.rbac.impl.SessionImpl
		 * @see rbac.rbac.impl.RbacPackageImpl#getSession()
		 * @generated
		 */
		EClass SESSION = eINSTANCE.getSession();

		/**
		 * The meta object literal for the '<em><b>Active Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference SESSION__ACTIVE_ROLES = eINSTANCE.getSession_ActiveRoles();

	}

} //RbacPackage
