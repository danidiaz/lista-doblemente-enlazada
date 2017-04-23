package info.danidiaz.util;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class DoublyLinkedListTest {

    private DoublyLinkedList<Integer> none;
    private DoublyLinkedList<Integer> one;
    private DoublyLinkedList<Integer> two;
    private DoublyLinkedList<Integer> three;
    private DoublyLinkedList<Integer> many;

    @Before
    public void setUp() {
        none = buildIntegerList(0);
        one = buildIntegerList(1);
        two = buildIntegerList(2);
        three = buildIntegerList(3);
        many = buildIntegerList(7);
    }
      
    // It is important to have independent size() tests because the value is
    // cached and bugs might make it diverge from the actual number of elements.
    @Test
    public void testEmptyListSize() {
        assertEquals("Emtpy list has size 0",
                0,none.size());
        assertTrue("Emtpy list is empty", none.isEmpty());
    }

    @Test
    public void testClearedListSize() {
        many.clear();
        assertEquals("Cleared list has size 0",0,many.size());
        assertTrue("Cleared list is empty",many.isEmpty());
    }
    
    @Test
    public void testSizeAfterAddFirst() {
        for (int i=0;i<5;i++) {
            none.addFirst(i);
            assertEquals(String.format("List size after adding %d elements",i+1),
                i+1,none.size());
        }
    }

    @Test
    public void testSizeAfterAddLast() {
        for (int i=0;i<5;i++) {
            none.addLast(i);
            assertEquals(String.format("List size after adding %d elements",i+1),
                i+1,none.size());
        }
    }

    @Test
    public void testSizeAfterAddMiddle() {
        final int initialSize = 5;
        final int howManyToInsert = 4;
        for (int startingIndex = 0;startingIndex <= initialSize;startingIndex++) {
            DoublyLinkedList<Integer> l = buildIntegerList(initialSize);
            ListIterator<Integer> iter = l.listIterator(startingIndex);
            for (int i=0;i<howManyToInsert;i++) {
                iter.add(i+100);
            }
            assertEquals(String.format("List size after adding %d elements to list of %d elements at index %d",howManyToInsert,initialSize,startingIndex),
                initialSize+howManyToInsert,l.size());
        }
    }
    
    @Test
    public void testSizeAfterRemoves() {
        final int originalSize = 7;
        DoublyLinkedList<Integer> l = buildIntegerList(originalSize);
        l.removeFirst();
        assertEquals(String.format("List of %d after removeFirst",originalSize),originalSize-1,l.size());

        l = buildIntegerList(originalSize);
        l.removeLast();
        assertEquals(String.format("List of %d after removeLast",originalSize),originalSize-1,l.size());
        
        l = buildIntegerList(originalSize);
        ListIterator<Integer> iter = l.listIterator(originalSize/2);
        
        int howManyDeleted = 0;
        while (iter.hasNext()) {
            iter.next();
            iter.remove();
            howManyDeleted++;
            assertEquals(String.format("List of %d after removing %d elements from the middle",originalSize,howManyDeleted),originalSize-howManyDeleted,l.size());
        }

        howManyDeleted = 0;
        l = buildIntegerList(originalSize);
        iter = l.listIteratorAtEnd();
        while (iter.hasPrevious()) {
            iter.previous();
            iter.remove();
            howManyDeleted++;
            assertEquals(String.format("List of %d after removing %d elements backwards from the middle",originalSize,howManyDeleted),originalSize-howManyDeleted,l.size());
        }
    }
    
    @Test
    public void testPeekFirstOnEmpty() {
        assertNull("peek on empty list",none.peek());
        assertNull("peek first on empty list",none.peekFirst());
        
        one.removeFirst();
        assertNull("peek on empty list 2",one.peekFirst());
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyGetFirst() {
        none.getFirst();
    }
    
    @Test
    public void testPeekLastOnEmpty() {
        assertNull("peek last on empty list", none.peekLast());

        one.removeFirst();
        assertNull("peek last on empty list 2",one.peekLast());
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyGetLast() {
        none.getLast();
    }

    @Test
    public void testPollFirstOnEmpty() {
        assertNull("poll on empty list",none.poll());
        assertNull("poll first on empty list",none.pollFirst());

        one.removeFirst();
        assertNull("poll first on empty list 2",one.pollFirst());
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyRemoveFirst() {
        none.removeFirst();
    }
    
    @Test
    public void testPollLastOnEmpty() {
        assertNull("poll last on empty list", none.pollLast());

        one.removeFirst();
        assertNull("poll last on empty list 2",one.pollLast());
    }

    @Test(expected=NoSuchElementException.class)
    public void testEmptyRemoveLast() {
        none.removeLast();
    }
    
    @Test
    public void testPollFirst() {
        for (int i=0;i<many.size();i++) {
            assertEquals((Integer)i,many.pollFirst());
        }
    }
    
    @Test
    public void testRemoveFirst() {
        for (int i=0;i<many.size();i++) {
            assertEquals((Integer)i,many.removeFirst());
        }
    }

    @Test
    public void testPollLast() {
        for (int i=many.size()-1;i>=0;i--) {
            assertEquals((Integer)i,many.pollLast());
        }
    }

    @Test
    public void testAddFirst() {
        three.addFirst(-1);
        three.addFirst(-2);
        assertArrayEquals(new Integer []{ -2,-1,0,1,2 }, three.toArray());
    }

    @Test
    public void testAddLast() {
        three.addLast(3);
        three.addLast(4);
        assertArrayEquals(new Integer []{ 0,1,2,3,4 }, three.toArray());
    }

    @Test
    public void testRemoveLast() {
        for (int i=many.size()-1;i>=0;i--) {
            assertEquals((Integer)i,many.removeLast());
        }
    }

    @Test
    public void testToArray() {
        assertArrayEquals(new Integer []{ 0,1,2 }, three.toArray());
    }

    @Test
    public void testGet() {
        for (int i=0;i<many.size();i++) {
            assertEquals(String.format("Getting index %d",i),
                    (Integer)i,many.get(i));
        }
    }
    
    @Test
    public void testIterNextIndex() {
        ListIterator<Integer> iter = two.listIterator();
        assertEquals("starting value",0,iter.nextIndex());

        iter.next();
        assertEquals("after 1st elem",1,iter.nextIndex());

        iter.add(17);
        assertEquals("after insertion",2,iter.nextIndex());

        iter.next();
        assertEquals("after 3rd elem",3,iter.nextIndex());

        iter.remove();
        assertEquals("after deletion",2,iter.nextIndex());
    }

    @Test
    public void testIterNextIndexAtBeginning() {
        ListIterator<Integer> iter = many.listIterator();
        assertEquals(0,iter.nextIndex());
    }

    @Test
    public void testIterNextIndexAtEnd() {
        ListIterator<Integer> iter = many.listIterator();
        while (iter.hasNext()) {
            iter.next();
        }
        assertEquals("iterating",7,iter.nextIndex());
        
        iter = many.listIteratorAtEnd();
        assertEquals("constructed",7,iter.nextIndex());
    }

    @Test
    public void testIterPreviousIndex() {
        ListIterator<Integer> iter = two.listIterator();
        assertEquals("starting value",-1,iter.previousIndex());

        iter.next();
        assertEquals("after 1st elem",0,iter.previousIndex());

        iter.add(17);
        assertEquals("after insertion",1,iter.previousIndex());

        iter.next();
        assertEquals("after 3rd elem",2,iter.previousIndex());

        iter.remove();
        assertEquals("after deletion",1,iter.previousIndex());
    }
    
    @Test
    public void testIterPreviousIndexAtBeginning() {
        ListIterator<Integer> iter = many.listIterator();
        assertEquals(-1,iter.previousIndex());
    }

    /**
     * As stated in the contract of {@link ListIterator} remove method, remove
     * can't be called just after calling add.
     */
    @Test(expected=IllegalStateException.class)
    public void testIterIllegalRemoveAfterAdd() {
        ListIterator<Integer> iter = many.listIterator();
        iter.add(17);
        iter.remove();
    }

    @Test(expected=IllegalStateException.class)
    public void testIterIllegalRemoveAfterRemove() {
        ListIterator<Integer> iter = many.listIteratorAtEnd();
        iter.remove();
        iter.remove();
    }

    @Test(expected=IllegalStateException.class)
    public void testIterIllegalRemoveBeforeNext() {
        ListIterator<Integer> iter = many.listIterator();
        iter.remove();
    }

    @Test()
    public void testIterRemoveAfterNext() {
        ListIterator<Integer> iter = three.listIterator();
        iter.next();
        iter.next();
        iter.remove();
        assertArrayEquals(new Object[] {0,2},three.toArray());
    }

    @Test()
    public void testIterRemoveAfterPrevious() {
        ListIterator<Integer> iter = three.listIterator();
        iter.next();
        iter.next();
        iter.previous();
        iter.remove();
        assertArrayEquals(new Object[] {0,2},three.toArray());
    }
    
    @Test()
    public void testIterNextAndPrevious() {
        ListIterator<Integer> iter = three.listIterator();
        Integer e1 = iter.next();
        Integer e2 = iter.previous();
        Integer e3 = iter.next();
        Integer e4 = iter.next();
        Integer e5 = iter.next();
        Integer e6 = iter.previous();
        Integer e7 = iter.previous();
        assertArrayEquals(new Object[] {0,0,0,1,2,2,1}
                         ,new Object[] {e1,e2,e3,e4,e5,e6,e7});
    }
    

    @Test()
    public void testDescendingIterator() {
        DoublyLinkedList<Integer>.DescendingIterator iter = 
                three.descendingIterator();

        Integer e1 = iter.next();
        Integer e2 = iter.next();
        Integer e3 = iter.next();
        
        assertArrayEquals(new Object[] {2,1,0}
                         ,new Object[] {e1,e2,e3});
        
        assertEquals("next index of the underlying iterator",
                     0, 
                     iter.forward().nextIndex());
    }

    public static DoublyLinkedList<Integer> buildIntegerList(int size) {
        DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
        for (int i=0;i<size;i++) {
            l.addLast(i);
        }
        return l;
    }
}
