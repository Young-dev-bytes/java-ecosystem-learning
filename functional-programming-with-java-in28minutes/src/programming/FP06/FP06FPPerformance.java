package programming.FP06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FP06FPPerformance {

    public static void main(String[] args) {


        List<String> courses1 = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");


        System.out.println(courses1
                .stream()
                .filter(course1 -> course1.length() > 11)
                .map(String::toUpperCase)
                .findFirst());
        // Optional[MICROSERVICES]




        // Intermediate Stream Operations are lazy. [中间流操作是惰性的]
        System.out.println(courses1
                .stream()
                .peek(System.out::println)
                .filter(course1 -> course1.length() > 11)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .findFirst());
        // Spring
        // Spring Boot
        // API
        // Microservices
        // MICROSERVICES
        // Optional[MICROSERVICES]



        System.out.println(courses1
                .stream()
                .filter(course1 -> course1.length() > 11)
                .peek(System.out::println)
                .map(String::toUpperCase)
                .peek(System.out::println)
                .findFirst());
        // Microservices
        // MICROSERVICES
        // Optional[MICROSERVICES]



        /*They are unmodifiable.
        Elements cannot be added, removed, or replaced.
        Calling any mutator method on the List will always
        cause UnsupportedOperationException to be thrown. However, i
        f the contained elements are themselves mutable,
        this may cause the List's contents to appear to change.*/

        /*! Exception in thread "main" java.lang.UnsupportedOperationException**/
        // courses1.replaceAll(str -> str.toUpperCase());


        List<String> modifiableCourses = new ArrayList<>(
                List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes"));
        modifiableCourses.replaceAll(String::toUpperCase);
        System.out.println(modifiableCourses);

        modifiableCourses.removeIf(course -> course.length() < 6);
        System.out.println(modifiableCourses);

        Runnable runnable = ()-> {
            IntStream.range(0,100)
                    .forEach(i -> System.out.println(Thread.currentThread().getId() + ":" + i));
        };
        runnable.run();
    }
}
