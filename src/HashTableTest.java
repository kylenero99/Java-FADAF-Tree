/*
 * NAME: Kyle Nero
 * PID:  A15900980
 */

import org.junit.*;

import static org.junit.Assert.*;

/**
 * Tester class for implementation of a hash table
 *
 * @author Kyle Nero
 * @since  6/8/20
 */
public class HashTableTest {
    // Declare instance variables
    HashTable table;

    @Before
    public void setUp() {
        table = new HashTable(10);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testConstructorSmallCapacity() {
        table = new HashTable(5);
    }

    @Test
    public void testInsert() {
        assertEquals(10, table.capacity());
        table.insert("0");
        table.insert("1");
        table.insert("2");
        table.insert("3");
        table.insert("4");
        table.insert("5");
        table.insert("6");
        assertEquals(10, table.capacity());
        table.insert("7");
        assertEquals(20, table.capacity());

    }

    @Test (expected = NullPointerException.class)
    public void testInsertNull() {
        table.insert(null);
    }

    @Test
    public void testLookup() {
        assertFalse(table.lookup("1"));
        table.insert("1");
        assertTrue(table.lookup("1"));
        assertFalse(table.lookup("11"));

        table.insert("10");
        assertTrue(table.lookup("10"));

        table.delete("10");
        assertFalse(table.lookup("10"));
    }

    @Test (expected = NullPointerException.class)
    public void testLookupNull() {
        table.lookup(null);
    }

    @Test
    public void testDelete() {
        assertEquals(0, table.size());
        table.insert("Kyle");
        assertEquals(1, table.size());
        table.delete("Kyle");
        assertEquals(0, table.size());

        table.insert("Kyle1");
        table.insert("Kyle2");
        table.insert("Kyle3");
        assertEquals(3, table.size());

        table.insert("Kyle2");
        table.insert("Kyle3");
        assertEquals(3, table.size());
        assertTrue(table.delete("Kyle1"));
        assertTrue(table.delete("Kyle2"));
        assertTrue(table.delete("Kyle3"));
        assertFalse(table.delete("Kyle2"));
        assertFalse(table.delete("Kyle3"));
        assertEquals(0, table.size());

    }

    @Test (expected = NullPointerException.class)
    public void testDeleteNull() {
        table.delete(null);
    }

    @Test
    public void testSize() { // getter, one test
        assertEquals(0, table.size());
        assertTrue(table.insert("1"));
        assertFalse(table.insert("1"));
        assertFalse(table.insert("1"));
        assertEquals(1, table.size());
        assertTrue(table.delete("1"));
        assertFalse(table.delete("1"));
        assertFalse(table.delete("1"));
        assertEquals(0, table.size());
    }

    @Test
    public void testCapacity() { // getter, one test
        assertEquals(10, table.capacity());
        table.insert("0");
        table.insert("1");
        table.insert("2");
        table.insert("3");
        table.insert("4");
        table.insert("5");
        table.insert("6");
        table.insert("7");
        assertEquals(20, table.capacity());
        table.insert("8");
        table.insert("9");
        table.insert("10");
        table.insert("11");
        table.insert("13");
        table.insert("14");
        table.insert("15");
        assertEquals(40, table.capacity());

    }

}
