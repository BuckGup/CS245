import java.util.LinkedList;

public class DoublyLinkedList<E> {

    private Node<E> first;

    private int size;



    public void add(E value) {





        size++;
    }






    public E get(int index) {
        E tempVar;
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        } else {
            tempVar = first[size - 1];
        }
        return tempVar;
    }


    public boolean isEmpty() {

        if (size == 0) {
            return true;
        } else {
            return false;
        }
    }


    private class Node {
        private E data;
        private Node next;
        private Node prev;


        public Node(E data, Node next, Node prev) {


        }

    }




    public int size(){


        return size;
    }


}
