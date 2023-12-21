//package java_basic._1109_collection.linkedList.a;
//
///**
// * @author Young.
// * @version 1.0
// * @date 2023/11/13 10:15
// */
//
//public class LinkedList01 {
//    public static void main(String[] args) {
//
//        Node jack = new Node("jack");
//        Node tom = new Node("tom");
//        Node young = new Node("Young");
//
//        jack.next = tom;
//        tom.next = young;
//
//        young.pre = tom;
//        tom.pre = jack;
//
//        // 双向链表的头节点
//        Node first = jack;
//        // 双向链表的尾节点
//        Node last = young;
//
//        // 从头到尾
//        while (true) {
//            if (first == null) {
//                break;
//            }
//            System.out.println(first);
//            first = first.next;
//        }
//        //从尾到头
//        while (true) {
//            if(last == null) break;
//            System.out.println(last);
//            last = last.pre;
//        }
//
//        Node smith = new Node("smith");
//        smith.next = young;
//        smith.pre = tom;
//        young.pre = smith;
//        tom.next = smith;
//
//
//        first = jack;
//        while (true) {
//            if (first == null) {
//                break;
//            }
//            System.out.println(first);
//            first = first.next;
//        }
//
//
//    }
//}
//
//
//// 定义一个node类，Node对象表示双向链表的一个节点
//class Node {
//
//    public Object item; // 真正存放数据的地方
//    public Node next;// 指向下一个节点
//    public Node pre;// 指向上一个节点
//
//    public Node(Object item) {
//        this.item = item;
//    }
//
//    @Override
//    public String toString() {
//        return "Node{" +
//                "item=" + item +
//                '}';
//    }
//}
