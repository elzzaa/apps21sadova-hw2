package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.Objects;

public final class ImmutableArrayList implements ImmutableList {
    private final Object[] elements;

    public ImmutableArrayList(Object[] elements) {
        this.elements = new Object[elements.length];
        System.arraycopy(elements, 0, this.elements, 0, elements.length);
    }

    public ImmutableArrayList() {
        elements = new Object[0];
    }

    @Override
    public ImmutableList add(Object e) {
        Object[] newElements = Arrays.copyOf(elements, elements.length+1);
        newElements[newElements.length-1] = e;
        return new ImmutableArrayList(newElements);
    }

    @Override
    public ImmutableList add(int index, Object e) {
        Object[] newElements = Arrays.copyOf(elements, elements.length+1);
        if (newElements.length - 1 - index >= 0)
            System.arraycopy(newElements, index, newElements, index + 1, newElements.length - 1 - index);
        newElements[index] = e;
        return new ImmutableArrayList(newElements);

    }

    @Override
    public ImmutableList addAll(Object[] c) {
        Object[] newElements = Arrays.copyOf(elements, elements.length+c.length);
        System.arraycopy(c, 0, newElements, elements.length, c.length);
        return new ImmutableArrayList(newElements);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        int el_length = elements.length;
        if (index < 0 || index > el_length) {
            return this;
        }
        Object[] newItems = new Object[el_length + c.length];
        System.arraycopy(elements, 0, newItems, 0, index);
        System.arraycopy(c, 0, newItems, index, c.length);
        System.arraycopy(elements, index, newItems, index + c.length, el_length - index);
        return new ImmutableArrayList(newItems);
    }

    @Override
    public Object get(int index) {
        return elements[index];
    }

    @Override
    public ImmutableList remove(int index) {
        int el_length = size();
        Object[] newItems = new Object[el_length - 1];
        System.arraycopy(elements, 0, newItems, 0, index);
        System.arraycopy(elements, index + 1, newItems, index, el_length - index - 1);
        return new ImmutableArrayList(newItems);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        int el_length = size();
        Object[] newItems = new Object[el_length];
        System.arraycopy(elements, 0, newItems, 0, el_length);
        newItems[index] = e;
        return new ImmutableArrayList(newItems);
    }

    @Override
    public int indexOf(Object e) {
        int el_length = size();
        for (int i = 0; i < el_length; i++) {
            if (elements[i] == e) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size());
    }
}
