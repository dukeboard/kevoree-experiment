package fr.inria.hocl.strategy;


import fr.inria.hocl.api.Atom;
import fr.inria.hocl.api.ReactionRule;
import fr.inria.hocl.api.Solution;
import fr.inria.hocl.api.Strategy;
import fr.inria.hocl.iterator.SimpleIterator;
import fr.inria.hocl.iterator.SimpleLinkedList;

/**
 * Unfair roundrobin strategy: (R1^*,...,Rn^*)^*
 * 
 */
public class StrategyUnFairRoundRobin implements Strategy {

	private SimpleLinkedList<ReactionRule> rules;

	private SimpleIterator<ReactionRule> it_currentRule;

	private boolean firstCalled;

	private boolean foundReaction;


	public StrategyUnFairRoundRobin() {
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
		it_currentRule.moveToBeginning();
		if( rules.isEmpty() ) {
			rr = null;
		} else {
			rr = it_currentRule.getElement();
		}
		return rr;
	}


	public ReactionRule queryNextRule( boolean hasPreviousReacted ) {
		if( !firstCalled ) {
			System.out.println( "Strategy.firstRule() should have been called first" );
			System.exit( 1 );
		}
		ReactionRule rr;
		if( hasPreviousReacted ) {
			foundReaction = true;
		} else {
			it_currentRule.next();
		}
		if( it_currentRule.isAtEnd() ) {
			if( foundReaction ) {
				rr = firstRule();
			} else {
				// the end
				rr = null;
			}
		} else {
			rr = it_currentRule.getElement();
		}
		return rr;
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
				// next call to queryNextRule will have argument hasPreviousReacted =
				// true and will keep
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
		return "Unfair Round Robin";
	}

} // class StrategyUnFairRoundRobin
