package java_basic._1106_exception.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 13:54
 */

public class Main {
    public static void main(String[] args) {
        int num1 = 10;
        int num2 = 0;
        int res = 0;
        try {
            res = num1/num2;
        } catch (Exception e) {
             e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println(res);
        System.out.println("程序继续执行...");
    }
}
