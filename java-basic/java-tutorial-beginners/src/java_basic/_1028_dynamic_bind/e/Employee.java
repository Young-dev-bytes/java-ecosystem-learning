package java_basic._1028_dynamic_bind.e;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 18:09
 */

public class Employee {

    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getAnnual() {
        return 12 * salary;
    }

}
