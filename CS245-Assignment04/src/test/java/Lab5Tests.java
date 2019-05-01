import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class Lab5Tests {

	private Stack<Integer> s;
	private Field elementData;
	private Field size;
	private Method ensureCapacity;
	
	@BeforeEach
	public void setUp() throws Exception {
		s = new Stack<Integer>(10);
		elementData = Stack.class.getDeclaredField("elementData");
		elementData.setAccessible(true);
		size = Stack.class.getDeclaredField("size");
		size.setAccessible(true);
		ensureCapacity = Stack.class.getDeclaredMethod("ensureCapacity", int.class);
		ensureCapacity.setAccessible(true);
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
}
