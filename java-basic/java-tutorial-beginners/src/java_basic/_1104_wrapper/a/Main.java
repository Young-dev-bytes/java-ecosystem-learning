package java_basic._1104_wrapper.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 10:01
 */

public class Main {
    public static void main(String[] args) {

        CellPhone cellPhone = new CellPhone();
        Inner inner = new Inner();
        cellPhone.testWork(inner,1,2);


    }
}

class Inner implements Computer{

    @Override
    public void work(int n1, int n2) {
        System.out.println(n1 + n2);
    }
}
