package java_basic._1028_dynamic_bind.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 16:37
 */

public class Student extends Person {

    private int score;

    public Student(String name) {
        super(name);
    }

    public void say() {
        System.out.println("student say()...");
    }

}
