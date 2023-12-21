package java_basic._1109_collection.linkedList.b;

import java.util.NoSuchElementException;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/13 12:34
 */

public class LinkedList {


    private Node first;
    private Node last;

    private class Node {

        private int value;
        private Node next;

        public Node(int value) {
            this.value = value;
        }
    }


    public void addLast(int item) {
        Node node = new Node(item);
        // node.value = item;

        if (isEmpty()) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
    }

    public void addFist(int item) {
        Node node = new Node(item);

        if (isEmpty()) {
            first = last = node;
        } else {
            node.next = first;
            first = node;
        }
    }


    public int indexOf(int item) {
        int index = 0;
        Node current = first;
        while (current != null) {
            if (current.value == item) return index;
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(int item) {
        return indexOf(item) != -1;
    }

    public void removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        if (first == last) {
            first = last = null;
            return;
        }

        Node second = first.next;
        first.next = null;
        first = second;
    }


    public void removeLast() {
        Node current = first;
        while (current != null){

        }
    }


    private boolean isEmpty() {
        return first == null;
    }


    // addFirst
    // addLast
    // deleteFirst
    // deleteLast
    // contains
    // indexOf

}
