package programming;


import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

class Course {
    private String name;
    private String category;
    private int reviewScore;
    private int noOfStudents;

    public Course(String name, String category, int reviewScore, int noOfStudents) {
        super();
        this.name = name;
        this.category = category;
        this.reviewScore = reviewScore;
        this.noOfStudents = noOfStudents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getReviewScore() {
        return reviewScore;
    }

    public void setReviewScore(int reviewScore) {
        this.reviewScore = reviewScore;
    }

    public int getNoOfStudents() {
        return noOfStudents;
    }

    public void setNoOfStudents(int noOfStudents) {
        this.noOfStudents = noOfStudents;
    }

    public String toString() {
        return name + ":" + noOfStudents + ":" + reviewScore;
    }

}

@SuppressWarnings("all")
public class FP04CustomClass {

    public static void main(String[] args) {
        List<Course> courses = List.of(new Course("Spring", "Framework", 98, 20000),
                new Course("Spring Boot", "Framework", 95, 18000), new Course("API", "Microservices", 97, 22000),
                new Course("Microservices", "Microservices", 96, 25000),
                new Course("FullStack", "FullStack", 91, 14000), new Course("AWS", "Cloud", 92, 21000),
                new Course("Azure", "Cloud", 99, 21000), new Course("Docker", "Cloud", 92, 20000),
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

        Comparator<Course> comparingByNoOfStudentsIncreasing = Comparator.comparingInt(o -> o.getNoOfStudents());




    }
}
