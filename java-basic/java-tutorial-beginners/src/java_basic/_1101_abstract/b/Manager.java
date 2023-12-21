package java_basic._1101_abstract.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 19:05
 */

public class Manager extends Employee{

    private double bonus;



    public Manager(String name, int id, double salary, double bonus) {
        super(name, id, salary);
        this.bonus = bonus;
    }

    @Override
    void work() {
        System.out.println("经理， 名字" + getName() + " 工作中");
    }
}
