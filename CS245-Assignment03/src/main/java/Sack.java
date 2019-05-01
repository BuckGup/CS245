import java.util.Arrays;

public class Sack<E> {


    public final static int DEFAULT_CAPACITY = 10;
    private int size;
    private E[] elementData;


    public Sack() {

        elementData = (E[]) (new Object[DEFAULT_CAPACITY]);

    }

    public Sack(int capacity) {

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


    public void add(E item) {
        ensureCapacity(size + 1);

        elementData[size] = item;

        size++;
    }


    public E grab() {
        int randomIndex = 0;
        E tempVar;

        if (size == 0) {
            return null;
        } else {

            randomIndex = (int) Math.random() * size;

            tempVar = elementData[randomIndex];
            remove(randomIndex);


        }

        return tempVar;
    }

    public E[] dump() {

        E[] secondArrayData;

        secondArrayData = (E[]) Arrays.copyOf(elementData, size);

        for (int i = 0; i < elementData.length; i++) {

            elementData[i] = null;
        }

        size = 0;
        return secondArrayData;


    }

    private void ensureCapacity(int capacity) {


        //use size not iterator through
        if (elementData.length < capacity) {
            capacity = elementData.length * 2 + 1;
            elementData = (E[]) Arrays.copyOf(elementData, capacity);

        }


    }

    private void remove(int index) {
        elementData[index] = elementData[size - 1];
        elementData[size - 1] = null;
        size--;
    }


}