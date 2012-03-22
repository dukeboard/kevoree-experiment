package fr.inria.hocl.api;


import fr.inria.hocl.core.hocli.Debug;
import fr.inria.hocl.core.hocli.Hocli;
import fr.inria.hocl.iterator.SimpleIterator;
import fr.inria.hocl.iterator.SimpleLinkedList;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Class representing a chemical solution.
 *
 * Observer DP, ConcreteSubject : don't use the JVM's Observable and Observer
 * classes
 *
 */
public class Solution implements Atom, Serializable {

	private static final long serialVersionUID = 1231397820954441349L;

	private Molecule contents; // molecule inside the solution

	private SimpleLinkedList<SolutionObserver> observersAdd; // list of observers
																														// of this solution

	private SolutionState state;

	private List<String> allTypes;

	private List<Solution> innerSolutions;


	public int getAllTypeSize() {
		return this.allTypes.size();
	}


	public void addToAllTypes( String s ) {
		this.allTypes.add( s );
	}


	public String getiAllType( int i ) {
		return this.allTypes.get( i );
	}


	public Solution() {
		contents = new Molecule();
		observersAdd = new SimpleLinkedList<SolutionObserver>();
		state = SolutionState.UNKNOWN;
		this.allTypes = new LinkedList<String>();
		this.innerSolutions = new LinkedList<Solution>();
	}


	/**
	 *
	 * @return true if the solution is inert
	 */
	public boolean isInert() {
		return state == SolutionState.INERT;
	}


	/**
	 * Set the solution to inert (assume that it is true)
	 *
	 */
	public void setInert() {
		state = SolutionState.INERT;
	}


	/**
	 * Set the solution to non inert (assume that it is true)
	 *
	 */

	public void setNonInert() {
		state = SolutionState.NON_INERT;
	}


	/**
	 *
	 * @return a new iterator on the content of this solution
	 */
	public SimpleIterator<Atom> newIterator() {
		return contents.newIterator();
	}


	/**
	 *
	 * @param atom
	 * @return true if atom belongs to this solution
	 */
	public boolean contains( Atom atom ) {
		return contents.contains( atom );
	}


	/**
	 * Add an observer to this solution
	 *
	 * @param obs
	 */
	public void addObserverAdd( SolutionObserver obs ) {
		observersAdd.add( obs );
	}


	/**
	 * Remove the given observer from the list of observers of this solution
	 *
	 * @param obs
	 */
	public void removeObserverAdd( SolutionObserver obs ) {
		observersAdd.remove( obs );
	}


	/**
	 * Observer DP: notify all observers that an atom has been added
	 *
	 * @param atom
	 */
	private void notifyObserversAdd( Atom atom ) {
		SimpleIterator<SolutionObserver> it_obs;

		it_obs = observersAdd.newIterator();
		it_obs.moveToBeginning();
		while( !it_obs.isAtEnd() ) {
			it_obs.next().notifyAdd( this, atom );
		}
	}


	/**
	 * Notify observers that an atom has been removed
	 *
	 * @param atom
	 */
	private void notifyObserversRemove( Atom atom ) {
		SimpleIterator<SolutionObserver> it_obs;

		it_obs = observersAdd.newIterator();
		it_obs.moveToBeginning();
		while( !it_obs.isAtEnd() ) {
			it_obs.next().notifyRemove( this, atom );
		}
	}


	/**
	 * Adds a molecule to the solution and update observers if it contains
	 * reaction rules.
	 *
	 * @param molecule
	 */
	public void addMolecule( Molecule molecule ) {
		if( !molecule.isEmpty() ) {
			SimpleIterator<Atom> it = molecule.newIterator();
			while( !it.isAtEnd() ) {
				contents.add( it.getElement() );
				if( it.getElement() instanceof ReactionRule ) {
					( ( ReactionRule ) it.getElement() ).setSolution( this );
				}
				notifyObserversAdd( it.getElement() );
				it.next();
			}
		}
	}


	/**
	 * Remove the given molecule
	 *
	 * @param molecule
	 */
	public void removeMolecule( Molecule molecule ) {

		if( !molecule.isEmpty() ) {
			SimpleIterator<Atom> it = molecule.newIterator();
			while( !it.isAtEnd() ) {
				notifyObserversRemove( it.getElement() ); // notify first
				contents.remove( it.getElement() ); // then remove
				it.next();
			}
		}
	}


    public void removeAtomFromContainer( Atom a ) {
	notifyObserversRemove( a );
	contents.remove( a );


    }

	public void removeFromContainer( Molecule m ) {
		// System.out.println("++++++++++++++++++++++++\n");

		SimpleIterator<Atom> it = contents.newIterator();
		while( !it.isAtEnd() ) {
			Atom a = it.getElement();
			// System.out.println("this element is "+a+";m is "+m+";\n");

			if( m.contains( a ) ) {
				contents.remove( a );
				break;
			}
			it.next();
		}
	}


	public int size() {
		return contents.size();
	}


	/*public void createObject() {

	}


	public Molecule addElement() {
		return null;
	}*/


	public boolean equals( Atom atom ) {
		return atom instanceof Solution
				&& contents.equals( ( (Solution) atom ).contents );
	}


	public SimpleIterator<Atom> getIterator( Atom a ) {
		SimpleIterator<Atom> it = contents.newIterator();
		while( !it.isAtEnd() && it.getElement() != a ) {
			it.next();
		}
		if( it.isAtEnd() ) {
			it = null;
		}
		return it;
	}


	public String toString() {
		return "<" + contents + ">";
		// return this.prepareprintsol();
	}


	public String prepareprintsol() {

		String input = this.toString();
		String output = "";
		int stair = 0;

		for( int i = 0; i < input.length(); i++ ) {
			String s = input.substring( i, i + 1 );

			if( s.equals( "<" ) ) {
				stair++;
				output += s + "\n";

				for( int j = 0; j < stair; j++ ) {
					output += "\t";
				}
			}

			else if( s.equals( ">" ) ) {
				stair--;
				output += "\n";

				for( int j = 0; j < stair; j++ ) {
					output += "\t";
				}

				output += s;
			}

			else if( s.equals( "(" ) ) {
				output += "\n";
				for( int j = 0; j < stair; j++ ) {
					output += "\t";
				}

				output += s;

			}

			else if( s.equals( "@" ) ) {
				if( input.substring( i + 1, i + 2 ).equals( "," ) ) {
					i++;
				}

				else if( input.substring( i + 1, i + 2 ).equals( ">" ) ) {
					output = output.substring( 0, output.length() - 1 );
					output += "\n>";
					break;
				}
			}

			else {
				output += s;
			}

		}

		return output;
	}


	/*public void printsol() {

		Hocli.debug.addLog( Debug.DebugSymbol.SOLUTION_WHATISINSIDE,
				"Solution is: \n" + this.prepareprintsol() );

		// Hocli.debug.addLog(Debug.DebugSymbol.SOLUTION_WHATISINSIDE,
		// "Solution is: " + this);
	}*/


	public void initInnerSolutions() {
		SimpleIterator<Atom> it = this.newIterator();
		for( int iterator = 0; iterator < size(); iterator++ ) {
			Atom atom = it.next();

			if( atom instanceof Solution) {
				Solution innerSolution = (Solution) atom;
				innerSolutions.add( innerSolution );
			}
		}

	}


	public boolean isInnerSolutions() {
		return innerSolutions.size() > 0;
	}


	/**
	 * r Reduce the solution
	 *
	 */
	public void reduce() {
		initInnerSolutions();
		if( isInnerSolutions() ) {
			for (Solution innerSolution : innerSolutions) {
				innerSolution.setNonInert();
				innerSolution.reduce();
			}
		}
		Hocli.debug.addLog( Debug.DebugSymbol.SOLUTION_WHATISINSIDE,
				"Initial solution is: \n" + this.prepareprintsol() );
		reduceSolution( this );
		Hocli.debug.addLog( Debug.DebugSymbol.SOLUTION_WHATISINSIDE,
				"Solution is: \n" + this.prepareprintsol() );
		// Hocli.debug.addLog(Debug.DebugSymbol.SOLUTION_PARTIAL, "\n" + solution);
		Hocli.debug.printLog();

	}


	/**
	 * Reduce the solution
	 *
	 * @param solution
	 */
	public void reduceSolution( Solution solution )  {
		boolean satisfied;
		ReactionRule currentRR;
		Molecule result;
		Strategy strategy = StrategyManager.newStrategy();
		strategy.manageSolution( solution );

		Hocli.debug.addLog( Debug.DebugSymbol.FIRST_LAST_STATES,
				"\n>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>\n" + solution );
		Hocli.debug.addLog( Debug.DebugSymbol.SOLUTION_REDUCED, "Reduce "
				+ solution + " with the strategy " + strategy );

		currentRR = strategy.firstRule();
		while( currentRR != null ) {
			if( size() < currentRR.getMinAtoms() ) { // if not enough atoms
				satisfied = false;
			} else { // enough atoms

				Hocli.debug.addLog( Debug.DebugSymbol.TESTED_PERM, "Next reaction "
						+ currentRR + ":" );

				satisfied = currentRR.nextReaction();

				if( satisfied ) {
					// found a reaction, rewrite the solution

					// get reactives string for debugging
					String reactives = currentRR.getReactives().toString();

					// compute the result
					result = currentRR.computeResult();

					Hocli.debug.incrRuleShots( currentRR );
					Hocli.debug.addLog( Debug.DebugSymbol.REACTIONS, ( currentRR
							.getShot() == ReactionRule.Shot.ONE_SHOT ) ? reactives + " -> "
							+ result : currentRR + ", " + reactives + " -> " + currentRR
							+ ", " + result );

					// remove the reactives
					removeMolecule( currentRR.getReactives() );

					// add the result
					addMolecule( result );

				}
			} // end if enough atoms
			currentRR = strategy.queryNextRule( satisfied );
			Hocli.debug.addLog( Debug.DebugSymbol.STRATEGY, "Strategy state = "
					+ strategy + "\n" + "Change rule to: " + currentRR );
		}
		setInert();
		Hocli.debug.addLog( Debug.DebugSymbol.SOLUTION_REDUCED, "Reduced to: "
				+ solution );
		Hocli.debug.addLog( Debug.DebugSymbol.REACTIONS_STATS, strategy.getStat() );
		Hocli.debug.addLog( Debug.DebugSymbol.FIRST_LAST_STATES, this
				+ "\n<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<\n" );

	}

} // class Solution
