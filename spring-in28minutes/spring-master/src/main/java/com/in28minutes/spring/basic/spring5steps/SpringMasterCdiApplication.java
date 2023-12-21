package com.in28minutes.spring.basic.spring5steps;

import com.in28minutes.spring.basic.spring5steps.cdi.SomeCdiBussiness;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("com.in28minutes.spring.basic.spring5steps.cdi")
public class SpringMasterCdiApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMasterCdiApplication.class);

    public static void main(String[] args) {

        // ApplicationContext applicationContext = SpringApplication.run(SpringMasterCdiApplication.class, args);
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringMasterBasicApplication.class);

        SomeCdiBussiness someCdiBussiness = applicationContext.getBean(SomeCdiBussiness.class);

        LOGGER.info("{} dao - {}", someCdiBussiness, someCdiBussiness.getSomeCdiDAO());
        System.out.println(someCdiBussiness);
    }

}
