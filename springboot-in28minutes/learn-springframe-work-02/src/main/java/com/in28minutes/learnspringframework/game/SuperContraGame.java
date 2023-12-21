package com.in28minutes.learnspringframework.game;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Qualifier("super")
public class SuperContraGame implements GamingConsole {

    public SuperContraGame() {
        System.out.println("SuperContraGame no args constructor");
    }

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
