package programming;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class FP03FunctionalInterfaces {
    public static void main(String[] args) {


        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        // Predicate<Integer> integerPredicate = num -> num % 2 == 0;
        /*Predicate<Integer> integerPredicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                return num % 2 == 0;
            }
        };*/

        Predicate<Integer> filterPredicate = (num) -> {
            return num % 2 == 0;
        };


        // Function<Integer, Integer> integerIntegerFunction = num -> num * num;
        /*Function<Integer, Integer> mapFunction = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer num) {
                return num * num;
            }
        };*/
        Function<Integer, Integer> mapFunction = (num) -> {
            return num * num;
        };
        // Consumer<Integer> printlnConsumer = System.out::println;
        /*Consumer<Integer> printlnConsumer = new Consumer<Integer>() {
            @Override
            public void accept(Integer number) {
                System.out.println(number);
            }
        };*/
        // Consumer<Integer> printlnConsumer = number -> System.out.println(number);
        // Consumer<Integer> printlnConsumer = number -> FP03FunctionalInterfaces.printT(number);
        Consumer<Integer> printlnConsumer = FP03FunctionalInterfaces::printT;
        numbers.stream()
                .filter(filterPredicate)
                .map(mapFunction)
                .forEach(printlnConsumer);


        // BinaryOperator<Integer> binaryOperator = Integer::sum;
        /*BinaryOperator<Integer> binaryOperator = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer aggregate, Integer nextNumber) {
                return aggregate + nextNumber;
            }
        };*/
        BinaryOperator<Integer> binaryOperator = (aggregate, nextNumber) -> {
            return aggregate + nextNumber;
        };
        // BinaryOperator<Integer> binaryOperator = (aggregate, nextNumber) -> aggregate + nextNumber;
        numbers.stream()
                .filter(filterPredicate)
                .map(mapFunction)
                // .reduce(0, (x,y) -> x + y);
                .reduce(0, binaryOperator);
    }

    public static void printT(Integer number) {
        System.out.println(number);
    }
}
