import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.prefs.BackingStoreException;

public class BinarySearchTree<E extends Comparable<E>> {
    private BSTNode<E> root; // root of overall tree
    private int numElements;
    private BSTNode first;

    // post: constructs an empty search tree
    public BinarySearchTree() {
        this.root = null;
        this.numElements = 0;
    }

    public Iterator iterator() {
        return new BinarySearchTree.Iterator();
    }

    // post: value added to tree so as to preserve binary search tree
    public void add(E value) {
        this.root = add(root, value, null, null);

    }

    // post: value added to tree so as to preserve binary search tree
    private BSTNode<E> add(BSTNode<E> node, E value, BSTNode<E> parent, BSTNode<E> prev) {
        BSTNode<E> tempNode;
        if (node == null) {
            node = new BSTNode<E>(value);
            node.parent = parent;
            this.numElements++;
            if (root == null) {
                root = node;
                first = node;
            } else {
                assignFirst();
            }
            tempNode = getPrevNode(node);
            if (tempNode != null) {
                tempNode.next = node;
            }
            else{
                node.next = null;
            }

        } else if (node.data.compareTo(value) > 0) {
            node.left = add(node.left, value, node, getPrevNode(node));

        } else if (node.data.compareTo(value) < 0) {
            node.right = add(node.right, value, node, node.parent);
        }
        return node;
    }

    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(this.root, value);
    }

    // post: returns true if given tree contains value, returns false otherwise
    private boolean contains(BSTNode<E> node, E value) {
        if (node == null) {
            return false;
        } else {
            int compare = value.compareTo(node.data);
            if (compare == 0) {
                return true;
            } else if (compare < 0) {
                return contains(node.left, value);
            } else {   // compare > 0
                return contains(node.right, value);
            }
        }
    }

    public void remove(E value) {
        this.root = remove(root, value);
    }

    private BSTNode<E> remove(BSTNode<E> node, E value) {
        BSTNode<E> tempNode;
        if (node == null) {
            return null;
        } else if (node.data.compareTo(value) > 0) {
            node.left = remove(node.left, value);
        } else if (node.data.compareTo(value) < 0) {
            node.right = remove(node.right, value);
        } else {  // node.data == value; remove this node
            if (node.right == null) {
                numElements--;
                if (node.left != null) {
                    node.left.parent = node.parent;
                }
                tempNode = getPrevNode(node);
                tempNode.next = node.next;
                return node.left;    // no R child; replace w/ L
            } else if (node.left == null) {
                numElements--;
                if (node.right != null) {
                    node.right.parent = node.parent;
                }
                tempNode = getPrevNode(node);
                tempNode.next = node.next;
                return node.right;   // no L child; replace w/ R
            } else {
                // both children; replace w/ max from L
                node.data = getMax(node.left);
                node.left = remove(node.left, node.data);
            }
        }
        return node;
    }

    private E getMax(BSTNode<E> node) {
        if (node.right == null) {
            return node.data;
        } else {
            return getMax(node.right);
        }
    }

    public void clear() {
        root = null;
        numElements = 0;
        first = null;
    }

    public boolean isEmpty() {
        return this.numElements == 0;
    }

    public int size() {
        return this.numElements;
    }

    private BSTNode<E> findFirst(BSTNode<E> node) {
        if (node.left != null) {
            node.left = findFirst(node.left);
        }
        first = node;

        return node;
    }


    public void assignFirst() {
        findFirst(root);

    }

    @SuppressWarnings("unchecked")
    public E[] toArray() {
        ArrayList<E> aList = new ArrayList<E>();
        E[] arr = (E[]) new Comparable[this.numElements];
        toArray(this.root, aList);
        return aList.toArray(arr);
    }

    private void toArray(BSTNode<E> node, List<E> aList) {
        if (node != null) {
            toArray(node.left, aList);
            aList.add(node.data);
            toArray(node.right, aList);
        }
    }


    private BSTNode<E> getMaxLeft(BSTNode<E> node) {
        if (node.right == null) {
            return node;
        } else {
            return getMaxLeft(node.right);
        }

    }

    private BSTNode<E> getPrevNode(BSTNode<E> node) {


        if (node.left != null) {
            return getMaxLeft(node.left);
        } else {
            if (node.parent != null) {
                if (node.right != null) {
                    return node.parent;
                } else if (node.left != null) {

                    while (node.parent.right != node && node.parent != null) {
                        node = node.parent;
                    }

                    return node.parent;
                } else {
                    if (node == root) {
                        return null;

                    }
                }

            }
        }


        return node;
    }

    public void balance() {


    }

    private BSTNode<E> buildTreeUtil(E[] values, int start, int end, BSTNode<E> parent) {

        return null;
    }

    private static class BSTNode<E> {
        public E data;
        public BSTNode<E> left;
        public BSTNode<E> right;
        public BSTNode<E> parent;
        public BSTNode<E> next;

        public BSTNode(E data) {
            this(data, null, null, null, null);
        }

        public BSTNode(E data, BSTNode<E> left, BSTNode<E> right, BSTNode<E> parent, BSTNode<E> next) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.parent = parent;
            this.next = next;
        }
    }

    public class Iterator {
        private BSTNode<E> currentNode;

        public Iterator() {
            currentNode = first;
        }

        public boolean hasNext() {
            return currentNode != null;
        }

        public E next() {
            E value = currentNode.data;
            currentNode = currentNode.next;
            return value;
        }
    }
}
