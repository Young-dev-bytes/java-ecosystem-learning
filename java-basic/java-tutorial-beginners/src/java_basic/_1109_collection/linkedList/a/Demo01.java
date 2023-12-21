package java_basic._1109_collection.linkedList.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/13 11:55
 */

public class Demo01 {

    public static void main(String[] args) {

        Node a1 = new Node("a1");
        Node a2 = new Node("a2");
        Node a3 = new Node("a3");


        a1.setNext(a2);
        a2.setNext(a3);

    }
}

class Node {

    private Node next;

    private Object data;

    public Node(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "next=" + next +
                ", data=" + data +
                '}';
    }
}
