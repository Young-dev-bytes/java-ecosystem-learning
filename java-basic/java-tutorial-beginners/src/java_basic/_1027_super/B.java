package java_basic._1027_super;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/27 14:32
 */

public class B extends A {

    public int n1 = 10;

    public void hi() {
        System.out.println(n1 + " " + super.n1 + " " + super.n2 + " " + super.n3 + " ");
    }

    public void ok() {
        this.test100();
        super.test200();
        super.test300();
    }

    public void test100() {
        System.out.println("B - test100");

    }

    public static void main(String[] args) {
        B b = new B();
        b.ok();
        b.hi();
    }


}
