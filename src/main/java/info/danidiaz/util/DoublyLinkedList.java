package info.danidiaz.util;

import java.util.Collection;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * <p>An implementation of a doubly linked list.<p>
 * 
 * <p>Some methods which the standard interfaces designate as optional have not
 * been implemented and throw {@link UnsupportedOperationException}. In
 * particular random-access methods, which are bound to be inefficient for
 * linked lists.</p>
 * 
 * <p>Also not implemented are methods like {@link subList} that are out of
 * scope for the exercise.</p>
 * 
 * <p>Intermediate insertions and deletions can be done using the {@link
 * ForwardIterator} returned by {@link listIterator()}.
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
    }

    @Override
    public void addLast(E e) {
        listIteratorAtEnd().add(e);
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
        final ForwardIterator iter = listIterator();
        if (iter.hasNext()) {
            E result = iter.next();
            iter.remove();
            return result;
        }
        return null;
    }

    @Override
    public E pollLast() {
        final ForwardIterator iter = listIteratorAtEnd();
        if (iter.hasPrevious()) {
            E result = iter.previous();
            iter.remove();
            return result;
        }
        return null;
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
        final ForwardIterator iter = listIterator();
        if (iter.hasNext()) {
            E result = iter.next();
            iter.remove();
            return result;
        }
        throw new NoSuchElementException();
    }

    @Override
    public E removeLast() {
        final ForwardIterator iter = listIteratorAtEnd();
        if (iter.hasPrevious()) {
            E result = iter.previous();
            iter.remove();
            return result;
        }
        throw new NoSuchElementException();
    }

    @Override
    public boolean remove(Object o) {
        return removeFirstOccurrence(o);
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        ListIterator<E> iter = iterOf(o);
        if (iter!=null) {
            iter.remove();
            return true;
        }
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        ListIterator<E> iter = iterLastOf(o);
        if (iter!=null) {
            iter.remove();
            return true;
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        addLast(e);
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        for (E e:c) {
            addLast(e);
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
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
        if (index >= 0 && index < size()) {
            Iterator<E> iter = listIterator(index);
            return iter.next();
        }
        throw new IndexOutOfBoundsException();
    }

    /**
     * Variant of {@link indexOf} which returns an iterator.
     * @param o Element to search for. 
     * @return null if the element isn't present, otherwise an iterator that has
     * just passed the element.
     */
    public ForwardIterator iterOf(Object o) {
        ForwardIterator iter = listIterator();
        if (o == null) {
            while (iter.hasNext()) {
                E element = iter.next();
                if (element == null) {
                    return iter;
                }
            }
        } else {
            while (iter.hasNext()) {
                E element = iter.next();
                if (o.equals(element)) {
                    return iter;
                }
            }
        }
        return null;
    }
    
    @Override
    public int indexOf(Object o) {
        ListIterator<E> iter = iterOf(o);
        return iter==null? -1 : iter.previousIndex();
    }


    @Override
    public Iterator<E> iterator() {
        return listIterator();
    }

    /**
     * Variant of {@link lastIndexOf} which returns an iterator.
     * @param o Element to search for. 
     * @return null if the element isn't present, otherwise an iterator that has
     * just passed the element while going backwards.
     */
    public ForwardIterator iterLastOf(Object o) {
        ForwardIterator iter = listIteratorAtEnd();
        if (o == null) {
            while (iter.hasPrevious()) {
                E element = iter.previous();
                if (element == null) {
                    return iter;
                }
            }
        } else {
            while (iter.hasPrevious()) {
                E element = iter.previous();
                if (element != null && element.equals(o)) {
                    return iter;
                }
            }
        }
        return null;
    }

    @Override
    public int lastIndexOf(Object o) {
        ListIterator<E> iter = iterLastOf(o);
        return iter==null? -1 : iter.nextIndex();
    }

    @Override
    public ForwardIterator listIterator() {
        return new ForwardIterator(0,null,first);
    }

    @Override
    public DescendingIterator descendingIterator() {
        return new ForwardIterator(size,last,null).descending();
    }

    public ForwardIterator listIteratorAtEnd() {
        return descendingIterator().forward();
    }

    @Override
    public ForwardIterator listIterator(int index) {
        final ForwardIterator iter = listIterator();
        for (int i=0;i<index;i++) {
            if (i < index-1 && !iter.hasNext()) {
                throw new IndexOutOfBoundsException();
            }
            iter.next();
        }
        return iter;
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

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append('[');
        Iterator<E> iter = iterator();
        while (iter.hasNext()) {
            E e = iter.next();
            if (e==null) {
                builder.append("null");
            } else {
                builder.append(e.toString());
            }
            if (iter.hasNext()) {
                builder.append(',');
            }
        }
        builder.append(']');
        return builder.toString();
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

    /** Unsupported.
      * @throws UnsupportedOperationException always
      */
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException();
    }

    /** Unsupported.
      * @throws UnsupportedOperationException always
      */
    @Override
    public void add(int index, E element) {
        throw new UnsupportedOperationException();
        
    }

    /** Unsupported.
      * @throws UnsupportedOperationException always
      */
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
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
        private Node<E> previous;
        private Node<E> next;
        public Node(E value, Node<E> previous,Node<E> next) {
            super();
            this.value = value;
            this.previous = previous;
            this.next = next;
        }
        public Node<E> getPrevious() {
            return previous;
        }
        public void setPrevious(Node<E> previous) {
            this.previous = previous;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext(Node<E> next) {
            this.next = next;
        }
        public E getValue() {
            return value;
        }
        public void setValue(E e) {
            value = e;
        }
        // Useful for debugging.
        public String toString() {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("{ val = %s",getValue()));
            if (getPrevious()!=null) {
                builder.append(String.format(", previous = %s ",getPrevious().getValue()));
            }
            if (getNext()!=null) {
                builder.append(String.format(", next = %s ",getNext().getValue()));
            }
            builder.append(" }");
            return builder.toString();
        }
    }

    public final class ForwardIterator implements ListIterator<E> {

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
            final Node<E> newNode = new Node<E>(arg0,iterPrevious,iterNext);
            if (iterPrevious==null) {
                first = newNode;
            } else {
                iterPrevious.setNext(newNode);
            }
            if (iterNext==null) {
                last = newNode;
            } else {
                iterNext.setPrevious(newNode);
            }
            iterPrevious = newNode; 
            lastMovement = null;
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
        public int nextIndex() {
            return index;
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
            lastMovement = null;
            size--;
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

    public final class DescendingIterator implements Iterator<E> {
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
