package com.in28minutes.learnspringframework;

import com.in28minutes.learnspringframework.game.GameRunner;
import com.in28minutes.learnspringframework.game.GamingConsole;
import com.in28minutes.learnspringframework.game.PacmanGame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App03GamingSpringBeans {

    public static void main(String[] args) {

        /*var game = new MarioGame();
        var game = new SuperContraGame();

        var game = new PacmanGame(); //1: Object Creation

        var gameRunner = new GameRunner(game);
        2: Object Creation + Wiring of Dependencies
        Game is a Dependency of GameRunner

        gameRunner.run();*/

        try (var applicationContext =
                     new AnnotationConfigApplicationContext(GamingConfiguration.class)) {
            applicationContext.getBean(GamingConsole.class).up();
            applicationContext.getBean(GameRunner.class).run();
        }

    }

}
