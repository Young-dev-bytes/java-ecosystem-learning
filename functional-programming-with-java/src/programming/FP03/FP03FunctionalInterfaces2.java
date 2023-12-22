package programming.FP03;

import java.util.List;
import java.util.Random;
import java.util.function.*;

public class FP03FunctionalInterfaces2 {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        Predicate<Integer> isEvenPredicate = (Integer x) -> x % 2 == 0;

        Function<Integer, Integer> squareFunction = x -> x * x;

        Function<Integer, String> stringOutputFunction = x -> x + "";

        // Consumer<Integer> sysoutConsumer = x -> System.out.println(x);
        Consumer<Integer> sysoutConsumer = System.out::println;

        BinaryOperator<Integer> sumBinaryOperator = (x, y) -> x + y;


        /*Supplier<Integer> supplier = new Supplier<>() {
            @Override
            public Integer get() {
                return null;
            }
        };*/

        // No input > Return Something
        Supplier<Integer> supplier2 = () -> null;
        Supplier<Integer> randomIntegerSupplier = () -> {
            Random random = new Random();
            return random.nextInt(1000);
        };
        System.out.println(randomIntegerSupplier.get());


        UnaryOperator<Integer> unaryOperator = x -> 3 * x;
        System.out.println(unaryOperator.apply(10));


        BiPredicate<Integer, String> biPredicate = (number, str) -> {
            return number < 10 && str.length() > 5;
        };
        System.out.println(biPredicate.test(10, "young"));


        BiFunction<Integer, String, String> biFunction = (number, str) -> number + " - " + str;
        System.out.println(biFunction.apply(10, "Young"));


        BiConsumer<Integer, String> biConsumer = (number, str) -> System.out.println(number + " - " + str);
        biConsumer.accept(100, "Young");

        BinaryOperator<Integer> binaryOperator = (x, y) -> x + y;

        //IntBinaryOperator
        //IntConsumer
        //IntFunction
        //IntPredicate
        //IntSupplier
        //IntToDoubleFunction
        //IntToLongFunction
        //IntUnaryOperator

        numbers.stream()
                .filter(isEvenPredicate)
                .map(squareFunction)
                .forEach(sysoutConsumer);

        int sum = numbers.stream()
                .reduce(0, sumBinaryOperator);


    }
}
