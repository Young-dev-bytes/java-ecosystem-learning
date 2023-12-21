package java_basic._1121_tankGame.tank_02;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/21 14:45
 */

public class Tank {

    private int x;
    private int y;
    private int direct;
    private int speed;

    public Tank(int x, int y, int direct) {
        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    public Tank(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
