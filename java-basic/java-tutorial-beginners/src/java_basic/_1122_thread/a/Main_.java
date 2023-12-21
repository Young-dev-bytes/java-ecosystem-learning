package java_basic._1122_thread.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/22 10:49
 */

// @SuppressWarnings("all")
public class Main_ {
    public static void main(String[] args) {

        Runtime runtime = Runtime.getRuntime();
        int i = runtime.availableProcessors();
        System.out.println(i);


    }
}
