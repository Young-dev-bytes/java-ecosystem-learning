package java_basic._1027_poly.d;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 14:04
 */

public class PolyDetails {
    public static void main(String[] args) {
        Animal animal = new Cat();
        Object obj = new Cat();
        animal.eat();
        Cat cat = (Cat) animal;
        cat.catchMouse();
        Dog dog = (Dog) animal;

        int i = 8;



    }
}
