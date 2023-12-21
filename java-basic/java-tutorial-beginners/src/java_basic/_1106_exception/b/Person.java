package java_basic._1106_exception.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/7 09:59
 */

public class Person {

    public static void main(String[] args) {

        try {
            Person person = new Person();
            person = null;
            System.out.println(person.equals("123"));
            int res = 10 / 0;
        } catch (NullPointerException e) {
            System.out.println("空指针：" + e.getMessage());
            throw new RuntimeException("error1");
        } catch (ArithmeticException e) {
            System.out.println("算数异常：" + e.getMessage());
            throw new RuntimeException("error2");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("error3");
        }


        System.out.println("keep going...");

    }
}
