package java_basic._1102_innerclass.d;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 10:07
 */

public class AnonymousT {
    public static void main(String[] args) {

        Outer outer = new Outer();
        outer.method();

    }
}

class Outer {
    private int n1 = 10;

    public void method() {

        Father inner = new Father("Young") {

            public void test() {
                System.out.println("inner test..." + name);
            }
        };

        inner.test();
        System.out.println(inner.getClass());

    }


}


class Father {

    protected String name;

    public Father(String name) {
        System.out.println("name  " + name);
        this.name = name;
    }

    public void test() {
        System.out.println("father test...");

    }

}
