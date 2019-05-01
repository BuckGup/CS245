public class SortedLinkedList<E extends Comparable<E>> {

    private Node first;
    private int size;


    public void add(E value) {

        if (size == 0) {
            Node newNode1 = new Node(value, null);
            first = newNode1;

        } else if (value.compareTo(first.data) < 0) {
            Node newNode2 = new Node(value, first);
            first = newNode2;
        } else {
            add(value, first);
        }
        size++;
    }


    public int indexOf(E value) {
        Node n = first;
        if (size == 0) {
            return -1;
        } else {
            return indexOf(value, 0, n);
        }
    }


    public void remove(int index) {
        Node n = first;

        if (size == 0 || index > size) {
            throw new IndexOutOfBoundsException();
        } else if (index == 0) {
            first = first.next;
        } else {
            remove(index, 0, n);
        }
        size--;
    }


    public E get(int index) {
        Node n = first;
        if (size == 0 || index >= size || index < 0 || first == null) {
            throw new IndexOutOfBoundsException();
        } else {
            return get(index, 0, n);
        }
    }


    public boolean isEmpty() {

        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }

    public int size() {
        return size;
    }

    public String toString() {
        String tempString = "";

        Node n = first;
        if (size == 0) {
            return "[]";

        } else {
            if (size == 1) {
                tempString = "" + n.data;
            } else {
                tempString = toString(first);

            }
        }
        return ("[" + tempString + "]");
    }


    private void add(E value, Node n) {

        if (n.next != null && (n.next.data.compareTo(value) < 0)) {
            add(value, n.next);

        } else {
            Node newNode4 = new Node(value, n.next);
            n.next = newNode4;
        }

    }

    private void remove(int index, int currentIndex, Node n) {

        if (index == (currentIndex + 1)) {
            n.next = n.next.next;
        } else {
            remove(index, ++currentIndex, n.next);
        }
    }


    private E get(int index, int currentIndex, Node n) {

        if (index == currentIndex) {
            return n.data;
        } else {
            return get(index, ++currentIndex, n.next);
        }
    }


    private int indexOf(E value, int currentIndex, Node n) {

        if (n == null) {
            return -1;
        } else if (value.compareTo(n.data) == 0) {
            return currentIndex;
        } else {
            return indexOf(value, ++currentIndex, n.next);
        }
    }

    private String toString(Node n) {
        String tempVar;
        if (n.next != null) {
           tempVar = n.data + ", ";
            return tempVar + toString(n.next);

        } else {
            return "" + n.data;



        }
    }


    private class Node {
        private E data;
        private Node next;


        public Node(E data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

}