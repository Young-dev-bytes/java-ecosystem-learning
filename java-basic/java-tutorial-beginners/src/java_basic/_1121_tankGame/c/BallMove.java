package java_basic._1121_tankGame.c;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Young.
 * @version 1.0
 * @date 2023/11/21 15:20
 */

public class BallMove extends JFrame {

    MyPanel myPanel = null;

    public BallMove() {
        myPanel = new MyPanel();
        this.add(myPanel);
        this.addKeyListener(myPanel);
        this.setSize(400, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args) {

        BallMove ballMove = new BallMove();

    }
}

class MyPanel extends JPanel implements KeyListener {

    int x = 10;
    int y = 10;

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillOval(x, y, 20, 20);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        System.out.println(e.getKeyCode());
        System.out.println(e.getKeyChar());

        if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            y += 2;
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            y -= 2;
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            x -= 2;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            x += 2;
        }

        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
