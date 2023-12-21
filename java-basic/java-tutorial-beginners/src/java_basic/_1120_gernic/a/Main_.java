package java_basic._1120_gernic.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 13:22
 */

public class Main_ {
    public static void main(String[] args) {

        Person<Cat,String> person = new Person<Cat, String>(new Cat());
        person.show();

    }
}

class Person<T, P> {

    T t = null;


    int age;

    public Person(T t) {
        this.t = t;
    }

    public Person() {
    }

    public T getT() {
        return t;
    }

    public void show() {
        System.out.println(t.getClass());
    }
}

class Cat{

}
