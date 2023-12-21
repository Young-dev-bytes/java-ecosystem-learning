package java_basic._1120_gernic.e;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 18:35
 */

@SuppressWarnings({"all"})
public class Main_<T> {
    public static void main(String[] args) {

        List<Object> aaList1 = new ArrayList();
        List<String> aaList2 = new ArrayList();
        List<AA> aaList3 = new ArrayList();
        List<BB> aaList4 = new ArrayList();
        List<CC> aaList5 = new ArrayList();

        // aaList.add(new AA());
        // aaList.add(10);
        // aaList.add("str");
        // aaList.add(new CC());
        // aaList.add(new Object());

        // printCollection1(aaList5);
        // printCollection2(aaList5);
        printCollection3(aaList1);
        // System.out.println(null instanceof Object);

        Main_<Integer> strMain = new Main_<>();
        //strMain.printCollection4();


    }

    public static void printCollection1(List<?> c) {
        for (Object obj : c) {
            System.out.println(obj + " -- " + obj.getClass().getSimpleName());
        }

    }

    public static void printCollection2(List<? extends AA> c) {
        for (Object obj : c) {
            System.out.println(obj + " -- " + obj.getClass().getSimpleName());
        }
    }

    public static void printCollection3(List<? super AA> c) {

        for (Object obj : c) {
            System.out.println(obj);
        }
    }

    public void printCollection4(List<? extends T> c) {

        for (Object obj : c) {
            System.out.println(obj);
        }
    }
}

class AA {

}

class BB extends AA {

}

class CC extends BB {

}
