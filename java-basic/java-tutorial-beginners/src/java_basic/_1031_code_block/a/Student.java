package java_basic._1031_code_block.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/31 13:45
 */

public class Student extends Person{

    // static {
    //    System.out.println("student static block");
    // }

     {
        System.out.println("student static block");
     }

    public Student(String name) {
        super(name);
    }

    public Student(String name, int age) {
        super(name, age);
    }
}
