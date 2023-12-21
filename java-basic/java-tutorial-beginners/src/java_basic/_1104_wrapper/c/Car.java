package java_basic._1104_wrapper.c;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 12:05
 */

public class Car {

    private int temperature;

    public Car(int temperature) {
        this.temperature = temperature;
    }

    class Air {
        public void flow() {
            if (temperature > 40) {
                System.out.println("吹冷气...");

            } else if (temperature >= 0) {
                System.out.println("关掉空调...");
            } else {
                System.out.println("吹暖气...");
            }
        }
    }


    public static void main(String[] args) {
        Car car = new Car(-9);
        Air air = car.new Air();
        air.flow();

    }
}
