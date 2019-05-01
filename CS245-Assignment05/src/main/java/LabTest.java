public class SortedLinkedList<E extends Comparable<E>>
{

    private Node first;
    private int size;


    public void add(E value) {

        if (size == 0) {
            Node newNode1 = new Node(value, null, null);
            first = newNode1;
            first.prev = newNode1;
            first.next = newNode1;
        } else {
            Node newNode2 = new Node(value, first, first.prev);
            first.prev = newNode2;
            first.prev.prev.next = newNode2;
        }
        size++;
    }


    public void add(int index, E value) {

        Node n = first;
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (size == 0) {
            Node newNode1 = new Node(value, null, null);
            first = newNode1;
            first.prev = newNode1;
            first.next = newNode1;
        } else {
            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            Node newNode2 = new Node(value, n, n.prev);

            n.prev = newNode2;
            n.prev.prev.next = newNode2;

            if (index == 0) {
                first = newNode2;
            }
        }
        size++;
    }


    public int indexOf(E value) {
        Node n = first;

        for (int i = 0; i < size; i++) {

            if (value.equals(n.data)) {
                return i;
            }
            n = n.next;

        }
        return -1;
    }


    public void remove(int index) {

        Node n = first;

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
        //single element
        if (size == 1) {
            first.next = null;
            first.prev = null;
        }
        //first element
        else if (index == 0) {
            first.prev.next = first.next;
            first.next.prev = first.prev;
            n = first;
            first = first.next;

            n.next = null;
            n.prev = null;
        }

        //anything else
        else {

            for (int i = 0; i < index; i++) {
                n = n.next;
            }
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n.next = null;
            n.prev = null;
        }
        size--;

    }


    public E get(int index) {
        Node n = first;
        if (size == 0 || index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {

            for (int i = 0; i < index; i++) {

                n = n.next;
            }
        }
        return n.data;
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
                tempString = (n.data.toString());
            } else {
                for (int i = 0; i < size; i++) {

                    if ((i + 1) == size) {
                        tempString += (n.data.toString());
                    } else {
                        tempString += (n.data.toString() + ", ");
                    }
                    n = n.next;
                }
            }
        }
        return ("[" + tempString + "]");
    }

    private class Node {
        private E data;
        private Node next;
        private Node prev;


        public Node(E data, Node next, Node prev) {
            this.data = data;
            this.next = next;
            this.prev = prev;
        }
    }

}