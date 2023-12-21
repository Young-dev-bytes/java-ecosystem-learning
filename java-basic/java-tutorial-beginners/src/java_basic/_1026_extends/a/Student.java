package java_basic._1026_extends.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/26 17:12
 */

public class Student {

    public String name;
    public int age;
    public double score;

    public void setScore(double score) {
        this.score = score;
    }

    public void showInfo() {
        System.out.println("学生名: " + name + "年龄: " + age + " 成绩: " + score);
    }

}
