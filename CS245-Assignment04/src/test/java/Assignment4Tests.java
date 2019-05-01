import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Random;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

public class Assignment4Tests {
    private Stack<Integer> s;
    private Field elementData;
    private Field size;
    private Method ensureCapacity;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() throws Exception {
        s = new Stack<Integer>();
        elementData = Stack.class.getDeclaredField("elementData");
        elementData.setAccessible(true);
        size = Stack.class.getDeclaredField("size");
        size.setAccessible(true);
        ensureCapacity = Stack.class.getDeclaredMethod("ensureCapacity", int.class);
        ensureCapacity.setAccessible(true);
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testDefaultConstructor() {
        try {
            assertEquals(0, size.get(s), "Stack() constructor is not working correctly (check size usage)");
            assertEquals(Stack.DEFAULT_CAPACITY,  ((Object[]) elementData.get(s)).length, "Stack() constructor is not working correctly (check array capacity)");
        }  catch (Exception e) {
            fail("Stack() constructor and/or instance variables not found");
        }
    }

    @Test
    public void testConstructor() {
        try {
            Random rand = new Random();
            int capacity = rand.nextInt(50);
            s= new Stack<Integer>(capacity);
            assertEquals(0, size.get(s), "Stack(int) constructor is not working correctly (check size usage)");
            assertEquals(capacity, ((Object[]) elementData.get(s)).length, "Stack(int) constructor is not working correctly (check array capacity)");
        }  catch (Exception e) {
            fail("Stack(int) constructor and/or instance variables not found");
        }
    }

    @Test
    public void testConstructorInvalidArgument() {
        Random rand = new Random();
        int capacity = rand.nextInt(49) + 1;
        Executable statement = () -> new Stack<Integer>(-capacity);
        assertThrows(IllegalArgumentException.class, statement, "Stack(int) constructor is not working correctly (check invalid argument)");
    }

    @Test
    public void testIsEmpty() {
        assertEquals(true, s.isEmpty(), "isEmpty is not working correctly (not returning true when appropriate)");
        try {
            size.set(s, 1);
            assertEquals(false, s.isEmpty(), "isEmpty is not working correctly (not returning false when appropriate)");
        } catch (Exception e) {
            fail("isEmpty is not working correctly");
        }
    }

    @Test
    public void testPush() {
        try {
            Random rand = new Random();
            int numElements = rand.nextInt(9) + 11;
            ArrayList<Integer> expectedElements = new ArrayList<Integer>();
            int randElement;
            for(int i=0; i<numElements; ++i) {
                randElement = rand.nextInt(50) + 1;
                expectedElements.add(randElement);
                s.push(randElement);
                assertEquals(i+1, size.get(s), "push is not working correctly (check size usage)");
            }
            for(int i=0; i<numElements; ++i) {
                assertEquals(expectedElements.get(i), ((Object[])elementData.get(s))[i], "push is not working correctly (check element location)");
            }
        } catch (Exception e) {
            fail("push is not working correctly");
        }
    }

    @Test
    public void testPop() {
        Random rand = new Random();
        int numElements = rand.nextInt(9) + 1;
        Integer[] setElementData = new Integer[10];
        ArrayList<Integer> expectedElements = new ArrayList<Integer>();
        int randElement;
        for(int i=0; i<numElements; ++i) {
            randElement = rand.nextInt(50) + 1;
            setElementData[i] = randElement;
            expectedElements.add(randElement);
        }
        try {
            elementData.set(s, setElementData);
            size.set(s, numElements);
            for(int i=0; i<numElements; ++i) {
                assertEquals(expectedElements.get(numElements-i-1), s.pop(), "pop is not working correctly (wrong element returned)");
                assertEquals(numElements-i-1, size.get(s), "pop is not working correctly (check size usage)");
            }
        } catch (Exception e) {
            fail("pop is not working correctly");
        }
    }

    @Test
    public void testPopEmptyStack() {
        Executable statement = () -> s.pop();
        assertThrows(EmptyStackException.class, statement, "pop is not working correctly (check empty stack behavior)");
    }

    @Test
    public void testPeek() {
        Random rand = new Random();
        int numElements = rand.nextInt(8) + 2;
        Integer[] setElementData = new Integer[10];
        ArrayList<Integer> expectedElements = new ArrayList<Integer>();
        int randElement;
        for(int i=0; i<numElements; ++i) {
            randElement = rand.nextInt(50) + 1;
            if(!expectedElements.contains(randElement)) {
                setElementData[i] = randElement;
                expectedElements.add(randElement);
            } else {
                --i;
            }
        }
        try {
            elementData.set(s, setElementData);
            size.set(s, numElements);
            assertEquals(setElementData[numElements-1], s.peek(), "peek is not working correctly (wrong element returned)");
            assertEquals(numElements, size.get(s), "peek is not working correctly (check size usage)");
            assertEquals(setElementData[numElements-1], s.peek(), "peek is not working correctly (wrong element returned)");
            assertEquals(numElements, size.get(s), "peek is not working correctly (check size usage)");
        } catch (Exception e) {
            fail("peek is not working correctly");
        }
    }

    @Test
    public void testPeekEmptyStack() {
        Executable statement = () -> s.peek();
        assertThrows(EmptyStackException.class, statement, "peek is not working correctly (check empty stack behavior)");
    }

    @Test
    public void testEnsureCapacity() {
        assertEquals(2, ensureCapacity.getModifiers(), "ensureCapacity does not have the correct modifiers");
        try {
            for(int i=0; i<=10; ++i) {
                ensureCapacity.invoke(s, i);
                assertEquals(10, ((Object[])elementData.get(s)).length, "ensureCapacity is not working correctly (default capacity changed inappropriately)");
            }
            ensureCapacity.invoke(s, 11);
            assertEquals(21, ((Object[])elementData.get(s)).length, "ensureCapacity is not working correctly (capacity changed incorrectly)");

            Random rand = new Random();
            int capacity = rand.nextInt(100)+1;
            s = new Stack<Integer>(capacity);
            for(int i=0; i<=capacity; ++i) {
                ensureCapacity.invoke(s, i);
                assertEquals(capacity, ((Object[])elementData.get(s)).length, "ensureCapacity is not working correctly (initial capacity changed inappropriately)");
            }
            ensureCapacity.invoke(s, capacity+1);
            assertEquals(capacity*2+1, ((Object[])elementData.get(s)).length, "ensureCapacity is not working correctly (capacity changed incorrectly)");
        } catch (Exception e) {
            fail("ensureCapacity is not working correctly");
        }
    }

    @Test
    void testMain() {
        setInput("back\nhttp://www.uwec.edu\nback\nhttp://www.amazon.com\nhttp://www.google.com\nback\nback\nquit\n");
        BrowsingHistory.main(null);
        String mainOutput = outContent.toString();
        Scanner driverOut = new Scanner(mainOutput);

        String outputLine = getNextOutputLine(driverOut);
        assertEquals("Enter a URL or \"quit\":", outputLine.substring(0, outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (initial prompt problem)");
        assertEquals("No URL to go back to", outputLine.substring(outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (can't go back issue)");

        outputLine = getNextOutputLine(driverOut);
        assertEquals("Enter a URL or \"quit\":", outputLine.substring(0, outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (prompt problem)");
        assertEquals("Current URL: http://www.uwec.edu", outputLine.substring(outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (current url problem)");

        outputLine = getNextOutputLine(driverOut);
        assertEquals("Enter a URL or \"quit\":", outputLine.substring(0, outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (prompt problem)");
        assertEquals("No URL to go back to", outputLine.substring(outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (can't go back issue)");

        outputLine = getNextOutputLine(driverOut);
        assertEquals("Current URL: http://www.uwec.edu", outputLine.trim(), "BrowsingHistory doesn't run as expected (current url problem)");

        outputLine = getNextOutputLine(driverOut);
        assertEquals("Enter a URL or \"quit\":", outputLine.substring(0, outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (prompt problem)");
        assertEquals("Current URL: http://www.amazon.com", outputLine.substring(outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (current url problem)");

        outputLine = getNextOutputLine(driverOut);
        assertEquals("Enter a URL, \"back\", or \"quit\":", outputLine.substring(0, outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (prompt problem)");
        assertEquals("Current URL: http://www.google.com", outputLine.substring(outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (current url problem)");

        outputLine = getNextOutputLine(driverOut);
        assertEquals("Enter a URL, \"back\", or \"quit\":", outputLine.substring(0, outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (prompt problem)");
        assertEquals("Current URL: http://www.amazon.com", outputLine.substring(outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (current url problem)");

        outputLine = getNextOutputLine(driverOut);
        assertEquals("Enter a URL, \"back\", or \"quit\":", outputLine.substring(0, outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (prompt problem)");
        assertEquals("Current URL: http://www.uwec.edu", outputLine.substring(outputLine.indexOf(":")+1).trim(), "BrowsingHistory doesn't run as expected (current url problem)");

        outputLine = getNextOutputLine(driverOut);
        assertEquals("Enter a URL or \"quit\":", outputLine.trim(), "BrowsingHistory doesn't run as expected (prompt problem)");

        assertFalse(driverOut.hasNext(), "BrowsingHistory doesn't run as expected (quit problem)");

        driverOut.close();
    }

    private String getNextOutputLine(Scanner driverOut) {
        String outputLine = driverOut.nextLine().trim();
        while(outputLine.isEmpty()) {
            outputLine = driverOut.nextLine().trim();
        }
        return outputLine;
    }

    private void setInput(String input) {
        ByteArrayInputStream inContent = new ByteArrayInputStream(input.getBytes());
        System.setIn(inContent);
    }
}
