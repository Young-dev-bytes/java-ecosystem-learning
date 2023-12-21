package java_basic._1109_collection.exercise.c;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 09:06
 */

@SuppressWarnings("all")
public class HashSetT {


    public static void main(String[] args) {

        HashSet set = new HashSet();
        // treeSet.add("young");
        // treeSet.add("people");
        // treeSet.add("young");
        Person p1 = new Person(1001, "AA");
        Person p2 = new Person(1002, "BB");
        set.add(p1);
        set.add(p2);
        System.out.println(set);
        p1.name = "CC";
        set.remove(p1);
        System.out.println(set);


        set.add(new Person(1001, "CC"));
        System.out.println(set);
        set.add(new Person(1001, "AA"));
        System.out.println(set);


//        System.out.println(15 & hash(p1));
//        System.out.println(hash(p1));
//
//        System.out.println(15 & hash(p2));
//        System.out.println(hash(p2));
//
//        System.out.println(15 & hash(new Person(1002, "CC")));
//        System.out.println(hash(new Person(1002, "CC")));


    }

    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}

class Person {


    public int id;
    public String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
