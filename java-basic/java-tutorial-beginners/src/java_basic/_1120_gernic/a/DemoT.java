package java_basic._1120_gernic.a;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 13:48
 */

public class DemoT {

    public static void main(String[] args) {

        Map<String, Teacher> studentHashMap = new HashMap<String, Teacher>();
        studentHashMap.put("a", new Student("a", 12));
        studentHashMap.put("b", new Student("b", 12));
        studentHashMap.put("c", new Student("c", 12));
        System.out.println(studentHashMap);

        Set<Map.Entry<String, Teacher>> entries = studentHashMap.entrySet();
        Iterator<Map.Entry<String, Teacher>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Teacher> next = iterator.next();
            System.out.println(next.getKey() + "-" + next.getValue());
        }

        Map<String, Student> map = new HashMap<String, Student>();
        map.put("123", (Student) new Teacher());




    }
}

class Student extends Teacher{

    private String name;

    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}

class Teacher{

}
