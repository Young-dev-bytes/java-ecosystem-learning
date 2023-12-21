package java_basic._1109_collection.set.a;


import java.util.HashSet;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/13 19:24
 */

public class SetMethod {
    @SuppressWarnings("all")
    public static void main(String[] args) {

//        Set set = new HashSet();
//        set.add("john");
//        set.add("lucy");
//        set.add("john");
//        set.add("jack");
//        set.add(null);
        // set.add(null);

        // System.out.println(set);
        HashSet hashSet = new HashSet();

        // hashSet.add("java");
        // hashSet.add("php");
        // hashSet.add("java");

        for (int i = 1; i <= 100; i++) {
            hashSet.add(new Dog("tom"));

        }
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));
        hashSet.add(new Dog("tom"));

        hashSet.add(new String("young"));
        hashSet.add(new String("young"));

        System.out.println(hashSet);


        String str1 = new String("chen");
        String str2 = new String("chen");
        String str3 = "chen";
        System.out.println(str1 == str2);
        System.out.println(str1.hashCode());
        System.out.println(str2.hashCode());
        System.out.println(str3.hashCode());


    }
}

class Dog {

    private String name;

    public Dog(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Dog{" +
                "name='" + name + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return 100;
    }
}
