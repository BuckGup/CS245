import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SackTests {

	private Sack<Integer> s;
	private Field elementData;
	private Field size;
	private Method ensureCapacity;
	private Method remove;
	
	@BeforeEach
	public void setUp() throws Exception {
		s = new Sack<Integer>();
		elementData = Sack.class.getDeclaredField("elementData");
		elementData.setAccessible(true);
		size = Sack.class.getDeclaredField("size");
		size.setAccessible(true);
		ensureCapacity = Sack.class.getDeclaredMethod("ensureCapacity", int.class);
		ensureCapacity.setAccessible(true);
		remove = Sack.class.getDeclaredMethod("remove", int.class);
		remove.setAccessible(true);
	}

	@Test
	public void testDefaultConstructor() {
		try {
			assertEquals(0, size.get(s), "Sack() constructor is not working correctly (check size usage)");
			assertEquals(Sack.DEFAULT_CAPACITY, ((Object[]) elementData.get(s)).length, "Sack() constructor is not working correctly (check array capacity usage)");
		}  catch (Exception e) {
			fail("Sack() constructor and/or instance variables not found");
		}
	}
	
	@Test
	public void testConstructor() {
		try {
			Random rand = new Random();
			int capacity = rand.nextInt(50);
			s= new Sack<Integer>(capacity);
			assertEquals(0, size.get(s), "Sack(int) constructor is not working correctly (check size usage)");
			assertEquals(capacity, ((Object[]) elementData.get(s)).length, "Sack(int) constructor is not working correctly (check array capacity usage)");
		}  catch (Exception e) {
			fail("Sack(int) constructor and/or instance variables not found");
		}
	}
	
	@Test
	public void testConstructorInvalidArgument() {
		Random rand = new Random();
		int capacity = rand.nextInt(49) + 1;
		Executable statement = () -> new Sack<Integer>(-capacity);
		assertThrows(IllegalArgumentException.class, statement, "Sack(int) constructor is not working correctly (check array capacity usage)");
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
	public void testAdd() {
		try {
			Random rand = new Random();
			int numElements = rand.nextInt(9) + 11;
			ArrayList<Integer> expectedElements = new ArrayList<Integer>();
			int randElement;
			for(int i=0; i<numElements; ++i) {
				randElement = rand.nextInt(50) + 1;
				expectedElements.add(randElement);
				s.add(randElement);
				assertEquals(i+1, size.get(s), "add is not working correctly (check size usage)");
			}
			for(int i=0; i<numElements; ++i) {
				assertEquals(expectedElements.get(i), ((Object[])elementData.get(s))[i], "add is not working correctly (sack array element order incorrect)");
			}
		} catch (Exception e) {
			fail("add is not working correctly");
		}
	}
	
	@Test
	public void testGrab() {
		assertNull(s.grab(), "grab is not working correctly (check with empty sack)");
		Random rand = new Random();
		int numElements = rand.nextInt(9) + 1;
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
			for(int i=0; i<numElements; ++i) {
				expectedElements.remove(s.grab());
				assertEquals(numElements-i-1, size.get(s), "grab is not working correctly (check size usage)");
			}
			assertEquals(0, expectedElements.size(), "grab is not working correctly (check size usage)");
		} catch (Exception e) {
			fail("grab is not working correctly");
		}
	}
	
	@Test
	public void testDump() {
		try {
			Random rand = new Random();
			int numElements = rand.nextInt(8) + 1;
			Integer[] setElementData = new Integer[numElements+1];
			for(int i=0; i<numElements; ++i) {
				setElementData[i] = rand.nextInt(50) + 1;
			}
			elementData.set(s, Arrays.copyOf(setElementData, setElementData.length));
			size.set(s, numElements);
			Object[] dumpedElements = s.dump();
			assertEquals(0, size.get(s), "dump is not working correctly (check size usage)");
			Object[] postDumpElementData = (Object[]) elementData.get(s);
			assertTrue(dumpedElements != postDumpElementData, "dump is not working correctly (sack shouldn't reference dumped data)");
			assertEquals(numElements, dumpedElements.length, "dump is not working correctly (dumped list capacity incorrect)");
			for(int i=0; i<numElements; ++i) {
				assertEquals(setElementData[i], dumpedElements[i], "dump is not working correctly (wrong element order)");
			}
			assertEquals(numElements+1, postDumpElementData.length, "dump is not working correctly (sack array capcity incorrect after dump)");
			for(int i=0; i<numElements; ++i) {
				assertNull(postDumpElementData[i], "dump is not working correctly (sack array elements not null after dump)");
			}
		} catch (Exception e) {
			fail("dump is not working correctly");
		}
	}
	
	@Test
	public void testEnsureCapacity() {
		assertEquals(2, ensureCapacity.getModifiers(), "ensureCapacity does not have the correct modifiers");
		try {
			for(int i=0; i<=10; ++i) {
				ensureCapacity.invoke(s, i);
				assertEquals(10, ((Object[])elementData.get(s)).length, "ensureCapacity is not working correctly (capacity changing unnecessarily)");
			}
			ensureCapacity.invoke(s, 11);
			assertEquals(21, ((Object[])elementData.get(s)).length, "ensureCapacity is not working correctly (capacity not increased correctly)");
			
			Random rand = new Random();
			int capacity = rand.nextInt(100)+1;
			s = new Sack<Integer>(capacity);
			for(int i=0; i<=capacity; ++i) {
				ensureCapacity.invoke(s, i);
				assertEquals(capacity, ((Object[])elementData.get(s)).length, "ensureCapacity is not working correctly (capacity changing unnecessarily)");
			}
			ensureCapacity.invoke(s, capacity+1);
			assertEquals(capacity*2+1, ((Object[])elementData.get(s)).length, "ensureCapacity is not working correctly (capacity not increased correctly)");
		} catch (Exception e) {
			fail("ensureCapacity is not working correctly");
		}
	}
	
	@Test
	public void testRemove() {
		assertEquals(2, remove.getModifiers(), "remove does not have the correct modifiers");
		try {
			Random rand = new Random();
			Integer[] setElementData = new Integer[10];
			ArrayList<Integer> expectedElements = new ArrayList<Integer>();
			int randElement;
			for(int i=0; i<10; ++i) {
				randElement = rand.nextInt(50) + 1;
				setElementData[i] = randElement;
				expectedElements.add(randElement);
			}
			elementData.set(s, setElementData);
			size.set(s, 10);
			
			int randIndex;
			int numEe;
			while(!expectedElements.isEmpty()) {
				numEe = expectedElements.size()-1;
				randIndex = rand.nextInt(expectedElements.size());
				expectedElements.set(randIndex, expectedElements.get(numEe));
				expectedElements.remove(numEe);
				remove.invoke(s, randIndex);
				assertEquals(expectedElements.size(), size.get(s), "remove is not working correctly (check size usage)");
				for(int i=0; i<expectedElements.size(); ++i) {
					assertEquals(expectedElements.get(i), ((Object[])elementData.get(s))[i], "remove is not working correctly (sack array element order incorrect)");
				}
				assertNull(((Object[])elementData.get(s))[expectedElements.size()], "remove is not working correctly (sack array element not nullified correctly)");
			}
			assertEquals(0, size.get(s), "remove is not working correctly (check size usage)");
			
		} catch (Exception e) {
			fail("remove is not working correctly");
		}
	}
}
