/*
 * Name: Kyle Nero
 * PID: A15900980
 */

import java.util.*;

/**
 * HashTable implementation which will be used to achieve time-efficiency in final FADAF tree
 *
 * @param <T> Generic type of value
 * @author Kyle Nero
 * @since 6/7/20
 */
public class HashTable<T> {

    // constants
    public static final int RESIZE_FACTOR = 2; // resize factor
    public static final int MIN_CAPACITY = 10; // minimum initial capacity
    public static final double MAX_LOAD_FACTOR = (double) 2 / 3; // maximum load factor

    // instance variables
    private LinkedList<T>[] table; // data storage
    private int nElems; // number of elements stored

    private int capacity;


    /**
     * Constructor for hash table.
     *
     * @param capacity the inital capacity of the hash table
     * @throws IllegalArgumentException if capacity is less than the minimum
     *                                  threshold
     */
    @SuppressWarnings("unchecked")
    public HashTable(int capacity) {
        if (capacity < MIN_CAPACITY) {
            throw new IllegalArgumentException();
        }
        this.capacity = capacity;
        table = new LinkedList[capacity];
        nElems = 0;
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
        if (value == null) {
            throw new NullPointerException();
        }
        if (lookup(value)) {
            return false;
        }
        double loadF = (double) nElems / (double) capacity;
        if (loadF > MAX_LOAD_FACTOR) {
            rehash();
        }
        int hash = hashValue(value);
        if (table[hash] == null) {
            table[hash] = new LinkedList<T>();
        }
        table[hash].add(value);
        nElems++;
        return true;
    }

    /**
     * Delete the given value from the hash table.
     *
     * @param value value to delete
     * @return true if the value was deleted, false if the value was not found
     * @throws NullPointerException if the value is null
     */
    public boolean delete(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        if (!lookup(value)) {
            return false;
        }
        int hash = hashValue(value);
        LinkedList<T> list = table[hash];
        int i = 0;
        while (true) {
            if (list.get(i).equals(value)) {
                list.remove(i);
                nElems--;
                return true;
            } else {
                i++;
            }
        }
    }

    /**
     * Check if the given value is present in the hash table.
     *
     * @param value value to look up
     * @return true if the value was found, false if the value was not found
     * @throws NullPointerException if the value is null
     */
    public boolean lookup(T value) {
        if (value == null) {
            throw new NullPointerException();
        }
        int hash = hashValue(value);
        if (table[hash] == null) {
            return false;
        }
        LinkedList<T> list = table[hash];
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).equals(value)) {
                return true;
            }
            i++;
        }
        return false;
    }

    /**
     * Get the total number of elements stored in the hash table.
     *
     * @return total number of elements
     */
    public int size() {
        return nElems;
    }

    /**
     * Get the capacity of the hash table.
     *
     * @return capacity
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Hash function calculated by the hash code of value.
     *
     * @param value input
     * @return hash value (index)
     */
    private int hashValue(T value) {
        int hashVal = value.hashCode() % capacity;
        if (hashVal < 0) {
            hashVal += capacity;
        }
        return hashVal;
    }

    /**
     * Double the capacity of the array and rehash all values.
     */
    @SuppressWarnings("unchecked")
    private void rehash() {
        LinkedList<T>[] oldTable = table;
        table = new LinkedList[capacity *= RESIZE_FACTOR];

        for (int i = 0; i < oldTable.length; i++) {
            table[i] = new LinkedList<T>();
            if (oldTable[i] != null) {
                for (int j = 0; j < oldTable[i].size(); j++) {
                    insert(oldTable[i].get(j));
                }
            }
        }
    }
}
