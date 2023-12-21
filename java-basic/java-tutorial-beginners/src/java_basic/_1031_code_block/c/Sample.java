package java_basic._1031_code_block.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/31 16:51
 */

public class Sample {

    Sample(String s) {
        System.out.println(s);
    }

    Sample() {
        System.out.println("sample 默认构造函数被调用");
    }
}

class Test {
    Sample sam1 = new Sample("sam1成员初始化");
    static Sample sam = new Sample("静态成员sam初始化");

    static {
        System.out.println("static块执行");
        if (sam == null) System.out.println("sam is null");
    }

    Test() {
        System.out.println("Test默认构造函数被调用");
    }

    public static void main(String[] args) {
        Test a = new Test();

    }
}
