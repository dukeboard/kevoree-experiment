package fr.inria.hocl.iterator;


import java.io.Serializable;

/**
 * Several iterators are allowed, but when removing an element, no test is made
 * whether some iterators point on that element
 * 
 * Semantics of the iterators is not the semantics of the iterators of the
 * Collection Interface. An object SimpleIterator is used as an iterator but
 * also as a "pointer" to an element.
 * 
 * @author Yann Radenac
 * @date november 2005
 * 
 */
public class SimpleLinkedList<E> implements Serializable {

	private static final long serialVersionUID = -6071131693246185161L;

	private SimpleEntry<E> start;

	private int size = 0;


	public SimpleLinkedList() {
		start = new SimpleEntry<E>();
		start.next = start;
		start.previous = start;
		start.element = null;
	}


	public void add( E o ) {
		SimpleEntry<E> nextStart = new SimpleEntry<E>();

		start.element = o;
		nextStart.previous = start;
		nextStart.next = start.next;
		start.next.previous = nextStart;
		start.next = nextStart;
		start = nextStart;
		size++;
	}


	public int size() {
		return size;
	}


	public void clear() {
		start.next = start;
		start.previous = start;
	}


	public boolean isEmpty() {
		return start.next == start;
	}


	public void concat( SimpleLinkedList<? extends E> l ) {
		SimpleIterator<? extends E> it = l.newIterator();

		while( !it.isAtEnd() ) {
			add( it.next() );
		}
	}


	public String toString() {
		String s;
		SimpleIterator<E> it = newIterator();

		s = "[";
		it.moveToBeginning();
		while( !it.isAtEnd() ) {
			s = s + it;
			if( it.hasNext() ) {
				s = s + ",";
			}
			it.next();
		}
		s = s + "]";
		return s;
	}


	public SimpleIterator<E> newIterator() {
		return new SimpleIter();
	}


	public void remove( E element ) {
		SimpleIterator<E> it = newIterator();
		for( it.moveToBeginning(); !it.isAtEnd()
				&& !it.getElement().equals( element ); // FIXME: wrong
																								// Object.equals(Object) should
																								// be E.equals(Object)
		it.next() ) {}
		if( !it.isAtEnd() ) {
			it.remove();
		}
	}

	// SimpleIterator
	private class SimpleIter extends SimpleIterator<E> {

		public SimpleIter() {
			moveToBeginning();
		}


		/**
		 * Move at the first element if it exists, or at the end of the list if the
		 * list is empty
		 * 
		 */
		public void moveToBeginning() {
			currentEntry = start.next;
		}


		/**
		 * Move after the last element
		 * 
		 */
		public void moveToEnd() {
			currentEntry = start;
		}


		public boolean hasNext() {
			return currentEntry.next != start;
		}


		/**
		 * 
		 * @return if the iterator is after the last element
		 */
		public boolean isAtEnd() {
			return currentEntry == start;
		}


		public boolean isAtStart() {
			return currentEntry.previous == start;
		}


		/**
		 * Returns the next element if it exists and move forward. If there is no
		 * next element, next() returns null.
		 * 
		 * @return the next element if it exists
		 */
		public E next() {
			E o = currentEntry.element;
			if( !isAtEnd() )
				currentEntry = currentEntry.next;
			return o;
		}


		/**
		 * Returns the previous element if it exists and move backward. If there is
		 * no previous element, previous() returns null.
		 * 
		 * @return the previous element if it exists
		 */
		public E previous() {
			E o = currentEntry.element;
			if( !isAtStart() )
				currentEntry = currentEntry.previous;
			return o;
		}


		/**
		 * return the current element (last element returned)
		 */
		public E getElement() throws IllegalStateException {
			if( isAtEnd() ) {
				throw new IllegalStateException();
			}
			return currentEntry.element;
		}


		public void remove() throws IllegalStateException {
			if( isAtEnd() ) {
				throw new IllegalStateException(
						"Cannot remove the header of the list." );
			} else {
				SimpleEntry<E> e = currentEntry;
				currentEntry = currentEntry.next;
				e.previous.next = currentEntry;
				currentEntry.previous = e.previous;
				e.next = null;
				e.previous = null;
				e.element = null;
				size--;
			}
		}


		public void insertBefore( E obj ) {
			SimpleEntry<E> newEntry = new SimpleEntry<E>();
			newEntry.element = obj;
			newEntry.previous = currentEntry.previous;
			newEntry.next = currentEntry;
			currentEntry.previous.next = newEntry;
			currentEntry.previous = newEntry;
			size++;
		}


		public void moveToIterator( SimpleIterator<E> it ) {
			currentEntry = it.currentEntry;
		}


		public boolean equals( SimpleIterator<E> it ) {
			return currentEntry == it.currentEntry;
		}


		public String toString() {
			String s;

			if( isAtEnd() ) {
				s = "END";
			} else {
				s = getElement().toString();
			}
			return s;
		}


		public SimpleIterator<E> clone() {
			SimpleIterator<E> it;

			it = new SimpleIter();
			it.moveToIterator( this );
			return it;
		}

	} // class SimpleIter

} // class SimpleLinkedList


class SimpleEntry<E> implements Serializable {

	private static final long serialVersionUID = 8887353827815034109L;

	SimpleEntry<E> next;

	SimpleEntry<E> previous;

	E element;


	SimpleEntry() {
		next = null;
		previous = null;
		element = null;
	}
} // class SimpleEntry
