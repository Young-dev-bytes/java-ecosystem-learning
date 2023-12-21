package java_basic._1101_abstract.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 19:10
 */

public class Demo {

    public static void main(String[] args) {

        Employee commonEmployee = new CommonEmployee("lisa", 1001, 10000);
        Employee manager = new Manager("mike", 1002, 20000, 3000);
        commonEmployee.work();
        manager.work();
    }
}
