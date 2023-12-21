package java_basic._1027_poly.b;

import java_basic._1027_poly.b.animal.Animal;
import java_basic._1027_poly.b.food.Food;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/27 17:18
 */

public class Master {

    private String name;

    public Master(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void feed(Animal animal, Food food) {
        System.out.println("主人 " + name + " 给 " + animal.getName() +  " 吃 " + food.getName());
    }



}
