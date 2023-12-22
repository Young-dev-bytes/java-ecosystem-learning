package programming.FP03;

import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FP03Exercises {
    @SuppressWarnings("all")
    public static void main(String[] args) {


        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        // Exercise 12 : Find Functional Interface behind the second argument of reduce method.
        // Create an implementation for the Functional Interface.

        BinaryOperator<Integer> binaryOperator = new BinaryOperator<Integer>() {
            @Override
            public Integer apply(Integer aggregate, Integer nextNum) {
                return aggregate + nextNum;
            }
        };
        // int sum = numbers.stream() .reduce(0, binaryOperator);
        // int sum = numbers.stream() .reduce(0, binaryOperator::apply);
        int sum = numbers.stream().reduce(0, (x, y) -> x + y);


        // Exercise 13 : Do Behavior Parameterization for the mapping logic.
        List<Integer> squaredNumbers = mapAndCreateNewList(numbers, x -> x * x);
        List<Integer> cubedNumbers = mapAndCreateNewList(numbers, x -> x * x * x);
        List<Integer> doubleNumbers = mapAndCreateNewList(numbers, x -> x + x);
        System.out.println(squaredNumbers);
    }

    private static List<Integer> mapAndCreateNewList(List<Integer> numbers, Function<Integer, Integer> function) {
        return numbers.stream()
                .map(function)
                .collect(Collectors.toList());
    }
}
