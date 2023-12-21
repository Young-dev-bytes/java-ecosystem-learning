package java_basic._1027_poly.a;

import java_basic._1027_poly.a.animal.Animal;
import java_basic._1027_poly.a.food.Food;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/27 16:55
 */

public class Master {

    public void feed(Food food, Animal animal) {
        System.out.println( animal.getName() + "喜欢吃 " + food);
    }

}
