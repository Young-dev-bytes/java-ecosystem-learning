package java_basic._1101_abstract.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 19:04
 */

public abstract class Employee {

    private String name;

    private int id;

    private double salary;

    public Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    abstract void work();

}
