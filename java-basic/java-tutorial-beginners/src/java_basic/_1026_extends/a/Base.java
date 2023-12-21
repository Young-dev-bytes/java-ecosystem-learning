package java_basic._1026_extends.a;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/26 17:24
 */

public class Base {

    public int n1 = 100;
    protected int n2 = 200;
    int n3 = 300;
    private int n4 = 400;

    public Base() {

    }

    public Base(int n1) {
        this.n1 = n1;
    }

    public Base(int n1, int n2, int n3, int n4) {
        this.n1 = n1;
        this.n2 = n2;
        this.n3 = n3;
        this.n4 = n4;
    }

    public void test100() {
        System.out.println("test100");
    }

    protected void test200() {
        System.out.println("test200");
    }

    void test300() {
        System.out.println("test300");
    }

    private void test400() {
        System.out.println("test400");
    }

    public int getN4() {
        return n4;
    }

    public void callTest400() {
        test400();
    }



}
