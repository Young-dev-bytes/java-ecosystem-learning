package java_basic._1101_singleton.b;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 14:56
 */

public class Demo {

    static void saveToFile(SingletonTest singleton, String filename) throws Exception {
        try (FileOutputStream fileOut = new FileOutputStream(filename);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(singleton);
        }
    }

    static SingletonTest readFromFile(String filename) throws Exception {
        try (FileInputStream fileIn = new FileInputStream(filename);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return (SingletonTest) in.readObject();
        }
    }

    public static void main(String[] args) throws Exception {

        SingletonTest singleton = SingletonTest.getInstance();
        singleton.setValue(3000);

        String filename = "singleText.text";
        saveToFile(singleton, filename);
        singleton.setValue(4000);

        SingletonTest singleton1 = readFromFile(filename);


        System.out.println(singleton.hashCode());
        System.out.println(singleton1.hashCode());

        System.out.println("1---" + singleton);
        System.out.println("2---" + singleton1);

    }
}
