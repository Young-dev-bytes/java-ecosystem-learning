package java_basic._1102_innerclass.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 16:25
 */

public class Outer {

    private int n1 = 100;

    public Outer(int n1) {
        this.n1 = n1;
    }

    public int getN1() {
        return 90;
    }

    abstract class AA{
        abstract void eat();
    }

    private AA aa;

    public AA getAa() {
        return aa;
    }

    public void m1() {
        Dog dog = new Dog();
        class InnerArea extends AA{
            int n2;
            int n1;
            public void innerM1() {
                System.out.println("n1" + this.n1);
            }

            @Override
            void eat() {
                System.out.println("innerArea eat");
            }
        }

        InnerArea innerArea = new InnerArea();
        System.out.println("----" + innerArea.n2);
        aa = innerArea;
        //aa.eat();

    }

    // 成员内部类
    class Inner {

    }

    // 静态内部类
    static class InnerStatic {

    }


}

class Dog{

}
