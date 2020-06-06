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
@SuppressWarnings("rawtypes")
public class DAFTree<K extends Comparable<? super K>, D> implements Iterable {

    // instance variables
    private DAFNode<K, D> root; // root node
    private int nElems; // number of elements stored
    private int nKeys; // number of unique keys stored

    /**
     * TODO: Class description
     * 
     * @param <K> Generic type of key
     * @param <D> Generic type of data
     */
    protected class DAFNode<K extends Comparable<? super K>, D> {
        K key;
        D data;
        DAFNode<K, D> left, dup, right; // children
        DAFNode<K, D> par; // parent

        /**
         * Initializes a DAFNode object.
         * 
         * @param key  key of the node
         * @param data data of the node
         * @throws NullPointerException if key or data is null
         */
        public DAFNode(K key, D data) {
            /* TODO */
        }

        /**
         * Check if obj equals to this object.
         * 
         * @param obj object to compare with
         * @return true if equal, false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            /* (Optional) TODO */
            return false;
        }

        /**
         * Returns the hash value of current node.
         * 
         * @return hash value
         */
        @Override
        public int hashCode() {
            /* (Optional) TODO */
            return -1;
        }

        /* PROVIDED HELPERS, MODIFY WITH CAUTION! */

        /**
         * Public helper to swap all DAFNode references of this and the given node.
         *
         * @param other Node to swap with this
         */
        public void swapReferencesWith(DAFNode<K, D> other) {
            DAFNode<K, D> temp = this.left;
            this.left = other.left;
            other.left = temp;
            if (this.left != null) {
                this.left.par = this;
            }
            if (other.left != null) {
                other.left.par = other;
            }

            temp = this.right;
            this.right = other.right;
            other.right = temp;
            if (this.right != null) {
                this.right.par = this;
            }
            if (other.right != null) {
                other.right.par = other;
            }

            // no swap of dup as dup is coupled with the node

            temp = this.par;
            this.changeParentTo(other, other.par);
            other.changeParentTo(this, temp);
        }

        /**
         * Public helper to change this node's par to the given parent. The given child
         * is used to determine which child (left, right, dup) of the given parent this
         * node should be. Only the connection between this and the given parent will
         * update. Does nothing if the given child is not a child of parent.
         *
         * @param child  Old child of the given parent
         * @param parent New parent of this node
         * @throws NullPointerException if child is null
         */
        public void changeParentTo(DAFNode<K, D> child, DAFNode<K, D> parent) {
            if (child == null)
                throw new NullPointerException();

            if (parent == null) {
                this.par = null;
                return;
            }

            if (parent.left == child) {
                parent.left = this;
                this.par = parent;
            } else if (parent.right == child) {
                parent.right = this;
                this.par = parent;
            } else if (parent.dup == child) {
                parent.dup = this;
                this.par = parent;
            }
        }
    }

    /**
     * Initializes an empty DAFTree.
     */
    public DAFTree() {
        /* TODO */
    }

    /**
     * Returns the total number of elements stored in the tree.
     * 
     * @return total number of elements stored
     */
    public int size() {
        /* TODO */
        return -1;
    }

    /**
     * Returns the total number of unique keys stored in the tree.
     * 
     * @return total number of unique keys stored
     */
    public int nUniqueKeys() {
        /* TODO */
        return -1;
    }

    /**
     * Inserts a new node that has given key and data to the tree.
     * 
     * @param key  key to insert
     * @param data data to insert
     * @return the inserted node object, or null if already exist
     * @throws NullPointerException if key or data is null
     */
    public DAFNode<K, D> insert(K key, D data) {
        /* TODO */
        return null;
    }

    /**
     * Checks if the key is stored in the tree.
     * 
     * @param key key to search
     * @return true if found, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean lookupAny(K key) {
        /* TODO */
        return false;
    }

    /**
     * Checks if the specified key-data pair is stored in the tree.
     * 
     * @param key  key to search
     * @param data data to search
     * @return true if found, false otherwise
     * @throws NullPointerException if key or data is null
     */
    public boolean lookup(K key, D data) {
        /* TODO */
        return false;
    }

    /**
     * Returns a LinkedList of all data associated with the given key.
     * 
     * @return list of data (empty if no data found)
     * @throws NullPointerException if the key is null
     */
    public LinkedList<D> getAllData(K key) {
        /* TODO */
        return null;
    }

    /**
     * Removes the node with given key and data from the tree.
     * 
     * @return true if removed, false if this node was not found
     * @throws NullPointerException if key or data is null
     */
    public boolean remove(K key, D data) {
        /* TODO */
        return false;
    }

    /**
     * Removes all nodes with given key from the tree.
     * 
     * @return true if any node is removed, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean removeAll(K key) {
        /* TODO */
        return false;
    }

    /**
     * Returns a tree iterator instance.
     * 
     * @return iterator
     */
    public Iterator<DAFNode<K, D>> iterator() {
        /* TODO */
        return null;
    }

    /**
     * TODO: Class description
     */
    public class DAFTreeIterator implements Iterator<DAFNode<K, D>> {

        /**
         * Initializes a tree iterator instance.
         */
        public DAFTreeIterator() {
            /* TODO */
        }

        /**
         * Checks if the iterator has next element.
         * 
         * @return true if there is a next, false otherwise
         */
        public boolean hasNext() {
            /* TODO */
            return false;
        }

        /**
         * Returns the next node of the iterator.
         * 
         * @return next node
         * @throws NoSuchElementException if the iterator reaches the end of traversal
         */
        public DAFNode<K, D> next() {
            /* TODO */
            return null;
        }
    }

    /* PROVIDED HELPERS, MODIFY WITH CAUTION! */

    /**
     * Public helper to remove the given node in BST's remove style.
     *
     * @param cur Node to remove
     * @boolean true always
     */
    public boolean remove(DAFNode<K, D> cur) {
        if (cur.dup == null && (cur.par == null || cur.par.dup != cur))
            nKeys--;

        if (cur == root) {
            root = removeHelper(cur, cur.key, cur.data);
            if (root != null) {
                root.par = null;
            }
        } else {
            // passing in par to let helper update both par and child reference
            removeHelper(cur.par, cur.key, cur.data);
        }

        nElems--;
        return true;
    }

    /**
     * Helper to remove node recursively in BST style of replacement by in-order
     * successor, with modification of handling dup and also node are swapped
     * instead of data field being replaced.
     *
     * @param root Root
     * @param key  To be removed
     * @return The node that replaces the node to be removed
     */
    private DAFNode<K, D> removeHelper(DAFNode<K, D> root, K key, D data) {
        if (root == null)
            return null;

        // update child reference and make replacement if root is the target
        DAFNode<K, D> replacedChild = null; // this is different from bst
        if (key.compareTo(root.key) < 0) {
            root.left = replacedChild = removeHelper(root.left, key, data);
        } else if (key.compareTo(root.key) > 0) {
            root.right = replacedChild = removeHelper(root.right, key, data);
        } else if (!data.equals(root.data)) { // this is different from bst
            root.dup = replacedChild = removeHelper(root.dup, key, data);
        } else if (root.dup != null) { // this is different from bst
            // swap only left & right
            root.dup.left = root.left;
            root.dup.right = root.right;
            if (root.left != null) {
                root.left.par = root.dup;
            }
            if (root.right != null) {
                root.right.par = root.dup;
            }

            root = root.dup;
        } else if (root.left != null && root.right != null) {
            // the following is all different from bst
            DAFNode<K, D> successor = findMin(root.right);
            DAFNode<K, D> nextRoot = root.right;
            DAFNode<K, D> temp;

            // swap content
            root.swapReferencesWith(successor);
            // swap the pointer back
            temp = root;
            root = successor;
            successor = temp;

            // special case: if root's right is successor,
            // references/connection between them will be broken
            // but will still be handled correctly by following code
            if (nextRoot == root)
                nextRoot = successor;

            root.right = replacedChild = removeHelper(nextRoot, successor.key, successor.data);
        } else {
            root = (root.left != null) ? root.left : root.right;
        }

        // update parent reference
        if (replacedChild != null) // this is different from bst
            replacedChild.par = root;

        return root;
    }

    /**
     * Helper to return the smallest node from a given subroot.
     *
     * @param root Smallest node will be found from this node
     * @return The smallest node from the 'root' node
     */
    private DAFNode<K, D> findMin(DAFNode<K, D> root) {
        DAFNode<K, D> cur = root;
        while (cur.left != null)
            cur = cur.left;
        return cur;
    }

}
