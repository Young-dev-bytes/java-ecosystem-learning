package java_basic._1028_dynamic_bind.e;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 18:10
 */

public class Worker extends Employee{


    public Worker(String name, double salary) {
        super(name, salary);
    }

    public void work() {
        System.out.println(getName() + "普通员工 work...");
    }

    @Override
    public double getAnnual() {
        return super.getAnnual();
    }


}
