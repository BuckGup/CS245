import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class DoublyLinkedListTests {

	private DoublyLinkedList<Integer> dll;
	private Field first;
	private Field size;
	private Field data;
	private Field next;
	private Field prev;
	private Constructor<?> nodeConstructor;

	@BeforeEach
	public void setUp() throws Exception {
		dll = new DoublyLinkedList<Integer>();
		first = DoublyLinkedList.class.getDeclaredField("first");
		first.setAccessible(true);
		size = DoublyLinkedList.class.getDeclaredField("size");
		size.setAccessible(true);

		Class<?> nodeClass = Class.forName("DoublyLinkedList$Node");
		data = nodeClass.getDeclaredField("data");
		data.setAccessible(true);
		next = nodeClass.getDeclaredField("next");
		next.setAccessible(true);
		prev = nodeClass.getDeclaredField("prev");
		prev.setAccessible(true);
		nodeConstructor = nodeClass.getDeclaredConstructors()[0];
	}

	@Test
	public void testFields() {
		assertEquals(2, DoublyLinkedList.class.getDeclaredFields().length, "DoublyLinkedList should only have \"first\" and \"size\" fields");
	}

	@Test
	public void testConstructor() {
		try {
			assertNull(first.get(dll), "DoublyLinkedList constructor is not working correctly (check first usage)");
			assertEquals(0, size.get(dll), "DoublyLinkedList constructor is not working correctly (check size usage)");
		} catch (Exception e) {
			fail("DoublyLinkedList constructor is not working correctly");
		}
	}

	@Test
	public void testAdd() {
		try {
			ArrayList<Integer> list = new ArrayList<Integer>();
			Random rand = new Random();
			int r;
			for(int i=0; i<10; ++i) {
				r = rand.nextInt(10);
				dll.add(r);
				assertEquals(i+1, size.get(dll), "add(E) is not working correctly (check size usage)");
				list.add(r);
				assertTrue(equalLists(list), "add(E) is not working correctly (check element order)");
			}
		} catch (Exception e) {
			fail("add(E) method is not working correctly");
		}
	}

	@Test
	public void testIsEmpty() {
		try {
			assertEquals(true, dll.isEmpty(), "isEmpty is not working correctly (not returning true when appropriate)");
			size.set(dll, 1);
			assertEquals(false, dll.isEmpty(), "isEmpty is not working correctly (not returning false when appropriate)");
		} catch (Exception e) {
			fail("isEmpty is not working correctly");
		}
	}

	@Test
	public void testSize() {
		try {
			assertEquals(0, dll.size(), "size method is not working correctly (not working correctly when empty)");
			size.set(dll, 1);
			assertEquals(1, dll.size(), "size method is not working correctly (not working correctly when non-empty)");
		} catch (Exception e) {
			fail("size method is not working correctly");
		}
	}

	@Test
	public void testInsert() {
		try {
			ArrayList<Integer> list = new ArrayList<Integer>();
			Random rand = new Random();
			int randValue;
			int randIndex;
			for(int i=0; i<10; ++i) {
				randValue = rand.nextInt(10);
				randIndex = rand.nextInt(list.size()+1);
				dll.add(randIndex, randValue);
				assertEquals(i+1, size.get(dll), "add(int, E) is not working correctly (check size usage)");
				list.add(randIndex, randValue);
				assertTrue(equalLists(list), "add(int, E) is not working correctly (check element order)");
			}
			//ensure insert at front
			randValue = rand.nextInt(10);
			dll.add(0, randValue);
			assertEquals(11, size.get(dll), "add(int, E) is not working correctly (check size usage)");
			list.add(0, randValue);
			assertTrue(equalLists(list), "add(int, E) is not working correctly (check element order)");
		} catch (Exception e) {
			fail("add(int, E) method is not working correctly");
		}
	}

	@Test
	public void testInsertIndexTooBig() {
		Executable statement = () -> dll.add(1, 100);
		assertThrows(IndexOutOfBoundsException.class, statement, "add(int, E) method is not throwing an exception correctly");
	}

	@Test
	public void testInsertIndexTooSmall() {
		Executable statement = () -> dll.add(-1, 100);
		assertThrows(IndexOutOfBoundsException.class, statement, "add(int, E) method is not throwing an exception correctly");
	}

	@Test
	public void testRemove() {
		try {
			ArrayList<Integer> list = generateLists();
			Random rand = new Random();
			int randIndex;
			//ensure early remove first
			dll.remove(0);
			list.remove(0);
			assertEquals(list.size(), size.get(dll), "remove is not working correctly (check size usage)");
			assertTrue(equalLists(list), "remove is not working correctly (check element order)");

			while(!list.isEmpty()) {
				randIndex = rand.nextInt(list.size());
				dll.remove(randIndex);
				list.remove(randIndex);
				assertEquals(list.size(), size.get(dll), "remove is not working correctly (check size usage)");
				assertTrue(equalLists(list), "remove is not working correctly (check element order)");
			}
		} catch(Exception e) {
			fail("remove is not working correctly");
		}
	}

	@Test
	public void testRemoveIndexTooBig() {
		Executable statement = () -> dll.remove(1);
		assertThrows(IndexOutOfBoundsException.class, statement, "remove is not throwing an exception correctly");
	}

	@Test
	public void testRemoveIndexTooSmall() {
		Executable statement = () -> dll.remove(-1);
		assertThrows(IndexOutOfBoundsException.class, statement, "remove is not throwing an exception correctly");
	}

	@Test
	public void testGet() {
		try {
			ArrayList<Integer> list = generateLists();

			for(int i=0; i<list.size(); ++i) {
				assertEquals(list.get(i), dll.get(i), "get is not working correctly (check returned element)");
				assertEquals(list.size(), size.get(dll), "get is not working correctly (size inappropriately)");
				assertTrue(equalLists(list), "get is not working correctly (list inappropriately modified)");
			}
		} catch(Exception e) {
			fail("get is not working correctly");
		}
	}

	@Test
	public void testGetIndexTooBig() {
		Executable statement = () -> dll.get(1);
		assertThrows(IndexOutOfBoundsException.class, statement, "get is not throwing an exception correctly");
	}

	@Test
	public void testGetIndexTooSmall() {
		Executable statement = () -> dll.get(-1);
		assertThrows(IndexOutOfBoundsException.class, statement, "get is not throwing an exception correctly");
	}

	@Test
	public void testIndexOf() {
		try {
			ArrayList<Integer> list = generateLists();
			Random rand = new Random();
			int randIndex;
			int randValue;
			for(int i=0; i<100; ++i) {
				randIndex = rand.nextInt(list.size());
				randValue = list.get(randIndex);
				assertEquals(list.indexOf(randValue), dll.indexOf(randValue), "indexOf is not working correctly (check returned index)");
				assertEquals(list.size(), size.get(dll), "indexOf is not working correctly (size inappriately changed)");
				assertTrue(equalLists(list), "indexOf is not working correctly (list inappropriately modified)");
			}
			assertEquals(-1, list.indexOf(100), "indexOf is not working correctly");
		} catch(Exception e) {
			fail("indexOf is not working correctly");
		}
	}

	@Test
	public void testToString() {
		try {
			ArrayList<Integer> list = new ArrayList<Integer>();
			assertEquals(list.toString(), dll.toString(), "toString is not working correctly (check with empty list)");
			Object n = nodeConstructor.newInstance(dll, 37, null, null);
			prev.set(n, n);
			next.set(n, n);
			first.set(dll, n);
			size.set(dll,  1);
			list.add(37);
			assertEquals(list.toString(),dll.toString(), "toString is not working correctly (check with single element)");
			list = generateLists();
			assertEquals(list.toString(), dll.toString(), "toString is not working correctly (check with multiple elements)");
		} catch(Exception e) {
			fail("toString is not working correctly");
		}
	}

	private ArrayList<Integer> generateLists() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		try {
			Random rand = new Random();
			int randValue = rand.nextInt(10);
			Object n = nodeConstructor.newInstance(dll, randValue, null, null);
			prev.set(n, n);
			next.set(n, n);
			Object f = n;
			Object n2;
			list.add(randValue);
			for(int i=0; i<9; ++i) {
				randValue = rand.nextInt(10);
				//n is the most recently created node
				n2 = nodeConstructor.newInstance(dll, randValue, f, n);
				next.set(n, n2);
				prev.set(f, n2);
				n = n2;
				list.add(randValue);
			}
			first.set(dll, f);
			size.set(dll,  10);
			return list;
		} catch(Exception e) {
			return null;
		}
	}

	private boolean equalLists(ArrayList<Integer> a) {
		try {
			if((int)size.get(dll) != a.size()) {
				return false;
			}
			Object n = first.get(dll);
			Object previous;
			int i=0;
			while(i<a.size()) {
				if(!a.get(i).equals(data.get(n))) {
					return false;
				}
				previous = n;
				n = next.get(n);
				if(!prev.get(n).equals(previous)) {
					return false;
				}
				i++;
			}
			if(n != first.get(dll)) {
				return false;
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
