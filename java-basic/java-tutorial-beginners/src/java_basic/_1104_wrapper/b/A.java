package java_basic._1104_wrapper.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 10:36
 */

public class A {

    private String name;
    public void f1() {
        class B{
            private final String NAME = "aa";
            public void show() {
                System.out.println("NAME= " + NAME);
                System.out.println("name= " + A.this.name);
            }
        }

        B b = new B();
        b.show();
    }

    public static void main(String[] args) {
        A a = new A();
        a.f1();
    }


}
