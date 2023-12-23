package programming.FP06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class FP06JoiningAndFlapMap {

    public static void main(String[] args) {

        List<String> courses = List.of(
                "Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        System.out.println(courses.stream().collect(Collectors.joining(" ")));
        // Spring Spring Boot API Microservices AWS PCF Azure Docker Kubernetes

        System.out.println(courses.stream().collect(Collectors.joining(",")));
        // Spring,Spring Boot,API,Microservices,AWS,PCF,Azure,Docker,Kubernetes

        System.out.println(Arrays.toString("Spring".split("")));
        // [S, p, r, i, n, g]

        System.out.println(courses.stream()
                .map(course -> course.split(""))
                .collect(Collectors.toList()));
        // [[Ljava.lang.String;@4517d9a3,
        // [Ljava.lang.String;@372f7a8d,
        // [Ljava.lang.String;@2f92e0f4,
        // [Ljava.lang.String;@28a418fc,
        // [Ljava.lang.String;@5305068a,
        // [Ljava.lang.String;@1f32e575,
        // [Ljava.lang.String;@279f2327,
        // [Ljava.lang.String;@2ff4acd0,
        // [Ljava.lang.String;@54bedef2]


        courses.stream()
                .map(course -> course.split(""))
                .forEach(str -> System.out.println(Arrays.toString(str)));
        // [S, p, r, i, n, g]
        // [S, p, r, i, n, g,  , B, o, o, t]
        // [A, P, I]
        // [M, i, c, r, o, s, e, r, v, i, c, e, s]
        // [A, W, S]
        // [P, C, F]
        // [A, z, u, r, e]
        // [D, o, c, k, e, r]
        // [K, u, b, e, r, n, e, t, e, s]


        System.out.println(courses.stream()
                .map(course -> course.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList()));
        // [S, p, r, i, n, g, S, p, r, i, n, g,  , B, o, o, t, A, P, I, M, i, c, r, o, s, e, r, v, i, c, e, s, A, W, S, P, C, F, A, z, u, r, e, D, o, c, k, e, r, K, u, b, e, r, n, e, t, e, s]

        System.out.println(courses.stream()
                .map(course -> course.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList()));
        // [S, p, r, i, n, g,  , B, o, t, A, P, I, M, c, s, e, v, W, C, F, z, u, D, k, K, b]


        List<String> courses1 = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        List<String> courses2 = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");

        // Expected output - Tuples with same length : [[Spring,Docker],[API,AWS],[API,PCF],[AWS,API]

        /*System.out.println(courses1.stream()
                .flatMap(course1 -> courses2
                        .stream()
                        .map(course2 -> List.of(course1, course2)))
                .collect(Collectors.toList()));*/
        // [[Spring, Spring], [Spring, Spring Boot], [Spring, API], [Spring, Microservices], [Spring, AWS], [Spring, PCF], [Spring, Azure], [Spring, Docker], [Spring, Kubernetes], [Spring Boot, Spring], [Spring Boot, Spring Boot], [Spring Boot, API], [Spring Boot, Microservices], [Spring Boot, AWS], [Spring Boot, PCF], [Spring Boot, Azure], [Spring Boot, Docker], [Spring Boot, Kubernetes], [API, Spring], [API, Spring Boot], [API, API], [API, Microservices], [API, AWS], [API, PCF], [API, Azure], [API, Docker], [API, Kubernetes], [Microservices, Spring], [Microservices, Spring Boot], [Microservices, API], [Microservices, Microservices], [Microservices, AWS], [Microservices, PCF], [Microservices, Azure], [Microservices, Docker], [Microservices, Kubernetes], [AWS, Spring], [AWS, Spring Boot], [AWS, API], [AWS, Microservices], [AWS, AWS], [AWS, PCF], [AWS, Azure], [AWS, Docker], [AWS, Kubernetes], [PCF, Spring], [PCF, Spring Boot], [PCF, API], [PCF, Microservices], [PCF, AWS], [PCF, PCF], [PCF, Azure], [PCF, Docker], [PCF, Kubernetes], [Azure, Spring], [Azure, Spring Boot], [Azure, API], [Azure, Microservices], [Azure, AWS], [Azure, PCF], [Azure, Azure], [Azure, Docker], [Azure, Kubernetes], [Docker, Spring], [Docker, Spring Boot], [Docker, API], [Docker, Microservices], [Docker, AWS], [Docker, PCF], [Docker, Azure], [Docker, Docker], [Docker, Kubernetes], [Kubernetes, Spring], [Kubernetes, Spring Boot], [Kubernetes, API], [Kubernetes, Microservices], [Kubernetes, AWS], [Kubernetes, PCF], [Kubernetes, Azure], [Kubernetes, Docker], [Kubernetes, Kubernetes]]


        /*System.out.println(courses1.stream()
                .flatMap(course1 -> courses2
                        .stream()
                        .map(course2 -> List.of(course1, course2)))
                .filter(list -> list.get(0).equals(list.get(1)))
                .collect(Collectors.toList()));*/
        // [[Spring, Spring], [Spring Boot, Spring Boot], [API, API], [Microservices, Microservices], [AWS, AWS], [PCF, PCF], [Azure, Azure], [Docker, Docker], [Kubernetes, Kubernetes]]


        System.out.println(courses1.stream()
                .flatMap(course1 -> courses2
                        .stream()
                        .map(course2 -> List.of(course1, course2)))
                .filter(list -> !list.get(0).equals(list.get(1)))
                .collect(Collectors.toList()));
        // [[Spring, Spring Boot], [Spring, API], [Spring, Microservices], [Spring, AWS], [Spring, PCF], [Spring, Azure], [Spring, Docker], [Spring, Kubernetes], [Spring Boot, Spring], [Spring Boot, API], [Spring Boot, Microservices], [Spring Boot, AWS], [Spring Boot, PCF], [Spring Boot, Azure], [Spring Boot, Docker], [Spring Boot, Kubernetes], [API, Spring], [API, Spring Boot], [API, Microservices], [API, AWS], [API, PCF], [API, Azure], [API, Docker], [API, Kubernetes], [Microservices, Spring], [Microservices, Spring Boot], [Microservices, API], [Microservices, AWS], [Microservices, PCF], [Microservices, Azure], [Microservices, Docker], [Microservices, Kubernetes], [AWS, Spring], [AWS, Spring Boot], [AWS, API], [AWS, Microservices], [AWS, PCF], [AWS, Azure], [AWS, Docker], [AWS, Kubernetes], [PCF, Spring], [PCF, Spring Boot], [PCF, API], [PCF, Microservices], [PCF, AWS], [PCF, Azure], [PCF, Docker], [PCF, Kubernetes], [Azure, Spring], [Azure, Spring Boot], [Azure, API], [Azure, Microservices], [Azure, AWS], [Azure, PCF], [Azure, Docker], [Azure, Kubernetes], [Docker, Spring], [Docker, Spring Boot], [Docker, API], [Docker, Microservices], [Docker, AWS], [Docker, PCF], [Docker, Azure], [Docker, Kubernetes], [Kubernetes, Spring], [Kubernetes, Spring Boot], [Kubernetes, API], [Kubernetes, Microservices], [Kubernetes, AWS], [Kubernetes, PCF], [Kubernetes, Azure], [Kubernetes, Docker]]


        System.out.println(courses1.stream()
                .flatMap(course1 -> courses2
                        .stream()
                        .filter(course2 -> course2.length() == course1.length())
                        .map(course2 -> List.of(course1, course2)))
                .filter(list -> !list.get(0).equals(list.get(1)))
                .collect(Collectors.toList()));
        // [[Spring, Docker], [API, AWS], [API, PCF], [AWS, API], [AWS, PCF], [PCF, API], [PCF, AWS], [Docker, Spring]]


        System.out.println(courses1
                .stream()
                .filter(course1 -> course1.length() > 11)
                .map(String::toUpperCase)
                .findFirst());
        // Optional[MICROSERVICES]



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
        //         Microservices
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
