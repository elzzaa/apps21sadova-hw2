package ua.edu.ucu.collections.immutable;

import java.util.ArrayList;
import java.util.Objects;

public final class ImmutableLinkedList implements ImmutableList {
    public int getSize() {
        return size;
    }

    private Node head = null;
    private Node tail = null;
    private int size = 0;

    public ImmutableLinkedList(Object[] elements) {
        for (Object elem : elements) {
            insertTail(new Node(elem));
        }
    }

    public ImmutableLinkedList() { }

    @Override
    public ImmutableList add(Object e) {
        return addAll(new Object[] {e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(size, c);
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > size) {
            return this;
        }
        ImmutableLinkedList newList = copy(this);
        for (int i = c.length - 1; i >= 0; i--) {
            newList.insert(index, new Node(c[i]));
        }
        return newList;
    }

    @Override
    public Object get(int index) {
        if (indexIsInvalid(index)) {
            return -1;
        }
        else if (index == 0) {
            return getFirst();
        }

        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public ImmutableList remove(int index) {
        if (indexIsInvalid(index)) {
            return this;
        }
        ImmutableLinkedList newList = copy(this);
        newList.size--;

        if (index == 0) {
            newList.head = newList.head.getNext();
            return newList;
        }
        else if (index == newList.size) {
            newList.tail = newList.tail.getPrevious();
            newList.tail.setNext(null);
            return newList;
        }

        Node toDelete = newList.head;
        for (int i = 1; i <= index; i++) {
            toDelete = toDelete.getNext();
        }
        toDelete.getNext().setPrevious(toDelete.getPrevious());
        toDelete.getPrevious().setNext(toDelete.getNext());

        return newList;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (indexIsInvalid(index)) {
            return this;
        }
        ImmutableLinkedList newList = copy(this);
        Node node = newList.head;

        for (int i = 1; i <= index; i++) {
            node = node.getNext();
        }
        node.setValue(e);
        return newList;
    }

    @Override
    public int indexOf(Object e) {
        Node node = head;
        int count = 0;

        while (node != null) {
            if (node.getValue() == e) {
                return count;
            }
            node = node.getNext();
            count++;
        }
        return -1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node node = head;
        int count = 0;

        while (count < size) {
            arr[count++] = node.getValue();
            node = node.getNext();
        }
        return arr;
    }

    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList newList = copy(this);
        newList.insertHead(new Node(e));
        return newList;
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList newList = copy(this);
        newList.insertTail(new Node(e));
        return newList;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public Object getFirst() {
        if (isEmpty()) {
            return null;
        }
        return head.getValue();
    }

    public Object getLast() {
        if (isEmpty()) {
            return null;
        }
        return tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        return (ImmutableLinkedList) remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return (ImmutableLinkedList) remove(size - 1);
    }

    private boolean indexIsInvalid(int index) {
        return index < 0 || index >= size;
    }

    private ImmutableLinkedList copy(ImmutableLinkedList list) {
        ImmutableLinkedList newList = new ImmutableLinkedList();
        Node currNode = list.head;

        while (currNode != null) {
            newList.insertTail(new Node(currNode.getValue()));
            currNode = currNode.getNext();
        }
        return newList;
    }

    private void insertHead(Node node) {
        if (head == null) {
            tail = node;
            head = tail;
        }
        else {
            node.setNext(head);
            head.setPrevious(node);
            head = node;
        }
        size++;
    }

    private void insertTail(Node node) {
        if (head == null) {
            tail = node;
            head = tail;
        } else {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
        }
        size++;
    }

    private void insert(int index, Node node) {
        if (index == 0) {
            insertHead(node);
            return;
        }
        else if (index == size) {
            insertTail(node);
            return;
        }
        Node tempNode = head;
        for (int i = 1; i < index; i++) {
            tempNode = tempNode.getNext();
        }
        node.setNext(tempNode.getNext());
        node.setPrevious(tempNode);
        tempNode.setNext(node);
        node.getNext().setPrevious(node);
        size++;
    }
}