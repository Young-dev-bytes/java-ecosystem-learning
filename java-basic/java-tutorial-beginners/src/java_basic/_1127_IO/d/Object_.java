package java_basic._1127_IO.d;

import java.io.*;

public class Object_ {
    public static void main(String[] args) {
        // saveObj();
        // readObj();
        System.out.println(System.in.getClass());
        System.out.println(System.out.getClass());
    }

    public static void saveObj() {
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(new FileOutputStream("/Users/share/Desktop/data.dat"));
            oos.writeInt(100);
            oos.write(200);
            oos.writeBoolean(true);
            oos.writeChar('p');
            oos.writeDouble(9.5);
            oos.writeUTF("陈秋阳");
            oos.writeObject(new Dog("小黑", 20));
            oos.writeObject(new Dog("小白", 10));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    public static void readObj() {

        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(new FileInputStream("/Users/share/Desktop/data.dat"));
            /*oos.writeInt(100);
            oos.write(200);
            oos.writeBoolean(true);
            oos.writeChar('p');
            oos.writeDouble(9.5);
            oos.writeUTF("陈秋阳");
            oos.writeObject(new Dog("小黑", 20));
            oos.writeObject(new Dog("小白", 10));*/

            // System.out.println(ois.read());
            System.out.println(ois.readInt());
            System.out.println(ois.read());
            System.out.println(ois.readBoolean());
            System.out.println(ois.readChar());
            System.out.println(ois.readDouble());
            System.out.println(ois.readUTF());
            Object dog_01 = ois.readObject();
            Object dog_02 = ois.readObject();
            System.out.println("运行类型01： " + dog_01.getClass());
            System.out.println("运行类型02： " + dog_02.getClass());
            System.out.println(dog_01);
            System.out.println(dog_02);

            Dog dog01 = (Dog) dog_01;
            Dog dog02 = (Dog) dog_02;
            System.out.println(dog01.getName());
            System.out.println(dog02.getName());

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}

class Dog implements Serializable {

    private static final long serialVersionUID = 1L;

    public String name;
    public int age;

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
