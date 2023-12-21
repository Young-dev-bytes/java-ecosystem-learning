package java_basic._1101_singleton.b;

import java.io.Serializable;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 14:47
 */

public class SingletonTest implements Serializable {



    private int value;

    private SingletonTest() {
    }

    public static SingletonTest singleton = new SingletonTest();

    public static SingletonTest getInstance() {
        return singleton;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    protected Object readResolve() {
        return singleton;
    }

    @Override
    public String toString() {
        return "SingletonTest{" +
                "value=" + value +
                '}';
    }
}
