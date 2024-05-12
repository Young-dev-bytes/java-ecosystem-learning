package programming.FP01;

import java.util.List;

/**
 * numbers.stream()：将给定的整数列表（List<Integer>）转换为一个流（Stream），
 * 这使得我们可以使用流操作对列表中的元素进行处理。
 * <p>
 * forEach(System.out::println)：
 * 对流中的每个元素执行指定的操作。
 * 在这里，操作是通过方法引用 System.out::println 来实现的。
 * 这表示对于流中的每个元素，都调用 System.out.println 方法打印该元素。
 * <p>
 * 因此，整个方法的目的是通过使用函数式编程的方式，
 * 使用Java 8引入的Stream API来遍历整数列表并打印每个元素。这种方法比传统的循环更为简洁，并且更符合函数式编程的风格。
 * <p>
 * <p>
 * <p>
 * 什么是方法引用，为什么有::这个?
 * 方法引用（Method Reference）是Java中一种简化Lambda表达式的语法糖。
 * 它允许你直接引用已经存在的方法，而不需要重新实现这个方法。方法引用的语法使用 :: 符号。
 * <p>
 * 方法引用有几种形式，具体取决于被引用方法的类型。
 * 在你提供的代码中，使用的是对象的实例方法引用，
 * 格式为 对象::方法。具体来说，System.out::println 表示引用 System.out 对象的 println 方法。
 * <p>
 * 为了更好地理解为什么会有 :: 这个符号，让我们看一个简单的例子。考虑以下Lambda表达式：
 * numbers.forEach(n -> System.out.println(n));
 * <p>
 * 上述Lambda表达式使用了箭头符号 ->，
 * 表示将参数 n 传递给 System.out.println(n) 这个方法。而使用方法引用的方式，可以简写为：
 * numbers.forEach(System.out::println);
 * <p>
 * 这里的 :: 就是一种简化语法，它告诉编译器“我要引用一个方法，
 * 而不是提供一个Lambda表达式”，这使得代码更为简洁和易读。
 * <p>
 * 方法引用可以分为四种类型：
 * <p>
 * 静态方法引用：ClassName::staticMethodName
 * 实例方法引用：instance::instanceMethodName
 * 特定类型方法引用：TypeName::methodName
 * 构造方法引用：ClassName::new
 */

public class FP01Functional {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        printAllNumbersInListFunctional(numbers);
        // printEvenNumbersInListFunctional(numbers);
        printSquaresNumbersInListFunctional(numbers);
    }

    private static void printT(int number) {
        System.out.println(number);
    }

    private static boolean isEven(int number) {
        return number % 2 == 0;
    }

    private static void printAllNumbersInListFunctional(List<Integer> numbers) {

        numbers.stream().forEach(a -> FP01Functional.printT(a));
        // numbers.stream().forEach(new FP01Functional()::printT); //printT is not a static method
        // numbers.stream().forEach(FP01Functional::printT);
        numbers.stream().forEach(System.out::println);
    }

    private static void printEvenNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(FP01Functional::isEven) // Filter - Only Allow Even Numbers
                .forEach(System.out::println);

        numbers.stream()
                .filter(num -> FP01Functional.isEven(num)) // Filter - Only Allow Even Numbers
                .forEach(System.out::println);

        numbers.stream()
                .filter(num -> num % 2 == 0) // Filter - Only Allow Even Numbers , lamdba expression
                .forEach(System.out::println);
    }

    private static void printSquaresNumbersInListFunctional(List<Integer> numbers) {
        numbers.stream()
                .filter(num -> num % 2 == 0)
                .map(num -> num * num)
                .forEach(System.out::println);
    }
}
