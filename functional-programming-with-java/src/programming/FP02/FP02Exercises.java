package programming.FP02;

import java.util.List;
import java.util.stream.Collectors;

public class FP02Exercises {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        List<String> courses = List.of(
                "Spring", "Spring Boot", "API",
                "Microservices", "AWS", "PCF", "Azure",
                "Docker", "Kubernetes");


        // Exercise 7 : Square every number in a list and find the sum of squares
        Integer squareSum = numbers.stream()
                .map(num -> num * num)
                .reduce(0, Integer::sum);


        // Exercise 8 : Cube every number in a list and find the sum of cubes
        Integer cubeSum = numbers.stream()
                .map(num -> num * num * num)
                .reduce(0, Integer::sum);

        // Exercise 9 : Find Sum of Odd Numbers in a list
        Integer oddSum = numbers.stream()
                .filter(num -> num % 2 != 0)
                .reduce(0, Integer::sum);

        System.out.println(squareSum);
        System.out.println(cubeSum);
        System.out.println(oddSum);


        // Exercise 10 : Create a List with Even Numbers Filtered from the Numbers List
        List<Integer> evenList = numbers.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toList());

        // Exercise 11 : Create a List with lengths of all course titles.
        List<Integer> courseLengthList = courses.stream()
                // .map(course -> course.length())
                .map(String::length)
                .collect(Collectors.toList());
    }

}
