package info.danidiaz.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class DoublyLinkedListTest {

/*	@Test
	public void test() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testEmptyListSize() {
		assertEquals("Emtpy list has size 0",
				new DoublyLinkedList<>().size(),0);
	}
}
