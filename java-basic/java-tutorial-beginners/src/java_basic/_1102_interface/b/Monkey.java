package java_basic._1102_interface.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 14:26
 */

public class Monkey {

    private String name;

    public Monkey(String name) {
        this.name = name;
    }

    public void climbing() {
        System.out.println(name + "  会爬树...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        System.out.println("");
    }


}
