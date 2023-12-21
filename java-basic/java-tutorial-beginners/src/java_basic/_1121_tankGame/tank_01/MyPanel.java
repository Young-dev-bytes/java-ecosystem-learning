package java_basic._1121_tankGame.tank_01;

import java_basic._1121_tankGame.tank_02.Hero;

import javax.swing.*;
import java.awt.*;

class MyPanel extends JPanel {

    java_basic._1121_tankGame.tank_02.Hero hero = null;

    public MyPanel() {
        hero = new Hero(100, 100, 0);
    }

    // 绘图方法
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        System.out.println("paint 方法被调用了~");
        g.fillRect(0, 0, 1000, 750);


        drawTank(hero.getX(), hero.getY(), g, 0, 0);

    }

    /**
     * 画出tank
     *
     * @param x      tank左上角X坐标
     * @param y      tank左上角y坐标
     * @param g      画笔
     * @param direct tank方向
     * @param type   tank类型
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {

        switch (type) {
            case 0:
                g.setColor(Color.cyan);
                break;
            case 1:
                g.setColor(Color.yellow);
                break;
        }

        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            default:
                System.out.println("暂时没有处理");
        }


    }
}
