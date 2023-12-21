package java_basic._1102_innerclass.e;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/3 12:40
 */

public class InnerExercise {
    public static void main(String[] args) {
        IL il = new IL() {
            @Override
            public void show() {
                System.out.println("show call...");
            }
        };
        f1(il);

        f1(new IL() {
            @Override
            public void show() {
                System.out.println("show call 111...");
            }
        });

        f1(() -> System.out.println("show call 111..."));

        // Picture picture = new Picture();
        f1(new Picture());

        f1(new IL() {
            @Override
            public void show() {
                System.out.println("emmmm....");
            }
        });

        f1(() -> System.out.println("emmmm...."));
    }

    public static void f1(IL il) {
        il.show();
    }
}

interface IL {
    void show();
}

class Picture implements IL{

    @Override
    public void show() {
        System.out.println("pictures show...");
    }
}
