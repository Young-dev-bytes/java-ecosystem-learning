package com.in28minutes.learnspringframework.game;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SuperContraGame implements GamingConsole {

    public void up() {
        System.out.println("up");
    }

    public void down() {
        System.out.println("Sit down");
    }

    public void left() {
        System.out.println("Go back");
    }

    public void right() {
        System.out.println("Shoot a bullet");
    }

}
