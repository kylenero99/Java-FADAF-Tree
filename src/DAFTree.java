/*
 * Name: Kyle Nero
 * PID: A15900980
 */

import java.util.*;

/**
 * Tree structure with duplicate key support
 * 
 * @param <K> Generic type of key
 * @param <D> Generic type of data
 * @author Kyle Nero
 * @since 6/9/20
 */
@SuppressWarnings("rawtypes")
public class DAFTree<K extends Comparable<? super K>, D> implements Iterable {

    // instance variables
    private DAFNode<K, D> root; // root node
    private int nElems; // number of elements stored
    private int nKeys; // number of unique keys stored

    /**
     * Node representation for tree that allows duplicate keys
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
            if (key == null || data == null) {
                throw new NullPointerException();
            }
            this.key = key;
            this.data = data;
        }

        /**
         * Check if obj equals to this object.
         * 
         * @param obj object to compare with
         * @return true if equal, false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof DAFNode)) {
                return false;
            }
            DAFNode node = (DAFNode) obj;
            if (node.key.equals(this.key) && node.data.equals(this.data)) {
                return true;
            }
            return false;
        }

        /**
         * Returns the hash value of current node.
         * 
         * @return hash value
         */
        @Override
        public int hashCode() {
            return key.hashCode() + data.hashCode();
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
        nElems = 0;
        nKeys = 0;
    }

    /**
     * Returns the total number of elements stored in the tree.
     * 
     * @return total number of elements stored
     */
    public int size() {
        return nElems;
    }

    /**
     * Returns the total number of unique keys stored in the tree.
     * 
     * @return total number of unique keys stored
     */
    public int nUniqueKeys() {
        return nKeys;
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
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        if (lookup(key, data)) { // if key-data pair already contained, return null
            return null;
        }
        DAFNode<K, D> currInsertion = new DAFNode(key, data);
        if (root == null) { // THIS IS NECESSARY! First insertion must initialize root!
            this.root = currInsertion;
            nElems++;
            nKeys++;
            return currInsertion;
        }
        DAFNode<K, D> currTraversal = root;
        while (true) {
            if (currInsertion.key.equals(currTraversal.key)) { // found the right key
                while (currTraversal.dup != null) {
                    currTraversal = currTraversal.dup; // move to the bottom of the duplicates
                }
                currTraversal.dup = currInsertion; // actual insertion step
                currInsertion.par = currTraversal;
                nElems++;
                return currInsertion;
            } else if (currInsertion.key.compareTo(currTraversal.key) > 0) { // move/insert right
                if (currTraversal.right == null) {
                    currTraversal.right = currInsertion;
                    currInsertion.par = currTraversal;
                    nKeys++;
                    nElems++;
                    return currInsertion;
                }
                currTraversal = currTraversal.right;
            } else {
                if (currTraversal.left == null) { // move/insert left
                    currTraversal.left = currInsertion;
                    currInsertion.par = currTraversal;
                    nKeys++;
                    nElems++;
                    return currInsertion;
                }
                currTraversal = currTraversal.left;

            }
        }
    }

    /**
     * Checks if the key is stored in the tree.
     * 
     * @param key key to search
     * @return true if found, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean lookupAny(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        DAFNode<K, D> currTraversal = root;
        while (currTraversal != null) {
            if (currTraversal.key.equals(key)) {
                return true;
            } else if (key.compareTo(currTraversal.key) > 0) {
                currTraversal = currTraversal.right;
            } else {
                currTraversal = currTraversal.left;
            }
        }
        return false;
    }

    /**
     * Returns the top node with a given key.
     *
     * @param key key to search
     * @return the top node with a given key
     * @throws NullPointerException if the key is null
     */
    public DAFNode<K, D> lookupKeyReturnTop(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        DAFNode<K, D> currTraversal = root;
        while (currTraversal != null) {
            if (currTraversal.key.equals(key)) {
                return currTraversal;
            } else if (key.compareTo(currTraversal.key) > 0) {
                currTraversal = currTraversal.right;
            } else {
                currTraversal = currTraversal.left;
            }
        }
        return null;
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
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        DAFNode<K, D> currTraversal = root;
        while (currTraversal != null) {
            if (currTraversal.key.equals(key)) {
                while (currTraversal != null) {
                    if (currTraversal.data.equals(data)) {
                        return true; // should be getting here for duplicate key-value pairs
                    }
                    currTraversal = currTraversal.dup;
                }
                return false;
            } else if (key.compareTo(currTraversal.key) > 0) {
                currTraversal = currTraversal.right;
            } else {
                currTraversal = currTraversal.left;
            }
        }
        return false;
    }

    /**
     * Returns a LinkedList of all data associated with the given key.
     * 
     * @return list of data (empty if no data found)
     * @throws NullPointerException if the key is null
     */
    public LinkedList<D> getAllData(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        LinkedList<D> result = new LinkedList();
        DAFNode<K, D> currTraversal = root;
        while (currTraversal != null) {
            if (currTraversal.key.equals(key)) {
                while (currTraversal != null) {
                    result.add(currTraversal.data);
                    currTraversal = currTraversal.dup;
                }
            } else if (key.compareTo(currTraversal.key) > 0) {
                currTraversal = currTraversal.right;
            } else {
                currTraversal = currTraversal.left;
            }
        }
        return result;
    }

    /**
     * Removes the node with given key and data from the tree.
     * 
     * @return true if removed, false if this node was not found
     * @throws NullPointerException if key or data is null
     */
    public boolean remove(K key, D data) {
        if (key == null || data == null) {
            throw new NullPointerException();
        }
        DAFNode<K, D> currTraversal = root;
        while (currTraversal != null) {
            if (currTraversal.key.equals(key)) {
                while (currTraversal != null) { // found the right key
                    if (currTraversal.data.equals(data)) { // found the right data
                        return remove(currTraversal);
                    } else {
                        if (currTraversal.dup == null) {
                            return false;
                        }
                        currTraversal = currTraversal.dup;
                    }
                }
                return true;
            } else if (key.compareTo(currTraversal.key) > 0) {
                currTraversal = currTraversal.right;
            } else {
                currTraversal = currTraversal.left;
            }
        }
        return false;
    }

    /**
     * Removes all nodes with given key from the tree.
     * 
     * @return true if any node is removed, false otherwise
     * @throws NullPointerException if the key is null
     */
    public boolean removeAll(K key) {
        if (key == null) {
            throw new NullPointerException();
        }
        DAFNode<K, D> currTraversal = root;
        while (currTraversal != null) {
            if (currTraversal.key.equals(key)) { // if key is found
                while (currTraversal != null) {
                    remove(currTraversal.key, currTraversal.data); // remove it from the tree
                    currTraversal = currTraversal.dup;
                }
                return true;
            } else if (key.compareTo(currTraversal.key) > 0) {
                currTraversal = currTraversal.right;
            } else {
                currTraversal = currTraversal.left;
            }
        }
        return false;
    }

    public DAFNode<K, D> getRoot() {
        return root;
    }

    /**
     * Returns a tree iterator instance.
     * 
     * @return iterator
     */
    public Iterator<DAFNode<K, D>> iterator() {
        return new DAFTreeIterator();
    }

    /**
     * Representation of a DAFTree iterator to traverse a DAFTree
     */
    public class DAFTreeIterator implements Iterator<DAFNode<K, D>> {
        private Stack<DAFNode<K, D>> stack;

        /**
         * Initializes a tree iterator instance.
         */
        public DAFTreeIterator() {
            stack = new Stack<>();
            DAFNode runner = root;
            while (runner != null) {
                stack.push(runner);
                try {
                    runner = runner.left;
                } catch (NullPointerException e) {
                    break;
                }
            }
        }

        /**
         * Checks if the iterator has next element.
         * 
         * @return true if there is a next, false otherwise
         */
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /**
         * Returns the next node of the iterator.
         * 
         * @return next node
         * @throws NoSuchElementException if the iterator reaches the end of traversal
         */
        public DAFNode<K, D> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            DAFNode<K, D> dup = stack.peek();
            DAFNode<K, D> top = stack.pop();
            DAFNode<K, D> right = top.right;

            while (right != null) { // as long as one of the current left diagonal node has right
                stack.push(right); // push them all
                right = right.left;
            }
            if (dup != null && !stack.contains(top.dup)) {
                while (dup.dup != null) { // go to the bottom of the dup chain
                    dup = dup.dup;
                }
                while (!dup.data.equals(top.data)) { // until we reach the top
                    stack.push(dup); // push to the stack from the bottom up
                    dup = dup.par;
                }
            }
            return top;
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
