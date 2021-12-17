package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList immutableLinkedList;

    public Queue() {
        immutableLinkedList = new ImmutableLinkedList();
    }
    public Object peek() {
        return immutableLinkedList.getFirst();
    }

    public Object dequeue() {
        Object popped = peek();
        immutableLinkedList = immutableLinkedList.removeFirst();
        return popped;
    }

    public void enqueue(Object e) {
        immutableLinkedList = immutableLinkedList.addLast(e);
    }
}
