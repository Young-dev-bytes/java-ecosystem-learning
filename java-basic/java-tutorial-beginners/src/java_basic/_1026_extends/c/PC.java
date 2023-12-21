package java_basic._1026_extends.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/10/27 14:20
 */

public class PC extends Computer{

    private String brand;


    public PC(String cpu, int disk, int memory, String brand) {
        super(cpu, disk, memory);
        this.brand = brand;
    }

    public void printInfo () {
        System.out.println(getDetails() + " brand=" + brand);
    }

}
