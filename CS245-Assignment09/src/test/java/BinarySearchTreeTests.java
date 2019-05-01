import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

class BinarySearchTreeTests {

	private BinarySearchTree<Integer> bst;
	private Field root;
	private Field numElements;
	private Field data;
	private Field left;
	private Field right;
	private Constructor<?> nodeConstructor;

	@BeforeEach
	public void setUp() throws Exception {
		bst = new BinarySearchTree<Integer>();
		root = BinarySearchTree.class.getDeclaredField("root");
		root.setAccessible(true);
		numElements = BinarySearchTree.class.getDeclaredField("numElements");
		numElements.setAccessible(true);

		Class<?> nodeClass = Class.forName("BinarySearchTree$BSTNode");
		data = nodeClass.getDeclaredField("data");
		data.setAccessible(true);
		left = nodeClass.getDeclaredField("left");
		left.setAccessible(true);
		right = nodeClass.getDeclaredField("right");
		right.setAccessible(true);
		nodeConstructor = nodeClass.getDeclaredConstructors()[1];
	}

	@Test
	public void testFields() {
		assertEquals(2, BinarySearchTree.class.getDeclaredFields().length, "BinarySearchTree should only have \"root\" and \"numElements\" fields");
	}

	@Test
	public void testConstructor() {
		try {
			assertNull(root.get(bst), "BinarySearchTree constructor is not working correctly (check root)");
			assertEquals(0, numElements.get(bst), "BinarySearchTree constructor is not working correctly (check numElements)");
		} catch (Exception e) {
			fail("BinarySearchTree constructor is not working correctly");
		}
	}

	@Test
	public void testIsEmpty() {
		try {
			assertTrue(bst.isEmpty(), "isEmpty is not working correctly (not returning true when appropriate)");
			numElements.set(bst, 1);
			assertFalse(bst.isEmpty(), "isEmpty is not working correctly (not returning false when appropriate)");
		} catch (Exception e) {
			fail("isEmpty is not working correctly");
		}
	}

	@Test
	public void testSize() {
		try {
			assertEquals(0, bst.size(), "size method is not working correctly (check with empty tree)");
			numElements.set(bst, 1);
			assertEquals(1, bst.size(), "size method is not working correctly (check with non-empty tree)");
		} catch (Exception e) {
			fail("size method is not working correctly");
		}
	}

	@Test
	public void testClear() {
		try {
			numElements.set(bst, 1);
			root.set(bst, nodeConstructor.newInstance(37, null, null));
			bst.clear();
			assertEquals(0, numElements.get(bst), "clear method is not working correctly (check numElements)");
			assertNull(root.get(bst),"clear method is not working correctly (check root)");
		} catch (Exception e) {
			fail("clear method is not working correctly");
		}
	}

	@Test
	public void testAdd() {
		try {
			BinarySearchTree<Integer> myBst = new BinarySearchTree<Integer>();
			Random r  = new Random();
			int randVal;
			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				bst.add(randVal);
				monkey(myBst, randVal);
				assertTrue(isBST(root.get(bst)), "add method is not working correctly (tree violates BST properties)");
				assertTrue(equalBSTs(myBst, bst), "add method is not working correctly (BST not constructed as expected)");
			}
		} catch (Exception e) {
			fail("add method is not working correctly");
		}
	}

	@Test
	public void testContains() {
		try {
			List<Integer> values = new ArrayList<Integer>();
			Random r  = new Random();
			int randVal;
			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				monkey(bst, randVal);
				values.add(randVal);
			}
			for(Integer i : values) {
				assertTrue(bst.contains(i), "contains method is not working correctly (not returning true when appropriate)");
				assertFalse(bst.contains(i+100), "contains method is not working correctly (not returning false when appropriate)");
			}
		} catch (Exception e) {
			fail("contains method is not working correctly");
		}
	}

	@Test
	public void testRemove() {
		try {
			BinarySearchTree<Integer> myBst = new BinarySearchTree<Integer>();
			ArrayList<Integer> values = new ArrayList<Integer>();
			Random r  = new Random();
			int randVal;
			for(int i=0; i<1000; ++i) {
				do {
					randVal = r.nextInt(1000);
				} while(values.contains(randVal));
				monkey(bst, randVal);
				monkey(myBst, randVal);
				values.add(randVal);
			}
			Iterator<Integer> itr = values.iterator();
			Integer value;
			int size;
			while(itr.hasNext()) {
				value = itr.next();
				size = (Integer)numElements.get(bst);
				bst.remove(value);
				pig(myBst, value);
				assertTrue((Integer)numElements.get(bst) < size, "remove method is not working correctly (check numElements)");
				assertTrue(isBST(root.get(bst)), "remove method is not working correctly (tree violates BST properties)");
				assertTrue(equalBSTs(myBst, bst), "remove method is not working correctly (BST not constructed as expected)");
				size = (Integer)numElements.get(bst);
				bst.remove(value+1001);//not present
				assertTrue(((Integer)numElements.get(bst)).equals(size), "remove method is not working correctly (check numElements)");
				assertTrue(isBST(root.get(bst)), "remove method is not working correctly (tree violates BST properties)");
				assertTrue(equalBSTs(myBst, bst), "remove method is not working correctly (BST not constructed as expected)");
			}
		} catch (Exception e) {
			fail("remove method is not working correctly");
		}
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testToArray() {
		try {
			Set<Integer> values = new TreeSet<Integer>();
			Random r  = new Random();
			int randVal;
			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				monkey(bst, randVal);
				values.add(randVal);
			}
			Comparable[] bstArray = bst.toArray();
			java.util.Iterator<Integer> itr = values.iterator();
			assertEquals(values.size(), bstArray.length,"toArray method is not working correctly (check array capacity)");
			int i=0;
			while(itr.hasNext()) {
				assertEquals(0, itr.next().compareTo((Integer) bstArray[i++]), "toArray method is not working correctly (element order/presence not correct)");
			}

		} catch (Exception e) {
			fail("toArray method is not working correctly");
		}
	}

	@Test
	public void testIterator() {
		BinarySearchTree.Iterator<Integer> itr = bst.iterator();
		Set<Integer> values = new TreeSet<Integer>();
		assertFalse(itr.hasNext(), "your iterator is not working correctly (hasNext not returning false when appropriate)");
		try {
			Random r  = new Random();
			int randVal;
			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				monkey(bst, randVal);
				values.add(randVal);
			}
			numElements.set(bst, values.size());
			itr = bst.iterator();
			java.util.Iterator<Integer> tsItr = values.iterator();
			for(int i=0; i<values.size(); ++i) {
				assertTrue(itr.hasNext(), "your iterator is not working correctly (hasNext not returning true when appropriate)");
				assertEquals(tsItr.next(), itr.next(),"your iterator is not working correctly (next not returning the correct element)");
			}
			assertFalse(itr.hasNext(), "your iterator is not working correctly (hasNext not returning false when appropriate)");

		} catch (Exception e) {
		    System.err.println(e.getMessage());
			fail("your iterator is not working correctly");
		}
	}

	private boolean isBST(Object r)  {
		try {
			if(r == null || left.get(r) == null && right.get(r) == null) {
				return true;
			}
	        if(left.get(r) != null) {
	        		return ((Integer) data.get(r)).compareTo((Integer) data.get(left.get(r))) > 0 && isBST(left.get(r));
	        }
	        if(right.get(r) != null) {
	        		return ((Integer) data.get(r)).compareTo((Integer) data.get(right.get(r))) < 0 && isBST(right.get(r));
	        }
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
    }

	private boolean equalBSTs(BinarySearchTree<Integer> mb, BinarySearchTree<Integer> yb) {
		try {
			if (!numElements.get(mb).equals(numElements.get(yb))) {
				return false;
			}
			return equalBSTs(root.get(mb), root.get(yb)) && numElements.get(mb).equals(numElements.get(yb));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private boolean equalBSTs(Object mn, Object yn) {
		try {
			if(mn == null && yn == null) {
				return true;
			} else if(mn != null && yn != null) {
				return data.get(mn).equals(data.get(yn)) && equalBSTs(left.get(mn), left.get(yn)) && equalBSTs(right.get(mn), right.get(yn));
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void monkey(BinarySearchTree<Integer> b, Integer v) {
        try {
			root.set(b, bear(root.get(b), v, b));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private Object bear(Object n, Integer v, Object b) {
    		try {
	        if (n == null) {
	            n = nodeConstructor.newInstance(v, null, null);
	            numElements.set(b, (Integer)(numElements.get(b))+1);
	        } else if (((Integer) data.get(n)).compareTo(v) > 0) {
	            left.set(n, bear(left.get(n), v, b));
	        } else if (((Integer) data.get(n)).compareTo(v) < 0) {
	            right.set(n, bear(right.get(n), v, b));
	        }
    		} catch (IllegalArgumentException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
        return n;
    }

    private void pig(BinarySearchTree<Integer> b, Integer v) {
    		try {
			root.set(b, mouse(root.get(b), v, b));
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private Object mouse(Object n, Integer v, Object b) {
    		try {
	        if (n == null) {
	            return null;
	        } else if (((Integer)data.get(n)).compareTo(v) > 0) {
	            left.set(n, mouse(left.get(n), v, b));
	        } else if (((Integer)data.get(n)).compareTo(v) < 0) {
	            right.set(n, mouse(right.get(n), v, b));
	        } else {
	            if (right.get(n) == null) {
	            		numElements.set(b, (Integer)(numElements.get(b))-1);
	                return left.get(n);
	            } else if (left.get(n) == null) {
	            		numElements.set(b, (Integer)(numElements.get(b))-1);
	                return right.get(n);
	            } else {
	                data.set(n, lion(left.get(n)));
	                left.set(n, mouse(left.get(n), (Integer)data.get(n), b));
	            }
	        }
    		} catch (IllegalArgumentException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        return n;
    }

    private Integer lion(Object n) {
    		try {
	        if (right.get(n) == null) {
	            return (Integer)data.get(n);
	        } else {
	            return lion(right.get(n));
	        }
    		} catch (IllegalArgumentException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return -1;
    }
}
