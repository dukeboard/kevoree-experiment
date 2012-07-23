/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package lrbac.impl;

import lrbac.Constraint;
import lrbac.Location;
import lrbac.LrbacFactory;
import lrbac.LrbacPackage;
import lrbac.Operation;
import lrbac.Permission;
import lrbac.Policy;
import lrbac.PolicyElement;
import lrbac.Role;
import lrbac.Session;
import lrbac.User;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LrbacFactoryImpl extends EFactoryImpl implements LrbacFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static LrbacFactory init() {
		try {
			LrbacFactory theLrbacFactory = (LrbacFactory)EPackage.Registry.INSTANCE.getEFactory("http://lrbac/1.0"); 
			if (theLrbacFactory != null) {
				return theLrbacFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new LrbacFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LrbacFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LrbacPackage.POLICY_ELEMENT: return createPolicyElement();
			case LrbacPackage.POLICY: return createPolicy();
			case LrbacPackage.USER: return createUser();
			case LrbacPackage.ROLE: return createRole();
			case LrbacPackage.PERMISSION: return createPermission();
			case LrbacPackage.OBJECT: return createObject();
			case LrbacPackage.SESSION: return createSession();
			case LrbacPackage.CONSTRAINT: return createConstraint();
			case LrbacPackage.LOCATION: return createLocation();
			case LrbacPackage.OPERATION: return createOperation();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public PolicyElement createPolicyElement() {
		PolicyElementImpl policyElement = new PolicyElementImpl();
		return policyElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Policy createPolicy() {
		PolicyImpl policy = new PolicyImpl();
		return policy;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public User createUser() {
		UserImpl user = new UserImpl();
		return user;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Role createRole() {
		RoleImpl role = new RoleImpl();
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Permission createPermission() {
		PermissionImpl permission = new PermissionImpl();
		return permission;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public lrbac.Object createObject() {
		ObjectImpl object = new ObjectImpl();
		return object;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Session createSession() {
		SessionImpl session = new SessionImpl();
		return session;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Constraint createConstraint() {
		ConstraintImpl constraint = new ConstraintImpl();
		return constraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Location createLocation() {
		LocationImpl location = new LocationImpl();
		return location;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Operation createOperation() {
		OperationImpl operation = new OperationImpl();
		return operation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LrbacPackage getLrbacPackage() {
		return (LrbacPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static LrbacPackage getPackage() {
		return LrbacPackage.eINSTANCE;
	}

} //LrbacFactoryImpl
