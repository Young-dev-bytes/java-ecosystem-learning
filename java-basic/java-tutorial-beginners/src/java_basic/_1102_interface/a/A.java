package java_basic._1102_interface.a;


/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 11:41
 */

public class A extends B implements C {



    public void test() {
    }

    public static void main(String[] args) {

        char a = 97, b = 'a';
        System.out.println(a + 1);
        System.out.println((char)(b + 1));


    }


}

class B {
    public int n1;

}

interface C {
    int n2 = 30;

}


