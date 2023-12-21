package java_basic._1121_tankGame.tank_01;

import javax.swing.*;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/21 14:50
 */

public class TankGame01 extends JFrame {

    MyPanel mp = null;
    public static void main(String[] args) {

        java_basic._1121_tankGame.tank_02.TankGame01 tankGame01 = new java_basic._1121_tankGame.tank_02.TankGame01();

    }

    public TankGame01() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 750);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}
