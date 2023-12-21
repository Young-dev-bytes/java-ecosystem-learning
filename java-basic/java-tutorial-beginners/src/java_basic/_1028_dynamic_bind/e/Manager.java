package java_basic._1028_dynamic_bind.e;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/28 18:12
 */

public class Manager extends Employee{
    private double bonus;

    public Manager(String name, double salary, double bonus) {
        super(name, salary);
        this.bonus = bonus;
    }

    public double getBonus() {
        return bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void manager() {
        System.out.println(getName() + "经理 manager...");
    }


    @Override
    public double getAnnual() {
        return super.getAnnual() + bonus;
    }
}
