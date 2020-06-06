/*
 * Name: TODO
 * PID: TODO
 */

import java.util.*;

/**
 * TODO: Class description
 *
 * @param <T> Generic type of value
 * @author TODO
 * @since TODO
 */
public class HashTable<T> {

    // constants
    public static final int RESIZE_FACTOR = 2; // resize factor
    public static final int MIN_CAPACITY = 10; // minimum initial capacity
    public static final double MAX_LOAD_FACTOR = (double) 2 / 3; // maximum load factor

    // instance variables
    private LinkedList<T>[] table; // data storage
    private int nElems; // number of elements stored

    /**
     * Constructor for hash table.
     * 
     * @throws IllegalArgumentException if capacity is less than the minimum
     *                                  threshold
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        /* TODO */
    }

    /**
     * Insert the value into the hash table.
     *
     * @param value value to insert
     * @return true if the value was inserted, false if the value was already
     *         present
     * @throws NullPointerException if the value is null
     */
    public boolean insert(T value) {
        /* TODO */
        return false;
    }

    /**
     * Delete the given value from the hash table.
     *
     * @param value value to delete
     * @return true if the value was deleted, false if the value was not found
     * @throws NullPointerException if the value is null
     */
    public boolean delete(T value) {
        /* TODO */
        return false;
    }

    /**
     * Check if the given value is present in the hash table.
     *
     * @param value value to look up
     * @return true if the value was found, false if the value was not found
     * @throws NullPointerException if the value is null
     */
    public boolean lookup(T value) {
        /* TODO */
        return false;
    }

    /**
     * Get the total number of elements stored in the hash table.
     *
     * @return total number of elements
     */
    public int size() {
        /* TODO */
        return -1;
    }

    /**
     * Get the capacity of the hash table.
     *
     * @return capacity
     */
    public int capacity() {
        /* TODO */
        return -1;
    }

    /**
     * Hash function calculated by the hash code of value.
     *
     * @param value input
     * @return hash value (index)
     */
    private int hashValue(T value) {
        /* TODO */
        return -1;
    }

    /**
     * Double the capacity of the array and rehash all values.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        /* TODO */
    }

}
