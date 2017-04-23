package info.danidiaz.util;

import static org.junit.Assert.*;

import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

public class DoublyLinkedListTest {

	// It is important to have independed size() tests because the value is
	// cached and bugs make it diverge from the actual number of elements.
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
	
	private static DoublyLinkedList<Integer> buildIntegerList(int size) {
		DoublyLinkedList<Integer> l = new DoublyLinkedList<>();
		for (int i=0;i<size;i++) {
			l.addLast(i);
		}
		return l;
	}
}
