package info.danidiaz.util;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * <p>An implementation of a doubly linked list.<p>
 * 
 * <p>Some methods which the standard interfaces designate as optional have not
 * been implemented and throw {@link UnsupportedOperationException}. In
 * particular random-access methods, which are bound to be inefficient for
 * linked lists.</p>
 *
 */
public final class DoublyLinkedList<E> implements List<E>, Deque<E>
{
	private Node<E> first;
	private Node<E> last;
	
    public DoublyLinkedList() {
		super();
	}

	@Override
	public void addFirst(E e) {
		final Node<E> newFirst = new Node<>(e,first,null);
		if (isEmpty()) {
			last = newFirst;
		} else {
			first.setPrevious(newFirst);
		}
		first = newFirst;
	}

	@Override
	public void addLast(E e) {
		final Node<E> newLast = new Node<>(e,null,last);
		if (isEmpty()) {
			first = newLast;
		} else {
			last.setNext(newLast);
		}
		last = newLast;
	}

	@Override
	public Iterator<E> descendingIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E element() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return first.getValue();
	}

	@Override
	public E getFirst() {
		return element();
	}

	@Override
	public E getLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean offer(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerFirst(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean offerLast(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E peek() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E peekLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E poll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E pop() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void push(E e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public E remove() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public E removeFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E removeLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(E e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void add(int index, E element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		first = null;
		last = null;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int indexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public Iterator<E> iterator() {
		return listIterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public ListIterator<E> listIterator() {
		return new DoublyLinkedListIterator();
	}

	@Override
	public ListIterator<E> listIterator(int index) {
		final ListIterator<E> iter = listIterator();
		for (int i=0;i<index;i++) {
			if (i < index-1 && !iter.hasNext()) {
				throw new IndexOutOfBoundsException();
			}
		}
		return iter;
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public boolean remove(Object o) {
		throw new UnsupportedOperationException();
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public E remove(int index) {
		throw new UnsupportedOperationException();
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public boolean removeAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public boolean retainAll(Collection<?> c) {
		throw new UnsupportedOperationException();
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public E set(int index, E element) {
		throw new UnsupportedOperationException();
	}

	@Override
	public int size() {
		final ListIterator<E> iter = listIterator();
		while (iter.hasNext()) {
			iter.next();
		}
		return iter.nextIndex();
	}

	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	private final static class Node<E> {
		private E value;
		private Node<E> next;
		private Node<E> previous;
		public Node(E value, Node<E> next, Node<E> previous) {
			super();
			this.value = value;
			this.next = next;
			this.previous = previous;
		}
		public Node<E> getNext() {
			return next;
		}
		public void setNext(Node<E> next) {
			this.next = next;
		}
		public Node<E> getPrevious() {
			return previous;
		}
		public void setPrevious(Node<E> previous) {
			this.previous = previous;
		}
		public E getValue() {
			return value;
		}
		public void setValue(E e) {
			value = e;
		}
	}
	
	private final class DoublyLinkedListIterator implements ListIterator<E> {

		private int index;
		private Node<E> iterPrevious;
		private Node<E> iterNext;
		// See https://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html#remove()
		// for an explanation of the necessity of keeping track of the last movement.
		private Node<E> lastMovement;

		public DoublyLinkedListIterator() {
			super();
			this.index = 0;
			this.iterPrevious = null;
			this.iterNext = DoublyLinkedList.this.first;
			this.lastMovement = null;
		}

		@Override
		public void add(E arg0) {
			index++;
			final Node<E> node = new Node<E>(arg0,iterPrevious,iterNext);
			if (iterPrevious==null) {
				DoublyLinkedList.this.first = node;
			}
			if (iterNext==null) {
				DoublyLinkedList.this.last = node;
			}
			lastMovement = null;
			iterPrevious = node;
		}

		@Override
		public boolean hasNext() {
			return iterNext != null;
		}

		@Override
		public boolean hasPrevious() {
			return iterPrevious != null;
		}

		@Override
		public E next() {
			if (iterNext == null) {
				throw new NoSuchElementException();
			}
			index++;
			final E result = iterNext.getValue();
			lastMovement = iterNext;
			iterPrevious = iterNext;
			iterNext = iterNext.getNext();
			return result;
		}

		@Override
		public int nextIndex() {
			return index;
		}

		@Override
		public E previous() {
			if (iterPrevious == null) {
				throw new NoSuchElementException();
			}
			index--;
			final E result = iterPrevious.getValue();
			lastMovement = iterPrevious;
			iterNext = iterPrevious;
			iterPrevious = iterPrevious.getPrevious();
			return result;
		}

		@Override
		public int previousIndex() {
			return index-1;
		}

		@Override
		public void remove() {
			if (lastMovement == null) {
				throw new IllegalStateException();
			}

			if (lastMovement == iterPrevious) { // last movement was forwards
				index--;

				iterPrevious = iterPrevious.getPrevious();
				if (iterPrevious == null) { // removal put us an the beginning
					DoublyLinkedList.this.first = iterNext;
				} else {
					iterPrevious.setNext(iterNext);
				}

				if (iterNext == null) { // we were at end of list
					DoublyLinkedList.this.last = iterPrevious;
				} else {
					iterNext.setPrevious(iterPrevious);
				}
				
			} else if (lastMovement == iterNext) { // last movement was backwards
				iterNext = iterNext.getNext();
				if (iterNext == null) { // removal put us at the end
					DoublyLinkedList.this.last = iterPrevious;
				} else {
					iterNext.setPrevious(iterPrevious);
				}

				if (iterPrevious == null) { // we were at the beginning of list
					DoublyLinkedList.this.first = iterNext;
				} else {
					iterPrevious.setNext(iterNext);
				}
			}

			lastMovement = null;
		}

		@Override
		public void set(E e) {
			if (lastMovement == null) {
				throw new IllegalStateException();
			}
			lastMovement.setValue(e);
		}
	}
}
