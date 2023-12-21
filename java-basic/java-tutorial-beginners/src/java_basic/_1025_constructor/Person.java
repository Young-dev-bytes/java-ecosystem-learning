package java_basic._1025_constructor;


/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/25 18:30
 */

public class Person {


    String name;

    int age;

    public Person(String pName, int pAge) {
        name = pName;
        age = pAge;
    }

    public void test(){
        System.out.println("test");
    }

    public static void main(String[] args) {
        Person person2 = new Person("Sunny", 19);
        System.out.println(person2.name + person2.age);
        person2.test();
//        new ArrayList<>()

        // new Person(9)


    }
}
