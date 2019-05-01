import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class Lab6Tests {

	private DoublyLinkedList<Integer> dll;
	private Field first;
	private Field size;
	private Field data;
	private Field next;
	private Field prev;

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
				assertTrue(equalLists(list), "add(E) is not working correctly (element order incorrect)");
			}
		} catch (Exception e) {
			fail("add(E) method is not working correctly");
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
