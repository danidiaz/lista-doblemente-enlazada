package info.danidiaz.util;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * This class defines a micro-language for data-driven tests of  iterator
 * operations.
 * 
 * Each test supplies a list of iterator commands, the initial
 * state of the test list, a list of elements that should be
 * "seen" by the iterator, and also the final expected state of
 * the test list.
 *
 */
@RunWith(Parameterized.class)
public class IteratorOpsTest {
    
    private enum IterOp {
        NEXT,
        PREVIOUS,
        REMOVE,
        ADD
    } 
    
    private final static int STARTING_FRESH_ELEMENT = 100;
    
    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] { 
            { new IterOp[]  {IterOp.ADD,IterOp.ADD,IterOp.NEXT,IterOp.REMOVE}, // op sequence
              new Integer[] {1}, // original list 
              new Integer[] {1}, // expected traversed elements
              new Integer[] {100,101}  // expected final list
            }, 
            { new IterOp[]  {IterOp.NEXT,IterOp.NEXT,IterOp.REMOVE,IterOp.NEXT,IterOp.ADD}, // op sequence
              new Integer[] {1,2,3}, // original list 
              new Integer[] {1,2,3}, // expected traversed elements
              new Integer[] {1,3,100}  // expected final list
            }, 
            { new IterOp[]  {IterOp.NEXT,IterOp.NEXT,IterOp.PREVIOUS,IterOp.REMOVE}, // op sequence
              new Integer[] {1,2,3}, // original list 
              new Integer[] {1,2,2}, // expected traversed elements
              new Integer[] {1,3}  // expected final list
            }, 
        });     
    }
   
    final private IterOp[] miniprogram;
    final private Integer[] originalList;
    final private Integer[] expectedTraversedValues;
    final private Integer[] expectedFinalList;

    public IteratorOpsTest(IterOp[] miniprogram, Integer[] originalList, Integer[] expectedTraversedValues,
            Integer[] expectedFinalList) {
        super();
        this.miniprogram = miniprogram;
        this.originalList = originalList;
        this.expectedTraversedValues = expectedTraversedValues;
        this.expectedFinalList = expectedFinalList;
    }

    @Test()
    public void testIteratorActions() {
        int nextFreshElement = STARTING_FRESH_ELEMENT;
        
        DoublyLinkedList<Integer> testList = new DoublyLinkedList<>();
        for (int i=0; i < originalList.length;i++) {
            testList.addLast(originalList[i]);
        }
        
        List<Integer> traversedValues = new ArrayList<>();
        ListIterator<Integer> iter = testList.listIterator();
        for (IterOp op : miniprogram)  {
            switch (op) {
                case NEXT:
                    Integer traversedNext = iter.next();
                    traversedValues.add(traversedNext);
                    break;
                        
                case PREVIOUS:
                    Integer traversedPrevious = iter.previous();
                    traversedValues.add(traversedPrevious);
                    break;
                             
                case REMOVE: 
                    iter.remove();
                    break;
                case ADD: 
                    iter.add(nextFreshElement);
                    nextFreshElement++;
                    break;
            }
        }
        assertArrayEquals("traversed values",
                          expectedTraversedValues,
                          traversedValues.toArray());
        assertArrayEquals("final list",
                          expectedFinalList,
                          testList.toArray());
    }
    
}
