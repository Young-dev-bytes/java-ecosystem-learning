package java_basic._1102_interface.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/2 14:27
 */

public class LittleMonkey extends Monkey implements Fishable{

    public LittleMonkey(String name) {
        super(name);
    }

    @Override
    public void swimming() {
        System.out.println( getName() + " 会游泳...");
    }
}
