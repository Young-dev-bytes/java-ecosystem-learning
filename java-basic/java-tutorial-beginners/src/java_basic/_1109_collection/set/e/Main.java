package java_basic._1109_collection.set.e;

import java.util.ArrayList;
import java.util.Set;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/15 10:40
 */

public class Main {
    public static void main(String[] args) {

        LinkedHashMapT linkedHashMapT = new LinkedHashMapT();
        linkedHashMapT.newNode(1001, "young", new Object(), null);

    }
}

interface MapT {
    interface EntryT {
        String getKey();
        // boolean equals(Object o);
    }
}

class HashMapT implements MapT {

    private String name;

    ArrayList entrySet;

    public int age;

    public static final String TAX = "tax";

    static class NodeT implements MapT.EntryT {
        final int hash;
        final String key;
        Object value;
        NodeT next;

        public NodeT(int hash, String key, Object value, NodeT next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        @Override
        public String getKey() {
            return null;
        }
    }

    public NodeT newNode(int hash, String key, Object value, NodeT next) {
        System.out.println("HashMapT newNode call..");
        return new NodeT(hash, key, value, next);
    }
}

class LinkedHashMapT extends HashMapT implements MapT {

    LinkedHashMapT.EntryT head;

    LinkedHashMapT.EntryT tail;

    Set entrySet;

    static class EntryT extends HashMapT.NodeT {
        EntryT before, after;

        public EntryT(int hash, String key, Object value, NodeT next) {
            super(hash, key, value, next);
        }
    }

    public NodeT newNode(int hash, String key, Object value, NodeT next) {
        System.out.println("LinkedHashMapT newNode call..");
        LinkedHashMapT.EntryT entryT = new EntryT(hash, key, value, next);
        return entryT;

    }
}


