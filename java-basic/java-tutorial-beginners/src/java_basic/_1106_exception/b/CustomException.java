package java_basic._1106_exception.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/7 09:20
 */

public class CustomException {
    public static void main(String[] args) {

        try {
            getAge();
        } catch (AgeException e) {
            e.printStackTrace();
            throw new RuntimeException("出现异常");
        }

        System.out.println("你的年龄是正确的");
    }

    private static void getAge() {
        int age = 810;

        if (!(age >= 18 && age <= 120)) {
            throw new AgeException("年龄需要在80 - 120之间...");
        }

        System.out.println("getAge...");
    }
}


class AgeException extends RuntimeException {

    public AgeException(String message) {
        super(message);
    }
}
