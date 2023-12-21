package java_basic._1101_abstract.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 17:22
 */

public abstract class Animal {

    private String name;

    public static int age = 10;

    public void say() {
        System.out.println("say");
    }

    public static void m1() {
        System.out.println("m1");
    }

    static {
        System.out.println("static");
    }

    static void eat() {

    }

    ;

    protected abstract void sleep();

    public Animal() {
    }

    public Animal(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                '}';
    }
}
