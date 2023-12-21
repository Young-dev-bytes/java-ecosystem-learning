package java_basic._1120_gernic.d;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/20 17:16
 */

public class Main_ {
    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.fly("e",12);

        // List<Object> list = new ArrayList<String>();

    }
}

class Animal<M, N> {


    public void run() {

    }

    public <T, R> void fly(T t, R r) {
        System.out.println(t.getClass());
        System.out.println(r.getClass());
    }

    public void fly(M m) {
    }

    public  void f1() {

    }


}
