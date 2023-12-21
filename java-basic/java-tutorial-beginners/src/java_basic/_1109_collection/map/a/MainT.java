package java_basic._1109_collection.map.a;

import java.util.*;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/15 17:49
 */

@SuppressWarnings("all")
public class MainT {
    public static void main(String[] args) {
        // 那为什么我debug  代码HashMap hashMap = new HashMap()时，
        // 创建好对象之后，debug上面显示变量hashMap下面的属性entrySet已经有地址了是HashMap$EntrySet@520,
        // 而且是一个[], 为什么，怎么得来的，怎么创建的？[]这个又是如何得来的，请用源码解读
        // HashMap hashMap = new HashMap();


        Map hashMap = new HashMap();

        hashMap.put("o-1", "Young");
        hashMap.put("o-2", "Chen");
        hashMap.put("o-3", "YoungT");

        Set set = hashMap.entrySet();
        System.out.println(set.getClass());

        for (Object obj : set) {
            System.out.println(obj.getClass()); // HashMap$Node
            // 向下转型
            Map.Entry entry = (Map.Entry) obj;
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }

        Set set1 = hashMap.keySet();
        Collection values = hashMap.values();
        System.out.println(set1.getClass());
        System.out.println(values.getClass());

        for (Object o : set1) {
            System.out.println(o + "- " + hashMap.get(o));
        }

        Iterator iterator = set1.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.println(next + " - " + hashMap.get(next));
        }

        Collection values1 = hashMap.values();
        for (Object o : values1) {
            System.out.println(o);
        }

        values1.forEach(item -> {
            System.out.println(item);
        });

    }
}
