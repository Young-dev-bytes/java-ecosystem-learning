package java_basic._1102_innerclass.d;


/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 09:52
 */

public class AnonymousInner {

    public static void main(String[] args) {
        new AnonymousInner().f1();
    }


    public void f1() {

        Anonymous e = new Anonymous() {

            @Override
            void cry() {

                System.out.println("cry...");

            }
        };

        e.cry();


    }

}
