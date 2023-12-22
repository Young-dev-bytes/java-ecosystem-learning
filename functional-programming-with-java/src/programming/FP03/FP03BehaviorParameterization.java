package programming.FP03;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class FP03BehaviorParameterization {
    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);


        filterAndPrint(numbers, new Predicate<>() {
            @Override
            public boolean test(Integer number) {
                return number % 2 == 0;
            }
        });

        filterAndPrint(numbers, (number) -> number % 2 == 0);
        filterAndPrint(numbers, (number) -> number % 2 != 0);
        filterAndPrint(numbers, (number) -> number % 3 == 0);


        List<Integer> mapAndCreateNewList = mapAndCreateNewList(numbers, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer number) {
                return number * number;
            }
        });
        System.out.println(mapAndCreateNewList);
    }

    private static void filterAndPrint(List<Integer> numbers, Predicate<? super Integer> predicate) {
        numbers.stream()
                .filter(predicate)
                // .filter(predicate::test)
                // .filter(num -> predicate.test(num))
                .forEach(System.out::println);
    }

    private static List<Integer> mapAndCreateNewList(List<Integer> numbers,
                                                     Function<Integer, Integer> function) {
        return numbers.stream()
                .map(function)
                // .map(function::apply)
                // .map(num ->function.apply(num))
                .collect(Collectors.toList());
    }
}
