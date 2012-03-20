package fr.inria.hocl.strategy;


import fr.inria.hocl.api.Atom;
import fr.inria.hocl.api.ReactionRule;
import fr.inria.hocl.api.Solution;
import fr.inria.hocl.api.Strategy;
import fr.inria.hocl.iterator.SimpleIterator;
import fr.inria.hocl.iterator.SimpleLinkedList;

import java.util.Random;

/**
 * Random strategy: choose a random rule among the non inert ones
 * 
 */
public class StrategyRandom implements Strategy {

	private SimpleLinkedList<Rule> rules;

	private SimpleIterator<Rule> it_currentRule;

	private Random random;

	private class Rule {

		ReactionRule reactionRule;

		boolean isInert;


		Rule( ReactionRule reactionRule ) {
			this.reactionRule = reactionRule;
			this.isInert = false;
		}


		ReactionRule getReactionRule() {
			return reactionRule;
		}

	} // class Rule


	public StrategyRandom() {
		rules = new SimpleLinkedList<Rule>();
		it_currentRule = rules.newIterator();
		random = new Random();
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
		return queryNextRule( true );
	}


	public ReactionRule queryNextRule( boolean hasPreviousReacted ) {
		ReactionRule rr;
		if( rules.isEmpty() ) {
			rr = null;
		} else {
			if( !hasPreviousReacted ) {
				it_currentRule.getElement().isInert = true;
			} else {
				// the previous one has reacted and may have produced new molecules
				for( SimpleIterator<Rule> it = rules.newIterator(); !it.isAtEnd(); it
						.next() ) {
					it.getElement().isInert = false;
				}
			}
			// compute a random int N
			int size = rules.size();
			int n = random.nextInt( size ) + 1;
			// move N times modulo the length of the list of rules
			int nbInert = 0;
			for( int i = 1; i <= n && nbInert < size; i++ ) {
				it_currentRule.next();
				if( it_currentRule.isAtEnd() ) {
					it_currentRule.moveToBeginning();
				}
				nbInert = 0;
				while( it_currentRule.getElement().isInert && nbInert < size ) {
					nbInert++;
					it_currentRule.next();
					if( it_currentRule.isAtEnd() ) {
						it_currentRule.moveToBeginning();
					}
				}
			}
			if( nbInert >= size ) {
				rr = null;
			} else {
				rr = it_currentRule.getElement().getReactionRule();
			}
		}
		return rr;
	}


	private void removeRule( ReactionRule reactionRule ) {
		// same object, stronger than same rule (equals(.) on reaction rules checks
		// equality of names)
		if( it_currentRule.getElement().getReactionRule() == reactionRule ) {
			it_currentRule.remove();
		} else {
			SimpleIterator<Rule> it = rules.newIterator();
			for( it.moveToBeginning(); !it.isAtEnd()
					&& it.getElement().getReactionRule() != reactionRule; it.next() ) {}
			if( !it.isAtEnd() ) {
				it.remove();
			}
		}
	}


	public void notifyAdd( Solution solution, Atom atom ) {
		if( atom instanceof ReactionRule ) {
			rules.add( new Rule( ( ReactionRule ) atom ) );
		}
	}


	public void notifyRemove( Solution solution, Atom atom ) {
		if( atom instanceof ReactionRule ) {
			removeRule( (ReactionRule) atom );
		}
	}


	public String getStat() {
		SimpleIterator<Rule> it_rr;
		String s = "";

		it_rr = rules.newIterator();
		while( !it_rr.isAtEnd() ) {
			s = s + ( it_rr.getElement().getReactionRule() ).getStat();
			it_rr.next();
		}
		return s;
	}


	public String toString() {
		return "Random";
	}

} // class StrategyRandom
