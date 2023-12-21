package java_basic._1102_innerclass.f;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 13:28
 */

public class Main {
    public static void main(String[] args) {

        Outer outer = new Outer();
        Outer.Inner inner1 = outer.f2();

        // Outer.Inner inner2 = outer.new Inner();

        // Outer.Inner inner3 = new Outer().new Inner();

         Outer.Inner inner = new Outer.Inner();


    }
}

class Outer {

    private int n1 = 10;

    public static void f1() {
        System.out.println("f1");
    }

    public Outer() {
        System.out.println("000");
    }

    static class Inner {
        private int n1 = 90;
        {
            System.out.println(n1);
            f1();
        }
        public void say() {
            // System.out.println(Outer.this.n1);
        }
    }

    public Inner f2() {
        Inner inner = new Inner();
        inner.say();

        return inner;
    }





}
