package ua.edu.ucu.collections.immutable;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {

    ImmutableLinkedList immutableLinkedList;

    @Before
    public void setUp() {
        immutableLinkedList = new ImmutableLinkedList(new Integer[] {1, 2});
    }


    @Test
    public void testSize() {
        assertEquals(2, immutableLinkedList.size());
    }

    @Test
    public void testClear() {
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.clear();
        assertArrayEquals(new Integer[]{}, immutableLinkedList.toArray());
    }

    @Test
    public void testIsEmpty() {
        Assert.assertTrue(immutableLinkedList.clear().isEmpty());
    }


    @Test
    public void testGetFirst() {
        assertNull(immutableLinkedList.getFirst());
    }
}