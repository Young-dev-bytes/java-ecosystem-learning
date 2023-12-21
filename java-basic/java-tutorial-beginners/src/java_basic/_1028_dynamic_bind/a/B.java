package java_basic._1028_dynamic_bind.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 15:36
 */

public class B extends A{

    public int i = 20;

    /*
    public int sum() {
        return i + 20;
    }
     */

    public int getI() {
        return i;
    }

    /*
    public int sum1() {
        return i + 10;
    }
     */
}
