package java_basic._1106_exception.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/7 09:38
 */

public class ReturnExceptionDemo {

    static void methodA() {
        try {
            System.out.println("进入方法A");
            // throw new RuntimeException("制造异常");
        } finally {
            System.out.println("用A方法的finally...");
        }
    }

    static void methodB() {

        try {
            System.out.println("进入方法B");
            return;
        } finally {
            System.out.println("用B方法的finally...");

        }
    }

    public static void main(String[] args) {

        try {
            ReturnExceptionDemo.methodA();
            throw new ArrayIndexOutOfBoundsException("下标越界异常");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        ReturnExceptionDemo.methodB();
    }
}
