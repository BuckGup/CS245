import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

public class Lab4Tests {

	private Sack<Integer> s;
	private Field elementData;
	private Field size;
	
	@BeforeEach
	public void setUp() throws Exception {
		s = new Sack<Integer>(10);
		elementData = Sack.class.getDeclaredField("elementData");
		elementData.setAccessible(true);
		size = Sack.class.getDeclaredField("size");
		size.setAccessible(true);
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
}
