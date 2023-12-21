package java_basic._1109_collection.set.c;

import java.util.Objects;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/14 17:24
 */

public class Employee {
    private String name;
    private int age;


    public Employee() {
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }



    /*@Override
    public int hashCode() {
        return this.name.hashCode() + this.age;
    }*/

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public boolean equals(Object obj) {
        Employee employee = (Employee) obj;
        return this.name == employee.getName() && this.age == employee.getAge();
    }
    /*@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return age == employee.age && Objects.equals(name, employee.name);
    }*/
}
