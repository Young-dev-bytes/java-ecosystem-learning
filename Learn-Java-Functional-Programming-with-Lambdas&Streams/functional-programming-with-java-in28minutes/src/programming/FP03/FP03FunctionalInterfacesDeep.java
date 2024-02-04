package programming.FP03;

import java.util.List;
import java.util.function.Predicate;

public class FP03FunctionalInterfacesDeep {
    public static void main(String[] args) {


        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);

        // Predicate<Integer> filterPredicate = num -> num % 2 == 0;
        Predicate<Integer> filterPredicate = new Predicate<Integer>() {
            @Override
            public boolean test(Integer num) {
                return num % 2 == 0;
            }
        };

        /*Predicate<Integer> filterPredicate = (num) -> {
            return num % 2 == 0;
        };*/

        /*
         *
         * Lambda can be replaced with call qualifier
         * Inspection info: Reports method references or lambda expressions
         * which point to a method of their own functional interface type
         * and hence can be replaced with their qualifiers, like
         * SwingUtilities.invokeLater(r::run);
         * SwingUtilities.invokeAndWait(() -> r.run());
         * can be replaced with
         * SwingUtilities.invokeLater(r);
         * SwingUtilities.invokeAndWait(r);
         */

        numbers.stream()
                // .filter(filterPredicate);
                // .filter(filterPredicate::test);
                .filter(num -> filterPredicate.test(num));
    }

}
