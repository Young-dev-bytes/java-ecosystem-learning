package java_basic._1129_reflection.b;

import java.lang.reflect.Field;

public class Main_ {
    public static void main(String[] args) throws Exception {

        Car car = new Car();

        Class carClass = Car.class;
        Class clazz = Class.forName("java_basic._1129_reflection.b.Car");
        System.out.println(clazz.hashCode() == carClass.hashCode());
        System.out.println(clazz.hashCode() == car.getClass().hashCode());
        System.out.println(carClass);
        System.out.println(clazz);

        System.out.println(clazz.getClass());
        System.out.println(clazz.getPackage().getName());
        System.out.println(clazz.getName());
        Object o = clazz.newInstance();
        System.out.println(o);
        Field brand = clazz.getField("brand");
        System.out.println(brand.get(o));
        brand.set(o, "小米");
        System.out.println(brand.get(o));

        Field[] fields = clazz.getFields();
        for (Field field : fields) {
            System.out.println(field.get(o));
        }

    }
}
