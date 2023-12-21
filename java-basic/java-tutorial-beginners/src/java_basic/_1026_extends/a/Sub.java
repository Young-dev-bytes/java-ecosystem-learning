package java_basic._1026_extends.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/26 17:28
 */

public class Sub extends Base{

    public Sub() {
        super(2);
        System.out.println("sub()....");
    }

    public void sayOk() {
        System.out.println(n1 + "-" + n2 + "-" + n3 );
        System.out.println(getN4());

        test100();
        test200();
        test300();

        callTest400();
    }

    public static void main(String[] args) {

        Sub sub = new Sub();
        sub.sayOk();
    }
}
