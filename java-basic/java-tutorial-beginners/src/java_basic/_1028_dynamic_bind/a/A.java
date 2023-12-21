package java_basic._1028_dynamic_bind.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 15:34
 */

public class A {

    public int i = 10;

    public int sum() {
        // System.out.println(this instanceof A);
        return getI() + 10;
    }

    public int sum1() {
        return i + 10;
    }

    public int getI() {
        return i;
    }
}
