import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable<E>> {
    private BSTNode<E> root; // root of overall tree
    private int numElements;

    // post: constructs an empty search tree
    public BinarySearchTree() {
        root = null;
        numElements = 0;
    }

    // post: value added to tree so as to preserve binary search tree
    public void add(E value) {
        root = add(root, value);
    }


    public boolean isEmpty() {
        return numElements == 0;

    }

    public Iterator<E> iterator() {
        BinarySearchTree.Iterator myIterator = new BinarySearchTree.Iterator(root);
        return myIterator;
    }

    // post: value added to tree so as to preserve binary search tree
    private BSTNode<E> add(BSTNode<E> node, E value) {
        if (node == null) {
            node = new BSTNode<E>(value);
            numElements++;
        } else if (node.data.compareTo(value) > 0) {
            node.left = add(node.left, value);
        } else if (node.data.compareTo(value) < 0) {
            node.right = add(node.right, value);
        }
        return node;
    }


    public void remove(E value) {
        root = remove(root, value);
    }

    public E[] toArray() {

        List<E> aList = new ArrayList<>();

        toArray(root, aList);
        E[] tempArray = (E[]) new Comparable[numElements];
        return aList.toArray(tempArray);

    }

    private BSTNode<E> remove(BSTNode<E> node, E value) {

        if (node == null) {
            return null;
        } else if (node.data.compareTo(value) > 0) {
            node.left = remove(node.left, value);
        } else if (node.data.compareTo(value) < 0) {
            node.right = remove(node.right, value);
        } else {
            if (node.right == null) {
                numElements--;
                return node.left;
            } else if (node.left == null) {
                numElements--;
                return node.right;
            } else {
                node.data = getMax(node.left);
                node.left = remove(node.left, node.data);

            }
        }

        return node;
    }


    private E getMax(BSTNode<E> node) {
        E tempVar = null;

        if (node.right != null) {
            tempVar = getMax(node.right);

        } else {
            tempVar = node.data;
        }

        return tempVar;
    }


    // post: returns true if tree contains value, returns false otherwise
    public boolean contains(E value) {
        return contains(root, value);
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

    private void toArray(BSTNode<E> node, List<E> aList) {


        if (node == null) {
            return;
        }

        toArray(node.left, aList);
        aList.add(node.data);
        toArray(node.right, aList);

    }

    public void clear() {
        root = null;
        numElements = 0;
    }

    public int size() {
        return numElements;
    }

    public static class Iterator<E> {

        private BSTNode<E> topnode, tempnode;
        private Stack<BSTNode<E>> stack;


        public Iterator(BSTNode<E> node) {
            stack = new Stack();
            topnode = node;
            insertValue(node);

        }

        private void inOrder(BSTNode<E> node) {
            if (node == null) {
                return;
            } else {

                inOrder(node.right);
                stack.push(node);
                inOrder(node.left);
            }
        }

        private void insertValue(BSTNode<E> node) {

            if (node == null) {
                return;
            }
            insertValue(node.right);
            stack.push(node);
            insertValue(node.left);
        }


        public boolean hasNext() {
            if (stack == null || stack.empty()) {
                return false;
            }

            return true;
        }

        public E next() {

            if (!stack.empty())
                return (E) stack.pop().data;
            else
                return null;

        }

    }

    private static class BSTNode<E> {
        public E data;
        public BSTNode<E> left;
        public BSTNode<E> right;

        public BSTNode(E data) {
            this(data, null, null);
        }

        public BSTNode(E data, BSTNode<E> left, BSTNode<E> right) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }


}
