package ua.edu.ucu.collections.immutable;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ImmutableArrayListTest {
    ImmutableArrayList immutableArrayList;

    @Before
    public void setUp() {
        immutableArrayList = new ImmutableArrayList(new Integer[] {1, 2});

    }

    @Test
    public void testAdd() {
        immutableArrayList = (ImmutableArrayList) immutableArrayList.add(3);
        assertArrayEquals(new Integer[] {1, 2, 3}, immutableArrayList.toArray());
        immutableArrayList = new ImmutableArrayList(new Integer[] {1, 2});
        immutableArrayList = (ImmutableArrayList) immutableArrayList.add(1, 3);
        assertArrayEquals(new Integer[] {1, 3, 2}, immutableArrayList.toArray());
    }

    @Test
    public void testAddAll() {
        immutableArrayList = (ImmutableArrayList) immutableArrayList.addAll(new Integer[] {3, 4});
        assertArrayEquals(new Integer[] {1, 2, 3, 4}, immutableArrayList.toArray());

        immutableArrayList = new ImmutableArrayList(new Integer[] {1, 2});
        immutableArrayList = (ImmutableArrayList) immutableArrayList.addAll(1, new Integer[]{3, 4});
        assertArrayEquals(new Integer[] {1, 3, 4, 2}, immutableArrayList.toArray());
    }

    @Test
    public void testGet() {
        Assert.assertEquals(2, immutableArrayList.get(1));
    }

    @Test
    public void testRemove() {
        assertArrayEquals(new Integer[] {1}, immutableArrayList.remove(1).toArray());
    }

    @Test
    public void testSet() {
        assertArrayEquals(new Integer[] {1, 5}, immutableArrayList.set(1, 5).toArray());
    }

    @Test
    public void testIndexOf() {
        Assert.assertEquals(1, immutableArrayList.indexOf(2));
    }

    @Test
    public void testSize() {
        Assert.assertEquals(2, immutableArrayList.size());
    }

    @Test
    public void testClear() {
        assertArrayEquals(new Integer[0], immutableArrayList.clear().toArray());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertFalse(immutableArrayList.isEmpty());
        Assert.assertTrue(immutableArrayList.clear().isEmpty());
    }

    @Test
    public void testToArray() {
        assertArrayEquals(new Integer[] {1, 2}, immutableArrayList.toArray());
    }
}
