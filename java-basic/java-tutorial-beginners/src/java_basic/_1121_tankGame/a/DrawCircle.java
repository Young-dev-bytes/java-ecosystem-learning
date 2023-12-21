package java_basic._1121_tankGame.a;

import javax.swing.*;
import java.awt.*;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/21 13:22
 */

public class DrawCircle extends JFrame{

    private MyPanel mp = null;
    public static void main(String[] args) {

        new DrawCircle();

    }

    public DrawCircle() {
        mp = new MyPanel();
        this.add(mp);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
}

class MyPanel extends JPanel {

    // 绘图方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("paint 方法被调用了~");
        g.drawOval(10, 10, 100, 100);
    }
}
