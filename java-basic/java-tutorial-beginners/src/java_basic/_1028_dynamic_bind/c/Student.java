package java_basic._1028_dynamic_bind.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 16:37
 */

public class Student extends Person {

    private double score;

    public Student(String name, int age, double score) {
        super(name, age);
        this.score = score;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    @Override
    public String say() {
        return super.say() + " score=" + score;
    }
}
