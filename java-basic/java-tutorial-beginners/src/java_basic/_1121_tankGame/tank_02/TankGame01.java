package java_basic._1121_tankGame.tank_02;


import javax.swing.JFrame;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/21 14:50
 */

public class TankGame01 extends JFrame {


    public TankGame01() {

        // System.out.println(this.getClass());

        MyPanel mp = new MyPanel();
        this.add(mp);
        this.setSize(1000, 750);
        this.addKeyListener(mp);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        TankGame01 tankGame01 = new TankGame01();
    }

}
