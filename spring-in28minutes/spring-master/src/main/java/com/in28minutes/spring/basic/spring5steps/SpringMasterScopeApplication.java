package com.in28minutes.spring.basic.spring5steps;

import com.in28minutes.spring.basic.spring5steps.scope.PersonDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackages = "com.in28minutes.spring.basic.spring5steps")
public class SpringMasterScopeApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMasterScopeApplication.class);
    public static void main(String[] args) {
        try (AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(SpringMasterScopeApplication.class)) {
            PersonDAO personDAO = applicationContext.getBean(PersonDAO.class);
            PersonDAO personDAO2 = applicationContext.getBean(PersonDAO.class);

            LOGGER.info("{}", personDAO);
            LOGGER.info("{}", personDAO.getJdbcConnection());

            LOGGER.info("{}", personDAO2);
            LOGGER.info("{}", personDAO2.getJdbcConnection());
        }
    }
}
