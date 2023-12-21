package java_basic._1109_collection.properties;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/17 17:07
 */

@SuppressWarnings("all")
public class Main_ {

    public static void main(String[] args) {

        /*TreeSet treeSet = new TreeSet(new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return ((String) o1).compareTo((String) o2);
            }
        });*/
        ComparatorT comparator = new ComparatorT();
        TreeSet treeSet = new TreeSet(comparator);
        treeSet.add("a");
        treeSet.add("c");
        treeSet.add("k");
        System.out.println(treeSet.toString());
    }
}

class ComparatorT implements Comparator {

    @Override
    public int compare(Object o1, Object o2) {
        return ((String) o1).compareTo((String) o2);
    }
}
