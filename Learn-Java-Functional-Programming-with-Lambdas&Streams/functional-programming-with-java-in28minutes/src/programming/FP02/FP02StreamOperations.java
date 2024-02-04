package programming.FP02;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FP02StreamOperations {

    public static void main(String[] args) {

        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        List<String> courses = List.of(
                "Spring", "Spring Boot", "API",
                "Microservices", "AWS", "PCF", "Azure",
                "Docker", "Kubernetes");


        numbers.stream().distinct().forEach(System.out::println);

        numbers.stream().sorted().forEach(System.out::println);

        numbers.stream().distinct().sorted().forEach(System.out::println);


        List<String> sortedList = sortedByLength(courses);
        System.out.println(sortedList);
    }

    public static List<String> sortedByAZ(List<String> courses) {
        return courses.stream()
                .sorted(new Comparator<>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.charAt(0) - o2.charAt(0);
                    }
                })
                .collect(Collectors.toList());
    }

    public static List<String> sortedByZA(List<String> courses) {
        return courses.stream()
                .sorted((o1, o2) -> o2.charAt(0) - o1.charAt(0))
                .collect(Collectors.toList());
        /*return courses.stream()
                .sorted(new Comparator<>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o2.charAt(0) - o1.charAt(0);
                    }
                })
                .collect(Collectors.toList());*/
    }

    public static List<String> sortedByLength(List<String> courses) {
        /*return courses.stream()
                .sorted(Comparator.comparingInt(String::length))
                .collect(Collectors.toList());*/

        /*return courses.stream()
                .sorted((o1, o2) -> o1.length() - o2.length())
                .collect(Collectors.toList());*/

        /*return courses.stream()
                .sorted(Comparator.comparing(str -> str.length()))
                .collect(Collectors.toList());*/

        /*return courses.stream()
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());*/

        courses.stream()
                .sorted(Comparator.reverseOrder());

        return courses.stream()
                .sorted(new Comparator<>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.length() - o2.length();
                    }
                })
                .collect(Collectors.toList());
    }

}
