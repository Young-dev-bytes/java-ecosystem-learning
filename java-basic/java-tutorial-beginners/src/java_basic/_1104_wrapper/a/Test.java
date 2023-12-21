package java_basic._1104_wrapper.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/4 16:23
 */

public class Test {
    public static void main(String[] args) {

        Double d1 = 100d;
        System.out.println(d1);

        int m = 10;
        Integer m1 = m;
        System.out.println(m1);


        // -128 - 127
        Integer i = 127;
        Integer j = new Integer(127);
        System.out.println(i == j);
        System.out.println(i.hashCode());
        System.out.println(j.hashCode());

        String s = new String("123");
        String s1 = new String("99");
        s = s1;
        System.out.println(s.toString());


    }
}
