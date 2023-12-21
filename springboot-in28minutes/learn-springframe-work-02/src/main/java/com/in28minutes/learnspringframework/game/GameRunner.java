package com.in28minutes.learnspringframework.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

//PacmanGame
@Component
public class GameRunner {

    // @Autowired
    // @Qualifier("superContraGame")
    private GamingConsole game;

    /*public GameRunner() {
        System.out.println("GameRunner no args constructor");
    }*/

    // @Autowired
    public GameRunner(
            // @Qualifier("superContraGame")
            // @Qualifier("super")
                    GamingConsole superContraGame) {
        System.out.println("GameRunner one args constructor");
        this.game = superContraGame;
    }

    // @Autowired
    /*public void setGame(@Qualifier("superContraGame") GamingConsole game) {
        System.out.println("set");
        this.game = game;
    }*/

    public void run() {
        System.out.println("Running game: " + game);
        game.up();
        game.down();
        game.left();
        game.right();
    }
}
