/*
 * NAME: Kyle Nero
 * PID:  A15900980
 */

import org.junit.*;

import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * BSTreeTester class for testing implementation of a duplicates-are-fine tree
 *
 * @author Kyle Nero
 * @since  6/9/20
 */
public class DAFTreeTest {
    // Declare instance variables
    DAFTree<Integer, String> t; // keys are ints, values are strings

    @Before
    public void setUp() {
        t = new DAFTree<>();
    }

    @Test
    public void testInsert() {
        assertFalse(t.lookup(10, "hi10"));
        assertFalse(t.lookupAny(10));
        t.insert(10, "hi10");
        assertTrue(t.lookupAny(10));
        t.insert(10, "hello10");
        t.insert(10, "hey10");
        t.insert(5, "hi5");
        t.insert(20, "hi20");
        t.insert(15, "hi15");
        t.insert(15, "hello15");
        t.insert(15, "hello15");
        assertEquals(7, t.size());
        assertEquals(4, t.nUniqueKeys());
        t.remove(10, "hi10");
        assertFalse(t.lookup(10, "hi10"));
        assertTrue(t.lookupAny(10));
        t.remove(10, "hello10");
        assertEquals(5, t.size());
        assertEquals(4, t.nUniqueKeys());
        t.remove(10, "hey10");
        assertEquals(4, t.size());
        assertEquals(3, t.nUniqueKeys());
        assertFalse(t.lookupAny(10));
    }

    @Test
    public void testRemove() {
        t.insert(10, "hi10");
        t.insert(10, "hello10");
        t.insert(10, "hey10");
        t.insert(5, "hi5");
        t.insert(20, "hi20");
        t.insert(15, "hi15");
        t.insert(15, "hello15");
        //      10
        //     /   \
        //   5      20
        //          /
        //        15
        assertFalse(t.lookup(10, "howdy5"));
        assertTrue(t.remove(15, "hello15"));
        assertFalse(t.lookup(15, "hello15"));
        assertFalse(t.remove(15, "howdy15"));
        assertFalse(t.lookup(15, "howdy15"));
        assertTrue(t.remove(10, "hi10"));
        assertTrue(t.remove(10, "hello10"));
        assertTrue(t.remove(10, "hey10"));
        assertFalse(t.lookup(10, "hi10"));
    }

    @Test
    public void testGetAllData() { // ORDER DOESNT MATTER
//        System.out.println(t.getAllData(10)); // CORRECT
//        t.insert(10, "hi10");
//        System.out.println(t.getAllData(10)); // CORRECT
//        t.insert(10, "hello10");
//        System.out.println(t.getAllData(10)); // CORRECT
//        t.insert(10, "hey10");
//        System.out.println(t.getAllData(10)); // CORRECT
//        t.remove(10, "hi10");
//        System.out.println(t.getAllData(10)); // CORRECT
//        t.remove(10, "hello10");
//        System.out.println(t.getAllData(10)); // CORRECT
//        t.remove(10, "hey10");
//        System.out.println(t.getAllData(10)); // CORRECT
//        t.insert(5, "hi5");
//        t.insert(5, "hello5");
//        t.insert(5, "hey5");
//        System.out.println(t.getAllData(5)); // CORRECT
//        t.remove(5, "hello5");
//        System.out.println(t.getAllData(5)); // CORRECT
//        t.removeAll(5);
//        System.out.println(t.getAllData(10)); // CORRECT
    }

    @Test (expected = NullPointerException.class)
    public void testInsertNullKey() {
        t.insert(null, "hi5");
    }

    @Test (expected = NullPointerException.class)
    public void testInsertNullData() {
        t.insert(5, null);
    }

    @Test (expected = NullPointerException.class)
    public void testInsertNullBoth() {
        t.insert(null, null);
    }

    @Test (expected = NullPointerException.class)
    public void testLookupNullKey() {
        t.lookup(null, "hi5");
    }

    @Test (expected = NullPointerException.class)
    public void testLookupAnyNullKey() {
        t.lookupAny(null);
    }

    @Test (expected = NullPointerException.class)
    public void testRemoveNullKey() {
        t.insert(5, "hi5");
        t.remove(null, "hi5");
    }

    // Iterator Tests

    @Test
    public void testIteratorConstructor() {
        t.insert(10, "hi10");
        t.insert(10, "hey10");
        t.insert(5, "hi5");
        t.insert(5, "hey5");
        t.insert(15, "hi15");
        Iterator<DAFTree<Integer, String>.DAFNode<Integer, String>> iter = t.iterator();
        System.out.println(iter.next().data); // hi5
        System.out.println(iter.next().data); // hey5
        System.out.println(iter.next().data); // hi10
        System.out.println(iter.next().data); // hey10
        System.out.println(iter.next().data); // hi15
    }
}
