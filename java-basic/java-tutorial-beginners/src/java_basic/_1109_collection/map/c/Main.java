package java_basic._1109_collection.map.c;

import java.util.Hashtable;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/17 15:42
 */

@SuppressWarnings("all")
public class Main {
    public static void main(String[] args) {

        Hashtable table = new Hashtable();

        /*for (int i = 1; i <= 100; i++) {
            table.put(i, "test");
        }*/

        table.put(new Person(), 100);
        table.put(new Person(), 200);


    }
}

class Person {

    @Override
    public int hashCode() {
        return 120;
    }
}


