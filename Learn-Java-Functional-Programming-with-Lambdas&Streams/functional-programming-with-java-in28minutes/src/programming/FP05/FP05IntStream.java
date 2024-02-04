package programming.FP05;

import java.math.BigInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class FP05IntStream {

    public static void main(String[] args) {

        /*IntStream.range(1, 10).forEach(System.out::println);
        System.out.println(IntStream.range(1, 10).sum());
        IntStream.rangeClosed(1,10).forEach(System.out::println);
        System.out.println(IntStream.rangeClosed(1, 10).sum());*/


        /*IntStream.iterate(1, new IntUnaryOperator() {
            @Override
            public int applyAsInt(int operand) {
                return operand + 2;
            }
        }).limit(3).forEach(System.out::println);*/
        // IntStream.iterate(1, e -> e + 2).limit(10).forEach(System.out::println);
        /*System.out.println(IntStream.iterate(1, e -> e + 2).limit(10).peek(System.out::println).sum());
        System.out.println(IntStream.iterate(2, e -> e + 2).limit(10).peek(System.out::println).sum());
        System.out.println(IntStream.iterate(2, e -> e * 2).limit(10).peek(System.out::println).sum());*/
        /*System.out.println(IntStream.iterate(2, e -> e * 2).limit(10).boxed().collect(Collectors.toList()));

        System.out.println(Integer.MAX_VALUE);
        System.out.println(Long.MAX_VALUE);

        System.out.println(IntStream.rangeClosed(1, 50).reduce(1,
                (x, y) -> {
                    return x * y;
                }));*/

        System.out.println(LongStream.rangeClosed(1, 50).reduce(1L, (x, y) -> x * y));
        System.out.println(LongStream.rangeClosed(1, 10).reduce(1L, (x, y) -> x * y));
        System.out.println(LongStream.rangeClosed(1, 20).reduce(1L, (x, y) -> x * y));
        System.out.println(LongStream.rangeClosed(1, 30).reduce(1L, (x, y) -> x * y));

        System.out.println(LongStream.rangeClosed(1, 50)
                .mapToObj(BigInteger::valueOf)
                .reduce(BigInteger.ONE, BigInteger::multiply));

    }
}
