package info.danidiaz.util;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class IndexOutOfBoundsTest {
	private final static int LIST_SIZE = 5;
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {{-1}, {LIST_SIZE}, {LIST_SIZE+1} });     
    }
   
    final private int index;
	public IndexOutOfBoundsTest(int index) {
		super();
		this.index = index;
	}
    
	// This silly test actually caught a bug!
	@Test(expected=IndexOutOfBoundsException.class)
	public void test() {
		DoublyLinkedListTest.buildIntegerList(LIST_SIZE).get(index);
	}
    
}
