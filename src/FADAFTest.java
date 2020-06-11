/*
 * NAME: Kyle Nero
 * PID:  A15900980
 */

import org.junit.*;

import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.Assert.*;

/**
 * Class for testing implementation of a FADAF tree
 *
 * @author Kyle Nero
 * @since  6/12/20
 */
public class FADAFTest {
    // Declare instance variables
    FADAF<Integer, String> t; // keys are ints, values are strings

    @Before
    public void setUp() {
        t = new FADAF<>(10);
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
        t.insert(5, "hello5");
        t.insert(5, "hey5");
        t.insert(20, "hi20");
        t.insert(20, "hey20");
        t.insert(20, "hello20");
        t.insert(15, "hi15");
        t.insert(15, "hello15");
        t.insert(15, "hey15");
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
    public void testRemoveAll() {
        assertFalse(t.lookup(10, "hi10"));
        assertFalse(t.lookupAny(10));
        assertFalse(t.remove(10, "hi10"));
        t.insert(10, "hi10");
        t.insert(10, "hello10");
        t.insert(10, "hey10");
        t.insert(5, "hi5");
        t.insert(5, "hello5");
        t.insert(5, "hey5");
        t.insert(20, "hi20");
        t.insert(20, "hey20");
        t.insert(20, "hello20");
        t.insert(15, "hi15");
        t.insert(15, "hello15");
        t.insert(15, "hey15");
        assertEquals(4, t.nUniqueKeys());
        assertEquals(12, t.size());
        assertTrue(t.lookup(10, "hi10"));
        assertTrue(t.lookupAny(10));
        assertTrue(t.removeAll(10));
        assertFalse(t.lookup(10, "hi10"));
        assertFalse(t.lookupAny(10));
        assertEquals(3, t.nUniqueKeys());
        assertEquals(9, t.size());
        assertTrue(t.removeAll(5));
        assertEquals(2, t.nUniqueKeys());
        assertEquals(6, t.size());
        assertTrue(t.removeAll(20));
        assertEquals(1, t.nUniqueKeys());
        assertEquals(3, t.size());
        t.remove(15, "hi15");
        t.remove(15, "hello15");
        assertEquals(1, t.nUniqueKeys());
        assertEquals(1, t.size());
        t.remove(15, "hey15");
        assertEquals(0, t.nUniqueKeys());
        assertEquals(0, t.size());
    }

    @Test
    public void testGetAllKeys() {

    }

    @Test
    public void testGetAllData() {

    }



    @Test
    public void testGetMinMax() {

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


}
