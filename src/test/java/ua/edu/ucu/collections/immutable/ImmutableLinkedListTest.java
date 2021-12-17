package ua.edu.ucu.collections.immutable;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Array;

import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    public ImmutableLinkedList list1;
    public ImmutableLinkedList listEmpty;

    @Before
    public void setUp() {
        list1 = new ImmutableLinkedList(new Object[] {1, 2, 3});
        listEmpty = new ImmutableLinkedList();
    }

    @Test
    public void testAdditions() {
        ImmutableLinkedList list2 = list1.addLast(1);
        assertEquals(2, list2.size());

        ImmutableLinkedList list3 = list1.addFirst(1);
        assertEquals(2, list3.size());

        ImmutableLinkedList list5 = (ImmutableLinkedList) listEmpty.add(0, 1);
        assertEquals(1, list5.size());
        ImmutableLinkedList list6 = (ImmutableLinkedList) listEmpty.add(5, 1);
        assertEquals(0, list6.size());
        ImmutableLinkedList list7 = (ImmutableLinkedList) listEmpty.add(-3, 1);
        assertEquals(0, list7.size());
    }

    @Test
    public void testGet() {
        assertEquals(-1, list1.get(-1));
        assertEquals(-1, list1.get(3));
    }

    @Test
    public void testRemove() {

        ImmutableLinkedList list3 = (ImmutableLinkedList) listEmpty.remove(0);
        assertEquals(0, list3.size());

        ImmutableLinkedList list5 = (ImmutableLinkedList) list1.remove(5);
        assertEquals(list1, list5);

        ImmutableLinkedList list6 = (ImmutableLinkedList) list1.remove(-5);
        assertEquals(list1, list6);
    }

    @Test
    public void testSet() {
        ImmutableLinkedList list6 = (ImmutableLinkedList) listEmpty.set(0, 10);
        assertTrue(list6.isEmpty());
    }

    @Test
    public void testIndexOf() {
        assertEquals(-1, list1.indexOf(1));
        assertEquals(-1, list1.indexOf(2));
        assertEquals(-1, listEmpty.indexOf(0));
    }

    @Test
    public void testSize() {
        assertEquals(3, list1.size());
        assertEquals(0, listEmpty.size());
    }

    @Test
    public void testClear() {
        ImmutableLinkedList list2 = (ImmutableLinkedList) list1.clear();
        assertEquals(0,list2.size());
    }

    @Test
    public void testIsEmpty() {
        assertFalse(list1.isEmpty());
        assertTrue(listEmpty.isEmpty());
    }


    @Test
    public void testRemoveFirst() {
        ImmutableLinkedList list2 = list1.removeFirst();
        assertEquals(null, list2.getHead());

        ImmutableLinkedList list3 = listEmpty.removeFirst();
        assertNull(list3.getFirst());
    }

    @Test
    public void testRemoveLast() {
        ImmutableLinkedList list3 = listEmpty.removeLast();
        assertNull(list3.getTail());
    }
}