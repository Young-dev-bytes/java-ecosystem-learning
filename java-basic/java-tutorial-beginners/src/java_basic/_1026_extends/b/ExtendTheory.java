package java_basic._1026_extends.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/27 11:17
 */

public class ExtendTheory {

    public static void main(String[] args) {
        Son son = new Son();


    }
}

class GrandPa {
    String name = "大头爷爷";
    String hobby = "旅游";
}

class Father extends GrandPa {
    String name = "大头爸爸";
    int age = 39;
}

class Son extends Father {
    String name = "大头儿子";
}
