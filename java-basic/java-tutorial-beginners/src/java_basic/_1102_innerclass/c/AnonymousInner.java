package java_basic._1102_innerclass.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 09:52
 */

public class AnonymousInner {


    public void f1() {

        new Anonymous(){
            @Override
            public void cry() {
                System.out.println("123");
            }
            public void f2() {
            }

        };
    }

}
