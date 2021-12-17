package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList immutableLinkedList;

    public Stack() {
        immutableLinkedList = new ImmutableLinkedList();
    }

    public void push(Object e) {
        immutableLinkedList = (ImmutableLinkedList) immutableLinkedList.add(e);
    }

    public Object pop() {
        Object toReturn = peek();
        immutableLinkedList = immutableLinkedList.removeLast();
        return toReturn;
    }

    public Object peek() {
        return immutableLinkedList.getLast();
    }
}