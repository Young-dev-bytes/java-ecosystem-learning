package java_basic._1106_exception.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 16:04
 */

public class Exception01 {
    public static void main(String[] args) {
        System.out.println(method());

    }

    public static int method() {

        try {

            final String[] names = new String[3];
            if ("tom".equals(names[1])) {
                System.out.println(names[1]);
            } else {
                // System.out.println(names[3]);
            }
            return 1;

        } catch (ArrayIndexOutOfBoundsException e) {
            return 2;
        } catch (NullPointerException e) {
            return 3;
        } finally {
            return 4;
        }

    }
}
