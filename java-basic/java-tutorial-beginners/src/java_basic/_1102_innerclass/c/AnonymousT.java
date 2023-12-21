package java_basic._1102_innerclass.c;

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

        // Tiger tiger = new Tiger();
        // tiger.cry();
        IA tiger = new IA() {
            @Override
            public void cry() {
                System.out.println("tiger cry.....");
            }
        };
        tiger.cry();
        // getClass就是获取到这个对象的运行类型
        System.out.println("tiger run class: " + tiger.getClass());
    }


}

interface IA {
    void cry();
}

class Tiger {


}
/*

class Dog implements IA {

    @Override
    public void cry() {
        System.out.println("dog cry...");
    }
}

 */

class Father {

    public Father(String name) {

    }

    public void test() {

    }

}
