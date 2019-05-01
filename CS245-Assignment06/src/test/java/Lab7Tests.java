import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class Lab7Tests {
	
	private SortedLinkedList<Integer> sll;
	private Field first;
	private Field size;
	private Field data;
	private Field next;
	private Class<?> nodeClass;

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
			for(int i=0; i<10; ++i) {
				r = rand.nextInt(10);
				sll.add(r);
				assertEquals(i+1, size.get(sll), "add(E) (or its private helper method) is not working correctly (check size usage)");
				list.add(r);
				Collections.sort(list);
				assertTrue(equalLists(list), "add(E) (or its private helper method) is not working correctly (check element order)");
			}
			try {
				//recursive call test
				Method addHelper = SortedLinkedList.class.getDeclaredMethod("add", Comparable.class, nodeClass);
				addHelper.setAccessible(true);
				for(int i=0; i<10; ++i) {
					r = rand.nextInt(10) + list.get(0) + 1;
					addHelper.invoke(sll, r, first.get(sll));
					list.add(r);
					Collections.sort(list);
					size.set(sll, list.size());
					assertTrue(equalLists(list), "You don't appear to be calling add(E)'s private helper method correctly");
				}
			} catch(NoSuchMethodException e) {
				fail("No add(E) private helper method found");
			}
		} catch (Exception e) {
			fail("add(E) method is not working correctly");
		}
	}
	
	private boolean equalLists(ArrayList<Integer> a) {
		try {
			if((int)size.get(sll) != a.size()) {
				return false;
			}
			Object n = first.get(sll);
			int i=0;
			while(i<a.size()) {
				if(!a.get(i).equals(data.get(n))) {
					return false;
				}
				n = next.get(n);
				i++;
			}
			return true;
		} catch(Exception e) {
			return false;
		}
	}
}
