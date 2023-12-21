package java_basic._1101_abstract.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/1 19:06
 */

public class CommonEmployee extends Employee{

    private String name;

    public CommonEmployee(String name, int id, double salary){
        super(name, id, salary);
        this.name = name;
    }

    @Override
    void work() {
        System.out.println("普通员工， 名字" + getName() + " 工作中");
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
