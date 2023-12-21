package java_basic._1109_collection.arrayList.a;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/9 18:09
 */

public class Demo {

    public static void main(String[] args) {

        ArrayList<String> data = new ArrayList<>();
        data.add("New Delhi");
        data.add("New York");
        System.out.println(data.toString());
//        System.out.println(data.get(3));
//        data.add("Mumbai");
//        data.add("London");
//        data.add("New Delhi");
//        data.add("New York");
//        data.add("Mumbai");
//        data.add("London");
//        data.add("New Delhi");
//        data.add("New York");
//        data.add("Mumbai");
//        data.add("London");



        data.forEach(item -> {
            System.out.println(item);
        });

        for (String datum : data) {
            System.out.println(datum);
        }

        Iterator<String> itr = data.iterator();
        while (itr.hasNext()) {

            System.out.println(itr.next());
            // data.remove(itr.next());
            // this line can introduce you to
            // java.util.ConcurrentModificationException.
        }
    }
}

class DogT{

}
