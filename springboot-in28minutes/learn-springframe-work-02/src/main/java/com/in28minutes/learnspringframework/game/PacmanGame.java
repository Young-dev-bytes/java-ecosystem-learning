package com.in28minutes.learnspringframework.game;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
// @Primary
public class PacmanGame implements GamingConsole {

    public PacmanGame() {
        System.out.println("PacmanGame no args constructor");
    }

    public void up() {
        System.out.println("up");
    }

    public void down() {
        System.out.println("down");
    }

    public void left() {
        System.out.println("left");
    }

    public void right() {
        System.out.println("right");
    }

}
