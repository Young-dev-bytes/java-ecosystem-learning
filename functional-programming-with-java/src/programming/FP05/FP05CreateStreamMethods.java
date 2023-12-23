package programming.FP05;

import java.util.Arrays;
import java.util.stream.Stream;

public class FP05CreateStreamMethods {

    public static void main(String[] args) {

        // create stream by Stream.of
        System.out.println(Stream.of(12, 9, 13, 4, 6, 2, 4, 12, 15).count());

        System.out.println(Stream.of(12, 9, 13, 4, 6, 2, 4, 12, 15).reduce(0, Integer::sum));

        System.out.println(Stream.of(12, 9, 13, 4, 6, 2, 4, 12, 15));

        // create primitive stream by Arrays.stream(new int[]{})
        // System.out.println(Arrays.stream(new int[]{1, 2}));

        int [] numberArray = {12, 9, 13, 4, 6, 2, 4, 12, 15};
        System.out.println(Arrays.stream(numberArray));
        System.out.println(Arrays.stream(numberArray).count());
        System.out.println(Arrays.stream(numberArray).sum());
        System.out.println(Arrays.stream(numberArray).average());
        System.out.println(Arrays.stream(numberArray).min());
        System.out.println(Arrays.stream(numberArray).max());

        System.out.println(Arrays.stream(numberArray).average().stream());
    }
}
