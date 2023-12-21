package java_basic._1030_debug.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/30 13:25
 */

public class LoadClassT {
    public static void main(String[] args) {


        Person jack = new Person("jack", 23);
        System.out.println(jack);


    }
}

class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
