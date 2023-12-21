package java_basic._1026_modifier.b;

import java_basic._1026_modifier.a.A;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/26 14:56
 */

public class C {

    public void m1() {
        A a = new A();
        System.out.println(a.n1);

    }

    public void m2() {
        this.m1();
    }

}
