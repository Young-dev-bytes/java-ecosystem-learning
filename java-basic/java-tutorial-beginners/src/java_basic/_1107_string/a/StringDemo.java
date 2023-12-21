package java_basic._1107_string.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/7 15:06
 */

public class StringDemo {
    public static void main(String[] args) {

        /*String name = "Young";
        // name = "Chen";
        String str = new String("apple");
        System.out.println(str);
        String a = "abc";
        String b = new String("abc");
        System.out.println(a.equals(b));
        System.out.println(a == b);
        System.out.println(a == b.intern());
        System.out.println(b == b.intern());*/

        /*String有一个属性private final char value[],其中value是一个final类，
        不可修改，是指地址不可修改，也就是不可以指向新的地址，而a指向了新的地址，你作何解释*/

        String s1 = "hello";
        String s2 = new String("hello1");
        s1.replace('h','p');
        System.out.println("- " + s1.hashCode());
        s1 = "haha";
        System.out.println("- " + s1.hashCode());
        System.out.println(s1);
        System.out.println(s2.hashCode());
    }
}
