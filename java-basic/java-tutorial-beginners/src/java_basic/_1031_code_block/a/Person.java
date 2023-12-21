package java_basic._1031_code_block.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/31 13:36
 */

public class Person {

    private String name = "123";

    private int age;


    // static {
    //     System.out.println("person static block");
    // }

    {
        System.out.println("person static block" + name);
    }

    public static int count = 1000;


    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "name=" + name + " " + "age=" + age;
    }
}
