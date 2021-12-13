package ua.edu.ucu.collections.immutable;


import java.util.ArrayList;
import java.util.Objects;

public final class ImmutableLinkedList implements ImmutableList {
    private Node head = new Node(), tail;
    private int length = 0;

    public ImmutableLinkedList(Object[] elements) {
        if (elements.length == 0) {
            tail = head;
        } else {
            Node previous = head;
            head.setValue(elements[0]);
            length++;
            for (int i = 1; i < elements.length; ++i) {
                final Node node = new Node();
                node.setValue(elements[i]);
                node.setPrevious(previous);
                previous.setNext(node);
                previous = node;
                length++;
            }
            tail = previous;
        }
    }

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        Node current = head;
        while (current != null) {
            res.append(current.getValue());
            res.append(" ");
            current = current.getNext();
        }
        return res.toString();
    }


    @Override
    public ImmutableList add(Object e) {
        return addAll(size(), new Object[] {e});
    }

    @Override
    public ImmutableList add(int index, Object e){
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        Object[] newElements = new Object[this.length+c.length];
        Node curr = this.head;
        for (int i = 0; i < this.length; i++) {
            newElements[i] = curr.getValue();
            curr = curr.getNext();
        }
        int counter = 0;
        for (int i = this.length; i < this.length+c.length; i++) {
            newElements[i] = c[counter++];
        }
        return new ImmutableLinkedList(newElements);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c){
    Object[] array = new Object[length+c.length];
    Node current = head;

    for (int i = 0; i < index; i++) {
        array[i] = current.getValue();
        current = current.getNext();
    }

    System.arraycopy(c, 0, array, index, c.length);

    for (int i = index + c.length; i < length + c.length; i++) {
        array[i] = current.getValue();
        current = current.getNext();
    }
        return new ImmutableLinkedList(array);
}

    @Override
    public Object get(int index) {
        int idx = 0;
        Node current = head;
        while (current != null) {
            if (Objects.equals(index, idx)) {
                return current.getValue();
            }
            idx++;
            current = current.getNext();
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ImmutableList remove(int index) {
        Object[] array = new Object[length-1];
        Node probe = head;
        int j = 0;
        for (int i = 0; i < length; i++) {
            if (i == index) {
                probe = probe.getNext();
                continue;
            }
            array[j++] = probe.getValue();
            probe = probe.getNext();
        }

        return new ImmutableLinkedList(array);
    }

    @Override
    public ImmutableList set(int index, Object e){
        checkIfValid(index);
            if (isEmpty()) {
            throw new IllegalArgumentException();
        }
        Object[] newList = toArray();
        newList[index] = e;
            return new ImmutableLinkedList(newList);
}

    private void checkIfValid(int index) {
    }


    @Override
    public int indexOf(Object e) {
        int i = 0;
        Node current = head;
        while (current != null) {
            if (Objects.equals(e, current.getValue())) {
                return i;
            }
            i++;
            current = current.getNext();
        }
        return -1;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public Object[] toArray() {
        if (head == null) {
            return new Object[0];
        }
        ArrayList<Object> arrCopy = new ArrayList<>();
        Node current = head;
        while (current != null) {
            arrCopy.add(current.getValue());
            current = current.getNext();
        }
        return arrCopy.toArray();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return (ImmutableLinkedList) addAll(0, new Object[] {e});
    }

    public ImmutableLinkedList addLast(Object e) {
        return (ImmutableLinkedList) add(e);
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public Object getFirst() {
        return head.getValue();
    }

    public Object getLast() {
        return tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(length - 1);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}