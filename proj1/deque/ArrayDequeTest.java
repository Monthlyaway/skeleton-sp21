package deque;


import org.junit.Test;

import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;


/**
 * Performs some basic linked list tests.
 */
public class ArrayDequeTest {

    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {
        ArrayDeque<String> arrd1 = new ArrayDeque<String>();

        assertTrue("A newly initialized LLDeque should be empty", arrd1.isEmpty());
        arrd1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, arrd1.size());
        assertFalse("arrd1 should now contain 1 item", arrd1.isEmpty());

        arrd1.addLast("middle");
        assertEquals(2, arrd1.size());

        arrd1.addLast("back");
        assertEquals(3, arrd1.size());

        System.out.println("Printing out deque: ");
        arrd1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        ArrayDeque<Integer> arrd1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue("arrd1 should be empty upon initialization", arrd1.isEmpty());

        arrd1.addFirst(10);
        // should not be empty
        assertFalse("arrd1 should contain 1 item", arrd1.isEmpty());

        arrd1.removeFirst();
        // should be empty
        assertTrue("arrd1 should be empty after removal", arrd1.isEmpty());
    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {


        ArrayDeque<Integer> arrd1 = new ArrayDeque<>();
        arrd1.addFirst(3);

        arrd1.removeLast();
        arrd1.removeFirst();
        arrd1.removeLast();
        arrd1.removeFirst();

        int size = arrd1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {

        ArrayDeque<String> arrd1 = new ArrayDeque<String>();
        ArrayDeque<Double> arrd2 = new ArrayDeque<Double>();
        ArrayDeque<Boolean> arrd3 = new ArrayDeque<Boolean>();

        arrd1.addFirst("string");
        arrd2.addFirst(3.14159);
        arrd3.addFirst(true);

        String s = arrd1.removeFirst();
        double d = arrd2.removeFirst();
        boolean b = arrd3.removeFirst();
    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {
        ArrayDeque<Integer> arrd1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, arrd1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, arrd1.removeLast());

    }

    @Test
    public void resizeTest() {
        /**
         * Set resize to be public method
         */
        ArrayDeque<Integer> arrd1 = new ArrayDeque<Integer>(20);
        arrd1.addFirst(0);
        arrd1.addFirst(1);
        arrd1.addLast(2);
        arrd1.addLast(3);
        arrd1.addLast(4);
        arrd1.addLast(5);
        arrd1.addLast(6);
        arrd1.printDeque();
        arrd1.resize(100);
        arrd1.printDeque();
    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {
        ArrayDeque<Integer> arrd1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            arrd1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) arrd1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) arrd1.removeLast(), 0.0);
        }

    }

    @Test
    public void randomizedTest() {
        AList<Integer> correct = new AList<>();
        ArrayDeque<Integer> broken = new ArrayDeque<>();

        int N = 500000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                correct.addLast(randVal);
                broken.addLast(randVal);
                assertEquals(correct.size(), broken.size());
                assertEquals(correct.getLast(), broken.getLast());
            } else if (operationNumber == 1) {
                // size
                assertEquals(correct.size(), broken.size());
            } else if (operationNumber == 2 && correct.size() > 0) {
                // getLast
                assertEquals(correct.getLast(), broken.getLast());
            } else if (operationNumber == 3 && correct.size() > 0) {
                // removeLast
                assertEquals(correct.removeLast(), broken.removeLast());
                assertEquals(correct.size(), broken.size());
            }
        }
    }


}


