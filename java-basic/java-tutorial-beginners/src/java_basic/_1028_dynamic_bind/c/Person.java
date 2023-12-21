package java_basic._1028_dynamic_bind.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 16:36
 */

public class Person {

    private String name;

    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String say() {
        return name + "\t" + age;
    }
}
