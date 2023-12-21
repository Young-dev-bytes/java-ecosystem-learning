package java_basic._1104_wrapper.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 10:52
 */

public class VehicleFactory {

    private static Horse horse = new Horse();

    private VehicleFactory() {
    }

    public static Boat getBoat() {
        return new Boat();
    }

    public static Horse getHorse() {
        return horse;
    }

    public static Plane getPlane() {
        return new Plane();
    }
}
