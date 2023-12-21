package java_basic._1109_collection.exercise.c;

import java.util.*;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/19 16:46
 */

@SuppressWarnings("all")
public class Demo {

    public static void main(String[] args) {

        Map map = new HashMap<String, Integer>();
        map.put("jack",650);
        map.put("tom",1200);
        map.put("smith",2900);

        map.put("jack",2600);
//        Collection values = map.values();
//        Set keySet = map.keySet();
//        Iterator iterator = keySet.iterator();
//        while (iterator.hasNext()) {
//            Object key;
//            map.put(key = iterator.next(), ((Integer)map.get(iterator.next()) + 1000));
//        }
//        values.forEach(item -> {
//            System.out.println(item);
//        });
//        keySet.forEach(item -> {
//            System.out.println(item);
//        });
        /*for (Object value : values) {
            value = (Integer)value + 1000;
        }*/

        Set entrySet = map.entrySet();
        Iterator iteratorT = entrySet.iterator();
        while (iteratorT.hasNext()) {
            Map.Entry entry = (Map.Entry)iteratorT.next();
            System.out.println(entry.getKey() + " --- " + entry.getValue());
        }


    }
}
