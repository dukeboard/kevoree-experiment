package lrbacTools.transformations;

import org.eclipse.viatra2.emf.incquery.runtime.exception.IncQueryRuntimeException;
import org.eclipse.viatra2.emf.incquery.runtime.extensibility.BuilderRegistry;
import incQueryPatterns.patternmatchers.kevoree.*;
import incQueryPatterns.patternbuilders.kevoree.*;
import incQueryPatterns.signatures.kevoree.*;


import kevoree.Channel;
import kevoree.ComponentInstance;
import kevoree.ContainerNode;
import kevoree.ContainerRoot;

public class Kevoree2PolicyStats {

	private ContainerRoot containerRoot;
	private NodeSubjectMatcher nodeSubjectMatcher;
	private NodeObjectMatcher nodeObjectMatcher;	
	private AuthorizationMatcher authorizationMatcher;
		
	public Kevoree2PolicyStats(ContainerRoot kev){
		containerRoot = kev;
		BuilderRegistry.getContributedStatelessPatternBuilders().put(NodeSubjectMatcher.FACTORY.getPatternName(),new PatternBuilderFornodeSubject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(NodeObjectMatcher.FACTORY.getPatternName(),new PatternBuilderFornodeObject());
		BuilderRegistry.getContributedStatelessPatternBuilders().put(AuthorizationMatcher.FACTORY.getPatternName(),new PatternBuilderForauthorization());
		
		try {
			nodeSubjectMatcher = NodeSubjectMatcher.FACTORY.getMatcher(containerRoot);
			nodeObjectMatcher = NodeObjectMatcher.FACTORY.getMatcher(containerRoot);
			authorizationMatcher = AuthorizationMatcher.FACTORY.getMatcher(containerRoot);
		} catch (IncQueryRuntimeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
	
	public String transformation(){
		String res = "";
		res = res + addNodeSubjects();
		res = res + addNodeObjects();
		res = res + addAuthorizations();
		
		return res;
	}
	
	private String addNodeSubjects() {
		String res = "";
		for(NodeSubjectSignature sig : nodeSubjectMatcher.getAllMatchesAsSignature()){
			res = res +"\n"+((ContainerNode)sig.getValueOfN()).getName();
		}
		return res;
	}



	private String addNodeObjects() {
		String res = "";
		for(NodeObjectSignature sig : nodeObjectMatcher.getAllMatchesAsSignature()){
			res = res +"\n"+((ContainerNode)sig.getValueOfN()).getName();
		}
		return res;
	}


	private String addAuthorizations() {
		String res = "";
		for(AuthorizationSignature sig : authorizationMatcher.getAllMatchesAsSignature()){
			String subj = ((ComponentInstance)sig.getValueOfSUBJECT()).getName();
			String cha = ((Channel)sig.getValueOfCHANNEL()).getName();
			String obj = ((ComponentInstance)sig.getValueOfOBJECT()).getName();
			res = res +"\n"+subj+":"+cha+":"+obj;
		}
		return res;
	}	
	
	
	
}
