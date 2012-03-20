package fr.inria.hocl.strategy;


import fr.inria.hocl.api.Atom;
import fr.inria.hocl.api.ReactionRule;
import fr.inria.hocl.api.Solution;
import fr.inria.hocl.api.Strategy;
import fr.inria.hocl.iterator.SimpleIterator;
import fr.inria.hocl.iterator.SimpleLinkedList;

/**
 * Fair roundrobin strategy (R1^1,...,Rn^1)^*
 * 
 */
public class StrategyFairRoundRobin implements Strategy {

	private SimpleLinkedList<ReactionRule> rules;

	private SimpleIterator<ReactionRule> it_currentRule;

	private boolean firstCalled;

	private boolean foundReaction;


	public StrategyFairRoundRobin() {
		rules = new SimpleLinkedList<ReactionRule>();
		it_currentRule = rules.newIterator();
		firstCalled = false;
		foundReaction = false;
	}


	public void manageSolution( Solution solution ) {
		solution.addObserverAdd( this );
		SimpleIterator<Atom> it = solution.newIterator();
		while( !it.isAtEnd() ) {
			notifyAdd( solution, it.getElement() );
			it.next();
		}
	}


	public ReactionRule firstRule() {
		ReactionRule rr;

		firstCalled = true;
		foundReaction = false;
		if( rules.isEmpty() ) {
			rr = null;
		} else {
			it_currentRule.moveToBeginning();
			rr = it_currentRule.getElement();
		}
		return rr;
	}


	public ReactionRule queryNextRule( boolean previousHasReacted ) {
		assert ( firstCalled ); // Strategy.firstRule() should have been called
														// first

		if( previousHasReacted ) {
			foundReaction = true;
		}
		it_currentRule.next();
		if( it_currentRule.isAtEnd() ) {
			if( foundReaction ) {
				return firstRule();
			} else {
				return null; // all rules inert
			}
		} else {
			return it_currentRule.getElement();
		}
	}


	public void notifyAdd( Solution solution, Atom atom ) {
		if( atom instanceof ReactionRule ) {
			rules.add( ( ReactionRule ) atom );
		}
	}


	public void notifyRemove( Solution solution, Atom atom ) {
		if( atom instanceof ReactionRule ) {
		    if( ( !( it_currentRule.isAtEnd() ) && ( it_currentRule.getElement() == atom ) ) ) {
				it_currentRule.remove();
				it_currentRule.previous(); // next call to queryNextRule returns next
			} else {
				rules.remove( ( ReactionRule ) atom );
			}
		}
	}


	public String getStat() {
		SimpleIterator<ReactionRule> it_rr;
		String s = "";

		it_rr = rules.newIterator();
		while( !it_rr.isAtEnd() ) {
			s = s + it_rr.getElement().getStat();
			it_rr.next();
		}
		return s;
	}


	public String toString() {
		return "Fair Round Robin that manages " + rules;
	}

}
