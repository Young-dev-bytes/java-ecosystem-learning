package java_basic._1109_collection.set.d;

import java.util.HashSet;
import java.util.Objects;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/15 09:26
 */

public class Main {

    public static void main(String[] args) {

        HashSet hashSet = new HashSet();

        hashSet.add(new Employee("jack",2000d, new MyDate("2023","11","26")));
        hashSet.add(new Employee("jack",2000d, new MyDate("2023","11","26")));
        hashSet.add(new Employee("tom",4000d, new MyDate("2023","11","26")));

    }
}

class Employee {

    private String name;
    private double salary;
    private MyDate birthday;

    public Employee(String name, Double salary, MyDate birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(name, employee.name) && Objects.equals(birthday, employee.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, birthday);
    }
}

class MyDate {

    private String year;
    private String month;
    private String day;

    public MyDate(String year, String month, String day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyDate myDate = (MyDate) o;
        return Objects.equals(year, myDate.year) && Objects.equals(month, myDate.month) && Objects.equals(day, myDate.day);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, month, day);
    }
}
