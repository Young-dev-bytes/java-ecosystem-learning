package java_basic._1028_dynamic_bind.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 16:39
 */

public class Test {
    public static void main(String[] args) {
        // Person [] personArr = new Person[5];
        Person[] personArr = new Person[]{new Person("per - rio"),
                new Student("stu - jack"), new Student("stu - mike"),
                new Teacher("tea - tom"), new Teacher("tea - lisa")};
        for (int i = 0; i < personArr.length; i++) {
            personArr[i].say();
        }
    }
}
