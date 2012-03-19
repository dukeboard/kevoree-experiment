package fr.inria.hocl.strategy;


import fr.inria.hocl.api.Atom;
import fr.inria.hocl.api.ReactionRule;
import fr.inria.hocl.api.Solution;
import fr.inria.hocl.api.Strategy;
import fr.inria.hocl.iterator.SimpleIterator;
import fr.inria.hocl.iterator.SimpleLinkedList;

import java.util.Comparator;

/**
 * In this strategy, we are using tropes and priorities. We denote by "t1 << t2"
 * a priority: rules with trope t1 have priority over rules with trope t2
 * 
 * reducer << optimizer << unknown << expanser
 * 
 * This way we favorize rules that decrease the size of the solution, to keep
 * the combinatorial explosion as low as possible
 * 
 * @author yradenac
 * 
 */
public class StrategyKeepSmallSolution implements Strategy {

	private SimpleLinkedList<Rule> rules; // rules sorted according to the tropes
																				// order

	private Rule currentRule;

	private SimpleIterator<Rule> it_currentRule;

	private boolean firstCalled;

	private final static Comparator<ReactionRule> tropesOrder = new ReactionRule.CmpSmallSolution();

	private static class Rule {

		private final ReactionRule reactionRule;

		boolean isInert;


		Rule( ReactionRule reactionRule ) {
			this.reactionRule = reactionRule;
			this.isInert = false;
		}


		ReactionRule getReactionRule() {
			return reactionRule;
		}

		@SuppressWarnings( "unused" )
		static class CmpSmallSolution implements Comparator<Rule> {

			static final Comparator<ReactionRule> rrCmpSmallSolution = new ReactionRule.CmpSmallSolution();


			public int compare( Rule r1, Rule r2 ) {
				return rrCmpSmallSolution.compare( r1.getReactionRule(), r2
						.getReactionRule() );
			}
		} // class CmpSmallSolution

	} // class Rule


	public StrategyKeepSmallSolution() {
		rules = new SimpleLinkedList<Rule>();
		it_currentRule = rules.newIterator();
		firstCalled = false;
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
		firstCalled = true;
		return moveToFirstNonInert();
	}


	private ReactionRule moveToFirstNonInert() {
		it_currentRule.moveToBeginning();
		currentRule = null;
		return moveToNextNonInert();
	}


	private ReactionRule moveToNextNonInert() {
		boolean found = false;
		ReactionRule rr = null;
		while( !it_currentRule.isAtEnd() && !found ) {
			currentRule = it_currentRule.getElement();
			if( !currentRule.isInert ) {
				found = true;
				rr = currentRule.getReactionRule();
			} else {
				it_currentRule.next();
			}
		}
		return rr;
	}


	public ReactionRule queryNextRule( boolean hasPreviousReacted ) {
		if( !firstCalled ) {
			System.out.println( "Strategy.firstRule() should have been called first" );
			System.exit( 1 );
		}

		ReactionRule rr;

		if( !hasPreviousReacted ) {
			currentRule.isInert = true;
			rr = moveToNextNonInert();
		} else {
			SimpleIterator<Rule> it = rules.newIterator();
			while( !it.isAtEnd() ) {
				Rule r = it.getElement();
				r.isInert = false;
				it.next();
			}
			rr = moveToFirstNonInert();
		}
		return rr;
	}


	private void removeRule( ReactionRule reactionRule ) {
		SimpleIterator<Rule> it = rules.newIterator();
		boolean found = false;

		while( !it.isAtEnd() && !found ) {
			Rule r = it.getElement();
			if( r.getReactionRule().equals( reactionRule ) ) {
				r.isInert = true; // set to true such that moveToNextNonInert() does not
													// select it again
				if( r == currentRule ) {
					moveToNextNonInert();
				}
				it.remove();
				found = true;
			}
			it.next();
		}
	}


	private void addRule( ReactionRule reactionRule ) {
		SimpleIterator<Rule> it_rule = rules.newIterator();
		it_rule.moveToBeginning();
		while( !it_rule.isAtEnd()
				&& tropesOrder.compare( reactionRule, it_rule.getElement()
						.getReactionRule() ) <= 0 ) {
			it_rule.next();
		}
		it_rule.insertBefore( new Rule( reactionRule ) );
		moveToFirstNonInert();
	}


	public void notifyAdd( Solution solution, Atom atom ) {
		if( atom instanceof ReactionRule) {
			addRule( (ReactionRule) atom );
		}
	}


	public void notifyRemove( Solution solution, Atom atom ) {
		if( atom instanceof ReactionRule) {
			removeRule( (ReactionRule) atom );
		}
	}


	public String getStat() {
		SimpleIterator<Rule> it_rr;
		String s = "";

		it_rr = rules.newIterator();
		while( !it_rr.isAtEnd() ) {
			Rule r = it_rr.getElement();
			s = s + r.getReactionRule().getStat();
			it_rr.next();
		}
		return s;
	}


	public String toString() {
		return "Keep Small Solution";
	}

} // class StrategyKeepSmallSolution
