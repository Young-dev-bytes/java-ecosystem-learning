package java_basic._1101_abstract.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 19:27
 */

public class AA extends CC {

    public void job() {
        long num = 0;
        for (int i = 1; i <= 10000000; i++) {
            num += i;
        }
        System.out.println(num);
    }
}
