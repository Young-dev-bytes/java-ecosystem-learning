package java_basic._1109_collection.linkedList.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/13 12:27
 */

public class Main {

    public static void main(String[] args) {

        /*LinkedList list = new LinkedList<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        System.out.println(list.contains(10));
        System.out.println(list.indexOf(10));
        Object[] toArray = list.toArray();
        System.out.println(toArray);
        System.out.println(Arrays.toString(toArray));*/


        LinkedList list = new LinkedList();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        list.addLast(40);
        // list.addFist(40);
        System.out.println(list.indexOf(30));


    }
}

