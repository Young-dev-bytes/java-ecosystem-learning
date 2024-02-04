package programming.FP01;

import java.util.List;

public class FP01Exercises {
    public static void main(String[] args) {
        List<Integer> numbers = List.of(12, 9, 13, 4, 6, 2, 4, 12, 15);
        List<String> courses = List.of("Spring", "Spring Boot", "API", "Microservices", "AWS", "PCF", "Azure", "Docker", "Kubernetes");
        // printOddNumbersInListFunctional(numbers);
        // printAllCoursesIndividually(courses);
        // printCoursesContainSpring(courses);
        // printCoursesAtleast4Letters(courses);
        // printCubesOfOddNumbers(numbers);
        printNumsAndNameInEachCourse(courses);
    }


    private static void printOddNumbersInListFunctional(List<Integer> numbers) {
        /*numbers.stream()
                .forEach(FP01Exercises::printOdd);*/

        /*numbers.stream()
                .filter(FP01Exercises::isOdd)
                .forEach(System.out::println);*/

        numbers.stream()
                .filter(num -> num % 2 != 0)
                .forEach(System.out::println);

    }

    private static void printAllCoursesIndividually(List<String> courses) {
        /*courses.stream().forEach(System.out::println);*/
        courses.stream().forEach(System.out::println);
    }

    // Print Courses Containing the word "Spring"
    private static void printCoursesContainSpring(List<String> course) {

        /*course.stream()
                .filter(item -> FP01Exercises.isContainSpring(item))
                .forEach(System.out::println);*/

        /*course.stream()
                .filter(FP01Exercises::isContainSpring)
                .forEach(System.out::println);*/

        course.stream()
                .filter(item -> item.contains("Spring"))
                .forEach(System.out::println);

    }

    private static void printCoursesAtleast4Letters(List<String> course) {
        course.stream()
                .filter(item -> item.length() >= 4)
                .forEach(System.out::println);

    }

    // Print the cubes of odd numbers
    private static void printCubesOfOddNumbers(List<Integer> numbers) {
        /*numbers.stream()
                .map(num -> num * num * num)
                .forEach(System.out::println);*/
        numbers.stream()
                .map(FP01Exercises::cubesNums)
                .forEach(System.out::println);

    }

    // Print the number of characters in each course name
    private static void printNumsAndNameInEachCourse(List<String> courses) {
        courses.stream()
                .map(course -> course + " " + course.length())
                .forEach(System.out::println);
    }


    public static void printOdd(int number) {
        System.out.println(number);
    }

    public static boolean isOdd(int number) {
        return number % 2 != 0;
    }

    private static boolean isContainSpring(String course) {
        return course.contains("Spring");
    }

    private static Integer cubesNums(Integer number) {
        return number * number * number;
    }
}
