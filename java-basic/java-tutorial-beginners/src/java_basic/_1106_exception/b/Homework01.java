package java_basic._1106_exception.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/7 10:27
 */

public class Homework01 {
    public static void main(String[] args) {

        try {
            testException(args);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
        System.out.println("keep going 1...");
    }

    private static void testException(String[] args)
        /*throws ArrayIndexOutOfBoundsException, NumberFormatException, ArithmeticException*/ {
        try {
            if (args.length != 2) {
                throw new ArrayIndexOutOfBoundsException("参数个数不对");
            }

            int n1 = Integer.parseInt(args[0]);
            int n2 = Integer.parseInt(args[1]);

            double res = cal(n1, n2);

            System.out.println("计算结果是= " + res);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            throw new ArrayIndexOutOfBoundsException("0000");
        } catch (NumberFormatException e) {
            System.out.println("参数格式不正确,需要输出整数" + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("算数异常, 出现了除0的异常");
        }

        System.out.println("keep going 2...");

    }

    public static int cal(int n1, int n2) {
        return n1 / n2;
    }
}
