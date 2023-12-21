package java_basic._1102_innerclass.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 16:51
 */

public class LocalInnerClass {
    public static void main(String[] args) {

        Out out = new Out();
        out.m1();
        out.m2();

    }
}

class Out {

    private int n1 = 100;



    private Object obj;

    public void m1() {

        class Inner {
            int n2 = 300;

            public void f1() {
                System.out.println(n1);
                m2();
            }
        }

        Inner inner = new Inner();
        System.out.println("inner's variable is " + inner.n2);
        obj = inner;
        System.out.println(inner.hashCode());

    }

    public void m2() {
        System.out.println(obj.hashCode());
    }


}
