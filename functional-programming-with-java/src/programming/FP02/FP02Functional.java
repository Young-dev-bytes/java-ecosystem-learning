package programming.FP02;

import java.util.List;
import java.util.stream.Collectors;

public class FP02Functional {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15, 90);

        numbers.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());

        int sum = addListFunctional(numbers);

        System.out.println(sum);
    }

    private static int addListFunctional(List<Integer> numbers) {
        /*return numbers.stream()
                .reduce(0, (x, y) -> FP02Functional.sum(x, y));*/

        /*return numbers.stream()
                .reduce(0, FP02Functional::sum);*/

        /*return numbers.stream()
                .reduce(0, (x, y) -> x + y);*/

        /*return numbers.stream()
                .reduce(0, Integer::sum);*/

        return numbers.stream()
                .reduce(Integer.MIN_VALUE, (x, y) -> Integer.max(x, y));

    }

    private static int sum(Integer aggregate, Integer nextNumber) {
        System.out.println(aggregate + "-" + nextNumber);
        return aggregate + nextNumber;
    }


}
