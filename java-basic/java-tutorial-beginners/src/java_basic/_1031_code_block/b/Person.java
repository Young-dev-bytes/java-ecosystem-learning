package java_basic._1031_code_block.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/31 14:11
 */

public class Person {

    private String name = getName();

    private int age;

    {
        System.out.println("person common code block");
    }

    static {
        System.out.println("person static code block 1");
    }

    public static int count = getCount();


    static {
        System.out.println("person static code block 2");
    }


    public Person(String name) {
        this.name = name;
    }

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    private static int getCount() {
        System.out.println("static method");
        return 1000;
    }

    public String getName() {
        return "Young";
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
