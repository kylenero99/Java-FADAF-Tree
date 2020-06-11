/*
 * Name: Kyle Nero
 * PID: A15900980
 */

import java.util.*;

/**
 * Tree structure that supports fast access as well as duplicate keys for different values
 *
 * @param <K> Generic type of key
 * @param <D> Generic type of data
 * @author Kyle Nero
 * @since 6/10/20
 */
public class FADAF<K extends Comparable<? super K>, D> {
    DAFTree<K, D> tree;
    HashTable<K> keyTable;
    HashTable<DAFTree<K, D>.DAFNode<K, D>> nodeTable;


    /**
     * Constructor for FADAF.
     * 
     * @param capacity initial capacity
     * @throws IllegalArgumentException if capacity is less than the minimum
     *                                  threshold
     */
    public FADAF(int capacity) {
        tree = new DAFTree<>();
        keyTable = new HashTable<>(capacity);
        nodeTable = new HashTable<>(capacity);
    }

    /**
     * Returns the total number of key-data pairs stored.
     * 
     * @return count of key-data pairs
     */
    public int size() {
        return tree.size();
    }

    /**
     * Returns the total number of unique keys stored.
     * 
     * @return count of unique keys
     */
    public int nUniqueKeys() {
        return tree.nUniqueKeys();
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
        DAFTree.DAFNode node = tree.insert(key, data); // wont actually insert if returns null
        if (node == null) {
            return false;
        }
        nodeTable.insert(node); // at this point, we know its not contained already
        keyTable.insert(key);   // so we can insert to both hash tables
        return true;
    }

    /**
     * Remove all key-data pairs that share the given key from the FADAF.
     * 
     * @param key key to remove
     * @return true if at least 1 pair is removed, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean removeAll(K key) {
        if (!tree.lookupAny(key)) {
            return false;
        }
        keyTable.delete(key); // remove the key from keyTable
        LinkedList<D> toRemove = tree.getAllData(key);
        DAFTree<K, D>.DAFNode<K, D> temp;
        for (D data : toRemove) {
            nodeTable.delete(tree.new DAFNode<K, D>(key, data)); // remove all from nodeTable
        }
        return tree.removeAll(key); // remove all from tree
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
        if (!tree.lookup(key, data)) {
            return false;
        }
        tree.remove(key, data); // remove key-data pair from tree
        if (!tree.lookupAny(key)) { // if the removal from tree was the only key...
            keyTable.delete(key); // remove from the key table as well
        }
        return nodeTable.delete(tree.new DAFNode<K, D>(key, data)); // finally, remove from nodes
    }

    /**
     * Check if any pair with the given key is stored.
     * 
     * @param key key to lookup
     * @return true if any pair is found, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean lookupAny(K key) {
        return keyTable.lookup(key);
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
        return tree.lookup(key, data);
    }

    /**
     * Return a LinkedList of all keys (including duplicates) in ascending order.
     * 
     * @return a list of all keys, empty list if no keys stored
     */
    public LinkedList<K> getAllKeys() {
        LinkedList<K> result = new LinkedList<>();
        Iterator<DAFTree<K, D>.DAFNode<K, D>> iter = tree.iterator();
        while (iter.hasNext()) {
            result.add(iter.next().key);
        }
        return result;
    }

    /**
     * Return a LinkedList of data paired with the given key.
     * 
     * @param key target key
     * @return a list of data
     * @throws NullPointerException if the key is null
     */
    public LinkedList<D> getAllData(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        LinkedList<D> result = new LinkedList<>();
        DAFTree<K, D>.DAFNode<K, D> top = tree.lookupKeyReturnTop(key);
        if (top == null) {
            return result;
        }
        while (top != null) {
            result.add(top.data);
            top = top.dup;
        }
        Collections.sort((List)result);
        return result;
    }

    /**
     * Return the minimum key stored.
     * 
     * @return minimum key, or null if no keys stored
     */
    public K getMinKey() {
        DAFTree<K, D>.DAFNode<K, D> runner = tree.getRoot();
        if (runner == null) {
            return null;
        }
        while (runner.left != null) {
            runner = runner.left;
        }
        return runner.key;
    }

    /**
     * Return the maximum key stored.
     * 
     * @return maximum key, or null if no keys stored
     */
    public K getMaxKey() {
        DAFTree<K, D>.DAFNode<K, D> runner = tree.getRoot();
        while (runner.right != null) {
            runner = runner.right;
        }
        return runner.key;
    }

}
