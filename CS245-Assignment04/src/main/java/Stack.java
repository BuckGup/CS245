import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack<E> {

    public final static int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] elementData;


    public Stack() {
        elementData = (E[]) (new Object[DEFAULT_CAPACITY]);
    }

    public Stack(int capacity) {

        if (capacity > 0) {
            elementData = (E[]) (new Object[capacity]);

        } else {
            throw new IllegalArgumentException("Capacity must be positive");
        }
    }

    public boolean isEmpty() {

        if (size == 0) {
            return true;
        } else {
            return false;
        }

    }


    public void push(E item) {

        ensureCapacity(size + 1);

        elementData[size] = item;

        size++;
    }


    public E peek() {
        E tempVar;
        if (size == 0) {
            throw new EmptyStackException();
        } else {
            tempVar = elementData[size - 1];
        }
        return tempVar;

    }


    private void ensureCapacity(int capacity) {

        if (elementData.length < capacity) {
            capacity = elementData.length * 2 + 1;
            elementData = (E[]) Arrays.copyOf(elementData, capacity);

        }

    }

    public E pop() throws EmptyStackException {
        E tempVar;

        if (size == 0) {
            throw new EmptyStackException();
        } else {
            tempVar = elementData[size - 1];
            elementData[size - 1] = null;
            size--;
        }
        return tempVar;
    }


}

