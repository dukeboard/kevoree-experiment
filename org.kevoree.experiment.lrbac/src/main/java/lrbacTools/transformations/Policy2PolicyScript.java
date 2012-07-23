package lrbacTools.transformations;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;

import incQueryPatterns.patternbuilders.lrbac.*;
import incQueryPatterns.patternmatchers.lrbac.*;
import incQueryPatterns.signatures.lrbac.*;
import lrbac.*;
import lrbac.Object;

public class Policy2PolicyScript {

	private Policy policy;
	
	private UserMatcher userMatcher;
	private RoleMatcher roleMatcher;
	private PermissionMatcher permissionMatcher;
	private OperationMatcher operationMatcher;
	private ObjectMatcher objectMatcher;
	private SessionMatcher sessionMatcher;

	private UserRoleAssignmentMatcher userRoleAssignmentMatcher;
	private RolePermissionAssignmentMatcher rolePermissionAssignmentMatcher ;
	private PermissionOperationAssignmentMatcher permissionOperationAssignmentMatcher;
	private OperationObjectAssignmentMatcher operationObjectAssignmentMatcher;
	
	private UserOperationMatcher userOperationMatcher;
	private UserRuleMatcher userRuleMatcher;

	public Policy2PolicyScript(Policy p) {
		policy = p;
		BuilderRegistry.getContributedStatelessPatternBuilders().put(UserMatcher.FACTORY.getPatternName(),new PatternBuilderForuser());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(RoleMatcher.FACTORY.getPatternName(),new PatternBuilderForrole());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(PermissionMatcher.FACTORY.getPatternName(),new PatternBuilderForpermission());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(OperationMatcher.FACTORY.getPatternName(),new PatternBuilderForoperation());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(ObjectMatcher.FACTORY.getPatternName(),new PatternBuilderForobject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(SessionMatcher.FACTORY.getPatternName(),new PatternBuilderForsession());
	
		BuilderRegistry.getContributedStatelessPatternBuilders().put(UserRoleAssignmentMatcher.FACTORY.getPatternName(),new PatternBuilderForuserRoleAssignment());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(RolePermissionAssignmentMatcher.FACTORY.getPatternName(),new PatternBuilderForRolePermissionAssignment());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(PermissionOperationAssignmentMatcher.FACTORY.getPatternName(),new PatternBuilderForPermissionOperationAssignment());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(OperationObjectAssignmentMatcher.FACTORY.getPatternName(),new PatternBuilderForOperationObjectAssignment());		
		
		BuilderRegistry.getContributedStatelessPatternBuilders().put(UserOperationMatcher.FACTORY.getPatternName(),	new PatternBuilderForuserOperation());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(UserRuleMatcher.FACTORY.getPatternName(),new PatternBuilderForuserRule());
		try {
			userMatcher = UserMatcher.FACTORY.getMatcher(policy);
			roleMatcher = RoleMatcher.FACTORY.getMatcher(policy);
			permissionMatcher = PermissionMatcher.FACTORY.getMatcher(policy);
			operationMatcher = OperationMatcher.FACTORY.getMatcher(policy);
			objectMatcher = ObjectMatcher.FACTORY.getMatcher(policy);
			sessionMatcher = SessionMatcher.FACTORY.getMatcher(policy);
			
			userRoleAssignmentMatcher = UserRoleAssignmentMatcher.FACTORY.getMatcher(policy);
			rolePermissionAssignmentMatcher= RolePermissionAssignmentMatcher.FACTORY.getMatcher(policy);
			permissionOperationAssignmentMatcher= PermissionOperationAssignmentMatcher.FACTORY.getMatcher(policy);
			operationObjectAssignmentMatcher= OperationObjectAssignmentMatcher.FACTORY.getMatcher(policy);
			
			userOperationMatcher = UserOperationMatcher.FACTORY.getMatcher(policy);
			userRuleMatcher = UserRuleMatcher.FACTORY.getMatcher(policy);
		} catch (IncQueryRuntimeException e) {
			e.printStackTrace();
		}
	}
	
	
	//POLICY ELEMENTS
	
	//user	
	public String addSubjects(){
		String script = "";
		for(UserSignature sig : userMatcher.getAllMatchesAsSignature()){
			String userName = ((User)sig.getValueOfU()).getName();
			script = script + "\n" + "addUser(" + userName+");";	
		}
		return script;
	}
	
	//role		
	public String addRoles(){
		String script = "";
		for(RoleSignature sig : roleMatcher.getAllMatchesAsSignature()){
			String roleName = ((Role)sig.getValueOfR()).getName();
			script = script + "\n" + "addRole(" + roleName+");";	
		}
		return script;
	}
	
	//permission
	public String addPermissions(){
		String script = "";
		for(PermissionSignature sig : permissionMatcher.getAllMatchesAsSignature()){
			String permissionName = ((Permission)sig.getValueOfP()).getName();
			script = script + "\n" + "addPermission(" + permissionName+");";	
		}
		return script;
	}
	
	//session
	public String addSessions(){
		String script = "";
		for(SessionSignature sig : sessionMatcher.getAllMatchesAsSignature()){
			String sessionName = ((Session)sig.getValueOfS()).getName();
			script = script + "\n" + "addSession(" + sessionName+");";	
		}
		return script;
	}

	
	//operation
		public String addOperations(){
			String script = "";
			for(OperationSignature sig : operationMatcher.getAllMatchesAsSignature()){
				String operationName = ((Operation)sig.getValueOfOP()).getName();
				script = script + "\n" + "addOperation(" + operationName+");";
			}
			return script;
		}
	
	
	//object
	public String addObjects(){
		String script = "";
		for(ObjectSignature sig : objectMatcher.getAllMatchesAsSignature()){
			String objectName = ((Object)sig.getValueOfOB()).getName();
			script = script + "\n" + "addObject(" + objectName+");";
		}
		return script;
	}
	
	
	
	//RELATIONSHIPS
	
	
	public String addUserRoleAssignments(){
		String script = "";
		for(UserRoleAssignmentSignature sig : userRoleAssignmentMatcher.getAllMatchesAsSignature()){
			String userName = ((User)sig.getValueOfUSER()).getName();
			String roleName = ((Role)sig.getValueOfROLE()).getName();
			script = script + "\n" + "addUserRole("+userName+","+roleName+");";
		}
		return script;
	}
	
	
	public String addRolePermissionAssignments(){
		String script = "";
		for(RolePermissionAssignmentSignature sig : rolePermissionAssignmentMatcher.getAllMatchesAsSignature()){
			String roleName = ((Role)sig.getValueOfROLE()).getName();
			String permissionName = ((Permission)sig.getValueOfPERM()).getName();
			script = script + "\n" + "addRolePermission("+roleName+","+permissionName+");";
		}
		return script;
	}
	
	public String addPermissionOperationAssignments(){
		String script = "";
		for(PermissionOperationAssignmentSignature sig : permissionOperationAssignmentMatcher.getAllMatchesAsSignature()){
			String permissionName = ((Permission)sig.getValueOfPERM()).getName();
			String opeName = ((Operation)sig.getValueOfOPE()).getName();
			script = script + "\n" + "addPermissionOperation("+permissionName+","+opeName+");";
		}
		return script;
	}
	
	public String addOperationObjectsAssignments(){
		String script = "";
		for(OperationObjectAssignmentSignature sig : operationObjectAssignmentMatcher.getAllMatchesAsSignature()){
			String permissionName = ((Permission)sig.getValueOfPERM()).getName();
			String opeName = ((Operation)sig.getValueOfOPE()).getName();
			String objName = ((Object)sig.getValueOfOBJ()).getName();
			script = script + "\n" + "addPermissionOperationObject("+permissionName+","+opeName+","+objName+");";
		}
		return script;
	}
	
	
	
	//usersOperations
	public String addUserOperations(){
		String script = "";
		for (UserOperationSignature sig : userOperationMatcher.getAllMatchesAsSignature()){
			System.out.println("sig : "+sig.getValueOfUSER()+" : "+sig.getValueOfOPERATIONNAME());
		}
		for (UserOperationSignature sig : userOperationMatcher.getAllMatchesAsSignature()){
			String userName = ((User)sig.getValueOfUSER()).getName();
			String operationName = sig.getValueOfOPERATIONNAME().toString();
		}
		return script;
	}
	
	//userRules
	public String addUserRules(){
		String script = "";
		int cpt=0;
		for(UserRuleSignature sig : userRuleMatcher.getAllMatchesAsSignature()){
			String userName = ((User)sig.getValueOfUSER()).getName();
			String objectName = ((Object)sig.getValueOfOBJECT()).getName();
			String operationName = ((Operation)sig.getValueOfOPERATION()).getName();
			script = script + "\n" + "rule"+cpt+" "+ userName+"."+operationName+"."+objectName;
			cpt = cpt+1;
		}
		return script;
	}
	
	
	public String transformation(){
		String script ="PolicyScript{\n";
		script = script + addSubjects() +"\n";
		script = script + addRoles()+"\n";
		script = script + addPermissions()+"\n";
		script = script + addOperations()+"\n";
		script = script + addObjects()+"\n";
		script = script + addSessions()+"\n";
		script = script + addUserRoleAssignments()+"\n";
		script = script + addRolePermissionAssignments()+"\n";
		script = script + addPermissionOperationAssignments()+"\n";		
		script = script + addOperationObjectsAssignments()+"\n";
		
		//script = script + addUserRules()+"\n";		
		script = script+"\n }";
		return script;
	}

}
