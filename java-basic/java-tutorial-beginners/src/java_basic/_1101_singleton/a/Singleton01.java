package java_basic._1101_singleton.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 10:39
 */

public class Singleton01 {

    private String name;

    private Singleton01(String name) {
        this.name = name;
        System.out.println("Singleton01 constructor");
        // this.age = 0;
    }

    // public static Singleton01 instance = new Singleton01("hello");
    public static Singleton01 instance;

    public static final int age = 10;

    static {
        System.out.println("static block!");
    }

    // public static Singleton01 getInstance() {
    //     return instance;
    // }
     public static Singleton01 getInstance() {
        if(instance == null){
            synchronized (Singleton01.class){
                if(instance == null){
                    instance = new Singleton01("nihao");
                }
            }
        }
         return instance;
     }

    @Override
    public String toString() {
        return "Singleton01{" +
                "name='" + name + '\'' +
                '}' + age;
    }
}
