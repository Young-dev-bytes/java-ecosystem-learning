package java_basic._1109_collection.map.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/17 11:40
 */

public class Employee {

    private int id;
    private String name;
    private Double salary;

    public Employee(int id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
