package java_basic._1028_dynamic_bind.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 16:37
 */

public class Teacher extends Person{

    private int salary;

    public Teacher(String name){
        super(name);
    }

    public void say() {
        System.out.println("teacher say()...");
    }


}
