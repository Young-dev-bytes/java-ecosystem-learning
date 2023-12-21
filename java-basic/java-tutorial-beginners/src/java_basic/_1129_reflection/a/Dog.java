package java_basic._1129_reflection.a;

public class Dog {

    public String name = "小黑";

    private int age;

    public void hi() {
        System.out.println("hi method");
    }

    public void cry() {
        System.out.println("no parameters cry method");
    }

    public void cry(String str, Integer i) {
        System.out.println(str);
        System.out.println(i);
        System.out.println("cry method");
    }

    public Dog() {
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
