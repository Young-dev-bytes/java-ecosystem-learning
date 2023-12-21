package java_basic._1120_gernic.b;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 14:40
 */

public class Main_ {
    public static void main(String[] args) {

        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee("a", 1200d, new MyDate(12, 1, 2020)));
        employees.add(new Employee("a", 2200d, new MyDate(12, 1, 2021)));
        employees.add(new Employee("a", 3200d, new MyDate(12, 1, 2019)));
        employees.add(new Worker());


        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                int res = o1.getName().compareTo(o2.getName());
                if (res == 0) {
                    return o1.getBirthday().compareTo(o2.getBirthday());
                }
                return res;
            }
        });
        Iterator<Employee> employeeIterator = employees.iterator();
        while (employeeIterator.hasNext()) {
            System.out.println(employeeIterator.next());
        }

    }
}

class Worker extends Employee {

    public Worker() {
    }

    public Worker(String name, Double salary, MyDate birthday) {
        super(name, salary, birthday);
    }
}

class Employee {

    private String name;
    private Double salary;
    private MyDate birthday;

    public Employee() {
    }

    public Employee(String name, Double salary, MyDate birthday) {
        this.name = name;
        this.salary = salary;
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public MyDate getBirthday() {
        return birthday;
    }

    public void setBirthday(MyDate birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}

class MyDate implements Comparable<MyDate> {

    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public int compareTo(MyDate myDate) {
        int compareYear = compare(year, myDate.getYear());
        int compareMonth = compare(month, myDate.getMonth());
        int compareDay = compare(day, myDate.getDay());
        return compareYear == 0 ? compareMonth == 0 ? compareDay : compareMonth : compareYear;
    }

    public int compareToT(MyDate myDate) {

        int compareYear = compare(year, myDate.getYear());
        if (compareYear != 0) {
            return compareYear;
        }

        int compareMonth = compare(month, myDate.getMonth());
        if (compareMonth != 0) {
            return compareMonth;
        }
        return compare(day, myDate.getDay());

    }

    public static int compare(int x, int y) {
        return Integer.compare(x, y);
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "day=" + day +
                ", month=" + month +
                ", year=" + year +
                '}';
    }

}
