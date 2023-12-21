package java_basic._1109_collection.map.b;

import java.util.*;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/17 11:41
 */

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {

        Map map = new HashMap();
        map.put(1001, new Employee(1001, "jack", 18000.20));
        map.put(1002, new Employee(1002, "tom", 19000.20));
        map.put(1003, new Employee(1003, "young", 11000.20));

        // 1 - keySet()
        Set keySet = map.keySet();
        for (Object o : keySet) {
            ergodicEmployee(map, o);
        }
        Iterator iterator = keySet.iterator();
        while (iterator.hasNext()) {
            ergodicEmployee(map, iterator.next());
        }

        // 2 - values()
        Collection values = map.values();
        for (Object value : values) {
            Employee employee = (Employee) value;
            if (employee.getSalary() > 18000) {
                System.out.println(employee);
            }
        }

        Iterator iteratorT = values.iterator();
        while (iteratorT.hasNext()) {
            // ergodicEmployee(map, iteratorT.next());
            Employee employee = (Employee) iteratorT.next();
            if (employee.getSalary() > 18000) {
                System.out.println(employee);
            }
        }

        // 3 - entrySet()
        Set entrySet = map.entrySet();
        for (Object o : entrySet) {
            Map.Entry mapEntry = (Map.Entry) o;
            Object key = mapEntry.getKey();
            Object value = mapEntry.getValue();
            System.out.println(mapEntry.getKey() + " - " + mapEntry.getValue());
        }

        // 4 -
        Iterator iteratorEn = entrySet.iterator();
        while (iteratorEn.hasNext()) {
            Object entry = iteratorEn.next();
            Map.Entry m = (Map.Entry) entry;
            System.out.println(m.getKey() + " - " + m.getValue());

        }
    }


    private static void ergodicEmployee(Map map, Object o) {
        Employee employee = (Employee) map.get(o);
        if (employee.getSalary() > 18000) {
            System.out.println(employee);
        }
    }
}
