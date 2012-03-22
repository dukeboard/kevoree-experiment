package fr.inria.hocl.core.hoclc2j;


// import java.util.*;

public class ReactionCondition extends External { // extends
																									// LinkedList<PrimaryCondition>
																									// {

	/*
	 * public boolean add(PrimaryCondition pc){ Iterator<PrimaryCondition> it;
	 * PrimaryCondition pc2; boolean found = false;
	 * 
	 * // merge to an existing entry if it uses the same set of variables it =
	 * iterator(); while(it.hasNext() && !found){ pc2 = it.next();
	 * if(pc2.equals(pc)){ pc2.mergeAnd(pc); found = true; } } // if it does not
	 * already exist, simply add the pc to the end of the list if(!found){
	 * super.add(pc); } return true; }
	 * 
	 * 
	 * public int nbOccurences(String var){ int occ = 0; PrimaryCondition pc2;
	 * Iterator<PrimaryCondition> it;
	 * 
	 * it = iterator(); while(it.hasNext()){ pc2 = it.next();
	 * if(pc2.variables.contains(var)){ occ++; } } return occ; }
	 * 
	 * public void mergeAll(){ PrimaryCondition firstCond, primCond;
	 * Iterator<PrimaryCondition> it;
	 * 
	 * it = iterator(); if(it.hasNext()){ firstCond = it.next();
	 * while(it.hasNext()){ primCond = it.next(); firstCond.condition = "(" +
	 * firstCond.condition + ") && (" + primCond.condition + ")"; it.remove(); } }
	 * }
	 */

}
