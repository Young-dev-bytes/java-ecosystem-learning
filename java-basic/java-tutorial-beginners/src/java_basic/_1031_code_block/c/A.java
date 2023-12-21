package java_basic._1031_code_block.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/31 15:36
 */

public class A {

    private int n2 = getN2();

    {
        System.out.println("A common block 01");
    }


    static {
        System.out.println("A static block 01");
        // System.out.println(n1);
        getN1();
    }
    private static int n1 = getN1();


    public static int getN1() {
        System.out.println("getN1被调用");
        return 10;
    }

    public int getN2() {
        System.out.println("getN2被调用");
        return 20;
    }

    public A() {
        System.out.println("A constructor call");
    }
}
