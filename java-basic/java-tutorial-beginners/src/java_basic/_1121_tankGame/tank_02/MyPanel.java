package java_basic._1121_tankGame.tank_02;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

class MyPanel extends JPanel implements KeyListener {

    private static final int STEPS = 6;
    private static final int DEFAULT_X = 100;
    private static final int DEFAULT_Y = 400;
    private static final int DEFAULT_DIRECT = 0;

    private static final int ENEMY_TANK_SIZE = 3;


    private final Hero hero;
    private final Vector<EnemyTank> enemies;

    public MyPanel() {

        hero = new Hero(DEFAULT_X, DEFAULT_Y, DEFAULT_DIRECT, STEPS);
        enemies = new Vector<>();

        for (int i = 1; i <= ENEMY_TANK_SIZE; i++) {
            enemies.add(new EnemyTank(100 * (i + 1), 10, 2));
            
        }
    }

    // 绘图方法
    @Override
    public void paint(Graphics g) {

        super.paint(g);
        System.out.println("paint 方法被调用了~");
        g.fillRect(0, 0, 1000, 750);

        drawTank(hero.getX(), hero.getY(), g, hero.getDirect(), 1);

        for (EnemyTank enemy : enemies) {
            drawTank(enemy.getX(), enemy.getY(), g, enemy.getDirect(), 0);
        }
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
            default:
                throw new RuntimeException("没有对应的颜色值");
        }

        switch (direct) {
            case 0: // 上
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y);
                break;
            case 1: // 右
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2: // 下
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 30, x + 20, y + 60);
                break;
            case 3: // 左
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                throw new RuntimeException("方向错误");
        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_W) {
            hero.setDirect(0);
            moveUp();
        } else if (e.getKeyCode() == KeyEvent.VK_D) {
            hero.setDirect(1);
            moveRight();
        } else if (e.getKeyCode() == KeyEvent.VK_S) {
            hero.setDirect(2);
            moveDown();

        } else if (e.getKeyCode() == KeyEvent.VK_A) {
            hero.setDirect(3);
            moveLeft();
        }

        this.repaint();

    }

    private void moveLeft() {
        hero.setX(hero.getX() - hero.getSpeed());
    }

    private void moveDown() {
        hero.setY(hero.getY() + hero.getSpeed());
    }

    private void moveRight() {
        hero.setX(hero.getX() + hero.getSpeed());
    }

    private void moveUp() {
        hero.setY(hero.getY() - hero.getSpeed());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
