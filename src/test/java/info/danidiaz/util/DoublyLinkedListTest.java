package info.danidiaz.util;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;

import org.junit.Test;

public class DoublyLinkedListTest {

	// It is important to have independed size() tests because the value is
	// cached and bugs might make it diverge from the actual number of elements.
	@Test
	public void testEmptyListSize() {
		assertEquals("Emtpy list has size 0",
				0,new DoublyLinkedList<>().size());
		assertTrue("Emtpy list is empty",
				new DoublyLinkedList<>().isEmpty());
	}

	@Test
	public void testClearedListSize() {
		DoublyLinkedList<Integer> l = buildIntegerList(5);
		l.clear();
		assertEquals("Cleared list has size 0",0,l.size());
		assertTrue("Cleared list is empty",l.isEmpty());
	}
	
	@Test
	public void testSizeAfterAddFirst() {
		DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
		for (int i=0;i<5;i++) {
			l.addFirst(i);
			assertEquals(String.format("List size after adding %d elements",i+1),
				i+1,l.size());
		}
	}

	@Test
	public void testSizeAfterAddLast() {
		DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
		for (int i=0;i<5;i++) {
			l.addLast(i);
			assertEquals(String.format("List size after adding %d elements",i+1),
				i+1,l.size());
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
		assertNull("peek on empty list",buildIntegerList(0).peek());
		assertNull("peek first on empty list",buildIntegerList(0).peekFirst());
	}

	@Test(expected=NoSuchElementException.class)
	public void testEmptyGetFirst() {
		assertNull(buildIntegerList(0).getFirst());
	}
	
	@Test
	public void testPeekLastOnEmpty() {
		assertNull(buildIntegerList(0).peekLast());
	}

	@Test(expected=NoSuchElementException.class)
	public void testEmptyGetLast() {
		assertNull(buildIntegerList(0).getLast());
	}

	public static DoublyLinkedList<Integer> buildIntegerList(int size) {
		DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
		for (int i=0;i<size;i++) {
			l.addLast(i);
		}
		return l;
	}
}
