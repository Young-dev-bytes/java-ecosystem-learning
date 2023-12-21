package java_basic._1101_singleton.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 13:29
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

class ReflectExampleClass {

    private String name;

    private ReflectExampleClass() {
        System.out.println("ReflectExampleClass constructor");
        this.name = "test";
    }


    @Override
    public String toString() {
        return "ReflectExampleClass{" +
                "name='" + name + '\'' +
                '}';
    }
}

public class ReflectExample {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<ReflectExampleClass> constructor = ReflectExampleClass.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        ReflectExampleClass instance = constructor.newInstance();
        System.out.println(instance);
    }
}

