// package com.in28minutes.learnspringframework;
//
// import com.in28minutes.learnspringframework.game.*;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.ComponentScan;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.context.annotation.Primary;
//
// @Configuration
// @ComponentScan(value = "com")
// public class GamingConfiguration {
//
//     /*@Bean
//     @Primary
//     public GamingConsole pacmanGame() {
//         return new PacmanGame();
//     }*/
//     /*@Bean
//     public GamingConsole marioGame() {
//         return new MarioGame();
//     }*/
//
//    /* @Bean
//     public GamingConsole superContraGame() {
//         return new SuperContraGame();
//     }*/
//
//     @Bean
//     public GameRunner gameRunner(@Qualifier("superContraGame") GamingConsole superContraGame) {
//         return new GameRunner(superContraGame);
//     }
//
// }
