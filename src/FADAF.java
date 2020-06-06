/*
 * Name: TODO
 * PID: TODO
 */

import java.util.*;

/**
 * TODO: Class description
 *
 * @param <K> Generic type of key
 * @param <D> Generic type of data
 * @author TODO
 * @since TODO
 */
public class FADAF<K extends Comparable<? super K>, D> {

    /*
     * TODO: initialize hash table, DAFTree, and possibly other data structures as
     * instance variables
     */

    /**
     * Constructor for FADAF.
     * 
     * @param capacity initial capacity
     * @throws IllegalArgumentException if capacity is less than the minimum
     *                                  threshold
     */
    public FADAF(int capacity) {
        /* TODO */
    }

    /**
     * Returns the total number of key-data pairs stored.
     * 
     * @return count of key-data pairs
     */
    public int size() {
        /* TODO */
        return -1;
    }

    /**
     * Returns the total number of unique keys stored.
     * 
     * @return count of unique keys
     */
    public int nUniqueKeys() {
        /* TODO */
        return -1;
    }

    /**
     * Insert the given key-data pair.
     * 
     * @param key  key to insert
     * @param data data to insert
     * @return true if the pair is inserted, false if the pair was already present
     * @throws NullPointerException if key or data is null
     */
    public boolean insert(K key, D data) {
        /* TODO */
        return false;
    }

    /**
     * Remove all key-data pairs that share the given key from the FADAF.
     * 
     * @param key key to remove
     * @return true if at least 1 pair is removed, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean removeAll(K key) {
        /* TODO */
        return false;
    }

    /**
     * Remove the specified pair from the FADAF.
     * 
     * @param key  key of the pair to remove
     * @param data data of the pair to remove
     * @return true if this pair is removed, false if this pair is not present
     * @throws NullPointerException if key or data is null
     */
    public boolean remove(K key, D data) {
        /* TODO */
        return false;
    }

    /**
     * Check if any pair with the given key is stored.
     * 
     * @param key key to lookup
     * @return true if any pair is found, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean lookupAny(K key) {
        /* TODO */
        return false;
    }

    /**
     * Check if a pair with the given key and data is stored.
     * 
     * @param key  key of the pair to lookup
     * @param data data of the pair to lookup
     * @return true if the pair is found, false otherwise
     * @throws NullPointerException if key or data is null
     */
    public boolean lookup(K key, D data) {
        /* TODO */
        return false;
    }

    /**
     * Return a LinkedList of all keys (including duplicates) in ascending order.
     * 
     * @return a list of all keys, empty list if no keys stored
     */
    public LinkedList<K> getAllKeys() {
        /* TODO */
        return null;
    }

    /**
     * Return a LinkedList of data paired with the given key.
     * 
     * @param key target key
     * @return a list of data
     * @throws NullPointerException if the key is null
     */
    public LinkedList<D> getAllData(K key) {
        /* TODO */
        return null;
    }

    /**
     * Return the minimum key stored.
     * 
     * @return minimum key, or null if no keys stored
     */
    public K getMinKey() {
        /* TODO */
        return null;
    }

    /**
     * Return the maximum key stored.
     * 
     * @return maximum key, or null if no keys stored
     */
    public K getMaxKey() {
        /* TODO */
        return null;
    }

}
