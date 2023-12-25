package programming.FP04;


import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// @SuppressWarnings("all")
public class FP04CustomClass {

    public static void main(String[] args) {
        List<Course> courses = List.of(
                new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000),
                new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000),
                new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000),
                new Course("Docker", "Cloud", 92, 20000),
                new Course("Kubernetes", "Cloud", 91, 20000));

        Predicate<Course> reviewScoreGreaterThan95Predicate
                = course -> course.getReviewScore() > 95;
        Predicate<Course> reviewScoreGreaterThan90Predicate
                = course -> course.getReviewScore() > 90;
        Predicate<Course> reviewScoreLessThan90Predicate
                = course -> course.getReviewScore() < 90;

        System.out.println(courses.stream().allMatch(reviewScoreGreaterThan95Predicate));
        System.out.println(courses.stream().noneMatch(reviewScoreGreaterThan90Predicate));
        System.out.println(courses.stream().anyMatch(reviewScoreLessThan90Predicate));
        System.out.println(courses.stream().anyMatch(reviewScoreGreaterThan95Predicate));

        /*Comparator<Course> comparingByNoOfStudentsIncreasing = new Comparator<Course>() {
            @Override
            public int compare(Course o1, Course o2) {
                return o1.getNoOfStudents() - o2.getNoOfStudents();
            }
        };*/

        /*Comparator<Course> comparingByNoOfStudentsIncreasing =
                (o1,o2) -> o1.getNoOfStudents() - o2.getNoOfStudents();*/


        /*Comparator<Course> comparingByNoOfStudentsIncreasing
                = Comparator.comparingInt(new ToIntFunction<Course>() {
            @Override
            public int applyAsInt(Course value) {
                return value.getNoOfStudents();
            }
        });*/


         /*Comparator<Course> comparingByNoOfStudentsIncreasing
                = Comparator.comparingInt(o -> o.getNoOfStudents());*/


        Comparator<Course> comparingByNoOfStudentsIncreasing
                = Comparator.comparingInt(Course::getNoOfStudents);

        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsIncreasing)
                        .collect(Collectors.toList()));
        //[FullStack:14000:91, Spring Boot:18000:95, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, AWS:21000:92, Azure:21000:99, API:22000:97, Microservices:25000:96]


        Comparator<Course> comparingByNoOfStudentsDecreasing
                = Comparator.comparingInt(Course::getNoOfStudents).reversed();

        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsDecreasing)
                        .collect(Collectors.toList()));
        //[Microservices:25000:96, API:22000:97, AWS:21000:92, Azure:21000:99, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]


        Comparator<Course> comparingByNoOfStudentsAndNoOfReviews
                = Comparator.comparingInt(Course::getNoOfStudents)
                .thenComparingInt(Course::getReviewScore).reversed();

        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .collect(Collectors.toList()));
        //[Microservices:25000:96, API:22000:97, Azure:21000:99, AWS:21000:92, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]


        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .limit(5)
                        .collect(Collectors.toList()));
        //[Microservices:25000:96, API:22000:97, Azure:21000:99, AWS:21000:92, Spring:20000:98]


        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .skip(3)
                        .collect(Collectors.toList()));
        //[AWS:21000:92, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95, FullStack:14000:91]


        System.out.println(
                courses.stream()
                        .sorted(comparingByNoOfStudentsAndNoOfReviews)
                        .skip(3)
                        .limit(5)
                        .collect(Collectors.toList()));
        //[AWS:21000:92, Spring:20000:98, Docker:20000:92, Kubernetes:20000:91, Spring Boot:18000:95]


        System.out.println(courses);
        //[Spring:20000:98, Spring Boot:18000:95, API:22000:97, Microservices:25000:96, FullStack:14000:91, AWS:21000:92, Azure:21000:99, Docker:20000:92, Kubernetes:20000:91]


        System.out.println(
                courses.stream()
                        .takeWhile(course -> course.getReviewScore() >= 95)
                        .collect(Collectors.toList()));
        //[Spring:20000:98, Spring Boot:18000:95, API:22000:97, Microservices:25000:96]


        System.out.println(
                courses.stream()
                        .dropWhile(course -> course.getReviewScore() >= 95)
                        .collect(Collectors.toList()));
        // dropWhile 操作会从流中删除满足指定条件的元素，但一旦遇到第一个不满足条件的元素，它就会停止删除并返回余下的元素
        //[FullStack:14000:91, AWS:21000:92, Azure:21000:99, Docker:20000:92, Kubernetes:20000:91]


        System.out.println(
                courses.stream()
                        .max(comparingByNoOfStudentsAndNoOfReviews));
        //Optional[FullStack:14000:91]


        System.out.println(
                courses.stream()
                        .min(comparingByNoOfStudentsAndNoOfReviews)
                        .orElse(new Course("Kubernetes", "Cloud", 91, 20000)));
        //Optional[Microservices:25000:96]
        //Microservices:25000:96


        System.out.println(
                courses.stream()
                        .filter(reviewScoreLessThan90Predicate)
                        .findFirst()); //Optional.empty


        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .findFirst()); //Optional[Spring:20000:98]


        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .findAny()); //Optional[Spring:20000:98]


        System.out.println(
                Arrays.toString(courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .mapToInt(Course::getNoOfStudents).toArray()));
        // [20000, 22000, 25000, 21000]


        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .mapToInt(course -> course.getNoOfStudents()).sum()); // 88000


        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .mapToInt(Course::getNoOfStudents).sum()); // 88000


        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .mapToInt(Course::getNoOfStudents)
                        .average());    //OptionalDouble[22000.0]


        System.out.println(
                courses.stream()
                        .filter(reviewScoreGreaterThan95Predicate)
                        .mapToInt(Course::getNoOfStudents)
                        .count());//4


        System.out.println(
                courses.stream()
                        .collect(Collectors.groupingBy(Course::getCategory)));
        // {
        // Cloud=[AWS:21000:92, Azure:21000:99, Docker:20000:92, Kubernetes:20000:91],
        // FullStack=[FullStack:14000:91],
        // Microservices=[API:22000:97, Microservices:25000:96],
        // Framework=[Spring:20000:98, Spring Boot:18000:95]
        // }


        System.out.println(
                courses.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Course::getCategory, Collectors.counting()
                                )
                        )
        );

        // {Cloud=4, FullStack=1, Microservices=2, Framework=2}


        System.out.println(
                courses.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Course::getCategory,
                                        Collectors.maxBy(Comparator.comparing(Course::getReviewScore))
                                )
                        )
        );
        // {
        // Cloud=Optional[Azure:21000:99],
        // FullStack=Optional[FullStack:14000:91],
        // Microservices=Optional[API:22000:97],
        // Framework=Optional[Spring:20000:98]
        // }


        System.out.println(
                courses.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Course::getCategory,
                                        Collectors.mapping(Course::getName, Collectors.toList())
                                )
                        )
        );
        // {
        // Cloud=[AWS, Azure, Docker, Kubernetes],
        // FullStack=[FullStack],
        // Microservices=[API, Microservices],
        // Framework=[Spring, Spring Boot]
        // }


        System.out.println(
                courses.stream()
                        .collect(
                                Collectors.groupingBy(
                                        Course::getCategory,
                                        Collectors.mapping(Course::getNoOfStudents, Collectors.toList())
                                )
                        )
        );
        // {
        // Cloud=[21000, 21000, 20000, 20000],
        // FullStack=[14000],
        // Microservices=[22000, 25000],
        // Framework=[20000, 18000]
        // }


        Predicate<Course> reviewScoreGreaterThan95Predicate2 = createPredicateWithCutoffReviewScore(95);
        Predicate<Course> reviewScoreGreaterThan90Predicate2 = createPredicateWithCutoffReviewScore(90);


    }

    private static Predicate<Course> createPredicateWithCutoffReviewScore(int cutoffReviewScore) {
        return course -> course.getReviewScore() > cutoffReviewScore;
    }
}
