package java_basic._1028_dynamic_bind.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 16:36
 */

public class Person {

    private String name;

    private int age;

    public Person(String name) {
        this.name = name;
    }

    public void say() {
        System.out.println(name + " say()...");
    }
}
