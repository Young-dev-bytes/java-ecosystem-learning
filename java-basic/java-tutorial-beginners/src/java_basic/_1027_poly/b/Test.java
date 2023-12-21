package java_basic._1027_poly.b;

import java_basic._1027_poly.b.animal.Animal;
import java_basic._1027_poly.b.animal.Dog;
import java_basic._1027_poly.b.food.Bone;
import java_basic._1027_poly.b.food.Fish;
import java_basic._1027_poly.b.food.Food;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 12:32
 */

public class Test {
    public static void main(String[] args) {
        /*
        Master master = new Master("小明");
        Dog dog = new Dog("qiqi");
        Bone bone = new Bone("排骨");
        master.feed(dog, bone);
         */

        Master master = new Master("Young");
        Animal dog = new Dog("Kiki");
        Bone bone = new Bone("delicious bones");
        Food fish = new Fish("小黄鱼");
        master.feed(dog, fish);
    }
}
