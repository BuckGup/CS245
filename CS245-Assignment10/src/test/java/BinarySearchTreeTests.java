import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;


class BinarySearchTreeTests {

	private BinarySearchTree<Integer> bst;
	private Field root;
	private Field first;
	private Field numElements;
	private Field data;
	private Field left;
	private Field right;
	private Field parent;
	private Field next;
	private Constructor<?> nodeConstructor;

	@BeforeEach
	public void setUp() throws Exception {
		bst = new BinarySearchTree<Integer>();
		root = BinarySearchTree.class.getDeclaredField("root");
		root.setAccessible(true);
		first = BinarySearchTree.class.getDeclaredField("first");
		first.setAccessible(true);
		numElements = BinarySearchTree.class.getDeclaredField("numElements");
		numElements.setAccessible(true);

		Class<?> nodeClass = Class.forName("BinarySearchTree$BSTNode");
		data = nodeClass.getDeclaredField("data");
		data.setAccessible(true);
		left = nodeClass.getDeclaredField("left");
		left.setAccessible(true);
		right = nodeClass.getDeclaredField("right");
		right.setAccessible(true);
		parent = nodeClass.getDeclaredField("parent");
		parent.setAccessible(true);
		next = nodeClass.getDeclaredField("next");
		next.setAccessible(true);
		nodeConstructor = nodeClass.getDeclaredConstructors()[1];
	}

	@Test
	public void testFields() {
		assertEquals(3, BinarySearchTree.class.getDeclaredFields().length, "BinarySearchTree should only have \"root\", \"first\", and \"numElements\" fields");
	}

	@Test
	public void testConstructor() {
		try {
			assertNull(root.get(bst),"BinarySearchTree constructor is not working correctly (check root)");
			assertNull(first.get(bst),"BinarySearchTree constructor is not working correctly (check first)");
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
			root.set(bst, nodeConstructor.newInstance(37, null, null, null, null));
			first.set(bst, root.get(bst));
			bst.clear();
			assertEquals(0, numElements.get(bst), "clear method is not working correctly (check numElements)");
			assertNull(root.get(bst), "clear method is not working correctly (check root)");
			assertNull(first.get(bst), "clear method is not working correctly (check first)");
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
				mercury(myBst, randVal);
				assertTrue(isLinkedBST(root.get(bst)), "add method is not working correctly (tree violates linked BST properties)");
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
				mercury(bst, randVal);
				values.add(randVal);
			}
			for(Integer i : values) {
				assertTrue(bst.contains(i), "contains method is not working correctly (not returning true when appropriate)");
				assertFalse(bst.contains(i+100),"contains method is not working correctly (not returning false when appropriate)");
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
				mercury(bst, randVal);
				mercury(myBst, randVal);
				values.add(randVal);
			}
			Iterator<Integer> itr = values.iterator();
			Integer value;
			int size;
			while(itr.hasNext()) {
				value = itr.next();
				size = (Integer)numElements.get(bst);
				bst.remove(value);
				mars(myBst, value);
				assertTrue((Integer)numElements.get(bst) < size,"remove method is not working correctly (check numElements)");
				assertTrue(isLinkedBST(root.get(bst)), "remove method is not working correctly (tree violates linked BST properties)");
				assertTrue(equalBSTs(myBst, bst),"remove method is not working correctly (BST not constructed as expected)");

				size = (Integer)numElements.get(bst);
				bst.remove(value+1001);//not present
				assertTrue(((Integer)numElements.get(bst)).equals(size),"remove method is not working correctly (check numElements)");
				assertTrue(isLinkedBST(root.get(bst)),"remove method is not working correctly (tree violates linked BST properties)");
				assertTrue(equalBSTs(myBst, bst),"remove method is not working correctly (BST not constructed as expected)");
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
				mercury(bst, randVal);
				values.add(randVal);
			}
			Comparable[] bstArray = bst.toArray();
			java.util.Iterator<Integer> itr = values.iterator();
			assertEquals(values.size(), bstArray.length, "toArray method is not working correctly (check array capacity)");
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
		BinarySearchTree<Integer>.Iterator itr = bst.iterator();
		Set<Integer> values = new TreeSet<Integer>();
		assertFalse(itr.hasNext(),"your iterator is not working correctly (hasNext not returning false when appropriate)");
		try {
			Random r  = new Random();
			int randVal;
			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				mercury(bst, randVal);
				values.add(randVal);
			}
			itr = bst.iterator();
			java.util.Iterator<Integer> tsItr = values.iterator();
			while(tsItr.hasNext()) {
				assertTrue(itr.hasNext(),"your iterator is not working correctly (hasNext not returning true when appropriate)");
				assertEquals(tsItr.next(), itr.next(),"your iterator is not working correctly (next not returning the correct element)");
			}
			assertFalse(itr.hasNext(),"your iterator is not working correctly (hasNext not returning false when appropriate)");

		} catch (Exception e) {
			fail("your iterator is not working correctly");
		}
	}

	@Test
	public void testBalance() {
		try {
			BinarySearchTree<Integer> myBst = new BinarySearchTree<Integer>();
			Set<Integer> values = new HashSet<Integer>();
			Random r  = new Random();
			int randVal;
			for(int i=0; i<100; ++i) {
				randVal = r.nextInt(100);
				bst.add(randVal);
				mercury(myBst, randVal);
				values.add(randVal);
			}
			numElements.set(bst, values.size());
			bst.balance();
			sun(myBst);
			assertTrue(isBalanced(root.get(bst)),"balance method is not working correctly (BST not balanced)");
			assertTrue(isLinkedBST(root.get(bst)),"remove method is not working correctly (tree violates linked BST properties)");
			assertTrue(equalBSTs(myBst, bst),"balance method is not working correctly (BST not constructed as expected)");
		} catch (Exception e) {
			fail("balance method is not working correctly");
		}
	}

	private boolean isLinkedBST(Object r)  {
		try {
			if(r == null || left.get(r) == null && right.get(r) == null) {
				return true;
			}
			if (next.get(r) == null && (right.get(r) != null || ((parent.get(r) != null && ((Integer) data.get(parent.get(r))).compareTo((Integer) data.get(r)) > 0)))) {
				return false;
			}
	        if(left.get(r) != null) {
	        		return ((Integer) data.get(r)).compareTo((Integer) data.get(left.get(r))) > 0 && isLinkedBST(left.get(r));
	        }
	        if(right.get(r) != null) {
	        		return ((Integer) data.get(r)).compareTo((Integer) data.get(right.get(r))) < 0 && isLinkedBST(right.get(r));
	        }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return false;
    }

	private boolean equalBSTs(BinarySearchTree<Integer> mb, BinarySearchTree<Integer> yb) {
		try {
			if (!numElements.get(mb).equals(numElements.get(yb))) {
				return false;
			}
			if(first.get(mb) != null) {
				if(first.get(yb) == null || !data.get(first.get(mb)).equals(data.get(first.get(yb)))) {
					return false;
				}
			} else if(first.get(yb) != null) {
				return false;
			}
			return equalBSTs(root.get(mb), root.get(yb));
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean equalBSTs(Object mn, Object yn) {
		try {
			if(mn == null && yn == null) {
				return true;
			}
			if(parent.get(mn) != null) {
				if(parent.get(yn) == null || !data.get(parent.get(mn)).equals(data.get(parent.get(yn)))) {
					return false;
				}
			} else if(parent.get(yn) != null) {
				return false;
			}
			if(next.get(mn) != null) {
				if(next.get(yn) == null || !data.get(next.get(mn)).equals(data.get(next.get(yn)))) {
					return false;
				}
			} else if(next.get(yn) != null) {
				return false;
			}
			if(mn != null && yn != null) {
				return data.get(mn).equals(data.get(yn)) && equalBSTs(left.get(mn), left.get(yn)) && equalBSTs(right.get(mn), right.get(yn));
			}
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return false;
	}

	private boolean isBalanced(Object n) {
		try {
	        int lh;
	        int rh;
	        if (n == null) return true;
	        	lh = height(left.get(n));
	        rh = height(right.get(n));
	        if (Math.abs(lh - rh) <= 1 && isBalanced(left.get(n)) && isBalanced(right.get(n)))
	            return true;
		} catch(Exception e) {
			System.out.println(e);
		}
		return false;
	}

	private int height(Object n) {
		try {
			if (n == null) return 0;
			return 1 + Math.max(height(left.get(n)), height(right.get(n)));
		} catch(Exception e) {
			System.out.println(e);
		}
		return 0;
	}

	private void mercury(BinarySearchTree<Integer> b, Integer v) {
        try {
			root.set(b, venus(root.get(b), v, null, null, b));
			moon(b);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private Object venus(Object n, Integer v, Object pa, Object pr, Object b) {
    		try {
	        if (n == null) {
	            n = nodeConstructor.newInstance(v, null, null, null, null);
	            parent.set(n, pa);
	            if(pa != null) {
	            		if(((Integer) data.get(pa)).compareTo(v) >= 0) {
	            			next.set(n, pa);
	            		}
	            }
	            if(pr != null) {
	            		next.set(n, next.get(pr));
	            		next.set(pr, n);
	            }
	            numElements.set(b, (Integer)(numElements.get(b))+1);
	        } else if (((Integer) data.get(n)).compareTo(v) > 0) {
	            left.set(n, venus(left.get(n), v, n, pluto(n), b));
	        } else if (((Integer) data.get(n)).compareTo(v) < 0) {
	            right.set(n, venus(right.get(n), v, n, n, b));
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

    private void mars(BinarySearchTree<Integer> b, Integer v) {
    		try {
			root.set(b, jupiter(root.get(b), v, b));
			moon(b);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private Object jupiter(Object n, Integer v, Object b) {
		try {
        if (n == null) {
            return null;
        } else if (((Integer)data.get(n)).compareTo(v) > 0) {
            left.set(n, jupiter(left.get(n), v, b));
        } else if (((Integer)data.get(n)).compareTo(v) < 0) {
            right.set(n, jupiter(right.get(n), v, b));
        } else {
            if (right.get(n) == null) {
            	Object pr = saturn(n);
        		if(pr != null) {
        			next.set(pr, next.get(n));
        		}
        		if(left.get(n) != null) {
        			parent.set(left.get(n), parent.get(n));
        		}
        		numElements.set(b, (Integer)(numElements.get(b))-1);
                return left.get(n);
            } else if (left.get(n) == null) {
            	Object pr = saturn(n);
            	if(pr != null) {
        			next.set(pr, next.get(n));
        		}
        		if(right.get(n) != null) {
        			parent.set(right.get(n), parent.get(n));
        		}
        		numElements.set(b, (Integer)(numElements.get(b))-1);
                return right.get(n);
            } else {
                data.set(n, neptune(left.get(n)));
                left.set(n, jupiter(left.get(n), (Integer)data.get(n), b));
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

    private Integer neptune(Object n) {
    		try {
	        if (right.get(n) == null) {
	            return (Integer)data.get(n);
	        } else {
	            return neptune(right.get(n));
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

    private Object[] earth(BinarySearchTree<Integer> b) {
    		ArrayList<Object> aList = new ArrayList<Object>();
    		Object[] arr = null;
    		try {
			arr = (Object[]) new Comparable[(Integer)numElements.get(b)];
			uranus(root.get(b), aList);
    		} catch (IllegalArgumentException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		} catch (IllegalAccessException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		return aList.toArray(arr);
}

    private void uranus(Object n, List<Object> aList) {
    		try {
			if (n != null) {
				uranus(left.get(n), aList);
				aList.add(data.get(n));
				uranus(right.get(n), aList);
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private Object pluto(Object n) {
    		try {
			if(parent.get(n) == null) {
				return null;
			}
			else if(right.get(parent.get(n)) == n) {
				return parent.get(n);
			}
			else {
				return pluto(parent.get(n));
			}
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    		return null;
    }

    private Object saturn(Object n) {
		try {
			if(left.get(n) != null) {
				Object n2 = left.get(n);
				while(right.get(n2) != null) {
					n2 = right.get(n2);
				}
				return n2;
			}
			if(parent.get(n) != null) {
				if(right.get(parent.get(n)) == n) {
					return parent.get(n);
				}
				else {
					Object n2 = n;
					while(parent.get(n2) != null && left.get(parent.get(n2)) == n2) {
						n2 = parent.get(n2);
					}
					if(parent.get(n2) != null) {
						return parent.get(n2);
					}
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
    }

    private void moon(Object b) {
    		try {
			Object n = root.get(b);
			if(n == null) {
					first.set(b, null);
	    			return;
	    		}
			while(left.get(n) != null) {
				n = left.get(n);
			}
			first.set(b, n);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
    }

    private void sun(BinarySearchTree<Integer> b) {
    		try {
			 Object[] v = earth(b);
			 root.set(b, ranOut(v, 0, (int)numElements.get(b)-1, null));
			 moon(b);
    		} catch(Exception e) {
    			e.printStackTrace();
    		}
	}

	private Object ranOut(Object[] v, int s, int e, Object p) {
		if(s > e) return null;
		try {
			int m = (s + e) / 2;
			Object n = nodeConstructor.newInstance(v[m], null, null, null, null);
			left.set(n, ranOut(v, s, m - 1, n));
	        right.set(n, ranOut(v, m + 1, e, n));
	        parent.set(n, p);
	        if(parent.get(n) != null) {
		        	if(((Integer) data.get(parent.get(n))).compareTo((Integer)data.get(n)) > 0 && right.get(n) == null) {
		        		next.set(n, parent.get(n));
		        }
		        else if(((Integer) data.get(parent.get(n))).compareTo((Integer)data.get(n)) > 0 && right.get(n) != null) {
		        		Object n2 = right.get(n);
		        		while(right.get(n2) != null) {
		        			n2 = right.get(n2);
		        		}
		        		next.set(n2, parent.get(n));
		        }
		        else if(((Integer) data.get(parent.get(n))).compareTo((Integer)data.get(n)) < 0 && left.get(n) == null) {
		        		next.set(parent.get(n), n);
		        }
		        else if(((Integer) data.get(parent.get(n))).compareTo((Integer)data.get(n)) < 0 && left.get(n) != null) {
		        		Object n2 = left.get(n);
			    		while(left.get(n2) != null) {
			    			n2 = left.get(n2);
			    		}
			    		next.set(parent.get(n), n2);
		        }
	        }
	        else if(right.get(n) != null) {
	        		Object n2 = right.get(n);
		    		while(left.get(n2) != null) {
		    			n2 = left.get(n2);
		    		}
		    		next.set(n, n2);
	        }
	        return n;
		} catch(Exception e2) {
			System.out.println(e2);
		}
		return null;
	}
}