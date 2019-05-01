import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class SortedLinkedListTests {

    private SortedLinkedList<Integer> sll;
    private Field first;
    private Field size;
    private Field data;
    private Field next;
    private Class<?> nodeClass;
    private Constructor<?> nodeConstructor;

    @BeforeEach
    public void setUp() throws Exception {
        sll = new SortedLinkedList<Integer>();
        first = SortedLinkedList.class.getDeclaredField("first");
        first.setAccessible(true);
        size = SortedLinkedList.class.getDeclaredField("size");
        size.setAccessible(true);

        nodeClass = Class.forName("SortedLinkedList$Node");
        data = nodeClass.getDeclaredField("data");
        data.setAccessible(true);
        next = nodeClass.getDeclaredField("next");
        next.setAccessible(true);
        nodeConstructor = nodeClass.getDeclaredConstructors()[0];
    }

    @Test
    public void testFields() {
        assertEquals(2, SortedLinkedList.class.getDeclaredFields().length, "SortedLinkedList should only have \"first\" and \"size\" fields");
    }

    @Test
    public void testConstructor() {
        try {
            assertNull(first.get(sll), "SortedLinkedList constructor is not working correctly (check first usage)");
            assertEquals(0, size.get(sll), "SortedLinkedList constructor is not working correctly (check size usage)");
        } catch (Exception e) {
            fail("SortedLinkedList constructor is not working correctly");
        }
    }

    @Test
    public void testAdd() {
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            Random rand = new Random();
            int r;
            for (int i = 0; i < 10; ++i) {
                r = rand.nextInt(10);
                sll.add(r);
                assertEquals(i + 1, size.get(sll), "add(E) (or its private helper method) is not working correctly (check size usage)");
                list.add(r);
                Collections.sort(list);
                assertTrue(equalLists(list), "add(E) (or its private helper method) is not working correctly (check element order)");
            }
            try {
                //recursive call test
                Method addHelper = SortedLinkedList.class.getDeclaredMethod("add", Comparable.class, nodeClass);
                addHelper.setAccessible(true);
                for (int i = 0; i < 10; ++i) {
                    r = rand.nextInt(10) + list.get(0) + 1;
                    addHelper.invoke(sll, r, first.get(sll));
                    list.add(r);
                    Collections.sort(list);
                    size.set(sll, list.size());
                    assertTrue(equalLists(list), "You don't appear to be calling add(E)'s private helper method correctly");
                }
            } catch (NoSuchMethodException e) {
                fail("No add(E) private helper method found");
            }
        } catch (Exception e) {
            fail("add(E) method is not working correctly");
        }
    }

    @Test
    public void testIsEmpty() {
        try {
            assertEquals(true, sll.isEmpty(), "isEmpty is not working correctly (not returning true when empty)");
            size.set(sll, 1);
            assertEquals(false, sll.isEmpty(), "isEmpty is not working correctly (not returning false when non-empty)");
        } catch (Exception e) {
            fail("isEmpty is not working correctly");
        }
    }

    @Test
    public void testSize() {
        try {
            assertEquals(0, sll.size(), "size method is not working correctly (check when empty)");
            size.set(sll, 1);
            assertEquals(1, sll.size(), "size method is not working correctly (check when non-empty)");
        } catch (Exception e) {
            fail("size method is not working correctly");
        }
    }

    @Test
    public void testRemove() {
        try {
            ArrayList<Integer> list = generateLists();
            Random rand = new Random();
            int randIndex;
            //ensure early remove first
            sll.remove(0);
            list.remove(0);
            assertEquals(list.size(), size.get(sll), "remove is not working correctly (check size usage)");
            assertTrue(equalLists(list), "remove is not working correctly (check element order)");

            while (!list.isEmpty()) {
                randIndex = rand.nextInt(list.size());
                sll.remove(randIndex);
                list.remove(randIndex);
                assertEquals(list.size(), size.get(sll), "remove is not working correctly (check size usage)");
                assertTrue(equalLists(list), "remove is not working correctly (check element order)");
            }
            try {
                //recursive call test
                Method removeHelper = SortedLinkedList.class.getDeclaredMethod("remove", int.class, int.class, nodeClass);
                removeHelper.setAccessible(true);
                list = generateLists();
                Collections.sort(list);
                while (list.size() > 1) {
                    randIndex = rand.nextInt(list.size() - 1) + 1;
                    removeHelper.invoke(sll, randIndex, 0, first.get(sll));
                    list.remove(randIndex);
                    size.set(sll, list.size());
                    assertTrue(equalLists(list), "You don't appear to be calling remove's private helper method correctly");
                }
            } catch (NoSuchMethodException e) {
                fail("No remove private helper method found");
            }
        } catch (Exception e) {
            fail("remove is not working correctly");
        }
    }

    @Test
    public void testRemoveIndexTooBig() {
        Executable statement = () -> sll.remove(1);
        assertThrows(IndexOutOfBoundsException.class, statement, "remove is not throwing an exception correctly");
    }

    @Test
    public void testRemoveIndexTooSmall() {
        Executable statement = () -> sll.remove(-1);
        assertThrows(IndexOutOfBoundsException.class, statement, "remove is not throwing an exception correctly");
    }

    @Test
    public void testGet() {
        try {
            ArrayList<Integer> list = generateLists();

            for (int i = 0; i < list.size(); ++i) {
                assertEquals(list.get(i), sll.get(i), "get is not working correctly (wrong element returned)");
                assertEquals(list.size(), size.get(sll), "get is not working correctly (size inappropriately changed)");
                assertTrue(equalLists(list), "get is not working correctly (list inappropriately changed)");
            }
            try {
                //recursive call test
                Method getHelper = SortedLinkedList.class.getDeclaredMethod("get", int.class, int.class, nodeClass);
                getHelper.setAccessible(true);
                list = generateLists();
                size.set(sll, list.size());
                for (int i = 0; i < 5; i++) {
                    getHelper.invoke(sll, i, 0, first.get(sll));
                    assertEquals(list.get(i), sll.get(i), "You don't appear to be calling get's private helper method correctly");
                    assertTrue(equalLists(list), "You don't appear to be calling get's private helper method correctly");
                }
            } catch (NoSuchMethodException e) {
                fail("No get private helper method found");
            }
        } catch (Exception e) {
            fail("get is not working correctly" + e.toString());
        }
    }

    @Test
    public void testGetIndexTooBig() {
        Executable statement = () -> sll.get(1);
        assertThrows(IndexOutOfBoundsException.class, statement, "get is not throwing an exception correctly");
    }

    @Test
    public void testGetIndexTooSmall() {
        Executable statement = () -> sll.get(-1);
        assertThrows(IndexOutOfBoundsException.class, statement, "get is not throwing an exception correctly");
    }

    @Test
    public void testIndexOf() {
        try {
            ArrayList<Integer> list = generateLists();
            Random rand = new Random();
            int randIndex;
            int randValue;
            for (int i = 0; i < 100; ++i) {
                randIndex = rand.nextInt(list.size());
                randValue = list.get(randIndex);
                assertEquals(list.indexOf(randValue), sll.indexOf(randValue), "indexOf is not working correctly (wrong index returned)");
                assertEquals(list.size(), size.get(sll), "indexOf is not working correctly (size inappropriately changed)");
                assertTrue(equalLists(list), "indexOf is not working correctly (list inappropriately changed)");
            }
            assertEquals(-1, sll.indexOf(100), "indexOf is not working correctly (not handling unfound case correctly)");
            try {
                //recursive call test
                Method indexOfHelper = SortedLinkedList.class.getDeclaredMethod("indexOf", Comparable.class, int.class, nodeClass);
                indexOfHelper.setAccessible(true);
                for (int i = 0; i < 5; ++i) {
                    randIndex = rand.nextInt(list.size());
                    randValue = list.get(randIndex);
                    int index = (int) indexOfHelper.invoke(sll, randValue, 0, first.get(sll));
                    assertEquals(list.indexOf(randValue), index, "You don't appear to be calling get's private helper method correctly");
                }
            } catch (NoSuchMethodException e) {
                fail("No add(E) private helper method found");
            }
        } catch (Exception e) {
            fail("indexOf is not working correctly");
        }
    }

    @Test
    public void testToString() {
        try {
            ArrayList<Integer> list = new ArrayList<Integer>();
            assertEquals(list.toString(), sll.toString(), "toString is not working correctly (check with empty list)");
            Object n = nodeConstructor.newInstance(sll, 37, null);
            next.set(n, null);
            first.set(sll, n);
            size.set(sll, 1);
            list.add(37);
            assertEquals(list.toString(), sll.toString(), "toString is not working correctly (check with single element list)");
            list = generateLists();
            assertEquals(list.toString(), sll.toString(), "toString is not working correctly (check with multiple element list)");
            try {
                //recursive call test
                Method toStringHelper = SortedLinkedList.class.getDeclaredMethod("toString", nodeClass);
                toStringHelper.setAccessible(true);
                assertEquals(list.toString().substring(1, list.toString().length() - 1), toStringHelper.invoke(sll, first.get(sll)), "You don't appear to be calling toString's private helper method correctly");
            } catch (NoSuchMethodException e) {
                fail("No add(E) private helper method found");
            }
        } catch (Exception e) {
            fail("toString is not working correctly");
        }
    }

    private ArrayList<Integer> generateLists() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        try {
            Random rand = new Random();
            int randSize = rand.nextInt(10) + 10;
            Object p = nodeConstructor.newInstance(sll, 0, null);
            first.set(sll, p);
            list.add(0);
            Object n;
            for (int i = 1; i <= randSize; ++i) {
                //n is the most recently created node
                n = nodeConstructor.newInstance(sll, i, null);
                next.set(p, n);
                p = n;
                list.add(i);
            }
            size.set(sll, randSize + 1);
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    private boolean equalLists(ArrayList<Integer> a) {
        try {
            if ((int) size.get(sll) != a.size()) {
                return false;
            }
            Object n = first.get(sll);
            int i = 0;
            while (i < a.size()) {
                if (!a.get(i).equals(data.get(n))) {
                    return false;
                }
                n = next.get(n);
                i++;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
