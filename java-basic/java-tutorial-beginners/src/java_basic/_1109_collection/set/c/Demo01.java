package java_basic._1109_collection.set.c;

import java.util.HashSet;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/14 17:30
 */

@SuppressWarnings("all")
public class Demo01 {
    public static void main(String[] args) {
        HashSet hashSet = new HashSet();
        hashSet.add(new Employee("tom",12));
        hashSet.add(new Employee("jack",12));
        hashSet.add(new Employee("tom",12));

        Employee obj1 = new Employee();
        Employee obj2 = new Employee();

        System.out.println(obj1 == obj2);

        if (obj1.getClass().equals(obj2.getClass())) {
            System.out.println("运行时类相同");
        } else {
            System.out.println("运行时类不同");
        }

        if (obj1.getClass() == obj2.getClass()) {
            System.out.println("运行时类相同");
        } else {
            System.out.println("运行时类不同");
        }


    }
}
