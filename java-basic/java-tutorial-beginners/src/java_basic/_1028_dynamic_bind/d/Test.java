package java_basic._1028_dynamic_bind.d;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 16:39
 */

public class Test {
    public static void main(String[] args) {
        // Person [] personArr = new Person[5];

        Person[] personArr = new Person[]{
                new Person("per - 1", 10),
                new Student("stu - 1", 12, 12.4),
                new Student("stu - 2", 30, 34.9),
                new Teacher("tea - 1", 30, 4000),
                new Teacher("tea - 1", 40, 5000)
        };

        for (int i = 0; i < personArr.length; i++) {

            if (personArr[i] instanceof Student) {
                ((Student)personArr[i]).study();
            }
            if (personArr[i] instanceof Teacher) {
                ((Teacher) personArr[i]).teach();
            }

            System.out.println(personArr[i].say());
        }
    }
}
