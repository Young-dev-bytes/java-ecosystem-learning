package java_basic._1026_extends.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/26 16:46
 */

public class Test {
    public static void main(String[] args) {
        Pupil pupil = new Pupil();
        pupil.name = "小明";
        pupil.age = 10;
        pupil.testing();
        pupil.setScore(60);
        pupil.showInfo();

        Graduate graduate = new Graduate();
        graduate.name = "小红";
        graduate.age = 18;
        graduate.testing();
        graduate.setScore(90);
        graduate.showInfo();

    }
}
