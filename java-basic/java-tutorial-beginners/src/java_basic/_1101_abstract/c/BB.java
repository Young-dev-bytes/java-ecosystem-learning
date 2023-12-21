package java_basic._1101_abstract.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 19:27
 */

public class BB extends CC {

    public void job() {
        long num = 1;
        for (int i = 1; i <= 100000; i++) {
            num = i + num;
        }
        System.out.println(num);
    }
}
