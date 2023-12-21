package java_basic._1120_gernic.f;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 20:30
 */

public class Main_ {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        list.add("aa");
//        list.add("bb");
//        list.add("cc");
//        list.addAll(list);
//        System.out.println(list);

        // A a = new A();
        System.out.println(A.name);
        //A.name = "";
        System.out.println(AIM.name);
        AIM aim = new AIM();
        aim.f1();
        aim.f2();
        aim.f5();


    }
}

interface A {

    public static final String name = "yes";

    default void f1(){
        System.out.println("a - f1");
    }

    default public void f2(){
        System.out.println("a - f2");

    }

    static void f3() {

    }

    public static void f4(){

    }

    abstract void f5();
}

class AIM implements A, Comparable{
    static String name = "no";

    @Override
    public void f5() {
        System.out.println("f5");
    }

//    @Override
//    public void f1() {
//        System.out.println("aim - f1");
//    }

    @Override
    public void f2() {
        System.out.println("aim - f2");

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
