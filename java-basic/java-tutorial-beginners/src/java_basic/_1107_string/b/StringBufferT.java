package java_basic._1107_string.b;


/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/8 11:28
 */

public class StringBufferT {

    public static void main(String[] args) {

        CharSequence a = "a";
        StringBuffer sb1 = new StringBuffer(a);
        StringBuffer sb2 = new StringBuffer(100);
        StringBuffer sb3 = new StringBuffer("hello");
        System.out.println(sb3);

        String s = new String(sb3);
        System.out.println(s);

        String str = null;
        StringBuffer sb = new StringBuffer();
        sb.append(str);
        System.out.println(sb.length());

        StringBuffer sb5 = new StringBuffer(str);
        System.out.println(sb5);

        StringBuilder stringBuilder = new StringBuilder();



    }
}
