package java_basic._1028_dynamic_bind.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 16:37
 */

public class Teacher extends Person {

    private double salary;

    public Teacher(String name, int age, double salary) {
        super(name, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String say() {
        return super.say() + " salary=" + salary;
    }
}
