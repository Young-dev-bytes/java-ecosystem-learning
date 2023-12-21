package java_basic._1109_collection.set.d;


import java.util.LinkedHashSet;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/15 10:00
 */

@SuppressWarnings("all")
public class LinkedHashSet_ {
    public static void main(String[] args) {
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new String("AA"));
        linkedHashSet.add(456);
        linkedHashSet.add(456);
        linkedHashSet.add(new Customer("刘",1001));
        linkedHashSet.add(123);
        linkedHashSet.add("hsp");
        System.out.println(linkedHashSet.toString());
        /*for (int i = 1; i <= 100; i++) {
            linkedHashSet.add(new Customer());
        }*/

    }
}

class Customer {

    private String name;
    private int id;

    public Customer() {
    }

    public Customer(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        return 100;
    }
}
