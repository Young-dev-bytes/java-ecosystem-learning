package java_basic._1101_singleton.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 13:33
 */

import java.io.*;

class SerializableExampleClass implements Serializable {
    private static final long serialVersionUID = 1L;

    private int age;

    SerializableExampleClass() {
        System.out.println("SerializableExampleClass constructor");
        this.age = 20;
    }

    @Override
    public String toString() {
        return "SerializableExampleClass{" +
                "age=" + age +
                '}';
    }
}

public class SerializationExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // 创建实例并序列化到文件中
        SerializableExampleClass instance = new SerializableExampleClass();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("data.txt"));
        out.writeObject(instance);
        out.close();

        // 从文件中反序列化出实例
        ObjectInputStream in = new ObjectInputStream(new FileInputStream("data.txt"));
        SerializableExampleClass newInstance = (SerializableExampleClass) in.readObject();
        in.close();
    }
}

