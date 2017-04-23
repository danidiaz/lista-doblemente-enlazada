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
 * <p>Also not implemented are methods like {@link subList} that would be
 * complex to implement and out of scope for the exercise.</p>
 * 
 * <p>Intermediate insertions can be done using the {@link ForwardIterator}
 * returned by {@link listIterator()}.
 * 
 * <p>Use {@link descendingIterator()} to get an iterator that goes backwards
 * from the end of the list. The {@link forward()} method changes direction.</p>
 *
 * <p>The iterator implementation contains most of the logic.</p>
 *
 */
public final class DoublyLinkedList<E> implements List<E>, Deque<E>
{
	private Node<E> first;
	private Node<E> last;
	private int size;
	
    public DoublyLinkedList() {
		super();
	}

	@Override
	public void addFirst(E e) {
		listIterator().add(e);
/*		final Node<E> newFirst = new Node<>(e,first,null);
		if (isEmpty()) {
			last = newFirst;
		} else {
			first.setPrevious(newFirst);
		}
		first = newFirst;
		size++;*/
	}

	@Override
	public void addLast(E e) {
		descendingIterator().forward().add(e);
/*
		final Node<E> newLast = new Node<>(e,null,last);
		if (isEmpty()) {
			first = newLast;
		} else {
			last.setNext(newLast);
		}
		last = newLast;
		size++;*/
	}

	@Override
	public DescendingIterator descendingIterator() {
		return new ForwardIterator(size-1,last,null).descending();
	}

	@Override
	public E element() {
		return getFirst();
	}

	@Override
	public E getFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return first.getValue();
	}

	@Override
	public E getLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		return last.getValue();
	}

	@Override
	public boolean offer(E e) {
		return offerLast(e);
	}

	@Override
	public boolean offerFirst(E e) {
		addFirst(e);
		return true;
	}

	@Override
	public boolean offerLast(E e) {
		addLast(e);
		return true;
	}

	@Override
	public E peek() {
		return peekFirst();
	}

	@Override
	public E peekFirst() {
		if (isEmpty()) {
			return null;
		}
		return first.getValue();
	}

	@Override
	public E peekLast() {
		if (isEmpty()) {
			return null;
		}
		return last.getValue();
	}

	@Override
	public E poll() {
		return pollFirst();
	}

	@Override
	public E pollFirst() {
		if (isEmpty()) {
			return null;
		}
		final ForwardIterator iter = listIterator();
		iter.next();
		return iter.removeAndGet();
	}

	@Override
	public E pollLast() {
		if (isEmpty()) {
			return null;
		}
		final ForwardIterator iter = descendingIterator().forward();
		iter.previous();
		return iter.removeAndGet();
	}

	@Override
	public E pop() {
		return removeFirst();
	}

	@Override
	public void push(E e) {
		addFirst(e);
	}

	@Override
	public E remove() {
		return removeFirst();
	}

	@Override
	public E removeFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		final ForwardIterator iter = listIterator();
		iter.next();
		return iter.removeAndGet();
	}

	@Override
	public boolean removeFirstOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public E removeLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
		final ForwardIterator iter = descendingIterator().forward();
		iter.previous();
		return iter.removeAndGet();
	}

	@Override
	public boolean removeLastOccurrence(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(E e) {
		addLast(e);
		return true;
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public void add(int index, E element) {
		throw new UnsupportedOperationException();
		
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		for (E e:c) {
			addLast(e);
		}
		return true;
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public boolean addAll(int index, Collection<? extends E> c) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void clear() {
		first = null;
		last = null;
		size = 0;
	}

	@Override
	public boolean contains(Object o) {
		if (o==null) {
			for (E e:this) {
				if (e==null) {
					return true;
				}
			}
		} else {
			for (E e:this) {
				if (o.equals(e)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o:c) {
			if (!contains(o)) {
				return false;
			}
		}
		return true;
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
		return size == 0;
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
	public ForwardIterator listIterator() {
		return new ForwardIterator(0,null,DoublyLinkedList.this.first);
	}

	@Override
	public ForwardIterator listIterator(int index) {
		final ForwardIterator iter = listIterator();
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

	/**
	 * This method has O(1) complexity, because the size is cached.
	 */
	@Override
	public int size() {
		return size;
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public List<E> subList(int fromIndex, int toIndex) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Object[] toArray() {
		Object [] result = new Object[size()];
		ListIterator<E> iter = listIterator();
		while (iter.hasNext()) {
			final int index = iter.nextIndex();
			final E element = iter.next();
			result[index] = element; 
		}
		return result;
	}

	/** Unsupported.
	  * @throws UnsupportedOperationException always
	  */
	@Override
	public <T> T[] toArray(T[] a) {
		throw new UnsupportedOperationException();
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

	private final class ForwardIterator implements ListIterator<E> {

		private int index;
		private Node<E> iterPrevious;
		private Node<E> iterNext;
		// See https://docs.oracle.com/javase/7/docs/api/java/util/ListIterator.html#remove()
		// for an explanation of the necessity of keeping track of the last movement.
		private Node<E> lastMovement = null;


		public ForwardIterator(int index, Node<E> iterPrevious, Node<E> iterNext) {
			super();
			this.index = index;
			this.iterPrevious = iterPrevious;
			this.iterNext = iterNext;
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
			size++;
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

		public E removeAndGet() {
			if (lastMovement == null) {
				throw new IllegalStateException();
			}

			if (lastMovement == iterPrevious) { // last movement was forwards
				index--;

				iterPrevious = iterPrevious.getPrevious();
				if (iterPrevious == null) { // removal put us an the beginning
					first = iterNext;
				} else {
					iterPrevious.setNext(iterNext);
				}

				if (iterNext == null) { // we were at end of list
					last = iterPrevious;
				} else {
					iterNext.setPrevious(iterPrevious);
				}
				
			} else if (lastMovement == iterNext) { // last movement was backwards
				iterNext = iterNext.getNext();
				if (iterNext == null) { // removal put us at the end
					last = iterPrevious;
				} else {
					iterNext.setPrevious(iterPrevious);
				}

				if (iterPrevious == null) { // we were at the beginning of list
					first = iterNext;
				} else {
					iterPrevious.setNext(iterNext);
				}
			}

			E result = lastMovement.getValue();
			lastMovement = null;
			size--;
			return result;
		}

		@Override
		public void remove() {
			removeAndGet();
		}

		@Override
		public void set(E e) {
			if (lastMovement == null) {
				throw new IllegalStateException();
			}
			lastMovement.setValue(e);
		}

		public DescendingIterator descending() {
			return new DescendingIterator(this);
		}
	}

	private final class DescendingIterator implements Iterator<E> {
		private ForwardIterator iter;

		public DescendingIterator(DoublyLinkedList<E>.ForwardIterator iter) {
			super();
			this.iter = iter;
		}

		@Override
		public boolean hasNext() {
			return iter.hasPrevious();
		}

		@Override
		public E next() {
			return iter.previous();
		}

		@Override
		public void remove() {
			iter.remove();
		}
		
		public ForwardIterator forward() {
			return iter;
		}
	}
}
