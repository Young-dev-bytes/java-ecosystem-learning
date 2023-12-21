package com.in28minutes.learnspringframework.game;

import com.in28minutes.learnspringframework.game.GameRunner;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@ComponentScan
public class GamingAppLauncherApplication {

    /*@Bean
    public GameRunner gameRunner(
            @Qualifier("superContraGame")
            GamingConsole game) {
        return new GameRunner(game);
    }*/

    public static void main(String[] args) {

        /*var game = new MarioGame();
        var game = new SuperContraGame();

        var game = new PacmanGame(); //1: Object Creation

        var gameRunner = new GameRunner(game);
        2: Object Creation + Wiring of Dependencies
        Game is a Dependency of GameRunner

        gameRunner.run();*/

        try (var applicationContext =
                     new AnnotationConfigApplicationContext(GamingAppLauncherApplication.class)) {
            Arrays.stream(applicationContext.getBeanDefinitionNames()).forEach(System.out::println);
            applicationContext.getBean(GameRunner.class).run();
            // applicationContext.getBean(GamingConsole.class).up();
        }
    }
}
