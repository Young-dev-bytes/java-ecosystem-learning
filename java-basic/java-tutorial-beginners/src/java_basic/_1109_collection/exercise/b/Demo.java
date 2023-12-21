package java_basic._1109_collection.exercise.b;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/19 16:33
 */

public class Demo {
    public static void main(String[] args) {

        List lists = new ArrayList();
        lists.add(new Car("宝马",400000d));
        lists.add(new Car("宾利",500000d));

        System.out.println(lists);
        //lists.remove(2);

        // System.out.println(lists.contains(new Car("宝马",400000d)));
        System.out.println(lists.isEmpty());
        //lists.clear();
        System.out.println(lists.isEmpty());
        lists.addAll(lists);
        System.out.println(lists);
        System.out.println(lists.containsAll(lists));


    }
}


class Car{

    private String name;
    private Double price;

    public Car(String name, Double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
