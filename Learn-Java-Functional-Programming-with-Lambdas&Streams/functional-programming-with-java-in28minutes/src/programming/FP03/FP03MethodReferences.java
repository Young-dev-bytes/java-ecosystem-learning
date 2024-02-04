package programming.FP03;

import java.util.List;
import java.util.function.Supplier;

public class FP03MethodReferences {
    private static void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices",
                "AWS", "PCF", "Azure", "Docker",
                "Kubernetes");
        courses.stream()
                // .map(str -> str.toUpperCase())
                .map(String::toUpperCase)
                // .forEach(str -> System.out.println(str));
                // .forEach(System.out::println);
                .forEach(FP03MethodReferences::print);

        Supplier<Integer> supplier = new Supplier<>() {
            @Override
            public Integer get() {
                return null;
            }
        };

        Supplier<Integer> supplier1 = () -> null;
        // Supplier<String> supplier2 = () -> new String();
        Supplier<String> supplier2 = String::new;
        Supplier<Integer> supplier3 = () -> 2;
        Supplier<FP03MethodReferences> supplier4 = FP03MethodReferences::new;


    }
}
