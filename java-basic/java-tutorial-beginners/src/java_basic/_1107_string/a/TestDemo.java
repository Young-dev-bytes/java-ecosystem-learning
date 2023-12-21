package java_basic._1107_string.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/7 17:06
 */

public class TestDemo {
    public static void main(String[] args) {

        /*StringTest st = new StringTest(new char[]{'e','l'});
        System.out.println("--" + st);
        st.updateValue();
        System.out.println("--" + st);*/



        // System.out.println(c);

        String a = "jack";
        String b = "jack";
        System.out.println(a.compareTo(b));







    }
}

class Test1{
    String str = new String("hsp");
    final char[] ch = {'j','a','v','a'};

    public void change(String str, char ch[]) {
        str = "javaaaaaaaaaaaa";
        ch[0] = 'h';
    }

    public static void main(String[] args) {
        Test1 ex = new Test1();
        ex.change(ex.str, ex.ch);

        System.out.print(ex.str + " and ");
        System.out.println(ex.ch);
    }
}
