package java_basic._1104_wrapper.b;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/6 10:46
 */

public class Person {

    private String name;
    private Vehicles vehicles;

    public Person(String name, Vehicles vehicles) {
        this.name = name;
        this.vehicles = vehicles;
    }

    public void passRiver() {
        if (!(vehicles instanceof Boat)) {
            vehicles = VehicleFactory.getBoat();
        }
        vehicles.work();
    }

    public void passHotMountain() {
        if(!(vehicles instanceof Plane)) {
            vehicles = VehicleFactory.getPlane();
        }
        vehicles.work();
    }

    public void common() {
        if (!(vehicles instanceof Horse)) {
            vehicles = VehicleFactory.getHorse();
        }
        vehicles.work();
    }

    public static void main(String[] args) {

        Person tang = new Person("唐僧", new Horse());
        tang.common();
        tang.passRiver();
        tang.passHotMountain();
        // System.out.println(null instanceof Object);
    }
}
