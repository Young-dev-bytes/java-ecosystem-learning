package java_basic._1109_collection.arrayList.b;

import java.util.List;
import java.util.Vector;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/10 12:49
 */

public class ArrayListSource {

    @SuppressWarnings({"all"})
    public static void main(String[] args) {

//        List list = new ArrayList<>();
//
//        for (int i = 1; i <= 10; i++) {
//            list.add(i);
//        }
//
//        for (int i = 11; i <= 15; i++) {
//            list.add(i);
//        }
//
//        list.add(100);
//        list.add(200);
//        list.add(null);



        List list = new Vector();

        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }

        list.add(100);

    }
}
